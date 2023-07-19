package com.campus.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.springboot.common.Constants;
import com.campus.springboot.controller.dto.AdminDTO;
import com.campus.springboot.entity.Admin;
import com.campus.springboot.exception.ServiceException;
import com.campus.springboot.mapper.AdminMapper;
import com.campus.springboot.service.AdminService;
import com.campus.springboot.utils.TokenUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzq
 * @since 2023-06-06 01:24:14
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Override
    public AdminDTO login(AdminDTO adminDTO) {
        Admin one = getUserLoginInfo(adminDTO);
        if (one!=null){
            BeanUtil.copyProperties(one,adminDTO,true);
            String token = TokenUtils.genToken("admin"+one.getId().toString(),one.getPassword());
            adminDTO.setToken(token);
            return adminDTO;
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }

    private Admin getUserLoginInfo(AdminDTO adminDTO){
        QueryWrapper<Admin> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("username",adminDTO.getUsername());
        queryWrapper.eq("password",adminDTO.getPassword());
        Admin one;
        try {
            one = getOne(queryWrapper); //从数据库查询用户信息
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return one;
    }


}
