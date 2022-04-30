create database `clgc`;
use clgc;

# 角色表
CREATE TABLE `s_user_role` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '唯一标识符',
                               `role_id` bigint NOT NULL COMMENT '角色编号',
                               `rolename` varchar(50) NOT NULL COMMENT '角色名称',
                               `is_delete` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
                               `description` varchar(250) DEFAULT NULL COMMENT '角色描述',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `s_user_role` VALUES (2, 2, '超级管理员', 0, '拥有最高权限');
INSERT INTO `s_user_role` VALUES (3, 3, '管理员', 0, '管理员');

# 用户表
CREATE TABLE `s_user` (
                          `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户唯一标识符',
                          `user_ID` bigint NOT NULL COMMENT '用户编号',
                          `username` varchar(30) NOT NULL COMMENT '用户名称',
                          `password` varchar(255) DEFAULT NULL COMMENT '用户密码',
                          `role_ID` bigint NOT NULL COMMENT '用户角色ID',
                          `description` varchar(250) DEFAULT NULL COMMENT '用户描述',
                          `is_delete` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `s_user` VALUES (1, 1000, 'admin', '7ed917eb25c9fc56499a76bf5adbfa50', 2, '超级管理员', 0);
INSERT INTO `s_user` VALUES (2, 111, 'clgc', '7ed917eb25c9fc56499a76bf5adbfa50', 3, '啊啊啊啊', 0);
INSERT INTO `s_user` VALUES (3, 324, '234', '7ed917eb25c9fc56499a76bf5adbfa50', 3, '222', 0);

# 权限表
CREATE TABLE `s_role_menu` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '唯一标识符',
                               `role_id` bigint DEFAULT NULL COMMENT '角色编号',
                               `menu_id` bigint DEFAULT NULL COMMENT '菜单编号',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `s_role_menu` VALUES (1, 2, 1);
INSERT INTO `s_role_menu` VALUES (2, 2, 2);
INSERT INTO `s_role_menu` VALUES (3, 2, 3);
INSERT INTO `s_role_menu` VALUES (4, 2, 4);
INSERT INTO `s_role_menu` VALUES (5, 2, 5);
INSERT INTO `s_role_menu` VALUES (6, 2, 6);
INSERT INTO `s_role_menu` VALUES (7, 2, 7);
INSERT INTO `s_role_menu` VALUES (8, 2, 8);
INSERT INTO `s_role_menu` VALUES (9, 2, 9);
INSERT INTO `s_role_menu` VALUES (10, 2, 10);
INSERT INTO `s_role_menu` VALUES (11, 2, 11);
INSERT INTO `s_role_menu` VALUES (15, 3, 9);

# 菜单表
CREATE TABLE `s_menu` (
                          `id` bigint NOT NULL AUTO_INCREMENT COMMENT '唯一标识符',
                          `menu_name` varchar(250) DEFAULT NULL COMMENT '菜单名称',
                          `menu_path` varchar(250) DEFAULT NULL COMMENT '菜单路径',
                          `menu_ico` varchar(200) DEFAULT NULL COMMENT '菜单图表',
                          `pid` bigint DEFAULT NULL COMMENT '父级菜单id',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `s_menu` VALUES (1,'数据信息', NULL, 'IconMenu', NULL);
INSERT INTO `s_menu` VALUES (2, '权限设置', NULL, 'Lock', NULL);
INSERT INTO `s_menu` VALUES (3, '系统设置', NULL, 'Setting', NULL);
INSERT INTO `s_menu` VALUES (4, '报名详细数据', '/itemShow', 'List', 1);
INSERT INTO `s_menu` VALUES (5, '报名表', '/itemType', 'Tickets', 1);
INSERT INTO `s_menu` VALUES (6, '角色管理', '/role', 'User', 2);
INSERT INTO `s_menu` VALUES (7, '人员管理', '/mans', 'Avatar', 2);
INSERT INTO `s_menu` VALUES (8,  '修改密码', '/pwd', 'Key', 3);
INSERT INTO `s_menu` VALUES (9, '个人密码', '/perPwd', 'User', 3);
INSERT INTO `s_menu` VALUES (10,  '黑名单', '/recordShow', 'Document', 1);
INSERT INTO `s_menu` VALUES (11, '数据展示', '/lookData', 'Calendar', 1);

# 报名表
CREATE TABLE `s_apply` (
                           `id` bigint NOT NULL AUTO_INCREMENT COMMENT '唯一标识符',
                           `type_id` bigint NOT NULL COMMENT '报名编号',
                           `typename` varchar(50) NOT NULL COMMENT '报名标题名称',
                           `is_delete` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
                           `description` varchar(250) DEFAULT NULL COMMENT '描述',
                           `is_show` tinyint(1) DEFAULT '0' COMMENT '是否展示',
                           `begin_time` datetime DEFAULT NULL COMMENT '开始时间',
                           `end_time` datetime DEFAULT NULL COMMENT '结束时间',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `s_apply` VALUES (1, 1654, 'c测试3', 0, '提供测试所用', 0, '2022-04-14 12:00:00', '2022-05-01 13:30:10');
INSERT INTO `s_apply` VALUES (2, 2022001, '测试一', 0, '提供测试所用', 0, '2022-04-14 12:00:00', '2022-05-01 13:30:10');
INSERT INTO `s_apply` VALUES (3, 342334, '2022年报名表1', 0, '提供测试所用', 0, '2022-04-14 12:00:00', '2022-05-01 13:30:10');
INSERT INTO `s_apply` VALUES (4, 2022043001, '2022年竞赛报名表', 0, '2022年竞赛报名表', 1, '2022-04-30 00:00:51', '2022-05-01 00:00:00');

# 信息表
CREATE TABLE `s_information` (
                                 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '物品id',
                                 `user_id` varchar(100) NOT NULL COMMENT '用户编号',
                                 `name` varchar(200) NOT NULL COMMENT '用户名称',
                                 `typeID` bigint NOT NULL COMMENT '报名表编号',
                                 `is_show` tinyint(1) DEFAULT '1' COMMENT '启用状态',
                                 `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
                                 `user_add_time` datetime DEFAULT NULL COMMENT '报名时间',
                                 `school` varchar(155) DEFAULT NULL COMMENT '院校',
                                 `professional` varchar(200) DEFAULT NULL COMMENT '专业',
                                 `grade` varchar(10) DEFAULT '0' COMMENT '年级',
                                 `phone` varchar(15) DEFAULT NULL COMMENT '手机号',
                                 `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
                                 `sex` varchar(5) DEFAULT NULL COMMENT '性别',
                                 `professional2` varchar(50) DEFAULT NULL COMMENT '中间值，临时存储专业',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `s_information` VALUES (1, '111111', '测试物品名称', 2022001, 1, 0, '2022-04-28 18:22:15',  '世界大学', '软件工程', '2019', '12345678911', '165165@qq.com', '女', NULL);
INSERT INTO `s_information` VALUES (2, '33333333', '2333424', 2022001, 1, 0, '2022-04-28 18:18:14', '世界大学', '其他', '2019', '159191955', '1656@qq.com', '男', NULL);
INSERT INTO `s_information` VALUES (3, '423432', 'rrt', 342334, 1, 0, '2022-04-28 19:59:21',  '世界大学', '软件工程', '2019', '23333333333', 'fweiofwei@qq.com', 'man', NULL);
INSERT INTO `s_information` VALUES (4, '324234', '雷人', 342334, 1, 0, '2022-04-28 20:09:55',  '宇宙大学', '其他', '2019', '22222222222', 'fweiofwei@qq.com', '男', NULL);
INSERT INTO `s_information` VALUES (5, '324234', '雷人', 342334, 1, 0, '2022-04-28 20:10:05', '宇宙大学', '其他', '2019', '22222222222', 'fweiofwei@qq.com', '男', NULL);
INSERT INTO `s_information` VALUES (6, '355435', '束带结发', 342334, 1, 0, '2022-04-28 20:13:01',  '世界大学', '软件工程', '2019', '33335454444', 'fweiofwei@qq.com', '女', NULL);
INSERT INTO `s_information` VALUES (7, '342342453', '流水段', 342334, 1, 0, '2022-04-28 20:13:38',  '宇宙大学', '其他', '2019', '22222214552', 'fweiofwei@qq.com', '男', '其他天天');
INSERT INTO `s_information` VALUES (8, '202204282', '2323', 342334, 1, 0, '2022-04-28 20:47:04',  '世界大学', '软件工程', '其他', '22222222222', 'fweiofwei@qq.com', '男', NULL);
INSERT INTO `s_information` VALUES (9, '20121314', '类诶', 342334, 1, 0, '2022-04-28 22:35:44',  '世界大学', '软件工程', '2019', '23444444444', '1995156@qq.com', '男', NULL);
INSERT INTO `s_information` VALUES (10, '2058946', '加额外分为', 342334, 1, 0, '2022-04-28 22:38:18',  '世界大学', '软件工程', '2019', '33333333333', '1995156@qq.com', '男', NULL);
INSERT INTO `s_information` VALUES (11, '3434234', 'frgreeger', 342334, 1, 0, '2022-04-29 13:38:07',  '世界大学', '其他', '2019', '33444444444', 'fweiofwei@qq.com', '男', '教育学');
INSERT INTO `s_information` VALUES (12, '876543', '了，看没机会那个吧', 342334, 1, 0, '2022-04-29 13:39:51',  '世界大学', '其他', '2019', '33333333333', 'fweiofwei@qq.com', '女', '教育学');
INSERT INTO `s_information` VALUES (13, '98i76', 'erferfrg', 342334, 1, 0, '2022-04-29 13:41:31',  '其他', '其他', '2019', '22222222222', 'fweiofwei@qq.com', '男', '的金佛');
INSERT INTO `s_information` VALUES (14, '564654', 'rt4345', 342334, 1, 0, '2022-04-29 13:50:15',  '世界大学', '软件工程', '2018', '11111111111', 'fweiofwei@qq.com', '男', '家附近人');
INSERT INTO `s_information` VALUES (15, '15918', '的副驾驶', 342334, 1, 0, '2022-04-29 18:56:41',  '世界大学', '软件工程', '2018', '22222222222', 'fweiofwei2@qq.com', '男', NULL);
INSERT INTO `s_information` VALUES (16, '15918', '的副驾驶', 342334, 1, 0, '2022-04-29 18:57:41',  '世界大学', '软件工程', '2018', '22222222222', 'fweiofwei@qq.com', '男', NULL);
INSERT INTO `s_information` VALUES (17, '234108', '67554', 342334, 1, 0, '2022-04-29 18:59:27',  '世界大学', '软件工程', '2019', '23232234433', '324234@qq.com', '男', NULL);
INSERT INTO `s_information` VALUES (18, '324547678', '你天涯热土', 342334, 1, 0, '2022-04-29 19:09:27',  '世界大学', '软件工程', '2020', '33333333333', 'fweiofwei@qq.com', '男', NULL);
INSERT INTO `s_information` VALUES (19, '7845845', 'gorijger', 342334, 1, 0, '2022-04-29 19:13:18',  '世界大学', '计算机科学与技术', '2020', '33333333333', 'fweiofwei@qq.com', '男', NULL);
INSERT INTO `s_information` VALUES (20, '1948949', 'gtbrthbtt', 342334, 1, 0, '2022-04-29 19:15:38',  '宇宙大学', '计算机科学与技术', '2020', '44444444444', 'fweiofwei@qq.com', '男', NULL);
INSERT INTO `s_information` VALUES (21, '53453434676', '566好', 342334, 1, 0, '2022-04-29 19:18:21',  '其他', '其他', '其他', '44444444444', 'fweiofwei@qq.com', '男', '几个人推荐人');
INSERT INTO `s_information` VALUES (22, '56', '43', 342334, 1, 0, '2022-04-29 19:19:36',  '其他', '其他', '其他', '77789089790', 'fweiofwei@qq.com', '女', '34天');
INSERT INTO `s_information` VALUES (23, '98489498', 't65g1r65ggt', 342334, 1, 0, '2022-04-29 23:15:20',  '宇宙大学', '软件工程', '其他', '33443434343', 'fweiofwei@qq.com', '男', NULL);
INSERT INTO `s_information` VALUES (24, '22165', '刘雷', 342334, 1, 0, '2022-04-29 23:55:08',  '世界大学', '物联网', '2020', '33333333333', 'fweiofwei@qq.com', '男', '物联网');
INSERT INTO `s_information` VALUES (25, '2016594', '累哦', 2022043001, 1, 0, '2022-04-30 00:02:03',  '世界大学', '软件工程', '2018', '33333333333', 'fweiofwei@qq.com', '男', NULL);
INSERT INTO `s_information` VALUES (26, '154786', '污染物', 2022043001, 1, 0, '2022-04-30 00:11:36',  '宇宙大学', '核工', '2018', '11111111111', 'fweiofwei@qq.com', '女', '核工');
INSERT INTO `s_information` VALUES (27, '498155', 'frg', 2022043001, 1, 0, '2022-04-30 09:10:30',  '世界大学', '软件工程', '2019', '22222222222', 'fweiofwei@qq.com', '女', NULL);
INSERT INTO `s_information` VALUES (28, '159753', '343个', 2022043001, 1, 0, '2022-04-30 09:37:31',  '世界大学', '软件工程', '2018', '22222222222', 'fweiofwei@qq.com', '男', NULL);
INSERT INTO `s_information` VALUES (29, '123456', '碳碳双键', 2022043001, 1, 0, '2022-04-30 10:30:16',  '世界大学', '软件工程', '2019', '11048528687', '1196720398@qq.com', '男', NULL);

# 黑名单
CREATE TABLE `s_black_list` (
                                `blacklist_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                                `blacklist_user_id` varchar(100) DEFAULT NULL COMMENT '黑名单用户编号',
                                `is_delete` tinyint DEFAULT '0' COMMENT '逻辑删除',
                                `user_name` varchar(50) DEFAULT NULL COMMENT '用户姓名',
                                `user_grade` varchar(50) DEFAULT NULL COMMENT '年级',
                                `usr_prof` varchar(50) DEFAULT NULL COMMENT '专业',
                                `description` varchar(150) DEFAULT NULL COMMENT '备注',
                                `add_time` datetime DEFAULT NULL COMMENT '添加时间',
                                PRIMARY KEY (`blacklist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `s_black_list` VALUES (1, '20220428', 0, '张三', '2000级', '是借款人', '反问我', '2022-04-29 14:15:45');
INSERT INTO `s_black_list` VALUES (2, '2016488', 0, '丽水', '2016', '洁儿', '热污染无', '2022-04-29 14:19:03');

