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

 Date: 16/08/2019 18:24:48
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
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所在地区',
  `legal_representative` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '法定代表人',
  `legal_representative_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '法定代表人电话',
  `contacts` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系人',
  `contacts_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系人电话',
  `postal_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮政编码',
  `mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电子邮箱',
  `mailing_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通信地址',
  `competent_department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主管部门',
  `project_start_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题起始时间',
  `project_completion_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题完成时间',
  `achievement_form` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '成果形式',
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of acceptance_certificate
-- ----------------------------
INSERT INTO `acceptance_certificate` VALUES (1, NULL, '第一文号', '第一课题编号', '第一课题名称', '第一完成单位', '第一验收组织部门', '1201-09-21', '第一单位名称', 1, '第一所在地区', '第一法定代表人', '15668486', '第一联系人', '第一联系人电话', '第一邮政编码', '489486@qq.com', '第一通讯地址', '第一主管部门', '1754-06-11', '1854-04-11', '第一成果形式', 1, '5', '2', '5', '5', '4', '4', 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, '第一解决关键技术与创新点', '第一技术与环境完成情况', '第一课题实施绩效', '第一完成单位部门意见', '第一验收部门意见', '第一环境厅意见', '第一验收证书url');
INSERT INTO `acceptance_certificate` VALUES (2, NULL, '第二文号', '第二课题编号', '第二课题名称', '第二完成单位', '第二验收组织部门', '1201-09-21', '第二单位名称', 1, '第二所在地区', '第二法定代表人', '15668486', '第二联系人', '第二联系人电话', '第二邮政编码', '489486@qq.com', '第二通讯地址', '第二主管部门', '1754-06-11', '1854-04-11', '第二成果形式', 2, '12', '1', '4', '8', '5', '5', 2121.45, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, '第二解决关键技术与创新点', '第二技术与环境完成情况', '第二课题实施绩效', '第二完成单位部门意见', '第二验收部门意见', '第二环境厅意见', '第二验收证书url');
INSERT INTO `acceptance_certificate` VALUES (3, NULL, '第三文号', '第三课题编号', '第三课题名称', '第三完成单位', '第三验收组织部门', '1201-09-21', '第三单位名称', 2, '第三所在地区', '第三法定代表人', '15668486', '第三联系人', '第三联系人电话', '第三邮政编码', '489486@qq.com', '第三通讯地址', '第三主管部门', '1754-06-11', '1854-04-11', '第三成果形式', 1, '54', '3', '1', '4', '6', '5', 12.74, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, 12.20, '第三解决关键技术与创新点', '第三技术与环境完成情况', '第三课题实施绩效', '第三完成单位部门意见', '第三验收部门意见', '第三环境厅意见', '第三验收证书url');
INSERT INTO `acceptance_certificate` VALUES (5, 57, '文号1', '课题编号1', '课题名称11', '完成单位11', '验收组织部门111', '2000-01-04', '单位名称11', 3, '所在地区111', '法定代表人111', '1234567489', '联系人12', '18855536468', '233000', '85465122@qq.com', '通信地址', '主管部门', '2111-1-1', '2222-2-2', '成果形式121', 2, '4', '6', '6', '32', '1', '11', 12.30, 11.10, 11.40, 12.40, 3.20, 232.30, 23.30, 12.90, 65.50, 94.30, 12.30, 66.50, 12.30, 43.40, 23.30, 11.10, 6.50, 32.40, 23.20, 23.30, '主要解决的技术难点', '主要技术', '课题实施的绩效', '科技部门意见', '组织验收部门意见', '省生态环境厅意见', NULL);

-- ----------------------------
-- Table structure for acceptance_certificate_patent
-- ----------------------------
DROP TABLE IF EXISTS `acceptance_certificate_patent`;
CREATE TABLE `acceptance_certificate_patent`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `acceptance_certificate_id` int(11) NOT NULL COMMENT '验收证书Id',
  `application_invention` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专利申请发明',
  `use_new_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专利申请使用新型',
  `patent_appearance` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专利申请外观设计',
  `patent_invention` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专利授权发明',
  `empower_new_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专利授权实用新型',
  `empower_appearance_design` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专利授权外观设计',
  `paper_number` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发表论文总数',
  `science_indexe` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发表论文科学英文索引',
  `engineer_index` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发表论文工程索引',
  `publish_work` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '出版科技著作',
  `technical_standard` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '制定技术标准',
  `new_product` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '新产品',
  `policy_system` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '制定政策制度',
  `new_device` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '建成新装置',
  `new_technology` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '新工艺',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of acceptance_certificate_patent
-- ----------------------------
INSERT INTO `acceptance_certificate_patent` VALUES (1, 1, '第一专利申请发明', '第一专利申请使用新型', '第一专利申请外观设计', '第一专利授权发明', '第一专利授权实用新型', '第一专利授权外观设计', '第一发表论文总数', '第一科学引文索引', '第一发表论文工程索引', '第一出版科技著作', '第一制定技术标准', '第一新产品', '第一制定政策制度', '第一建成新装置', '第一新工艺');
INSERT INTO `acceptance_certificate_patent` VALUES (2, 1, '第二专利申请发明', '第二专利申请使用新型', '第二专利申请外观设计', '第二专利授权发明', '第二专利授权实用新型', '第二专利授权外观设计', '第二发表论文总数', '第二科学引文索引', '第二发表论文工程索引', '第二出版科技著作', '第二制定技术标准', '第二新产品', '第二制定政策制度', '第二建成新装置', '第二新工艺');
INSERT INTO `acceptance_certificate_patent` VALUES (3, 2, '第三专利申请发明', '第三专利申请使用新型', '第三专利申请外观设计', '第三专利授权发明', '第三专利授权实用新型', '第三专利授权外观设计', '第三发表论文总数', '第三科学引文索引', '第三发表论文工程索引', '第三出版科技著作', '第三制定技术标准', '第三新产品', '第三制定政策制度', '第三建成新装置', '第三新工艺');
INSERT INTO `acceptance_certificate_patent` VALUES (4, 57, '专利申请发明', '专利申请使用新型', '专利外观设计', '专利授权发明', '专利授权实用新型', '专利授权外观设计', '8', '英文索引', '论文工程索引', '出版科技著作', '制定技术标准', '新产品', '制定政策制度', '新装置', '新工艺');
INSERT INTO `acceptance_certificate_patent` VALUES (5, 57, '专利申请发明', '专利申请使用新型', '专利外观设计', '专利授权发明', '专利授权实用新型', '专利授权外观设计', '8', '英文索引', '论文工程索引', '出版科技著作', '制定技术标准', '新产品', '制定政策制度', '新装置', '新工艺');
INSERT INTO `acceptance_certificate_patent` VALUES (6, 57, '专利申请发明1111', '专利申请使用新型111', '专利外观设计1111', '专利授权发明1111', '专利授权实用新型1111', '专利授权外观设计1111', '8', '英文索引111', '论文工程索引111', '出版科技著作', '制定技术标准1111', '新产品111', '制定政策制度1111', '新装置1111', '新工艺1111');

