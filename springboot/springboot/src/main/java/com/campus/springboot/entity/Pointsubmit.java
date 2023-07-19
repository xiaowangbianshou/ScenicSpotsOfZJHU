package com.campus.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzq
 * @since 2023-06-09 04:30:00
 */
@Getter
@Setter
@TableName("sys_pointsubmit")
public class Pointsubmit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 景点id
     */
    private Integer pointid;

    /**
     * 景点名称
     */
    private String pointname;

    /**
     * 景点图片
     */
    private String img;

    /**
     * 景点校区
     */
    private String area;

    /**
     * 景点大类
     */
    private String typebig;

    /**
     * 景点小类
     */
    private String typesmall;

    /**
     * 景点时间
     */
    private String descri;

    /**
     * 提交时间
     */
    private LocalDateTime submittime;
}
