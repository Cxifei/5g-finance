package cn.fate.ssm.controller;

import cn.fate.ssm.beans.User;
import cn.fate.ssm.commons.ErrorCode;
import cn.fate.ssm.commons.ResultData;
import cn.fate.ssm.service.IUserBackgroundService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 后台用户管理控制层
 *
 * @author rimi
 * @DATE 2019-06-19 17:03
 */


@Api("订单接口")
@Controller
@ResponseBody
@CrossOrigin
public class UserBackgroundController {

    private IUserBackgroundService iUserBackgroundService;


    @Autowired
    public UserBackgroundController(IUserBackgroundService iUserBackgroundService){
         this.iUserBackgroundService=iUserBackgroundService;
    }


    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping(value = "/bFindAll",method = RequestMethod.POST)
    public ResultData bFindAll(){
        List<User> list=iUserBackgroundService.bFindAll();
        return ResultData.of(list);
    }

    /**
     * 根据ID删除用户
     * @return
     */
    @RequestMapping(value = "/bDelete",method = RequestMethod.POST)
    public ResultData bDelete(int id){
        boolean b=iUserBackgroundService.bDelete(id);
        if (b){
            return ResultData.success();
        }else{
            return ResultData.of(ErrorCode.FAIL);
        }
    }


    /**
     * 查询所有已通过验证的用户
     * @return
     */
    @RequestMapping(value = "/bFindVerification",method = RequestMethod.POST)
    public ResultData bFindVerification(){
        List<User> list=iUserBackgroundService.bFindVerification();
        return ResultData.of(list);
    }

    /**
     * 查询所有未通过验证的用户
     * @return
     */
    @RequestMapping(value = "/bFindUnverified",method = RequestMethod.POST)
    public ResultData bFindUnverified(){
        List<User> list=iUserBackgroundService.bFindUnverified();
        return ResultData.of(list);
    }





}
