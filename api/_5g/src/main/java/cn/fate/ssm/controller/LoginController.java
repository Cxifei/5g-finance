package cn.fate.ssm.controller;

import cn.fate.ssm.beans.User;
import cn.fate.ssm.commons.ErrorCode;
import cn.fate.ssm.commons.ResultData;
import cn.fate.ssm.service.IUserService;
import cn.fate.ssm.utils.PhoneUtli;
import cn.fate.ssm.utils.RedisUtli;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * 发送的验证码
     */
    private String phoneCode;

    /**
     * 验证码是否正确
     */
    private boolean codeTrue;

    /**
     * 手机号
     */
    private Long phone;

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
            String token = RandomStringUtils.random(10);
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
     * 发送验证码
     * @param user 获取手机号
     */
    @RequestMapping(value = "/phoneCode",method = RequestMethod.POST)
    public ResultData getPhoneCode(User user){
        if (userService.queryUserByPhone(user) == null){
            this.phoneCode= PhoneUtli.phoneCode(user.getPhone() + "");
            this.phone = user.getPhone();
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
    public ResultData validationCode(String code){
        if (phoneCode.equals(code)){
            codeTrue = true;
            return ResultData.success();
        }else {
            return ResultData.error();
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
        //判断是否验证码通过
        if (codeTrue){
            //让注册的手机号和发送验证的手机号一致
            user.setPhone(phone);
            if (userService.registerUser(user)){
                return  ResultData.success();
            }
        }
        return ResultData.error();

    }

}