-- ----------------------------
-- Table structure for acceptance_certificate_principal_personnel
-- ----------------------------
DROP TABLE IF EXISTS `acceptance_certificate_principal_personnel`;
CREATE TABLE `acceptance_certificate_principal_personnel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `acceptance_certificate_id` int(11) NOT NULL COMMENT '验收证书id',
  `participant_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主要参与人员姓名',
  `participant_sex` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主要参与人员性别',
  `participant_birth_date` date NOT NULL COMMENT '主要参与人员出生年月',
  `participant_technical_title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主要参与人员技术职称',
  `participant_education` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主要参与人员学历',
  `participant_work_unit` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主要参与人员工作单位',
  `task_taking` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主要参与人员承担的主要研究任务',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of acceptance_certificate_principal_personnel
-- ----------------------------
INSERT INTO `acceptance_certificate_principal_personnel` VALUES (1, 1, '第一主要参与人员姓名', '男', '1121-06-16', '第一主要参与人员技术职称', '第一主要参与人员学历', '第一主要参与人员工作单位', '第一承担的主要研究任务');
INSERT INTO `acceptance_certificate_principal_personnel` VALUES (2, 1, '第二主要参与人员姓名', '男', '1121-06-16', '第二主要参与人员技术职称', '第二主要参与人员学历', '第二主要参与人员工作单位', '第二承担的主要研究任务');
INSERT INTO `acceptance_certificate_principal_personnel` VALUES (3, 57, '第三主要参与人员姓名', '男', '1121-06-16', '第三主要参与人员技术职称', '第三主要参与人员学历', '第三主要参与人员工作单位', '第三承担的主要研究任务');
INSERT INTO `acceptance_certificate_principal_personnel` VALUES (4, 57, '主要参与人员姓名', '男', '2000-01-02', '技术职称', '学历', '工作单位', '主要研究任务');
INSERT INTO `acceptance_certificate_principal_personnel` VALUES (5, 57, '主要参与人员姓名1`', '男', '2222-12-12', '技术职称111', '学历11', '工作单位11', '主要研究任务11');

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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of acceptance_certificate_subject_people
-- ----------------------------
INSERT INTO `acceptance_certificate_subject_people` VALUES (1, 1, '第一张三', '男', '1355-07-11', '第一专业', '第一学历', '第一职称', '第一联系电话');
INSERT INTO `acceptance_certificate_subject_people` VALUES (2, 1, '第二张三', '男', '1355-07-11', '第二专业', '第二学历', '第二职称', '第二联系电话');
INSERT INTO `acceptance_certificate_subject_people` VALUES (3, 2, '第三张三', '男', '1355-07-11', '第三专业', '第三学历', '第三职称', '第三联系电话');
INSERT INTO `acceptance_certificate_subject_people` VALUES (4, 57, '课题负责人姓名', '男', '2222-01-05', '专业', '学历', '责任人职称', '12354845');
INSERT INTO `acceptance_certificate_subject_people` VALUES (5, 57, '课题负责人姓名4', '男', '2222-01-05', '专业44', '学历44', '责任人职称44', '12354845');

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
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `administrator_information` VALUES (17, 5, '公司名称1221', 25, '公司地址11', '2', '12100000425010757A', '法人姓名', '340303119609280415', '18855532979', '12345687@qq.com', 146, 147, 148, '2019-08-09 14:56:01');
INSERT INTO `administrator_information` VALUES (19, 18, '公司名称1221', 16, '公司地址11', '2', '12100000425010757A', '法人姓名', '340303119609280415', '18855532979', '12345687@qq.com', 97, 98, 99, '2019-08-13 15:05:17');

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
  `is_outcome` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否已经加入过成果库\r\n0：还没有加入到成果库 （默认为 0）\r\n1:  已经加入到成果库',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of check_apply
