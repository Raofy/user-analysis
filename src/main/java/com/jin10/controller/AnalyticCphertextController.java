package com.jin10.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.jin10.entity.BaseResponse;
import com.jin10.utils.AESUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Airey
 * @date 2020/3/17 15:42
 * ----------------------------------------------
 * 解析密文
 * ----------------------------------------------
 */
@RestController
@RequestMapping("analytic")
public class AnalyticCphertextController {


    /**
     * 解密数据
     *
     * @param params
     * @return
     */
    @PostMapping("decrypt")
    public BaseResponse decrypt(@RequestBody Map<String, Object> params) {

        String text = MapUtil.getStr(params, "text");
        String decrypt = "";
        String[] split = text.split("\\s+");
        if (split.length == 2) {
            String key = split[0];
            String encryptedText = split[1];
            decrypt = AESUtil.decrypt(DigestUtil.md5Hex(key), encryptedText);
        } else {
            return BaseResponse.error("传递的参数异常！！！");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("decrypt", decrypt);
        return BaseResponse.ok(map);
    }

}
