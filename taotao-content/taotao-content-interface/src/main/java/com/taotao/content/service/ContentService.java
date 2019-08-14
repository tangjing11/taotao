package com.taotao.content.service;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

import java.util.List;

public interface ContentService {
    EasyUIResult findContentAll(long contentCategoryId);
    TaotaoResult addContent(TbContent tbContent);
    List<TbContent> getContentAll(long contentCategoryId);
}
