package cn.fate.ssm.controller;

import cn.fate.ssm.beans.User;
import cn.fate.ssm.commons.ErrorCode;
import cn.fate.ssm.commons.ResultData;
import cn.fate.ssm.service.IUserService;
import cn.fate.ssm.utils.GetUserByToken;
import cn.fate.ssm.utils.RedisUtli;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ResponseHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * 用户中心的控制层
 *
 * @author fate
 * @date 2019-06-11 17:05
 */

@Api("用户中心接口")
@Controller
@CrossOrigin
@ResponseBody
@EnableSwagger2
@Configuration

public class UserController {

    private IUserService service;


    @Autowired
    public UserController(IUserService service) {
        this.service = service;
    }

    /**
     * 展示用户的信息
     * @param headers 携带的token
     * @return 用户的信息和状态码
     */

    @ApiImplicitParam(paramType = "header",name = "token")
    @RequestMapping(value = "/showUser")
    public ResultData showUser(@RequestHeader HttpHeaders headers) {
        String code = headers.getFirst("token");
        System.out.println(code);
        if (code == null){
            return ResultData.of(ErrorCode.NOT_LOGIN_ERROR);
        }
        User user = JSON.parseObject(RedisUtli.getString(code), User.class);
        if (user.getHead() != null&& !"".equals(user.getHead())){
            user.setHead(user.getHead());
        }
        return ResultData.of(user);
    }


    /**
     * 用于修改用户信息
     *
     * @param user 要修改的信息
     * @param headers 携带token
     * @return 修改的状态码
     */
    @ApiImplicitParam(paramType = "header",name = "token")
    @RequestMapping(value = "/changeUser",method = RequestMethod.POST)
    public ResultData changeUser(User user,@RequestHeader HttpHeaders headers){

        User userByToken = GetUserByToken.getUserByToken(headers);
        if (userByToken==null){
            return ResultData.of(ErrorCode.NOT_LOGIN_ERROR);
        }
        user.setId(userByToken.getId());
        userByToken.setMsg(user.getMsg());
        return service.changeUser(user)?ResultData.of(userByToken):ResultData.of(ErrorCode.FAIL);
    }
}
