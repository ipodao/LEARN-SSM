package com.seckill.enums;

/**
 * 使用枚举表示常量数据字段
 */
public enum SeckillStatEnum {

    SUCCESS(1, "秒杀成功"),
    END(0, "秒杀结束"),
    REPEAT_KILL(-1, "重复秒杀"),
    INNER_ERROR(-2, "系统异常"),
    DATE_REWRITE(-3, "数据篡改");

    private int state;
    private String stateinfo;

    SeckillStatEnum(int state, String info) {
        this.state = state;
        this.stateinfo = info;
    }

    public int getState() {
        return state;
    }


    public String getStateinfo() {
        return stateinfo;
    }

    public static SeckillStatEnum stateOf(int index) {
        for (SeckillStatEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
