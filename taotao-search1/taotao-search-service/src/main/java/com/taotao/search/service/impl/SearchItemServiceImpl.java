package com.taotao.search.service.impl;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.SearchItemMapper;
import com.taotao.search.dao.SearchDao;
import com.taotao.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class SearchItemServiceImpl implements SearchItemService {
    @Autowired
    private SearchItemMapper searchItemMapper;
    @Autowired
    private SearchDao searchDao;
    @Autowired
    private SolrServer solrServer;
    @Override
    public TaotaoResult importAllItems()  {
        List<SearchItem> searchItems = searchItemMapper.getItemList();
        for (SearchItem searchItem:searchItems) {
            try {
                SolrInputDocument document = new SolrInputDocument();
                document.addField("id", searchItem.getId());
                document.addField("item_title", searchItem.getTitle());
                document.addField("item_sell_point", searchItem.getSellPoint());
                document.addField("item_price", searchItem.getPrice());
                document.addField("item_image", searchItem.getImage());
                document.addField("item_category_name", searchItem.getCategoryName());
                document.addField("item_desc", searchItem.getItemDesc());
                solrServer.add(document);
            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return TaotaoResult.ok();
    }
    //分类，价格，排序
    @Override
    public SearchResult search(String queryString,int page, int rows) throws Exception {
        SolrQuery query = new SolrQuery();
        if (queryString != null && !"".equals(queryString)) {
            query.setQuery(queryString);
        } else {
            query.setQuery("*:*");
        }
        //开始索引
        query.setStart((page - 1) * rows);
        query.setRows(rows);
        query.set("df", "item_keywords");
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<span style='color:red'>");
        query.setHighlightSimplePost("</span>");
        SearchResult result = searchDao.search(query);
        long totalPage = result.getRecordCount() % rows == 0 ? result.getRecordCount() / rows : result.getRecordCount() / rows + 1;
        result.setPageCount(totalPage);
        return result;
    }
    @Override
    public void addDocument(SearchItem searchItem) {
        try {
            SolrInputDocument document = new SolrInputDocument();
            // 4、为文档添加域
            document.addField("id", searchItem.getId());
            document.addField("item_title", searchItem.getTitle());
            document.addField("item_sell_point", searchItem.getSellPoint());
            document.addField("item_price", searchItem.getPrice());
            document.addField("item_image", searchItem.getImage());
            document.addField("item_category_name", searchItem.getCategoryName());
            document.addField("item_desc", searchItem.getItemDesc());
            // 5、向索引库中添加文档。
            solrServer.add(document);
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
