package com.taotao.service.impl;

import com.taotao.common.pojo.PictureResult;
import com.taotao.common.util.FtpUtil;
import com.taotao.common.util.IDUtils;
import com.taotao.service.PictureService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;

@Service
public class PictureServiceImpl implements PictureService {
    //从resource.properties配置文件中根据key取value
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FILI_UPLOAD_PATH}")
    private String FILI_UPLOAD_PATH;
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;
    @Override
    public PictureResult uploadFile(byte[] bytes, String name) {
        PictureResult result = new PictureResult();
        String newName = IDUtils.genImageName() + name.substring(name.lastIndexOf("."));
        //当前日期
        String filepath = new DateTime().toString("yyyy/MM/dd");
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        boolean b = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD,
                FILI_UPLOAD_PATH, filepath, newName, bis);
        if (b) {
            result.setError(0);
            result.setUrl(IMAGE_BASE_URL + "/" + filepath + "/" + newName);
        } else {
            result.setError(1);
            result.setMessage("图片上传失败");
        }
        return result;
    }
}