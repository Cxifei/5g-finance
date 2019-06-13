package cn.fate.ssm.controller;

import cn.fate.ssm.beans.Bill;
import cn.fate.ssm.commons.ErrorCode;
import cn.fate.ssm.commons.ResultData;
import cn.fate.ssm.service.IBillService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 订单的控制层
 *
 * @author rimi
 * @DATE 2019-06-11 17:23
 */
@Api("订单接口")
@Controller
@CrossOrigin
public class BillController {

    private IBillService iBillService;

    @Autowired
    public BillController(IBillService billService){
        this.iBillService=billService;
    }


    /**
     * 获取最新的20个订单
     * @return
     */
    @ModelAttribute("newestList")
    public List findNewestBill(){
        List<Bill> newestList=iBillService.findNewestBill();
        return newestList;
    }

    /**
     * 接单人确认交易
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/issuerConfirm")
    public ResultData issuerConfirm(int id){

        Bill bill = iBillService.findById(id);
        boolean b = iBillService.issuerConfirm(id);
        if (b){
            if (bill.getJconfirm()==1){
                return ResultData.of(ErrorCode.TRANSACTION_SUCCESS);
            }else {
                return ResultData.of(ErrorCode.ITRANSACTION_SUCCESS);
            }
        }else{
            return ResultData.of(ErrorCode.TRANSACTION_ERROR);
        }
    }

    /**
     * 发单人确认交易
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/receiverConfirm")
    public ResultData receiverConfirm(int id){

        Bill bill = iBillService.findById(id);
        boolean b = iBillService.receiverConfirm(id);
        if (b){
            if(bill.getUconfirm()==1){
                return ResultData.of(ErrorCode.TRANSACTION_SUCCESS);
            }else{
                return ResultData.of(ErrorCode.RTRANSACTION_SUCCESS);
            }
        }else {
            return ResultData.of(ErrorCode.TRANSACTION_ERROR);
        }
    }

    /**
     * 发单人取消订单
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "cancelBill")
    public ResultData cancelBill(int id){
        boolean b = iBillService.cancelBill(id);
        return b?ResultData.success():ResultData.of(ErrorCode.DELETE_ERROR);

    }






}
