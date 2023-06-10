# campus

#### 介绍
这是湖州师范学院景点打卡系统前后端代码

#### 软件架构
本系统前端采用VUE框架搭建，后端采用Spring Boot框架搭建，数据库采用MySQL


#### 安装教程

1.  本项目需提前安装VUE、node.js springboot环境
2.  下载代码 git clone [URL]
3.  VUE文件夹为前端代码 Springboot文件夹为后端代码 campus.sql为数据库文件

#### 使用说明

1.  打开VUE文件 `npm run server` 运行前端代码
2.  数据库工具打开campus.sql创建数据库campus
3.  IDEA打开Sprintboot文件并运行

#### 参数修改
1.  修改Springboot文件夹下的springboot\springboot\src\main\resources\application.yml文件

    server:
      port: 9090  #服务器端口
    
    spring:
      datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3307/campus?serverTimezone=GMT%2b8  #数据库地址
        username: root   #数据库用户名
        password: 123456  #数据库用户密码
    mybatis:
      mapper-locations: classpath:mapper/*.xml
    mybatis-plus:
      configuration:
        log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    files:
      upload:
        path: C:/Users/12087/Desktop/项目/campus/files/  #文件上传地址
