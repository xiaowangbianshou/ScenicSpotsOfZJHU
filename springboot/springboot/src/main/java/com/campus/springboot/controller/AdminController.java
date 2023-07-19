package com.campus.springboot.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.springboot.common.Constants;
import com.campus.springboot.common.Result;
import com.campus.springboot.controller.dto.AdminDTO;
import com.campus.springboot.controller.dto.UserDTO;
import com.campus.springboot.entity.User;
import com.campus.springboot.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.campus.springboot.service.AdminService;
import com.campus.springboot.entity.Admin;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzq
 * @since 2023-06-06 01:24:14
 */
@RestController
@RequestMapping("/admin")
    public class AdminController {

        @Resource
        private AdminService adminService;

        //登录接口
        @PostMapping("/login")
        public Result login(@RequestBody AdminDTO adminDTO) {
                String username = adminDTO.getUsername();
                String password = adminDTO.getPassword();
                if (StringUtils.isBlank(username)||StringUtils.isBlank(password)){
                        return Result.error(Constants.CODE_400,"参数错误");
                }
                AdminDTO dto = adminService.login(adminDTO);
                return Result.success(dto);
        }

        @GetMapping("/username/{username}")
        public Result findOneInfo(@PathVariable String username){
            QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username",username);
            return Result.success(adminService.getOne(queryWrapper));
        }
        // 新增或者更新
        @PostMapping
        public Result save(@RequestBody Admin admin) {
                return Result.success(adminService.saveOrUpdate(admin));
        }

        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                return Result.success(adminService.removeById(id));
        }

        @PostMapping("/del/batch")
        public Result deleteBatch(@RequestBody List<Integer> ids) {
                return Result.success(adminService.removeByIds(ids));
        }

        @GetMapping
        public Result findAll() {
                return Result.success(adminService.list());
        }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
                return Result.success(adminService.getById(id));
        }

        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNumber,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String username,
                               @RequestParam(defaultValue = "") String classname,
                               @RequestParam(defaultValue = "") String adminid) {
                IPage<Admin> page = new Page<>(pageNumber, pageSize);
                QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
                if (!"".equals(username)) {
                        queryWrapper.like("username", username);
                }
                if (!"".equals(classname)) {
                        queryWrapper.like("classname", classname);
                }
                if (!"".equals(adminid)) {
                        queryWrapper.like("adminid", adminid);
                }
                queryWrapper.orderByDesc("id");
                Admin currentAdmin = TokenUtils.getCurrenAdmin();
                return Result.success(adminService.page(page, queryWrapper));
        }
}
