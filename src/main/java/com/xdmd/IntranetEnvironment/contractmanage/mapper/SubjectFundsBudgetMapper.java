package com.xdmd.IntranetEnvironment.contractmanage.mapper;

import com.xdmd.IntranetEnvironment.contractmanage.pojo.SubjectFundsBudgetDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/08/06
 * @description: 课题经费预算数据层
 */
@Repository
public interface SubjectFundsBudgetMapper {
    /**
     * [新增]
     * @author Kong
     * @date 2019/08/06
     **/
    @Insert(value = "insert into subject_funds_budget\n" +
            "VALUES(\n" +
            "DEFAULT," +
            "#{contractId},\n" +
            "#{fundingSourcesBudget},\n" +
            "#{currentYear},\n" +
            "#{nextYear},\n" +
            "#{afterYear},\n" +
            "#{fundingSourcesNote},\n" +
            "#{currentYearSourceTotal},\n" +
            "#{nextYearSourceTotal},\n" +
            "#{afterYearSourceTotal},\n" +
            "#{provincialBudget},\n" +
            "#{provincialCurrentBudget},\n" +
            "#{provincialNextBudget},\n" +
            "#{provincialAfterBudget},\n" +
            "#{provincialNoteBudget},\n" +
            "#{departmentBudget},\n" +
            "#{departmentCurrentBudget},\n" +
            "#{departmentNextBudget},\n" +
            "#{departmentAfterBudget},\n" +
            "#{departmentNoteBudget},\n" +
            "#{bearBudget},\n" +
            "#{bearCurrentBudget},\n" +
            "#{bearNextBudget},\n" +
            "#{bearAfterBudget},\n" +
            "#{bearNoteBudget},\n" +
            "#{otherBudget},\n" +
            "#{otherCurrentBudget},\n" +
            "#{otherNextBudget},\n" +
            "#{otherAfterBudget},\n" +
            "#{otherNoteBudget},\n" +
            "#{expenditureBudget},\n" +
            "#{currentYearExpenditureTotal},\n" +
            "#{nextYearExpenditureTotal},\n" +
            "#{afterYearExpenditureTotal},\n" +
            "#{selfTotalExpenditures},\n" +
            "#{totalExpendituresNote},\n" +
            "#{equipmentBudget},\n" +
            "#{equipmentCurrentBudget},\n" +
            "#{equipmentNextBudget},\n" +
            "#{equipmentAfterBudget},\n" +
            "#{equipmentSupportingBudget},\n" +
            "#{equipmentNoteBudget},\n" +
            "#{materialBudget},\n" +
            "#{materialCurrentBudget},\n" +
            "#{materialNextBudget},\n" +
            "#{materialAfterBudget},\n" +
            "#{materialSupportingBudget},\n" +
            "#{materialNoteBudget},\n" +
            "#{testBudget},\n" +
            "#{testCurrentBudget},\n" +
            "#{testNextBudget},\n" +
            "#{testAfterBudget},\n" +
            "#{testSupportingBudget},\n" +
            "#{testNoteBudget},\n" +
            "#{fuelBudget},\n" +
            "#{fuelCurrentBudget},\n" +
            "#{fuelNextBudget},\n" +
            "#{fuelAfterBudget},\n" +
            "#{fuelSupportingBudget},\n" +
            "#{fuelNoteBudget},\n" +
            "#{mettingBudget},\n" +
            "#{mettingCurrentBudget},\n" +
            "#{mettingNextBudget},\n" +
            "#{mettingAfterBudget},\n" +
            "#{mettingSupportingBudget},\n" +
            "#{mettingNoteBudget},\n" +
            "#{laborBudget},\n" +
            "#{laborCurrentBudget},\n" +
            "#{laborNextBudget},\n" +
            "#{laborAfterBudget},\n" +
            "#{laborSupportingBudget},\n" +
            "#{laborNoteBudget},\n" +
            "#{expertsBudget},\n" +
            "#{expertsCurrentBudget},\n" +
            "#{expertsNextBudget},\n" +
            "#{expertsAfterBudget},\n" +
            "#{expertsSupportingBudget},\n" +
            "#{expertsNoteBudget},\n" +
            "#{dailyBudget},\n" +
            "#{dailyCurrentBudget},\n" +
            "#{dailyNextBudget},\n" +
            "#{dailyAfterBudget},\n" +
            "#{dailySupportingBudget},\n" +
            "#{dailyNoteBudget}\n)")
    int insert(SubjectFundsBudgetDTO subjectFundsBudgetDTO);

    /**
     * [查詢] 根據合同管理id查詢
     * @author Kong
     * @date 2019/08/06
     **/
    @Select(value = "select sfb.* from subject_funds_budget as sfb,contract_manage as cm where sfb.contract_id=cm.id and cm.id=#{id}")
    SubjectFundsBudgetDTO getBudgetInfoById(@Param("id") int id);

    /**
     * [查詢] 获取全部预算信息
     * @return
     */
    @Select(value = "select * from subject_funds_budget")
    List<SubjectFundsBudgetDTO> getAllInfo()

