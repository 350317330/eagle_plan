package com.example.demo.enums;

import java.util.Objects;

public enum StatusEnum {
    Deleted(-1, "删除"),
    Inactive(0, "禁用"),
    Active(1, "启用");

    private Integer value;

    private String display;

    private StatusEnum(int value, String display) {
        this.value = value;
        this.display = display;
    }

    //显示名
    public String getDisplay() {
        return display;
    }

    //保存值
    public Integer getValue() {
        return value;
    }

    //获取枚举实例
    public static StatusEnum fromValue(Integer value) {
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (Objects.equals(value, statusEnum.getValue())) {
                return statusEnum;
            }
        }
        throw new IllegalArgumentException();
    }
}
