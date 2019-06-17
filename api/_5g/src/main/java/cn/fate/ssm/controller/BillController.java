package cn.fate.ssm.controller;

import cn.fate.ssm.beans.Bill;
import cn.fate.ssm.commons.ErrorCode;
import cn.fate.ssm.commons.ResultData;
import cn.fate.ssm.service.IBillService;
import cn.fate.ssm.utils.RedisUtli;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
     * 新建订单
     *
     * @param bill 需要添加的订单信息
     * @return 返回添加是否成功
     */
    @RequestMapping(value = "/addBill",method = RequestMethod.POST)
    public ResultData addBill(Bill bill){

        String token = RedisUtli.getString("token");
        System.out.println(token);
        boolean b = false;
        boolean b2 = true;
        Date d = new Date();
        bill.setCreatetime(d.toString());
        if(bill.getType() != null && !"".equals(bill.getType())){
            b = bill.getAddress() != null && !"".equals(bill.getAddress())
                    && bill.getAmount() != null && !"".equals(bill.getAmount())
                    && bill.getRate() != null && !"".equals(bill.getRate())
                    && bill.getCycle() != null && !"".equals(bill.getCycle())
                    && bill.getCreditplus() != null && !"".equals(bill.getCreditplus())
                    && bill.getPrototype() != null && !"".equals(bill.getPlot())
                    && bill.getRisk() != null && !"".equals(bill.getRisk())
                    && bill.getCommossion() != null && !"".equals(bill.getCommossion())
            ;
            if(bill.getType().equals("过桥")){
                b2 = true;
            }
        }

        if(b && b2) {
            boolean isok = iBillService.addBill(bill);
            System.out.println(isok);
            if (isok) {
                return ResultData.success();
            }
        }
        return ResultData.of(ErrorCode.SAVEBILL_ERROR);
    }

    @RequestMapping(value = "/acceptBill",method = RequestMethod.POST)
    public ResultData acceptBill(int id, int uid){
        boolean isok = iBillService.acceptBill(id,uid);
        if(isok){
            return ResultData.success();
        }
        return ResultData.of(ErrorCode.ACCEPTBILL_ERROR);
    }

    @RequestMapping(value = "/checkBill",method = RequestMethod.POST)
    public ResultData checkBill(int id, int status){

        if(status == 1 || status == 2) {
            boolean isok = iBillService.checkBill(id, status);
            if (isok) {
                return ResultData.success();
            }
        }
        return ResultData.of(ErrorCode.CHECKBILL_ERROR);
    }
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