    /**
     * 不通过被退回时重新提交[修改]
     * @param subjectFundsBudgetDTO
     * @return
     */
    @Update("update subject_funds_budget SET\n" +
            "funding_sources_budget=#{fundingSourcesBudget},\n" +
            "current_year=#{currentYear},\n" +
            "next_year=#{nextYear},\n" +
            "after_year=#{afterYear},\n" +
            "funding_sources_note=#{fundingSourcesNote},\n" +
            "current_year_source_total=#{currentYearSourceTotal},\n" +
            "next_year_source_total=#{nextYearSourceTotal},\n" +
            "after_year_source_total=#{afterYearSourceTotal},\n" +
            "provincial_budget=#{provincialBudget},\n" +
            "provincial_current_budget=#{provincialCurrentBudget},\n" +
            "provincial_next_budget=#{provincialNextBudget},\n" +
            "provincial_after_budget=#{provincialAfterBudget},\n" +
            " provincial_note_budget=#{ provincialNoteBudget },\n" +
            "department_budget=#{departmentBudget},\n" +
            "department_current_budget=#{departmentCurrentBudget},\n" +
            "department_next_budget=#{departmentNextBudget},\n" +
            "department_after_budget=#{departmentAfterBudget},\n" +
            "department_note_budget=#{departmentNoteBudget},\n" +
            "bear_budget=#{bearBudget},\n" +
            "bear_current_budget=#{bearCurrentBudget},\n" +
            "bear_next_budget=#{bearNextBudget},\n" +
            "bear_after_budget=#{bearAfterBudget},\n" +
            "bear_note_budget=#{bearNoteBudget},\n" +
            "other_budget=#{otherBudget},\n" +
            "other_current_budget=#{otherCurrentBudget},\n" +
            "other_next_budget=#{otherNextBudget},\n" +
            "other_after_budget=#{otherAfterBudget},\n" +
            "other_note_budget=#{otherNoteBudget},\n" +
            "expenditure_budget=#{expenditureBudget},\n" +
            "current_year_expenditure_total=#{currentYearExpenditureTotal},\n" +
            "next_year_expenditure_total=#{nextYearExpenditureTotal},\n" +
            "after_year_expenditure_total=#{afterYearExpenditureTotal},\n" +
            "self_total_expenditures=#{selfTotalExpenditures},\n" +
            "total_expenditures_note=#{totalExpendituresNote},\n" +
            "equipment_budget=#{equipmentBudget},\n" +
            "equipment_current_budget=#{equipmentCurrentBudget},\n" +
            "equipment_next_budget=#{equipmentNextBudget},\n" +
            "equipment_after_budget=#{equipmentAfterBudget},\n" +
            "equipment_supporting_budget=#{equipmentSupportingBudget},\n" +
            "equipment_note_budget=#{equipmentNoteBudget},\n" +
            "material_budget=#{materialBudget},\n" +
            "material_current_budget=#{materialCurrentBudget},\n" +
            "material_next_budget=#{materialNextBudget}, \n" +
            "material_after_budget=#{materialAfterBudget},\n" +
            "material_supporting_budget=#{materialSupportingBudget},\n" +
            "material_note_budget=#{materialNoteBudget},\n" +
            "test_budget=#{testBudget},\n" +
            "test_current_budget=#{testCurrentBudget},\n" +
            "test_next_budget=#{testNextBudget},\n" +
            "test_after_budget=#{testAfterBudget},\n" +
            "test_supporting_budget=#{testSupportingBudget},\n" +
            "test_note_budget=#{testNoteBudget},\n" +
            "fuel_budget=#{fuelBudget},\n" +
            "fuel_current_budget=#{fuelCurrentBudget},\n" +
            "fuel_next_budget=#{fuelNextBudget},\n" +
            "fuel_after_budget=#{fuelAfterBudget},\n" +
            "fuel_supporting_budget=#{fuelAfterBudget},\n" +
            "fuel_note_budget=#{fuelNoteBudget},\n" +
            "metting_budget=#{mettingBudget},\n" +
            "metting_current_budget=#{mettingCurrentBudget},\n" +
            "metting_next_budget=#{mettingNextBudget},\n" +
            "metting_after_budget=#{mettingAfterBudget},\n" +
            "metting_supporting_budget=#{mettingSupportingBudget},\n" +
            "metting_note_budget=#{mettingNoteBudget},\n" +
            "labor_budget=#{laborBudget},\n" +
            "labor_current_budget=#{laborCurrentBudget},\n" +
            "labor_next_budget=#{laborNextBudget},\n" +
            "labor_after_budget=#{laborAfterBudget},\n" +
            "labor_supporting_budget=#{laborSupportingBudget},\n" +
            "labor_note_budget=#{laborNoteBudget},\n" +
            "experts_budget=#{expertsBudget},\n" +
            "experts_current_budget=#{expertsCurrentBudget},\n" +
            "experts_next_budget=#{expertsNextBudget},\n" +
            "experts_after_budget=#{expertsAfterBudget},\n" +
            "experts_supporting_budget=#{expertsSupportingBudget},\n" +
            "experts_note_budget=#{expertsNoteBudget},\n" +
            "daily_budget=#{dailyBudget},\n" +
            "daily_current_budget=#{dailyCurrentBudget},\n" +
            "daily_next_budget=#{dailyNextBudget},\n" +
            "daily_after_budget=#{dailyAfterBudget},\n" +
            "daily_supporting_budget=#{dailySupportingBudget},\n" +
            "daily_note_budget=#{dailyNoteBudget}\n" +
            "WHERE\n" +
            "contract_id=#{contractId}\n")
    int UpdateSubjectFundsBudget(SubjectFundsBudgetDTO subjectFundsBudgetDTO);
  
}
