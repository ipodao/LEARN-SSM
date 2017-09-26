package com.seckill.service;

import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillCloseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> seckillList = seckillService.getSeckillList();
//        System.out.println(seckillList);
        logger.info("list={}", seckillList);
    }

    @Test
    public void getById() throws Exception {
        long seckillId = 1006;
        Seckill seckill = seckillService.getById(seckillId);
//        System.out.println(seckill);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        long seckillId = 1006;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        logger.info("exposer={}", exposer);
        //exposer=Exposer{exposed=true, md5='e0dd9333af7d55a10739ca66bb7da6d6', seckillId=1006, now=0, start=0, end=0}

    }

    @Test
    public void onlyexecuteSeckill() throws Exception {
        long seckillId = 1006;
        long phone = 41315316135L;
        String md5 = "e0dd9333af7d55a10739ca66bb7da6d6";
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, phone, md5);
            logger.info("result={}", seckillExecution);
            //result=SeckillExecution{seckillId=1006, state=1, stateInfo='秒杀成功', successKilled=SuccessKilled{seckillId=1006, userPhone=41315316135, state=0, createTime=Mon Sep 25 17:31:38 CST 2017}}
        } catch (RepeatKillException e) {
            logger.error(e.getMessage(), e);
        } catch (SeckillCloseException e) {
            logger.error(e.getMessage(), e);
        }
    }

    //完整逻辑代码测试，注意可重复执行
    @Test
    public void testSeckillLogic() throws Exception {
        long seckillId = 1006;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()) {
            logger.info("expose={}", exposer);
            long userPhone = 13476191876L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
                System.out.println(seckillExecution);
            } catch (RepeatKillException e) {
                logger.error(e.getMessage(), e);
            } catch (SeckillCloseException e) {
                logger.error(e.getMessage(), e);
            }
        } else {
            //秒杀未开启
            logger.info("expose={}", exposer);
        }
    }

    @Test
    public void xecuteSeckillProcedure(){
        long seckillId = 1005;
        long phone = 41315316135L;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()){
            String md5 = exposer.getMd5();
            SeckillExecution seckillExecution = seckillService.executeSeckillProcedure(seckillId, phone, md5);
            logger.info(seckillExecution.getStateInfo());
        }
    }
}