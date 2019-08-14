package com.taotao.mapper;


import com.taotao.pojo.TbItemParamItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface TbItemParamItemMapper {
    @Insert("INSERT INTO tbitemparamitem(itemId, paramData, created, updated) VALUE (#{itemId},#{paramData},#{created},#{updated})")
    int addTbitemParamItem(TbItemParamItem tbItemParamItem);
    @Select("SELECT * FROM tbitemparamitem WHERE itemId = #{itemId}")
    TbItemParamItem findItemParamByItemId(Long itemId);
}