package com.jjn.ojManagement.controller;

import com.jjn.ojManagement.common.BaseResponse;
import com.jjn.ojManagement.common.DeleteRequest;
import com.jjn.ojManagement.common.ErrorCode;
import com.jjn.ojManagement.common.ResultUtils;
import com.jjn.ojManagement.exception.BusinessException;
import com.jjn.ojManagement.exception.ThrowUtils;
import com.jjn.ojManagement.model.dto.question.QuestionAddRequest;
import com.jjn.ojManagement.model.entity.Question;
import com.jjn.ojManagement.model.entity.User;
import com.jjn.ojManagement.model.vo.LoginUserVo;
import com.jjn.ojManagement.service.QuestionService;
import com.jjn.ojManagement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目接口
 *
 * @author 焦久宁
 * @date 2023/12/20
 */
@RestController
@RequestMapping("/question")
@Slf4j
public class QuestionController {
    @Resource
    private QuestionService questionService;

    @Resource
    private UserService userService;

    // region 增删改查
    /**
     * 添加题目
     * @param questionAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addQuestion(@RequestBody QuestionAddRequest questionAddRequest, HttpServletRequest request) {
        if (questionAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Question question = new Question();
        BeanUtils.copyProperties(questionAddRequest, question);
        questionService.validPost(question, true);
        Long loginUserId = userService.getLoginUser(request).getId();
        question.setId(loginUserId);
        question.setFavourNum(0);
        question.setSubmitNum(0);
        question.setThumbNum(0);
        question.setAcceptedNum(0);
        boolean save = questionService.save(question);
        if (!save) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(question.getId());
    }

    /**
     * 删除题目
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteQuestion(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 判断题目是否存在
        Question oldQuestion = questionService.getById(deleteRequest.getId());
        ThrowUtils.throwIf(oldQuestion == null, ErrorCode.NOT_FOUND_ERROR);

        // 删除操作只能本人或管理员操作
        Long loginUserId = userService.getLoginUser(request).getId();
        if (!oldQuestion.getId().equals(loginUserId) || !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = questionService.removeById(deleteRequest.getId());
        return ResultUtils.success(b);
    }


}
