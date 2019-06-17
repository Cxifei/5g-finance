package cn.fate.ssm.controller;

import cn.fate.ssm.beans.User;
import cn.fate.ssm.beans.UserCode;
import cn.fate.ssm.commons.ErrorCode;
import cn.fate.ssm.commons.ResultData;
import cn.fate.ssm.service.IUserService;
import cn.fate.ssm.utils.PhoneUtli;
import cn.fate.ssm.utils.RedisUtli;
import cn.fate.ssm.utils.StringUtli;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 登陆接口
 *
 * @author fate
 * @date 2019-06-07 15:02
 */
@Api("登陆接口")
@Controller
@CrossOrigin
@ResponseBody
public class LoginController {


    private IUserService userService;
    /**
     * 验证是否成功
     */
    private final static String VAL_CODE="true";


    @Autowired
    public LoginController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 登陆验证功能
     *
     * @param user 传入用户对象
     * @return ResultDat的json字符串，其中obj里面包括token
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultData login(User user){
        User queryUser = userService.queryUser(user);
        if (queryUser == null){
            return ResultData.of(ErrorCode.LOGIN_ERROR);
        }else {

            //随机生成10位的字符串
            String token = StringUtli.getRandomString(30);
            //将用户信息转为json
            String userMsg = JSON.toJSONString(queryUser);
            //将token和用户信息存入redis
            RedisUtli.addString(token,userMsg);
            return ResultData.of(token);
        }
    }

    /**
     * 注册判断电话是否注册过
     *
     * @param user 注册的用户信息
     * @return 返回电话是否重复
     */
    @RequestMapping(value = "/phoneValidation",method = RequestMethod.POST)
    public ResultData phoneValidation(User user){
        return userService.queryUserByPhone(user) == null ? ResultData.success():ResultData.error();
    }

    /**
     * 发送验证码,5分钟过期
     * @param user 获取手机号
     */
    @RequestMapping(value = "/phoneCode",method = RequestMethod.POST)
    public ResultData getPhoneCode(User user){
        if (userService.queryUserByPhone(user) == null){
            String phoneCode= PhoneUtli.phoneCode(user.getPhone() + "");
            if (phoneCode == null){
                return ResultData.error();
            }
            RedisUtli.addStringCode(user.getPhone()+"",phoneCode);
            return ResultData.success();
        }else {
            return ResultData.error();
        }
    }

    /**
     * 验证验证码是否相同
     * @param code 传入用户输入的验证码
     * @return 验证码是否相同，返回状态码
     */
    @RequestMapping(value = "/validationCode",method = RequestMethod.POST)
    public ResultData validationCode(UserCode userCode){
        System.out.println(userCode);

        String phoneCode = RedisUtli.getString(userCode.getPhone());

        if (phoneCode == null) {
            return ResultData.of(ErrorCode.FAIL);
        }

        if (userCode.getCode().equals(phoneCode)){
            RedisUtli.addString(userCode.getPhone(),"true");
            return ResultData.success();
        }else {
            return ResultData.of(ErrorCode.CODE_ERROR);
        }
    }

    /**
     * 注册用户
     *
     * @param user 注册的信息
     * @return 返回注册是否成功
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResultData register(User user){
        System.out.println(user);
        //判断是否验证码通过
        if (VAL_CODE.equals(RedisUtli.getString(user.getPhone()+""))){
            if (userService.registerUser(user)){
                return  ResultData.success();
            }
        }
        return ResultData.error();
    }


}
