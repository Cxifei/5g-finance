package cn.fate.ssm.controller;

import cn.fate.ssm.beans.Bill;
import cn.fate.ssm.commons.ErrorCode;
import cn.fate.ssm.commons.ResultData;
import cn.fate.ssm.service.ISearchService;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 搜索控制层
 *
 * @author always_on_the_way
 * @date 2019-06-13
 */
@Api("搜索接口")
@Controller
@CrossOrigin
@ResponseBody
public class SearchController {

    private ISearchService searchService;

    @Autowired
    public SearchController(ISearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * 搜索功能
     *
     * @param keyword
     * @return 返回billList的json字符串
     */
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ResultData searchBillListByKeyWord(@RequestParam("keyword") String keyword){

        List<Bill> billList = null;
        try {
            billList = searchService.searchBillListByKeyWord(keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (billList == null || billList.size() == 0){
            return ResultData.of(ErrorCode.FAIL);
        }else{
            //将用户信息转为json并返回
            String billListJson = JSON.toJSONString(billList);
            return ResultData.of(billListJson);
        }

    }


}
