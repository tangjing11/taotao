package com.taotao.service;

import com.taotao.common.pojo.EasyUiTreeNode;

import java.util.List;

public interface ItemCatService {
    List<EasyUiTreeNode> getCatList(Long id);
}
