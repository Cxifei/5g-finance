package cn.fate.ssm.controller;

import cn.fate.ssm.beans.Bill;
import cn.fate.ssm.beans.User;
import cn.fate.ssm.commons.ErrorCode;
import cn.fate.ssm.commons.ResultData;
import cn.fate.ssm.service.IBillService;
import cn.fate.ssm.utils.GetUserByToken;
import cn.fate.ssm.utils.RedisUtli;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.Header;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
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
@ResponseBody
@CrossOrigin
public class BillController {

    private IBillService iBillService;
    private static final String BILL_TYPE = "过桥";

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
    public ResultData addBill(@RequestHeader HttpHeaders headers,Bill bill){
        User userByToken = GetUserByToken.getUserByToken(headers);
        if (userByToken == null){
            return ResultData.of(ErrorCode.NOT_LOGIN_ERROR);
        }
        bill.setUid(userByToken.getId().intValue());
        boolean b = false;
        boolean b2 = true;
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = simpleDateFormat.format(date);
        bill.setCreatetime(time);
        if(bill.getType() != null && !"".equals(bill.getType())){
            b = bill.getAddress() != null && !"".equals(bill.getAddress())
                    && bill.getAmount() != null && !"".equals(bill.getAmount())
                    && bill.getRate() != null && !"".equals(bill.getRate())
                    && bill.getCycle() != null && !"".equals(bill.getCycle())
                    && bill.getCreditplus() != null && !"".equals(bill.getCreditplus())
                    && bill.getPrototypes() != null && !"".equals(bill.getPlot())
                    && bill.getRisk() != null && !"".equals(bill.getRisk())
                    && bill.getCommossion() != null && !"".equals(bill.getCommossion())
            ;
            if(BILL_TYPE.equals(bill.getType())){
                b2 = true;
            }
        }

        if(b && b2) {
            if (iBillService.addBill(bill)) {
                return ResultData.success();
            }
        }
        return ResultData.of(ErrorCode.SAVEBILL_ERROR);
    }


    /**
     *
     * @param
     * @return 是否接单成功
     */
    @RequestMapping(value = "/acceptBill",method = RequestMethod.POST)
    public ResultData acceptBill(@RequestHeader HttpHeaders headers,Bill bill){
        User userByToken = GetUserByToken.getUserByToken(headers);
        if (userByToken == null){
            return ResultData.of(ErrorCode.NOT_LOGIN_ERROR);
        }
        boolean isok = iBillService.acceptBill((Integer.parseInt(bill.getId())) ,userByToken.getId().intValue());
        if(isok){
            return ResultData.success();
        }
        return ResultData.of(ErrorCode.ACCEPTBILL_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/findBillListByType",method = RequestMethod.GET)
    public ResultData findBillListByType(@RequestParam("type") String type){

        List<Bill> billList = iBillService.findBillListByType(type);

        if (billList == null || billList.size() == 0){
            return ResultData.of(ErrorCode.FAIL);
        }else{
            //将用户信息转为json并返回String billListJson = JSON.toJSONString(billList);
            return ResultData.of(billList);
        }

    }

    /**
     * 获取最新的20个订单
     * @return
     */
    @RequestMapping(value = "/findNewestBill",method = RequestMethod.GET)
    public ResultData findNewestBill(){
        List<Bill> newestList=iBillService.findNewestBill();
        return ResultData.of(newestList);
    }

    /**
     * 发单人确认交易
     * @param
     * @return
     */

    @RequestMapping(value = "/issuerConfirm",method = RequestMethod.POST)
    public ResultData issuerConfirm(Bill bill){

        boolean b = iBillService.issuerConfirm(Integer.parseInt(bill.getId()));
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
     * @param
     * @return
     */

    @RequestMapping(value = "/receiverConfirm",method = RequestMethod.POST)
    public ResultData receiverConfirm(Bill bill){

        boolean b = iBillService.receiverConfirm(Integer.parseInt(bill.getId()));
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
     * @param
     * @return
     */
    @RequestMapping(value = "/cancelBill",method = RequestMethod.POST)
    public ResultData cancelBill(Bill bill){
        boolean b = iBillService.cancelBill(Integer.parseInt(bill.getId()));
        return b?ResultData.success():ResultData.of(ErrorCode.DELETE_ERROR);

    }

    /**
     * 接单人取消接单
     * @param
     * @return
     */
    @RequestMapping(value = "/CancellationOfOrders",method = RequestMethod.POST)
    public ResultData CancellationOfOrders(Bill bill){
        boolean b=iBillService.quxiao(Integer.parseInt(bill.getId()));
        return b?ResultData.success():ResultData.of(ErrorCode.FAIL);
    }

    /**
     * 发单人取消交易
     * @param
     * @return
     */
    @RequestMapping(value = "/iCancellationOfTransactions",method = RequestMethod.POST)
    public ResultData IcancellationOfTransactions(Bill bill){
        boolean b = iBillService.IcancellationOfTransactions(Integer.parseInt(bill.getId()));
        return b?ResultData.success():ResultData.of(ErrorCode.FAIL);
    }


    /**
     * 接单人取消交易
     * @param
     * @return
     */
    @RequestMapping(value = "/rCancellationOfTransactions",method = RequestMethod.POST)
    public ResultData RcancellationOfTransactions(Bill bill){
        boolean b = iBillService.RcancellationOfTransactions(Integer.parseInt(bill.getId()));
        return b?ResultData.success():ResultData.of(ErrorCode.FAIL);
    }

    /**
     * 用户的发单
     * @param
     * @return
     */
    @RequestMapping(value = "/myInvoice",method =RequestMethod.GET)
    public ResultData myInvoice(@RequestHeader HttpHeaders header){
        User userByToken = GetUserByToken.getUserByToken(header);
        if (userByToken == null){
            return ResultData.of(ErrorCode.NOT_LOGIN_ERROR);
        }
        int id=userByToken.getId().intValue();
        List<Bill> list=iBillService.myInvoice(id);
               for(Bill bill:list){
            System.out.println(bill.getId());
        }
        return ResultData.of(list);


    }

    /**
     * 用户的接单
     * @param
     * @return
     */
    @RequestMapping(value = "/myReceipt",method =RequestMethod.GET)
    public ResultData myReceipt(@RequestHeader HttpHeaders header){
        User userByToken = GetUserByToken.getUserByToken(header);
        if (userByToken == null){
            return ResultData.of(ErrorCode.NOT_LOGIN_ERROR);
        }
        int id=userByToken.getId().intValue();
        List<Bill> list=iBillService.myReceipt(id);
        return ResultData.of(list);
    }




}
