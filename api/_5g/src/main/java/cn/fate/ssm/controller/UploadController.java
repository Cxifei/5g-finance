package cn.fate.ssm.controller;

import cn.fate.ssm.commons.ErrorCode;
import cn.fate.ssm.commons.ResultData;
import io.swagger.annotations.Api;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * 文件上传的servlet
 *
 * @author fate
 * @date 2019-06-12 10:16
 */


@Api("登陆接口")
@Controller
@CrossOrigin
@ResponseBody
public class UploadController {
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ResultData upload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        if (file.isEmpty()){
            return ResultData.of(ErrorCode.FILE_ERROR);
        }
        //获取文件名称
        String originalFilename = file.getOriginalFilename();
        //文件大小
        long size = file.getSize();
        InputStream inputStream;
        try {
            //获取文件的输入流
            inputStream= file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            //获取不到
            return ResultData.of(ErrorCode.FILE_ERROR);
        }
        //创建文件保存的路径
        String realPath = request.getServletContext().getRealPath("/images");
        File imagePath = new File(realPath);
        if (!imagePath.exists()){
            imagePath.mkdirs();
        }
        //获取文件后缀
        String nameEnd = originalFilename.substring(originalFilename.lastIndexOf("."));
        Random random = new Random();
        //随机一个文件名
        String name = ""+random.nextLong()+nameEnd;



        return ResultData.success();
    }
}
