package com.xdmd.IntranetEnvironment.contractmanage.mapper;


import com.xdmd.IntranetEnvironment.contractmanage.pojo.KeyResearchDevelopersDTO;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * @author: Kong
 * @createDate: 2019/08/08
 * @description: 主要开发人员sql工具类
 */
public class KeyResearchDevlpersProvider {
    /**
     * 新增主要开发人员信息【批量插入】
     *
     * @param map
     * @return
     */
    public String batchInsertKeyDev(Map map) {
        List<KeyResearchDevelopersDTO> summaryList = (List<KeyResearchDevelopersDTO>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO key_research_developers VALUES");
        MessageFormat mf = new MessageFormat(
                "(DEFAULT," +
                        "#'{'list[{0}].contractId},#'{'list[{0}].keyDevName},#'{'list[{0}].unitName}," +
                        "#'{'list[{0}].gender},#'{'list[{0}].age},#'{'list[{0}].professionalTitle}," +
                        "#'{'list[{0}].professional},#'{'list[{0}].workTask},#'{'list[{0}].workingTime})"
        );
        for (int i = 0; i < summaryList.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < summaryList.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}

