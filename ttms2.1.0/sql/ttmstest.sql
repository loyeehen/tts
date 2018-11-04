DROP database IF EXISTS ttms;
CREATE database ttms DEFAULT CHARACTER SET 'utf8';
use ttms;

DROP TABLE IF EXISTS `tms_projects`;
DROP TABLE IF EXISTS `tms_teams`;
DROP TABLE IF EXISTS `tms_classes`;
DROP TABLE IF EXISTS `tms_products`;
DROP TABLE IF EXISTS `tms_attachements`;
DROP TABLE IF EXISTS `tms_companies`;

DROP TABLE IF EXISTS `sys_organizations`;
DROP TABLE IF EXISTS `sys_resources`;
DROP TABLE IF EXISTS `sys_users`;
DROP TABLE IF EXISTS `sys_roles`;
DROP TABLE IF EXISTS `sys_user_roles`;
DROP TABLE IF EXISTS `sys_role_menus`;

--项目表
CREATE TABLE `tms_projects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) DEFAULT NULL COMMENT '项目编码',
  `name` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `beginDate` datetime DEFAULT NULL COMMENT '开始日期',
  `endDate` datetime   DEFAULT NULL COMMENT '结束日期',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser`  varchar(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--团表
CREATE TABLE `tms_teams` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '团名称',
  `projectId` int(11) DEFAULT NULL COMMENT '项目id',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser`  varchar(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `tms_classes`;
CREATE TABLE `tms_classes` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类主键',
  `name` varchar(200) DEFAULT NULL COMMENT '分类名称',
  `sort` int(11) DEFAULT NULL COMMENT '分类序号',
  `parentId` int(11) DEFAULT NULL COMMENT '父类id ',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `createdUser` varchar(255) DEFAULT NULL COMMENT '创建人用户名',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedUser` varchar(255) DEFAULT NULL COMMENT '修改人用户名',
  `modifiedTime` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

--系统产品表
CREATE TABLE `tms_products` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `code` varchar(50) NOT NULL COMMENT '产品编号',
  `name` varchar(200) DEFAULT '' COMMENT '产品名称',
  `teamId` int(11) DEFAULT NULL COMMENT '团号Id',
  `exText` varchar(500) DEFAULT NULL COMMENT '特殊提示',
  `onlineDate` date DEFAULT NULL COMMENT '上架时间',
  `offlineDate` date DEFAULT NULL COMMENT '下架时间',
  `quantity` int(11) DEFAULT '0' COMMENT '预售数量',
  `minQty` int(11) DEFAULT '0' COMMENT '最低数量',
  `soldQty` int(11) DEFAULT '0' COMMENT '已售数量',
  `price` decimal(10,0) DEFAULT '0' COMMENT '产品价格',
  `classId` int(11) DEFAULT '0' COMMENT '产品分类编号',
  `nights` int(11) DEFAULT '0' COMMENT '晚数',
  `state` int(11) DEFAULT '0' COMMENT '产品状态  0：待售  1：上架   2：下架',
  `note` varchar(2000) DEFAULT NULL COMMENT '备注',
  `createdUser` varchar(255) DEFAULT NULL COMMENT '创建人用户名',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedUser` varchar(255) DEFAULT NULL COMMENT '最后修改人用户名',
  `modifiedTime` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--产品附件表
CREATE TABLE `tms_attachements` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '附件主键',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `fileName` int(11) DEFAULT NULL COMMENT '文件名称 ',
  `contentType` varchar(50) DEFAULT NULL COMMENT '文件类型',
  `filePath` varchar(200) DEFAULT NULL COMMENT '备注',
  `fileDisgest` varchar(200) DEFAULT NULL COMMENT '文件摘要',
  `createdUser` varchar(255) DEFAULT NULL COMMENT '创建人用户名',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedUser` varchar(255) DEFAULT NULL COMMENT '修改人用户名',
  `modifiedTime` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

-- 分销企业表
CREATE TABLE `sys_companies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `category` varchar(50) DEFAULT NULL COMMENT '类型',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `address` varchar(100) DEFAULT NULL COMMENT '电话',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `valid` tinyint(1) DEFAULT '1' COMMENT '有效标志',
  `createdTime` datetime DEFAULT NULL COMMENT '新增时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '更新时间',
  `createdUser`  varchar(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '更新用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- 组织结构表
CREATE TABLE `sys_organizations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '机构名称',
  `code` varchar(50) DEFAULT NULL COMMENT '机构编码',
  `parentId` int(11) DEFAULT NULL COMMENT '父机构id',
  `parentIds` int(11) DEFAULT NULL COMMENT '父机构ids 0/1/2/3',
  `valid` tinyint(1)  DEFAULT '1' COMMENT '是否有效',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser`  varchar(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- 资源菜单
CREATE TABLE `sys_resources` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COMMENT '资源名称',
  `url` varchar(200) COMMENT '资源URL',
  `type` int COMMENT '类型     1：菜单   2：按钮',
  `sort` int COMMENT '排序',
  `note` varchar(100) COMMENT '备注',
  `parentId` int COMMENT '父菜单ID，一级菜单为0',
  `permission` varchar(500) COMMENT '授权(如：user:create)',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser`  varchar(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源管理';

-- 系统用户
CREATE TABLE `sys_users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) COMMENT '密码',
  `email` varchar(100) COMMENT '邮箱',
  `mobile` varchar(100) COMMENT '手机号',
  `valid` tinyint COMMENT '状态  0：禁用   1：正常',
  `orgId` int(11) COMMENT '手机号',
  `note` varchar(100) COMMENT '备注',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser`  varchar(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- 角色
CREATE TABLE `sys_roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COMMENT '角色名称',
  `note` varchar(100) COMMENT '备注',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser`  varchar(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- 用户与角色对应关系
CREATE TABLE `sys_user_roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int COMMENT '用户ID',
  `role_id` int COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- 角色与菜单对应关系
CREATE TABLE `sys_role_resources` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int COMMENT '角色ID',
  `resource_id` int COMMENT 'ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';