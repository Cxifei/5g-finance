package cn.fate.ssm.controller;

import cn.fate.ssm.beans.Adminer;
import cn.fate.ssm.commons.ErrorCode;
import cn.fate.ssm.commons.ResultData;
import cn.fate.ssm.service.IAdminerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 管理员控制层
 *
 * @author always_on_the_way
 * @date 2019-06-19
 */
@Api("后台管理员接口")
@Controller
@CrossOrigin
@ResponseBody
public class AdminerController {


    private IAdminerService adminerService;

    @Autowired
    public AdminerController(IAdminerService adminerService) {
        this.adminerService = adminerService;
    }

    /**
     * 管理员登录验证
     * @param adminer 管理员信息对象
     * @return
     */
    @RequestMapping(value = "/adminLogin",method = RequestMethod.POST)
    public ResultData login(Adminer adminer){
        Adminer res = adminerService.findAdminer(adminer);
        if (res == null){
            return ResultData.of(ErrorCode.LOGIN_ERROR);
        }
        return ResultData.of(res);
    }

}
