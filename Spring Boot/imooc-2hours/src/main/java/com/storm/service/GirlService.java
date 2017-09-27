package com.storm.service;

import com.storm.domain.Girl;
import com.storm.enums.ResultEnum;
import com.storm.exception.GirlException;
import com.storm.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    /**
     * 事务管理：
     * A和B两个女生必须同时插入才算成功，不能A成功B不成功
     */
    @Transactional
    public void insertTwo() {
        Girl girlA = new Girl();
        girlA.setCupSize("A");
        girlA.setAge(18);
        girlRepository.save(girlA);

        Girl girlB = new Girl();
        girlB.setCupSize("B");
        girlB.setAge(16);
        girlRepository.save(girlB);
    }

    /**
     * 抛出自己定义的异常！！
     *
     * @param id
     * @throws Exception
     */
    public void getAge(Integer id) throws Exception {
        Girl girl = girlRepository.findOne(id);
        Integer age = girl.getAge();
        if (age < 10) {
            //返回“你在上小学吧”
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        } else if (age > 10 && age < 16) {
            //返回“你可能在上初中”
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }
    }

    /**
     * 通过id查询一个女生的信息
     *
     * @param id
     * @return
     */
    public Girl findOne(Integer id) {
        return girlRepository.findOne(id);
    }
}
