package com.taotao.controller;


import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;


    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem findItem(@PathVariable long itemId){
        TbItem item = itemService.getItemById(itemId);
        return item;
    }
    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIResult getItemList(Integer page,Integer rows){
        EasyUIResult result = itemService.getItemList(page, rows);
        return result;
    }
    @RequestMapping("/item/save")
    @ResponseBody
    public TaotaoResult getItemDesc(TbItem tbItem,String desc){
        TaotaoResult result=itemService.addItems(tbItem,desc);
        return  result;
    }
    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public  TaotaoResult deleteItems(Long[] ids){
        TaotaoResult result=itemService.deleteItems(ids);
        return  result;
    }
    @RequestMapping("/rest/item/instock")
    @ResponseBody
    public TaotaoResult lowerShelf(Long[] ids){
        TaotaoResult result = itemService.lowerShelf(ids);
        return  result;
    }
    @RequestMapping("/rest/item/reshelf")
    @ResponseBody
    public TaotaoResult upperShelf(Long[] ids){
        TaotaoResult result = itemService.upperShelf(ids);
        return  result;
    }
    @RequestMapping("/item/query/item/desc/{itemId} ")
    @ResponseBody
    public TaotaoResult updateItemDesc(@PathVariable long itemId){
        TaotaoResult result=itemService.updateItemDesc(itemId);
        return  result;
    }
    @RequestMapping("/item/update")
    @ResponseBody
    public TaotaoResult updateItem(TbItem tbItem,String desc){
        TaotaoResult taotaoResult =itemService.updateItem(tbItem, desc);
        System.out.println(taotaoResult);
        return  taotaoResult;
    }


}