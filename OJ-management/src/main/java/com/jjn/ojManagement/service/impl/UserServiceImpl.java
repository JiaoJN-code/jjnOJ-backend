package com.jjn.ojManagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jjn.ojManagement.common.ErrorCode;
import com.jjn.ojManagement.constant.UserConst;
import com.jjn.ojManagement.exception.BusinessException;
import com.jjn.ojManagement.mapper.UserMapper;
import com.jjn.ojManagement.model.entity.User;
import com.jjn.ojManagement.model.vo.LoginUserVo;
import com.jjn.ojManagement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author 焦久宁
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2023-11-20 20:06:30
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "jjn";

    /**
     * 用户登录
     *
     * @param userAccount  账户
     * @param userPassword 密码
     * @param request
     * @return
     */
    @Override
    public LoginUserVo userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1. 校验
        // 用户名最低四位
        if (userAccount.length() < 3) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号错误");
        }
        if (userPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
        }
        // 2. 密码加密
//        String md5Password = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 3. 根据用户名,密码查询数据库
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", userPassword);
        User user = this.baseMapper.selectOne(queryWrapper);
        // user为空
        if (Objects.isNull(user)) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名密码错误");
        }
        // 4. 将查询的用户信息保存到session中
        LoginUserVo loginUserVo = new LoginUserVo();
        BeanUtils.copyProperties(user, loginUserVo);
        request.getSession().setAttribute(UserConst.USER_LOGIN_STATE, loginUserVo);
        // 5. 返回
        return loginUserVo;
    }

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    @Override
    public LoginUserVo getLoginUser(HttpServletRequest request) {
        // 1. 判断是否登录
        LoginUserVo loginUserVo = (LoginUserVo) request.getSession().getAttribute(UserConst.USER_LOGIN_STATE);
        if (Objects.isNull(loginUserVo) || Objects.isNull(loginUserVo.getId())) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return loginUserVo;
    }

    @Override
    public Boolean isAdmin(HttpServletRequest request) {
        LoginUserVo loginUser = (LoginUserVo) request.getSession().getAttribute(UserConst.USER_LOGIN_STATE);
        return loginUser.getUserRole().equals(UserConst.ADMIN_ROLE);
    }
}




