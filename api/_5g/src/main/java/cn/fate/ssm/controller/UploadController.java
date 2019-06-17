package cn.fate.ssm.controller;

import cn.fate.ssm.beans.User;
import cn.fate.ssm.commons.ErrorCode;
import cn.fate.ssm.commons.ResultData;
import cn.fate.ssm.service.IUserService;
import cn.fate.ssm.utils.Base64Util;
import cn.fate.ssm.utils.FastDfsClientWrapper;
import cn.fate.ssm.utils.RedisUtli;
import cn.fate.ssm.utils.RequestBaiduUtli;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    private IUserService service;

    public UploadController(IUserService service) {
        this.service = service;
    }

    /**
     *  文件上传的公共方法
     * /@RequestMapping(value = "/uploadTest",method = RequestMethod.POST)
     * @param file 上传的文件
     * @return 返回文件的上传的路径
     */

    private String upload(MultipartFile file){
        if (file.isEmpty()){
            return null;
        }
        //获取文件名称
        String[] r = FastDfsClientWrapper.upload(file);
        //获取的文件的路径
        return  "/"+r[0]+"/"+r[1];
    }


    /**
     * 微信头像修改
     *
     * @param headers 请求头
     * @param baseImg 图片的base64
     * @return 响应的状态
     */
    @RequestMapping(value = "/headUpload",method = RequestMethod.POST)
    public ResultData headUpload(@RequestHeader HttpHeaders headers,String baseImg){
        //获取token
        String token = headers.getFirst("token");

        if (token == null){
            return ResultData.of(ErrorCode.NOT_LOGIN_ERROR);
        }
        //获取正在登陆的用户的信息
        User user = JSON.parseObject(RedisUtli.getString(token), User.class);
        //返回的图片的路径
        String imgPath = upload(Base64Util.base64ToMultipart(baseImg));
        System.out.println(user);
        //修改用户信息
        user.setHead(imgPath);
        //发送到数据库
        if (service.changeUser(user)){
            return ResultData.of(user);
        }else {
            return ResultData.of(ErrorCode.FILE_ERROR);
        }
    }


    @RequestMapping(value = "/validationIdCard",method = RequestMethod.POST)
    public ResultData validationIdCard(@RequestHeader HttpHeaders headers,String baseImg){
        System.out.println(baseImg);
        //获取token
        String token = headers.getFirst("token");
        if (token == null){
            return ResultData.of(ErrorCode.NOT_LOGIN_ERROR);
        }
        //获取正在登陆的用户的信息
        User user = JSON.parseObject(RedisUtli.getString(token), User.class);
        JSONObject jsonObject = RequestBaiduUtli.requestBaidu(baseImg);
        System.out.println(jsonObject);
        if (RequestBaiduUtli.validationIsOk(jsonObject)){

            JSONObject wordsResult = jsonObject.getJSONObject("words_result");

            String name = wordsResult.getJSONObject("姓名").getString("words");
            String address = wordsResult.getJSONObject("住址").getString("words");
            String idCard = wordsResult.getJSONObject("公民身份号码").getString("words");
            String sex = wordsResult.getJSONObject("性别").getString("words");
            //重置用户信息
            user.setSex(sex);
            user.setAddress(address);
            user.setIdCard(idCard);
            user.setName(name);
            //更改为已经认证
            user.setValidation("1");
            System.out.println(user);

            if (service.changeUser(user)){
                return ResultData.success();
            }else {
                return ResultData.error();
            }
            //---------------------
        }else {
            System.out.println("认证失败");
            return ResultData.of(ErrorCode.FILE_ERROR);
        }
    }

}
