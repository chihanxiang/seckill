package org.chihx.seckill.controller;

import com.alibaba.fastjson.JSON;
import org.chihx.seckill.Result.CodeMsg;
import org.chihx.seckill.Result.Result;
import org.chihx.seckill.domain.User;
import org.chihx.seckill.redis.RedisService;
import org.chihx.seckill.redis.UserKey;
import org.chihx.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.provider.MD5;

import java.sql.PreparedStatement;

@Controller
@RequestMapping("/demo")
/**
 * 1、rest api json输出 2、页面
 */
public class SampleController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;


    // 模板页面输出
    @RequestMapping("/thymeleafa")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "chihx");
        return "hello";
    }

    // 数据输出 需要ResponseBody注解
    @RequestMapping("db/get")
    @ResponseBody
    public User dbGet() {
        User user = userService.getById(1);
        return user;
    }

    @RequestMapping("test/error")
    @ResponseBody
    public Result error() {
        return Result.Error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("redis/get")
    @ResponseBody
    public Result redisGet() {
//        redisService.set(UserKey.getById, "key111", "val111");
        User val = redisService.get(UserKey.getById,"1", User.class);
        return Result.Success(JSON.toJSONString(val));
    }

    @RequestMapping("redis/set")
    @ResponseBody
    public Result redisSet() {
        User user = new User();
        user.setId(5);
        user.setName("hsq");
        redisService.set(UserKey.getById, "1", user);
        return Result.Success("success");
    }
}
