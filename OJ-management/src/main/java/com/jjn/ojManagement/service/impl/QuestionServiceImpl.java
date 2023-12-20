package com.jjn.ojManagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jjn.ojManagement.common.ErrorCode;
import com.jjn.ojManagement.exception.BusinessException;
import com.jjn.ojManagement.exception.ThrowUtils;
import com.jjn.ojManagement.model.entity.Question;
import com.jjn.ojManagement.service.QuestionService;
import com.jjn.ojManagement.mapper.QuestionMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author 焦久宁
* @description 针对表【question(题目)】的数据库操作Service实现
* @createDate 2023-12-20 14:07:11
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

    @Override
    public void validPost(Question question, Boolean flag) {
        if (question == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 创建时参数不能为空
        String title = question.getTitle();
        String content = question.getContent();
        if (flag) {
            ThrowUtils.throwIf(StringUtils.isAllBlank(title, content), ErrorCode.PARAMS_ERROR);
        }
        // 校验标题长度
        if (StringUtils.isNotBlank(title) && title.length() > 80) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "标题过长");
        }
        // 校验内容长度
        if (StringUtils.isNotBlank(content) && content.length() > 8000) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长");
        }
    }
}




