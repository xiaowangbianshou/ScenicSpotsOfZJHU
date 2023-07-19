package com.campus.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.springboot.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.campus.springboot.service.GetmodelService;
import com.campus.springboot.entity.Getmodel;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzq
 * @since 2023-06-10 04:54:49
 */
@RestController
@RequestMapping("/getmodel")
public class GetmodelController {

        @Resource
        private GetmodelService getmodelService;

        // 新增或者更新
        @PostMapping
        public Result save(@RequestBody Getmodel getmodel) {
                return Result.success(getmodelService.saveOrUpdate(getmodel));
        }

        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                return Result.success(getmodelService.removeById(id));
        }

        @PostMapping("/del/batch")
        public Result deleteBatch(@RequestBody List<Integer> ids) {
                return Result.success(getmodelService.removeByIds(ids));
        }

        @GetMapping
        public Result findAll() {
                return Result.success(getmodelService.list());
        }

        @GetMapping("/{username}")
        public Result findUser(@PathVariable String username) {
                QueryWrapper<Getmodel> wrapper = new QueryWrapper<>();
                wrapper.eq("username",username);
                return Result.success(getmodelService.list(wrapper));
        }

        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String username) {
                QueryWrapper<Getmodel> queryWrapper = new QueryWrapper<>();
                queryWrapper.like("username",username);
                queryWrapper.orderByDesc("id");
                return Result.success(getmodelService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }


}

