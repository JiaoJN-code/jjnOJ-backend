package com.jjn.ojManagement.model.vo;

import com.jjn.ojManagement.model.dto.questionSubmit.JudgeInfo;
import lombok.Data;

import java.util.Date;

/**
 * @author 焦久宁
 * @date 2023/12/20
 */
@Data
public class QuestionSubmitVo {
    /**
     * id
     */
    private Long id;

    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;

    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;

    /**
     * 判题状态
     */
    private Integer status;

    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 回答用户id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date creatTime;

    private static final long serialVersionUID = 1L;
}
