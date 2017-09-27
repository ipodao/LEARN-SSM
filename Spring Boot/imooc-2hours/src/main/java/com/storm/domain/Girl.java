package com.storm.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 数据库对应实体类
 * 会在数据库自动创建表
 */
@Entity
public class Girl {
    @Override
    public String toString() {
        return "Girl{" +
                "id=" + id +
                ", cupSize='" + cupSize + '\'' +
                ", age=" + age +
                '}';
    }

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank(message = "这个*字段必传")
    private String cupSize;

    @Min(value = 18, message = "未成年少女禁止入内")
//    @NotNull
//    @Max()
//    @Length()
    private Integer age;

    @NotNull(message = "金额必传")
    private Double money;

    //必须有一个无参构造方法
    public Girl() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
