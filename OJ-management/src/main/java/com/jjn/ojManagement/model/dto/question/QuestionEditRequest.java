package com.jjn.ojManagement.model.dto.question;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 焦久宁
 * @date 2023/12/20
 */
@Data
public class QuestionEditRequest implements Serializable {

    /**
     * 题目名称
     */
    private String title;

    /**
     * 题目内容
     */
    private String content;

    /**
     * 标签
     */
    private List<String> tags;

    /**
     * 题目答案
     */
    private String answer;

    /**
     * 判题用例(json数组)
     */
    private List<JudgeCase> judgeCase;

    /**
     * 判题配置(json对象)
     */
    private JudgeConfig judgeConfig;

    private static final long serialVersionUID = 1L;
}
