package cn.fate.ssm.controller;

import cn.fate.ssm.beans.User;
import cn.fate.ssm.commons.ResultData;
import cn.fate.ssm.service.IUserService;
import io.swagger.annotations.Api;
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

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResultData login(User user){
        User queryUser = userService.queryUser(user);
        return queryUser == null ? ResultData.error():ResultData.success();
    }
}
