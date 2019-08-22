package com.xdmd.IntranetEnvironment.Demo.controller;

import com.xdmd.IntranetEnvironment.Demo.mapper.TestMapper;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @PostMapping("two")
    public void queryFile(@RequestParam("filePath")String remoteFilePath,
                          HttpServletResponse response) {
        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        response.setHeader("Content-Disposition", "inline;filename="+ UUID.randomUUID().toString()+".pdf");
        //点击会提供对话框选择另存为：
        //response.setHeader( “Content-Disposition “, “attachment;filename= “+filename);
        //通过IE浏览器直接选择相关应用程序插件打开：
        //response.setHeader( “Content-Disposition “, “inline;filename= “+fliename)
        //下载前询问（是打开文件还是保存到计算机）
        //response.setHeader( “Content-Disposition “, “filename= “+filename);
        try
        {
            urlfile = new URL(remoteFilePath);
            httpUrl = (HttpURLConnection)urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(response.getOutputStream());
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1)
            {
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
            httpUrl.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                bis.close();
                bos.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("queryFileStream")
    public void downLoad(HttpServletResponse response, HttpServletRequest request) throws IOException {

        String r = request.getParameter("r");
        //使用字节流读取本地图片
        ServletOutputStream out=null;
        response.reset(); // 必要地清除response中的缓存信息
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename="
                + java.net.URLEncoder.encode("bbbbbbbb.zip", "UTF-8"));
        BufferedInputStream buf=null;
        //创建一个文件对象，对应的文件就是python把词云图片生成后的路径以及对应的文件名
        File file = new File("D:\\xdmd_environment\\专家信息库\\2019-08-22新建文本文档.zip");
        try {
            //使用输入读取缓冲流读取一个文件输入流
            buf=new BufferedInputStream(new FileInputStream(file));
            //利用response获取一个字节流输出对象
            out=response.getOutputStream();
            //定义个数组，由于读取缓冲流中的内容
            byte[] buffer=new byte[1024];
            //while循环一直读取缓冲流中的内容到输出的对象中
            while(buf.read(buffer)!=-1) {
                out.write(buffer);
            }
            //写出到请求的地方
            out.flush();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            if(buf!=null) buf.close();
            if(out!=null) out.close();
        }
//        //传输结束后，删除文件，可以不删除，在生成的图片中回对此进行覆盖
//        File file1 = new File("E:\\Java\\eclipse_code\\NLP\\WebContent\\source\\wordcloud.png");
//        file1.delete();
//        System.out.println("文件删除！");
    }
}
