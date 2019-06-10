package cn.fate.ssm.controller;

import cn.fate.ssm.beans.User;
import cn.fate.ssm.commons.ResultData;
import cn.fate.ssm.service.IUserService;
import cn.fate.ssm.utils.RedisUtli;
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
public class LoginController {


    private IUserService userService;


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
    @ResponseBody
    public ResultData login(User user){
        User queryUser = userService.queryUser(user);

        if (queryUser == null){
            return ResultData.error();
        }else {
            //随机生成10位的字符串
            String token = RandomStringUtils.random(10);

            //将token和用户账号存入redis
            RedisUtli.addString(token,user.getUsername());
            return ResultData.of(token);
        }
    }
}