-- ----------------------------
INSERT INTO `check_apply` VALUES (50, '课题名称1', '123', 4, '公司名称1', 3, '项目负责人名1', '15588865919', '158965512@qq.com', '公司地址1', '2019-07-01', '2019-07-03', '2019-07-04', 1, '申请验收地点', '验收联系人1', '13566678482', '主要研究内容完成情况1', '提交成果情况1', '课题承担单位意见1', '所在环保部门意见1', '省生态环境评估中心初审意见1', '省环保厅主管部门意见1', '1', 3, '2019-07-18 10:15:20', '创建人1', 77, 78, NULL, NULL, 4, NULL, 76, NULL, NULL, NULL, '0');
INSERT INTO `check_apply` VALUES (51, '课题名称2', '123', 6, '公司名称2', 2, '项目负责人名2', '15588865911', '158965512@163.com', '公司地址2', '2019-07-01', '2019-07-03', '2019-07-04', 1, '申请验收地点', '验收联系人2', '13566678482', '主要研究内容完成情况2', '提交成果情况2', '课题承担单位意见2', '所在环保部门意见2', '省生态环境评估中心初审意见2', '省环保厅主管部门意见2', '2', 3, '2019-07-18 10:15:20', '创建人2', 77, 78, NULL, NULL, 4, NULL, 76, NULL, NULL, NULL, '0');
INSERT INTO `check_apply` VALUES (52, '课题名称3', '1234', 2, '公司名称3', 2, '项目负责人名2', '15588865741', '158965512@wixing.com', '公司地址2', '2019-07-01', '2019-07-03', '2019-07-04', 1, '申请验收地点3', '验收联系人3', '13566678485', '主要研究内容完成情况3', '提交成果情况3', '课题承担单位意见3', '所在环保部门意见3', '省生态环境评估中心初审意见3', '省环保厅主管部门意见3', '3', 1, '2019-07-18 10:15:20', '创建人1', 77, 78, NULL, NULL, 4, NULL, 76, NULL, NULL, NULL, '0');
INSERT INTO `check_apply` VALUES (53, '课题名称5', '12345', 2, '公司名称4', 2, '项目负责人名5', '15588865741', '158965512@wixing.com', '公司地址5', '2019-07-01', '2019-07-03', '2019-07-04', 2, '申请验收地点5', '验收联系人5', '13566678485', '主要研究内容完成情况5', '提交成果情况5', '课题承担单位意见5', '所在环保部门意见5', '省生态环境评估中心初审意见5', '省环保厅主管部门意见5', '5', 4, '2019-07-18 10:15:20', '创建人1', 77, 78, NULL, NULL, 4, NULL, 76, NULL, NULL, NULL, '0');
INSERT INTO `check_apply` VALUES (54, '课题名称7', '12345', 2, '公司名称2', 2, '项目负责人名7', '15588865741', '158965512@wixing.com', '公司地址5', '2019-07-01', '2019-07-03', '2019-07-04', 1, '申请验收地点7', '验收联系人7', '13566678485', '主要研究内容完成情况7', '提交成果情况7', '课题承担单位意见7', '所在环保部门意见7', '省生态环境评估中心初审意见7', '省环保厅主管部门意见7', '7', 77, '2019-07-18 10:15:20', '创建人1', 77, 78, NULL, NULL, 4, NULL, 76, NULL, NULL, NULL, '0');
INSERT INTO `check_apply` VALUES (55, '课题名称666', '12345', 2, '公司名称1', 2, '项目负责人名666', '15588865741', '158965512@wixing.com', '公司地址5', '2019-07-01', '2019-07-03', '2019-07-04', 2, '申请验收地点666', '验收联系人666', '13566678485', '主要研究内容完成情况666', '提交成果情况666', '课题承担单位意见6667', '所在环保部门意见6667', '省生态环境评估中心初审意见6667', '省环保厅主管部门意见7', '6', 3, '2019-07-18 10:15:20', '创建人1', 77, 78, NULL, NULL, 77, 78, 76, NULL, 77, NULL, '0');
INSERT INTO `check_apply` VALUES (56, '课题名称9999', '123453', 14, 'xdmd', 2, '项目负责人999', '9999999999', '9999999@qq.com', '公司地址999', '2019-07-01', '2019-07-11', '2019-07-15', 2, '申请验收地点9999', '验收联系人99999', '9999999999', '主要研究内容完成情况9999', '提交成果情况9999', '课题承担单位意见999999', '所在环保部门意见9999', '省生态环境评估中心初审意见99999', '省环保厅主管部门意见999999', '2', 5, '2019-07-23 14:12:12', '创建人999', 77, 78, NULL, NULL, NULL, 144, NULL, NULL, NULL, 88, '0');
INSERT INTO `check_apply` VALUES (57, '656课题名称', '6565课题编号', 555, '课题承担单位121', 1, '单位负责人', '18855536494', '15615615@qq.com', '通讯地址', '2000-02-05', '2111-05-04', '2333-05-07', 2, '申请验收地点212', '验收联系人231', '15588865131', '主要完成情况', '提交成果情况54543', '课题承担单位意见839232', '环保部门意见8392', '初审意见893232', '省环保主管部门意见321', '提交清单23121', 77, '2019-08-12 20:58:45', '测试的人名', 87, 122, 77, 78, 77, 78, 86, 78, 77, 91, '0');
INSERT INTO `check_apply` VALUES (60, '测试课题名称', '测试课题编号', 555, '测试课题承担单位121', 1, '测试单位负责人', '18855536494', '15615615@qq.com', '通讯地址', '2000-02-05', '2111-05-04', '2333-05-07', 2, '申请验收地点212', '验收联系人231', '15588865131', '主要完成情况', '提交成果情况54543', '课题承担单位意见839232', '环保部门意见8392', '初审意见893232', '省环保主管部门意见321', '1,2,4', 2, '2019-08-15 15:10:00', '测试的人名', 130, 131, NULL, NULL, NULL, NULL, 129, NULL, NULL, NULL, NULL);
INSERT INTO `check_apply` VALUES (61, '测试课题名称', '测试课题编号', 555, '测试课题承担单位121', 1, '测试单位负责人', '18855536494', '15615615@qq.com', '通讯地址', '2000-02-05', '2111-05-04', '2333-05-07', 2, '申请验收地点212', '验收联系人231', '15588865131', '主要完成情况', '提交成果情况54543', '课题承担单位意见839232', '环保部门意见8392', '初审意见893232', '省环保主管部门意见321', '1,2,4', 3, '2019-08-15 15:13:11', '测试的人名', 136, 137, NULL, NULL, NULL, NULL, 135, NULL, NULL, NULL, '0');

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
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `check_apply_state` VALUES (43, 57, '测试的人名', NULL, '公司审批', '2019-08-12 20:58:45', '待处理', NULL, NULL);
INSERT INTO `check_apply_state` VALUES (44, 20, '王六', NULL, '审核最终验收报告', '2019-08-12 23:43:47', '等待审核', NULL, NULL);
INSERT INTO `check_apply_state` VALUES (45, 60, '测试的人名', NULL, '公司审批', '2019-08-15 15:06:26', '待处理', NULL, NULL);
INSERT INTO `check_apply_state` VALUES (46, 60, '测试的人名', NULL, '公司审批', '2019-08-15 15:10:00', '待处理', NULL, NULL);
INSERT INTO `check_apply_state` VALUES (47, 61, '测试的人名', '测试的人名', '公司审批', '2019-08-15 15:13:11', '已处理', '审核通过', '2019-08-15 16:10:41');
INSERT INTO `check_apply_state` VALUES (48, 61, '测试的人名', NULL, '等待验收初审', '2019-08-15 16:10:41', '等待处理', NULL, NULL);

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
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`egc_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expert_group_comments
-- ----------------------------
INSERT INTO `expert_group_comments` VALUES (1, 56, '666测试课题名称', '测试课题编号', '课题负责人', '666课题承担单位', 11, 1.10, 1.20, 1.30, 1.40, 1.50, 1.60, 1.70, 2.0, '对课题的总体评价', '建议', 77, '专家组组长姓名', '2000-01-03', '2019-07-30 11:52:20', 'admin');

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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expert_group_comments_name
-- ----------------------------
INSERT INTO `expert_group_comments_name` VALUES (14, 1, '9898专家姓名', '98单位名称', '99专业', '99职务');
INSERT INTO `expert_group_comments_name` VALUES (15, 1, '121专家姓名', '321单位名称', '212专业', '121职务');

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
  `professional_field` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专业领域',
  `curriculum_vitae` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '个人简历',
  `recommendation_unit_opinion` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '推荐单位意见',
  `create_author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建此条信息的人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建此条信息的时间',
  `is_province` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否省内  存放字典表中的id',
  `reason` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核未通过的原因',
  `expert_information_url_id` int(10) NULL DEFAULT NULL COMMENT '专家信息的文件id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expert_information
