package com.jjn.ojManagement.model.dto.question;

import lombok.Data;

/**
 * 题目配置
 * @author 焦久宁
 * @date 2023/12/20
 */
@Data
public class JudgeConfig {
    /**
     * 时间限制(ms)
     */
    private int timeLimit;

    /**
     * 内存限制(kb)
     */
    private int memoryLimit;

    /**
     * 堆栈限制(kb)
     */
    private int stackLimit;
}
