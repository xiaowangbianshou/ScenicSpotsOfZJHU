package com.campus.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("sys_dict")
public class Dict {
    private String name;
    private String value;
    private String type;
}
