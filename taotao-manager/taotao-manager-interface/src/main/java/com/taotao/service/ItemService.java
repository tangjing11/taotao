package com.taotao.service;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;

public interface ItemService {

    TbItem getItemById(Long itemId);
    EasyUIResult getItemList(int page, int pageSize);
    TaotaoResult addItems(TbItem tbItem, String desc);
    TaotaoResult deleteItems(Long[] ids);
    TaotaoResult lowerShelf(Long[] ids);
    TaotaoResult upperShelf(Long[] ids);
    TaotaoResult updateItemDesc(Long id);
    TaotaoResult updateItem(TbItem tbItem,String des);
}