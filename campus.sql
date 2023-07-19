/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3307
 Source Server Type    : MySQL
 Source Server Version : 80012 (8.0.12)
 Source Host           : localhost:3307
 Source Schema         : campus

 Target Server Type    : MySQL
 Target Server Version : 80012 (8.0.12)
 File Encoding         : 65001

 Date: 10/06/2023 18:24:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `classname` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '班级',
  `adminid` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '工号',
  `avatar` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES (1, 'admin', '123456', '管理员', '20200822', '01', 'http://localhost:9090/file/0374b51c1e0641e7a4b779910a450099.jpg', '2023-06-06 13:23:34');
INSERT INTO `sys_admin` VALUES (2, 'admina', '123', '管理员2', '20200823', '02', 'http://localhost:9090/file/05a25c79e0194faf9337597d974b5e0c.jpg', '2023-06-06 16:15:37');

-- ----------------------------
-- Table structure for sys_comment
-- ----------------------------
DROP TABLE IF EXISTS `sys_comment`;
CREATE TABLE `sys_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `commentdate` date NOT NULL,
  `commenttime` time NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_comment
-- ----------------------------
INSERT INTO `sys_comment` VALUES (1, 'admin', '第一次留言', '2023-06-09', '16:41:24');
INSERT INTO `sys_comment` VALUES (2, 'admin', '第二次留言', '2023-06-10', '15:06:30');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '类型'
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('用户', 'el-icon-user', 'icon');
INSERT INTO `sys_dict` VALUES ('角色', 'el-icon-s-custom', 'icon');
INSERT INTO `sys_dict` VALUES ('菜单', 'el-icon-menu', 'icon');
INSERT INTO `sys_dict` VALUES ('勋章', 'el-icon-trophy', 'icon');
INSERT INTO `sys_dict` VALUES ('景点', 'el-icon-location-outline', 'icon');
INSERT INTO `sys_dict` VALUES ('文件', 'el-icon-folder', 'icon');
INSERT INTO `sys_dict` VALUES ('主页', 'el-icon-house', 'icon');
INSERT INTO `sys_dict` VALUES ('留言', 'el-icon-chat-dot-square', 'icon');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '文件名称',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '文件类型',
  `size` bigint(20) NULL DEFAULT NULL COMMENT '文件大小（KB）',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '下载链接',
  `md5` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '文件md5',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  `enable` tinyint(1) NULL DEFAULT 1 COMMENT '是否禁用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_md5`(`md5`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (7, '1215442247.jpg', 'jpg', 140, 'http://localhost:9090/file/f70310e92b7c450b85eda1412f58abb2.jpg', '33b49f5605da6421272257ff4253810e', 0, 1);
INSERT INTO `sys_file` VALUES (8, '1722453f339ca42b7e53a61c3471f0d.jpg', 'jpg', 188, 'http://localhost:9090/file/88af7aa814474e18a8e421767dd9ab4a.jpg', '456ab08a35043d73e706968fbff8f5b8', 1, 1);
INSERT INTO `sys_file` VALUES (9, '屏幕截图(1).png', 'png', 297, 'http://localhost:9090/file/4c2fe877eb2f49969d345d020ef9baf3.png', '949c7922e826c19bb7058729bcacba0c', 1, 1);
INSERT INTO `sys_file` VALUES (10, 'WIN_20221221_19_02_07_Pro.jpg', 'jpg', 45, 'http://localhost:9090/file/1d02b816a4c4434a8d00fd82052d760f.jpg', 'a5db79967491a7fda54d8a785974f47c', 1, 1);
INSERT INTO `sys_file` VALUES (11, '书签式页面.pptx', 'pptx', 221, 'http://localhost:9090/file/3067bf4b2d434618896b4517a75707e8.pptx', '18c91289e5c9b627d78c6b29c673f0f0', 0, 1);
INSERT INTO `sys_file` VALUES (12, '下载.jpg', 'jpg', 8, 'http://localhost:9090/file/939fd322e4234d8192f0c120d941e99a.jpg', '2a43bbab4e2e0e1a0148d5348139a3c8', 0, 1);
INSERT INTO `sys_file` VALUES (13, 'OIP-C.jpg', 'jpg', 10, 'http://localhost:9090/file/9cc431e22b38456e93a1006f53307483.jpg', '7453282a97c6b49f0416de49ca5ca3fe', 0, 1);
INSERT INTO `sys_file` VALUES (14, 'OIP-C (1).jpg', 'jpg', 9, 'http://localhost:9090/file/11112d368dda42b4978d681263730976.jpg', '15a363b47369e9b429c6577e60162362', 0, 1);
INSERT INTO `sys_file` VALUES (15, 'OIP-C (2).jpg', 'jpg', 9, 'http://localhost:9090/file/7e7a2862bf954c7598f4f273264dc4ef.jpg', '9745f7d5807c4ec82db18abb6fb9e1eb', 0, 1);
INSERT INTO `sys_file` VALUES (16, 'OIP-C (3).jpg', 'jpg', 8, 'http://localhost:9090/file/0374b51c1e0641e7a4b779910a450099.jpg', '160dd1153c6edf10f239f3067d0e6ef1', 0, 1);
INSERT INTO `sys_file` VALUES (17, 'OIP-C (4).jpg', 'jpg', 5, 'http://localhost:9090/file/05a25c79e0194faf9337597d974b5e0c.jpg', '7bb9c831cfb5274c7558d57df075de7b', 0, 1);
INSERT INTO `sys_file` VALUES (18, 'u=165764012,751844309&fm=253&fmt=auto&app=138&f=JPEG.webp', 'webp', 9, 'http://localhost:9090/file/139d9112eeaf442e86f95dfeeedecbd9.webp', 'f74f94827d887ed354709e9427c69985', 0, 1);
INSERT INTO `sys_file` VALUES (26, '3.jpg', 'jpg', 97, 'http://localhost:9090/file/01f3dbcefe734399a8d92d2dd184545b.jpg', 'e905da1150b4d35e27eb39102d121832', 0, 1);

-- ----------------------------
-- Table structure for sys_getmodel
-- ----------------------------
DROP TABLE IF EXISTS `sys_getmodel`;
CREATE TABLE `sys_getmodel`  (
  `id` int(11) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `modelid` int(11) NOT NULL,
  `gettime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`username`, `modelid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_getmodel
-- ----------------------------
INSERT INTO `sys_getmodel` VALUES (20, 'test', 1, '2023-06-10 17:15:11');
INSERT INTO `sys_getmodel` VALUES (19, 'test', 11, '2023-06-10 17:15:06');
INSERT INTO `sys_getmodel` VALUES (17, 'admin', 3, '2023-06-10 17:12:56');
INSERT INTO `sys_getmodel` VALUES (5, 'admin', 6, '2023-06-06 20:44:37');
INSERT INTO `sys_getmodel` VALUES (6, 'admin', 7, '2023-06-06 20:44:37');
INSERT INTO `sys_getmodel` VALUES (7, 'admin', 8, '2023-06-06 20:44:37');
INSERT INTO `sys_getmodel` VALUES (8, 'admin', 12, '2023-06-06 20:44:37');
INSERT INTO `sys_getmodel` VALUES (9, 'admin', 17, '2023-06-06 20:44:37');
INSERT INTO `sys_getmodel` VALUES (1371541505, 'wzq', 2, '2023-06-10 18:09:40');
INSERT INTO `sys_getmodel` VALUES (452988929, 'wzq', 1, '2023-06-10 18:07:55');
INSERT INTO `sys_getmodel` VALUES (276828162, 'wzq', 14, '2023-06-10 18:07:49');
INSERT INTO `sys_getmodel` VALUES (14, 'admin', 9, '2023-06-10 17:04:29');

-- ----------------------------
-- Table structure for sys_medal
-- ----------------------------
DROP TABLE IF EXISTS `sys_medal`;
CREATE TABLE `sys_medal`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '名字',
  `condit` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '获取条件',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '勋章类型',
  `enable` tinyint(1) NULL DEFAULT 1 COMMENT '是否禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_medal
-- ----------------------------
INSERT INTO `sys_medal` VALUES (1, '新生入校', '打卡一个地点', '打卡次数', 1);
INSERT INTO `sys_medal` VALUES (2, '略知一二', '打卡两个景点', '打卡次数', 1);
INSERT INTO `sys_medal` VALUES (3, '三顾茅庐', '打卡三个景点', '打卡次数', 1);
INSERT INTO `sys_medal` VALUES (4, '三番五次', '打卡五个景点', '打卡次数', 1);
INSERT INTO `sys_medal` VALUES (5, '十全十美', '打卡十个景点', '打卡次数', 1);
INSERT INTO `sys_medal` VALUES (6, '鹄望德清', '打卡德清湖', '打卡地点', 1);
INSERT INTO `sys_medal` VALUES (7, '春溢安吉', '打卡安吉园', '打卡地点', 1);
INSERT INTO `sys_medal` VALUES (8, '韵舞长兴', '打卡长兴广场', '打卡地点', 1);
INSERT INTO `sys_medal` VALUES (9, '旭照吴兴', '打卡吴兴园', '打卡地点', 1);
INSERT INTO `sys_medal` VALUES (10, '波粲南浔', '打卡南浔河', '打卡地点', 1);
INSERT INTO `sys_medal` VALUES (11, '秀逸明达', '打卡明达路', '打卡地点', 1);
INSERT INTO `sys_medal` VALUES (12, '同沐德音', '打卡校训广场', '打卡地点', 1);
INSERT INTO `sys_medal` VALUES (13, '白衣为证', '打卡医学生誓言广场', '打卡地点', 1);
INSERT INTO `sys_medal` VALUES (14, '鱼韵桑影', '打卡桑基鱼塘', '打卡地点', 1);
INSERT INTO `sys_medal` VALUES (15, '两斋遗馨', '打卡胡媛广场', '打卡地点', 1);
INSERT INTO `sys_medal` VALUES (16, '深恩聚塔', '打卡恩泽碑', '打卡地点', 1);
INSERT INTO `sys_medal` VALUES (17, '湖师十景', '打卡十个指定地点', '打卡', 1);
INSERT INTO `sys_medal` VALUES (18, '湖师学子', '获得所有勋章', '打卡', 1);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '路径',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '主页', '/home', 'el-icon-house', '', NULL);
INSERT INTO `sys_menu` VALUES (3, '系统管理', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (5, '用户管理', '/user', 'el-icon-user', '', 3);
INSERT INTO `sys_menu` VALUES (6, '留言', '', '', '', NULL);
INSERT INTO `sys_menu` VALUES (7, '角色管理', '/role', 'el-icon-s-custom', NULL, 3);
INSERT INTO `sys_menu` VALUES (8, '菜单管理', '/menu', 'el-icon-menu', NULL, 3);
INSERT INTO `sys_menu` VALUES (9, '勋章管理', '/', 'el-icon-trophy', NULL, 3);
INSERT INTO `sys_menu` VALUES (10, '景点管理', '/', 'el-icon-location-outline', NULL, 3);
INSERT INTO `sys_menu` VALUES (11, '文件管理', '/file', 'el-icon-folder', NULL, 3);
INSERT INTO `sys_menu` VALUES (12, '留言管理', '/', 'el-icon-chat-dot-square', NULL, 6);

-- ----------------------------
-- Table structure for sys_point
-- ----------------------------
DROP TABLE IF EXISTS `sys_point`;
CREATE TABLE `sys_point`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '景点名称',
  `coordinatex` double NOT NULL DEFAULT 0 COMMENT 'x轴',
  `coordinatey` double NOT NULL DEFAULT 0 COMMENT 'y轴',
  `enable` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1660964868 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_point
-- ----------------------------
INSERT INTO `sys_point` VALUES (12, '中校河', 30.873748, 120.124215, 1);
INSERT INTO `sys_point` VALUES (2, '安吉园', 30.873195, 120.135163, 1);
INSERT INTO `sys_point` VALUES (1, '德清湖', 30.873581, 120.133601, 1);
INSERT INTO `sys_point` VALUES (11, '恩泽碑', 30.87363, 120.122094, 1);
INSERT INTO `sys_point` VALUES (4, '吴兴园', 30.873324, 120.129772, 1);
INSERT INTO `sys_point` VALUES (3, '长兴广场', 30.874564, 120.136051, 1);
INSERT INTO `sys_point` VALUES (5, '南浔河', 30.872924, 120.130655, 1);
INSERT INTO `sys_point` VALUES (6, '明达路', 30.873799, 120.131089, 1);
INSERT INTO `sys_point` VALUES (7, '校训广场', 30.872275, 120.128531, 1);
INSERT INTO `sys_point` VALUES (8, '医学生誓言广场', 30.872317, 120.13161, 1);
INSERT INTO `sys_point` VALUES (9, '桑基鱼塘', 30.875379, 120.133772, 1);
INSERT INTO `sys_point` VALUES (10, '胡媛广场', 30.873505, 120.125398, 1);

-- ----------------------------
-- Table structure for sys_pointsubmit
-- ----------------------------
DROP TABLE IF EXISTS `sys_pointsubmit`;
CREATE TABLE `sys_pointsubmit`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '用户名',
  `pointid` int(11) NOT NULL DEFAULT 0 COMMENT '景点id',
  `pointname` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '景点名称',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '景点图片',
  `area` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '景点校区',
  `typebig` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '景点大类',
  `typesmall` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '景点小类',
  `descri` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '景点时间',
  `submittime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_pointsubmit
-- ----------------------------
INSERT INTO `sys_pointsubmit` VALUES (1, 'admin', 12, '中校河', '', '中校区', '风景', '水景', '校河流经中校区，两岸花草遍布、绿树葱郁，河水清澈。', '2023-06-08 20:41:08');
INSERT INTO `sys_pointsubmit` VALUES (6, 'admin', 4, '吴兴园', '', '东校区', '风景', '公园', '这是吴兴园', '2023-06-10 17:04:29');
INSERT INTO `sys_pointsubmit` VALUES (5, 'admin', 6, '明达路', '', '东校区', '建筑', '道路', '这是明达路', '2023-06-10 16:12:11');
INSERT INTO `sys_pointsubmit` VALUES (7, 'test', 6, '明达路', '', '东校区', '风景', '道路', '这是明达路', '2023-06-10 17:15:06');
INSERT INTO `sys_pointsubmit` VALUES (8, 'test', 9, '桑基鱼塘', '', '东校区', '建筑', '公园', '这是桑基鱼塘', '2023-06-10 17:16:43');
INSERT INTO `sys_pointsubmit` VALUES (9, 'test', 3, '长兴广场', '', '东校区', '风景', '教学', '这是长兴广场', '2023-06-10 17:17:56');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (2, '管理员', '管理员');
INSERT INTO `sys_role` VALUES (3, 'admin', '管理员');
INSERT INTO `sys_role` VALUES (6, '管理员1', 'admin');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` int(11) NOT NULL,
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` int(11) NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `classname` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '班级',
  `studentid` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '学号',
  `avatar` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456', '管理员', '12345678', '11111111111', 'http://localhost:9090/file/0374b51c1e0641e7a4b779910a450099.jpg', '2023-04-11 21:25:28');
INSERT INTO `user` VALUES (21, 'admin2', '123', '管理员2', '1', '1', NULL, '2023-06-06 16:38:56');
INSERT INTO `user` VALUES (23, 'test1', '123', '测试1', NULL, NULL, NULL, '2023-06-10 17:30:49');
INSERT INTO `user` VALUES (22, 'test', '123', '测试员', NULL, NULL, NULL, '2023-06-10 17:14:32');
INSERT INTO `user` VALUES (24, 'testa', '123', '123', '12345678', '1235678900', NULL, '2023-06-10 17:33:59');

SET FOREIGN_KEY_CHECKS = 1;
