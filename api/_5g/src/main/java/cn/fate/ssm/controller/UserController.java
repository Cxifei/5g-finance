package cn.fate.ssm.controller;

import cn.fate.ssm.commons.ResultData;
import cn.fate.ssm.utils.RedisUtli;
import io.swagger.annotations.Api;
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

    @RequestMapping(value = "/showUser",method = RequestMethod.POST)
    public ResultData showUser(@RequestHeader HttpHeaders headers) {
        String code = headers.getFirst("token");
        String user = RedisUtli.getString(code);
        return ResultData.of(user);
    }
}
