package com.campus.springboot.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.springboot.common.Constants;
import com.campus.springboot.common.Result;
import com.campus.springboot.controller.dto.UserDTO;
import com.campus.springboot.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.campus.springboot.service.UserService;
import com.campus.springboot.entity.User;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzq
 * @since 2023-04-15 05:19:49
 */
@RestController
@RequestMapping("/user")
public class UserController {

        @Autowired
        private UserService userService;

        //登录接口
        @PostMapping("/login")
        public Result login(@RequestBody UserDTO userDTO) {
                String username = userDTO.getUsername();
                String password = userDTO.getPassword();
                if (StringUtils.isBlank(username)||StringUtils.isBlank(password)){
                        return Result.error(Constants.CODE_400,"参数错误");
                }
                UserDTO dto = userService.login(userDTO);
                return Result.success(dto);
        }

        //注册
        @PostMapping("/register")
        public Result register(@RequestBody UserDTO userDTO) {
                String username = userDTO.getUsername();
                String password = userDTO.getPassword();
                if (StringUtils.isBlank(username)||StringUtils.isBlank(password)){
                        return Result.error(Constants.CODE_400,"参数错误");
                }
                return Result.success(userService.register(userDTO));
        }

        //获取单一用户信息
        @GetMapping("/username/{username}")
        public Result findOneInfo(@PathVariable String username){
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("username",username);
                return Result.success(userService.getOne(queryWrapper));
        }

        // 新增或者更新
        @PostMapping
        public Result save(@RequestBody User user) {
                return Result.success(userService.saveOrUpdate(user));
        }

        //删除某一用户
        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                return Result.success(userService.removeById(id));
        }

        //批量删除用户
        @PostMapping("/del/batch")
        public Result deleteBatch(@RequestBody List<Integer> ids) {
                return Result.success(userService.removeByIds(ids));
        }

        @GetMapping
        public Result findAll() {
                return Result.success(userService.list());
        }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
                return Result.success(userService.getById(id));
        }

        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNumber,
                                    @RequestParam Integer pageSize,
                                    @RequestParam(defaultValue = "") String username,
                                    @RequestParam(defaultValue = "") String classname,
                                    @RequestParam(defaultValue = "") String studentid) {
                IPage<User> page = new Page<>(pageNumber, pageSize);
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                if (!"".equals(username)) {
                        queryWrapper.like("username", username);
                }
                if (!"".equals(classname)) {
                        queryWrapper.like("classname", classname);
                }
                if (!"".equals(studentid)) {
                        queryWrapper.like("studentid", studentid);
                }
                queryWrapper.orderByDesc("id");
                User currentUser = TokenUtils.getCurrenUser();
                return Result.success(userService.page(page, queryWrapper));
        }
}