-- ----------------------------
INSERT INTO `expert_information` VALUES (1, NULL, '男', '1996-10-23', '本科', '主任', '技术部主任', '环境治理', '环境治理专业', '南京环境治理公司', '南京市江宁区', '233000', '5236478', '18532158651', '18535154865@qq.com', '研究环境', '环境治理', '18918156158gergegegregergergergergergergergregeuigueirhgeuihguei', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `expert_information` VALUES (2, NULL, '男', '1996-10-23', '本科', '主任', '技术部主任', '环境治理', '环境治理专业', '南京环境治理公司', '南京市江宁区', '233000', '5236478', '18532158651', '18535154865@qq.com', '研究环境', '环境治理', '18918156158gergegegregergergergergergergergregeuigueirhgeuihguei', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `expert_information` VALUES (3, NULL, '男', '1996-10-23', '本科', '主任', '技术部主任', '环境治理', '环境治理专业', '南京环境治理公司', '南京市江宁区', '233000', '5236478', '18532158651', '18535154865@qq.com', '研究环境', '环境治理', '18918156158gergegegregergergergergergergergregeuigueirhgeuihguei', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `expert_information` VALUES (4, NULL, '男', '0201-02-11', '本科', '现任职务1', '技术职称1', '所学专业1', '从事专业1', '工作单位1', '通讯地址1', '邮政编码1', '123456', '123456789', '123456@qq.com', '1', '专业领域1', '个人简历1', NULL, 'admin', '2019-07-31 09:58:09', NULL, NULL, NULL);
INSERT INTO `expert_information` VALUES (7, NULL, '男', '0201-02-11', '本科', '现任职务1', '技术职称1', '所学专业1', '从事专业1', '工作单位1', '通讯地址1', '邮政编码1', '123456', '123456789', '123456@qq.com', '1', '专业领域1', '个人简历1', NULL, 'admin', '2019-07-31 10:07:59', NULL, NULL, NULL);
INSERT INTO `expert_information` VALUES (8, NULL, '男', '0201-02-11', '本科', '现任职务1', '技术职称1', '所学专业1', '从事专业1', '工作单位1', '通讯地址1', '邮政编码1', '123456', '123456789', '123456@qq.com', '2', '专业领域1', '个人简历1', NULL, 'admin', '2019-07-31 10:34:36', NULL, '就是不给你通过', NULL);
INSERT INTO `expert_information` VALUES (9, 6, '男', '2000-01-02', '本科', '职务111', '技术职称111', '所学专业333', '从事专业222', '工作单位3232', '通讯地址33232', '233000', '18855532979', '18855532646', '54652555@qq.com', '33', '专业领域', '个人简历2121', '推荐单位意见32', NULL, '2019-08-09 18:17:03', '1', NULL, NULL);
INSERT INTO `expert_information` VALUES (10, 7, '男', '2000-01-02', '本科', '职务111', '技术职称111', '所学专业333', '从事专业222', '工作单位3232', '通讯地址33232', '233000', '18855532979', '18855532646', '54652555@qq.com', '33', '专业领域', '个人简历2121', '推荐单位意见32', NULL, '2019-08-09 18:18:19', '1', NULL, NULL);
INSERT INTO `expert_information` VALUES (12, 9, '男', '2000-01-02', '本科', '职务111', '技术职称111', '所学专业333', '从事专业222', '工作单位3232', '通讯地址33232', '233000', '18855532979', '18855532646', '54652555@qq.com', '33', '专业领域', '个人简历2121', '推荐单位意见32', NULL, '2019-08-09 18:24:55', '1', NULL, NULL);
INSERT INTO `expert_information` VALUES (13, 11, '男', '2000-01-02', '本科', '职务111', '技术职称111', '所学专业333', '从事专业222', '工作单位3232', '通讯地址33232', '233000', '18855532979', '18855532646', '54652555@qq.com', '33', '专业领域', '个人简历2121', '推荐单位意见32', NULL, '2019-08-09 18:31:26', '1', NULL, NULL);
INSERT INTO `expert_information` VALUES (16, NULL, '男', '2000-01-02', '本科', '职务111', '技术职称111', '所学专业333', '从事专业222', '工作单位3232', '通讯地址33232', '233000', '18855532979', '18855532646', '54652555@qq.com', '33', '专业领域', '个人简历2121', '推荐单位意见32', NULL, '2019-08-13 16:02:28', '1', NULL, NULL);
INSERT INTO `expert_information` VALUES (17, NULL, '男', '2000-01-02', '本科', '职务111', '技术职称111', '所学专业333', '从事专业222', '工作单位3232', '通讯地址33232', '233000', '18855532979', '18855532646', '54652555@qq.com', '33', '专业领域', '个人简历2121', '推荐单位意见32', NULL, '2019-08-13 16:10:52', '1', NULL, NULL);
INSERT INTO `expert_information` VALUES (18, NULL, '男', '2000-01-02', '本科', '职务111', '技术职称111', '所学专业333', '从事专业222', '工作单位3232', '通讯地址33232', '233000', '18855532979', '18855532646', '54652555@qq.com', '33', '专业领域', '个人简历2121', '推荐单位意见32', NULL, '2019-08-13 16:13:13', '1', NULL, NULL);
INSERT INTO `expert_information` VALUES (19, 28, '男', '2000-01-02', '本科', '职务111', '技术职称111', '所学专业333', '从事专业222', '工作单位3232', '通讯地址33232', '233000', '18855532979', '18855532646', '54652555@qq.com', '33', '专业领域', '个人简历2121', '推荐单位意见32', NULL, '2019-08-13 16:17:25', '1', NULL, NULL);
INSERT INTO `expert_information` VALUES (20, 29, '男', '2000-01-02', '本科', '职务111', '技术职称111', '所学专业333', '从事专业222', '工作单位3232', '通讯地址33232', '233000', '18855532979', '18855532646', '54652555@qq.com', '33', '专业领域', '个人简历2121', '推荐单位意见32', NULL, '2019-08-13 16:21:08', '1', NULL, 114);
INSERT INTO `expert_information` VALUES (21, 31, '男', '2000-01-02', '本科', '职务111', '技术职称111', '所学专业333', '从事专业222', '工作单位3232', '通讯地址33232', '233000', '18855532979', '18855532646', '54652555@qq.com', '1', '1', '个人简历2121', '推荐单位意见32', NULL, '2019-08-13 16:48:37', '1', NULL, 116);
INSERT INTO `expert_information` VALUES (22, 32, '男', '2000-01-02', '本科', '职务111', '技术职称111', '所学专业333', '从事专业222', '工作单位3232', '通讯地址33232', '233000', '18855532979', '18855532646', '54652555@qq.com', '33', '1', '个人简历2121', '推荐单位意见32', NULL, '2019-08-13 16:52:27', '1', '啦啦啦啦啦', 117);
INSERT INTO `expert_information` VALUES (23, 34, '男', '2000-01-02', '666本科', '66职务111', '66技术职称111', '66所学专业333', '66从事专业222', '66工作单位3232', '66通讯地址33232', '66233000', '6618855532979', '618855532646', '564652555@qq.com', '33', '专业领域', '个人简历2121', '推荐单位意见32', NULL, '2019-08-13 21:46:02', '1', NULL, 120);

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
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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

-- ----------------------------
-- Table structure for expert_information_research_direction
-- ----------------------------
DROP TABLE IF EXISTS `expert_information_research_direction`;
CREATE TABLE `expert_information_research_direction`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `expert_id` int(11) NULL DEFAULT NULL COMMENT '专家信息表id',
  `main_research_directions` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主要研究方向',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
-- Table structure for notification
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `start_time` date NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` date NULL DEFAULT NULL COMMENT '结束时间',
  `is_delete` int(20) NULL DEFAULT NULL COMMENT '0:启用  1：逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notification
-- ----------------------------
INSERT INTO `notification` VALUES (1, '课题1', '内容1', '1998-01-14 18:16:05', 'admin', '2000-02-01', '2111-05-01', 0);
INSERT INTO `notification` VALUES (2, '课题1', '内容1', '2019-07-02 18:16:24', 'admin', '1994-06-15', '2111-05-01', 0);
INSERT INTO `notification` VALUES (3, '课题66', '内容12666', '2019-08-05 11:28:04', 'admin', '1221-04-08', '2019-08-12', 0);

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
  `achievement_url_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '成果信息附件url的id',
  `achievement_end_time` date NOT NULL COMMENT '成果结束时间',
  `create_author` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `check_apply_id` int(11) NULL DEFAULT NULL COMMENT '对应验收申请表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of outcome_information
-- ----------------------------
INSERT INTO `outcome_information` VALUES (4, '12121', '课题名称1', '应用单位1', '通讯地址1', '通讯邮编1', '2019-07-03', '应用情况1', '99', '2019-07-18', NULL, NULL, 55);
INSERT INTO `outcome_information` VALUES (5, '5445', '课题名称2', '应用单位2', '通讯地址2', '通讯邮编2', '2019-07-03', '应用情况2', '84', '2019-07-18', NULL, NULL, 55);
INSERT INTO `outcome_information` VALUES (6, '5445', '课题名称1', '应用单位2', '通讯地址2', '通讯邮编2', '2019-07-03', '应用情况2', '85', '2019-07-18', NULL, NULL, 56);
INSERT INTO `outcome_information` VALUES (7, '5445', '课题名称1', '应用单位1', '通讯地址2', '通讯邮编2', '2019-07-03', '应用情况2', '86', '2019-07-18', NULL, NULL, 56);
INSERT INTO `outcome_information` VALUES (8, '5445', '课题名称2', '应用单位2', '通讯地址2', '通讯邮编2', '2019-07-03', '应用情况2', '83', '2019-07-18', NULL, NULL, 56);
INSERT INTO `outcome_information` VALUES (9, '5445', '课题名称2', '应用单位1', '通讯地址2', '通讯邮编2', '2019-07-03', '应用情况2', '84', '2019-07-18', NULL, NULL, 5);
INSERT INTO `outcome_information` VALUES (10, '5445', '课题名称2', '应用单位1', '通讯地址2', '通讯邮编2', '2019-07-03', '应用情况2', '85', '2019-07-18', NULL, NULL, 55);
INSERT INTO `outcome_information` VALUES (11, '课题编号1', '课题名称1', '应用单位名称2', '通讯地址2', '233000', '2000-01-02', '应用情况', '成果附件id', '2222-05-04', '张三', '2019-08-02 11:02:43', 55);
INSERT INTO `outcome_information` VALUES (12, '课题编号0000', '课题名称1', '应用单位名称2', '通讯地址2', '233000', '2000-01-02', '应用情况', '77', '2222-05-04', '张三', '2019-08-02 15:47:11', 56);
INSERT INTO `outcome_information` VALUES (13, '课题编号0000', '课题名称1', '应用单位名称2', '通讯地址2', '233000', '2000-01-02', '应用情况', '成果附件id', '2222-05-04', '张三', '2019-08-02 15:48:08', 55);
INSERT INTO `outcome_information` VALUES (16, '课题编号0000', '课题名称1', '应用单位名称2', '通讯地址2', '233000', '2000-01-02', '应用情况', '成果附件id', '2222-05-04', '李四', '2019-08-02 16:13:07', 53);
INSERT INTO `outcome_information` VALUES (23, '课题编号0454540', '课题名称1', '应用单位名称2', '通讯地址2', '233000', '2000-01-02', '应用情况', '成果附件id', '2222-05-04', '李四', '2019-08-02 16:39:04', 55);
INSERT INTO `outcome_information` VALUES (24, '课题编号22222222', '课题名称1', '应用单位名称2', '通讯地址2', '233000', '2000-01-02', '应用情况', '成果附件id', '2222-05-04', '王五', '2019-08-02 18:01:04', 56);

-- ----------------------------
-- Table structure for outcome_information_paper
-- ----------------------------
DROP TABLE IF EXISTS `outcome_information_paper`;
CREATE TABLE `outcome_information_paper`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `achievements_id` int(11) NOT NULL COMMENT '成果信息id',
  `serial_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '序号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `publication` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '刊物',
  `publication_time` date NOT NULL COMMENT '发表时间',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `paper_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '论文级别（SCI/EI/核心等）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of outcome_information_paper
-- ----------------------------
INSERT INTO `outcome_information_paper` VALUES (1, 4, '213544', '第一名称', '第一刊物', '1785-04-24', '第一作者', '第一论文级别');
INSERT INTO `outcome_information_paper` VALUES (2, 4, '4456512', '第二名称', '第二刊物', '1785-04-24', '第二作者', '第二论文级别');
INSERT INTO `outcome_information_paper` VALUES (3, 4, '213544', '第三名称', '第三刊物', '1785-04-24', '第三作者', '第三论文级别');
INSERT INTO `outcome_information_paper` VALUES (4, 12, '论文序号666', '论文名称1', '论文刊物1', '2011-02-03', '论文作者1', '论文级别1');
INSERT INTO `outcome_information_paper` VALUES (5, 12, '论文序号777', '论文名称2', '论文刊物2', '2011-02-03', '论文作者2', '论文级别2');
INSERT INTO `outcome_information_paper` VALUES (6, 23, '论文序号55555', '论文名称5555', '论文刊物1', '2011-02-03', '论文作者1', '论文级别1');
INSERT INTO `outcome_information_paper` VALUES (7, 23, '论文序号4545', '论文名称2', '论文刊物2', '2011-02-03', '论文作者2', '论文级别2');
INSERT INTO `outcome_information_paper` VALUES (8, 24, '论文序号233333', '论文名称5555', '论文刊物1', '2011-02-03', '论文作者1', '论文级别1');
INSERT INTO `outcome_information_paper` VALUES (9, 24, '论文序号33222', '论文名称2', '论文刊物2', '2011-02-03', '论文作者2', '论文级别2');

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
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of outcome_information_patent
-- ----------------------------
INSERT INTO `outcome_information_patent` VALUES (1, 4, '115623', '第一成果名称', '1745-12-24', '第一专利号');
INSERT INTO `outcome_information_patent` VALUES (2, 4, '1156156', '第二成果名称', '1745-12-24', '第二专利号');
INSERT INTO `outcome_information_patent` VALUES (3, 4, '11516', '第三成果名称', '1745-12-24', '第三专利号');
INSERT INTO `outcome_information_patent` VALUES (4, 12, '专利序号6666', '专利名称1', '2000-03-05', '专利号');
INSERT INTO `outcome_information_patent` VALUES (5, 12, '专利序号7777', '专利名称2', '2000-03-05', '专利号2');
INSERT INTO `outcome_information_patent` VALUES (6, 23, '专利序号65464', '专利名称1', '2000-03-05', '专利号');
INSERT INTO `outcome_information_patent` VALUES (7, 23, '专利序号345435', '专利名称2', '2000-03-05', '专利号2');
INSERT INTO `outcome_information_patent` VALUES (8, 24, '专利序号23333', '专利名称1', '2000-03-05', '专利号');
INSERT INTO `outcome_information_patent` VALUES (9, 24, '专利序号32222', '专利名称2', '2000-03-05', '专利号2');

-- ----------------------------
-- Table structure for shiro_company_name
-- ----------------------------
DROP TABLE IF EXISTS `shiro_company_name`;
CREATE TABLE `shiro_company_name`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公司id',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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

-- ----------------------------
-- Table structure for shiro_permission
-- ----------------------------
DROP TABLE IF EXISTS `shiro_permission`;
CREATE TABLE `shiro_permission`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `explain` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限的解释',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shiro_permission
-- ----------------------------
INSERT INTO `shiro_permission` VALUES (1, 'add', NULL);
INSERT INTO `shiro_permission` VALUES (2, 'delete', NULL);
INSERT INTO `shiro_permission` VALUES (3, 'edit', NULL);
INSERT INTO `shiro_permission` VALUES (4, 'query', NULL);
INSERT INTO `shiro_permission` VALUES (5, 'changePassword', '后台管理中修改自己的密码');
INSERT INTO `shiro_permission` VALUES (6, 'distributeAccount', '公司管理员分配子账号');
INSERT INTO `shiro_permission` VALUES (7, 'applyCheck', '验收申请');
INSERT INTO `shiro_permission` VALUES (8, 'checkQuery', '验收查询');
INSERT INTO `shiro_permission` VALUES (9, 'checkAudit', '验收审核');
INSERT INTO `shiro_permission` VALUES (10, 'checkResultQuery', '验收最终查询');
INSERT INTO `shiro_permission` VALUES (11, 'submitFinalReport', '提交最终报告');
INSERT INTO `shiro_permission` VALUES (12, 'uploadExpertFile', '上传专家文件');

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
INSERT INTO `shiro_permission_role` VALUES (1, 10);
INSERT INTO `shiro_permission_role` VALUES (1, 11);
INSERT INTO `shiro_permission_role` VALUES (1, 12);
INSERT INTO `shiro_permission_role` VALUES (2, 5);
INSERT INTO `shiro_permission_role` VALUES (2, 7);
INSERT INTO `shiro_permission_role` VALUES (2, 8);
INSERT INTO `shiro_permission_role` VALUES (2, 9);
INSERT INTO `shiro_permission_role` VALUES (2, 10);
INSERT INTO `shiro_permission_role` VALUES (2, 11);
INSERT INTO `shiro_permission_role` VALUES (2, 12);
INSERT INTO `shiro_permission_role` VALUES (3, 5);

-- ----------------------------
-- Table structure for shiro_role
-- ----------------------------
DROP TABLE IF EXISTS `shiro_role`;
CREATE TABLE `shiro_role`  (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shiro_role
-- ----------------------------
INSERT INTO `shiro_role` VALUES (1, 'administrator');
INSERT INTO `shiro_role` VALUES (2, 'staff');
INSERT INTO `shiro_role` VALUES (3, 'expert');

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
  `identity` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份( 0：管理员 1：员工 2：专家)',
  `is_delete` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否启用（0：启用 1：停用）\r\n当管理员注册，管理员给分配子账号，内网分配专家账号时，默认为启用',
  `is_first` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否第一次登陆（0：是第一次登陆  1：多次登陆）',
  `is_state` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否审核通过  （1：审核通过 2：等待审核  3：审核未通过）\r\n内网分配账号时，默认为审核通过\r\n只有当外网注册专家时，才需要内网进行审核',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shiro_user_information
-- ----------------------------
INSERT INTO `shiro_user_information` VALUES (1, '真实姓名', '登陆名', '21232f297a57a5a743894a0e4a801fc3', '0', '0', '1', '1');
INSERT INTO `shiro_user_information` VALUES (2, '真实姓名', '登陆名11', '0fe359d5924e09441ad054236bd47528', '0', '0', '1', '1');
INSERT INTO `shiro_user_information` VALUES (5, '真实姓名', '登陆名1221', '0fe359d5924e09441ad054236bd47528', '0', '0', '1', '1');
INSERT INTO `shiro_user_information` VALUES (6, '真实专家姓名', '登陆名656565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '1', '1', '2');
INSERT INTO `shiro_user_information` VALUES (7, '真实专家姓名', '登陆名656323565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '1', '1', '2');
INSERT INTO `shiro_user_information` VALUES (9, '真实专家姓名', '登陆名6563fsgsdf23565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '1', '1', '2');
INSERT INTO `shiro_user_information` VALUES (11, '真实专家姓名', '6767登陆名6563fsgsdf23565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '1', '1', '2');
INSERT INTO `shiro_user_information` VALUES (12, '真实姓名', '登录名11', '21232f297a57a5a743894a0e4a801fc3', '2', '0', '0', '1');
INSERT INTO `shiro_user_information` VALUES (14, '真实姓名', '登录名1dfd1', '登录名1dfd1@123', '2', '0', '0', '1');
INSERT INTO `shiro_user_information` VALUES (15, '真实姓名', '登录名1dasdffd1', '登录名1dasdffd1@123', '2', '0', '0', '1');
INSERT INTO `shiro_user_information` VALUES (16, '真实姓名121', 'zhangsan1212', 'zhangsan1212@123', '1', '1', '0', '1');
INSERT INTO `shiro_user_information` VALUES (18, '真实姓名', '登陆名1221222', '0fe359d5924e09441ad054236bd47528', '0', '0', '1', '1');
INSERT INTO `shiro_user_information` VALUES (25, '真实专家姓名', '11116767登陆名6563fsgsdf23565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '1', '1', '2');
INSERT INTO `shiro_user_information` VALUES (26, '真实专家姓名', '2222211116767登陆名6563fsgsdf23565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '1', '1', '2');
INSERT INTO `shiro_user_information` VALUES (27, '真实专家姓名', '4333332222211116767登陆名6563fsgsdf23565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '1', '1', '2');
INSERT INTO `shiro_user_information` VALUES (28, '真实专家姓名', '43hfir7登陆名6563fsgsdf23565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '1', '1', '2');
INSERT INTO `shiro_user_information` VALUES (29, '真实专家姓名', 'fdfdfd43hfir7登陆名6563fsgsdf23565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '1', '1', '2');
INSERT INTO `shiro_user_information` VALUES (31, '真实专家姓名', 'fdfdfd登陆名6563fsgsdf23565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '1', '1', '1');
INSERT INTO `shiro_user_information` VALUES (32, '真实专家姓名', 'erueirefd登陆名6563fsgsdf23565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '0', '1', '1');
INSERT INTO `shiro_user_information` VALUES (33, '真实专家姓名', 'f5tg5t登陆名6563fsgsdf23565', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '0', '0', '1');
INSERT INTO `shiro_user_information` VALUES (34, '真实专家姓名', 'fdf3f3ff', 'ae3aaa6f80543e490d9ff9b0ede59a92', '2', '0', '0', '1');

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
INSERT INTO `staff_information` VALUES (4, 15, '假的公司名', 4, '18877763212', '3223jui@qq.com', '340303199809391283', 92, '2019-08-10 23:24:28', '假的创建人名');
INSERT INTO `staff_information` VALUES (5, 16, '测试公司名', 4, '18855532979', '1555343555@qq.com', '340303199609280415', 93, '2019-08-13 14:15:01', '测试人名');

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
) ENGINE = InnoDB AUTO_INCREMENT = 140 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `upload_file` VALUES (83, NULL, 'D:/xdmd_environment/null/验收申请/2019-08-12员工身份证.rar', '员工身份证.rar', NULL, '验收申请', 'rar', '24', '2019-08-12 20:55:17', '测试的人名');
INSERT INTO `upload_file` VALUES (84, NULL, 'D:/xdmd_environment/null/成果附件/2019-08-12员工身份证.rar', '员工身份证.rar', NULL, '成果附件', 'rar', '24', '2019-08-12 20:55:17', '测试的人名');
INSERT INTO `upload_file` VALUES (85, NULL, 'D:/xdmd_environment/null/提交清单/2019-08-12员工身份证.rar', '员工身份证.rar', NULL, '提交清单', 'rar', '24', '2019-08-12 20:55:17', '测试的人名');
INSERT INTO `upload_file` VALUES (86, NULL, 'D:/xdmd_environment/null/验收申请/2019-08-12员工身份证.rar', '员工身份证.rar', NULL, '验收申请', 'rar', '24', '2019-08-12 20:58:45', '测试的人名');
INSERT INTO `upload_file` VALUES (87, NULL, 'D:/xdmd_environment/null/成果附件/2019-08-12员工身份证.rar', '员工身份证.rar', NULL, '成果附件', 'rar', '24', '2019-08-12 20:58:45', '测试的人名');
INSERT INTO `upload_file` VALUES (88, NULL, 'D:/xdmd_environment/null/提交清单/2019-08-12员工身份证.rar', '员工身份证.rar', NULL, '提交清单', 'rar', '24', '2019-08-12 20:58:45', '测试的人名');
INSERT INTO `upload_file` VALUES (89, NULL, 'D:/xdmd_environment/王六公司/验收证书/2019-08-12最终证书.rar', '最终证书.rar', NULL, '验收证书', 'rar', '24', '2019-08-12 23:38:18', '王六');
INSERT INTO `upload_file` VALUES (90, NULL, 'D:/xdmd_environment/王六公司/验收证书/2019-08-12最终证书.rar', '最终证书.rar', NULL, '验收证书', 'rar', '24', '2019-08-12 23:42:29', '王六');
INSERT INTO `upload_file` VALUES (91, NULL, 'D:/xdmd_environment/王六公司/验收证书/2019-08-12最终证书.rar', '最终证书.rar', NULL, '验收证书', 'rar', '24', '2019-08-12 23:43:48', '王六');
INSERT INTO `upload_file` VALUES (92, NULL, 'D:/xdmd_environment/测试公司名/员工信息扫描件/2019-08-13员工身份证文件.zip', '员工身份证文件.zip', NULL, '员工信息扫描件', 'zip', '22', '2019-08-13 14:15:02', '测试人名');
INSERT INTO `upload_file` VALUES (93, NULL, 'D:/xdmd_environment/修改公司名/员工信息扫描件/2019-08-13测试压缩文件.zip', '测试压缩文件.zip', NULL, '员工信息扫描件', 'zip', '5982', '2019-08-13 14:48:56', '修改人名');
INSERT INTO `upload_file` VALUES (97, NULL, 'D:/xdmd_environment/公司名称1221/营业执照/2019-08-13测试压缩文件.zip', '测试压缩文件.zip', NULL, '营业执照', 'zip', '5982', '2019-08-13 15:05:17', '真实姓名');
INSERT INTO `upload_file` VALUES (98, NULL, 'D:/xdmd_environment/公司名称1221/法人身份证文件/2019-08-13测试压缩文件.zip', '测试压缩文件.zip', NULL, '法人身份证文件', 'zip', '5982', '2019-08-13 15:05:17', '真实姓名');
INSERT INTO `upload_file` VALUES (99, NULL, 'D:/xdmd_environment/公司名称1221/联系人身份证文件/2019-08-13测试压缩文件.zip', '测试压缩文件.zip', NULL, '联系人身份证文件', 'zip', '5982', '2019-08-13 15:05:17', '真实姓名');
INSERT INTO `upload_file` VALUES (107, NULL, 'D:/xdmd_environment/专家信息库/专家信息表.zip', '专家信息表.zip', NULL, '专家信息库', 'zip', '0', '2019-08-13 16:02:29', '真实专家姓名');
INSERT INTO `upload_file` VALUES (111, NULL, 'D:/xdmd_environment/专家信息库/测试压缩文件.zip', '测试压缩文件.zip', NULL, '专家信息库', 'zip', '0', '2019-08-13 16:10:53', '真实专家姓名');
INSERT INTO `upload_file` VALUES (112, NULL, 'D:/xdmd_environment/专家信息库/初审报告.zip', '初审报告.zip', NULL, '专家信息库', 'zip', '0', '2019-08-13 16:12:56', '真实专家姓名');
INSERT INTO `upload_file` VALUES (113, NULL, 'D:/xdmd_environment/专家信息库/测试压缩文件.zip', '测试压缩文件.zip', NULL, '专家信息库', 'zip', '0', '2019-08-13 16:17:25', '真实专家姓名');
INSERT INTO `upload_file` VALUES (114, NULL, 'D:/xdmd_environment/专家信息库/初审报告.zip', '初审报告.zip', NULL, '专家信息库', 'zip', '0', '2019-08-13 16:21:08', '真实专家姓名');
INSERT INTO `upload_file` VALUES (116, NULL, 'D:/xdmd_environment/专家信息库/提交清单3.zip', '提交清单3.zip', NULL, '专家信息库', 'zip', '0', '2019-08-13 16:48:38', '真实专家姓名');
INSERT INTO `upload_file` VALUES (117, NULL, 'D:/xdmd_environment/专家信息库/法人身份证.zip', '法人身份证.zip', NULL, '专家信息库', 'zip', '0', '2019-08-13 16:52:28', '真实专家姓名');
INSERT INTO `upload_file` VALUES (118, NULL, 'D:/xdmd_environment/专家信息库/最终证书.rar', '最终证书.rar', NULL, '专家信息库', 'rar', '0', '2019-08-13 21:42:25', '真实专家姓名');
INSERT INTO `upload_file` VALUES (119, NULL, 'D:/xdmd_environment/专家信息库/最终证书.rar', '最终证书.rar', NULL, '专家信息库', 'rar', '0', '2019-08-13 21:46:02', '真实专家姓名');
INSERT INTO `upload_file` VALUES (120, NULL, 'D:/xdmd_environment/专家信息库/2019-08-15测试压缩文件.zip', '测试压缩文件.zip', NULL, '专家信息库', 'zip', '5982', '2019-08-15 10:23:23', '测试用的人名');
INSERT INTO `upload_file` VALUES (121, NULL, 'D:/xdmd_environment/人名测试/提交清单/2019-08-15测试压缩文件.zip', '测试压缩文件.zip', NULL, '提交清单', 'zip', '5982', '2019-08-15 14:44:54', '公司测试');
INSERT INTO `upload_file` VALUES (122, NULL, 'D:/xdmd_environment/人名测试/提交清单/2019-08-15测试压缩文件.zip', '测试压缩文件.zip', NULL, '提交清单', 'zip', '5982', '2019-08-15 14:46:06', '公司测试');
INSERT INTO `upload_file` VALUES (123, NULL, 'D:/xdmd_environment/null/验收申请/2019-08-15联系人身份证.zip', '联系人身份证.zip', NULL, '验收申请', 'zip', '22', '2019-08-15 15:06:27', '测试的人名');
INSERT INTO `upload_file` VALUES (124, NULL, 'D:/xdmd_environment/null/成果附件/2019-08-15联系人身份证.zip', '联系人身份证.zip', NULL, '成果附件', 'zip', '22', '2019-08-15 15:06:27', '测试的人名');
INSERT INTO `upload_file` VALUES (125, NULL, 'D:/xdmd_environment/null/提交清单/2019-08-15联系人身份证.zip', '联系人身份证.zip', NULL, '提交清单', 'zip', '22', '2019-08-15 15:06:27', '测试的人名');
INSERT INTO `upload_file` VALUES (126, NULL, 'D:/xdmd_environment/null/验收申请/2019-08-15联系人身份证.zip', '联系人身份证.zip', NULL, '验收申请', 'zip', '22', '2019-08-15 15:09:19', '测试的人名');
INSERT INTO `upload_file` VALUES (127, NULL, 'D:/xdmd_environment/null/成果附件/2019-08-15联系人身份证.zip', '联系人身份证.zip', NULL, '成果附件', 'zip', '22', '2019-08-15 15:09:19', '测试的人名');
INSERT INTO `upload_file` VALUES (128, NULL, 'D:/xdmd_environment/null/提交清单/2019-08-15联系人身份证.zip', '联系人身份证.zip', NULL, '提交清单', 'zip', '22', '2019-08-15 15:09:19', '测试的人名');
INSERT INTO `upload_file` VALUES (129, NULL, 'D:/xdmd_environment/null/验收申请/2019-08-15联系人身份证.zip', '联系人身份证.zip', NULL, '验收申请', 'zip', '22', '2019-08-15 15:10:00', '测试的人名');
INSERT INTO `upload_file` VALUES (130, NULL, 'D:/xdmd_environment/null/成果附件/2019-08-15联系人身份证.zip', '联系人身份证.zip', NULL, '成果附件', 'zip', '22', '2019-08-15 15:10:00', '测试的人名');
INSERT INTO `upload_file` VALUES (131, NULL, 'D:/xdmd_environment/null/提交清单/2019-08-15联系人身份证.zip', '联系人身份证.zip', NULL, '提交清单', 'zip', '22', '2019-08-15 15:10:00', '测试的人名');
INSERT INTO `upload_file` VALUES (132, NULL, 'D:/xdmd_environment/null/验收申请/2019-08-15联系人身份证.zip', '联系人身份证.zip', NULL, '验收申请', 'zip', '22', '2019-08-15 15:12:41', '测试的人名');
INSERT INTO `upload_file` VALUES (133, NULL, 'D:/xdmd_environment/null/成果附件/2019-08-15联系人身份证.zip', '联系人身份证.zip', NULL, '成果附件', 'zip', '22', '2019-08-15 15:12:41', '测试的人名');
INSERT INTO `upload_file` VALUES (134, NULL, 'D:/xdmd_environment/null/提交清单/2019-08-15联系人身份证.zip', '联系人身份证.zip', NULL, '提交清单', 'zip', '22', '2019-08-15 15:12:41', '测试的人名');
INSERT INTO `upload_file` VALUES (135, NULL, 'D:/xdmd_environment/null/验收申请/2019-08-15初审报告.zip', '初审报告.zip', NULL, '验收申请', 'zip', '22', '2019-08-15 15:13:11', '测试的人名');
INSERT INTO `upload_file` VALUES (136, NULL, 'D:/xdmd_environment/null/成果附件/2019-08-15初审报告.zip', '初审报告.zip', NULL, '成果附件', 'zip', '22', '2019-08-15 15:13:11', '测试的人名');
INSERT INTO `upload_file` VALUES (137, NULL, 'D:/xdmd_environment/null/提交清单/2019-08-15初审报告.zip', '初审报告.zip', NULL, '提交清单', 'zip', '22', '2019-08-15 15:13:11', '测试的人名');
INSERT INTO `upload_file` VALUES (138, NULL, 'D:/xdmd_environment/测试公司名/专家组评议文件/2019-08-16测试压缩文件.zip', '测试压缩文件.zip', NULL, '专家组评议文件', 'zip', '5982', '2019-08-16 14:00:14', '测试人名');
INSERT INTO `upload_file` VALUES (139, NULL, 'D:/xdmd_environment/测试公司名/专家组评议文件/2019-08-16测试压缩文件.zip', '测试压缩文件.zip', NULL, '专家组评议文件', 'zip', '5982', '2019-08-16 14:06:01', '测试人名');
INSERT INTO `upload_file` VALUES (140, NULL, 'D:/xdmd_environment/测试公司名/专家组评议文件/2019-08-16测试压缩文件.zip', '测试压缩文件.zip', NULL, '专家组评议文件', 'zip', '5982', '2019-08-16 14:13:32', '测试人名');
INSERT INTO `upload_file` VALUES (141, NULL, 'D:/xdmd_environment/测试公司名/专家组评议文件/2019-08-16测试压缩文件.zip', '测试压缩文件.zip', NULL, '专家组评议文件', 'zip', '5982', '2019-08-16 14:14:42', '测试人名');
INSERT INTO `upload_file` VALUES (142, NULL, 'D:/xdmd_environment/测试公司名/专家组评议文件/2019-08-16测试压缩文件.zip', '测试压缩文件.zip', NULL, '专家组评议文件', 'zip', '5982', '2019-08-16 14:15:29', '测试人名');
INSERT INTO `upload_file` VALUES (143, NULL, 'D:/xdmd_environment/测试公司名/专家组评议文件/2019-08-16测试压缩文件.zip', '测试压缩文件.zip', NULL, '专家组评议文件', 'zip', '5982', '2019-08-16 14:17:02', '测试人名');
INSERT INTO `upload_file` VALUES (144, NULL, 'D:/xdmd_environment/测试公司名/专家组评议文件/2019-08-16测试压缩文件.zip', '测试压缩文件.zip', NULL, '专家组评议文件', 'zip', '5982', '2019-08-16 14:20:46', '测试人名');

SET FOREIGN_KEY_CHECKS = 1;
