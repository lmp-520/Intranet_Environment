package com.xdmd.IntranetEnvironment.company.Pojo;
import java.util.HashMap;
import java.util.Map;

public class CreditCodeRegex {
//	private static String isCreditCode = "true";
//    private static String error_CreditCode = "社会信用代码有误";
//    private static String error_CreditCode_min = "社会信用代码不足18位，请核对后再输！";
//    private static  String error_CreditCode_max = "社会信用代码大于18位，请核对后再输！";
//    private static String error_CreditCode_empty ="社会信用代码不能为空！";
    private static Map<String,Integer> datas = null;
    private static char[] pre17s;
    static int[] power = {1,3,9,27,19,26,16,17,20,29,25,13,8,24,10,30,28};
	// 社会统一信用代码不含（I、O、S、V、Z） 等字母
    static char[] code = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','T','U','W','X','Y'};
    public  static  void main(String[] args){
    	System.out.println(isValid_credit("91320104754102484X"));
    }
    public  static  boolean isValid_credit(String creditCode){
    	try
    	{
	        initDatas(code.length);
	        pre17(creditCode);
	        return isCreditCode(creditCode);
    	}catch(Exception e){
    		return  false;
    	}
    }



    /**
     * 判断是否是一个有效的社会信用代码
     * @param creditCode
     * @return
     */
    private  static boolean  isCreditCode(String creditCode){
        if("".equals(creditCode)||" ".equals(creditCode)){
        	return false;
        }else if(creditCode.length()<18){
        	return false;
        }else if(creditCode.length()>18){
        	return false;
        }else{
            int sum =  sum(pre17s);
            int temp = sum%31;
            temp = temp==0?31:temp;//  谢谢 whhitli的帮助
            return creditCode.substring(17,18).equals(code[31-temp]+"")?true:false;
        }
        
    }


    /**
     * @param chars
     * @return
     */
    private static int sum(char[] chars){
        int sum = 0;
        for(int i=0;i<chars.length;i++){
            int code = datas.get(chars[i]+"");
            sum+=power[i]*code;
        }
        return sum;


    }


    /**
     * 获取前17位字符
     * @param creditCode
     */
    private  static void  pre17(String creditCode){
    	 System.out.println(creditCode);
    	 
        String pre17 = creditCode.substring(0,17);
       
        pre17s = pre17.toCharArray();
    }


    /**
     * 初始化数据
     * @param count
     */
    private static void  initDatas(int count){
        datas = new HashMap();
        for(int i=0;i<code.length;i++){
            datas.put(code[i]+"",i);
        }
    }
}
