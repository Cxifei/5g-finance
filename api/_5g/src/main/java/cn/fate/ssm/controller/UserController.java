package cn.fate.ssm.controller;

import cn.fate.ssm.beans.User;
import cn.fate.ssm.commons.ErrorCode;
import cn.fate.ssm.commons.ResultData;
import cn.fate.ssm.service.IUserService;
import cn.fate.ssm.utils.RedisUtli;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



/**
 * 用户中心的控制层
 *
 * @author fate
 * @date 2019-06-11 17:05
 */

@Api("登陆接口")
@Controller
@CrossOrigin
@ResponseBody
public class UserController {
    private IUserService service;
    @Autowired
    public UserController(IUserService service) {
        this.service = service;
    }


    @RequestMapping(value = "/showUser",method = RequestMethod.POST)
    public ResultData showUser(@RequestHeader HttpHeaders headers) {
        String code = headers.getFirst("token");
        if (code == null){
            return ResultData.of(ErrorCode.FAIL);
        }
        String user = RedisUtli.getString(code);
        return ResultData.of(user);
    }
    @RequestMapping(value = "/changUser",method = RequestMethod.POST)
    public ResultData changUser(User user,@RequestHeader HttpHeaders headers){

        String code = headers.getFirst("token");

        if (code == null){
            return ResultData.of(ErrorCode.FAIL);
        }

        String user2 = RedisUtli.getString(code);
        //获取正在登陆的用户的id
        User user1 = JSON.parseObject(user2, User.class);
        user.setId(user1.getId());
        if (service.changeUser(user)){
            return ResultData.success();
        }
        return ResultData.of(ErrorCode.FAIL);
    }
}
