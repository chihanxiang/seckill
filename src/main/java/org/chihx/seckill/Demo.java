package org.chihx.seckill;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Demo {

    public static void main(String[] args) {
        Bean bean = new Bean();
        bean.setId("0");
        bean.setName("chihx");
        JSONObject json = new JSONObject();
        String s = JSON.toJSONString(bean);
        System.out.println(s);

        Object parse = JSON.parse(s);
    }
}
