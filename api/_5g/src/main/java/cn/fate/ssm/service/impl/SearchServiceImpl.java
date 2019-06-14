package cn.fate.ssm.service.impl;

import cn.fate.ssm.beans.Bill;
import cn.fate.ssm.dao.ISearchDao;
import cn.fate.ssm.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 搜索业务层实现类
 *
 * @author always_on_the_way
 * @date 2019-06-14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SearchServiceImpl implements ISearchService {

    private ISearchDao searchDao;

    @Autowired
    public SearchServiceImpl(ISearchDao searchDao) {
        this.searchDao = searchDao;
    }

    /**
     * 通过关键字搜索相关的订单
     *
     * @param keyword
     * @return
     */
    @Override
    public List<Bill> searchBillListByKeyWord(String keyword) throws Exception {
        return searchDao.selectBillListByKeyWord(keyword);
    }
}
