package cn.fate.ssm.controller;/**
 * @author admin
 * @date 2019-06-19 17:43
 */

import cn.fate.ssm.beans.Bill;
import cn.fate.ssm.beans.User;
import cn.fate.ssm.commons.ErrorCode;
import cn.fate.ssm.commons.ResultData;
import cn.fate.ssm.service.BGBillService;
import cn.fate.ssm.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author: Brand
 * @Date: 2019/6/19 17:43
 * @Description: 后台订单管理控制层
 */

@Api("后台订单管理接口")
@Controller
@CrossOrigin
@ResponseBody
public class BGBillController {

    private BGBillService bgBillService;

    @Autowired
    public BGBillController(BGBillService bgBillService, IUserService iUserService){
        this.bgBillService = bgBillService;
    }

    /**
     * 后台审核订单
     * @param id 订单id
     * @param status 是否审核通过 1 未通过，2 通过
     * @return
     */
    @RequestMapping(value = "/checkBill",method = RequestMethod.POST)
    public ResultData checkBill(int id, int status){

        if(status == 1 || status == 2) {
            boolean isok = bgBillService.checkBill(id, status);
            if (isok) {
                return ResultData.success();
            }
        }
        return ResultData.of(ErrorCode.CHECKBILL_ERROR);
    }

    /**
     *
     * @param bill 需要删除的订单
     * @return 是否删除成功
     */
    @RequestMapping(value = "/bgDeleteBill",method = RequestMethod.POST)
    public ResultData delete(Bill bill){
        if(bill.getUconfirm() == 1 && bill.getJconfirm() == 1){
            return ResultData.of(ErrorCode.DELETEBILL_STATUS_ERROR);
        }
        boolean b = bgBillService.deleteBill(Integer.parseInt(bill.getId()));
        if(b){
            return ResultData.success();
        }
        return ResultData.of(ErrorCode.DELETEBILL_ERROR);
    }

    @RequestMapping(value = "/getAllNoCheckBill",method = RequestMethod.POST)
    public ResultData getAllNoCheckBill(){
        List<Bill> noCheckBills = bgBillService.getBillByStatus();
        if (noCheckBills == null || noCheckBills.size() == 0){
            return ResultData.of(ErrorCode.FAIL);
        }else{
            for (Bill bill : noCheckBills) {
                User user = bgBillService.getUserById(bill.getUid());
                System.out.println(user);
                bill.setPhone(user.getPhone());
                bill.setUsername(user.getName());
            }
            //将订单信息转为json并返回
            return ResultData.of(noCheckBills);
        }
    }

}
