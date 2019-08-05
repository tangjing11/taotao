package com.taotao.mapper;

import com.taotao.pojo.TbItemDesc;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TbItemDescMapper {

    @Insert("INSERT INTO tbitemdesc(itemId, itemDesc, created, updated) VALUE (#{itemId},#{itemDesc},#{created},#{updated})")
    int addItemDesc(TbItemDesc tbItemDesc);
    @Select("select itemDesc from tbitemdesc where itemId=#{itemId}")
    TbItemDesc updateItemDesc(Long itemId);
    @Update("<script>update tbitemdesc <set>" +
            "<if test='itemDesc != null'>itemDesc = #{itemDesc},</if>" +
            "<if test='created != null'>created = #{created},</if>" +
            "<if test='updated != null'>updated = #{updated},</if>" +
            "</set> where itemId = #{itemId}</script>")

    void updateItemDesc( TbItemDesc tbItemDesc);
}