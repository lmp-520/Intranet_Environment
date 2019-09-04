/*
 Navicat Premium Data Transfer

 Source Server         : Xdmd-Test
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : newenvironment

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 04/09/2019 16:38:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for acceptance_certificate
-- ----------------------------
DROP TABLE IF EXISTS `acceptance_certificate`;
CREATE TABLE `acceptance_certificate`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `cid` int(11) NULL DEFAULT NULL COMMENT '验收申请表的id',
  `translate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文号',
  `topic_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题编号',
  `topic_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题名称',
  `completion_unit` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '完成单位',
  `acceptance_department` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '验收组织部门',
  `acceptance_time` date NOT NULL COMMENT '验收日期',
  `unit_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位名称',
  `unit_nature` int(5) NOT NULL COMMENT '单位性质',
  `location` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所在地区',
  `legal_representative` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '法定代表人',
  `legal_representative_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '法定代表人电话',
  `contacts` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系人',
  `contacts_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系人电话',
  `postal_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮政编码',
  `mail` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电子邮箱',
  `mailing_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通信地址',
  `competent_department` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主管部门',
  `project_start_time` date NOT NULL COMMENT '课题起始时间',
  `project_completion_time` date NOT NULL COMMENT '课题完成时间',
  `achievement_form` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '成果形式',
  `achievement_level` int(5) NOT NULL COMMENT '成果水平',
  `development_total_number` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题研发人员总数',
  `doctor_total_number` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题研发博士人员数量',
  `master_total_number` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题研发硕士数量',
  `senior_total_number` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题研发高级职称数量',
  `intermediate_total_number` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题研发中级职称数量',
  `school_master_number` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题研发在校研究生数量',
  `total_project_funds` decimal(9, 2) NOT NULL COMMENT '课题实际到位经费项目总经费',
  `environment_topic_funds` decimal(9, 2) NOT NULL COMMENT '课题实际到位经费省环保科研课题经费',
  `competent_department_match` decimal(9, 2) NOT NULL COMMENT '课题实际到位经费主管部门配套',
  `bank_loans` decimal(9, 2) NOT NULL COMMENT '课题实际到位经费银行贷款',
  `unit_raise_money` decimal(9, 2) NOT NULL COMMENT '课题实际到位经费单位自筹',
  `other_actual_money` decimal(9, 2) NOT NULL COMMENT '课题实际到位经费其他',
  `equipment_cost` decimal(9, 2) NOT NULL COMMENT '课题经费支出设备费',
  `material_fee` decimal(9, 2) NOT NULL COMMENT '课题经费支出材料费',
  `laboratory_fees` decimal(9, 2) NOT NULL COMMENT '课题经费支出测试化验加工费',
  `fuel_costs` decimal(9, 2) NOT NULL COMMENT '课题经费支出燃料动力费',
  `travel_expenses` decimal(9, 2) NOT NULL COMMENT '课题经费支出差旅费',
  `conference_fee` decimal(9, 2) NOT NULL COMMENT '课题经费支出会议费',
  `international_communication` decimal(9, 2) NOT NULL COMMENT '课题经费支出国际合作交流会',
  `expert_consult` decimal(9, 2) NOT NULL COMMENT '课题经费支出专家咨询费',
  `management_expense` decimal(9, 2) NOT NULL COMMENT '课题经费支出管理及人员费',
  `other_expenditure_money` decimal(9, 2) NOT NULL COMMENT '课题经费支出其他费用',
  `new_output` decimal(9, 2) NOT NULL COMMENT '新增产值',
  `new_sales_volume` decimal(9, 2) NOT NULL COMMENT '新增销售额',
  `new_profit_tax` decimal(9, 2) NOT NULL COMMENT '新增利税',
  `exit_earn` decimal(9, 2) NOT NULL COMMENT '出口创汇',
  `main_solve_technology` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主要解决的关键技术与创新点',
  `main_completion` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主要技术',
  `implementation_achievement` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题实施的绩效',
  `science_department_opinion` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '完成单位科技部门意见',
  `check_department_opinion` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组织验收部门意见',
  `environment_office_opinion` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省生态环境厅意见',
  `acceptance_certificate_url` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '验收证书url',
  `total_expenditure` decimal(9, 2) NULL DEFAULT NULL COMMENT '课题经费支出合计',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for acceptance_certificate_patent
-- ----------------------------
DROP TABLE IF EXISTS `acceptance_certificate_patent`;
CREATE TABLE `acceptance_certificate_patent`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `acceptance_certificate_id` int(11) NOT NULL COMMENT '验收证书Id',
  `application_invention` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专利申请发明',
  `use_new_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专利申请使用新型',
  `patent_appearance` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专利申请外观设计',
  `patent_invention` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专利授权发明',
  `empower_new_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专利授权实用新型',
  `empower_appearance_design` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专利授权外观设计',
  `paper_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发表论文总数',
  `science_indexe` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发表论文科学英文索引',
  `engineer_index` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发表论文工程索引',
  `publish_work` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '出版科技著作',
  `technical_standard` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '制定技术标准',
  `new_product` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '新产品',
  `policy_system` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '制定政策制度',
  `new_device` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '建成新装置',
  `new_technology` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '新工艺',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for acceptance_certificate_principal_personnel
-- ----------------------------
DROP TABLE IF EXISTS `acceptance_certificate_principal_personnel`;
CREATE TABLE `acceptance_certificate_principal_personnel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `acceptance_certificate_id` int(11) NOT NULL COMMENT '验收证书id',
  `participant_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主要参与人员姓名',
  `participant_sex` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主要参与人员性别',
  `participant_birth_date` date NOT NULL COMMENT '主要参与人员出生年月',
  `participant_technical_title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主要参与人员技术职称',
  `participant_education` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主要参与人员学历',
  `participant_work_unit` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主要参与人员工作单位',
  `task_taking` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主要参与人员承担的主要研究任务',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for acceptance_certificate_subject_people
-- ----------------------------
DROP TABLE IF EXISTS `acceptance_certificate_subject_people`;
CREATE TABLE `acceptance_certificate_subject_people`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `acceptance_certificate_id` int(11) NOT NULL COMMENT '验收证书id',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题责任人姓名',
  `sex` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题责任人性别',
  `birth_date` date NOT NULL COMMENT '课题责任人出生年月',
  `major` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题责任人专业',
  `education` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题责任人学历',
  `title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题责任人职称',
  `phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题责任人联系方式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
-- Table structure for adjust_type
-- ----------------------------
DROP TABLE IF EXISTS `adjust_type`;
CREATE TABLE `adjust_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '调整类型主键id',
  `adjust_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '调整类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of adjust_type
-- ----------------------------
INSERT INTO `adjust_type` VALUES (1, '变更');
INSERT INTO `adjust_type` VALUES (2, '备案');

-- ----------------------------
-- Table structure for adjustment_matters
-- ----------------------------
DROP TABLE IF EXISTS `adjustment_matters`;
CREATE TABLE `adjustment_matters`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '调整事项主键id',
  `adjustment_matters` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '调整事项',
  `adjust_type_id` int(11) NOT NULL COMMENT '调整原因id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of adjustment_matters
-- ----------------------------
INSERT INTO `adjustment_matters` VALUES (1, '课题负责人', 1);
INSERT INTO `adjustment_matters` VALUES (2, '研究内容或示范点', 1);
INSERT INTO `adjustment_matters` VALUES (3, '课题延期', 1);
INSERT INTO `adjustment_matters` VALUES (4, '课题经费', 1);
INSERT INTO `adjustment_matters` VALUES (5, '其他[变更]', 1);
INSERT INTO `adjustment_matters` VALUES (6, '主要参加人员变动', 2);
INSERT INTO `adjustment_matters` VALUES (7, '课题经费调整', 2);
INSERT INTO `adjustment_matters` VALUES (8, '其他[备案]', 2);

-- ----------------------------
-- Table structure for administrator_information
-- ----------------------------
DROP TABLE IF EXISTS `administrator_information`;
CREATE TABLE `administrator_information`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `aid` int(11) NULL DEFAULT NULL COMMENT '用户信息id',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称',
  `company_id` int(10) NULL DEFAULT NULL COMMENT '公司id',
  `company_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司地址',
  `unit_nature` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位性质',
  `social_credit_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社会信用号',
  `legal_person` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人姓名',
  `contact_id_card` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人身份证号',
  `contact_phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人手机号',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `business_url_id` int(11) NULL DEFAULT NULL COMMENT '营业执照url的id',
  `legal_card_id_url_id` int(11) NULL DEFAULT NULL COMMENT '法人身份证url的id',
  `contact_card_url_id` int(11) NULL DEFAULT NULL COMMENT '联系人身份证url的id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of administrator_information
-- ----------------------------
INSERT INTO `administrator_information` VALUES (1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-22 00:00:00');
INSERT INTO `administrator_information` VALUES (2, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-22 00:00:00');
INSERT INTO `administrator_information` VALUES (4, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-22 00:00:00');
INSERT INTO `administrator_information` VALUES (5, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-22 00:00:00');
INSERT INTO `administrator_information` VALUES (6, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-22 00:00:00');
INSERT INTO `administrator_information` VALUES (7, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-22 00:00:00');
INSERT INTO `administrator_information` VALUES (8, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-22 00:00:00');
INSERT INTO `administrator_information` VALUES (9, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-22 16:32:44');
INSERT INTO `administrator_information` VALUES (10, NULL, NULL, 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-22 16:34:03');
INSERT INTO `administrator_information` VALUES (11, NULL, NULL, 12, '公司地址1', '1', '52123654AAAAAAAAAA', '法人1', '340303199702150432', '18855532979', '123456987@qq.com', 1, 1, 1, '2019-07-23 11:22:32');
INSERT INTO `administrator_information` VALUES (12, NULL, NULL, 13, '公司地址1', '1', '52123654AAAAAAAAAA', '法人1', '340303199702150432', '18855532979', '123456987@qq.com', 1, 1, 1, '2019-07-23 11:24:47');
INSERT INTO `administrator_information` VALUES (13, NULL, NULL, 14, '公司地址1', '1', '111111112222222222', '法人姓名1', '340333666575623145', '18855532979', '8433455@qq.com', 1, 1, 1, '2019-07-26 14:10:17');
INSERT INTO `administrator_information` VALUES (14, NULL, NULL, 15, '公司地址1', '1', '111111112222222222', '法人姓名1', '340333655555555555', '18855532565', '8433456@11.com', 1, 1, 1, '2019-07-26 16:06:19');
INSERT INTO `administrator_information` VALUES (15, NULL, NULL, 16, '公司地址呀', '2', '91370105MA3D5P8C9U', '法人姓名1', '340303199609280455', '18855532656', '85463221@qq.com', 1, 1, 1, '2019-08-05 14:43:25');
INSERT INTO `administrator_information` VALUES (16, NULL, NULL, 20, '公司地址呀6666', '2', '91370105MA3D5P8C9U', '法人姓名1', '340303199609280455', '18855532656', '85463221@qq.com', 88, 89, 90, '2019-08-05 16:21:50');
INSERT INTO `administrator_information` VALUES (17, 2, '公司名称1221', 25, '公司地址11', '2', '12100000425010757A', '法人姓名', '340303119609280415', '18855532979', '12345687@qq.com', 146, 147, 148, '2019-08-09 14:56:01');
INSERT INTO `administrator_information` VALUES (19, 18, '公司名称12212', 16, '公司地址11', '2', '12100000425010757A', '法人姓名', '340303119609280415', '18855532979', '12345687@qq.com', 97, 98, 99, '2019-08-13 15:05:17');
INSERT INTO `administrator_information` VALUES (20, 57, '4', 18, '5', '3', '91210200317986672Y', '8', '9', '10', '11', 169, 170, 171, '2019-08-20 18:49:58');
INSERT INTO `administrator_information` VALUES (21, 59, '12', 19, '3123', '4', '91210200317986672Y', '我', '333333333333333333', '13777777777', '123@qq.com', 173, 174, 175, '2019-08-23 09:33:04');

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
  `unit_nature` int(5) NOT NULL COMMENT '单位性质',
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
  `acceptance_phase_id` int(5) NOT NULL COMMENT '验收审核状态（1：等待员工提交 2：等待企业管理员提交 3：等待验收初审  4：通过初审，等待提交专家表 5：等待环保厅审核公司上传的专家文件  6.公司上传最终验收报告（验收证书） 7.审核最终验收报告 77：验收通过 88，验收结题 99.验收不通过）',
  `create_time` datetime(0) NOT NULL COMMENT '该表创建时间',
  `create_author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建该表的人',
  `achievement_url_id` int(5) NULL DEFAULT NULL COMMENT '成果附件上传文件的id',
  `submit_url_id` int(5) NULL DEFAULT NULL COMMENT '提交清单上传文件的id',
  `audit_report_url_id` int(5) NULL DEFAULT NULL COMMENT '审计报告上传文件的id',
  `first_inspection_report_url_id` int(5) NULL DEFAULT NULL COMMENT '初审报告上传文件的id',
  `expert_group_comments_url_id` int(5) NULL DEFAULT NULL COMMENT '专家组意见上传文件的id',
  `expert_acceptance_form_id` int(5) NULL DEFAULT NULL COMMENT '专家验收评议表上传文件的id',
  `application_url_id` int(5) NULL DEFAULT NULL COMMENT '验收申请表上传文件的id',
  `acceptance_conclusion_id` int(5) NULL DEFAULT NULL COMMENT '验收结论的id (在字典表中)',
  `acceptance_certificate_id` int(5) NULL DEFAULT NULL COMMENT '最终验收证书文件的id',
  `acceptance_final_result_id` int(5) NULL DEFAULT NULL COMMENT '最终验收结果id',
  `is_outcome` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否已经加入过成果库\r\n0：还没有加入到成果库 （默认为 0）\r\n1:  已经加入到成果库',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of check_apply
-- ----------------------------
INSERT INTO `check_apply` VALUES (84, 'vfbcfgsdeh', 'projectNo', 18, '4', 3, 'subjeceL', 'subjectLeaderPhone', 'eMail', 'commitmentUnitAddress', '2017-08-09', '2019-08-03', '2019-09-12', 6, '申请验收地点', '张三', '18855532979', '主要完成情况', '提交成果情况', '课题承担单位意见：	', '所在地环保部门意见：	', '省生态环境评估中心初审意见：	', '省环保厅主管部门意见：	', '[7,11,8]', 3, '2019-09-04 14:42:06', '张三', 297, 298, NULL, NULL, NULL, NULL, 296, NULL, NULL, NULL, '0');
INSERT INTO `check_apply` VALUES (85, 'bbbb', '33333', 18, '4', 3, 'dfsefsf', 'fdeesefss', 'sdfsefs', 'dfsers', '2017-08-09', '2019-08-21', '2019-09-02', 6, 'aa', 'aa', 'aaa', 'aa', 'aaa', 'aaa', 'aaa', 'aaa', 'aaa', '[8,12,9]', 88, '2019-09-04 15:01:20', '李四', 300, 301, 302, 303, 304, 305, 299, NULL, NULL, NULL, '0');

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
  `handle_content` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理内容',
  `second_handle_time` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 93 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of check_apply_state
-- ----------------------------
INSERT INTO `check_apply_state` VALUES (105, 84, '张三', '张三', '公司审批', '2019-09-04 14:42:06', '已处理', '审核通过', '2019-09-04 14:51:33');
INSERT INTO `check_apply_state` VALUES (106, 84, '张三', NULL, '等待验收初审', '2019-09-04 14:51:33', '等待处理', NULL, NULL);
INSERT INTO `check_apply_state` VALUES (107, 85, '李四', '张三', '公司审批', '2019-09-04 15:01:20', '已处理', '审核通过', '2019-09-04 15:01:56');
INSERT INTO `check_apply_state` VALUES (108, 85, '张三', '王五', '等待验收初审', '2019-09-04 15:01:56', '已处理', '审核通过', '2019-09-04 15:36:35');
INSERT INTO `check_apply_state` VALUES (109, 85, '王五', '李四', '通过初审，等待提交专家表', '2019-09-04 15:36:35', '已处理', '审核通过', '2019-09-04 15:40:50');
INSERT INTO `check_apply_state` VALUES (110, 85, '李四', '王五', '等待审核公司上传的专家文件', '2019-09-04 15:40:50', '已处理', '审核通过', '2019-09-04 15:50:50');

-- ----------------------------
-- Table structure for content_indicators
-- ----------------------------
DROP TABLE IF EXISTS `content_indicators`;
CREATE TABLE `content_indicators`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '合同管理的子表一',
  `contract_id` int(11) NOT NULL COMMENT '合同主表id',
  `time` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '日期',
  `program_content_assessment_indicators` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '计划内容及考核指标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of content_indicators
-- ----------------------------
INSERT INTO `content_indicators` VALUES (1, 1, '2018-8到2018-12', '让法国人tyre');
INSERT INTO `content_indicators` VALUES (2, 2, '2018-8到2019-1', '现代风格的人体感染');
INSERT INTO `content_indicators` VALUES (3, 3, 'dgvgf', 'dhtrhh');
INSERT INTO `content_indicators` VALUES (4, 4, 'ghjuyi', 'fgntjuyi');
INSERT INTO `content_indicators` VALUES (9, 1, 'dxfsefds', 'sdfsesetring');
INSERT INTO `content_indicators` VALUES (10, 1, 'sdfsestring', 'sdfsetring');
INSERT INTO `content_indicators` VALUES (11, 15, '654321', '123456');
INSERT INTO `content_indicators` VALUES (12, 15, '654321', '123456');
INSERT INTO `content_indicators` VALUES (13, 16, '654321', '123456');
INSERT INTO `content_indicators` VALUES (14, 16, '6543210', '1234567');
INSERT INTO `content_indicators` VALUES (15, 17, '654321', '123456');
INSERT INTO `content_indicators` VALUES (16, 17, '6543210', '1234567');
INSERT INTO `content_indicators` VALUES (17, 18, '654321', '123456');
INSERT INTO `content_indicators` VALUES (18, 18, '6543210', '1234567');
INSERT INTO `content_indicators` VALUES (19, 19, '654321', '123456');
INSERT INTO `content_indicators` VALUES (20, 19, '6543210', '1234567');
INSERT INTO `content_indicators` VALUES (21, 20, '654321', '123456');
INSERT INTO `content_indicators` VALUES (22, 20, '6543210', '1234567');
INSERT INTO `content_indicators` VALUES (23, 21, '654321', '123456');
INSERT INTO `content_indicators` VALUES (24, 21, '6543210', '1234567');
INSERT INTO `content_indicators` VALUES (25, 22, '654321', '123456');
INSERT INTO `content_indicators` VALUES (26, 22, '6543210', '1234567');
INSERT INTO `content_indicators` VALUES (27, 26, '2018-8到2018-12', '让法国人tyre');
INSERT INTO `content_indicators` VALUES (28, 26, '2018-8到2019-1', '现代风格的人体感染');
INSERT INTO `content_indicators` VALUES (29, 27, '2018-8到2018-12', '让法国人tyre');
INSERT INTO `content_indicators` VALUES (30, 27, 'time1', 'content');
INSERT INTO `content_indicators` VALUES (31, 27, 'time1', 'content');
INSERT INTO `content_indicators` VALUES (32, 27, 'time1', 'content');
INSERT INTO `content_indicators` VALUES (33, 27, 'time1', 'content');
INSERT INTO `content_indicators` VALUES (34, 28, '2018-8到2018-12', '让法国人tyre');
INSERT INTO `content_indicators` VALUES (35, 28, '2018-8到2019-1', '现代风格的人体感染');
INSERT INTO `content_indicators` VALUES (36, 29, '2018-8到2018-12', '让法国人tyre');
INSERT INTO `content_indicators` VALUES (37, 29, '2018-8到2019-1', '现代风格的人体感染');
INSERT INTO `content_indicators` VALUES (38, 30, '2018-8到2018-12', '让法国人tyre');
INSERT INTO `content_indicators` VALUES (39, 30, '2018-8到2019-1', '现代风格的人体感染');
INSERT INTO `content_indicators` VALUES (40, 31, '2018-8到2018-12', '让法国人tyre');
INSERT INTO `content_indicators` VALUES (41, 31, '2018-8到2019-1', '现代风格的人体感染');
INSERT INTO `content_indicators` VALUES (42, 32, '2018-8到2018-12', '让法国人tyre');
INSERT INTO `content_indicators` VALUES (43, 32, '2018-8到2019-1', '现代风格的人体感染');
INSERT INTO `content_indicators` VALUES (44, 33, '2018-8到2018-12', '让法国人tyre');
INSERT INTO `content_indicators` VALUES (45, 33, '2018-8到2019-1', '现代风格的人体感染');
INSERT INTO `content_indicators` VALUES (46, 34, '2018-8到2018-12', '让法国人tyre');
INSERT INTO `content_indicators` VALUES (47, 34, '2018-8到2019-1', '现代风格的人体感染');
INSERT INTO `content_indicators` VALUES (48, 35, '2018-8到2018-12', '让法国人tyre');
INSERT INTO `content_indicators` VALUES (49, 35, '2018-8到2019-1', '现代风格的人体感染');
INSERT INTO `content_indicators` VALUES (50, 36, '2018-8到2018-12', '让法国人tyre');
INSERT INTO `content_indicators` VALUES (51, 36, '2018-8到2019-1', '现代风格的人体感染');

-- ----------------------------
-- Table structure for contract_manage
-- ----------------------------
DROP TABLE IF EXISTS `contract_manage`;
CREATE TABLE `contract_manage`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `subject_category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题类别',
  `project_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题编号',
  `subject_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题名称',
  `contract_start_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '合同开始时间()',
  `contract_end_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '合同结束时间',
  `subjece_leader` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题负责人',
  `subject_leader_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题负责人电话及手机',
  `subject_contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题联系人',
  `subject_contact_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题联系人电话及手机',
  `commitment_Unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '承担单位',
  `commitment_unit_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '承担单位地址',
  `commitment_unit_zip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '承担单位邮编',
  `subject_supervisor_department` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题主管部门',
  `open_bank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '开户银行',
  `open_bank_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '开户银行账号',
  `email` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电子信箱(E-mail)',
  `guaranteed_units` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '保证单位',
  `guaranteed_unit_contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '保证单位联系人',
  `guaranteed_contact_phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '保证单位联系人电话\r\n',
  `commissioning_Unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '委托单位（甲方）',
  `legal_representative_entrusting_A` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '委托单位法定代表人',
  `commissioned_unit_address_A` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '委托单位地址',
  `commissioned_unit_zip_A` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '委托单位邮政编码',
  `responsibility_unit_B` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '承担单位（乙方）',
  `responsibility_legal_representative_B` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '承担单位法定代表人',
  `commit_unit_address_B` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '承担单位地址\r\n\r\n',
  `commit_unit_zip_B` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '承担单位邮政编码',
  `commit_unit_leader_B` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '承担单位课题负责人',
  `commitunit_leaders_phone_B` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '承担单位课题负责人电话（手机号）',
  `commitment_unit_email_B` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '承担单位课题负责人电子邮件',
  `guaranteed_unit_C` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '保证单位（丙方）',
  `guaranteed_unit_leader_C` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '保证单位法定代表人/部门负责人',
  `guaranteed_unit_address_C` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '保证单位地址',
  `guaranteed_unit_zip_C` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '保证单位邮编',
  `subject_signing_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题签订说明\r\n',
  `subject_objectives_research` varchar(111) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题的目标和主要研究内容',
  `subject_acceptance_assessment` varchar(111) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题验收内容和考核指标',
  `approval_status` int(11) NOT NULL DEFAULT 1 COMMENT '合同审批状态(0-单位员工待提交  1-评估中心员工待审批 2-法规科技处待审批   3-法规科技处已审批)',
  `mid_record_id` int(11) NULL DEFAULT 0 COMMENT '中期检查记录id（关联表）',
  `contract_annex_id` int(11) NULL DEFAULT NULL COMMENT '合同附件id',
  `mid_check_annex_id` int(11) NULL DEFAULT NULL COMMENT '中期检查表附件id',
  `expert_assessment_annex_id` int(11) NULL DEFAULT NULL COMMENT '专家评估表附件id',
  `subject_suggest_annex_id` int(11) NULL DEFAULT NULL COMMENT '课题意见附件id',
  `is_check_apply` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '此条数据是否已经加入验收申请（0：未加入，1：已加入）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of contract_manage
-- ----------------------------
INSERT INTO `contract_manage` VALUES (1, '76', 'nkhi', 'kong', '1905-07-11', '1906-07-11', 'dfss0', '12345678910', 'jbj7', '14725836910', 'vhg4', 'hgvh9', '200000', 'khnku1', 'bhcg3', '1236547899874560000', '132@qwe.com', 'bjjgfv', 'fvhyh', '15062114931', 'chdgtfh', 'buhgfyv', 'gyuf', '210000', 'gcfhfcty', 'fvfc', 'yhfy', '220000', 'vbhfvyh', '17896341796', '456@asd.com', 'hgfvy', 'uf', 'hfv', '230000', '________课题经  苏财建﹝ ﹞  号文批准列入江苏省___年环保科研计划，甲方核拨乙方课题经费____万元（大写：_____元）。依据《中华人民共和国合同法》的规定，为明确甲、乙、丙三方的权利和责任，保证课题的顺利实施和科研经费的合理使用，签订本合同', 'hgvyh', 'vyh', 0, 1, NULL, 1, 2, NULL, '0');
INSERT INTO `contract_manage` VALUES (2, '76', 'subjectNot', 'lmp', '1905-02-11', '1906-06-11', 'subjeceLeader', 'subjectLeaderPhone', 'subjectContact', '14725836910', 'subjectContactPhone', 'commitmentUnit', 'commitmentUnitAddress', 'commitmentUnitZip', 'subjectSupervisorDepartment', 'openBankAccount', 'eMail', 'guaranteedUnits', 'guaranteedUnitContact', 'guaranteedContactPhone', 'commissioningUnit', 'legalRepresentativeEntrustingA', 'commissionedUnitAddressA', 'commissionedUnitZipA', 'responsibilityUnitB', 'responsibilityLegalRepresentativeB', 'commitUnitAddressB', 'commitUnitZipB', 'commitUnitLeaderB', 'commitunitLeadersPhoneB', 'commitmentUnitEmailB', 'guaranteedUnitC', 'guaranteedUnitLeaderC', 'guaranteedUnitAddressC', 'guaranteedUnitZipC', '________课题经  苏财建﹝ ﹞  号文批准列入江苏省___年环保科研计划，甲方核拨乙方课题经费____万元（大写：_____元）。依据《中华人民共和国合同法》的规定，为明确甲、乙、丙三方的权利和责任，保证课题的顺利实施和科研经费的合理使用，签订本合同', 'subjectObjectivesResearch', 'subjectAcceptanceAssessment', 0, 2, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (3, '76', 'woxihuan', 'ln', '2019-02-11', '2019-06-11', 'subjfgfdtheceLeader', 'gfdth', 'subjectContact', '14725836910', 'subjectContactPhone', 'commitmentUnit', 'commitmentUnitAddress', 'commitmentUnitZip', 'subjectSupervisorDepartment', 'openBankAccount', 'eMail', 'guaranteedUnits', 'guaranteedUnitContact', 'guaranteedContactPhone', 'commissioningUnit', 'legalRepresentativeEntrustingA', 'commissionedUnitAddressA', 'commissionedUnitZipA', 'responsibilityUnitB', 'responsibilityLegalRepresentativeB', 'commitUnitAddressB', 'commitUnitZipB', 'commitUnitLeaderB', 'commitunitLeadersPhoneB', 'commitmentUnitEmailB', 'guaranteedUnitC', 'guaranteedUnitLeaderC', 'guaranteedUnitAddressC', 'guaranteedUnitZipC', '________课题经  苏财建﹝ ﹞  号文批准列入江苏省___年环保科研计划，甲方核拨乙方课题经费____万元（大写：_____元）。依据《中华人民共和国合同法》的规定，为明确甲、乙、丙三方的权利和责任，保证课题的顺利实施和科研经费的合理使用，签订本合同', 'subjectObjectivesResearch', 'subjectAcceptanceAssessment', 0, 1, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (4, '76', 'projectNo', 'rfgdgd', 'contractStartTime', 'contractEndTime', 'subjeceLeader', 'subjectLeaderPhone', 'subjectContact', 'subjectContactPhone', '4', 'commitmentUnitAddress', 'commitmentUnitZip', 'subjectSupervisorDepartment', 'openBank', 'openBankAccount', 'eMail', 'guaranteedUnits', 'guaranteedUnitContact', 'guaranteedContactPhone', 'commissioningUnit', 'legalRepresentativeEntrustingA', 'commissionedUnitAddressA', 'commissionedUnitZipA', 'responsibilityUnitB', 'responsibilityLegalRepresentativeB', 'commitUnitAddressB', 'commitUnitZipB', 'commitUnitLeaderB', 'commitunitLeadersPhoneB', 'commitmentUnitEmailB', 'guaranteedUnitC', 'guaranteedUnitLeaderC', 'guaranteedUnitAddressC', 'guaranteedUnitZipC', '________课题经  苏财建﹝ ﹞  号文批准列入江苏省___年环保科研计划，甲方核拨乙方课题经费____万元（大写：_____元）。依据《中华人民共和国合同法》的规定，为明确甲、乙、丙三方的权利和责任，保证课题的顺利实施和科研经费的合理使用，签订本合同', 'subjectObjectivesResearch', 'subjectAcceptanceAssessment', 0, 2, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (5, '76', 'projectNo', 'xfgdtg', 'contractStartTime', 'contractEndTime', 'subjeceLeader', 'subjectLeaderPhone', 'subjectContact', 'subjectContactPhone', '4', 'commitmentUnitAddress', 'commitmentUnitZip', 'subjectSupervisorDepartment', 'openBank', 'openBankAccount', 'eMail', 'guaranteedUnits', 'guaranteedUnitContact', 'guaranteedContactPhone', 'commissioningUnit', 'legalRepresentativeEntrustingA', 'commissionedUnitAddressA', 'commissionedUnitZipA', 'responsibilityUnitB', 'responsibilityLegalRepresentativeB', 'commitUnitAddressB', 'commitUnitZipB', 'commitUnitLeaderB', 'commitunitLeadersPhoneB', 'commitmentUnitEmailB', 'guaranteedUnitC', 'guaranteedUnitLeaderC', 'guaranteedUnitAddressC', 'guaranteedUnitZipC', '________课题经  苏财建﹝ ﹞  号文批准列入江苏省___年环保科研计划，甲方核拨乙方课题经费____万元（大写：_____元）。依据《中华人民共和国合同法》的规定，为明确甲、乙、丙三方的权利和责任，保证课题的顺利实施和科研经费的合理使用，签订本合同', 'subjectObjectivesResearch', 'subjectAcceptanceAssessment', 0, 1, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (6, '78', 'projectNo', 'vfbcfgsdeh', 'contractStartTime', 'contractEndTime', 'subjeceLeader', 'subjectLeaderPhone', 'subjectContact', 'subjectContactPhone', '4', 'commitmentUnitAddress', 'commitmentUnitZip', 'subjectSupervisorDepartment', 'openBank', 'openBankAccount', 'eMail', 'guaranteedUnits', 'guaranteedUnitContact', 'guaranteedContactPhone', 'commissioningUnit', 'legalRepresentativeEntrustingA', 'commissionedUnitAddressA', 'commissionedUnitZipA', 'responsibilityUnitB', 'responsibilityLegalRepresentativeB', 'commitUnitAddressB', 'commitUnitZipB', 'commitUnitLeaderB', 'commitunitLeadersPhoneB', 'commitmentUnitEmailB', 'guaranteedUnitC', 'guaranteedUnitLeaderC', 'guaranteedUnitAddressC', 'guaranteedUnitZipC', '________课题经  苏财建﹝ ﹞  号文批准列入江苏省___年环保科研计划，甲方核拨乙方课题经费____万元（大写：_____元）。依据《中华人民共和国合同法》的规定，为明确甲、乙、丙三方的权利和责任，保证课题的顺利实施和科研经费的合理使用，签订本合同', 'subjectObjectivesResearch', 'subjectAcceptanceAssessment', 0, 2, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (9, '76', 'sdfesfs', 'efsefsdfsfs', '2017-8-9', '2019-08-21', 'dfsefsfss', 'fdeesefss', 'sdfsesef', 'sedfsefss', '4', 'dfsers', 'dfsfsf', 'sefswfsefws', 'sdfsews', 'sxefewsef', 'sdfsefs', 'sedfsesf', 'sdfsef', 'sdfsese', 'xdfrgxc', 'sefsefs', 'dfsrew', 'qweasd', 'dsefsefss', 'dfsesfse', 'fvdrgtdv', '123weq', 'cxfger', 'sdfsef', 'dfsfesw', 'sdfsesf', 'sdfsese', 'sdfse', 'sdesfess', 'sefsfsefs3e', 'efsefsefssedsfe', 'sdfsesf', 0, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (10, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (11, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (12, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (13, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (14, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (15, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (16, '78', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (17, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (18, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (19, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (20, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (21, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (22, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (23, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (24, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (25, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (26, '78', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (27, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (28, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (29, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (30, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (31, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (32, '78', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (33, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (34, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (35, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (36, '77', '2', '3', '4', '2019-08-03', '6', '7', '8', '9', '4', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (37, '76', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '34', '33', '35', '36', '37', 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `contract_manage` VALUES (38, 'aaaaaa', 'A00001', '课题名称1', '2017-08-09', '2019-08-21', '课题负责人', '18855532979', 'sdfsesef', 'sedfsefss', '4', '承担单位地址', 'dfsfsf', 'sefswfsefws', 'sdfsews', 'sxefewsef', '843183340@qq.com', 'sedfsesf', 'sdfsef', 'sdfsese', '承担单位', 'sefsefs', 'dfsrew', 'qweasd', 'dsefsefss', 'dfsesfse', 'fvdrgtdv', '123weq', 'cxfger', 'sdfsef', 'dfsfesw', 'sdfsesf', 'sdfsese', 'sdfse', 'sdesfess', 'sefsfsefs3e', 'efsefsefssedsfe', 'sdfsesf', 0, 0, NULL, NULL, NULL, NULL, '0');

-- ----------------------------
-- Table structure for contract_midcheck_annexs
-- ----------------------------
DROP TABLE IF EXISTS `contract_midcheck_annexs`;
CREATE TABLE `contract_midcheck_annexs`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '中期检查附件主键',
  `mid_check_annex_id` int(11) NULL DEFAULT NULL COMMENT '中期检查表附件id',
  `expert_assessment_annex_id` int(11) NULL DEFAULT NULL COMMENT '专家评估表附件id',
  `open_report_annex_id` int(11) NULL DEFAULT NULL COMMENT '开题报告附件id',
  `subject_progress_annex_id` int(11) NULL DEFAULT NULL COMMENT '课题进展附件id',
  `fund_progress_annex_id` int(11) NULL DEFAULT NULL COMMENT '经费进展附件id',
  `contract_annex_id` int(11) NULL DEFAULT NULL COMMENT '合同附件id',
  `expert_suggest_annex_id` int(11) NULL DEFAULT NULL COMMENT '专家意见附件id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of contract_midcheck_annexs
-- ----------------------------
INSERT INTO `contract_midcheck_annexs` VALUES (1, 1, 2, 3, 4, 5, 6, 7);

-- ----------------------------
-- Table structure for contract_research_development_tasks
-- ----------------------------
DROP TABLE IF EXISTS `contract_research_development_tasks`;
CREATE TABLE `contract_research_development_tasks`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课题进展子表第一部分',
  `progress_id` int(11) NOT NULL COMMENT '课题进展id',
  `require_stodd_task_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '要求研发任务内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of contract_research_development_tasks
-- ----------------------------
INSERT INTO `contract_research_development_tasks` VALUES (1, 1, 'fdgsdrgsg242');
INSERT INTO `contract_research_development_tasks` VALUES (2, 2, 'fdgsdrgsg243');
INSERT INTO `contract_research_development_tasks` VALUES (3, 2, 'fdgsdrgsg244');
INSERT INTO `contract_research_development_tasks` VALUES (4, 2, '测试提交');
INSERT INTO `contract_research_development_tasks` VALUES (5, 3, 'st测试ring');

-- ----------------------------
-- Table structure for current_progress
-- ----------------------------
DROP TABLE IF EXISTS `current_progress`;
CREATE TABLE `current_progress`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课题进展子表第二部分',
  `progress_id` int(11) NOT NULL COMMENT '课题进展id',
  `current_progress_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '目前进展情况内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of current_progress
-- ----------------------------
INSERT INTO `current_progress` VALUES (1, 1, 'xdfsgr');
INSERT INTO `current_progress` VALUES (2, 1, 'zdgsdrg');
INSERT INTO `current_progress` VALUES (3, 2, 'dfdgr');
INSERT INTO `current_progress` VALUES (4, 2, 'fgdredtrge');
INSERT INTO `current_progress` VALUES (5, 2, 'cfgsdtrhwhr');

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
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `dictionary` VALUES (89, '专家省份', '省内专家', 21, 1, 1);
INSERT INTO `dictionary` VALUES (90, '专家省份', '省外专家', 21, 2, 1);

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
-- Table structure for expert_assessment
-- ----------------------------
DROP TABLE IF EXISTS `expert_assessment`;
CREATE TABLE `expert_assessment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课题编号',
  `subject_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课题名称',
  `subject_leader` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课题负责人',
  `commitment_unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '承担单位',
  `evaluation_content` int(11) NOT NULL COMMENT '汇报情况【53-内容清楚 54-内容基本清楚 55-内容不够清楚】\r\n',
  `progress_execution` int(11) NOT NULL COMMENT '进度执行情况【 56-超额完成 57-完成 58-基本完成 59-未完成】',
  `project_implementation_conditions` int(11) NOT NULL COMMENT '课题实施所需条件【60-落实 61-基本落实 62-未落实】',
  `technical_economic_assessment` int(11) NOT NULL COMMENT '技术、经济等考核指标 【63-达到合同规定【 64-基本达到合同 65-未达合同规定】',
  `funding_performance` int(11) NOT NULL COMMENT '经费执行情况【66-合理 67-基本合理 68-不合理】',
  `next_work_plan` int(11) NOT NULL COMMENT '下一步工作计划【69-可行 70-基本可行 71-不可行 】',
  `evaluation_time` int(11) NOT NULL COMMENT '评价等次【72-优秀 73-良好 74-一般 75-较差】',
  `major_technological_breakthroughs` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '取得重大技术突破以及成效等情况',
  `problem` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '存在问题',
  `suggestions` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '建议',
  `expert group_views` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '专家组综合意见',
  `expert_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '专家名称，用、分开',
  `fill_date` date NOT NULL COMMENT '填写日期',
  `expert_assessment_annex_id` int(11) NOT NULL COMMENT '专家评估附件',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of expert_assessment
-- ----------------------------
INSERT INTO `expert_assessment` VALUES (1, 'fsfes', 'sczszscz', 'zzczcs', 'xczdcz', 53, 56, 60, 63, 66, 69, 72, 'bcgfbfgb', 'cvbcggb', 'cvbcbcc', 'xcvdfd', 'cvxv,xcx', '2019-08-09', 200);
INSERT INTO `expert_assessment` VALUES (2, '萨芬色呃', '都说了', '自动分', '但是公司的v', 53, 56, 60, 65, 66, 69, 72, ' xfasddzvzdvd', 'xvdzfdzf', '的VS达人葛尔', '堵塞旅馆', 'asd,weq', '2018-09-07', 24);

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
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`egc_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expert_group_comments
-- ----------------------------
INSERT INTO `expert_group_comments` VALUES (11, 85, 'bbbb', '33333', 'dfsefsf', '4', 7, 2.00, 3.00, 2.00, 2.00, 2.00, 2.00, 2.00, 2.1, 'aaa', 'aaa', 87, 'aa', '2019-09-16', '2019-09-04 15:40:50', '李四');

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
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expert_group_comments_name
-- ----------------------------
INSERT INTO `expert_group_comments_name` VALUES (26, 11, 'aa', 'aa', 'aa', 'aa');

-- ----------------------------
-- Table structure for expert_information
-- ----------------------------
DROP TABLE IF EXISTS `expert_information`;
CREATE TABLE `expert_information`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `aid` int(11) NULL DEFAULT NULL COMMENT '用户信息的id',
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
  `professional_field` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专业领域',
  `curriculum_vitae` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '个人简历',
  `recommendation_unit_opinion` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '推荐单位意见',
  `create_author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建此条信息的人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建此条信息的时间',
  `is_province` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否省内  存放字典表中的id',
  `reason` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核未通过的原因',
  `expert_information_url_id` int(10) NULL DEFAULT NULL COMMENT '专家信息的文件id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expert_information
-- ----------------------------
INSERT INTO `expert_information` VALUES (1, NULL, '男', '1996-10-23', '本科', '主任', '技术部主任', '环境治理', '环境治理专业', '南京环境治理公司', '南京市江宁区', '233000', '5236478', '18532158651', '18535154865@qq.com', '31', '33,35,', '18918156158gergegegregergergergergergergergregeuigueirhgeuihguei', NULL, NULL, NULL, NULL, NULL, 114);
INSERT INTO `expert_information` VALUES (2, NULL, '男', '1996-10-23', '本科', '主任', '技术部主任', '环境治理', '环境治理专业', '南京环境治理公司', '南京市江宁区', '233000', '5236478', '18532158651', '18535154865@qq.com', '32', '36,', '18918156158gergegegregergergergergergergergregeuigueirhgeuihguei', NULL, NULL, NULL, NULL, NULL, 114);
INSERT INTO `expert_information` VALUES (3, NULL, '男', '1996-10-23', '本科', '主任', '技术部主任', '环境治理', '环境治理专业', '南京环境治理公司', '南京市江宁区', '233000', '5236478', '18532158651', '18535154865@qq.com', '31', '33,34,35,', '18918156158gergegegregergergergergergergergregeuigueirhgeuihguei', NULL, NULL, NULL, NULL, NULL, 114);
INSERT INTO `expert_information` VALUES (4, NULL, '男', '0201-02-11', '本科', '现任职务1', '技术职称1', '所学专业1', '从事专业1', '工作单位1', '通讯地址1', '邮政编码1', '123456', '123456789', '123456@qq.com', '31', '33,34,36,', '个人简历1', NULL, 'admin', '2019-07-31 09:58:09', NULL, NULL, 114);
INSERT INTO `expert_information` VALUES (7, NULL, '男', '0201-02-11', '本科', '现任职务1', '技术职称1', '所学专业1', '从事专业1', '工作单位1', '通讯地址1', '邮政编码1', '123456', '123456789', '123456@qq.com', '32', '33,35,36,', '个人简历1', NULL, 'admin', '2019-07-31 10:07:59', NULL, NULL, 114);
INSERT INTO `expert_information` VALUES (8, NULL, '男', '0201-02-11', '本科', '现任职务1', '技术职称1', '所学专业1', '从事专业1', '工作单位1', '通讯地址1', '邮政编码1', '123456', '123456789', '123456@qq.com', '31', '33,', '个人简历1', '推荐单位意见', 'admin', '2019-07-31 10:34:36', '89', '就是不给你通过', 114);
INSERT INTO `expert_information` VALUES (9, NULL, '男', '2000-01-02', '本科', '职务111', '技术职称111', '所学专业333', '从事专业222', '工作单位3232', '通讯地址33232', '233000', '18855532979', '18855532646', '54652555@qq.com', '31', '33,34,36,', '个人简历2121', '推荐单位意见32', NULL, '2019-08-09 18:17:03', '1', '不通过5566666', 116);
INSERT INTO `expert_information` VALUES (10, 7, '男', '2000-01-02', '本科', '职务111', '技术职称111', '所学专业333', '从事专业222', '工作单位3232', '通讯地址33232', '233000', '18855532979', '18855532646', '54652555@qq.com', '32', '36,', '个人简历2121', '推荐单位意见32', NULL, '2019-08-09 18:18:19', '1', NULL, 114);
INSERT INTO `expert_information` VALUES (12, 9, '男', '2000-01-02', '本科', '职务111', '技术职称111', '所学专业333', '从事专业222', '工作单位3232', '通讯地址33232', '233000', '18855532979', '18855532646', '54652555@qq.com', '32', '35,', '个人简历2121', '推荐单位意见32', NULL, '2019-08-09 18:24:55', '1', NULL, 114);
INSERT INTO `expert_information` VALUES (13, 11, '男', '2000-01-02', '本科', '职务111', '技术职称111', '所学专业333', '从事专业222', '工作单位3232', '通讯地址33232', '233000', '18855532979', '18855532646', '54652555@qq.com', '30', '34,', '个人简历2121', '推荐单位意见32', NULL, '2019-08-09 18:31:26', '1', NULL, 114);
INSERT INTO `expert_information` VALUES (16, NULL, '男', '2000-01-02', '本科', '职务111', '技术职称111', '所学专业333', '从事专业222', '工作单位3232', '通讯地址33232', '233000', '18855532979', '18855532646', '54652555@qq.com', '30', '33,34,', '个人简历2121', '推荐单位意见32', NULL, '2019-08-13 16:02:28', '1', NULL, 114);
INSERT INTO `expert_information` VALUES (17, NULL, '男', '2000-01-02', '本科', '职务111', '技术职称111', '所学专业333', '从事专业222', '工作单位3232', '通讯地址33232', '233000', '18855532979', '18855532646', '54652555@qq.com', '31', '36,', '个人简历2121', '推荐单位意见32', NULL, '2019-08-13 16:10:52', '1', NULL, 114);
INSERT INTO `expert_information` VALUES (18, NULL, '男', '2000-01-02', '本科', '职务111', '技术职称111', '所学专业333', '从事专业222', '工作单位3232', '通讯地址33232', '233000', '18855532979', '18855532646', '54652555@qq.com', '32', '35,34,36,', '个人简历2121', '推荐单位意见32', NULL, '2019-08-13 16:13:13', '1', NULL, 114);
INSERT INTO `expert_information` VALUES (19, 28, '男', '2000-01-02', '本科', '职务111', '技术职称111', '所学专业333', '从事专业222', '工作单位3232', '通讯地址33232', '233000', '18855532979', '18855532646', '54652555@qq.com', '31', '33,35,36,', '个人简历2121', '推荐单位意见32', NULL, '2019-08-13 16:17:25', '1', NULL, 114);
INSERT INTO `expert_information` VALUES (20, 29, '男', '2000-01-02', '本科', '职务111', '技术职称111', '所学专业333', '从事专业222', '工作单位3232', '通讯地址33232', '233000', '18855532979', '18855532646', '54652555@qq.com', '31', '34,36,', '个人简历2121', '推荐单位意见32', NULL, '2019-08-13 16:21:08', '1', 'ahahahahhaha', 114);
INSERT INTO `expert_information` VALUES (21, 31, '男', '2000-01-02', '本科', '职务111', '技术职称111', '所学专业333', '从事专业222', '工作单位3232', '通讯地址33232', '233000', '18855532979', '18855532646', '54652555@qq.com', '32', '33,34,', '个人简历2121', '推荐单位意见32', NULL, '2019-08-13 16:48:37', '1', NULL, 116);
INSERT INTO `expert_information` VALUES (22, 6, '男', '2000-01-02', '本科', '职务111', '技术职称111', '所学专业333', '从事专业222', '工作单位3232', '通讯地址33232', '233000', '18855532979', '18855532646', '54652555@qq.com', '32', '33,34,36,', '个人简历2121', '推荐单位意见32', NULL, '2019-08-13 16:52:27', '1', '啦啦啦啦啦', 117);
INSERT INTO `expert_information` VALUES (23, 34, '男', '2000-01-02', '本科', '66职务111', '66技术职称111', '66所学专业333', '66从事专业222', '66工作单位3232', '66通讯地址33232', '66233000', '6618855532979', '618855532646', '564652555@qq.com', '32', '33,34,36,', '个人简历2121', '推荐单位意见32', NULL, '2019-08-13 21:46:02', '1', NULL, 120);
INSERT INTO `expert_information` VALUES (34, 46, '女', '2019-08-14', '本科', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '31', '33,34,', '1', '1', 'admin', '2019-08-20 17:55:14', '省内', NULL, 156);
INSERT INTO `expert_information` VALUES (36, 48, '女', '2019-08-22', '本科', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '32', '33,34,35,', '1', '1', 'admin', '2019-08-20 18:17:57', '省内', NULL, 158);
INSERT INTO `expert_information` VALUES (38, 50, '女', '2019-08-21', '本科', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '32', '33,34,36,', '1', '1', 'admin', '2019-08-20 18:29:59', '省内', NULL, 160);
INSERT INTO `expert_information` VALUES (39, 51, '女', '2019-08-21', '本科', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '32', '33,36,35,', '1', '1', 'admin', '2019-08-20 18:30:30', '省内', NULL, 161);
INSERT INTO `expert_information` VALUES (40, 52, '女', '2019-08-22', '本科', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '32', '33,', '5', '1', 'admin', '2019-08-20 18:35:00', '省内', NULL, 162);
INSERT INTO `expert_information` VALUES (41, 53, '女', '2019-08-06', '本科', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '32', '33,35,36,', '5', '1', 'admin', '2019-08-20 18:35:52', '省内', NULL, 163);
INSERT INTO `expert_information` VALUES (42, 54, '女', '2019-08-14', '本科', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '32', '34,35,36,', '1', '1', 'admin', '2019-08-20 18:37:43', '省外', NULL, 164);
INSERT INTO `expert_information` VALUES (43, 55, '女', '2019-08-14', '本科', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '31', '33,34,35,', '1', '1', 'admin', '2019-08-20 18:38:10', '省外', NULL, 165);
INSERT INTO `expert_information` VALUES (44, 58, '男', '2019-08-14', '本科', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '31', '33,34,35,', '1', '1', 'admin', '2019-08-22 09:58:20', '89', NULL, 172);

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
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expert_information_article
-- ----------------------------
INSERT INTO `expert_information_article` VALUES (1, 1, '第一文章题目', '第一作者排序', '第一文章期刊名', '1999-07-12');
INSERT INTO `expert_information_article` VALUES (2, 2, '第二文章题目', '第二作者排序', '第二文章期刊名', '1999-07-12');
INSERT INTO `expert_information_article` VALUES (3, 1, '第三文章题目', '第三作者排序', '第三文章期刊名', '1999-07-12');
INSERT INTO `expert_information_article` VALUES (6, 16, '文章题目11', '作者排序', '文章期刊名11', '2111-01-02');
INSERT INTO `expert_information_article` VALUES (7, 16, '文章题目2211', '作者排序222', '文章期刊名2211', '2112-01-02');
INSERT INTO `expert_information_article` VALUES (8, 17, '文章题目11', '作者排序', '文章期刊名11', '2111-01-02');
INSERT INTO `expert_information_article` VALUES (9, 17, '文章题目2211', '作者排序222', '文章期刊名2211', '2112-01-02');
INSERT INTO `expert_information_article` VALUES (10, 18, '文章题目11', '作者排序', '文章期刊名11', '2111-01-02');
INSERT INTO `expert_information_article` VALUES (11, 18, '文章题目2211', '作者排序222', '文章期刊名2211', '2112-01-02');
INSERT INTO `expert_information_article` VALUES (12, 19, '文章题目11', '作者排序', '文章期刊名11', '2111-01-02');
INSERT INTO `expert_information_article` VALUES (13, 19, '文章题目2211', '作者排序222', '文章期刊名2211', '2112-01-02');
INSERT INTO `expert_information_article` VALUES (14, 20, '文章题目11', '作者排序', '文章期刊名11', '2111-01-02');
INSERT INTO `expert_information_article` VALUES (15, 20, '文章题目2211', '作者排序222', '文章期刊名2211', '2112-01-02');
INSERT INTO `expert_information_article` VALUES (16, 21, '文章题目11', '作者排序', '文章期刊名11', '2111-01-02');
INSERT INTO `expert_information_article` VALUES (17, 21, '文章题目2211', '作者排序222', '文章期刊名2211', '2112-01-02');
INSERT INTO `expert_information_article` VALUES (18, 22, '文章题目11', '作者排序', '文章期刊名11', '2111-01-02');
INSERT INTO `expert_information_article` VALUES (19, 22, '文章题目2211', '作者排序222', '文章期刊名2211', '2112-01-02');
INSERT INTO `expert_information_article` VALUES (26, 23, '66文章题目11', '66作者排序', '66文章期刊名11', '2111-01-02');
INSERT INTO `expert_information_article` VALUES (27, 23, '666文章题目2211', '666作者排序222', '666文章期刊名2211', '2112-01-02');
INSERT INTO `expert_information_article` VALUES (36, 34, '1', '1', '1', '2019-08-14');
INSERT INTO `expert_information_article` VALUES (37, 34, '1', '1', '1', '2019-08-07');
INSERT INTO `expert_information_article` VALUES (38, 34, '1', '1', '1', '2019-08-21');
INSERT INTO `expert_information_article` VALUES (40, 36, '1', '1', '1', '2019-08-09');
INSERT INTO `expert_information_article` VALUES (41, 36, '1', '1', '1', '2019-08-14');
INSERT INTO `expert_information_article` VALUES (44, 38, '1', '1', '1', '2019-08-09');
INSERT INTO `expert_information_article` VALUES (45, 38, '1', '1', '1', '2019-08-07');
INSERT INTO `expert_information_article` VALUES (46, 39, '1', '1', '1', '2019-08-09');
INSERT INTO `expert_information_article` VALUES (47, 39, '1', '1', '1', '2019-08-07');
INSERT INTO `expert_information_article` VALUES (48, 40, '1', '1', '1', '2019-08-09');
INSERT INTO `expert_information_article` VALUES (49, 40, '1', '1', '1', '2019-08-07');
INSERT INTO `expert_information_article` VALUES (50, 41, '1', '1', '1', '2019-08-09');
INSERT INTO `expert_information_article` VALUES (51, 41, '1', '1', '1', '2019-08-07');
INSERT INTO `expert_information_article` VALUES (52, 42, '1', '1', '1', '2019-08-09');
INSERT INTO `expert_information_article` VALUES (53, 42, '1', '1', '1', '2019-08-07');
INSERT INTO `expert_information_article` VALUES (54, 43, '1', '1', '1', '2019-08-09');
INSERT INTO `expert_information_article` VALUES (55, 43, '1', '1', '1', '2019-08-07');
INSERT INTO `expert_information_article` VALUES (56, 44, '1', '1', '1', '2019-08-09');
INSERT INTO `expert_information_article` VALUES (57, 44, '1', '1', '1', '2019-08-07');

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
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expert_information_book
-- ----------------------------
INSERT INTO `expert_information_book` VALUES (1, 1, '第一著作名称', '第一排序', '第一著作出版社', '1856-02-23');
INSERT INTO `expert_information_book` VALUES (2, 1, '第二著作名称', '第二排序', '第二著作出版社', '1856-02-23');
INSERT INTO `expert_information_book` VALUES (3, 2, '第三著作名称', '第三排序', '第三著作出版社', '1856-02-23');
INSERT INTO `expert_information_book` VALUES (4, 16, '著作名称12', '排序89', '著作出版社777', '2222-1-5');
INSERT INTO `expert_information_book` VALUES (5, 16, '著作名称212', '排序289', '著作出版社2777', '2222-1-5');
INSERT INTO `expert_information_book` VALUES (6, 17, '著作名称12', '排序89', '著作出版社777', '2222-1-5');
INSERT INTO `expert_information_book` VALUES (7, 17, '著作名称212', '排序289', '著作出版社2777', '2222-1-5');
INSERT INTO `expert_information_book` VALUES (8, 18, '著作名称12', '排序89', '著作出版社777', '2222-1-5');
INSERT INTO `expert_information_book` VALUES (9, 18, '著作名称212', '排序289', '著作出版社2777', '2222-1-5');
INSERT INTO `expert_information_book` VALUES (10, 19, '著作名称12', '排序89', '著作出版社777', '2222-1-5');
INSERT INTO `expert_information_book` VALUES (11, 19, '著作名称212', '排序289', '著作出版社2777', '2222-1-5');
INSERT INTO `expert_information_book` VALUES (12, 20, '著作名称12', '排序89', '著作出版社777', '2222-1-5');
INSERT INTO `expert_information_book` VALUES (13, 20, '著作名称212', '排序289', '著作出版社2777', '2222-1-5');
INSERT INTO `expert_information_book` VALUES (14, 21, '著作名称12', '排序89', '著作出版社777', '2222-1-5');
INSERT INTO `expert_information_book` VALUES (15, 21, '著作名称212', '排序289', '著作出版社2777', '2222-1-5');
INSERT INTO `expert_information_book` VALUES (16, 22, '著作名称12', '排序89', '著作出版社777', '2222-1-5');
INSERT INTO `expert_information_book` VALUES (17, 22, '著作名称212', '排序289', '著作出版社2777', '2222-1-5');
INSERT INTO `expert_information_book` VALUES (24, 23, '666著作名称12', '666排序89', '666著作出版社777', '2222-1-5');
INSERT INTO `expert_information_book` VALUES (25, 23, '66著作名称212', '666排序289', '666著作出版社2777', '2222-1-5');
INSERT INTO `expert_information_book` VALUES (29, 34, '1', '1', '1', '2019-08-14');
INSERT INTO `expert_information_book` VALUES (31, 38, '2', '2', '2', '2019-08-21');
INSERT INTO `expert_information_book` VALUES (32, 39, '2', '2', '2', '2019-08-21');
INSERT INTO `expert_information_book` VALUES (33, 40, '5', '5', '5', '2019-08-22');
INSERT INTO `expert_information_book` VALUES (34, 41, '5', '5', '5', '2019-08-22');
INSERT INTO `expert_information_book` VALUES (35, 42, '1', '1', '1', '2019-08-21');
INSERT INTO `expert_information_book` VALUES (36, 43, '1', '', '1', '2019-08-21');

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
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expert_information_patent
-- ----------------------------
INSERT INTO `expert_information_patent` VALUES (1, 1, '第一专利名称', '第一专利类型', '第一专利号', '1523-11-23');
INSERT INTO `expert_information_patent` VALUES (2, 1, '第二专利名称', '第二专利类型', '第二专利号', '1523-11-23');
INSERT INTO `expert_information_patent` VALUES (3, 2, '第三专利名称', '第三专利类型', '第三专利号', '1523-11-23');
INSERT INTO `expert_information_patent` VALUES (4, 16, '专利名称21', '专利类型231', '专利号21', '2015-05-04');
INSERT INTO `expert_information_patent` VALUES (5, 16, '专利名称21we', '专利类型2ew31', '专利号2q1', '2015-05-04');
INSERT INTO `expert_information_patent` VALUES (6, 17, '专利名称21', '专利类型231', '专利号21', '2015-05-04');
INSERT INTO `expert_information_patent` VALUES (7, 17, '专利名称21we', '专利类型2ew31', '专利号2q1', '2015-05-04');
INSERT INTO `expert_information_patent` VALUES (8, 18, '专利名称21', '专利类型231', '专利号21', '2015-05-04');
INSERT INTO `expert_information_patent` VALUES (9, 18, '专利名称21we', '专利类型2ew31', '专利号2q1', '2015-05-04');
INSERT INTO `expert_information_patent` VALUES (10, 19, '专利名称21', '专利类型231', '专利号21', '2015-05-04');
INSERT INTO `expert_information_patent` VALUES (11, 19, '专利名称21we', '专利类型2ew31', '专利号2q1', '2015-05-04');
INSERT INTO `expert_information_patent` VALUES (12, 20, '专利名称21', '专利类型231', '专利号21', '2015-05-04');
INSERT INTO `expert_information_patent` VALUES (13, 20, '专利名称21we', '专利类型2ew31', '专利号2q1', '2015-05-04');
INSERT INTO `expert_information_patent` VALUES (14, 21, '专利名称21', '专利类型231', '专利号21', '2015-05-04');
INSERT INTO `expert_information_patent` VALUES (15, 21, '专利名称21we', '专利类型2ew31', '专利号2q1', '2015-05-04');
INSERT INTO `expert_information_patent` VALUES (16, 22, '专利名称21', '专利类型231', '专利号21', '2015-05-04');
INSERT INTO `expert_information_patent` VALUES (17, 22, '专利名称21we', '专利类型2ew31', '专利号2q1', '2015-05-04');
INSERT INTO `expert_information_patent` VALUES (24, 23, '666专利名称21', '666专利类型231', '6666专利号21', '2015-05-04');
INSERT INTO `expert_information_patent` VALUES (25, 23, '666专利名称21we', '66专利类型2ew31', '666专利号2q1', '2015-05-04');
INSERT INTO `expert_information_patent` VALUES (27, 34, '1', '1', '1', '2019-08-28');
INSERT INTO `expert_information_patent` VALUES (28, 39, '', '', '', '2019-08-27');
INSERT INTO `expert_information_patent` VALUES (29, 40, '6', '6', '6', '2019-08-21');
INSERT INTO `expert_information_patent` VALUES (30, 41, '6', '6', '6', '2019-08-21');
INSERT INTO `expert_information_patent` VALUES (31, 42, '1', '1', '1', '2019-08-28');
INSERT INTO `expert_information_patent` VALUES (32, 43, '1', '1', '1', '2019-08-28');

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
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expert_information_prize_winning
-- ----------------------------
INSERT INTO `expert_information_prize_winning` VALUES (1, 1, '第一获奖名称', '第一获奖排序', '第一颁奖部门', '1544-06-14');
INSERT INTO `expert_information_prize_winning` VALUES (2, 1, '第二获奖名称', '第二获奖排序', '第二颁奖部门', '1544-06-14');
INSERT INTO `expert_information_prize_winning` VALUES (3, 2, '第三获奖名称', '第三获奖排序', '第三颁奖部门', '1544-06-14');
INSERT INTO `expert_information_prize_winning` VALUES (4, 16, '获奖名称21', '获奖排序211', '获奖部门2112', '1111-02-04');
INSERT INTO `expert_information_prize_winning` VALUES (5, 16, '获奖名称21', '获奖排序211', '获奖部门2112', '1111-02-04');
INSERT INTO `expert_information_prize_winning` VALUES (6, 17, '获奖名称21', '获奖排序211', '获奖部门2112', '1111-02-04');
INSERT INTO `expert_information_prize_winning` VALUES (7, 17, '获奖名称21', '获奖排序211', '获奖部门2112', '1111-02-04');
INSERT INTO `expert_information_prize_winning` VALUES (8, 18, '获奖名称21', '获奖排序211', '获奖部门2112', '1111-02-04');
INSERT INTO `expert_information_prize_winning` VALUES (9, 18, '获奖名称21', '获奖排序211', '获奖部门2112', '1111-02-04');
INSERT INTO `expert_information_prize_winning` VALUES (10, 19, '获奖名称21', '获奖排序211', '获奖部门2112', '1111-02-04');
INSERT INTO `expert_information_prize_winning` VALUES (11, 19, '获奖名称21', '获奖排序211', '获奖部门2112', '1111-02-04');
INSERT INTO `expert_information_prize_winning` VALUES (12, 20, '获奖名称21', '获奖排序211', '获奖部门2112', '1111-02-04');
INSERT INTO `expert_information_prize_winning` VALUES (13, 20, '获奖名称21', '获奖排序211', '获奖部门2112', '1111-02-04');
INSERT INTO `expert_information_prize_winning` VALUES (14, 21, '获奖名称21', '获奖排序211', '获奖部门2112', '1111-02-04');
INSERT INTO `expert_information_prize_winning` VALUES (15, 21, '获奖名称21', '获奖排序211', '获奖部门2112', '1111-02-04');
INSERT INTO `expert_information_prize_winning` VALUES (16, 22, '获奖名称21', '获奖排序211', '获奖部门2112', '1111-02-04');
INSERT INTO `expert_information_prize_winning` VALUES (17, 22, '获奖名称21', '获奖排序211', '获奖部门2112', '1111-02-04');
INSERT INTO `expert_information_prize_winning` VALUES (24, 23, '6666获奖名称21', '666获奖排序211', '666获奖部门2112', '1111-02-04');
INSERT INTO `expert_information_prize_winning` VALUES (25, 23, '666获奖名称21', '66获奖排序211', '666获奖部门2112', '1111-02-04');
INSERT INTO `expert_information_prize_winning` VALUES (26, 34, '1', '1', '1', '2019-08-21');
INSERT INTO `expert_information_prize_winning` VALUES (27, 40, '7', '7', '7', '2019-08-28');
INSERT INTO `expert_information_prize_winning` VALUES (28, 41, '7', '7', '7', '2019-08-28');
INSERT INTO `expert_information_prize_winning` VALUES (29, 42, '1', '1', '1', '2019-08-07');
INSERT INTO `expert_information_prize_winning` VALUES (30, 43, '1', '1', '1', '2019-08-07');

-- ----------------------------
-- Table structure for expert_information_research_direction
-- ----------------------------
DROP TABLE IF EXISTS `expert_information_research_direction`;
CREATE TABLE `expert_information_research_direction`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `expert_id` int(11) NULL DEFAULT NULL COMMENT '专家信息表id',
  `main_research_directions` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主要研究方向',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expert_information_research_direction
-- ----------------------------
INSERT INTO `expert_information_research_direction` VALUES (1, 1, '第一研究方向');
INSERT INTO `expert_information_research_direction` VALUES (2, 2, '第二研究方向');
INSERT INTO `expert_information_research_direction` VALUES (3, 16, '主要研究方向11');
INSERT INTO `expert_information_research_direction` VALUES (4, 16, '主要研究方向222');
INSERT INTO `expert_information_research_direction` VALUES (5, 17, '主要研究方向11');
INSERT INTO `expert_information_research_direction` VALUES (6, 17, '主要研究方向222');
INSERT INTO `expert_information_research_direction` VALUES (7, 18, '主要研究方向11');
INSERT INTO `expert_information_research_direction` VALUES (8, 18, '主要研究方向222');
INSERT INTO `expert_information_research_direction` VALUES (9, 19, '主要研究方向11');
INSERT INTO `expert_information_research_direction` VALUES (10, 19, '主要研究方向222');
INSERT INTO `expert_information_research_direction` VALUES (11, 20, '主要研究方向11');
INSERT INTO `expert_information_research_direction` VALUES (12, 20, '主要研究方向222');
INSERT INTO `expert_information_research_direction` VALUES (13, 21, '主要研究方向11');
INSERT INTO `expert_information_research_direction` VALUES (14, 21, '主要研究方向222');
INSERT INTO `expert_information_research_direction` VALUES (15, 22, '主要研究方向11');
INSERT INTO `expert_information_research_direction` VALUES (16, 22, '主要研究方向222');
INSERT INTO `expert_information_research_direction` VALUES (23, 23, '666主要研究方向11');
INSERT INTO `expert_information_research_direction` VALUES (24, 23, '6666主要研究方向222');
INSERT INTO `expert_information_research_direction` VALUES (25, 34, '1');
INSERT INTO `expert_information_research_direction` VALUES (26, 34, '1');
INSERT INTO `expert_information_research_direction` VALUES (27, 34, '1');
INSERT INTO `expert_information_research_direction` VALUES (28, 36, '1');
INSERT INTO `expert_information_research_direction` VALUES (29, 36, '1');
INSERT INTO `expert_information_research_direction` VALUES (30, 36, '1');
INSERT INTO `expert_information_research_direction` VALUES (31, 38, '1');
INSERT INTO `expert_information_research_direction` VALUES (32, 38, '1');
INSERT INTO `expert_information_research_direction` VALUES (33, 38, '1');
INSERT INTO `expert_information_research_direction` VALUES (34, 39, '1');
INSERT INTO `expert_information_research_direction` VALUES (35, 39, '1');
INSERT INTO `expert_information_research_direction` VALUES (36, 39, '1');
INSERT INTO `expert_information_research_direction` VALUES (37, 40, '5');
INSERT INTO `expert_information_research_direction` VALUES (38, 40, '5');
INSERT INTO `expert_information_research_direction` VALUES (39, 40, '5');
INSERT INTO `expert_information_research_direction` VALUES (40, 41, '5');
INSERT INTO `expert_information_research_direction` VALUES (41, 41, '5');
INSERT INTO `expert_information_research_direction` VALUES (42, 41, '5');
INSERT INTO `expert_information_research_direction` VALUES (43, 42, '1');
INSERT INTO `expert_information_research_direction` VALUES (44, 42, '1');
INSERT INTO `expert_information_research_direction` VALUES (45, 42, '1');
INSERT INTO `expert_information_research_direction` VALUES (46, 43, '1');
INSERT INTO `expert_information_research_direction` VALUES (47, 43, '1');
INSERT INTO `expert_information_research_direction` VALUES (48, 43, '1');
INSERT INTO `expert_information_research_direction` VALUES (49, 44, '1');
INSERT INTO `expert_information_research_direction` VALUES (50, 44, '1');
INSERT INTO `expert_information_research_direction` VALUES (51, 44, '1');

-- ----------------------------
-- Table structure for extranet_login_log
-- ----------------------------
DROP TABLE IF EXISTS `extranet_login_log`;
CREATE TABLE `extranet_login_log`  (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT '主键Id (外网登陆日志)',
  `login_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆名',
  `identity` int(5) NULL DEFAULT NULL COMMENT '身份( 0：管理员 1：员工 2：专家)',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 162 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of extranet_login_log
-- ----------------------------
INSERT INTO `extranet_login_log` VALUES (1, '登陆名11', 0, '2019-08-22 09:13:04');
INSERT INTO `extranet_login_log` VALUES (2, '12', 0, '2019-08-22 10:14:04');
INSERT INTO `extranet_login_log` VALUES (3, '12', 0, '2019-08-22 14:17:24');
INSERT INTO `extranet_login_log` VALUES (4, 'wch', 0, '2019-08-23 09:33:37');
INSERT INTO `extranet_login_log` VALUES (5, 'wch', 0, '2019-08-23 10:17:12');
INSERT INTO `extranet_login_log` VALUES (6, 'wch', 0, '2019-08-23 10:23:42');
INSERT INTO `extranet_login_log` VALUES (7, 'wch', 0, '2019-08-23 14:52:19');
INSERT INTO `extranet_login_log` VALUES (8, '登陆名11', 0, '2019-08-23 15:38:33');
INSERT INTO `extranet_login_log` VALUES (9, '登陆名11', 0, '2019-08-23 16:12:13');
INSERT INTO `extranet_login_log` VALUES (10, 'wch', 0, '2019-08-25 09:04:21');
INSERT INTO `extranet_login_log` VALUES (11, '12', 0, '2019-08-25 09:17:20');
INSERT INTO `extranet_login_log` VALUES (12, '登陆名11', 0, '2019-08-25 09:41:04');
INSERT INTO `extranet_login_log` VALUES (13, '登陆名11', 0, '2019-08-25 09:45:11');
INSERT INTO `extranet_login_log` VALUES (14, '登陆名11', 0, '2019-08-25 10:33:45');
INSERT INTO `extranet_login_log` VALUES (15, '登陆名11', 0, '2019-08-25 11:32:15');
INSERT INTO `extranet_login_log` VALUES (16, '12', 0, '2019-08-25 14:21:05');
INSERT INTO `extranet_login_log` VALUES (17, '12', 0, '2019-08-25 14:32:48');
INSERT INTO `extranet_login_log` VALUES (18, '登陆名11', 0, '2019-08-25 14:40:54');
INSERT INTO `extranet_login_log` VALUES (19, '12', 0, '2019-08-25 14:48:50');
INSERT INTO `extranet_login_log` VALUES (20, '12', 0, '2019-08-25 14:52:23');
INSERT INTO `extranet_login_log` VALUES (21, '12', 0, '2019-08-25 15:29:22');
INSERT INTO `extranet_login_log` VALUES (22, '12', 0, '2019-08-25 15:30:10');
INSERT INTO `extranet_login_log` VALUES (23, '12', 0, '2019-08-25 16:38:05');
INSERT INTO `extranet_login_log` VALUES (24, '12', 0, '2019-08-26 08:56:53');
INSERT INTO `extranet_login_log` VALUES (25, '12', 0, '2019-08-26 09:44:16');
INSERT INTO `extranet_login_log` VALUES (26, '12', 0, '2019-08-26 10:24:30');
INSERT INTO `extranet_login_log` VALUES (27, '12', 0, '2019-08-26 10:26:34');
INSERT INTO `extranet_login_log` VALUES (28, '12', 0, '2019-08-26 11:23:00');
INSERT INTO `extranet_login_log` VALUES (29, '12', 0, '2019-08-26 14:10:12');
INSERT INTO `extranet_login_log` VALUES (30, '12', 0, '2019-08-26 18:24:31');
INSERT INTO `extranet_login_log` VALUES (31, '12', 0, '2019-08-26 18:30:05');
INSERT INTO `extranet_login_log` VALUES (32, '12', 0, '2019-08-26 18:34:53');
INSERT INTO `extranet_login_log` VALUES (33, '12', 0, '2019-08-26 18:46:39');
INSERT INTO `extranet_login_log` VALUES (34, '12', 0, '2019-08-27 10:28:21');
INSERT INTO `extranet_login_log` VALUES (35, '12', 0, '2019-08-27 11:02:48');
INSERT INTO `extranet_login_log` VALUES (36, '12', 0, '2019-08-27 11:26:51');
INSERT INTO `extranet_login_log` VALUES (37, '12', 0, '2019-08-27 14:04:21');
INSERT INTO `extranet_login_log` VALUES (38, '12', 0, '2019-08-27 14:41:10');
INSERT INTO `extranet_login_log` VALUES (39, '12', 0, '2019-08-27 15:11:03');
INSERT INTO `extranet_login_log` VALUES (40, '12', 0, '2019-08-27 15:45:00');
INSERT INTO `extranet_login_log` VALUES (41, '12', 0, '2019-08-27 16:19:55');
INSERT INTO `extranet_login_log` VALUES (42, '12', 0, '2019-08-28 09:03:50');
INSERT INTO `extranet_login_log` VALUES (43, '12', 0, '2019-08-28 09:37:36');
INSERT INTO `extranet_login_log` VALUES (44, '12', 0, '2019-08-28 10:14:02');
INSERT INTO `extranet_login_log` VALUES (45, '12', 0, '2019-08-28 10:48:25');
INSERT INTO `extranet_login_log` VALUES (46, '12', 0, '2019-08-28 10:50:37');
INSERT INTO `extranet_login_log` VALUES (47, '12', 0, '2019-08-28 11:41:44');
INSERT INTO `extranet_login_log` VALUES (48, '12', 0, '2019-08-28 14:08:59');
INSERT INTO `extranet_login_log` VALUES (49, '12', 0, '2019-08-28 14:52:09');
INSERT INTO `extranet_login_log` VALUES (50, '12', 0, '2019-08-28 15:02:13');
INSERT INTO `extranet_login_log` VALUES (51, '12', 0, '2019-08-28 15:09:58');
INSERT INTO `extranet_login_log` VALUES (52, '12', 0, '2019-08-28 18:54:46');
INSERT INTO `extranet_login_log` VALUES (53, '12', 0, '2019-08-28 18:59:01');
INSERT INTO `extranet_login_log` VALUES (54, '12', 0, '2019-08-29 16:32:52');
INSERT INTO `extranet_login_log` VALUES (55, '12', 0, '2019-08-29 16:35:43');
INSERT INTO `extranet_login_log` VALUES (56, '12', 0, '2019-08-29 16:39:32');
INSERT INTO `extranet_login_log` VALUES (57, '12', 0, '2019-08-29 16:41:18');
INSERT INTO `extranet_login_log` VALUES (58, '12', 0, '2019-08-29 17:08:40');
INSERT INTO `extranet_login_log` VALUES (59, '12', 0, '2019-08-29 17:10:10');
INSERT INTO `extranet_login_log` VALUES (60, '12', 0, '2019-08-30 13:53:20');
INSERT INTO `extranet_login_log` VALUES (61, '12', 0, '2019-08-30 14:10:52');
INSERT INTO `extranet_login_log` VALUES (62, '12', 0, '2019-08-30 14:42:57');
INSERT INTO `extranet_login_log` VALUES (63, '12', 0, '2019-08-30 16:58:33');
INSERT INTO `extranet_login_log` VALUES (64, 'wch', 0, '2019-08-30 17:33:28');
INSERT INTO `extranet_login_log` VALUES (65, '12', 0, '2019-08-30 17:55:57');
INSERT INTO `extranet_login_log` VALUES (66, 'wch', 0, '2019-09-01 09:12:52');
INSERT INTO `extranet_login_log` VALUES (67, '12', 0, '2019-09-01 09:22:52');
INSERT INTO `extranet_login_log` VALUES (68, '12', 0, '2019-09-01 09:36:45');
INSERT INTO `extranet_login_log` VALUES (69, '12', 0, '2019-09-01 09:38:21');
INSERT INTO `extranet_login_log` VALUES (70, '12', 0, '2019-09-01 10:12:07');
INSERT INTO `extranet_login_log` VALUES (71, 'wch', 0, '2019-09-01 10:33:41');
INSERT INTO `extranet_login_log` VALUES (72, '12', 0, '2019-09-01 11:14:31');
INSERT INTO `extranet_login_log` VALUES (73, '12', 0, '2019-09-01 11:46:52');
INSERT INTO `extranet_login_log` VALUES (74, '12', 0, '2019-09-01 11:50:17');
INSERT INTO `extranet_login_log` VALUES (75, '12', 0, '2019-09-01 11:50:17');
INSERT INTO `extranet_login_log` VALUES (76, '12', 0, '2019-09-01 11:50:17');
INSERT INTO `extranet_login_log` VALUES (77, '12', 0, '2019-09-01 11:50:20');
INSERT INTO `extranet_login_log` VALUES (78, '12', 0, '2019-09-01 14:27:09');
INSERT INTO `extranet_login_log` VALUES (79, '12', 0, '2019-09-01 14:27:15');
INSERT INTO `extranet_login_log` VALUES (80, '12', 0, '2019-09-01 14:27:24');
INSERT INTO `extranet_login_log` VALUES (81, '12', 0, '2019-09-01 14:27:37');
INSERT INTO `extranet_login_log` VALUES (82, '12', 0, '2019-09-01 14:28:03');
INSERT INTO `extranet_login_log` VALUES (83, '12', 0, '2019-09-01 14:28:31');
INSERT INTO `extranet_login_log` VALUES (84, '12', 0, '2019-09-01 14:28:46');
INSERT INTO `extranet_login_log` VALUES (85, '12', 0, '2019-09-01 14:29:07');
INSERT INTO `extranet_login_log` VALUES (86, '12', 0, '2019-09-01 14:30:57');
INSERT INTO `extranet_login_log` VALUES (87, '12', 0, '2019-09-01 14:31:56');
INSERT INTO `extranet_login_log` VALUES (88, '1', 1, '2019-09-01 15:00:50');
INSERT INTO `extranet_login_log` VALUES (89, '1', 1, '2019-09-01 15:01:11');
INSERT INTO `extranet_login_log` VALUES (90, '1', 1, '2019-09-01 15:04:29');
INSERT INTO `extranet_login_log` VALUES (91, '1', 1, '2019-09-01 15:04:37');
INSERT INTO `extranet_login_log` VALUES (92, '1', 1, '2019-09-01 15:05:07');
INSERT INTO `extranet_login_log` VALUES (93, '1', 1, '2019-09-01 15:08:00');
INSERT INTO `extranet_login_log` VALUES (94, '1', 1, '2019-09-01 15:14:02');
INSERT INTO `extranet_login_log` VALUES (95, '1', 1, '2019-09-01 15:14:44');
INSERT INTO `extranet_login_log` VALUES (96, '1', 1, '2019-09-01 15:16:07');
INSERT INTO `extranet_login_log` VALUES (97, '1', 1, '2019-09-01 15:25:48');
INSERT INTO `extranet_login_log` VALUES (98, '1', 1, '2019-09-01 15:28:41');
INSERT INTO `extranet_login_log` VALUES (99, '1', 1, '2019-09-01 15:30:56');
INSERT INTO `extranet_login_log` VALUES (100, '1', 1, '2019-09-01 16:07:20');
INSERT INTO `extranet_login_log` VALUES (101, '12', 0, '2019-09-01 16:16:25');
INSERT INTO `extranet_login_log` VALUES (102, '12', 0, '2019-09-01 16:45:11');
INSERT INTO `extranet_login_log` VALUES (103, '1', 1, '2019-09-01 16:47:00');
INSERT INTO `extranet_login_log` VALUES (104, '12', 0, '2019-09-01 16:47:36');
INSERT INTO `extranet_login_log` VALUES (105, '12', 0, '2019-09-01 16:48:06');
INSERT INTO `extranet_login_log` VALUES (106, '1', 1, '2019-09-01 16:49:26');
INSERT INTO `extranet_login_log` VALUES (107, '1', 1, '2019-09-01 16:51:45');
INSERT INTO `extranet_login_log` VALUES (108, '1', 1, '2019-09-01 16:56:46');
INSERT INTO `extranet_login_log` VALUES (109, '1', 1, '2019-09-01 18:21:46');
INSERT INTO `extranet_login_log` VALUES (110, '1', 1, '2019-09-02 09:08:43');
INSERT INTO `extranet_login_log` VALUES (111, '1', 1, '2019-09-02 09:38:03');
INSERT INTO `extranet_login_log` VALUES (112, '1', 1, '2019-09-02 09:45:25');
INSERT INTO `extranet_login_log` VALUES (113, '1', 1, '2019-09-02 10:46:33');
INSERT INTO `extranet_login_log` VALUES (114, '12', 0, '2019-09-02 14:54:30');
INSERT INTO `extranet_login_log` VALUES (115, '1', 1, '2019-09-02 14:59:30');
INSERT INTO `extranet_login_log` VALUES (116, 'wch', 0, '2019-09-02 15:35:20');
INSERT INTO `extranet_login_log` VALUES (117, '12', 0, '2019-09-02 15:38:11');
INSERT INTO `extranet_login_log` VALUES (118, '12', 0, '2019-09-02 16:43:51');
INSERT INTO `extranet_login_log` VALUES (119, '12', 0, '2019-09-02 16:51:31');
INSERT INTO `extranet_login_log` VALUES (120, '1', 1, '2019-09-02 17:02:17');
INSERT INTO `extranet_login_log` VALUES (121, '1', 1, '2019-09-02 18:09:26');
INSERT INTO `extranet_login_log` VALUES (122, '12', 0, '2019-09-02 18:18:48');
INSERT INTO `extranet_login_log` VALUES (123, '12', 0, '2019-09-02 19:10:48');
INSERT INTO `extranet_login_log` VALUES (124, '12', 0, '2019-09-03 09:07:29');
INSERT INTO `extranet_login_log` VALUES (125, 'wch', 0, '2019-09-03 09:44:31');
INSERT INTO `extranet_login_log` VALUES (126, '1', 1, '2019-09-03 11:27:50');
INSERT INTO `extranet_login_log` VALUES (127, 'wch', 0, '2019-09-03 11:49:35');
INSERT INTO `extranet_login_log` VALUES (128, '1', 1, '2019-09-03 11:58:27');
INSERT INTO `extranet_login_log` VALUES (129, '12', 0, '2019-09-03 14:06:57');
INSERT INTO `extranet_login_log` VALUES (130, 'wch', 0, '2019-09-03 14:30:29');
INSERT INTO `extranet_login_log` VALUES (131, 'wch', 0, '2019-09-03 14:31:38');
INSERT INTO `extranet_login_log` VALUES (132, '12', 0, '2019-09-03 14:33:12');
INSERT INTO `extranet_login_log` VALUES (133, '1', 1, '2019-09-03 14:45:49');
INSERT INTO `extranet_login_log` VALUES (134, '1', 1, '2019-09-03 14:46:37');
INSERT INTO `extranet_login_log` VALUES (135, '1', 1, '2019-09-03 14:47:23');
INSERT INTO `extranet_login_log` VALUES (136, '1', 1, '2019-09-03 14:47:23');
INSERT INTO `extranet_login_log` VALUES (137, 'wch', 0, '2019-09-03 14:59:53');
INSERT INTO `extranet_login_log` VALUES (138, 'wch', 0, '2019-09-03 15:01:20');
INSERT INTO `extranet_login_log` VALUES (139, 'wch', 0, '2019-09-03 15:01:39');
INSERT INTO `extranet_login_log` VALUES (140, '12', 0, '2019-09-03 15:04:28');
INSERT INTO `extranet_login_log` VALUES (141, 'wch', 0, '2019-09-03 15:05:00');
INSERT INTO `extranet_login_log` VALUES (142, 'wch', 0, '2019-09-03 15:10:05');
INSERT INTO `extranet_login_log` VALUES (143, '12', 0, '2019-09-03 15:40:33');
INSERT INTO `extranet_login_log` VALUES (144, '1', 1, '2019-09-03 16:17:01');
INSERT INTO `extranet_login_log` VALUES (145, '12', 0, '2019-09-03 16:29:14');
INSERT INTO `extranet_login_log` VALUES (146, '12', 0, '2019-09-03 16:29:51');
INSERT INTO `extranet_login_log` VALUES (147, '12', 0, '2019-09-03 16:34:48');
INSERT INTO `extranet_login_log` VALUES (148, '12', 0, '2019-09-03 16:41:26');
INSERT INTO `extranet_login_log` VALUES (149, '12', 0, '2019-09-03 16:45:36');
INSERT INTO `extranet_login_log` VALUES (150, 'wch', 0, '2019-09-03 16:53:43');
INSERT INTO `extranet_login_log` VALUES (151, 'wch', 0, '2019-09-03 16:54:34');
INSERT INTO `extranet_login_log` VALUES (152, '1', 1, '2019-09-03 17:19:30');
INSERT INTO `extranet_login_log` VALUES (153, '12', 0, '2019-09-03 18:58:47');
INSERT INTO `extranet_login_log` VALUES (154, '1', 1, '2019-09-03 18:59:25');
INSERT INTO `extranet_login_log` VALUES (155, '12', 0, '2019-09-04 08:55:50');
INSERT INTO `extranet_login_log` VALUES (156, '1', 1, '2019-09-04 08:57:25');
INSERT INTO `extranet_login_log` VALUES (157, 'wch', 0, '2019-09-04 09:47:09');
INSERT INTO `extranet_login_log` VALUES (158, '12', 0, '2019-09-04 09:47:41');
INSERT INTO `extranet_login_log` VALUES (159, '1', 1, '2019-09-04 09:50:33');
INSERT INTO `extranet_login_log` VALUES (160, '12', 0, '2019-09-04 10:13:56');
INSERT INTO `extranet_login_log` VALUES (161, '12', 0, '2019-09-04 10:47:15');
INSERT INTO `extranet_login_log` VALUES (162, '12', 0, '2019-09-04 10:53:18');
INSERT INTO `extranet_login_log` VALUES (163, 'wch', 0, '2019-09-04 10:54:57');
INSERT INTO `extranet_login_log` VALUES (164, '12', 0, '2019-09-04 11:00:21');
INSERT INTO `extranet_login_log` VALUES (165, '12', 0, '2019-09-04 11:00:55');
INSERT INTO `extranet_login_log` VALUES (166, '12', 0, '2019-09-04 11:03:27');
INSERT INTO `extranet_login_log` VALUES (167, '12', 0, '2019-09-04 11:37:25');
INSERT INTO `extranet_login_log` VALUES (168, '1', 1, '2019-09-04 11:45:30');
INSERT INTO `extranet_login_log` VALUES (169, '1', 1, '2019-09-04 14:07:07');
INSERT INTO `extranet_login_log` VALUES (170, '12', 0, '2019-09-04 14:08:17');
INSERT INTO `extranet_login_log` VALUES (171, '1', 1, '2019-09-04 14:11:50');
INSERT INTO `extranet_login_log` VALUES (172, '12', 0, '2019-09-04 14:40:25');
INSERT INTO `extranet_login_log` VALUES (173, '12', 0, '2019-09-04 14:49:47');
INSERT INTO `extranet_login_log` VALUES (174, '12', 0, '2019-09-04 14:50:57');
INSERT INTO `extranet_login_log` VALUES (175, '12', 0, '2019-09-04 14:59:15');
INSERT INTO `extranet_login_log` VALUES (176, '1', 1, '2019-09-04 15:00:33');
INSERT INTO `extranet_login_log` VALUES (177, '12', 0, '2019-09-04 15:01:45');
INSERT INTO `extranet_login_log` VALUES (178, '12', 0, '2019-09-04 15:35:37');
INSERT INTO `extranet_login_log` VALUES (179, '12', 0, '2019-09-04 15:36:57');
INSERT INTO `extranet_login_log` VALUES (180, '1', 1, '2019-09-04 15:37:17');
INSERT INTO `extranet_login_log` VALUES (181, '1', 1, '2019-09-04 15:51:26');
INSERT INTO `extranet_login_log` VALUES (182, '12', 0, '2019-09-04 16:11:57');
INSERT INTO `extranet_login_log` VALUES (183, '1', 1, '2019-09-04 16:12:20');
INSERT INTO `extranet_login_log` VALUES (184, '12', 0, '2019-09-04 16:34:16');
INSERT INTO `extranet_login_log` VALUES (185, '12', 0, '2019-09-04 16:34:49');
INSERT INTO `extranet_login_log` VALUES (186, '12', 0, '2019-09-04 16:36:41');

-- ----------------------------
-- Table structure for extranet_menu
-- ----------------------------
DROP TABLE IF EXISTS `extranet_menu`;
CREATE TABLE `extranet_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `is_father` int(11) NULL DEFAULT NULL COMMENT '是否是一级菜单 0：一级 1：二级菜单',
  `father_id` int(11) NULL DEFAULT NULL COMMENT '父级的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of extranet_menu
-- ----------------------------
INSERT INTO `extranet_menu` VALUES (1, '指南管理', 0, 0);
INSERT INTO `extranet_menu` VALUES (2, '指南征集申报', 1, 1);
INSERT INTO `extranet_menu` VALUES (3, '课题立项', 0, 0);
INSERT INTO `extranet_menu` VALUES (4, '课题申报', 1, 3);
INSERT INTO `extranet_menu` VALUES (5, '课题查询', 1, 3);
INSERT INTO `extranet_menu` VALUES (6, '合同管理', 0, 0);
INSERT INTO `extranet_menu` VALUES (7, '合同提交', 1, 6);
INSERT INTO `extranet_menu` VALUES (8, '合同查询', 1, 6);
INSERT INTO `extranet_menu` VALUES (9, '日常管理', 0, 0);
INSERT INTO `extranet_menu` VALUES (10, '中期检查提交', 1, 9);
INSERT INTO `extranet_menu` VALUES (11, '中期检查查询', 1, 9);
INSERT INTO `extranet_menu` VALUES (12, '进展情况报告提交', 1, 9);
INSERT INTO `extranet_menu` VALUES (13, '进展情况报告查询', 1, 9);
INSERT INTO `extranet_menu` VALUES (14, '重大事项报告', 1, 9);
INSERT INTO `extranet_menu` VALUES (15, '重大事项查询', 1, 9);
INSERT INTO `extranet_menu` VALUES (16, '课题验收', 0, 0);
INSERT INTO `extranet_menu` VALUES (17, '验收申请', 1, 16);
INSERT INTO `extranet_menu` VALUES (18, '已审核验收', 1, 16);
INSERT INTO `extranet_menu` VALUES (19, '验收查询', 1, 16);
INSERT INTO `extranet_menu` VALUES (20, '通知公告', 0, 0);
INSERT INTO `extranet_menu` VALUES (21, '通知公告列表', 1, 20);
INSERT INTO `extranet_menu` VALUES (22, '后台管理', 0, 0);
INSERT INTO `extranet_menu` VALUES (23, '个人信息管理', 1, 22);
INSERT INTO `extranet_menu` VALUES (24, '管理员管理', 1, 22);

-- ----------------------------
-- Table structure for guide_collection
-- ----------------------------
DROP TABLE IF EXISTS `guide_collection`;
CREATE TABLE `guide_collection`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `guide_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '指南建议申报',
  `category` int(25) NOT NULL COMMENT '所属类别',
  `domain` int(25) NOT NULL COMMENT '所属领域',
  `fill_unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '填报单位',
  `fill_contacts` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '填报联系人',
  `unit_principal` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '单位负责人(签字)',
  `reason_basis` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '建议理由及依据(300字以内)',
  `research_content_technology` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主要研究内容和关键技术(300字以内)',
  `expected_target_outcome` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '预期目标和成果（200字以内）',
  `standards_specifications_regulatory` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '拟出标准、技术规范、法规名称',
  `research_period` int(11) NOT NULL COMMENT '研究期限',
  `research_fund` decimal(7, 2) NOT NULL COMMENT '研究经费测算（万元）',
  `demonstration_scale` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '示范工程规模',
  `demonstration_point` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '示范工程点',
  `province_domain_mechanism` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '省内从事该领域的主要研究机构',
  `contact_phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系电话(手机)',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `is_select` int(11) NULL DEFAULT 0 COMMENT '是否选中(0-未选中；1-已选中)',
  `declaration_status` int(11) NULL DEFAULT 0 COMMENT '0- 未审核 1-审核通过 2-审核未通过',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guide_collection
-- ----------------------------
INSERT INTO `guide_collection` VALUES (1, 'qwe', 76, 79, 'xdmd-1', 'fgdsfg', 'kong', 'dfgr5y76y4', 'fgsahteruy6w', '预期目标-1', '拟出标准-1', 2, 12.00, '示范工程规模-1', '示范工程点-1', '主要研究机构-1', '1242342342', '2019-08-20 17:56:44', 1, 0);
INSERT INTO `guide_collection` VALUES (2, 'asd', 77, 80, 'xdmd-2', 'dhnfsfee', 'dfgere', 'rge5ty', 'dfgwegtew5', '预期目标-2', '拟出标准-2', 1, 21.00, '示范工程规模-2', '示范工程点-2', '主要研究机构-2', '4234234', '2019-08-01 23:07:44', 0, 0);
INSERT INTO `guide_collection` VALUES (3, 'zxc', 78, 83, 'xdmd-3', 'sdefsefw', 'dfdr', 'fgdrte', 'gfew56', '预期目标-3', '拟出标准-3', 1, 15.00, '示范工程规模-3', '示范工程点-3', '主要研究机构-3', '234234252', '2019-08-20 17:56:44', 1, 0);
INSERT INTO `guide_collection` VALUES (4, 'zaq', 78, 80, 'xdmd-4', 'fdrt4ewa4tg', 'dfser', 'drgtdewt54', 'gfasdert', '预期目标-4', '拟出标准-4', 2, 32.00, '示范工程规模-4', '示范工程点-4', '主要研究机构-4', '23423242', '2019-08-01 23:07:54', 0, 0);
INSERT INTO `guide_collection` VALUES (5, 'wsx', 77, 81, 'xdmd-5', 'fdtgr5ywe4ty', 'def4e4', 'dftger5tw4', 'dfge5w', '预期目标-5', '拟出标准-5', 2, 52.00, '示范工程规模-5', '示范工程点-5', '主要研究机构-5', '24235342', '2019-08-20 17:56:44', 1, 0);
INSERT INTO `guide_collection` VALUES (6, 'cde', 76, 84, 'xdmd-6', 'rdgewt4e5', 'dfe4rt', 'dfges45', 'gsdartgwer5', '预期目标-6', '拟出标准-6', 1, 54.00, '示范工程规模-6', '示范工程点-6', '主要研究机构-6', '23423424', '2019-08-01 23:08:03', 0, 0);
INSERT INTO `guide_collection` VALUES (8, 'rfv', 78, 85, 'xdmd-8', 'rges5w', 'rgetg', 'fdges5uyw4', 'sergew54', '预期目标-8', '拟出标准-8', 3, 25.00, '示范工程规模-8', '示范工程点-8', '主要研究机构-8', '5653265', '2019-08-01 23:08:06', 0, 0);
INSERT INTO `guide_collection` VALUES (9, 'bgt', 77, 83, 'xdmd-9', 'rfged5e45', 'drge', 'drgrse5y', 'efe5qwt', '预期目标-9', '拟出标准-9', 5, 13.00, '示范工程规模-9', '示范工程点-9', '主要研究机构-9', '265326593', '2019-08-20 17:56:44', 1, 1);
INSERT INTO `guide_collection` VALUES (10, 'yhn', 76, 81, 'hikhg', 'hkhi', 'hikhi', 'hikhik', 'hbihug', 'jguyyfguy', 'ugugu', 2, 30.00, 'hugu', 'hgugtu', 'ghuig', '634146574', '2019-08-01 23:08:16', 0, 0);
INSERT INTO `guide_collection` VALUES (11, 'xfdgrs', 78, 85, 'hvhjv', 'bvhvg', 'jbjv', 'jbvjj', 'jbjvj', 'bhjbvjvj', 'hcgv', 1, 14.25, 'igihhu', 'ihigi', 'ihgfuho', '1472583690', '2019-08-01 23:08:24', 0, 0);
INSERT INTO `guide_collection` VALUES (12, 'fxgdgr', 77, 83, 'dfrgrs', 'fdgrst', 'hxdfgdr', 'fhdrtyA', 'fmgjt', 'figuhdtg', 'dfogjsdor', 1, 25.58, 'dfgsr', 'jdsgfs', 'ujguyj', '12634646', '2019-08-01 23:08:28', 0, 0);
INSERT INTO `guide_collection` VALUES (13, 'fxgdgr', 78, 82, 'dfrgrs', 'fdgrst', 'hxdfgdr', 'fhdrtyA', 'fmgjt', 'figuhdtg', 'dfogjsdor', 1, 25.58, 'dfgsr', 'jdsgfs', 'ujguyj', '12634646', '2019-08-01 23:08:32', 0, 0);
INSERT INTO `guide_collection` VALUES (14, 'fxgdgr', 77, 81, 'dfrgrs', 'fdgrst', 'hxdfgdr', 'fhdrtyA', 'fmgjt', 'figuhdtg', 'dfogjsdor', 1, 25.58, 'dfgsr', 'jdsgfs', 'ujguyj', '12634646', '2019-08-01 23:08:36', 0, 0);
INSERT INTO `guide_collection` VALUES (15, 'kong', 76, 81, 'fggty5ty', 'fgedr5t', 'dr5ydr', 'dgt5ryr', 'fdgr5', 'thr6y', 'fhgrht5rey', 2, 25.52, 'trg5y4564', 'fgdrtwey', 'fgdt5ry', '51438545', '2019-08-01 23:08:39', 0, 0);
INSERT INTO `guide_collection` VALUES (16, '李梦培', 76, 83, 'resew', 'xgfdey5rw', 'rgwe5y', 'fge5w', 'drgwe56', 'rdy54y745', 'hgrf6y537', 2, 25.27, 'fghfry5e', 'ghet6e4', 'ght67u', '4564336', '2019-08-01 23:08:43', 0, 0);
INSERT INTO `guide_collection` VALUES (17, 'chfth大丰是', 77, 83, 'fgyjt6', 'fhtre67', 'fthuyuyht68', 'htre67', 'fgygyu658', 'ytur68', 'yt8765', 1, 12.23, 'rgr6y', 'drygr4567', 'fty6e657', '1535896615', '2019-08-01 23:08:45', 0, 0);
INSERT INTO `guide_collection` VALUES (18, 'xjhbvdrjf', 78, 82, 'bnikgb', 'bhjigvb ', 'jkhbjk', 'bkjbkib', 'bkjbkj', 'bkjbk', 'hjbjv', 1, 14.25, 'nbkjgbg', 'bjhbj', 'bkjbvjk', '12347569', '2019-08-01 23:08:50', 0, 0);
INSERT INTO `guide_collection` VALUES (19, 'xfges5rt', 78, 85, 'jhvbju', 'jhbvjuv ', 'hjvuhj', 'jhvb', 'jhbbik', 'bnjgkik', 'bkjgbggkj', 1, 47.25, 'jvhbnj', 'bmjhb', 'bhkjl', '478965', '2019-08-07 14:58:24', 0, 2);
INSERT INTO `guide_collection` VALUES (20, 'FDRG', 77, 79, 'dfd', 'cfgdrg', 'dfgwer', 'xdgder', 'dger', 'dfrgesr', 'drgegr4e', 2, 23.25, 'drgedr4g', 'drgweg', 'dwsewgs', '12547852', '2019-08-01 23:09:04', 0, 0);
INSERT INTO `guide_collection` VALUES (21, 'rtge57', 76, 79, 'dre5yew', 'drtew56y', 'ert456', 'rt465', 'derty43753', 'ryt5uy735', 'rtyr5675', 2, 34.25, 'sefgw5y', 'rtew57', 'grt357', '2564896', '2019-08-01 23:09:07', 0, 0);
INSERT INTO `guide_collection` VALUES (22, 'fgsdty', 77, 80, 'xgtr', 'xfbgte', 'dfhrtew56', 'dcfhgte4685', 'bcgety7u', 'fgrte67', 'ther6735', 3, 23.58, 'fgerte6', 'cgnjhyr7i', 'fbfghrtu6e', '25134694', '2019-08-01 23:09:10', 0, 0);
INSERT INTO `guide_collection` VALUES (23, 'QWE', 78, 84, 'xdmd-1', 'fgdsfg', 'kong', 'dfgr5y76y4', 'fgsahteruy6w', '预期目标-1', '拟出标准-1', 2, 12.00, '示范工程规模-1', '示范工程点-1', '主要研究机构-1', '1242342342', '2019-08-07 15:33:02', 0, 0);
INSERT INTO `guide_collection` VALUES (24, 'qwe', 78, 82, 'xdmd-1', 'fgdsfg', 'kong', 'dfgr5y76y4', 'fgsahteruy6w', '预期目标-1', '拟出标准-1', 2, 12.00, '示范工程规模-1', '示范工程点-1', '主要研究机构-1', '1242342342', '2019-08-01 23:09:24', 0, 0);
INSERT INTO `guide_collection` VALUES (25, 'QWE', 77, 79, 'xdmd-1', 'fgdsfg', 'kong', 'dfgr5y76y4', 'fgsahteruy6w', '预期目标-1', '拟出标准-1', 2, 12.00, '示范工程规模-1', '示范工程点-1', '主要研究机构-1', '1242342342', '2019-08-07 15:33:07', 0, 0);
INSERT INTO `guide_collection` VALUES (26, 'qwe', 76, 83, 'xdmd-1', 'fgdsfg', 'kong', 'dfgr5y76y4', 'fgsahteruy6w', '预期目标-1', '拟出标准-1', 2, 12.00, '示范工程规模-1', '示范工程点-1', '主要研究机构-1', '1242342342', '2019-08-01 23:09:55', 0, 0);
INSERT INTO `guide_collection` VALUES (27, 'lmp520', 76, 79, 'ilikelmp', 'fthfrty', 'fhjyteu', 'fhgrtyu6e', 'ghtr6u', 'vghtr7i', 'ghtr7i', 2, 23.25, 'fgret63', 'rgw357', 'rfgwe573', '54341956', '2019-08-01 23:09:38', 0, 0);
INSERT INTO `guide_collection` VALUES (28, 'qWeRt', 77, 82, 'fghftyu', 'bgftuyjt', 'gnvghyir', 'gndyu', 'gvngtiure', 'yjhrt7i', 'gjhyrui', 4, 45.26, 'gfrty', 'fdwsthr', 'vfdshtwr', '125354968', '2019-08-07 15:33:21', 0, 0);
INSERT INTO `guide_collection` VALUES (29, 'QWErrt', 78, 80, 'dfgther', 'ghrty', 'fghet', 'ghreut6', 'fghtu6', 'gfhteu6fth', 'gfhrtdueu6', 3, 36.25, 'cfgsthes', 'ftgsrhtw', 'fbgsdtrhew', '1875155698', '2019-08-07 15:33:35', 0, 0);
INSERT INTO `guide_collection` VALUES (30, 'grery', 78, 83, 'vrgfeyq', 'vdgfrtg', 'dfryhwyh', 'fvdrhyrwe5', 'fdbrgty', 'dver', 'fdgegwrhy', 2, 112.00, 'fdxbvget', 'xdedry', 'fgty5ry', 'bfghsreth', '2019-08-07 14:26:40', 0, 0);
INSERT INTO `guide_collection` VALUES (31, 'guideName', 78, 81, 'fillUnit', 'fillContacts', 'unitPrincipal', 'reasonBasis', 'researchContentTechnology', 'expectedTargetOutcome', 'standardsSpecificationsRegulatory', 2, 123.00, 'demonstrationScale', 'demonstrationPoint', 'provinceDomainMechanism', 'contactPhone', '2019-08-07 14:48:44', 0, 0);
INSERT INTO `guide_collection` VALUES (32, 'fhtreu', 76, 82, 'thru6', 'fgrtw67', 'tfghe64', 'ghryjruik', 'htruytu', 'dfghgfyute', 'dtrhyyu7i', 2, 10.00, 'fghrfhesh', 'gffrthrhe', 'drthytir', 'fvghf', '2019-08-07 14:52:26', 0, 0);
INSERT INTO `guide_collection` VALUES (33, 'guideName1', 86, 80, 'fillUnit1', 'fillContacts1', 'unitPrincipal1', 'reasonBasis1', 'researchContentTechnology1', 'expectedTargetOutcome1', 'standardsSpecificationsRegulatory1', 3, 145.00, 'demonstrationScale', 'demonstrationPoint', 'provinceDomainMechanism', 'contactPhone', '2019-08-07 14:57:59', 0, 0);
INSERT INTO `guide_collection` VALUES (34, 'grewy4y', 86, 82, 'dfrgew5yw', 'dfvewry5', 'ythr53', 'fdsgbtrhweu354', 'trh536u', 'dsgrg', 'dfgwty', 3, 4456.00, 'bdfhyrwetyd', 'fgdswthwr', 'drg435y', 'fbgsht', '2019-08-07 15:00:23', 0, 0);
INSERT INTO `guide_collection` VALUES (35, '123', 76, 79, '123', '123', '123', '123', '123', '123', '123', 123, 123.00, '123', '123', '123', '123123123', '2019-08-18 10:03:29', 0, 0);
INSERT INTO `guide_collection` VALUES (36, '123', 76, 79, '123', '123', '123', '123', '123', '123', '123', 123, 123.00, '123', '123', '123', '123123123', '2019-08-18 10:11:30', 0, 0);
INSERT INTO `guide_collection` VALUES (37, 'ftgyer67', 76, 79, 'drtffw5463', 'dfgtrw5', 'tfye67', 'fguy658', 'ftghrw67y3', 'deasft3', 'ftgh57', 12, 12.25, 'xdfrtw', 'cfgdsty', 'tyre67e', 'vcghfd', '2019-08-18 10:21:45', 0, 0);
INSERT INTO `guide_collection` VALUES (38, '123', 76, 79, '123', '123', '123', '123', '123', '123', '123', 123, 123.00, '123', '123', '123', '123', '2019-08-18 10:23:14', 0, 0);
INSERT INTO `guide_collection` VALUES (39, '1', 76, 80, '1', '1', '1', '1', '11', '1', '1', 1, 1.00, '1', '1', '1', '1', '2019-08-18 10:30:27', 0, 0);
INSERT INTO `guide_collection` VALUES (40, '2', 76, 79, '2', '2', '2', '2', '2', '2', '2', 2, 2.00, '22', '2', '2', '2', '2019-08-18 10:32:58', 0, 0);
INSERT INTO `guide_collection` VALUES (41, '33', 76, 79, '3', '3', '3', '3', '3', '3', '3', 3, 3.00, '3', '3', '3', '3', '2019-08-18 10:40:06', 0, 0);
INSERT INTO `guide_collection` VALUES (42, '4', 77, 80, '4', '4', '4', '4', '4', '4', '4', 4, 4.00, '4', '4', '4', '4', '2019-08-18 10:41:31', 0, 0);
INSERT INTO `guide_collection` VALUES (43, '5', 76, 79, '5', '5', '5', '5', '5', '5', '5', 5, 5.00, '5', '5', '5', '5', '2019-08-18 10:42:11', 0, 0);
INSERT INTO `guide_collection` VALUES (44, '6', 77, 80, '6', '6', '6', '6', '6', '6', '6', 6, 6.00, '6', '6', '6', '6', '2019-08-18 10:43:28', 0, 0);
INSERT INTO `guide_collection` VALUES (45, '7', 76, 79, '7', '7', '7', '7', '7', '77', '7', 7, 7.00, '7', '7', '7', '7', '2019-08-18 10:44:16', 0, 0);
INSERT INTO `guide_collection` VALUES (46, '1', 78, 79, '1', '1', '1', '1', '1', '1', '1', 1, 1.00, '1', '1', '1', '1', '2019-08-18 10:44:36', 0, 0);
INSERT INTO `guide_collection` VALUES (47, '123', 77, 80, '123', '123', '123', '123', '123', '123', '12', 123, 3123.00, '123', '123', '123', '123', '2019-08-18 10:47:29', 0, 0);
INSERT INTO `guide_collection` VALUES (48, '111', 76, 79, '11', '11', '11', '11', '11', '11', '111', 11, 11.00, '11', '11', '11', '11', '2019-08-18 10:48:58', 0, 0);
INSERT INTO `guide_collection` VALUES (49, 'qwe', 76, 79, 'qwe', 'qwe', 'qwe', 'qwe', 'qwe', 'qwe', 'qwe', 1, 1.00, 'qwe', 'eqwe', 'qwe', 'qwe', '2019-08-18 10:49:30', 0, 0);
INSERT INTO `guide_collection` VALUES (50, '12', 86, 79, '21', '12', '12', '12', '12', '12', '12', 12, 12.00, '12', '12', '21', '12', '2019-08-18 10:50:41', 0, 0);
INSERT INTO `guide_collection` VALUES (51, '1231', 77, 80, '123', '1231', '12312', '123', '123123', '123', '3123', 123, 123.00, '1231', '123', '23', '23', '2019-08-18 10:51:30', 0, 0);
INSERT INTO `guide_collection` VALUES (52, '55', 77, 79, '34', '44', '3434', '123', '34', '3434', '34', 343, 4343.00, '434', '4', '44', '4', '2019-08-18 10:52:40', 0, 0);
INSERT INTO `guide_collection` VALUES (53, '11', 76, 79, '1', '1', '1', '1', '11', '1', '1', 1, 1.00, '1', '1', '1', '1', '2019-08-18 10:53:36', 0, 0);
INSERT INTO `guide_collection` VALUES (54, '11', 77, 80, '1', '111', '11', '1', '11', '11', '11', 1, 11.00, '111', '111', '1', '11', '2019-08-18 11:03:48', 0, 0);
INSERT INTO `guide_collection` VALUES (55, '0123q', 76, 80, '123', '123', '123', '123', '123', '123', '123', 123, 1231.00, '23', '123', '123', '123', '2019-08-18 14:32:51', 0, 0);
INSERT INTO `guide_collection` VALUES (56, '11', 76, 79, '1', '1', '1', '1', '1', '1', '1', 1, 1.00, '1', '1', '1', '1', '2019-08-18 14:33:31', 0, 0);
INSERT INTO `guide_collection` VALUES (57, '1', 76, 79, '1', '1', '1', '1', '1', '1', '1', 1, 1.00, '1', '1', '1', '1', '2019-08-18 14:35:31', 0, 0);
INSERT INTO `guide_collection` VALUES (58, '1', 77, 79, '1', '1', '1', '1', '1', '1', '1', 1, 1.00, '1', '1', '1', '1', '2019-08-18 14:36:53', 0, 0);
INSERT INTO `guide_collection` VALUES (59, '1', 77, 79, '1', '1', '1', '1', '1', '1', '1', 1, 1.00, '1', '1', '1', '1', '2019-08-18 14:38:11', 0, 0);
INSERT INTO `guide_collection` VALUES (60, '0', 76, 79, '0', '0', '0', '0', '0', '0', '0', 0, 0.00, '0', '0', '0', '0', '2019-08-18 14:39:49', 0, 0);
INSERT INTO `guide_collection` VALUES (61, '99999999', 76, 79, '9999999', '9', '9', '9', '9999999999', '99999999999', '9', 99999, 9999.00, '9', '99999', '99', '9', '2019-08-18 15:56:08', 0, 0);
INSERT INTO `guide_collection` VALUES (62, '1www', 77, 80, '1', '1', '1', '1', '1', '1', '1', 1, 1.00, '1', '1', '1', '1', '2019-08-19 09:54:15', 0, 0);
INSERT INTO `guide_collection` VALUES (63, 'guideName', 78, 79, 'fillUnit', 'fillContacts', 'unitPrincipal', 'reasonBasis', 'researchContentTechnology', 'expectedTargetOutcome', 'standardsSpecificationsRegulatory', 23, 123.00, 'demonstrationScale', 'demonstrationPoint', 'provinceDomainMechanism', 'contactPhone', '2019-08-23 11:19:17', 0, 0);
INSERT INTO `guide_collection` VALUES (64, 'stdfsering', 78, 0, 'strsdfseing', 'strfsetreing', 'xfdse', 'string', 'ssdsewtring', 'staadwring', 'sing', 23, 2.00, 'stdsdsfring', 'xdfs', 'stdasewrring', 'qwe', '2019-08-23 11:26:48', 0, 0);
INSERT INTO `guide_collection` VALUES (65, '名称1', 76, 79, '啊啊啊啊啊', '嗷嗷', '啊啊a', '11111111', '111111111111111', '1111', '2手动阀', 12312, 1123.00, '蛐蛐儿无若', '切尔奇翁r', '却仍然若若', '111111111', '2019-08-23 11:27:42', 0, 0);

-- ----------------------------
-- Table structure for guide_collection_limit_time
-- ----------------------------
DROP TABLE IF EXISTS `guide_collection_limit_time`;
CREATE TABLE `guide_collection_limit_time`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guide_collection_start_time` date NOT NULL COMMENT '指南征集开始时间',
  `guide_collection_end_time` date NOT NULL COMMENT '指南征集结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guide_collection_limit_time
-- ----------------------------
INSERT INTO `guide_collection_limit_time` VALUES (1, '2019-08-05', '2019-08-07');

-- ----------------------------
-- Table structure for guide_summary
-- ----------------------------
DROP TABLE IF EXISTS `guide_summary`;
CREATE TABLE `guide_summary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `guide_summary_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '指南汇总标题',
  `guide_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '指南建议名称',
  `domain` int(255) NOT NULL COMMENT '所属领域',
  `category` int(255) NOT NULL COMMENT '填报类别（所属类别）',
  `unit_category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '单位类别',
  `fill_unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '填报单位',
  `fill_contacts` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '填报联系人',
  `research_period` int(11) NOT NULL COMMENT '研究期限',
  `reason_basis` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '建议理由及依据',
  `research_content_technology` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主要研究内容和关键技术',
  `expected_target_outcome` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '预期目标和成果',
  `standards_specifications_regulatory` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '拟出标准、技术规范、法规名称',
  `research_fund` decimal(10, 2) NOT NULL COMMENT '研究经费测算',
  `demonstration_scale` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '示范工程规模',
  `demonstration_point` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '示范工程点',
  `province_domain_mechanism` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '省内从事该领域的主要研究机构',
  `contact_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系电话(手机)',
  `project_time` date NOT NULL COMMENT '建议立项时间',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `check_back_result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '查重结果',
  `check_back_note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '查重备注',
  `ownership_category` int(11) NOT NULL COMMENT '归属类别',
  `ownership_domain` int(11) NOT NULL COMMENT '归属领域',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guide_summary
-- ----------------------------
INSERT INTO `guide_summary` VALUES (1, '测试1', '指南建议名称1', 80, 78, '暂时数据', '12', '技术规范、法规', 5, '由及依据由及依据由及依据', '要研究内容和关要研究内容和关要研究内容和关', '预期目标预期目标预期目标', '技术规范、法规技术规范、法规', 123.00, '技术规范、法规', '技术规范、法规', '技术规范、法规技术规范、法规', '13777777777', '2019-09-04', NULL, NULL, NULL, 76, 82, 'xxxx', '20190904101439');
INSERT INTO `guide_summary` VALUES (2, '测试1', '1111', 80, 77, '暂时数据', '4', 'zzz', 44, '123121', '2312321', '12321321', '34543543', 45.00, '44', '44', '44', '18855532999', '2019-09-04', NULL, NULL, NULL, 76, 82, 'xxxx', '20190904101439');
INSERT INTO `guide_summary` VALUES (3, '测试1', 'zxc', 82, 76, '暂时数据', '4', 'zxc', 230, 'qwe', 'zxc', 'zxc', 'zxc', 320.00, 'zxc', 'zxc', 'zxc', 'zxc', '2019-09-04', NULL, NULL, NULL, 76, 82, 'xxxx', '20190904101439');
INSERT INTO `guide_summary` VALUES (4, '测试1', 'zxc', 82, 76, '暂时数据', '4', 'zxc', 230, 'qwe', 'zxc', 'zxc', 'zxc', 320.00, 'zxc', 'zxc', 'zxc', 'zxc', '2019-09-04', NULL, NULL, NULL, 76, 82, 'xxxx', '20190904101439');
INSERT INTO `guide_summary` VALUES (5, '测试1', 'zxc', 82, 76, '暂时数据', '4', 'zxc', 230, 'qwe', 'zxc', 'zxc', 'zxc', 320.00, 'zxc', 'zxc', 'zxc', 'zxc', '2019-09-04', NULL, NULL, NULL, 76, 82, 'xxxx', '20190904101439');
INSERT INTO `guide_summary` VALUES (6, '测试1', 'sd', 82, 76, '暂时数据', '4', 'sds', 230, 'qwe', 'qwe', 'sd', 'qweq', 320.00, 'asd', 'sd', 'asd', 'asd', '2019-09-04', NULL, NULL, NULL, 76, 82, 'xxxx', '20190904101439');
INSERT INTO `guide_summary` VALUES (7, '测试1', '名称123', 81, 78, '暂时数据', '12', '搜索', 2, '理由理由理由理由', '技术技术技术', '成果成果', '名称名称', 123.00, '规模', '示范点', '拉克丝江东父老卡接收到了开发', '13777777777', '2019-09-04', NULL, NULL, NULL, 76, 82, 'xxxx', '20190904101439');
INSERT INTO `guide_summary` VALUES (8, '测试1', 'qwe', 82, 78, '暂时数据', '4', 'qwe', 230, 'qwe', 'qwe', 'qwe', 'qweq', 320.00, 'qwe', 'qwe', 'we', 'qwe', '2019-09-04', NULL, NULL, NULL, 76, 82, 'xxxx', '20190904101439');
INSERT INTO `guide_summary` VALUES (9, '测试1', '这地方瑟夫', 79, 76, '暂时数据', '这地方瑟夫', '这地方瑟夫', 20, '这地方瑟夫', '这地方瑟夫', '这地方瑟夫', '这地方瑟夫', 123.00, '这地方瑟夫', '这地方瑟夫', '这地方瑟夫', '这地方瑟夫', '2019-09-04', NULL, NULL, NULL, 76, 82, 'xxxx', '20190904101439');
INSERT INTO `guide_summary` VALUES (10, '测试1', '11', 79, 76, '暂时数据', '222', '222', 222, '222', '222', '222', '222', 22.00, '22', '22', '22', '13775899605', '2019-09-04', NULL, NULL, NULL, 76, 82, 'xxxx', '20190904101439');
INSERT INTO `guide_summary` VALUES (11, '测试2', '1111', 80, 77, '暂时数据', '4', 'zzz', 44, '123121', '2312321', '12321321', '34543543', 45.00, '44', '44', '44', '18855532999', '2019-09-03', NULL, NULL, NULL, 76, 85, 'xxxx', '20190904101527');
INSERT INTO `guide_summary` VALUES (12, '测试2', '指南建议名称1', 80, 78, '暂时数据', '12', '技术规范、法规', 5, '由及依据由及依据由及依据', '要研究内容和关要研究内容和关要研究内容和关', '预期目标预期目标预期目标', '技术规范、法规技术规范、法规', 123.00, '技术规范、法规', '技术规范、法规', '技术规范、法规技术规范、法规', '13777777777', '2019-09-04', NULL, NULL, NULL, 77, 85, 'xxxx', '20190904101554');
INSERT INTO `guide_summary` VALUES (13, '测试1', '1111', 80, 77, '暂时数据', '4', 'zzz', 44, '123121', '2312321', '12321321', '34543543', 45.00, '44', '44', '44', '18855532999', '2019-09-04', NULL, NULL, NULL, 77, 82, 'xxxx', '20190904101630');
INSERT INTO `guide_summary` VALUES (14, '测试1', '指南建议名称1', 80, 78, '暂时数据', '12', '技术规范、法规', 5, '由及依据由及依据由及依据', '要研究内容和关要研究内容和关要研究内容和关', '预期目标预期目标预期目标', '技术规范、法规技术规范、法规', 123.00, '技术规范、法规', '技术规范、法规', '技术规范、法规技术规范、法规', '13777777777', '2019-09-04', NULL, NULL, NULL, 76, 82, 'xxxx', '20190904101740');
INSERT INTO `guide_summary` VALUES (15, '测试1', '指南建议名称1', 80, 78, '暂时数据', '12', '技术规范、法规', 5, '由及依据由及依据由及依据', '要研究内容和关要研究内容和关要研究内容和关', '预期目标预期目标预期目标', '技术规范、法规技术规范、法规', 123.00, '技术规范、法规', '技术规范、法规', '技术规范、法规技术规范、法规', '13777777777', '2019-09-04', NULL, NULL, NULL, 78, 82, 'xxxx', '20190904101740');
INSERT INTO `guide_summary` VALUES (16, '测试1', '1111', 80, 77, '暂时数据', '4', 'zzz', 44, '123121', '2312321', '12321321', '34543543', 45.00, '44', '44', '44', '18855532999', '2019-09-04', NULL, NULL, NULL, 77, 82, 'xxxx', '20190904101740');
INSERT INTO `guide_summary` VALUES (17, '测试3', '指南建议名称1', 80, 78, '暂时数据', '12', '技术规范、法规', 5, '由及依据由及依据由及依据', '要研究内容和关要研究内容和关要研究内容和关', '预期目标预期目标预期目标', '技术规范、法规技术规范、法规', 123.00, '技术规范、法规', '技术规范、法规', '技术规范、法规技术规范、法规', '13777777777', '2019-09-04', NULL, NULL, NULL, 76, 80, 'xxxx', '20190904105305');
INSERT INTO `guide_summary` VALUES (18, '测试3', '1111', 80, 77, '暂时数据', '4', 'zzz', 44, '123121', '2312321', '12321321', '34543543', 45.00, '44', '44', '44', '18855532999', '2019-09-04', NULL, NULL, NULL, 76, 80, 'xxxx', '20190904105305');

-- ----------------------------
-- Table structure for key_research_developers
-- ----------------------------
DROP TABLE IF EXISTS `key_research_developers`;
CREATE TABLE `key_research_developers`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '合同管理的子表三',
  `contract_id` int(11) NOT NULL COMMENT '合同主表id',
  `key_dev_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主要开发人员姓名',
  `unit_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所在单位',
  `gender` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '性别',
  `age` int(2) NOT NULL COMMENT '年龄',
  `professional_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '职称',
  `professional` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '从事专业',
  `work_task` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '本课题中承担工作',
  `working_time` int(25) NOT NULL COMMENT '为本课题工作时间（%）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of key_research_developers
-- ----------------------------
INSERT INTO `key_research_developers` VALUES (1, 27, '孔维潮', 'xdmd', '男', 25, 'zdfses', 'dsaa', 'java开发', 80);
INSERT INTO `key_research_developers` VALUES (2, 27, '张玉得龙', 'string', '男', 22, 'xdfrsd', 'fddrd', 'string', 90);
INSERT INTO `key_research_developers` VALUES (3, 27, '孔维潮', 'xdmd', '男', 25, 'zdfses', 'dsaa', 'java开发', 80);
INSERT INTO `key_research_developers` VALUES (4, 27, '张玉得龙', 'string', '男', 22, 'xdfrsd', 'fddrd', 'string', 90);
INSERT INTO `key_research_developers` VALUES (5, 27, '孔维潮', 'xdmd', '男', 25, 'zdfses', 'dsaa', 'java开发', 80);
INSERT INTO `key_research_developers` VALUES (6, 27, '张玉得龙', 'string', '男', 22, 'xdfrsd', 'fddrd', 'string', 90);
INSERT INTO `key_research_developers` VALUES (7, 27, '孔维潮', 'xdmd', '男', 25, 'zdfses', 'dsaa', 'java开发', 80);
INSERT INTO `key_research_developers` VALUES (8, 27, '张玉得龙', 'string', '男', 22, 'xdfrsd', 'fddrd', 'string', 90);
INSERT INTO `key_research_developers` VALUES (12, 34, '1', '2', '3', 4, '5', '6', '7', 8);
INSERT INTO `key_research_developers` VALUES (13, 34, '1', '2', '3', 4, '5', '6', '7', 8);
INSERT INTO `key_research_developers` VALUES (14, 34, '1', '2', '3', 4, '5', '6', '7', 8);
INSERT INTO `key_research_developers` VALUES (15, 34, '1', '2', '3', 4, '5', '6', '7', 8);
INSERT INTO `key_research_developers` VALUES (16, 35, '1', '2', '3', 4, '5', '6', '7', 8);
INSERT INTO `key_research_developers` VALUES (17, 36, '1', '2', '3', 4, '5', '6', '7', 8);

-- ----------------------------
-- Table structure for major_matters_filing
-- ----------------------------
DROP TABLE IF EXISTS `major_matters_filing`;
CREATE TABLE `major_matters_filing`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `subject_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课题名称',
  `commitment_unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '承担单位',
  `unit_head` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '单位负责人',
  `unit_head_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '单位负责人电话',
  `project_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '課題編號',
  `adjust_type_id` int(11) NOT NULL COMMENT '调整类型',
  `adjustment_matters_id` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调整事项',
  `specific_facts` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '具体情况说明',
  `change_application_attachment_id` int(11) NULL DEFAULT NULL COMMENT '变更申请表附件id',
  `expert_argumentation_attachment_id` int(11) NULL DEFAULT NULL COMMENT '专家论证意见附件id',
  `filing_application_attachment_id` int(11) NULL DEFAULT NULL COMMENT '备案申请表附件id',
  `approval_documents_attachment_id` int(11) NULL DEFAULT NULL COMMENT '批准文件附件id',
  `shenhe_status` int(11) NOT NULL DEFAULT 0 COMMENT '审批状态【0-未审批 1-已审批】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of major_matters_filing
-- ----------------------------
INSERT INTO `major_matters_filing` VALUES (1, 'xfvgdrgd', 'yhfvyug', 'gyugug', '', 'uyjgguygf', 1, '3', 'juhzsbebdfrgfrtydfsg', NULL, NULL, NULL, NULL, 1);
INSERT INTO `major_matters_filing` VALUES (2, 'dfsrgt', 'yhfvyugxdfrgrtge', 'xdfsrtgea', '', 'dsgewrargarg', 2, '7', 'juhzsbebdfrgfrtydfsg', NULL, NULL, NULL, NULL, 1);
INSERT INTO `major_matters_filing` VALUES (3, 'dfsaerg', 'sxdwaerva', 'sdasdfWEF', '', 'SAsAWEF', 2, '6', 'juhzsbebdfrgfrtydfsg', NULL, NULL, NULL, NULL, 1);
INSERT INTO `major_matters_filing` VALUES (4, 'subjectName', 'commitmentUnit', 'unitHead', '', 'unitHeadPhone', 1, '2', 'qweasd', NULL, NULL, NULL, NULL, 0);
INSERT INTO `major_matters_filing` VALUES (5, 'string', 'string', 'string', '', 'string', 0, '0', 'string', NULL, NULL, NULL, NULL, 0);
INSERT INTO `major_matters_filing` VALUES (6, 'dfvet', 'QWE', 'erte', '', 'asda', 0, '0', 'asd', NULL, NULL, NULL, NULL, 0);
INSERT INTO `major_matters_filing` VALUES (7, 'zxcsd', 'nbmfh', 'zxcas', '', 'qweasd', 1, '4', 'tryd', NULL, NULL, NULL, NULL, 0);
INSERT INTO `major_matters_filing` VALUES (8, '147', '123', '852', '', '456', 2, '6', '789', NULL, NULL, NULL, NULL, 0);
INSERT INTO `major_matters_filing` VALUES (9, 'gfhgw', 'qweas', 'gfht', '', 'asdz', 2, '2,3', 'erta', NULL, NULL, NULL, NULL, 0);
INSERT INTO `major_matters_filing` VALUES (10, '1', '3', '4', '', '2', 1, '5,2', '5', NULL, NULL, NULL, NULL, 0);
INSERT INTO `major_matters_filing` VALUES (11, '1', '3', '4', '', '2', 1, '2,3', '5', NULL, NULL, NULL, NULL, 0);
INSERT INTO `major_matters_filing` VALUES (12, '1', '3', '4', '', '2', 2, '6', '5', NULL, NULL, NULL, NULL, 0);

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
-- Table structure for mid_check
-- ----------------------------
DROP TABLE IF EXISTS `mid_check`;
CREATE TABLE `mid_check`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `subject_no` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课题编号',
  `subject_name` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课题名称',
  `subject_start_time` date NOT NULL COMMENT '课题开始时间',
  `subject_end_time` date NOT NULL COMMENT '课题结束时间',
  `commitment_unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '承担单位',
  `topic_contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课题联系人',
  `subject_contact_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课题联系人电话',
  `subject_leader` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课题负责人',
  `leader_phone` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课题负责人电话',
  `contract_plan_crogress_execution` int(11) NOT NULL COMMENT '课题按合同计划进度执行情况(字典id/41-44)',
  `not_completing_reason` int(11) NOT NULL COMMENT '课题未按时完成原因(字典id/48-52)',
  `total_budget` decimal(10, 2) NOT NULL COMMENT '预算总经费（万元）',
  `provincial_subject_budget` decimal(10, 2) NOT NULL COMMENT '省环保课题预算',
  `unit_financing_budget` decimal(10, 2) NOT NULL COMMENT '单位自筹预算',
  `other_budgets` decimal(10, 2) NOT NULL COMMENT '其他预算',
  `total_expenditure` decimal(10, 2) NOT NULL COMMENT '经费使用总支出（万元）',
  `provincial_subject_expenditure_budget` decimal(10, 2) NOT NULL COMMENT '省环保课题支出预算',
  `unit_inancing_expenditure_budget` decimal(10, 2) NOT NULL COMMENT '单位自筹支出预算',
  `other_expenditure_budget` decimal(10, 2) NOT NULL COMMENT '其他支出预算',
  `equipment_usage` decimal(10, 2) NOT NULL COMMENT '设备费使用情况',
  `material_usage` decimal(10, 2) NOT NULL COMMENT '材料费使用情况',
  `test_ingy_use` decimal(10, 2) NOT NULL COMMENT '测试化验使用情况',
  `processing_fee_usage` decimal(10, 2) NOT NULL COMMENT '加工费使用情况',
  `fuel_usage` decimal(10, 2) NOT NULL COMMENT '燃料动力费使用情况',
  `travel_expenses` decimal(10, 2) NOT NULL COMMENT '差旅费使用情况',
  `meeting_fee_usage` decimal(10, 2) NOT NULL COMMENT '会议费使用情况',
  `expert_consultation_fees_usage` decimal(10, 2) NOT NULL COMMENT '专家咨询费使用情况',
  `publication_documentation_news_intellectualproperty` decimal(10, 2) NOT NULL COMMENT '出版/文献/信息传播/知识产权事务费使用情况',
  `labour_costs_usage` decimal(10, 2) NOT NULL COMMENT '劳务费（或管理及人员费中人员费）使用情况',
  `other_expense_usage` decimal(10, 2) NOT NULL COMMENT '其他费用使用情况',
  `indirect_costs_usage` decimal(10, 2) NOT NULL COMMENT '间接费用（水、电、气消耗及管理费等）使用情况',
  `external_cooperation_fees` decimal(10, 2) NOT NULL COMMENT '外部合作费使用情况',
  `new_sales` decimal(10, 2) NOT NULL COMMENT '新增销售额（万元）',
  `new_profit` decimal(10, 2) NOT NULL COMMENT '新增利润（万元）',
  `new_tax` decimal(10, 2) NOT NULL COMMENT '新增税金（万元）',
  `foreign_exchange` decimal(10, 2) NOT NULL COMMENT '创汇（万美元）',
  `new_products` int(255) NOT NULL COMMENT '新产品（个）',
  `new_equipment` int(255) NOT NULL COMMENT '新装备（装置）（台）',
  `new_materials` int(255) NOT NULL COMMENT '新材料(件）',
  `new_process` int(255) NOT NULL COMMENT '新工艺（项）',
  `new_varieties` int(255) NOT NULL COMMENT '新品种（个）',
  `invention_patents` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发明专利',
  `utility_model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实用新型',
  `design` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '外观设计',
  `foreign_patents` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '国外专利',
  `total_number_papers` int(255) NOT NULL COMMENT '论文总数（篇）',
  `core_journals` int(255) NOT NULL COMMENT '核心期刊（篇）',
  `sci_index` int(255) NOT NULL COMMENT 'sci索引（篇）',
  `ei_index` int(255) NOT NULL COMMENT 'ei索引（篇）',
  `publication_monograph` int(255) NOT NULL COMMENT '出版专著（部）',
  `research` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '研究（咨询）',
  `report` int(255) NOT NULL COMMENT '报告（份）',
  `develop_technical_standards` int(11) NOT NULL COMMENT '制定技术标准（个）',
  `release_documents` int(11) NOT NULL COMMENT '出台文件（个）',
  `senior_talent` int(11) NOT NULL COMMENT '高级人才（人）',
  `national_outstanding_young_people` int(11) NOT NULL COMMENT '其中，国家杰出青年（长江学者、千人计划或其他）人数（分别列出）',
  `graduate_students_number` int(11) NOT NULL COMMENT '培养研究生人数',
  `participating_units` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '参加单位',
  `actual_progress_project` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目实际进度情况（包括经济、技术等指标完成情况，重点介绍课题进展、重大突破及工作成效等）（限300字以内）',
  `project_funds_usage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课题资金使用情况（包括资金落实、使用及存在问题）（限200字以内）',
  `recommendations_problems_solutions` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目执行过程中存在的问题、解决措施及有关建议（限200字以内）',
  `bear_contaacter` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '承担单位联系人',
  `bear_contaact_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '承担单位联系人电话',
  `mid_inspection_annex` int(11) NULL DEFAULT NULL COMMENT '中期检查附件id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mid_check
-- ----------------------------
INSERT INTO `mid_check` VALUES (1, 'subjectNo', 'subjectName', '2102-08-05', '2015-09-01', 'commitmentUnit', 'topicContact', 'subjectContactPhone', 'subjectLeader', 'leaderPhone', 41, 48, 58.59, 12.65, 45.64, 36.69, 89.46, 12.64, 25.68, 33.64, 20.36, 78.53, 15.64, 239.56, 45.64, 85.64, 85.25, 363.69, 45.21, 23.58, 78.69, 45.61, 32.13, 56.20, 32.25, 36.32, 45.65, 50, 32, 78, 63, 78, 'foreignPatents', 'utilityModel', 'design', 'foreignPatents', 36, 56, 32, 23, 71, 'research', 30, 74, 47, 789, 987, 3698, 'participatingUnits', 'actualProgressProject', 'projectFundsUsage', 'recommendationsProblemsSolutions', 'bearContaacter', 'bearContaactPhone', 20);
INSERT INTO `mid_check` VALUES (2, 'cgfhtft', 'rfgedrye', '2019-08-07', '2018-09-01', 'xfgver', 'fghrsthr', 'fgbshgsrt', 'rgegey', 'fghrfsh', 41, 49, 52346.00, 43453.00, 254854.00, 1241.00, 23439678.00, 342342.00, 25458.00, 21525.00, 4547.00, 15486.00, 453453.00, 15245.00, 3453.00, 4548548.00, 45486.00, 345.00, 453423.00, 563.00, 21541.00, 3564.00, 345.00, 21451.00, 23145.00, 12145.00, 356.00, 21, 121, 544, 45, 5458, 'fgedsgre', 'fgdgdsr', 'dgrfsre', 'fgdshtr', 541, 20, 42545, 48, 23, 'rfgeywy', 24154, 23, 4545, 45484, 15455, 743, 'fdgdg', 'htr5tyrwy', 'fdsgrteterr', 'frerte', 'dssggrewg', 'dswset', 10);

-- ----------------------------
-- Table structure for mid_check_record
-- ----------------------------
DROP TABLE IF EXISTS `mid_check_record`;
CREATE TABLE `mid_check_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid_check_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '中期检查名称',
  `mid_check_initinate_time` date NOT NULL COMMENT '中期检查发起时间',
  `mid_check_state` int(11) NOT NULL DEFAULT 0 COMMENT '中期检查发起状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mid_check_record
-- ----------------------------
INSERT INTO `mid_check_record` VALUES (1, 'xcfghtreth', '2019-08-12', 1);
INSERT INTO `mid_check_record` VALUES (2, 'frdgtewy', '2019-08-12', 0);
INSERT INTO `mid_check_record` VALUES (3, '风格风格', '2019-08-05', 0);
INSERT INTO `mid_check_record` VALUES (4, '2019zhonhgqilnxldns', '2019-08-04', 0);
INSERT INTO `mid_check_record` VALUES (5, 'fgtry6yye', '2019-05-02', 0);

-- ----------------------------
-- Table structure for next_work_plan
-- ----------------------------
DROP TABLE IF EXISTS `next_work_plan`;
CREATE TABLE `next_work_plan`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课题进展子表第五部分',
  `progress_id` int(255) NOT NULL COMMENT '课题进展id',
  `next_work_plan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '下一步工作计划',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of next_work_plan
-- ----------------------------
INSERT INTO `next_work_plan` VALUES (1, 1, 'stfrgdrertring');
INSERT INTO `next_work_plan` VALUES (2, 1, 'strfdrgeteing');

-- ----------------------------
-- Table structure for notification
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `start_time` date NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` date NULL DEFAULT NULL COMMENT '结束时间',
  `is_delete` int(20) NULL DEFAULT NULL COMMENT '0:启用  1：逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notification
-- ----------------------------
INSERT INTO `notification` VALUES (1, '禁止“洋垃圾”入境政策取得较大进展！2019年上半年我国进口固废同比减少26.99%\r\n文章链接：环保在线 ', '内容1', '1998-01-14 18:16:05', 'admin', '2000-02-01', '2111-05-01', 0);
INSERT INTO `notification` VALUES (2, '农村污水治理进展缓慢？这两地成效明显 治理经验值得借鉴', '内容1', '2019-07-02 18:16:24', 'admin', '1994-06-15', '2111-05-01', 0);
INSERT INTO `notification` VALUES (3, '全球近1/4人面临“极度缺水”危机！ 我们可以做什么', '内容12666', '2019-08-05 11:28:04', 'admin', '1221-04-08', '2019-10-01', 0);
INSERT INTO `notification` VALUES (4, '企业基础信息库、问题库已建立 长江“三磷”污染防治加速进行中\r\n企业基础信息库、问题库已建立  长江“三磷”污染防治加速进行中', '测试1111', '2019-08-18 16:44:19', 'admin', '2019-08-20', '2019-09-25', 0);
INSERT INTO `notification` VALUES (5, '我国地下水污染防治工作进展如何？ 生态环境部发言人给出答案', '测试1111', '2019-08-18 16:44:31', 'admin', '2019-08-08', '2019-10-01', 0);
INSERT INTO `notification` VALUES (6, '新研究表明，要研发以氮磷回收利用为导向的污水处理绿色技术', '333', '2019-08-18 17:07:24', 'admin', '2019-07-01', '2019-10-01', 0);
INSERT INTO `notification` VALUES (7, '2018年中国再生资源行业上游分析 废钢铁占再生资源回收量的五成以上', '333', '2019-08-18 17:07:24', 'admin', '2019-08-02', '2019-11-01', 0);
INSERT INTO `notification` VALUES (8, '2018年中国再生资源行业市场分析：市场规模接近3000亿，废钢占据回收量超五成', '333', '2019-08-18 17:07:24', 'admin', '2019-08-03', '2019-11-01', 0);
INSERT INTO `notification` VALUES (9, '2018年中国垃圾分类产业市场概况与发展趋势 垃圾回收上下游渠道有望进一步规范化', '333', '2019-08-18 17:07:24', 'admin', '2019-08-04', '2019-11-01', 0);
INSERT INTO `notification` VALUES (10, '2019年中国再生资源行业市场现状及发展前景分析 国家政策赋能助推行业转型升级', '333', '2019-08-18 17:07:24', 'admin', '2019-08-05', '2019-10-01', 0);
INSERT INTO `notification` VALUES (11, '2017年中国再生资源行业市场现状及发展前景分析 国家政策赋能助推行业转型升级', '333', '2019-08-18 17:07:24', 'admin', '2019-08-06', '2019-10-01', 0);

-- ----------------------------
-- Table structure for open_tender
-- ----------------------------
DROP TABLE IF EXISTS `open_tender`;
CREATE TABLE `open_tender`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '招标备案id',
  `project_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题编号',
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '项目名称',
  `tender_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标书标号',
  `subcontracting_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分包号',
  `subject_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题名称',
  `responsible_unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '责任单位',
  `bidders` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '投标人',
  `subject_leader` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题负责人',
  `leader_contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题负责人联系方式',
  `join_tender_units` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题联合投标单位（如有请填写,没有就填无）',
  `operator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '经办人',
  `operator_contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '经办人联系方式',
  `winning_amount` decimal(10, 2) NOT NULL COMMENT '中标（成交）金额',
  `supporting_funds` decimal(10, 2) NOT NULL COMMENT '配套经费',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `audit_status` int(11) NULL DEFAULT 1 COMMENT '招标备案审批状态(0-单位员工待提交 1-单位管理员待审批 2-评估中心员工待审批 3-评估中心员工已审批)',
  `winning_file_attachment_id` int(11) NULL DEFAULT 0 COMMENT '中标文件附件id',
  `announcement_transaction_announcement_id` int(11) NULL DEFAULT 0 COMMENT '成交公告附件id',
  `deal_notification_attachment_id` int(11) NULL DEFAULT 0 COMMENT '成交通知附件id',
  `response_file_attachment_id` int(11) NULL DEFAULT 0 COMMENT '响应文件附件id',
  `other_attachments_id` int(11) NULL DEFAULT NULL COMMENT '其他附件id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of open_tender
-- ----------------------------
INSERT INTO `open_tender` VALUES (1, 'xdmd20190001', 'dftgry', 'dfgre5y', 'dfhftreu', '6fght6r7', 'vdgderg', 'fghter67', 'fth6567', 'tyu4', '68tyute467i', 'fthre6u8', 'hftut', 45.56, 25.35, '', 1, 273, 274, 275, 276, 277);
INSERT INTO `open_tender` VALUES (2, 'xdmd20190002', 'kong', 'fgdsartw', 'drgews5y', 'drg5yw', 'fcgd', 'dfgtr5y', 'fthrew5yw', 'fdtyrwe7y5', 'drytrw75', 'tr75', 'ftyre76534', 12.56, 45.36, '', 2, 278, 279, 280, 281, 282);
INSERT INTO `open_tender` VALUES (3, 'xdmd20190003', 'dfgdyg', 'xdfsgrthds', 'xdgsarg', 'drswry', 'vdgdergfgdtge', 'rgtwret', 'seagqt', 'sdrgwq4et', 'sgarrtwq', 'dgawrtw', 'dfsat', 25.69, 58.69, '', 2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (4, 'xdmd20190004', 'kong', 'dfgre5y', 'dfhftreu', '6fght6r7', 'vdgdergdfsf', 'fghter67', 'fth6567', 'tyu4', '68tyute467i', 'fthre6u8', 'hftut', 45.56, 25.35, '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (5, 'xdmd20190005', 'kong', 'fgdsartw', 'drgews5y', 'drg5yw', 'vdgderg', 'dfgtr5y', 'fthrew5yw', 'fdtyrwe7y5', 'drytrw75', 'tr75', 'ftyre76534', 12.56, 45.36, '', 2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (6, 'xdmd20190006', 'kong', 'fgdsartw', 'drgews5y', 'drg5yw', 'vdgderg', 'dfgtr5y', 'lmp520', 'fdtyrwe7y5', 'drytrw75', 'tr75', 'ftyre76534', 12.56, 45.36, '', 2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (7, 'xdmd20190007', 'kong', 'fgdsartw', 'drgews5y', 'drg5yw', 'vdgderg', 'dfgtr5y', 'fthrew5yw', 'fdtyrwe7y5', 'drytrw75', 'tr75', 'ftyre76534', 12.56, 45.36, '', 2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (8, 'xdmd20190008', 'trhe6u775', 'yh676u64', 'gyhjtr7i6', 'yr67u6', 'vdgderg', 'yjr676', 'yjr7u6', 'jt76u', 'yjtu6t', 'yrt6u', '54987938', 24.35, 45.21, '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (9, 'xdmd20190009', 'komgfh', 'fbstr', 'gbftrwe', 'fgbrter', 'vdgderg', 'fgrft', 'dfbtr', '144453635', 'gbfte', 'saer', '254896', 12.35, 45.45, '', 2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (10, 'xdmd201900010', 'kong', 'ffdthr', 'ghrfyjt', 'ghtrj', 'vdgderg', 'ghtru7t', 'yghntr7u', '4514889', '无', 'gfhtryj', 'ghntyr7i', 25.34, 85.26, '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (11, 'xdmd20190011', 'qwertyuiop', 'fbdsrt', 'gvsfu', 'vgnfety', 'vdgderg', 'gftuey', 'bgfsdthe', '3546754543', '无', 'dgtrhu', '453459', 12.35, 45.68, '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (12, 'xdmd20190012', 'rgdryg5y', 'drfg5', 'fdhrttty5', 'hfgtr3y5', 'vdgderg', 'fhgry6', '5ydtrhrr3w', '54365456', 'wu', 'fgrt5', '4254567', 45.66, 45.58, '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (13, 'xdmd20190013', 'rty6u67', 'rgt', '4dty6', 'trhr567', 'vdgderg', 'yt67iity', 'thyi67578i', '2134986', 'wu', 'drgtyu', '1537896859', 415.65, 14.58, '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (14, 'xdmd20190014', 'rty6u67', 'rgt', '4dty6', 'trhr567', 'vdgderg', 'yt67iity', 'thyi67578i', '2134986', 'wu', 'drgtyu', '1537896859', 415.65, 14.58, '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (15, 'xdmd250190016', 'dsgerte', 'dfrgr', 'jbjhbhj', 'bhjbj', 'vdgderg', 'bhjmbj', 'jhbjh', 'jbvj', 'wu', 'bjhbhj', '6315647', 125.36, 4475.25, '', 3, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (17, 'xdmd20190017', '520lmp', 'rgt', '4dty6', 'trhr567', 'vdgderg', 'yt67iity', 'thyi67578i', '2134986', 'wu', 'drgtyu', '1537896859', 415.65, 14.58, '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (18, 'xdmd20190018', '520lmp', 'rgt', '4dty6', 'trhr567', 'vdgderg', 'yt67iity', 'thyi67578i', '2134986', 'wu', 'drgtyu', '1537896859', 415.65, 14.58, '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (19, 'xdmd20190019', 'kong520lmp', 'xfgdytr', 'fdghdtrt5y', 'fry54', 'vdgderg', 'dfbryt35w6', 'fbgrshtwr', 'fbsdrtwhy', '15346535498', 'vdfre', '254687954987', 25.36, 58.65, '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (20, 'xdmd20190020', 'kong520lmp', 'xfgdytr', 'fdghdtrt5y', 'fry54', 'vdgderg', 'dfbryt35w6', 'fbgrshtwr', 'fbsdrtwhy', '15346535498', 'vdfre', '254687954987', 25.36, 58.65, '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (21, 'xdmd20190021', 'Lee_ning', 'xfgdytr', 'fdghdtrt5y', 'fry54', 'vdgderg', 'dfbryt35w6', 'fbgrshtwr', 'fbsdrtwhy', '15346535498', 'vdfre', '254687954987', 25.36, 58.65, '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (22, 'xdmd20190022', 'qwertyuiop', 'xfgdytr', 'fdghdtrt5y', 'fry54', 'vdgderg', 'dfbryt35w6', 'fbgrshtwr', 'fbsdrtwhy', '15346535498', 'vdfre', '254687954987', 25.36, 58.65, '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (23, 'xdmd20190023', 'qwe123', 'asd123', 'zxc456', 'asd456', 'vdgderg', 'qwe741', 'dfsre1564', '15684796189', '无', 'fbgy', '153459456', 45.58, 89.36, '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (24, 'xdmd20190024', '实在是地方', 'asd123', 'zxc456', 'asd456', 'vdgderg', 'qwe741', 'dfsre1564', '15684796189', '无', 'fbgy', '153459456', 45.58, 89.36, '', 2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (25, 'xdmd20190025', 'qwe123', 'asd123', 'zxc456', 'asd456', 'vdgderg', 'qwe741', 'dfsre1564', '15684796189', '无', 'fbgy', '153459456', 45.58, 89.36, '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (26, 'xdmd20190026', '大体符合人体有', 'asd123', 'zxc456', 'asd456', 'vdgderg', 'qwe741', 'dfsre1564', '15684796189', '无', 'fbgy', '153459456', 45.58, 89.36, '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (27, 'xdmd20190027', '风格和try', 'asd123', 'zxc456', 'asd456', 'vdgderg', 'qwe741', 'dfsre1564', '15684796189', '无', 'fbgy', '153459456', 45.58, 89.36, '', 2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (28, 'xdmd20190028', '计划v不定积分', '防爆手电筒', '防不胜防', '编程风格', 'vdgderg', '反对', '法师', '43543146', '无', '部分更换电脑', '5411543934', 12.35, 45.68, '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (29, 'xdmd20190029', 'strdfsefing', 'sdfsfes', 'stdsfsefring', 'stdsfsering', 'strsdfsfeing', 'dfsfse', 'strsdfseing', 'fse', 'sdfsef', 'stsdfsfering', 'strsdfsefsing', 100.00, 200.00, 'strfseftsedfing', 3, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (30, 'xdmd20190030', 'strdfsefing', 'sdfsfes', 'stdsfsefring', 'stdsfsering', 'strsdfsfeing', 'dfsfse', 'strsdfseing', 'fse', 'sdfsef', 'stsdfsfering', 'strsdfsefsing', 100.00, 200.00, 'strfseftsedfing', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `open_tender` VALUES (31, 'xdmd20190031', 'sfew4', 'sewr54rt', 'sefw4t', 'serw4r5t', 'sdefse4', 'rdtgewy', 'sefwe4rt', 'sefse3', 'efsew', 'sefwe', 'sef3w', 25.00, 23.00, 'sdfswe4w', 1, 0, 0, 0, 0, NULL);
INSERT INTO `open_tender` VALUES (32, 'xdmd20190032', 'strdfsefing', 'sdfsfes', 'stdsfsefring', 'stdsfsering', 'strsdfsfeing', 'dfsfse', 'strsdfseing', 'fse', 'sdfsef', 'stsdfsfering', 'strsdfsefsing', 100.00, 200.00, 'strfseftsedfing', 3, 0, 0, 0, 0, NULL);
INSERT INTO `open_tender` VALUES (33, 'xdmd20190033', 'strdfsefing', 'sdfsfes', 'stdsfsefring', 'stdsfsering', 'strsdfsfeing', 'dfsfse', 'strsdfseing', 'fse', 'sdfsef', 'stsdfsfering', 'strsdfsefsing', 100.00, 200.00, 'strfseftsedfing', 1, 0, 0, 0, 0, NULL);
INSERT INTO `open_tender` VALUES (34, 'xdmd20190034', '1', '2', '3', '4', '14', '5', '8', '9', '10', '11', '12', 6.00, 7.00, '13', 3, 0, 0, 0, 0, NULL);
INSERT INTO `open_tender` VALUES (35, 'xdmd20190035', '1', '2', '3', '4', '14', '5', '8', '9', '10', '11', '12', 6.00, 7.00, '13', 1, 0, 0, 0, 0, NULL);
INSERT INTO `open_tender` VALUES (36, 'xdmd20190036', '1', '2', '3', '4', '14', '5', '8', '9', '10', '11', '12', 6.00, 7.00, '13', 1, 0, 0, 0, 0, NULL);
INSERT INTO `open_tender` VALUES (37, 'xdmd20190037', 'xdmd2019END', 'xdmd2019END', 'xdmd2019END', 'xdmd2019END', 'xdmd2019END', 'xdmd2019END', 'xdmd2019END', 'xdmd2019END', 'xdmd2019END', 'xdmd2019END', 'xdmd2019END', 12345678.00, 12345678.00, 'xdmd2019END', 3, 0, 0, 0, 0, NULL);
INSERT INTO `open_tender` VALUES (38, 'xdmd20190038', '1', '1', '1', '1', '1', '1', '1', '13777777777', '1', '1', '13777777777', 1.00, 1.00, '1', 1, 0, 0, 0, 0, NULL);
INSERT INTO `open_tender` VALUES (39, 'xdmd20190039', '22', '22', '22', '22', '22', '2', '22', '13888888888', '22', '22', '13888888888', 22.00, 22.00, '22', 1, 0, 0, 0, 0, NULL);
INSERT INTO `open_tender` VALUES (40, 'xdmd20190040', '33', '33', '33', '33', '33', '33', '33', '13888888888', '33', '33', '13888888888', 33.00, 33.00, '13888888888', 3, 0, 0, 0, 0, NULL);
INSERT INTO `open_tender` VALUES (41, 'xdmd20190041', '33', '33', '33', '33', '33', '33', '33', '13888888888', '33', '33', '13888888888', 33.00, 33.00, '13888888888', 1, 0, 0, 0, 0, NULL);
INSERT INTO `open_tender` VALUES (42, 'xdmd20190042', '122', '122', '12', '1212', '1212', '12', '21', '13888888888', '21', '212', '13888888888', 121.00, 12.00, '1231', 1, 0, 0, 0, 0, NULL);
INSERT INTO `open_tender` VALUES (43, 'xdmd20190043', '111222', '11', '11', '1', '11', '11', '11', '13777777777', '11', '11', '13777777777', 11.00, 1.00, '111', 3, 0, 0, 0, 0, NULL);
INSERT INTO `open_tender` VALUES (44, 'xdmd20190044', '测试1', '测试1', '测试1', '测试1', '测试1', '测试1', '测试1', '13888888888', '测试1', '测试1', '13777777777', 123.00, 123.00, '测试1', 1, 0, 0, 0, 0, NULL);
INSERT INTO `open_tender` VALUES (45, 'xdmd20190045', '测试2', '测试2', '测试2', '测试2', '测试2', '测试2', '测试2', '13222222222', '测试2', '测试2', '13775899999', 123.00, 234.00, '测试2测试2', 1, 0, 0, 0, 0, NULL);
INSERT INTO `open_tender` VALUES (46, 'xdmd20190046', '测试3', '测试3', '测试3', '测试3', '测试3', '测试3', '测试3', '13777777777', '测试3', '测试3', '13222222222', 222.00, 333.00, '测试3测试3测试3测试3', 1, 0, 0, 0, 0, NULL);
INSERT INTO `open_tender` VALUES (47, 'xdmd20190047', '测试4', '测试4', '测试4', '测试4', '测试4', '测试4', '测试4', '13777777777', '测试4', '测试4', '13555555555', 222.00, 999.00, '测试4测试4测试4', 1, 0, 0, 0, 0, NULL);
INSERT INTO `open_tender` VALUES (48, 'xdmd20190048', '测试5', '测试5', '测试5', '测试5', '测试5', '测试5', '测试5', '15555555555', '测试5', '测试5', '15555555555', 555.00, 555.00, '测试5测试5测试5', 1, 0, 0, 0, 0, NULL);
INSERT INTO `open_tender` VALUES (49, 'xdmd20190049', '测试6', '测试6', '测试6', '测试6', '测试6', '测试6', '测试6', '16666666666', '测试6', '测试6', '16666666666', 666.00, 666.00, '测试6测试6测试6测试6', 1, 0, 0, 0, 0, NULL);
INSERT INTO `open_tender` VALUES (50, 'xdmd20190050', '测试7', '测试7', '测试7测试7', '测试7', '测试7', '测试7', '测试7', '17777777777', '测试7', '测试7', '17777777777', 777.00, 777.00, '测试7测试7测试7', 1, 0, 0, 0, 0, NULL);
INSERT INTO `open_tender` VALUES (51, 'xdmd20190051', '测试7', '测试7', '测试7测试7', '测试7', '测试7', '测试7', '测试7', '17777777777', '测试7', '测试7', '17777777777', 777.00, 777.00, '测试7测试7测试7', 1, 283, 284, 285, 286, 287);
INSERT INTO `open_tender` VALUES (52, 'xdmd20190052', '测试1', '测试1', '测试1', '测试1', '测试1', '测试1', '测试1', '13775888888', '测试1', '测试1', '15111111111', 11.00, 12.00, '测试1测试1测试1测试1', 1, 288, 289, 290, 291, 292);

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
  `correspondence_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通讯邮编',
  `achievement_start_time` date NOT NULL COMMENT '成果开始时间',
  `economic_performance` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用情况、社会经济效益（含计算过程）',
  `achievement_url_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '成果信息附件url的id',
  `achievement_end_time` date NOT NULL COMMENT '成果结束时间',
  `create_author` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `check_apply_id` int(11) NULL DEFAULT NULL COMMENT '对应验收申请表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for outcome_information_paper
-- ----------------------------
DROP TABLE IF EXISTS `outcome_information_paper`;
CREATE TABLE `outcome_information_paper`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `achievements_id` int(11) NOT NULL COMMENT '成果信息id',
  `serial_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '序号',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `publication` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '刊物',
  `publication_time` date NOT NULL COMMENT '发表时间',
  `author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `paper_level` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '论文级别（SCI/EI/核心等）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for outcome_information_patent
-- ----------------------------
DROP TABLE IF EXISTS `outcome_information_patent`;
CREATE TABLE `outcome_information_patent`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `achievements_id` int(11) NOT NULL COMMENT '成果信息id',
  `serial_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '序号',
  `outcome_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '成果名称',
  `patent_application_time` date NOT NULL COMMENT '专利申请时间',
  `patent_application_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专利申请号/专利号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_main_problems
-- ----------------------------
DROP TABLE IF EXISTS `project_main_problems`;
CREATE TABLE `project_main_problems`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课题进展第三部分',
  `progress_id` int(11) NOT NULL COMMENT '课题进展id',
  `main_problems` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题实施中存在的主要问题（研究遇到的困难及技术、方案、人员等变动情况)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_main_problems
-- ----------------------------
INSERT INTO `project_main_problems` VALUES (1, 3, 'stdsfsdesdsring');
INSERT INTO `project_main_problems` VALUES (2, 3, 'fbcfdd');

-- ----------------------------
-- Table structure for project_progress
-- ----------------------------
DROP TABLE IF EXISTS `project_progress`;
CREATE TABLE `project_progress`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课题进展情况主表id',
  `bearer_unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '承担单位',
  `fill_time` date NOT NULL COMMENT '填报日期',
  `subject_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课题名称',
  `project_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课题编号',
  `project_leader` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目负责人',
  `project_leader_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目负责人电话',
  `primary_contacts` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主要联系人',
  `primary_contacts_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主要联系人电话',
  `progress` int(11) NOT NULL COMMENT '进展情况类型/45-超前 46-正常 47-滞后',
  `progress_completed_percentage` double(10, 0) NOT NULL COMMENT '目前进展情况完成百分比',
  `total_funds_inplace` decimal(10, 2) NOT NULL COMMENT '已到位课题总经费（万元）',
  `project_funds_used` decimal(10, 2) NOT NULL COMMENT '已使用课题经费（万元）',
  `total_funding` double(10, 2) NOT NULL COMMENT '占总经费%',
  `provincial_environmental_funds_used` decimal(10, 2) NOT NULL COMMENT '已使用省环保课题经费（万元）',
  `provincial_environmental_funds_percent` double(10, 2) NOT NULL COMMENT '占省环保课题经费%',
  `contract_agreed_closing_time` date NOT NULL COMMENT '合同约定结题时间',
  `is_complate_contract` int(2) NOT NULL COMMENT '能否按合同约定时间完成课题，分为：0-能、1-不能',
  `estimated_acceptance_time` date NOT NULL COMMENT '预计申请课题验收时间',
  `unit_audit_comments` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '单位审核意见',
  `open_report_annex_id` int(11) NULL DEFAULT NULL COMMENT '开题报告附件id',
  `expert_suggest_annex_id` int(11) NULL DEFAULT NULL COMMENT '专家意见附件id',
  `subject_progress_annex_id` int(11) NULL DEFAULT NULL COMMENT '课题进展附件id',
  `fund_progress_annex_id` int(11) NULL DEFAULT NULL COMMENT '进度情况经费使用情况附件id',
  `commit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '进展提交时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of project_progress
-- ----------------------------
INSERT INTO `project_progress` VALUES (1, 'qwe', '2019-08-07', 'zxc', 'zaq', 'dfgdr', 'dfrtgertge', 'dfgdtrg', 'fgrdthrw', 45, 34, 3453.00, 3432.00, 4653.00, 3243.00, 242.00, '2018-08-05', 0, '2018-09-02', 'grdege', NULL, NULL, NULL, NULL, '2019-08-26 16:30:11');
INSERT INTO `project_progress` VALUES (2, 'asd', '2019-08-02', 'asd', 'wsx', 'dfgdr', 'dfrtgertge', 'dfgdtrg', 'fgrdthrw', 46, 34, 3453.00, 3432.00, 4653.00, 3243.00, 242.00, '2018-08-05', 0, '2018-09-02', 'grdege', NULL, NULL, NULL, NULL, '2019-08-26 16:30:14');
INSERT INTO `project_progress` VALUES (3, 'zxc', '2019-08-01', 'qwe', 'cde', 'dfgdr', 'dfrtgertge', 'dfgdtrg', 'fgrdthrw', 47, 34, 3453.00, 3432.00, 4653.00, 3243.00, 242.00, '2018-08-05', 0, '2018-09-02', 'grdege', NULL, NULL, NULL, NULL, '2019-08-26 16:30:17');
INSERT INTO `project_progress` VALUES (7, 'dfgrdrtges', '2019-08-10', 'fgdsfh', 'bfsgtwsh', 'ghfdyjedt', 'fgtrht6w', 'dfrrgase', 'cgfbsdthw', 45, 24, 35.00, 1254.00, 45.00, 45.00, 23.00, '2015-09-04', 1, '2017-09-04', 'dxrgfeyw', NULL, NULL, NULL, NULL, '2019-08-26 16:30:24');
INSERT INTO `project_progress` VALUES (8, 'bearerUnit', '2015-09-05', 'subjectName', 'projectNo', 'projectLeader', 'projectLeaderPhone', 'primaryContacts', 'primaryContactsPhone', 46, 20, 30.00, 40.00, 70.00, 80.00, 20.00, '2017-09-08', 1, '2014-07-04', 'progress', NULL, NULL, NULL, NULL, '2019-08-26 16:57:18');
INSERT INTO `project_progress` VALUES (9, 'qwe', '2017-09-02', 'tryr', 'dfsfw', 'sad', 'zdxcf', 'wqeq', 'asdada', 45, 20, 45.00, 23.00, 56.00, 23.00, 12.00, '2018-09-04', 1, '2015-08-01', 'cghfh', NULL, NULL, NULL, NULL, '2019-08-26 16:59:04');
INSERT INTO `project_progress` VALUES (10, 'asd', '2017-09-02', 'tryr', 'dfsfw', 'sad', 'zdxcf', 'wqeq', 'asdada', 45, 20, 45.00, 23.00, 56.00, 23.00, 12.00, '2018-09-04', 1, '2015-08-01', 'cghfh', NULL, NULL, NULL, NULL, '2019-08-26 17:09:27');
INSERT INTO `project_progress` VALUES (11, '1', '2019-08-26', '17', '18', '13', '14', '8', '9', 45, 28, 21.00, 12.00, 20.00, 16.00, 15.00, '2019-08-19', 0, '2019-08-06', '22', NULL, NULL, NULL, NULL, '2019-08-26 17:18:13');
INSERT INTO `project_progress` VALUES (12, '1', '2019-08-13', '17', '18', '13', '14', '8', '9', 45, 28, 21.00, 12.00, 20.00, 16.00, 15.00, '2019-08-20', 0, '2019-08-13', '22', NULL, NULL, NULL, NULL, '2019-08-26 17:45:56');

-- ----------------------------
-- Table structure for shiro_company_name
-- ----------------------------
DROP TABLE IF EXISTS `shiro_company_name`;
CREATE TABLE `shiro_company_name`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公司id',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `shiro_company_name` VALUES (16, '公司名称1221');
INSERT INTO `shiro_company_name` VALUES (18, '4');
INSERT INTO `shiro_company_name` VALUES (19, '12');

-- ----------------------------
-- Table structure for shiro_permission
-- ----------------------------
DROP TABLE IF EXISTS `shiro_permission`;
CREATE TABLE `shiro_permission`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `explain` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限的解释',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shiro_permission
-- ----------------------------
INSERT INTO `shiro_permission` VALUES (1, 'add', NULL);
INSERT INTO `shiro_permission` VALUES (2, 'delete', NULL);
INSERT INTO `shiro_permission` VALUES (3, 'edit', NULL);
INSERT INTO `shiro_permission` VALUES (4, 'query', NULL);
INSERT INTO `shiro_permission` VALUES (5, 'backstageManagerChangePassword', '后台管理中修改自己的密码(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (6, 'backstageManagerAddSubaccount', '公司管理员分配子账号(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (7, 'backstageManagerQueryStaff', '查询本单位的所有账号(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (8, 'backstageManagerChangeState', '改变账号状态 启用或停用(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (9, 'backstageManagerModifyStaff', '对员工信息进行修改(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (10, 'companyRegister', '公司的注册(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (11, 'companyLogin', '公司的登陆(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (12, 'fileQueryFileStream', '对文件进行下载(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (13, 'extranetExpertRegister', '外网专家注册(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (14, 'extranetExpertLogin', '外网专家的登陆(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (15, 'applyAddAcceptApply', '员工填写验收申请表(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (16, 'applyCheckApplyStateQuery', '公司管理员进行查询 查询的内容是审核过程中的内容(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (17, 'applyExamine', '管理员审核(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (18, 'applyQueryResult', '查询最终结果(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (19, 'applySubmitLastReport', '提交最终验收报告(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (20, 'applySubmitExpertGroup', '上传专家组意见信息与专家组意见文件，专家组评议表文件(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (21, 'applyModify', '在提交验收申请后，审核之前进行修改(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (22, 'applyExpertGroupModify', '对专家组信息，专家组，专家组评议表文件进行修改上传(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (23, 'applyLastReportModify', '对最终证书文件与信息 修改(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (24, 'applyQueryTopicName', '外网在做课题申请时，先查询可添加的课题(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (25, 'applyQueryTopicNumber', '通过课题名称，获取课题编号(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (26, 'applyQueryInformationByTopicNumber', '验收申请中，根据课题编号获取信息(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (27, 'notificationExtranetQuery', '通知公告的查询(外网 zydl)');
INSERT INTO `shiro_permission` VALUES (28, 'achievementQueryAchievement', '成果库中的成果查询(内网 zydl)');
INSERT INTO `shiro_permission` VALUES (29, 'achievementAddAchievementQuery', '成果新增时查询(内网 zydl)');
INSERT INTO `shiro_permission` VALUES (30, 'achievementAddAchievement', '成果新增的提交(内网 zydl)');
INSERT INTO `shiro_permission` VALUES (31, 'achievementQueryAllCheckApply', '成果新增时，查询验收申请的所有信息(内网 zydl)');
INSERT INTO `shiro_permission` VALUES (32, 'expertDistributionExpertAccount', '内网专家分配账号(内网 zydl)');
INSERT INTO `shiro_permission` VALUES (33, 'expertQuery', '专家的查询(内网 zydl)');
INSERT INTO `shiro_permission` VALUES (34, 'expertExamine', '专家账号的审核(内网 zydl)');
INSERT INTO `shiro_permission` VALUES (35, 'expertModify', '专家表的修改(内网 zydl)');
INSERT INTO `shiro_permission` VALUES (36, 'expertChangeState', '专家账号的审核与停用(内网 zydl)');
INSERT INTO `shiro_permission` VALUES (37, 'checkApplyQuery', '查询企业提交的验收申请(内网 zydl)');
INSERT INTO `shiro_permission` VALUES (38, 'subjectAcceptQuery', '课题验收的查询(内网 zydl)');
INSERT INTO `shiro_permission` VALUES (39, 'subjectAcceptExamine', '课题验收的审核(内网 zydl)');
INSERT INTO `shiro_permission` VALUES (40, 'acceptStateQuery', '验收申请的查询(内网 zydl)');
INSERT INTO `shiro_permission` VALUES (41, 'acceptStateExamine', '验收申请的审核(内网 zydl)');
INSERT INTO `shiro_permission` VALUES (42, 'AcceptEndQuery', '验收结束的查询(内网 zydl)');
INSERT INTO `shiro_permission` VALUES (43, 'AcceptEndExamine', '验收结束的审核(内网 zydl)');
INSERT INTO `shiro_permission` VALUES (44, 'notificationAdd', '通知公告的新增(内网 zydl)');
INSERT INTO `shiro_permission` VALUES (45, 'notificationDelete', '通知公告的删除(内网 zydl)');
INSERT INTO `shiro_permission` VALUES (46, 'notificationUpdate', '通知公告的修改(内网 zydl)');
INSERT INTO `shiro_permission` VALUES (47, 'notificationQuery', '通知公告的查询(内网 zydl)');

-- ----------------------------
-- Table structure for shiro_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `shiro_permission_role`;
CREATE TABLE `shiro_permission_role`  (
  `rid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  PRIMARY KEY (`rid`, `pid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shiro_permission_role
-- ----------------------------
INSERT INTO `shiro_permission_role` VALUES (1, 5);
INSERT INTO `shiro_permission_role` VALUES (1, 6);
INSERT INTO `shiro_permission_role` VALUES (1, 7);
INSERT INTO `shiro_permission_role` VALUES (1, 8);
INSERT INTO `shiro_permission_role` VALUES (1, 9);
INSERT INTO `shiro_permission_role` VALUES (1, 16);
INSERT INTO `shiro_permission_role` VALUES (1, 17);
INSERT INTO `shiro_permission_role` VALUES (1, 18);
INSERT INTO `shiro_permission_role` VALUES (1, 27);
INSERT INTO `shiro_permission_role` VALUES (2, 5);
INSERT INTO `shiro_permission_role` VALUES (2, 15);
INSERT INTO `shiro_permission_role` VALUES (2, 16);
INSERT INTO `shiro_permission_role` VALUES (2, 18);
INSERT INTO `shiro_permission_role` VALUES (2, 19);
INSERT INTO `shiro_permission_role` VALUES (2, 20);
INSERT INTO `shiro_permission_role` VALUES (2, 21);
INSERT INTO `shiro_permission_role` VALUES (2, 22);
INSERT INTO `shiro_permission_role` VALUES (2, 23);
INSERT INTO `shiro_permission_role` VALUES (2, 24);
INSERT INTO `shiro_permission_role` VALUES (2, 25);
INSERT INTO `shiro_permission_role` VALUES (2, 26);
INSERT INTO `shiro_permission_role` VALUES (2, 27);
INSERT INTO `shiro_permission_role` VALUES (3, 5);
INSERT INTO `shiro_permission_role` VALUES (3, 27);
INSERT INTO `shiro_permission_role` VALUES (4, 28);
INSERT INTO `shiro_permission_role` VALUES (4, 29);
INSERT INTO `shiro_permission_role` VALUES (4, 30);
INSERT INTO `shiro_permission_role` VALUES (4, 31);
INSERT INTO `shiro_permission_role` VALUES (4, 32);
INSERT INTO `shiro_permission_role` VALUES (4, 33);
INSERT INTO `shiro_permission_role` VALUES (4, 34);
INSERT INTO `shiro_permission_role` VALUES (4, 35);
INSERT INTO `shiro_permission_role` VALUES (4, 36);
INSERT INTO `shiro_permission_role` VALUES (4, 37);
INSERT INTO `shiro_permission_role` VALUES (4, 38);
INSERT INTO `shiro_permission_role` VALUES (4, 39);
INSERT INTO `shiro_permission_role` VALUES (4, 40);
INSERT INTO `shiro_permission_role` VALUES (4, 41);
INSERT INTO `shiro_permission_role` VALUES (4, 42);
INSERT INTO `shiro_permission_role` VALUES (4, 43);
INSERT INTO `shiro_permission_role` VALUES (4, 44);
INSERT INTO `shiro_permission_role` VALUES (4, 45);
INSERT INTO `shiro_permission_role` VALUES (4, 46);
INSERT INTO `shiro_permission_role` VALUES (4, 47);
INSERT INTO `shiro_permission_role` VALUES (5, 28);
INSERT INTO `shiro_permission_role` VALUES (5, 29);
INSERT INTO `shiro_permission_role` VALUES (5, 31);
INSERT INTO `shiro_permission_role` VALUES (5, 32);
INSERT INTO `shiro_permission_role` VALUES (5, 33);
INSERT INTO `shiro_permission_role` VALUES (5, 37);
INSERT INTO `shiro_permission_role` VALUES (5, 38);
INSERT INTO `shiro_permission_role` VALUES (5, 40);
INSERT INTO `shiro_permission_role` VALUES (5, 42);
INSERT INTO `shiro_permission_role` VALUES (5, 47);
INSERT INTO `shiro_permission_role` VALUES (6, 32);

-- ----------------------------
-- Table structure for shiro_role
-- ----------------------------
DROP TABLE IF EXISTS `shiro_role`;
CREATE TABLE `shiro_role`  (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shiro_role
-- ----------------------------
INSERT INTO `shiro_role` VALUES (1, 'administrator');
INSERT INTO `shiro_role` VALUES (2, 'staff');
INSERT INTO `shiro_role` VALUES (3, 'expert');
INSERT INTO `shiro_role` VALUES (4, 'sectionChief');
INSERT INTO `shiro_role` VALUES (5, 'clerk');
INSERT INTO `shiro_role` VALUES (6, 'technologyDepartment');

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
-- Table structure for shiro_user_information
-- ----------------------------
DROP TABLE IF EXISTS `shiro_user_information`;
CREATE TABLE `shiro_user_information`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `real_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `login_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `identity` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份( 0：管理员 1：员工 2：专家 3: 科长 4：科员 5：法规科技处)',
  `is_delete` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否启用（0：启用 1：停用）\r\n当管理员注册，管理员给分配子账号，内网分配专家账号时，默认为启用',
  `is_first` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否第一次登陆（0：是第一次登陆  1：多次登陆）',
  `is_state` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否审核通过  （1：审核通过 2：等待审核  3：审核未通过）\r\n内网分配账号时，默认为审核通过\r\n只有当外网注册专家时，才需要内网进行审核',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shiro_user_information
-- ----------------------------
INSERT INTO `shiro_user_information` VALUES (1, '真实姓名', '登陆名', '21232f297a57a5a743894a0e4a801fc3', '0', '0', '1', '1');
INSERT INTO `shiro_user_information` VALUES (2, '真实姓名', '登陆名11', '0fe359d5924e09441ad054236bd47528', '0', '0', '1', '1');
INSERT INTO `shiro_user_information` VALUES (5, '真实姓名', '登陆名1221', '0fe359d5924e09441ad054236bd47528', '0', '0', '1', '1');
INSERT INTO `shiro_user_information` VALUES (6, '真实专家姓名', '登陆名656565', '0fe359d5924e09441ad054236bd47528', '2', '0', '1', '3');
INSERT INTO `shiro_user_information` VALUES (7, '真实专家姓名', '登陆名656323565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '1', '1', '1');
INSERT INTO `shiro_user_information` VALUES (9, '真实专家姓名', '登陆名6563fsgsdf23565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '1', '1', '1');
INSERT INTO `shiro_user_information` VALUES (11, '真实专家姓名', '6767登陆名6563fsgsdf23565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '1', '1', '1');
INSERT INTO `shiro_user_information` VALUES (12, '真实姓名', '登录名112', '0fe359d5924e09441ad054236bd47528', '2', '0', '0', '1');
INSERT INTO `shiro_user_information` VALUES (14, '真实姓名', '登录名1dfd1', '登录名1dfd1@123', '2', '0', '0', '1');
INSERT INTO `shiro_user_information` VALUES (15, '真实姓名', '登录名1dasdffd1', '登录名1dasdffd1@123', '2', '0', '0', '1');
INSERT INTO `shiro_user_information` VALUES (16, '真实姓名121', 'zhangsan1212', 'zhangsan1212@123', '1', '1', '0', '1');
INSERT INTO `shiro_user_information` VALUES (18, '真实姓名', '登陆名1221222', '0fe359d5924e09441ad054236bd47528', '0', '0', '1', '1');
INSERT INTO `shiro_user_information` VALUES (25, '真实专家姓名1', '11116767登陆名6563fsgsdf23565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '1', '1', '2');
INSERT INTO `shiro_user_information` VALUES (26, '2真实专家姓名', '2222211116767登陆名6563fsgsdf23565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '1', '1', '2');
INSERT INTO `shiro_user_information` VALUES (27, '真实专家姓名3', '4333332222211116767登陆名6563fsgsdf23565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '1', '1', '2');
INSERT INTO `shiro_user_information` VALUES (28, '真实专家姓名4', '43hfir7登陆名6563fsgsdf23565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '1', '1', '1');
INSERT INTO `shiro_user_information` VALUES (29, '真实专家姓名5', 'fdfdfd43hfir7登陆名6563fsgsdf23565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '1', '1', '3');
INSERT INTO `shiro_user_information` VALUES (31, '真实专家姓名6', 'fdfdfd登陆名6563fsgsdf23565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '1', '1', '1');
INSERT INTO `shiro_user_information` VALUES (32, '真实专家姓名7', 'erueirefd登陆名6563fsgsdf23565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '0', '1', '1');
INSERT INTO `shiro_user_information` VALUES (33, '真实专家姓名8', 'f5tg5t登陆名6563fsgsdf23565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '0', '0', '1');
INSERT INTO `shiro_user_information` VALUES (34, '真实专家姓名9', 'fdf3f3ff', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '0', '0', '1');
INSERT INTO `shiro_user_information` VALUES (57, '张三', '12', 'e4da3b7fbbce2345d7772b0674a318d5', '0', '0', '1', '1');
INSERT INTO `shiro_user_information` VALUES (58, '李四', '1', 'e4da3b7fbbce2345d7772b0674a318d5', '1', '0', '1', '1');
INSERT INTO `shiro_user_information` VALUES (59, '我', 'wch', 'e10adc3949ba59abbe56e057f20f883e', '0', '0', '1', '1');
INSERT INTO `shiro_user_information` VALUES (60, '王五', 'admin', '21232f297a57a5a743894a0e4a801fc3', '3', '0', '1', '1');

-- ----------------------------
-- Table structure for shiro_user_role
-- ----------------------------
DROP TABLE IF EXISTS `shiro_user_role`;
CREATE TABLE `shiro_user_role`  (
  `uid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  INDEX `uid`(`uid`) USING BTREE,
  INDEX `rid`(`rid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shiro_user_role
-- ----------------------------
INSERT INTO `shiro_user_role` VALUES (2, 2);
INSERT INTO `shiro_user_role` VALUES (1, 1);
INSERT INTO `shiro_user_role` VALUES (16, 2);
INSERT INTO `shiro_user_role` VALUES (18, 1);
INSERT INTO `shiro_user_role` VALUES (25, 3);
INSERT INTO `shiro_user_role` VALUES (26, 3);
INSERT INTO `shiro_user_role` VALUES (27, 3);
INSERT INTO `shiro_user_role` VALUES (28, 3);
INSERT INTO `shiro_user_role` VALUES (29, 3);
INSERT INTO `shiro_user_role` VALUES (31, 3);
INSERT INTO `shiro_user_role` VALUES (32, 3);
INSERT INTO `shiro_user_role` VALUES (34, 3);
INSERT INTO `shiro_user_role` VALUES (46, 3);
INSERT INTO `shiro_user_role` VALUES (48, 3);
INSERT INTO `shiro_user_role` VALUES (50, 3);
INSERT INTO `shiro_user_role` VALUES (51, 3);
INSERT INTO `shiro_user_role` VALUES (52, 3);
INSERT INTO `shiro_user_role` VALUES (53, 3);
INSERT INTO `shiro_user_role` VALUES (54, 3);
INSERT INTO `shiro_user_role` VALUES (55, 3);
INSERT INTO `shiro_user_role` VALUES (57, 1);
INSERT INTO `shiro_user_role` VALUES (58, 3);
INSERT INTO `shiro_user_role` VALUES (59, 1);

-- ----------------------------
-- Table structure for staff_information
-- ----------------------------
DROP TABLE IF EXISTS `staff_information`;
CREATE TABLE `staff_information`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `aid` int(11) NULL DEFAULT NULL COMMENT '用户信息id',
  `company_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称',
  `company_id` int(11) NULL DEFAULT NULL COMMENT '公司id',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `id_card` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `id_card_url_id` int(60) NULL DEFAULT NULL COMMENT '身份证文件的id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff_information
-- ----------------------------
INSERT INTO `staff_information` VALUES (4, 58, '4', 18, '18877763212', '3223jui@qq.com', '340303199809391283', 92, '2019-08-10 23:24:28', '假的创建人名');
INSERT INTO `staff_information` VALUES (5, 16, '测试公司名', 4, '18855532979', '1555343555@qq.com', '340303199609280415', 93, '2019-08-13 14:15:01', '测试人名');

-- ----------------------------
-- Table structure for subject_funds_budget
-- ----------------------------
DROP TABLE IF EXISTS `subject_funds_budget`;
CREATE TABLE `subject_funds_budget`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '合同管理的子表三',
  `contract_id` int(11) NOT NULL COMMENT '合同管理id',
  `funding_sources_budget` decimal(10, 2) NOT NULL COMMENT '经费来源合计预算数',
  `current_year` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '当前年',
  `next_year` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '明年',
  `after_year` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '后年',
  `funding_sources_note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '經費來源备注',
  `current_year_source_total` decimal(15, 2) NOT NULL COMMENT '當前年來源合計',
  `next_year_source_total` decimal(15, 2) NOT NULL COMMENT '明年來源合計',
  `after_year_source_total` decimal(15, 2) NOT NULL COMMENT '後年來源合計',
  `provincial_budget` decimal(10, 2) NOT NULL COMMENT '省环保科研课题经费预算数',
  `provincial_current_budget` decimal(10, 2) NOT NULL COMMENT '省环保科研课题经费当前年预算',
  `provincial_next_budget` decimal(10, 2) NOT NULL COMMENT '省环保科研课题经费明年预算',
  `provincial_after_budget` decimal(10, 2) NOT NULL COMMENT '省环保科研课题经费后年预算',
  `provincial_note_budget` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '省环保科研课题经费预算备注',
  `department_budget` decimal(10, 2) NOT NULL COMMENT '部门、地方配套预算数',
  `department_current_budget` decimal(10, 2) NOT NULL COMMENT '部门、地方配套当前年预算',
  `department_next_budget` decimal(10, 2) NOT NULL COMMENT '部门、地方配套明年预算',
  `department_after_budget` decimal(10, 2) NOT NULL COMMENT '部门、地方配套后年预算',
  `department_note_budget` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '部门、地方配套预算备注',
  `bear_budget` decimal(10, 2) NOT NULL COMMENT '承担单位自筹预算数',
  `bear_current_budget` decimal(10, 2) NOT NULL COMMENT '承担单位自筹当前年预算',
  `bear_next_budget` decimal(10, 2) NOT NULL COMMENT '承担单位自筹明年预算',
  `bear_after_budget` decimal(10, 2) NOT NULL COMMENT '承担单位自筹后年预算',
  `bear_note_budget` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '承担单位自筹预算备注',
  `other_budget` decimal(10, 2) NOT NULL COMMENT '其他来源预算数',
  `other_current_budget` decimal(10, 2) NOT NULL COMMENT '其他来源当前年预算',
  `other_next_budget` decimal(10, 2) NOT NULL COMMENT '其他来源明年预算',
  `other_after_budget` decimal(10, 2) NOT NULL COMMENT '其他来源后年预算',
  `other_note_budget` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '其他来源预算备注',
  `expenditure_budget` decimal(10, 2) NOT NULL COMMENT '省环保科研课题经费支出合计预算数',
  `current_year_expenditure_total` decimal(10, 0) NOT NULL COMMENT '當前年支出合計',
  `next_year_expenditure_total` decimal(10, 2) NOT NULL COMMENT '明年支出合計',
  `after_year_expenditure_total` decimal(10, 2) NOT NULL COMMENT '后年支出合計',
  `self_total_expenditures` decimal(10, 2) NOT NULL COMMENT '自筹配套经费支出合計',
  `total_expenditures_note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '经费支出合計備注',
  `equipment_budget` decimal(10, 2) NOT NULL COMMENT '设备费支出预算数',
  `equipment_current_budget` decimal(10, 2) NOT NULL COMMENT '设备费支出当前年预算',
  `equipment_next_budget` decimal(10, 2) NOT NULL COMMENT '设备费支出明年预算',
  `equipment_after_budget` decimal(10, 2) NOT NULL COMMENT '设备费支出后年预算',
  `equipment_supporting_budget` decimal(10, 2) NOT NULL COMMENT '设备费支出自筹配套经费预算',
  `equipment_note_budget` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '设备费支出预算备注',
  `material_budget` decimal(10, 2) NOT NULL COMMENT '材料费支出预算数',
  `material_current_budget` decimal(10, 2) NOT NULL COMMENT '材料费支出当前年预算',
  `material_next_budget` decimal(10, 2) NOT NULL COMMENT '材料费支出明年预算',
  `material_after_budget` decimal(10, 2) NOT NULL COMMENT '材料费支出后年预算',
  `material_supporting_budget` decimal(10, 2) NOT NULL COMMENT '材料费支出自筹配套经费预算',
  `material_note_budget` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '材料费支出预算备注',
  `test_budget` decimal(10, 2) NOT NULL COMMENT '测试化验加工费支出预算数',
  `test_current_budget` decimal(10, 2) NOT NULL COMMENT '测试化验加工费支出当前年预算',
  `test_next_budget` decimal(10, 2) NOT NULL COMMENT '测试化验加工费支出明年预算',
  `test_after_budget` decimal(10, 2) NOT NULL COMMENT '测试化验加工费支出后年预算',
  `test_supporting_budget` decimal(10, 2) NOT NULL COMMENT '测试化验加工费支出自筹配套经费预算',
  `test_note_budget` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '测试化验加工费支出预算备注',
  `fuel_budget` decimal(10, 2) NOT NULL COMMENT '燃料动力费支出预算数',
  `fuel_current_budget` decimal(10, 2) NOT NULL COMMENT '燃料动力费支出当前年预算',
  `fuel_next_budget` decimal(10, 2) NOT NULL COMMENT '燃料动力费支出明年预算',
  `fuel_after_budget` decimal(10, 2) NOT NULL COMMENT '燃料动力费支出后年预算',
  `fuel_supporting_budget` decimal(10, 2) NOT NULL COMMENT '燃料动力费支出自筹配套经费预算',
  `fuel_note_budget` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '燃料动力费支出预算备注',
  `metting_budget` decimal(10, 2) NOT NULL COMMENT '会议差旅费支出预算数',
  `metting_current_budget` decimal(10, 2) NOT NULL COMMENT '会议差旅费支出当前年预算',
  `metting_next_budget` decimal(10, 2) NOT NULL COMMENT '会议差旅费支出明年预算',
  `metting_after_budget` decimal(10, 2) NOT NULL COMMENT '会议差旅费支出后年预算',
  `metting_supporting_budget` decimal(10, 2) NOT NULL COMMENT '会议差旅费支出自筹配套经费预算',
  `metting_note_budget` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '会议差旅费支出预算备注',
  `labor_budget` decimal(10, 2) NOT NULL COMMENT '劳务费支出预算数',
  `labor_current_budget` decimal(10, 2) NOT NULL COMMENT '劳务费支出当前年预算',
  `labor_next_budget` decimal(10, 2) NOT NULL COMMENT '劳务费支出明年预算',
  `labor_after_budget` decimal(10, 2) NOT NULL COMMENT '劳务费支出后年预算',
  `labor_supporting_budget` decimal(10, 2) NOT NULL COMMENT '劳务费支出自筹配套经费预算',
  `labor_note_budget` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '劳务费支出预算备注',
  `experts_budget` decimal(10, 2) NOT NULL COMMENT '专家咨询费预算数',
  `experts_current_budget` decimal(10, 2) NOT NULL COMMENT '专家咨询费支出当前年预算',
  `experts_next_budget` decimal(10, 2) NOT NULL COMMENT '专家咨询费支出明年预算',
  `experts_after_budget` decimal(10, 2) NOT NULL COMMENT '专家咨询费支出后年预算',
  `experts_supporting_budget` decimal(10, 2) NOT NULL COMMENT '专家咨询费支出自筹配套经费预算',
  `experts_note_budget` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '专家咨询费支出预算备注',
  `daily_budget` decimal(10, 2) NOT NULL COMMENT '日常水、电、气、暖消耗等支出预算数',
  `daily_current_budget` decimal(10, 2) NOT NULL COMMENT '日常水、电、气、暖消耗等支出当前年预算',
  `daily_next_budget` decimal(10, 2) NOT NULL COMMENT '日常水、电、气、暖消耗等支出明年预算',
  `daily_after_budget` decimal(10, 2) NOT NULL COMMENT '\r\n日常水、电、气、暖消耗等支出后年预算',
  `daily_supporting_budget` decimal(10, 2) NOT NULL COMMENT '日常水、电、气、暖消耗等支出自筹配套经费预算',
  `daily_note_budget` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '日常水、电、气、暖消耗等支出预算备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject_funds_budget
-- ----------------------------
INSERT INTO `subject_funds_budget` VALUES (1, 1, 123.59, '2015', '2014', '2015', 'dgrhtfu', 0.00, 0.00, 0.00, 475.32, 453453.00, 45343.00, 543434.00, '453453', 25.36, 4858.00, 87487.00, 4587.00, 'fgryry4w', 45.56, 152436.00, 45485.00, 153485.00, 'frgftuh', 78.89, 53434.00, 145343.00, 452453.00, '452434', 74.14, 0, 0.00, 0.00, 0.00, '', 58.25, 4524.00, 453434.00, 4524.00, 21534.00, 'rr6tut6u', 76.54, 4534.00, 54343.00, 12345.00, 45348.00, '45343', 45.58, 453434.00, 53484.00, 453453.00, 453434.00, '53454', 78.21, 5454.00, 4543.00, 4525.00, 4534.00, '1523', 324.65, 5234.00, 5348.00, 53454.00, 5484.00, '544534', 75.25, 453484.00, 54358.00, 523454.00, 5424.00, 'estyr', 72.31, 4534.00, 15435.00, 5234.00, 4152.00, 'bfdhrtfhj', 35.46, 45486.00, 45348.00, 5434.00, 45483.00, '45634');
INSERT INTO `subject_funds_budget` VALUES (2, 2, 145.36, '1234', '2015', '2018', '12345678', 0.00, 0.00, 0.00, 475.32, 12345678.00, 12345678.00, 12345678.00, '12345678', 25.36, 12345678.00, 12345678.00, 12345678.00, '12345678', 45.56, 12345678.00, 12345678.00, 12345678.00, '12345678', 78.89, 12345678.00, 12345678.00, 12345678.00, '12345678', 74.14, 0, 0.00, 0.00, 0.00, '', 58.25, 12345678.00, 12345678.00, 12345678.00, 12345678.00, '12345678', 76.54, 12345678.00, 12345678.00, 12345678.00, 1234567.00, '12345678', 45.58, 12345678.00, 12345678.00, 12345678.00, 12345678.00, '12345678', 78.21, 12345678.00, 12345678.00, 12345678.00, 12345678.00, '12345678', 324.65, 12345678.00, 12345678.00, 12345678.00, 12345678.00, '12345678', 75.25, 12345678.00, 12345678.00, 12345678.00, 12345678.00, '12345678', 72.31, 12345678.00, 12345678.00, 12345678.00, 12345678.00, '12345678', 35.46, 12345678.00, 12345678.00, 12345678.00, 12345678.00, '12345678');
INSERT INTO `subject_funds_budget` VALUES (3, 3, 4563.23, '2015', '2014', '2015', 'dgrhtfu', 0.00, 0.00, 0.00, 475.32, 453453.00, 45343.00, 543434.00, '453453', 25.36, 4858.00, 87487.00, 4587.00, 'fgryry4w', 45.56, 152436.00, 45485.00, 153485.00, 'frgftuh', 78.89, 53434.00, 145343.00, 452453.00, '452434', 74.14, 0, 0.00, 0.00, 0.00, '', 58.25, 4524.00, 453434.00, 4524.00, 21534.00, 'rr6tut6u', 76.54, 4534.00, 54343.00, 12345.00, 45348.00, '45343', 45.58, 453434.00, 53484.00, 453453.00, 453434.00, '53454', 78.21, 5454.00, 4543.00, 4525.00, 4534.00, '1523', 324.65, 5234.00, 5348.00, 53454.00, 5484.00, '544534', 75.25, 453484.00, 54358.00, 523454.00, 5424.00, 'estyr', 72.31, 4534.00, 15435.00, 5234.00, 4152.00, 'bfdhrtfhj', 35.46, 45486.00, 45348.00, 5434.00, 45483.00, '45634');
INSERT INTO `subject_funds_budget` VALUES (4, 4, 485.24, '2015', '2014', '2015', 'dgrhtfu', 0.00, 0.00, 0.00, 475.32, 453453.00, 45343.00, 543434.00, '453453', 25.36, 4858.00, 87487.00, 4587.00, 'fgryry4w', 45.56, 152436.00, 45485.00, 153485.00, 'frgftuh', 78.89, 53434.00, 145343.00, 452453.00, '452434', 74.14, 0, 0.00, 0.00, 0.00, '', 58.25, 4524.00, 453434.00, 4524.00, 21534.00, 'rr6tut6u', 76.54, 4534.00, 54343.00, 12345.00, 45348.00, '45343', 45.58, 453434.00, 53484.00, 453453.00, 453434.00, '53454', 78.21, 5454.00, 4543.00, 4525.00, 4534.00, '1523', 324.65, 5234.00, 5348.00, 53454.00, 5484.00, '544534', 75.25, 453484.00, 54358.00, 523454.00, 5424.00, 'estyr', 72.31, 4534.00, 15435.00, 5234.00, 4152.00, 'bfdhrtfhj', 35.46, 45486.00, 45348.00, 5434.00, 45483.00, '45634');
INSERT INTO `subject_funds_budget` VALUES (6, 6, 12345678.09, '2016', '2017', '2015', 'efswsetw', 0.00, 0.00, 0.00, 12345678.09, 12345678.09, 12345678.09, 12345678.09, '12345678.09', 12345678.09, 12345678.09, 12345678.09, 12345678.09, 'ygyjgbuyv', 12345678.09, 12345678.09, 12345678.09, 12345678.09, 'jiohjno', 12345678.09, 12345678.09, 12345678.09, 12345678.09, 'thgjyiu', 12345678.09, 0, 0.00, 0.00, 0.00, '', 12345678.09, 12345678.09, 12345678.09, 12345678.09, 12345678.09, 'yghijygu8', 12345678.09, 12345678.09, 12345678.09, 12345678.09, 12345678.09, 'hgiuygi', 12345678.09, 12345678.09, 12345678.09, 12345678.09, 12345678.09, '12345678.09', 12345678.09, 12345678.09, 12345678.09, 12345678.09, 12345678.09, 'trfhgbvujg', 12345678.09, 12345678.09, 12345678.09, 12345678.09, 12345678.09, 'sfsaew', 12345678.09, 12345678.09, 12345678.09, 12345678.09, 12345678.09, 'gtyhgu', 12345678.09, 12345678.09, 12345678.09, 12345678.09, 12345678.09, 'ytfvgjybhi', 12345678.09, 12345678.09, 12345678.09, 12345678.09, 12345678.09, 'hgbugyu');
INSERT INTO `subject_funds_budget` VALUES (7, 21, 0.00, '2019', '2020', '2021', '123', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, '111', 0.00, 0.00, 0.00, 0.00, '1', 0.00, 0.00, 0.00, 0.00, '1111', 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0, 0.00, 0.00, 0.00, '', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 111.00, 0.00, 0.00, 0.00, '111', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '1111');
INSERT INTO `subject_funds_budget` VALUES (8, 22, 0.00, '2019', '2020', '2021', '123', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, '111', 0.00, 0.00, 0.00, 0.00, '1', 0.00, 0.00, 0.00, 0.00, '1111', 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0, 0.00, 0.00, 0.00, '', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 111.00, 0.00, 0.00, 0.00, '111', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '1111');
INSERT INTO `subject_funds_budget` VALUES (9, 26, 0.00, '2019', '2020', '2021', '123', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, '111', 0.00, 0.00, 0.00, 0.00, '1', 0.00, 0.00, 0.00, 0.00, '1111', 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0, 0.00, 0.00, 0.00, '', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 111.00, 0.00, 0.00, 0.00, '111', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '1111');
INSERT INTO `subject_funds_budget` VALUES (10, 27, 0.00, '2019', '2020', '2021', '123', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, '111', 0.00, 0.00, 0.00, 0.00, '1', 0.00, 0.00, 0.00, 0.00, '1111', 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0, 0.00, 0.00, 0.00, '', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 111.00, 0.00, 0.00, 0.00, '111', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '1111');
INSERT INTO `subject_funds_budget` VALUES (11, 31, 0.00, '2019', '2020', '2021', '123', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, '111', 0.00, 0.00, 0.00, 0.00, '1', 0.00, 0.00, 0.00, 0.00, '1111', 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0, 0.00, 0.00, 0.00, '', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 111.00, 0.00, 0.00, 0.00, '111', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '1111');
INSERT INTO `subject_funds_budget` VALUES (12, 35, 0.00, '2019', '2020', '2021', '123', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, '111', 0.00, 0.00, 0.00, 0.00, '1', 0.00, 0.00, 0.00, 0.00, '1111', 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0, 0.00, 0.00, 0.00, '', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 111.00, 0.00, 0.00, 0.00, '111', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, 0.00, '1111');
INSERT INTO `subject_funds_budget` VALUES (13, 0, 0.00, '2018', '2020', '2019', '159', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 'wqe', 0.00, 0.00, 0.00, 0.00, '789', 0.00, 0.00, 0.00, 0.00, '123', 0.00, 0.00, 0.00, 0.00, '017', 0.00, 0, 0.00, 0.00, 0.00, 'asdw', 0.00, 0.00, 0.00, 0.00, 0.00, '147', 0.00, 0.00, 0.00, 0.00, 0.00, '843', 0.00, 0.00, 0.00, 0.00, 0.00, 'asd', 0.00, 0.00, 0.00, 0.00, 0.00, '369', 0.00, 0.00, 0.00, 0.00, 0.00, '761', 0.00, 0.00, 0.00, 0.00, 0.00, '357', 0.00, 0.00, 0.00, 0.00, 0.00, '258', 0.00, 0.00, 0.00, 0.00, 0.00, '456');
INSERT INTO `subject_funds_budget` VALUES (14, 36, 0.00, '2019', '2020', '2021', '1', 1.00, 1.00, 1.00, 0.00, 13.00, 14.00, 15.00, '16', 0.00, 13.00, 14.00, 15.00, '16', 0.00, 2.00, 3.00, 4.00, '5', 0.00, 2.00, 3.00, 4.00, '5', 119.00, 11, 12.00, 13.00, 14.00, '15', 17.00, 18.00, 19.00, 20.00, 21.00, '22', 317.00, 318.00, 319.00, 320.00, 321.00, '322', 1.00, 2.00, 3.00, 4.00, 5.00, '6', 117.00, 118.00, 119.00, 120.00, 121.00, '122', 917.00, 918.00, 919.00, 920.00, 921.00, '922', 117.00, 118.00, 119.00, 120.00, 121.00, '0122', 16.00, 17.00, 18.00, 19.00, 20.00, '21', 6.00, 7.00, 8.00, 9.00, 10.00, '11');

-- ----------------------------
-- Table structure for subject_participating_unit
-- ----------------------------
DROP TABLE IF EXISTS `subject_participating_unit`;
CREATE TABLE `subject_participating_unit`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '合同管理的子表二',
  `contract_id` int(11) NOT NULL COMMENT '合同主表id',
  `bearing_units` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课题承担单位',
  `participating_units` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课题参加单位',
  `overseas_cooperation_units` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '境外合作单位',
  `country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '国家或地区',
  `leader_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课题负责人姓名',
  `unit_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所在单位',
  `gender` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '性别',
  `age` int(2) NOT NULL COMMENT '年龄',
  `professional_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '职称',
  `professional` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '从事专业',
  `work_task` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '本课题中承担工作',
  `working_time` int(11) NOT NULL COMMENT '为本课题工作时间（%）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of subject_participating_unit
-- ----------------------------
INSERT INTO `subject_participating_unit` VALUES (1, 1, 'dfgrs34', 'dfsef335', 'hvhgg32', 'hjvn3554', 'jvj5', 'hgc44', '男', 25, 'nmv24', 'gfc253', 'mn55', 20);
INSERT INTO `subject_participating_unit` VALUES (2, 2, 'dfgrs35', 'dfsef336', 'hvhgg33', 'hjvn3555', 'jvj6', 'hgc45', '男', 26, 'nmv25', 'gfc254', 'mn56', 20);
INSERT INTO `subject_participating_unit` VALUES (3, 3, 'dfgrs36', 'dfsef337', 'hvhgg34', 'hjvn3556', 'jvj7', 'hgc46', '男', 27, 'nmv26', 'gfc255', 'mn57', 20);
INSERT INTO `subject_participating_unit` VALUES (4, 4, 'dsffgsa', 'dergety', 'rge5y', 'sdefsafg', 'ger5y', 'ertet', '女', 23, 're5ty', 'rge5t', 'erte3t', 20);
INSERT INTO `subject_participating_unit` VALUES (5, 18, 'string', 'string', 'string', 'string', 'string', 'string', '男', 0, 'string', 'string', 'string', 20);
INSERT INTO `subject_participating_unit` VALUES (6, 19, 'string', 'string', 'string', 'string', 'string', 'string', '男', 0, 'string', 'string', 'string', 20);
INSERT INTO `subject_participating_unit` VALUES (7, 20, 'string', 'string', 'string', 'string', 'string', 'string', '男', 0, 'string', 'string', 'string', 20);
INSERT INTO `subject_participating_unit` VALUES (8, 21, 'string', 'string', 'string', 'string', 'string', 'string', '男', 0, 'string', 'string', 'string', 20);
INSERT INTO `subject_participating_unit` VALUES (9, 22, 'string', 'string', 'string', 'string', 'string', 'string', '男', 0, 'string', 'string', 'string', 20);
INSERT INTO `subject_participating_unit` VALUES (10, 26, 'string', 'string', 'string', 'string', 'string', 'string', '男', 0, 'string', 'string', 'string', 20);
INSERT INTO `subject_participating_unit` VALUES (11, 27, 'string', 'string', 'string', 'string', 'string', 'string', '男', 0, 'string', 'string', 'string', 20);
INSERT INTO `subject_participating_unit` VALUES (12, 28, 'fgdr', 'str方式ing', 'st勝多負少ring', 'dfsf', 'st死而復生ring', '度過', '女', 22, '的方式', 'str都是ing', '給對方', 50);
INSERT INTO `subject_participating_unit` VALUES (13, 31, '1', '2', '3', '4', '5', '6', '7', 8, '9', '10', '11', 12);
INSERT INTO `subject_participating_unit` VALUES (14, 32, '1', '2', '3', '4', '5', '6', '7', 8, '9', '10', '11', 12);
INSERT INTO `subject_participating_unit` VALUES (15, 33, '1', '2', '3', '4', '5', '6', '7', 8, '9', '10', '11', 12);
INSERT INTO `subject_participating_unit` VALUES (16, 34, '1', '2', '3', '4', '5', '6', '7', 8, '9', '10', '11', 12);
INSERT INTO `subject_participating_unit` VALUES (17, 35, '1', '2', '3', '4', '5', '6', '7', 8, '9', '10', '11', 12);
INSERT INTO `subject_participating_unit` VALUES (18, 36, '1', '2', '3', '4', '5', '6', '7', 8, '9', '10', '11', 12);

-- ----------------------------
-- Table structure for tender_contract_shenhe_record
-- ----------------------------
DROP TABLE IF EXISTS `tender_contract_shenhe_record`;
CREATE TABLE `tender_contract_shenhe_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `shenhe_table_id` int(11) NULL DEFAULT NULL COMMENT '对应需审核表的id',
  `fist_handler` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交办人',
  `second_handler` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理人',
  `audit_step` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核步骤',
  `first_handle_time` datetime(0) NULL DEFAULT NULL COMMENT '交办时间',
  `state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `handle_content` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理内容',
  `second_handle_time` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tender_contract_shenhe_record
-- ----------------------------
INSERT INTO `tender_contract_shenhe_record` VALUES (1, 42, '单位员工', '单位管理员', '单位员工提交，等待单位管理员审核', '2019-08-30 09:11:58', '已退回', '就是不通过', '2019-08-30 09:14:15');
INSERT INTO `tender_contract_shenhe_record` VALUES (2, 42, '单位管理员', '评估中心', '等待单位员工重新提交', '2019-08-30 09:14:15', '已退回', '不给通过', '2019-08-30 09:39:32');
INSERT INTO `tender_contract_shenhe_record` VALUES (3, 42, '评估中心', '单位员工', '等待单位员工重新提交', '2019-08-30 09:39:32', '等待处理', '等待单位管理员审核', '2019-08-30 10:02:58');
INSERT INTO `tender_contract_shenhe_record` VALUES (4, 42, '单位员工', '单位管理员', '单位员工提交，等待单位管理员审核', '2019-08-30 10:02:58', '已退回', '不好意思，手贱点错了', '2019-08-30 10:03:33');
INSERT INTO `tender_contract_shenhe_record` VALUES (5, 42, '单位管理员', '单位员工', '等待单位员工重新提交', '2019-08-30 10:03:33', '等待处理', '等待单位管理员审核', '2019-08-30 10:04:56');
INSERT INTO `tender_contract_shenhe_record` VALUES (6, 42, '单位员工', '单位管理员', '单位员工提交，等待单位管理员审核', '2019-08-30 10:04:56', '已处理', '单位管理员审核通过', '2019-08-30 10:05:33');
INSERT INTO `tender_contract_shenhe_record` VALUES (7, 42, '单位管理员', '评估中心', '通过单位管理员初审，等待评估中心审核', '2019-08-30 10:05:33', '已处理', '评估中心审核通过', '2019-08-30 10:08:16');
INSERT INTO `tender_contract_shenhe_record` VALUES (8, 43, '单位员工', NULL, '单位员工提交，等待单位管理员审核', '2019-09-01 10:49:11', '等待处理', NULL, NULL);
INSERT INTO `tender_contract_shenhe_record` VALUES (9, 37, '单位员工', NULL, '单位员工提交，等待单位管理员审核', '2019-09-01 11:52:17', '等待处理', NULL, NULL);

-- ----------------------------
-- Table structure for unit_contract
-- ----------------------------
DROP TABLE IF EXISTS `unit_contract`;
CREATE TABLE `unit_contract`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_id` int(11) NOT NULL COMMENT '单位id',
  `contract_id` int(11) NOT NULL COMMENT '合同主表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of unit_contract
-- ----------------------------
INSERT INTO `unit_contract` VALUES (1, 1, 1);
INSERT INTO `unit_contract` VALUES (2, 1, 3);
INSERT INTO `unit_contract` VALUES (3, 1, 5);
INSERT INTO `unit_contract` VALUES (4, 2, 2);
INSERT INTO `unit_contract` VALUES (5, 2, 4);
INSERT INTO `unit_contract` VALUES (6, 2, 6);

-- ----------------------------
-- Table structure for unit_guide_collection
-- ----------------------------
DROP TABLE IF EXISTS `unit_guide_collection`;
CREATE TABLE `unit_guide_collection`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_id` int(11) NOT NULL,
  `collection_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of unit_guide_collection
-- ----------------------------
INSERT INTO `unit_guide_collection` VALUES (1, 1, 5);
INSERT INTO `unit_guide_collection` VALUES (2, 3, 9);
INSERT INTO `unit_guide_collection` VALUES (3, 2, 4);
INSERT INTO `unit_guide_collection` VALUES (4, 1, 7);
INSERT INTO `unit_guide_collection` VALUES (5, 2, 5);
INSERT INTO `unit_guide_collection` VALUES (6, 3, 2);
INSERT INTO `unit_guide_collection` VALUES (7, 5, 1);
INSERT INTO `unit_guide_collection` VALUES (8, 7, 6);
INSERT INTO `unit_guide_collection` VALUES (9, 1, 3);
INSERT INTO `unit_guide_collection` VALUES (10, 1, 8);
INSERT INTO `unit_guide_collection` VALUES (11, 1, 10);
INSERT INTO `unit_guide_collection` VALUES (12, 1, 30);
INSERT INTO `unit_guide_collection` VALUES (13, 1, 20);

-- ----------------------------
-- Table structure for unit_major
-- ----------------------------
DROP TABLE IF EXISTS `unit_major`;
CREATE TABLE `unit_major`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id【单位关联重大事项】',
  `unit_id` int(11) NOT NULL COMMENT '单位id',
  `major_id` int(11) NOT NULL COMMENT '重大id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of unit_major
-- ----------------------------
INSERT INTO `unit_major` VALUES (1, 1, 1);
INSERT INTO `unit_major` VALUES (2, 2, 2);
INSERT INTO `unit_major` VALUES (3, 1, 3);
INSERT INTO `unit_major` VALUES (4, 2, 4);
INSERT INTO `unit_major` VALUES (5, 1, 5);
INSERT INTO `unit_major` VALUES (6, 1, 6);

-- ----------------------------
-- Table structure for unit_project_progress
-- ----------------------------
DROP TABLE IF EXISTS `unit_project_progress`;
CREATE TABLE `unit_project_progress`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_id` int(11) NOT NULL COMMENT '单位id',
  `subject_progress_id` int(11) NOT NULL COMMENT '课题进展id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of unit_project_progress
-- ----------------------------
INSERT INTO `unit_project_progress` VALUES (1, 1, 2);
INSERT INTO `unit_project_progress` VALUES (2, 3, 4);
INSERT INTO `unit_project_progress` VALUES (3, 4, 5);

-- ----------------------------
-- Table structure for unit_tender
-- ----------------------------
DROP TABLE IF EXISTS `unit_tender`;
CREATE TABLE `unit_tender`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_id` int(11) NOT NULL COMMENT '单位id',
  `tender_id` int(11) NOT NULL COMMENT '公开招标报名表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of unit_tender
-- ----------------------------
INSERT INTO `unit_tender` VALUES (1, 1, 2);
INSERT INTO `unit_tender` VALUES (2, 2, 3);
INSERT INTO `unit_tender` VALUES (3, 5, 6);
INSERT INTO `unit_tender` VALUES (4, 3, 1);
INSERT INTO `unit_tender` VALUES (5, 1, 2);

-- ----------------------------
-- Table structure for upload_file
-- ----------------------------
DROP TABLE IF EXISTS `upload_file`;
CREATE TABLE `upload_file`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `upload_surface_id` int(20) NULL DEFAULT NULL COMMENT '上传文件表Id',
  `upload_file_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上传文件地址',
  `upload_file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上传文件名',
  `date_file_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日期文件名',
  `upload_file_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上传文件类型',
  `upload_suffix_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上传文件后缀名',
  `file_size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件大小',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 296 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of upload_file
-- ----------------------------
INSERT INTO `upload_file` VALUES (296, NULL, 'D:/xdmd_environment/4/验收申请/2019-09-04测试压缩文件.zip', '测试压缩文件.zip', NULL, '验收申请', 'zip', '5982', '2019-09-04 14:42:06', '张三');
INSERT INTO `upload_file` VALUES (297, NULL, 'D:/xdmd_environment/4/成果附件/2019-09-04测试压缩文件.zip', '测试压缩文件.zip', NULL, '成果附件', 'zip', '5982', '2019-09-04 14:42:06', '张三');
INSERT INTO `upload_file` VALUES (298, NULL, 'D:/xdmd_environment/4/提交清单/2019-09-04测试压缩文件.zip', '测试压缩文件.zip', NULL, '提交清单', 'zip', '5982', '2019-09-04 14:42:06', '张三');
INSERT INTO `upload_file` VALUES (299, NULL, 'D:/xdmd_environment/4/验收申请/2019-09-04测试压缩文件.zip', '测试压缩文件.zip', NULL, '验收申请', 'zip', '5982', '2019-09-04 15:01:21', '李四');
INSERT INTO `upload_file` VALUES (300, NULL, 'D:/xdmd_environment/4/成果附件/2019-09-04测试压缩文件.zip', '测试压缩文件.zip', NULL, '成果附件', 'zip', '5982', '2019-09-04 15:01:21', '李四');
INSERT INTO `upload_file` VALUES (301, NULL, 'D:/xdmd_environment/4/提交清单/2019-09-04测试压缩文件.zip', '测试压缩文件.zip', NULL, '提交清单', 'zip', '5982', '2019-09-04 15:01:21', '李四');
INSERT INTO `upload_file` VALUES (302, NULL, 'D:/xdmd_environment/4/专项审计报告/2019-09-04测试压缩文件.zip', '测试压缩文件.zip', NULL, '专项审计报告', 'zip', '5982', '2019-09-04 15:36:35', '王五');
INSERT INTO `upload_file` VALUES (303, NULL, 'D:/xdmd_environment/4/初审报告/2019-09-04测试压缩文件.zip', '测试压缩文件.zip', NULL, '初审报告', 'zip', '5982', '2019-09-04 15:36:35', '王五');
INSERT INTO `upload_file` VALUES (304, NULL, 'D:/xdmd_environment/4/专家组意见/2019-09-04初审报告.zip', '初审报告.zip', NULL, '专家组意见', 'zip', '22', '2019-09-04 15:40:50', '李四');
INSERT INTO `upload_file` VALUES (305, NULL, 'D:/xdmd_environment/4/专家组评议/2019-09-04联系人身份证.zip', '联系人身份证.zip', NULL, '专家组评议', 'zip', '22', '2019-09-04 15:40:50', '李四');

SET FOREIGN_KEY_CHECKS = 1;
