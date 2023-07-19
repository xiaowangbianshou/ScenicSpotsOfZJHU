package com.campus.springboot.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Collections;

/**
 * 代码生成器
 * by wzq
 */
public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3307/campus?serverTimezone=GMT%2b8", "root", "123456")
                // 全局配置
                .globalConfig(builder -> {
                    builder.author("wzq") // 设置作者
                            .commentDate("yyyy-MM-dd hh:mm:ss")   //注释日期
                            .outputDir("D:\\服务外包实验室\\前端\\campus\\springboot\\springboot\\src\\main\\java\\") // 指定输出目录
                            .disableOpenDir() //禁止打开输出目录，默认打开
                    ;
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.campus.springboot") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\服务外包实验室\\前端\\campus\\springboot\\springboot\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("sys_getmodel") // 设置需要生成的表名
                            .addTablePrefix("sys_") // 设置过滤表前缀
                            // Entity 策略配置
                            .entityBuilder()
                            .enableLombok() //开启 Lombok
                            .enableFileOverride() // 覆盖已生成文件
                            .naming(NamingStrategy.underline_to_camel)  //数据库表映射到实体的命名策略：下划线转驼峰命
                            .columnNaming(NamingStrategy.underline_to_camel)    //数据库表字段映射到实体的命名策略：下划线转驼峰命
                            // Mapper 策略配置
                            .mapperBuilder()
                            .enableFileOverride() // 覆盖已生成文件
                            // Service 策略配置
                            .serviceBuilder()
                            .enableFileOverride() // 覆盖已生成文件
                            .formatServiceFileName("%sService") //格式化 service 接口文件名称，%s进行匹配表名，如 UserService
                            .formatServiceImplFileName("%sServiceImpl") //格式化 service 实现类文件名称，%s进行匹配表名，如 UserServiceImpl
                            // Controller 策略配置
                            .controllerBuilder()
                            .enableFileOverride() // 覆盖已生成文件
                    ;
                })
                .execute();

    }
}

