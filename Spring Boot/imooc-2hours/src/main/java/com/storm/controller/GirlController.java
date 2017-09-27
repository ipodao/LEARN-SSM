package com.storm.controller;

import com.storm.aspect.HttpAspect;
import com.storm.domain.Girl;
import com.storm.domain.Result;
import com.storm.repository.GirlRepository;
import com.storm.service.GirlService;
import com.storm.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    /**
     * 查询所有女生列表
     *
     * @return
     */
    @GetMapping(value = "/girls")
    public List<Girl> girlList() {
        logger.info("girlList");
        return girlRepository.findAll();
    }

    /**
     * 通过年龄查询所有女生列表
     *
     * @return
     */
    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age) {
        return girlRepository.findByAge(age);
    }

    /**
     * 添加一个女生
     * x-www-form-urlencoded ???
     *
     * @return
     */
    @PostMapping(value = "/girls")
    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {//发生错误
//            return null;
            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        girl.setCupSize(girl.getCupSize());
        girl.setAge(girl.getAge());

        return ResultUtil.success(girlRepository.save(girl));
    }

    /**
     * 查询一个女生
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id) {
        return girlRepository.findOne(id);
    }

    /**
     * 更新一个女生
     * 注意：postman测试时，Put 方式时选择 x-www-form-urlencoded 格式
     *
     * @param id
     * @return
     */
    @PutMapping(value = "/girls/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age) {

        Girl girl = new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return girlRepository.save(girl);
    }

    /**
     * 删除一个女生
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id) {
        girlRepository.delete(id);
    }

    /**
     * 同时插入两个女生
     */
    @PostMapping(value = "/girls/two")
    public void girlTwo() {
        girlService.insertTwo();
    }

    /**
     * 统一异常处理！！！
     *
     * @param id
     * @throws Exception
     */
    @GetMapping(value = "/girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception {
        girlService.getAge(id);
    }
}
