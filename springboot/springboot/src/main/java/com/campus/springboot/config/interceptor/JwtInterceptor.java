package com.campus.springboot.config.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.campus.springboot.common.Constants;
import com.campus.springboot.entity.Admin;
import com.campus.springboot.entity.User;
import com.campus.springboot.exception.ServiceException;
import com.campus.springboot.service.AdminService;
import com.campus.springboot.service.UserService;
import com.campus.springboot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        // 执行认证
        if (StrUtil.isBlank(token)) {
            throw new ServiceException(Constants.CODE_401, "无token，请重新登录");
        }
        // 获取 token 中的 user id
//        String userType;
//        try {
//            userType = JWT.decode(token).getAudience().get(0);
//        } catch (JWTDecodeException j) {
//            throw new ServiceException(Constants.CODE_401, "token验证失败，请重新登录");
//        }
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new ServiceException(Constants.CODE_401, "token验证失败，请重新登录");
        }
//        String userType = userId.substring(0,4);
        // 根据token中的userid查询数据库
        JWTVerifier jwtVerifier = null;
        if (userId.substring(0,5).equals("admin")){
            Admin admin = adminService.getById(userId.substring(5));
//         用户密码加签验证 token
            jwtVerifier = JWT.require(Algorithm.HMAC256(admin.getPassword())).build();
        }
        if(userId.substring(0,5).equals("users")){
            User user = userService.getById(userId.substring(5));
//         用户密码加签验证 token
            jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        }
        try {
            jwtVerifier.verify(token); // 验证token
        } catch (JWTVerificationException e) {
            throw new ServiceException(Constants.CODE_401, "token验证失败2，请重新登录");
        }
        return true;
    }
}