package com.xdmd.IntranetEnvironment.company.Controller;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.company.Pojo.CreditCodeRegex;
import com.xdmd.IntranetEnvironment.company.Pojo.UserInformation;
import com.xdmd.IntranetEnvironment.company.Service.CompanyServiceTwo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("company")
public class CompanyControllerTwo {

    @Autowired
    private CompanyServiceTwo companyServiceTwo;
    ResultMap resultMap = new ResultMap();
    private static Logger log = LoggerFactory.getLogger(CompanyControllerTwo.class);

    //公司的注册
    @ResponseBody
    @PostMapping("register")
    public ResultMap companyRegister(//@CookieValue(value = "check", required = false) String check,
                                     @Valid @RequestPart UserInformation userInformation, BindingResult result,
                                    // @RequestParam("code") String code,
                                     @RequestPart("businessFile") MultipartFile businessFile, //营业执照扫描件
                                     @RequestPart("legalCardIdFile") MultipartFile legalCardIdFile,//法人身份证文件
                                     @RequestPart("contactCardFile") MultipartFile contactCardFile) { //联系人身份证文件
        if (!businessFile.getOriginalFilename().contains(".") || !legalCardIdFile.getOriginalFilename().contains(".") || !contactCardFile.getOriginalFilename().contains(".")) {
            return resultMap.fail().message("上传的文件不可以为空");
        }

//        if (!check.equalsIgnoreCase(code)) {
//            //用户输入的验证码有误
//            return resultMap.fail().message("输入的验证码有误");
//        }

        //用于判断用户传输的参数是否有误
        if (result.hasErrors()) {
            List<ObjectError> ls = result.getAllErrors();
            String errorMessage = ls.get(0).getDefaultMessage();
            return resultMap.fail().message(errorMessage);
        }

        //判断输入的社会信用代码格式
        String socialCreditCode = userInformation.getAdministratorInformation().getSocialCreditCode();
        boolean socialCodeFlag = CreditCodeRegex.isValid_credit(socialCreditCode);
        if(socialCodeFlag ==false){
            return resultMap.fail().message("请输入正确的社会信用号");
        }

        try {
            resultMap = companyServiceTwo.register(userInformation,businessFile,legalCardIdFile,contactCardFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("CompanyController 中 register方法 错误 --"+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //企业的登陆
    @ResponseBody
    @PostMapping("login")
    public ResultMap login(@RequestParam("loginName") String loginName, //登陆名
                           @RequestParam("password") String password,   //密码
                           @CookieValue(value = "check", required = false) String check,
                           @RequestParam("code") String code,   //验证码
                           HttpServletResponse response){

        if (!check.equalsIgnoreCase(code)) {
            //用户输入的验证码有误
            return resultMap.fail().message("输入的验证码有误");
        }
        try {
            resultMap = companyServiceTwo.login(loginName,password,response);

//            //创建shiro的令牌
//            Subject subject = SecurityUtils.getSubject();
//            //  在认证提交前准备token（令牌）
//            UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
//            //执行认证登陆
//            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("CompanyController 中 login 方法错误 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }
}