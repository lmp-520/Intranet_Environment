package com.xdmd.IntranetEnvironment.Demo.controller;

import com.xdmd.IntranetEnvironment.Demo.mapper.TestMapper;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RestController
@RequestMapping("test")
public class testController {
    @Autowired
    private TestMapper testMapper;

    @PostMapping("one")
    public String test(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);

        List<Integer> ids = testMapper.test(format);


    }
}
