package ccom.campus.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzq
 * @since 2023-04-15
 */
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("用户名")
      private String username;

      @ApiModelProperty("密码")
      private String password;

      @ApiModelProperty("昵称")
      private String nickname;

      @ApiModelProperty("班级")
      private String classname;

      @ApiModelProperty("学号")
      private String studentid;

      @ApiModelProperty("创建时间")
      private LocalDateTime createtime;
    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public String getUsername() {
        return username;
    }

      public void setUsername(String username) {
          this.username = username;
      }
    
    public String getPassword() {
        return password;
    }

      public void setPassword(String password) {
          this.password = password;
      }
    
    public String getNickname() {
        return nickname;
    }

      public void setNickname(String nickname) {
          this.nickname = nickname;
      }
    
    public String getClassname() {
        return classname;
    }

      public void setClassname(String classname) {
          this.classname = classname;
      }
    
    public String getStudentid() {
        return studentid;
    }

      public void setStudentid(String studentid) {
          this.studentid = studentid;
      }
    
    public LocalDateTime getCreatetime() {
        return createtime;
    }

      public void setCreatetime(LocalDateTime createtime) {
          this.createtime = createtime;
      }

    @Override
    public String toString() {
        return "User{" +
              "id = " + id +
                  ", username = " + username +
                  ", password = " + password +
                  ", nickname = " + nickname +
                  ", classname = " + classname +
                  ", studentid = " + studentid +
                  ", createtime = " + createtime +
              "}";
    }
}
