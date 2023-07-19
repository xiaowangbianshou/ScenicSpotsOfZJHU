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
 * @since 2023-06-02 08:26:11
 */
@Getter
@Setter
@TableName("sys_medal")
public class Medal implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 获取条件
     */
    private String condit;

    /**
     * 勋章类型
     */
    private String type;

    /**
     * 是否禁用
     */
    private Boolean enable;
}
