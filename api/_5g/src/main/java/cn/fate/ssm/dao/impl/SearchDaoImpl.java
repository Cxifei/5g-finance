package cn.fate.ssm.dao.impl;

import cn.fate.ssm.beans.Bill;
import cn.fate.ssm.dao.ISearchDao;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索数据层实现类
 *
 * @author always_on_the_way
 * @date 2019-06-14
 */
@Repository
public class SearchDaoImpl implements ISearchDao {


    private SolrServer solrServer;

    @Autowired
    public SearchDaoImpl(SolrServer solrServer) {
        this.solrServer = solrServer;
    }

    /**
     * 通过关键字搜索相关的订单
     *
     * @param keyword
     * @return
     */
    @Override
    public List<Bill> selectBillListByKeyWord(String keyword) throws Exception {

        SolrQuery solrQuery = new SolrQuery();
//        设置相关配置信息
        //关键词
        solrQuery.setQuery(keyword);
        //分页
        solrQuery.setStart(0);
        solrQuery.setRows(5);
        //默认域
        solrQuery.set("df","bill_keyword");
        //指定域
//        要指定查询的域（字段）
        String field = "id,user_name,bill_address,bill_type,bill_amount,bill_instructions,bill_valuation,bill_rate,bill_levorprofit,bill_cycle,bill_used,bill_editor,bill_area,bill_plot,bill_floorprice,bill_houseprice,bill_creditplus,bill_mortqaqed,bill_prototype,bill_risk,bill_commossion,bill_createtime,bill_status";
        solrQuery.set("fl",field);

        //执行查询
        QueryResponse response = solrServer.query(solrQuery);
        //获取文档结果集
        SolrDocumentList docs = response.getResults();

        List<Bill> billList = new ArrayList<>();

        for (SolrDocument doc:docs) {
            Bill bill = new Bill();

            bill.setId((String) doc.get("id"));
            bill.setUsername((String) doc.get("user_name"));
            bill.setAddress((String) doc.get("bill_address"));
            bill.setType((String) doc.get("bill_type"));
            bill.setAmount((Integer) doc.get("bill_amount"));
            bill.setInstructions((String) doc.get("bill_instructions"));
            bill.setValuation((String) doc.get("bill_valuation"));
            bill.setRate((String) doc.get("bill_rate"));
            bill.setLevorprofit((String) doc.get("bill_levorprofit"));
            bill.setCycle((String) doc.get("bill_cycle"));
            bill.setUsed((String) doc.get("bill_used"));
            bill.setEditor((String) doc.get("bill_editor"));
            bill.setArea((Double) doc.get("bill_area"));
            bill.setPlot((String) doc.get("bill_plot"));
            bill.setFloorprice((Double) doc.get("bill_floorprice"));
            bill.setHouseprice((Double) doc.get("bill_houseprice"));
            bill.setCreditplus((String) doc.get("bill_creditplus"));
            bill.setMortqaqed((String) doc.get("bill_mortqaqed"));
            bill.setPrototype((String) doc.get("bill_prototype"));
            bill.setRisk((String) doc.get("bill_risk"));
            bill.setCommossion((String) doc.get("bill_commossion"));
            bill.setCreatetime((String) doc.get("bill_createtime"));
            bill.setStatus((Integer) doc.get("bill_status"));

            billList.add(bill);
        }
        return billList;
    }
}
