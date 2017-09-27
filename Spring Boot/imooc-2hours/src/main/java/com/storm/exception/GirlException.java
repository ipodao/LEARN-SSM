package com.storm.exception;

import com.storm.enums.ResultEnum;

/**
 * 自己定义一个异常
 * 多一个code属性
 */
public class GirlException extends Exception {

    private Integer code;

    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
