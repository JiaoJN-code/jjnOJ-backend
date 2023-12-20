package com.jjn.ojManagement.service;

import com.jjn.ojManagement.model.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 焦久宁
* @description 针对表【question(题目)】的数据库操作Service
* @createDate 2023-12-20 14:07:11
*/
public interface QuestionService extends IService<Question> {
    /**
     * 校验添加题目是否合法
     * @param question
     * @param flag
     */
    void validPost(Question question, Boolean flag);
}
