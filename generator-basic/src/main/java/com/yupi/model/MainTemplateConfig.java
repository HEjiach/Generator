package com.yupi.model;

import lombok.Data;

@Data
public class MainTemplateConfig {
    //填充值
    private String author ="hejiachen";
    //输出消息
    private String outputText ="输出结果";
    //是否循环
    private boolean loop;
}
