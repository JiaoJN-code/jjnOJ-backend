package com.jjn.ojManagement.model.dto.question;

import lombok.Data;

/**
 * @author 焦久宁
 * @date 2023/12/20
 */
@Data
public class JudgeCase {
    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;
}
