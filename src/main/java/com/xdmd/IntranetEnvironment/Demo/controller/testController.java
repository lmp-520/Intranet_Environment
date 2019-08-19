package com.xdmd.IntranetEnvironment.Demo.controller;

import com.xdmd.IntranetEnvironment.Demo.mapper.TestMapper;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RestController
@RequestMapping("test")
public class testController {
    @Autowired
    private TestMapper testMapper;

    @PostMapping("one")
    public String test() throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowTime = sdf.format(date);

        List<Integer> ids = testMapper.test(nowTime);

        for (Integer id : ids) {
            String contractEndTime = testMapper.queryContractEndTime(id);

            Date parse = sdf.parse(contractEndTime);

            Calendar cal = Calendar.getInstance();
            cal.setTime(parse);
            cal.add(Calendar.MONTH,3);

            String dateOver = sdf.format(cal.getTime());

            System.out.println(dateOver);
        }


        return "aaa";


    }
}
