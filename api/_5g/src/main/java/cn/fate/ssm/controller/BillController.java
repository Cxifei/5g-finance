package cn.fate.ssm.controller;

import cn.fate.ssm.beans.Bill;
import cn.fate.ssm.commons.ErrorCode;
import cn.fate.ssm.commons.ResultData;
import cn.fate.ssm.service.IBillService;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
     * 分类查询订单
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findBillListByType",method = RequestMethod.GET)
    public ResultData findBillListByType(@RequestParam("type") String type){

        List<Bill> billList = iBillService.findBillListByType(type);

        if (billList == null || billList.size() == 0){
            return ResultData.of(ErrorCode.FAIL);
        }else{
            //将用户信息转为json并返回
            String billListJson = JSON.toJSONString(billList);
            return ResultData.of(billListJson);
        }

    }

    /**
     * 获取最新的20个订单
     * @return
     */
    @ModelAttribute("newestList")
    public List findNewestBill(Model model, HttpServletRequest request){
        List<Bill> newestList=iBillService.findNewestBill();
        model.addAttribute("newestList",newestList);
        return newestList;
    }

    /**
     * 发单人确认交易
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/issuerConfirm",method = RequestMethod.POST)
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
     * 接单人确认交易
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/receiverConfirm",method = RequestMethod.POST)
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
    @RequestMapping(value = "/cancelBill",method = RequestMethod.POST)
    public ResultData cancelBill(int id){
        boolean b = iBillService.cancelBill(id);
        return b?ResultData.success():ResultData.of(ErrorCode.DELETE_ERROR);

    }

    /**
     * 发单人取消交易
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/iCancellationOfTransactions",method = RequestMethod.POST)
    public ResultData IcancellationOfTransactions(int id){
        boolean b = iBillService.IcancellationOfTransactions(id);
        return b?ResultData.success():ResultData.of(ErrorCode.FAIL);
    }


    /**
     * 接单人取消交易
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/rCancellationOfTransactions",method = RequestMethod.POST)
    public ResultData RcancellationOfTransactions(int id){
        boolean b = iBillService.RcancellationOfTransactions(id);
        return b?ResultData.success():ResultData.of(ErrorCode.FAIL);
    }





}
