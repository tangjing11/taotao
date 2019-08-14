package com.taotao.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private TbItemParamMapper tbItemParamMapper;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;


    @Override
    public TaotaoResult getItemParamByCid(long itemCatId) {
        TbItemParam tbItemParam = tbItemParamMapper.findItemParamByCatId(itemCatId);
        if(tbItemParam!=null){
            return TaotaoResult.ok(tbItemParam);
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult addItemParam(TbItemParam tbItemParam) {
        //默认页面已经吧要想要的参数 根据 我们的pojo 的属性已经绑定好了不用java程序员去管理
        Date time = new Date();
        tbItemParam.setCreated(time);
        tbItemParam.setUpdated(time);

        tbItemParamMapper.addItemParam(tbItemParam);

        return TaotaoResult.ok();
    }

    @Override
    public String getItemParamByItemId(Long itemId) {
        TbItemParamItem tbItemParamItem = tbItemParamItemMapper.findItemParamByItemId(itemId);
        String paramData = tbItemParamItem.getParamData();
        //问题是 如果说有web程序员配合我们  我们直接返回这个json格式 页面就自己绑定数据了
        //现在的问题是  这个页面 没有web程序员来做
        List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
        StringBuffer sb = new StringBuffer();
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
        sb.append("    <tbody>\n");
        for(Map m1:jsonList) {
            sb.append("        <tr>\n");
            sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
            sb.append("        </tr>\n");
            List<Map> list2 = (List<Map>) m1.get("params");
            for(Map m2:list2) {
                sb.append("        <tr>\n");
                sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
                sb.append("            <td>"+m2.get("v")+"</td>\n");
                sb.append("        </tr>\n");
            }
        }
        sb.append("    </tbody>\n");
        sb.append("</table>");
        return sb.toString();

    }
}
