package org.chihx.seckill.util;

import com.alibaba.druid.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {
    private static final Pattern MOBILE_PATTERN = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src) {
        if (StringUtils.isEmpty(src)) {
            return false;
        }
        Matcher matcher = MOBILE_PATTERN.matcher(src);
        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(ValidatorUtil.isMobile("13805073815"));
        System.out.println(ValidatorUtil.isMobile("1380507381"));
    }
}
