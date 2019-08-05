package com.taotao.service.impl;

import com.taotao.common.pojo.EasyUiTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Override
    public List<EasyUiTreeNode> getCatList(Long id) {
        List<TbItemCat> tbItemCats = tbItemCatMapper.findTbItemByParentId(id);
        List<EasyUiTreeNode> result = new ArrayList<>();
        for (TbItemCat tbItemCat : tbItemCats) {
            EasyUiTreeNode node = new EasyUiTreeNode();
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            node.setState(tbItemCat.getIsParent()?"closed":"open");
            result.add(node);
        }

        return result;
    }
}
