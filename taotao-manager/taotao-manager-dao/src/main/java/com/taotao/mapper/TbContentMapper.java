package com.taotao.mapper;


import com.taotao.pojo.TbContent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbContentMapper {
    @Select("SELECT * FROM tbcontent WHERE categoryId = #{categoryId}")
    List<TbContent> findContentByCategroyId(long contentCategoryId);
    @Insert("INSERT INTO tbcontent(categoryId, title, subTitle, titleDesc, url, pic, pic2, content, created, updated) VALUE (#{categoryId},#{title},#{subTitle},#{titleDesc},#{url},#{pic},#{pic2},#{content},#{created},#{updated})")
    void addContent(TbContent tbContent);
}