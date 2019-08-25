package com.xdmd.IntranetEnvironment.company.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("file")
public class FileDownLoadController {

    //对文件进行下载
    @GetMapping("queryFileStream")
    public void downLoad(HttpServletResponse response, HttpServletRequest request,
                         @RequestParam("fileUrl")String fileUrl,//文件路径
                         @RequestParam("fileName")String fileName) throws IOException {     //文件名称

        //使用字节流读取本地图片
        ServletOutputStream out=null;
        response.reset(); // 必要地清除response中的缓存信息
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename="
                + java.net.URLEncoder.encode(fileName, "UTF-8"));
        BufferedInputStream buf=null;
        //创建一个文件对象，对应的文件就是python把词云图片生成后的路径以及对应的文件名
        File file = new File(fileUrl);
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
    }
}
