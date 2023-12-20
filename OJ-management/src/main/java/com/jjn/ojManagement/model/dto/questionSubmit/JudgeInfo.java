package com.jjn.ojManagement.model.dto.questionSubmit;

import lombok.Data;

/**
 * @author 焦久宁
 * @date 2023/12/20
 */
@Data
public class JudgeInfo {
    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 程序执行时间(ms)
     */
    private int time;

    /**
     * 程序执行内存(kb)
     */
    private int memory;
}
