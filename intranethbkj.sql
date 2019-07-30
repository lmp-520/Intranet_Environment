/*
 Navicat Premium Data Transfer

 Source Server         : Xdmd-Test
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : intranethbkj

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 30/07/2019 17:58:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for acceptance_phase
-- ----------------------------
DROP TABLE IF EXISTS `acceptance_phase`;
CREATE TABLE `acceptance_phase`  (
  `ap_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `ap_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核状态名称',
  PRIMARY KEY (`ap_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of acceptance_phase
-- ----------------------------
INSERT INTO `acceptance_phase` VALUES (1, '等待员工提交');
INSERT INTO `acceptance_phase` VALUES (2, '等待企业管理员提交');
INSERT INTO `acceptance_phase` VALUES (3, '等待验收初审');
INSERT INTO `acceptance_phase` VALUES (4, '通过初审，等待提交专家表');
INSERT INTO `acceptance_phase` VALUES (5, '等待环保厅审核公司上传的专家文件');
INSERT INTO `acceptance_phase` VALUES (6, '公司上传最终验收报告');
INSERT INTO `acceptance_phase` VALUES (7, '审核最终验收报告');
INSERT INTO `acceptance_phase` VALUES (77, '验收通过');
INSERT INTO `acceptance_phase` VALUES (88, '验收结题');
INSERT INTO `acceptance_phase` VALUES (99, '验收不通过');

-- ----------------------------
-- Table structure for check_apply
-- ----------------------------
DROP TABLE IF EXISTS `check_apply`;
CREATE TABLE `check_apply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `topic_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题名称',
  `topic_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题编号',
  `subject_undertaking_unit_id` int(10) NOT NULL COMMENT '课题承担单位id',
  `subject_undertaking_unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '承担单位名称',
  `unit_nature` int(10) NOT NULL COMMENT '单位性质',
  `project_leader` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题负责人',
  `project_leader_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题负责人联系电话',
  `project_leader_mail` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题负责人联系邮箱',
  `postal_address` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通讯地址',
  `agreement_start_time` date NOT NULL COMMENT '合同开始时间',
  `agreement_end_time` date NOT NULL COMMENT '合同结束时间',
  `application_acceptance_time` date NOT NULL COMMENT '申请验收时间',
  `application_acceptance_mode` int(5) NOT NULL COMMENT '申请验收方式',
  `application_acceptance_place` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请验收地点',
  `acceptance_contact` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '验收联系人',
  `acceptance_contact_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '验收联系人联系电话',
  `main_content_situation` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主要研究内容完成情况',
  `submission_achievements_situation` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '提交成果情况',
  `subject_undertaking_unit_opinion` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题承担单位意见',
  `environmental_departments_opinion` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所在环保部门意见',
  `province_assessment_center_opinion` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省生态环境评估中心初审意见',
  `competent_department_oinion` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省环保厅主管部门意见',
  `submit_inventory` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '提交资料清单',
  `acceptance_phase_id` int(50) NOT NULL COMMENT '验收审核状态（1：等待员工提交 2：等待企业管理员提交 3：等待验收初审  4：通过初审，等待提交专家表 5：等待环保厅审核公司上传的专家文件  6.公司上传最终验收报告（验收证书） 7.审核最终验收报告 77：验收通过 88，验收结题 99.验收不通过）',
  `create_time` datetime(0) NOT NULL COMMENT '该表创建时间',
  `create_author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建该表的人',
  `achievement_url_id` int(11) NULL DEFAULT NULL COMMENT '成果附件上传文件的id',
  `submit_url_id` int(11) NULL DEFAULT NULL COMMENT '提交清单上传文件的id',
  `audit_report_url_id` int(11) NULL DEFAULT NULL COMMENT '审计报告上传文件的id',
  `first_inspection_report_url_id` int(11) NULL DEFAULT NULL COMMENT '初审报告上传文件的id',
  `expert_group_comments_url_id` int(11) NULL DEFAULT NULL COMMENT '专家组意见上传文件的id',
  `expert_acceptance_form_id` int(11) NULL DEFAULT NULL COMMENT '专家验收评议表上传文件的id',
  `application_url_id` int(11) NULL DEFAULT NULL COMMENT '验收申请表上传文件的id',
  `acceptance_conclusion_id` int(11) NULL DEFAULT NULL COMMENT '验收结论的id (在字典表中)',
  `acceptance_certificate_id` int(11) NULL DEFAULT NULL COMMENT '最终验收证书文件的id',
  `acceptance_final_result_id` int(11) NULL DEFAULT NULL COMMENT '最终验收结果id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of check_apply
-- ----------------------------
INSERT INTO `check_apply` VALUES (50, '课题名称1', '123', 4, '公司名称1', 3, '项目负责人名1', '15588865919', '158965512@qq.com', '公司地址1', '2019-07-01', '2019-07-03', '2019-07-04', 6, '申请验收地点', '验收联系人1', '13566678482', '主要研究内容完成情况1', '提交成果情况1', '课题承担单位意见1', '所在环保部门意见1', '省生态环境评估中心初审意见1', '省环保厅主管部门意见1', '提交资料清单1', 3, '2019-07-18 10:15:20', '创建人1', 77, 78, NULL, NULL, 4, NULL, 76, NULL, NULL, NULL);
INSERT INTO `check_apply` VALUES (51, '课题名称2', '123', 6, '公司名称2', 2, '项目负责人名2', '15588865911', '158965512@163.com', '公司地址2', '2019-07-01', '2019-07-03', '2019-07-04', 6, '申请验收地点', '验收联系人2', '13566678482', '主要研究内容完成情况2', '提交成果情况2', '课题承担单位意见2', '所在环保部门意见2', '省生态环境评估中心初审意见2', '省环保厅主管部门意见2', '提交资料清单2', 3, '2019-07-18 10:15:20', '创建人2', 77, 78, NULL, NULL, 4, NULL, 76, NULL, NULL, NULL);
INSERT INTO `check_apply` VALUES (52, '课题名称3', '1234', 2, '公司名称3', 2, '项目负责人名2', '15588865741', '158965512@wixing.com', '公司地址2', '2019-07-01', '2019-07-03', '2019-07-04', 4, '申请验收地点3', '验收联系人3', '13566678485', '主要研究内容完成情况3', '提交成果情况3', '课题承担单位意见3', '所在环保部门意见3', '省生态环境评估中心初审意见3', '省环保厅主管部门意见3', '提交资料清单3', 1, '2019-07-18 10:15:20', '创建人1', 77, 78, NULL, NULL, 4, NULL, 76, NULL, NULL, NULL);
INSERT INTO `check_apply` VALUES (53, '课题名称5', '12345', 2, '公司名称4', 2, '项目负责人名5', '15588865741', '158965512@wixing.com', '公司地址5', '2019-07-01', '2019-07-03', '2019-07-04', 4, '申请验收地点5', '验收联系人5', '13566678485', '主要研究内容完成情况5', '提交成果情况5', '课题承担单位意见5', '所在环保部门意见5', '省生态环境评估中心初审意见5', '省环保厅主管部门意见5', '提交资料清单5', 4, '2019-07-18 10:15:20', '创建人1', 77, 78, NULL, NULL, 4, NULL, 76, NULL, NULL, NULL);
INSERT INTO `check_apply` VALUES (54, '课题名称7', '12345', 2, '公司名称2', 2, '项目负责人名7', '15588865741', '158965512@wixing.com', '公司地址5', '2019-07-01', '2019-07-03', '2019-07-04', 3, '申请验收地点7', '验收联系人7', '13566678485', '主要研究内容完成情况7', '提交成果情况7', '课题承担单位意见7', '所在环保部门意见7', '省生态环境评估中心初审意见7', '省环保厅主管部门意见7', '提交资料清单7', 4, '2019-07-18 10:15:20', '创建人1', 77, 78, NULL, NULL, 4, NULL, 76, NULL, NULL, NULL);
INSERT INTO `check_apply` VALUES (55, '课题名称666', '12345', 2, '公司名称1', 2, '项目负责人名666', '15588865741', '158965512@wixing.com', '公司地址5', '2019-07-01', '2019-07-03', '2019-07-04', 3, '申请验收地点666', '验收联系人666', '13566678485', '主要研究内容完成情况666', '提交成果情况666', '课题承担单位意见6667', '所在环保部门意见6667', '省生态环境评估中心初审意见6667', '省环保厅主管部门意见7', '提交资料清单7', 7, '2019-07-18 10:15:20', '创建人1', 77, 78, NULL, NULL, 77, 78, 76, NULL, 77, NULL);
INSERT INTO `check_apply` VALUES (56, '课题名称9999', '123453', 14, 'xdmd', 2, '项目负责人999', '9999999999', '9999999@qq.com', '公司地址999', '2019-07-01', '2019-07-11', '2019-07-15', 2, '申请验收地点9999', '验收联系人99999', '9999999999', '主要研究内容完成情况9999', '提交成果情况9999', '课题承担单位意见999999', '所在环保部门意见9999', '省生态环境评估中心初审意见99999', '省环保厅主管部门意见999999', '提交资料清单9999', 5, '2019-07-23 14:12:12', '创建人999', 77, 78, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for check_apply_state
-- ----------------------------
DROP TABLE IF EXISTS `check_apply_state`;
CREATE TABLE `check_apply_state`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `check_apply_id` int(11) NULL DEFAULT NULL COMMENT '对应验收申请表的id',
  `fist_handler` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交办人',
  `second_handler` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理人',
  `audit_step` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核步骤',
  `first_handle_time` datetime(0) NULL DEFAULT NULL COMMENT '交办时间',
  `state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `handle_content` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理内容',
  `second_handle_time` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of check_apply_state
-- ----------------------------
INSERT INTO `check_apply_state` VALUES (1, 50, '公司员工', '公司老板', '公司审批', '2019-06-20 14:21:21', '已处理', '审核通过', '2019-06-22 14:05:00');
INSERT INTO `check_apply_state` VALUES (3, 50, '公司老板', 'admin', '验收初审', '2019-06-22 14:05:00', '已处理', '审核通过', '2019-07-25 10:05:57');
INSERT INTO `check_apply_state` VALUES (4, 50, 'admin', 'admin', '等待公司提交文件', '2019-07-25 10:05:57', '已退回', '1111', '2019-07-25 10:24:07');
INSERT INTO `check_apply_state` VALUES (5, 50, 'admin', 'admin', '公司员工重新提交', '2019-07-25 10:24:07', '已退回', '2222', '2019-07-25 11:11:13');
INSERT INTO `check_apply_state` VALUES (6, 50, 'admin', NULL, '公司员工重新提交', '2019-07-25 11:11:13', '等待处理', NULL, NULL);
INSERT INTO `check_apply_state` VALUES (7, 51, '公司员工', '公司老板', '公司审批', '2019-05-06 13:15:10', '已处理', '审核通过', '2019-05-12 13:15:27');
INSERT INTO `check_apply_state` VALUES (8, 51, '公司老板', '', '验收初审', '2019-05-12 13:15:27', '等待处理', NULL, NULL);
INSERT INTO `check_apply_state` VALUES (9, 52, '公司员工', '公司老板', '公司审批', '2019-06-19 13:16:56', '等待处理', NULL, NULL);
INSERT INTO `check_apply_state` VALUES (10, 53, '公司员工', '公司老板', '公司审批', '2019-07-17 13:35:54', '已处理', '审核通过', '2019-07-18 13:36:05');
INSERT INTO `check_apply_state` VALUES (11, 53, '公司老板', 'admin', '验收审批', '2019-07-18 13:36:05', '已处理', '审核通过', '2019-07-26 13:44:40');
INSERT INTO `check_apply_state` VALUES (12, 53, 'admin', 'admin', '等待公司提交文件', '2019-07-26 13:44:40', '已处理', '审核通过', '2019-07-26 13:44:58');
INSERT INTO `check_apply_state` VALUES (13, 53, 'admin', 'admin', '等待公司提交文件', '2019-07-26 13:44:58', '已处理', '审核通过', '2019-07-26 13:46:47');
INSERT INTO `check_apply_state` VALUES (14, 53, 'admin', NULL, '等待公司提交文件', '2019-07-26 13:46:47', '等待处理', NULL, NULL);
INSERT INTO `check_apply_state` VALUES (15, 54, '公司员工', '公司老板', '公司审批', '2019-07-09 13:49:21', '已处理', '审核通过', '2019-07-10 13:49:32');
INSERT INTO `check_apply_state` VALUES (16, 54, '公司老板', 'admin', '验收初审', '2019-07-26 13:49:32', '已处理', '审核通过', '2019-07-26 13:50:35');
INSERT INTO `check_apply_state` VALUES (17, 54, 'admin', NULL, '等待公司提交文件', '2019-07-26 13:50:35', '等待处理', NULL, NULL);
INSERT INTO `check_apply_state` VALUES (18, 55, '公司员工', '公司老板', '公司审批', '2019-07-01 13:52:16', '已处理', '审核通过', '2019-07-04 13:52:27');
INSERT INTO `check_apply_state` VALUES (19, 55, '公司老板', 'admin', '验收初审', '2019-07-04 13:52:27', '已处理', '审核通过', '2019-07-26 15:33:30');
INSERT INTO `check_apply_state` VALUES (20, 55, 'admin', 'admin', '等待公司提交文件', '2019-07-26 15:33:30', '已处理', '审核通过', '2019-07-29 09:47:23');
INSERT INTO `check_apply_state` VALUES (21, 55, 'admin', 'admin', '等待公司上传最终验收报告', '2019-07-29 09:47:23', '已处理', '审核通过', '2019-07-29 09:50:26');
INSERT INTO `check_apply_state` VALUES (22, 55, 'admin', 'admin', '等待公司上传最终验收报告', '2019-07-29 09:50:26', '已处理', '审核通过', '2019-07-29 09:52:29');
INSERT INTO `check_apply_state` VALUES (23, 55, 'admin', 'admin', '等待公司上传最终验收报告', '2019-07-29 09:52:29', '已退回', '就是不让你通过', '2019-07-29 09:53:07');
INSERT INTO `check_apply_state` VALUES (24, 55, 'admin', 'admin', '等待公司上传最终验收报告', '2019-07-29 09:53:07', '已退回', '就是不让你通过22222', '2019-07-29 09:53:55');
INSERT INTO `check_apply_state` VALUES (25, 55, 'admin', 'admin', '等待公司上传最终验收报告', '2019-07-29 09:53:55', '已处理', '审核通过', '2019-07-29 09:54:20');
INSERT INTO `check_apply_state` VALUES (26, 55, 'admin', 'admin', '等待公司上传最终验收报告', '2019-07-29 09:54:20', '已处理', '审核通过', '2019-07-29 09:55:37');
INSERT INTO `check_apply_state` VALUES (27, 55, 'admin', 'admin', '等待公司上传最终验收报告', '2019-07-29 09:55:37', '已退回', '啦啦啦啦啦啦', '2019-07-29 09:56:03');
INSERT INTO `check_apply_state` VALUES (28, 55, 'admin', NULL, '通过初审，等待提交专家表', '2019-07-29 09:56:03', '等待处理', NULL, NULL);
INSERT INTO `check_apply_state` VALUES (29, 56, '公司员工', '公司老板', '等待员工提交', '2019-07-01 14:20:29', '已处理', '审核通过', '2019-07-04 14:20:48');
INSERT INTO `check_apply_state` VALUES (30, 56, '公司老板', 'admin', '等待验收初审', '2019-07-04 14:20:48', '已处理', '审核通过', '2019-07-29 14:26:42');
INSERT INTO `check_apply_state` VALUES (31, 56, 'admin', 'admin', '等待公司提交文件', '2019-07-29 14:26:42', '已退回', '啦啦啦啦啦啦', '2019-07-29 14:30:23');
INSERT INTO `check_apply_state` VALUES (32, 56, 'admin', 'admin', '等待企业管理员提交', '2019-07-29 14:30:23', '已处理', '审核通过', '2019-07-29 14:32:45');
INSERT INTO `check_apply_state` VALUES (33, 56, 'admin', 'admin', '通过初审，等待提交专家表', '2019-07-29 14:32:45', '已退回', '啦啦啦啦啦啦', '2019-07-29 14:33:29');
INSERT INTO `check_apply_state` VALUES (34, 56, 'admin', 'admin', '等待企业管理员提交', '2019-07-29 14:33:29', '已处理', '审核通过', '2019-07-29 15:34:47');
INSERT INTO `check_apply_state` VALUES (35, 56, 'admin', 'admin', '等待公司上传最终验收报告', '2019-07-29 15:34:47', '已处理', '审核通过', '2019-07-29 15:45:44');
INSERT INTO `check_apply_state` VALUES (36, 56, 'admin', 'admin', '等待公司上传最终验收报告', '2019-07-29 15:45:44', '已处理', '审核通过', '2019-07-29 15:50:42');
INSERT INTO `check_apply_state` VALUES (37, 56, 'admin', 'admin', '等待公司上传最终验收报告', '2019-07-29 15:50:42', '已处理', '审核通过', '2019-07-29 17:27:39');
INSERT INTO `check_apply_state` VALUES (38, 56, 'admin', 'admin', '等待公司上传最终验收报告', '2019-07-29 17:27:39', '已处理', '审核通过', '2019-07-29 17:29:48');
INSERT INTO `check_apply_state` VALUES (39, 56, 'admin', 'admin', '等待公司上传最终验收报告', '2019-07-29 17:29:48', '已处理', '审核通过', '2019-07-29 17:30:53');
INSERT INTO `check_apply_state` VALUES (40, 56, 'admin', 'admin', '等待公司上传最终验收报告', '2019-07-29 17:30:53', '已处理', '审核通过', '2019-07-29 17:32:50');
INSERT INTO `check_apply_state` VALUES (41, 56, 'admin', 'admin', '等待公司上传最终验收报告', '2019-07-29 17:32:50', '已退回', '就是不给你通过', '2019-07-30 08:47:22');
INSERT INTO `check_apply_state` VALUES (42, 56, 'admin', 'admin', '等待公司上传最终验收报告', '2019-07-30 08:47:22', '已处理', '审核通过', '2019-07-30 08:47:59');

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `classification` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类',
  `content` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `classification_id` int(11) NOT NULL COMMENT '分类id',
  `content_id` int(11) NOT NULL COMMENT '内容id',
  `state` int(5) NOT NULL COMMENT '启用（0:逻辑删除  1：启用）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 89 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES (1, '单位性质', '大专院校', 1, 1, 1);
INSERT INTO `dictionary` VALUES (2, '单位性质', '科研院所', 1, 2, 1);
INSERT INTO `dictionary` VALUES (3, '单位性质', '企业', 1, 3, 1);
INSERT INTO `dictionary` VALUES (4, '单位性质', '其他', 1, 4, 1);
INSERT INTO `dictionary` VALUES (5, '申请验收形式', '会评', 2, 1, 1);
INSERT INTO `dictionary` VALUES (6, '申请验收形式', '函评', 2, 2, 1);
INSERT INTO `dictionary` VALUES (7, '验收提交资料清单', '课题验收申请表', 3, 1, 1);
INSERT INTO `dictionary` VALUES (8, '验收提交资料清单', '课题合同书', 3, 2, 1);
INSERT INTO `dictionary` VALUES (9, '验收提交资料清单', '课题设计书', 3, 3, 1);
INSERT INTO `dictionary` VALUES (10, '验收提交资料清单', '课题工作报告', 3, 4, 1);
INSERT INTO `dictionary` VALUES (11, '验收提交资料清单', '课题经费决算表', 3, 5, 1);
INSERT INTO `dictionary` VALUES (12, '验收提交资料清单', '课题技术报告', 3, 6, 1);
INSERT INTO `dictionary` VALUES (13, '验收提交资料清单', '其他（发表学术论文、用户意见、监测报告等）', 3, 7, 1);
INSERT INTO `dictionary` VALUES (14, '成果形式', '论文著作', 4, 1, 1);
INSERT INTO `dictionary` VALUES (15, '成果形式', '研究（咨询）报告', 4, 2, 1);
INSERT INTO `dictionary` VALUES (16, '成果形式', '新产品', 4, 3, 1);
INSERT INTO `dictionary` VALUES (17, '成果形式', '新装置', 4, 4, 1);
INSERT INTO `dictionary` VALUES (18, '成果形式', '新材料', 4, 5, 1);
INSERT INTO `dictionary` VALUES (19, '成果形式', '新工艺(或新方法、新模式)', 4, 6, 1);
INSERT INTO `dictionary` VALUES (20, '成果形式', '计算机软件', 4, 7, 1);
INSERT INTO `dictionary` VALUES (21, '成果形式', '技术标准', 4, 8, 1);
INSERT INTO `dictionary` VALUES (22, '成果形式', '专利', 4, 9, 1);
INSERT INTO `dictionary` VALUES (23, '成果形式', '其他', 4, 10, 1);
INSERT INTO `dictionary` VALUES (24, '成果水平', '国际领先', 5, 1, 1);
INSERT INTO `dictionary` VALUES (25, '成果水平', '国际先进', 5, 2, 1);
INSERT INTO `dictionary` VALUES (26, '成果水平', '国际领先', 5, 3, 1);
INSERT INTO `dictionary` VALUES (27, '成果水平', '国内先进', 5, 4, 1);
INSERT INTO `dictionary` VALUES (28, '成果水平', '省内领先', 5, 5, 1);
INSERT INTO `dictionary` VALUES (29, '成果水平', '其他', 5, 6, 1);
INSERT INTO `dictionary` VALUES (30, '工作性质', '科研(教育)', 6, 1, 1);
INSERT INTO `dictionary` VALUES (31, '工作性质', '咨询(设计、规划)', 6, 2, 1);
INSERT INTO `dictionary` VALUES (32, '工作性质', '管理及其他', 6, 3, 1);
INSERT INTO `dictionary` VALUES (33, '专业领域', '水环境治理', 7, 1, 1);
INSERT INTO `dictionary` VALUES (34, '专业领域', '大气环境保护', 7, 2, 1);
INSERT INTO `dictionary` VALUES (35, '专业领域', '土壤环境治理', 7, 3, 1);
INSERT INTO `dictionary` VALUES (36, '专业领域', '自然生态保护', 7, 4, 1);
INSERT INTO `dictionary` VALUES (37, '专业领域', '环境监测监控', 7, 5, 1);
INSERT INTO `dictionary` VALUES (38, '专业领域', '核与辐射防治', 7, 6, 1);
INSERT INTO `dictionary` VALUES (39, '专业领域', '规划与战略研究', 7, 7, 1);
INSERT INTO `dictionary` VALUES (40, '专业领域', '污染预防与控制(清洁生产)', 7, 8, 1);
INSERT INTO `dictionary` VALUES (41, '课题按合同计划进度执行情况', '超额完成', 8, 1, 1);
INSERT INTO `dictionary` VALUES (42, '课题按合同计划进度执行情况', '完成', 8, 2, 1);
INSERT INTO `dictionary` VALUES (43, '课题按合同计划进度执行情况', '基本完成', 8, 3, 1);
INSERT INTO `dictionary` VALUES (44, '课题按合同计划进度执行情况', '未完成', 8, 4, 1);
INSERT INTO `dictionary` VALUES (45, '课题进展情况类型', '超前', 9, 1, 1);
INSERT INTO `dictionary` VALUES (46, '课题进展情况类型', '正常', 9, 2, 1);
INSERT INTO `dictionary` VALUES (47, '课题进展情况类型', '之后', 9, 3, 1);
INSERT INTO `dictionary` VALUES (48, '课题未按时完成原因', '技术变化', 10, 1, 1);
INSERT INTO `dictionary` VALUES (49, '课题未按时完成原因', '经费未落实', 10, 2, 1);
INSERT INTO `dictionary` VALUES (50, '课题未按时完成原因', '项目负责人或技术骨干变动', 10, 3, 1);
INSERT INTO `dictionary` VALUES (51, '课题未按时完成原因', '协作关系影响', 10, 4, 1);
INSERT INTO `dictionary` VALUES (52, '课题未按时完成原因', '其他原因', 10, 5, 1);
INSERT INTO `dictionary` VALUES (53, '汇报情况', '内容清楚', 11, 1, 1);
INSERT INTO `dictionary` VALUES (54, '汇报情况', '内容基本清楚', 11, 2, 1);
INSERT INTO `dictionary` VALUES (55, '汇报情况', '内容不够清楚', 11, 3, 1);
INSERT INTO `dictionary` VALUES (56, '进度执行情况', '超额完成', 12, 1, 1);
INSERT INTO `dictionary` VALUES (57, '进度执行情况', '完成', 12, 2, 1);
INSERT INTO `dictionary` VALUES (58, '进度执行情况', '基本完成', 12, 3, 1);
INSERT INTO `dictionary` VALUES (59, '进度执行情况', '未完成', 12, 4, 1);
INSERT INTO `dictionary` VALUES (60, '课题实施所需条件', '落实', 13, 1, 1);
INSERT INTO `dictionary` VALUES (61, '课题实施所需条件', '基本落实', 13, 2, 1);
INSERT INTO `dictionary` VALUES (62, '课题实施所需条件', '未落实', 13, 3, 1);
INSERT INTO `dictionary` VALUES (63, '技术/经济考核指标', '达到合同规定', 14, 1, 1);
INSERT INTO `dictionary` VALUES (64, '技术/经济考核指标', '基本达到合同规定', 14, 2, 1);
INSERT INTO `dictionary` VALUES (65, '技术/经济考核指标', '未达合同规定', 14, 3, 1);
INSERT INTO `dictionary` VALUES (66, '经费执行情况', '合理', 15, 1, 1);
INSERT INTO `dictionary` VALUES (67, '经费执行情况', '基本合理', 15, 2, 1);
INSERT INTO `dictionary` VALUES (68, '经费执行情况', '不合理', 15, 3, 1);
INSERT INTO `dictionary` VALUES (69, '下一步工作计划', '可行', 16, 1, 1);
INSERT INTO `dictionary` VALUES (70, '下一步工作计划', '基本可性', 16, 2, 1);
INSERT INTO `dictionary` VALUES (71, '下一步工作计划', '不可行', 16, 3, 1);
INSERT INTO `dictionary` VALUES (72, '评级等次', '优秀', 17, 1, 1);
INSERT INTO `dictionary` VALUES (73, '评级等次', '良好', 17, 2, 1);
INSERT INTO `dictionary` VALUES (74, '评级等次', '一般', 17, 3, 1);
INSERT INTO `dictionary` VALUES (75, '评级等次', '较差', 17, 4, 1);
INSERT INTO `dictionary` VALUES (76, '所属类别', '综合师范类', 18, 1, 1);
INSERT INTO `dictionary` VALUES (77, '所属类别', '技术研发类', 18, 2, 1);
INSERT INTO `dictionary` VALUES (78, '所属类别', '重大技术攻关类', 18, 3, 1);
INSERT INTO `dictionary` VALUES (79, '所属领域', '水污染防治领域', 19, 1, 1);
INSERT INTO `dictionary` VALUES (80, '所属领域', '大气污染防治领域', 19, 2, 1);
INSERT INTO `dictionary` VALUES (81, '所属领域', '土壤及地下水污染防治领域', 19, 3, 1);
INSERT INTO `dictionary` VALUES (82, '所属领域', '固废与辐射污染防治领域', 19, 4, 1);
INSERT INTO `dictionary` VALUES (83, '所属领域', '自然与生态领域', 19, 5, 1);
INSERT INTO `dictionary` VALUES (84, '所属领域', '标准政策法规', 19, 6, 1);
INSERT INTO `dictionary` VALUES (85, '所属领域', '其他', 19, 7, 1);
INSERT INTO `dictionary` VALUES (86, '验收结论', '通过验收', 20, 1, 1);
INSERT INTO `dictionary` VALUES (87, '验收结论', '结题', 20, 2, 1);
INSERT INTO `dictionary` VALUES (88, '验收结论', '不通过验收', 20, 3, 1);

-- ----------------------------
-- Table structure for expert_acceptance_review
-- ----------------------------
DROP TABLE IF EXISTS `expert_acceptance_review`;
CREATE TABLE `expert_acceptance_review`  (
  `ar_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id（专家评议表）',
  `ac_id` int(11) NULL DEFAULT NULL COMMENT '验收申请表id',
  `topic_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课题名称',
  `topic_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课题编号',
  `project_leader` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课题负责人',
  `subject_undertaking_unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课题承担单位',
  `research_content_grade` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '研究内容完成分数',
  `technical_indicator_grade` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '技术经济指标分数',
  `environment_objective_grade` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环保目标及效益分数',
  `outcome_level_grade` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '成果水平及应用情况分数',
  `plan_progress_grade` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '计划进度分数',
  `technical_information_grade` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织实施与技术资料分数',
  `financial_situation_grade` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经费执行情况分数',
  `total_score` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总分',
  `acceptance_conclusion_id` int(10) NULL DEFAULT NULL COMMENT '验收结论id',
  `opinion` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '意见',
  `expert_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专家姓名',
  `date` date NULL DEFAULT NULL COMMENT '日期',
  `create_author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ar_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expert_acceptance_review
-- ----------------------------
INSERT INTO `expert_acceptance_review` VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for expert_group_comments
-- ----------------------------
DROP TABLE IF EXISTS `expert_group_comments`;
CREATE TABLE `expert_group_comments`  (
  `egc_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id  (专家组意见表)',
  `ca_id` int(11) NULL DEFAULT NULL COMMENT '验收申请表id',
  `topic_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课题名称',
  `topic_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课题编号',
  `project_leader` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课题负责人',
  `subject_undertaking_unit` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课题承担单位',
  `acceptance_expert_number` int(11) NULL DEFAULT NULL COMMENT '验收专家人数',
  `expert_one_grade` decimal(5, 2) NULL DEFAULT NULL COMMENT '专家1评分',
  `expert_two_grade` decimal(5, 2) NULL DEFAULT NULL COMMENT '专家2评分',
  `expert_three_grade` decimal(5, 2) NULL DEFAULT NULL COMMENT '专家3评分',
  `expert_four_grade` decimal(5, 2) NULL DEFAULT NULL COMMENT '专家4评分',
  `expert_five_grade` decimal(5, 2) NULL DEFAULT NULL COMMENT '专家5评分',
  `expert_six_grade` decimal(5, 2) NULL DEFAULT NULL COMMENT '专家6评分',
  `expert_seven_grade` decimal(5, 2) NULL DEFAULT NULL COMMENT '专家7评分',
  `synthesize_grade` decimal(5, 1) NULL DEFAULT NULL COMMENT '综合得分',
  `topic_overall_evaluation` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对课题情况的总体评价',
  `suggest` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建议',
  `acceptance_conclusion_id` int(11) NULL DEFAULT NULL COMMENT '验收结论id',
  `expert_leader` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专家组组长姓名',
  `write_date` date NULL DEFAULT NULL COMMENT '日期',
  `create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `create_author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`egc_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expert_group_comments
-- ----------------------------
INSERT INTO `expert_group_comments` VALUES (1, 56, '课题名称测试1111', '课题编号测试1111', '课题负责人测试', '课题承担单位测试111', 20, 12.30, 32.10, 89.30, 32.10, 44.30, 84.20, 33.20, 23.4, '课题情况的总体评价测试111', '建议111', 88, '专家组组长姓名测试111', '2011-05-14', '2019-07-30 11:52:20.000000', 'admin');

-- ----------------------------
-- Table structure for expert_group_comments_name
-- ----------------------------
DROP TABLE IF EXISTS `expert_group_comments_name`;
CREATE TABLE `expert_group_comments_name`  (
  `egc_nid` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id (专家组意见从表)',
  `egc_id` int(10) NULL DEFAULT NULL COMMENT '专家意见表id',
  `expert_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专家姓名',
  `company_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位名称',
  `major` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业',
  `job` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职务',
  PRIMARY KEY (`egc_nid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expert_group_comments_name
-- ----------------------------
INSERT INTO `expert_group_comments_name` VALUES (1, 1, '专家姓名测试1111', '单位名称测试1111', '专业测试1111', '职务测试111111');
INSERT INTO `expert_group_comments_name` VALUES (2, 1, '专家姓名测试22222', '单位名称测试22222', '专业测试2222', '职务测试222222');
INSERT INTO `expert_group_comments_name` VALUES (3, 1, '专家姓名测试33333', '单位名称测试33333', '专业测试3333', '职务测试3333333');

-- ----------------------------
-- Table structure for expert_information
-- ----------------------------
DROP TABLE IF EXISTS `expert_information`;
CREATE TABLE `expert_information`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `sex` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `birth_date` date NOT NULL COMMENT '出生日期',
  `education` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学历',
  `present_post` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '现任职务',
  `technical_title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '技术职称',
  `study_major` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所学专业',
  `professionalism` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '从事专业',
  `work_unit` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工作单位',
  `postal_address` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通讯地址',
  `postal_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮政编码',
  `work_telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位电话',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机',
  `mail` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电子邮箱',
  `nature_work` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工作性质',
  `professional_field` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专业领域',
  `curriculum_vitae` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '个人简历',
  `is_first` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否第一次登陆 （true: 默认，false:意味着已经第一次强制修改过密码了）',
  `create_author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建此条信息的人',
  `create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建此条信息的时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expert_information
-- ----------------------------
INSERT INTO `expert_information` VALUES (1, '张三', NULL, '', '男', '1996-10-23', '本科', '主任', '技术部主任', '环境治理', '环境治理专业', '南京环境治理公司', '南京市江宁区', '233000', '5236478', '18532158651', '18535154865@qq.com', '研究环境', '环境治理', '18918156158gergegegregergergergergergergergregeuigueirhgeuihguei', NULL, NULL, NULL);
INSERT INTO `expert_information` VALUES (2, '李四', NULL, '', '男', '1996-10-23', '本科', '主任', '技术部主任', '环境治理', '环境治理专业', '南京环境治理公司', '南京市江宁区', '233000', '5236478', '18532158651', '18535154865@qq.com', '研究环境', '环境治理', '18918156158gergegegregergergergergergergergregeuigueirhgeuihguei', NULL, NULL, NULL);
INSERT INTO `expert_information` VALUES (3, '张三', NULL, '', '男', '1996-10-23', '本科', '主任', '技术部主任', '环境治理', '环境治理专业', '南京环境治理公司', '南京市江宁区', '233000', '5236478', '18532158651', '18535154865@qq.com', '研究环境', '环境治理', '18918156158gergegegregergergergergergergergregeuigueirhgeuihguei', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for expert_information_article
-- ----------------------------
DROP TABLE IF EXISTS `expert_information_article`;
CREATE TABLE `expert_information_article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `expert_id` int(11) NOT NULL COMMENT '专家信息表id',
  `article_title` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章题目',
  `authorr_ranking` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者排序',
  `title_articles_periodicals` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章期刊名',
  `articles_publication_time` date NOT NULL COMMENT '文章发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expert_information_article
-- ----------------------------
INSERT INTO `expert_information_article` VALUES (1, 1, '第一文章题目', '第一作者排序', '第一文章期刊名', '1999-07-12');
INSERT INTO `expert_information_article` VALUES (2, 2, '第二文章题目', '第二作者排序', '第二文章期刊名', '1999-07-12');
INSERT INTO `expert_information_article` VALUES (3, 1, '第三文章题目', '第三作者排序', '第三文章期刊名', '1999-07-12');

-- ----------------------------
-- Table structure for expert_information_book
-- ----------------------------
DROP TABLE IF EXISTS `expert_information_book`;
CREATE TABLE `expert_information_book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `expert_id` int(11) NOT NULL COMMENT '专家信息id',
  `work_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '著作名称',
  `sort` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '排序',
  `book_publishing_house` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '著作出版社',
  `writing_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '著作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expert_information_book
-- ----------------------------
INSERT INTO `expert_information_book` VALUES (1, 1, '第一著作名称', '第一排序', '第一著作出版社', '1856-02-23');
INSERT INTO `expert_information_book` VALUES (2, 1, '第二著作名称', '第二排序', '第二著作出版社', '1856-02-23');
INSERT INTO `expert_information_book` VALUES (3, 2, '第三著作名称', '第三排序', '第三著作出版社', '1856-02-23');

-- ----------------------------
-- Table structure for expert_information_patent
-- ----------------------------
DROP TABLE IF EXISTS `expert_information_patent`;
CREATE TABLE `expert_information_patent`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `expert_id` int(11) NOT NULL COMMENT '专家信息id',
  `patent_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专利名称',
  `patent_types` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专利类型',
  `patent_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专利号',
  `patent_time` date NOT NULL COMMENT '专利时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expert_information_patent
-- ----------------------------
INSERT INTO `expert_information_patent` VALUES (1, 1, '第一专利名称', '第一专利类型', '第一专利号', '1523-11-23');
INSERT INTO `expert_information_patent` VALUES (2, 1, '第二专利名称', '第二专利类型', '第二专利号', '1523-11-23');
INSERT INTO `expert_information_patent` VALUES (3, 2, '第三专利名称', '第三专利类型', '第三专利号', '1523-11-23');

-- ----------------------------
-- Table structure for expert_information_prize_winning
-- ----------------------------
DROP TABLE IF EXISTS `expert_information_prize_winning`;
CREATE TABLE `expert_information_prize_winning`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `expert_id` int(11) NOT NULL COMMENT '专家信息id',
  `prize_winning_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '获奖名称',
  `prize_winning_order` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '获奖排序',
  `prize_winning_department` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '获奖部门',
  `prize_winning_time` date NOT NULL COMMENT '获奖时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expert_information_prize_winning
-- ----------------------------
INSERT INTO `expert_information_prize_winning` VALUES (1, 1, '第一获奖名称', '第一获奖排序', '第一颁奖部门', '1544-06-14');
INSERT INTO `expert_information_prize_winning` VALUES (2, 1, '第二获奖名称', '第二获奖排序', '第二颁奖部门', '1544-06-14');
INSERT INTO `expert_information_prize_winning` VALUES (3, 2, '第三获奖名称', '第三获奖排序', '第三颁奖部门', '1544-06-14');

-- ----------------------------
-- Table structure for expert_information_research_direction
-- ----------------------------
DROP TABLE IF EXISTS `expert_information_research_direction`;
CREATE TABLE `expert_information_research_direction`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `expert_id` int(11) NULL DEFAULT NULL COMMENT '专家信息表id',
  `main_research_directions` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主要研究方向',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expert_information_research_direction
-- ----------------------------
INSERT INTO `expert_information_research_direction` VALUES (1, 1, '第一研究方向');
INSERT INTO `expert_information_research_direction` VALUES (2, 2, '第二研究方向');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `is_father` int(10) NULL DEFAULT NULL COMMENT '是否是一级菜单 0：一级 1：二级菜单',
  `father_id` int(11) NULL DEFAULT NULL COMMENT '父id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '指南管理', 0, 0);
INSERT INTO `menu` VALUES (2, '指南征集', 1, 1);
INSERT INTO `menu` VALUES (3, '指南汇总', 1, 1);
INSERT INTO `menu` VALUES (4, '指南发布', 1, 1);
INSERT INTO `menu` VALUES (5, '课题立项', 0, 0);
INSERT INTO `menu` VALUES (6, '招标备案', 1, 5);
INSERT INTO `menu` VALUES (7, '课题审批', 1, 5);
INSERT INTO `menu` VALUES (8, '合同管理', 0, 0);
INSERT INTO `menu` VALUES (9, '合同中心', 1, 8);
INSERT INTO `menu` VALUES (10, '合同审批', 1, 8);
INSERT INTO `menu` VALUES (11, '日常管理', 0, 0);
INSERT INTO `menu` VALUES (12, '中期检查发起', 1, 11);
INSERT INTO `menu` VALUES (13, '中期检查查询', 1, 11);
INSERT INTO `menu` VALUES (14, '进展报告', 1, 11);
INSERT INTO `menu` VALUES (15, '重大事项管理', 1, 11);
INSERT INTO `menu` VALUES (16, '课题验收', 0, 0);
INSERT INTO `menu` VALUES (17, '验收申请', 1, 16);
INSERT INTO `menu` VALUES (18, '验收审核', 1, 16);
INSERT INTO `menu` VALUES (19, '课题验收', 1, 16);
INSERT INTO `menu` VALUES (20, '验收结束', 1, 16);
INSERT INTO `menu` VALUES (21, '成果管理', 0, 0);
INSERT INTO `menu` VALUES (22, '成果新增', 1, 21);
INSERT INTO `menu` VALUES (23, '成果库', 1, 21);
INSERT INTO `menu` VALUES (24, '专家管理', 0, 0);
INSERT INTO `menu` VALUES (25, '专家库', 1, 24);
INSERT INTO `menu` VALUES (26, '专家新增', 1, 24);
INSERT INTO `menu` VALUES (27, '信用管理', 0, 0);
INSERT INTO `menu` VALUES (28, '承担单位信用', 1, 27);
INSERT INTO `menu` VALUES (29, '责任人信用', 1, 27);
INSERT INTO `menu` VALUES (30, '专家信用', 1, 27);
INSERT INTO `menu` VALUES (31, '统计分析', 0, 0);
INSERT INTO `menu` VALUES (32, '年度环保科研统计分析', 1, 31);
INSERT INTO `menu` VALUES (33, '成果统计分析', 1, 31);
INSERT INTO `menu` VALUES (34, '课题项目分布', 1, 31);
INSERT INTO `menu` VALUES (35, '通知公告', 0, 0);
INSERT INTO `menu` VALUES (36, '通知公告', 1, 35);
INSERT INTO `menu` VALUES (37, '后台管理', 0, 0);
INSERT INTO `menu` VALUES (38, '个人信息管理', 1, 37);
INSERT INTO `menu` VALUES (39, '管理员管理', 1, 37);

-- ----------------------------
-- Table structure for outcome_information
-- ----------------------------
DROP TABLE IF EXISTS `outcome_information`;
CREATE TABLE `outcome_information`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `topic_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题编号',
  `topic_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题名称',
  `application_unit_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用单位名称',
  `postal_address` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通讯地址',
  `correspondence_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通讯邮编',
  `achievement_start_time` date NOT NULL COMMENT '成果开始时间',
  `economic_performance` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用情况、社会经济效益（含计算过程）',
  `enclosure` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '成果信息附件url',
  `achievement_end_time` date NOT NULL COMMENT '成果结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of outcome_information
-- ----------------------------
INSERT INTO `outcome_information` VALUES (4, '12121', '课题名称1', '应用单位1', '通讯地址1', '通讯邮编1', '2019-07-03', '应用情况1', '成果附件1', '2019-07-18');
INSERT INTO `outcome_information` VALUES (5, '5445', '课题名称2', '应用单位2', '通讯地址2', '通讯邮编2', '2019-07-03', '应用情况2', '成果附件2', '2019-07-18');
INSERT INTO `outcome_information` VALUES (6, '5445', '课题名称1', '应用单位2', '通讯地址2', '通讯邮编2', '2019-07-03', '应用情况2', '成果附件2', '2019-07-18');
INSERT INTO `outcome_information` VALUES (7, '5445', '课题名称1', '应用单位1', '通讯地址2', '通讯邮编2', '2019-07-03', '应用情况2', '成果附件2', '2019-07-18');
INSERT INTO `outcome_information` VALUES (8, '5445', '课题名称2', '应用单位2', '通讯地址2', '通讯邮编2', '2019-07-03', '应用情况2', '成果附件2', '2019-07-18');
INSERT INTO `outcome_information` VALUES (9, '5445', '课题名称2', '应用单位1', '通讯地址2', '通讯邮编2', '2019-07-03', '应用情况2', '成果附件2', '2019-07-18');
INSERT INTO `outcome_information` VALUES (10, '5445', '课题名称2', '应用单位1', '通讯地址2', '通讯邮编2', '2019-07-03', '应用情况2', '成果附件2', '2019-07-18');

-- ----------------------------
-- Table structure for shiro_company_name
-- ----------------------------
DROP TABLE IF EXISTS `shiro_company_name`;
CREATE TABLE `shiro_company_name`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公司id',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shiro_company_name
-- ----------------------------
INSERT INTO `shiro_company_name` VALUES (1, '希11');
INSERT INTO `shiro_company_name` VALUES (2, '希1122');
INSERT INTO `shiro_company_name` VALUES (3, '小溪迈德');
INSERT INTO `shiro_company_name` VALUES (4, '小溪迈德2');
INSERT INTO `shiro_company_name` VALUES (5, '小溪迈德888');
INSERT INTO `shiro_company_name` VALUES (6, '小溪迈德8889');
INSERT INTO `shiro_company_name` VALUES (7, '小溪迈德88891');
INSERT INTO `shiro_company_name` VALUES (8, '小溪迈德888912');
INSERT INTO `shiro_company_name` VALUES (9, '小溪迈德888912333');
INSERT INTO `shiro_company_name` VALUES (12, '新溪卖得');
INSERT INTO `shiro_company_name` VALUES (13, '新溪卖得1');
INSERT INTO `shiro_company_name` VALUES (14, 'xdmd');

-- ----------------------------
-- Table structure for shiro_permission
-- ----------------------------
DROP TABLE IF EXISTS `shiro_permission`;
CREATE TABLE `shiro_permission`  (
  `id` int(11) NOT NULL COMMENT '主键id',
  `permission_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for shiro_role
-- ----------------------------
DROP TABLE IF EXISTS `shiro_role`;
CREATE TABLE `shiro_role`  (
  `id` int(1) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shiro_role
-- ----------------------------
INSERT INTO `shiro_role` VALUES (1, '管理员');

-- ----------------------------
-- Table structure for shiro_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `shiro_role_menu`;
CREATE TABLE `shiro_role_menu`  (
  `rid` int(11) NOT NULL COMMENT '角色id',
  `mid` int(11) NOT NULL COMMENT '菜单id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shiro_role_menu
-- ----------------------------
INSERT INTO `shiro_role_menu` VALUES (1, 1);
INSERT INTO `shiro_role_menu` VALUES (1, 5);
INSERT INTO `shiro_role_menu` VALUES (1, 8);
INSERT INTO `shiro_role_menu` VALUES (1, 11);
INSERT INTO `shiro_role_menu` VALUES (1, 16);
INSERT INTO `shiro_role_menu` VALUES (1, 21);
INSERT INTO `shiro_role_menu` VALUES (1, 24);
INSERT INTO `shiro_role_menu` VALUES (1, 27);
INSERT INTO `shiro_role_menu` VALUES (1, 31);
INSERT INTO `shiro_role_menu` VALUES (1, 35);
INSERT INTO `shiro_role_menu` VALUES (1, 37);

-- ----------------------------
-- Table structure for shiro_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `shiro_role_permission`;
CREATE TABLE `shiro_role_permission`  (
  `rid` int(11) NOT NULL COMMENT '角色id',
  `pid` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for shiro_user
-- ----------------------------
DROP TABLE IF EXISTS `shiro_user`;
CREATE TABLE `shiro_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆名',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `is_delete` int(5) NULL DEFAULT NULL COMMENT '是否启用 0：禁止 1：启用',
  `status` int(5) NULL DEFAULT NULL COMMENT '身份判断 0：管理员 1：部长 2：员工',
  `modify` int(5) NULL DEFAULT NULL COMMENT '员工修改登陆名次数 默认是1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shiro_user
-- ----------------------------
INSERT INTO `shiro_user` VALUES (1, 'admin', 'admin', '21232f297a57a5a743894a0e4a801fc3', '管理员', '2019-07-23 14:16:24', 1, 0, NULL);

-- ----------------------------
-- Table structure for shiro_user_role
-- ----------------------------
DROP TABLE IF EXISTS `shiro_user_role`;
CREATE TABLE `shiro_user_role`  (
  `uid` int(11) NOT NULL COMMENT '用户id',
  `rid` int(11) NOT NULL COMMENT '角色id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shiro_user_role
-- ----------------------------
INSERT INTO `shiro_user_role` VALUES (1, 1);

-- ----------------------------
-- Table structure for upload_file
-- ----------------------------
DROP TABLE IF EXISTS `upload_file`;
CREATE TABLE `upload_file`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `upload_surface_id` int(20) NULL DEFAULT NULL COMMENT '上传文件表Id',
  `upload_file_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上传文件地址',
  `upload_file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上传文件名',
  `date_file_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日期文件名',
  `upload_file_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上传文件类型',
  `upload_suffix_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上传文件后缀名',
  `file_size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件大小',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 83 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of upload_file
-- ----------------------------
INSERT INTO `upload_file` VALUES (49, 40, 'D://xdmd_environment//Extranet//公司名//验收申请表//2019-07-19成果附件测试.docx', '成果附件测试.docx', '成果附件测试.docx', '验收申请表', 'docx', '11121', '2019-07-19 14:39:36', '创建人');
INSERT INTO `upload_file` VALUES (50, 40, 'D://xdmd_environment//Extranet//公司名//成果附件//2019-07-19//成果附件测试2.docx', '成果附件测试2.docx', '2019-07-19成果附件测试2.docx', '成果附件', 'docx', '0', '2019-07-19 14:39:36', '创建人');
INSERT INTO `upload_file` VALUES (51, 40, 'D://xdmd_environment//Extranet//公司名//提交清单文件//2019-07-19//提交清单3.zip', '提交清单3.zip', '2019-07-19提交清单3.zip', '提交清单', 'zip', '22', '2019-07-19 14:39:36', '创建人');
INSERT INTO `upload_file` VALUES (52, 41, 'D://xdmd_environment//Extranet//公司名//验收申请表//2019-07-18//验收申请表3.docx', '验收申请表3.docx', '2019-07-18验收申请表3.docx', '验收申请表', 'docx', '0', '2019-07-18 16:16:24', '创建人');
INSERT INTO `upload_file` VALUES (53, 41, 'D://xdmd_environment//Extranet//公司名//成果附件//2019-07-18//成果附件3.docx', '成果附件3.docx', '2019-07-18成果附件3.docx', '成果附件', 'docx', '0', '2019-07-18 16:16:24', '创建人');
INSERT INTO `upload_file` VALUES (54, 41, 'D://xdmd_environment//Extranet//公司名//提交清单文件//2019-07-18//提交清单测试.zip', '提交清单测试.zip', '2019-07-18提交清单测试.zip', '提交清单', 'zip', '22', '2019-07-18 16:16:24', '创建人');
INSERT INTO `upload_file` VALUES (55, 42, 'D://xdmd_environment//Extranet//公司名//验收申请表//2019-07-18//验收申请表3.docx', '验收申请表3.docx', '2019-07-18验收申请表3.docx', '验收申请表', 'docx', '0', '2019-07-18 16:16:26', '创建人');
INSERT INTO `upload_file` VALUES (56, 42, 'D://xdmd_environment//Extranet//公司名//成果附件//2019-07-18//成果附件3.docx', '成果附件3.docx', '2019-07-18成果附件3.docx', '成果附件', 'docx', '0', '2019-07-18 16:16:26', '创建人');
INSERT INTO `upload_file` VALUES (57, 42, 'D://xdmd_environment//Extranet//公司名//提交清单文件//2019-07-18//提交清单测试.zip', '提交清单测试.zip', '2019-07-18提交清单测试.zip', '提交清单', 'zip', '22', '2019-07-18 16:16:26', '创建人');
INSERT INTO `upload_file` VALUES (58, 43, 'D://xdmd_environment//Extranet//公司名//验收申请表//2019-07-18//验收申请表3.docx', '验收申请表3.docx', '2019-07-18验收申请表3.docx', '验收申请表', 'docx', '0', '2019-07-18 16:16:27', '创建人');
INSERT INTO `upload_file` VALUES (59, 43, 'D://xdmd_environment//Extranet//公司名//成果附件//2019-07-18//成果附件3.docx', '成果附件3.docx', '2019-07-18成果附件3.docx', '成果附件', 'docx', '0', '2019-07-18 16:16:27', '创建人');
INSERT INTO `upload_file` VALUES (60, 43, 'D://xdmd_environment//Extranet//公司名//提交清单文件//2019-07-18//提交清单测试.zip', '提交清单测试.zip', '2019-07-18提交清单测试.zip', '提交清单', 'zip', '22', '2019-07-18 16:16:27', '创建人');
INSERT INTO `upload_file` VALUES (61, 44, 'D://xdmd_environment//Extranet//公司名//验收申请表//2019-07-18//验收申请表3.docx', '验收申请表3.docx', '2019-07-18验收申请表3.docx', '验收申请表', 'docx', '0', '2019-07-18 16:16:27', '创建人');
INSERT INTO `upload_file` VALUES (62, 44, 'D://xdmd_environment//Extranet//公司名//成果附件//2019-07-18//成果附件3.docx', '成果附件3.docx', '2019-07-18成果附件3.docx', '成果附件', 'docx', '0', '2019-07-18 16:16:27', '创建人');
INSERT INTO `upload_file` VALUES (63, 44, 'D://xdmd_environment//Extranet//公司名//提交清单文件//2019-07-18//提交清单测试.zip', '提交清单测试.zip', '2019-07-18提交清单测试.zip', '提交清单', 'zip', '22', '2019-07-18 16:16:27', '创建人');
INSERT INTO `upload_file` VALUES (64, 45, 'D://xdmd_environment//Extranet//公司名//验收申请表//2019-07-18//验收申请表3.docx', '验收申请表3.docx', '2019-07-18验收申请表3.docx', '验收申请表', 'docx', '0', '2019-07-18 16:16:28', '创建人');
INSERT INTO `upload_file` VALUES (65, 45, 'D://xdmd_environment//Extranet//公司名//成果附件//2019-07-18//成果附件3.docx', '成果附件3.docx', '2019-07-18成果附件3.docx', '成果附件', 'docx', '0', '2019-07-18 16:16:28', '创建人');
INSERT INTO `upload_file` VALUES (66, 45, 'D://xdmd_environment//Extranet//公司名//提交清单文件//2019-07-18//提交清单测试.zip', '提交清单测试.zip', '2019-07-18提交清单测试.zip', '提交清单', 'zip', '22', '2019-07-18 16:16:28', '创建人');
INSERT INTO `upload_file` VALUES (67, 46, 'D://xdmd_environment//Extranet//公司名//验收申请表//2019-07-18//验收申请表3.docx', '验收申请表3.docx', '2019-07-18验收申请表3.docx', '验收申请表', 'docx', '0', '2019-07-18 16:16:28', '创建人');
INSERT INTO `upload_file` VALUES (68, 46, 'D://xdmd_environment//Extranet//公司名//成果附件//2019-07-18//成果附件3.docx', '成果附件3.docx', '2019-07-18成果附件3.docx', '成果附件', 'docx', '0', '2019-07-18 16:16:28', '创建人');
INSERT INTO `upload_file` VALUES (69, 46, 'D://xdmd_environment//Extranet//公司名//提交清单文件//2019-07-18//提交清单测试.zip', '提交清单测试.zip', '2019-07-18提交清单测试.zip', '提交清单', 'zip', '22', '2019-07-18 16:16:28', '创建人');
INSERT INTO `upload_file` VALUES (70, 47, 'D://xdmd_environment//Extranet//公司名//验收申请表//2019-07-18//验收申请表3.docx', '验收申请表3.docx', '2019-07-18验收申请表3.docx', '验收申请表', 'docx', '0', '2019-07-18 16:16:29', '创建人');
INSERT INTO `upload_file` VALUES (71, 47, 'D://xdmd_environment//Extranet//公司名//成果附件//2019-07-18//成果附件3.docx', '成果附件3.docx', '2019-07-18成果附件3.docx', '成果附件', 'docx', '0', '2019-07-18 16:16:29', '创建人');
INSERT INTO `upload_file` VALUES (72, 47, 'D://xdmd_environment//Extranet//公司名//提交清单文件//2019-07-18//提交清单测试.zip', '提交清单测试.zip', '2019-07-18提交清单测试.zip', '提交清单', 'zip', '22', '2019-07-18 16:16:29', '创建人');
INSERT INTO `upload_file` VALUES (73, 48, 'D://xdmd_environment//Extranet//公司名//验收申请表//2019-07-18//验收申请表3.docx', '验收申请表3.docx', '2019-07-18验收申请表3.docx', '验收申请表', 'docx', '0', '2019-07-18 16:16:29', '创建人');
INSERT INTO `upload_file` VALUES (74, 48, 'D://xdmd_environment//Extranet//公司名//成果附件//2019-07-18//成果附件3.docx', '成果附件3.docx', '2019-07-18成果附件3.docx', '成果附件', 'docx', '0', '2019-07-18 16:16:29', '创建人');
INSERT INTO `upload_file` VALUES (75, 48, 'D://xdmd_environment//Extranet//公司名//提交清单文件//2019-07-18//提交清单测试.zip', '提交清单测试.zip', '2019-07-18提交清单测试.zip', '提交清单', 'zip', '22', '2019-07-18 16:16:29', '创建人');
INSERT INTO `upload_file` VALUES (76, 49, 'D://xdmd_environment//Extranet//公司名//验收申请表//2019-07-18//验收申请表3.docx', '验收申请表3.docx', '2019-07-18验收申请表3.docx', '验收申请表', 'docx', '0', '2019-07-18 16:16:30', '创建人');
INSERT INTO `upload_file` VALUES (77, 49, 'D://xdmd_environment//Extranet//公司名//成果附件//2019-07-18//成果附件3.docx', '成果附件3.docx', '2019-07-18成果附件3.docx', '成果附件', 'docx', '0', '2019-07-18 16:16:30', '创建人');
INSERT INTO `upload_file` VALUES (78, 49, 'D://xdmd_environment//Extranet//公司名//提交清单文件//2019-07-18//提交清单测试.zip', '提交清单测试.zip', '2019-07-18提交清单测试.zip', '提交清单', 'zip', '22', '2019-07-18 16:16:30', '创建人');
INSERT INTO `upload_file` VALUES (79, NULL, 'D://xdmd_environment//Intranet//xdmd//专家组意见//成果附件3.docx', '成果附件3.docx', '2019-07-29成果附件3.docx', '专家组意见', 'docx', '0', '2019-07-29 15:24:43', 'admin');
INSERT INTO `upload_file` VALUES (80, NULL, 'D://xdmd_environment//Intranet//xdmd//专家组意见//成果附件3.docx', '成果附件3.docx', '2019-07-29成果附件3.docx', '专家组意见', 'docx', '0', '2019-07-29 15:34:07', 'admin');
INSERT INTO `upload_file` VALUES (81, NULL, 'D://xdmd_environment//Intranet//xdmd//专家组意见//成果附件3.docx', '成果附件3.docx', '2019-07-29成果附件3.docx', '专家组意见', 'docx', '0', '2019-07-29 15:44:44', 'admin');
INSERT INTO `upload_file` VALUES (82, NULL, 'D://xdmd_environment//Intranet//xdmd//专家组评议//法人身份证.zip', '法人身份证.zip', '2019-07-29法人身份证.zip', '专家组评议', 'zip', '0', '2019-07-29 15:44:59', 'admin');

SET FOREIGN_KEY_CHECKS = 1;
