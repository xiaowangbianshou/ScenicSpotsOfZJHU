package com.campus.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzq
 * @since 2023-06-08 06:06:50
 */
@Getter
@Setter
@TableName("sys_point")
public class Point implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 景点名称
     */
    private String name;

    /**
     * x轴
     */
    private Object coordinatex;

    /**
     * y轴
     */
    private Object coordinatey;

    /**
     * 是否启用
     */
    private Boolean enable;
}
