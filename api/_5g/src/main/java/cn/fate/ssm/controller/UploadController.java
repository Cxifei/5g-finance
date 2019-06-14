package cn.fate.ssm.controller;

import cn.fate.ssm.commons.ErrorCode;
import cn.fate.ssm.commons.ResultData;
import cn.fate.ssm.utils.FastDfsClientWrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 文件上传的servlet
 *
 * @author fate
 * @date 2019-06-12 10:16
 */


@Api("文件上传接口")
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
        String[] r = FastDfsClientWrapper.upload(file);
        for (String s : r) {
            System.out.println(s);
        }
        return ResultData.success();

    }
}
