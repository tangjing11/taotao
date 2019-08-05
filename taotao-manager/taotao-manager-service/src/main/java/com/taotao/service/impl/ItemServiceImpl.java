package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Override
    public TbItem getItemById(Long itemId) {
        return tbItemMapper.findItemById(itemId);
    }

    @Override
    public EasyUIResult getItemList(int page, int pageSize) {
        //初始化分页设置 他的底层原理是 自动拼接limit语句
        PageHelper.startPage(page, pageSize);
        //查询所有商品信息
        List<TbItem> items = tbItemMapper.findItemAll();
        //取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(items);
        //ctrl+alt+L  整理代码
        EasyUIResult result = new EasyUIResult();
        //使用插件得到总记录条数
        result.setTotal(pageInfo.getTotal());
        //被分页插件处理以后得到的结果集了集合对象
        result.setRows(items);
        return result;
    }

    @Override
    public TaotaoResult addItems(TbItem tbItem, String desc) {
        Long iId = IDUtils.genItemId();
        tbItem.setId(iId);
        tbItem.setStatus((byte) 1);
        Date date = new Date();
        tbItem.setUpdated(date);
        tbItem.setCreated(date);
        int itemCount = tbItemMapper.addItems(tbItem);
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setItemId(iId);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        int descCount = tbItemDescMapper.addItemDesc(tbItemDesc);
        if (itemCount != 0 && descCount != 0) {
            return TaotaoResult.ok();
        }
        return TaotaoResult.build(500, "添加商品有误，请重新输入");
    }

    @Override
    public TaotaoResult deleteItems(Long[] ids) {
        int count = tbItemMapper.deleteItems(ids);
        if (count != 0) {
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public TaotaoResult lowerShelf(Long[] ids) {
        int count = tbItemMapper.lowerShelf(ids);
        if (count != 0) {
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public TaotaoResult upperShelf(Long[] ids) {
        int count = tbItemMapper.upperShelf(ids);
        if (count != 0) {
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public TaotaoResult updateItemDesc(Long id) {
        TbItemDesc tbItemDesc = tbItemDescMapper.updateItemDesc(id);
        TaotaoResult taotaoResult = new TaotaoResult();
        taotaoResult.setStatus(200);
        taotaoResult.setData(tbItemDesc.getItemDesc());
        return taotaoResult;
    }

    @Override
    public TaotaoResult updateItem(TbItem tbItem, String desc) {

        Date date = new Date();
        tbItem.setUpdated(date);
        tbItem.setStatus((byte) 1);
        int count = tbItemMapper.updateItem(tbItem);
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(tbItem.getId());
        tbItemDesc.setUpdated(date);
        tbItemDesc.setItemDesc(desc);
         tbItemDescMapper.updateItemDesc(tbItemDesc);
        if (count != 0) {
            return TaotaoResult.ok();
        }
        return TaotaoResult.build(500, "更新商品有误，请重新");


    }
}
