package com.campus.springboot.entity;

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
 * @since 2023-06-10 06:05:38
 */
@Getter
@Setter
@TableName("sys_getmodel")
public class Getmodel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private Integer modelid;

    private LocalDateTime gettime;
}
