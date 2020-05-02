package org.chihx.seckill.controller;

import com.alibaba.druid.util.StringUtils;
import org.chihx.seckill.Result.CodeMsg;
import org.chihx.seckill.Result.Result;
import org.chihx.seckill.redis.RedisService;
import org.chihx.seckill.service.SeckillUserService;
import org.chihx.seckill.service.UserService;
import org.chihx.seckill.util.ValidatorUtil;
import org.chihx.seckill.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    protected static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    SeckillUserService seckillUserService;


    // 模板页面输出
    @RequestMapping("/thymeleafa")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "chihx123");
        return "hello";
    }

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(@Valid LoginVo loginVo) {
        logger.info(loginVo.toString());
        // 参数校验
//        String password = loginVo.getPassword();
//        String mobile = loginVo.getMobile();
//        if (StringUtils.isEmpty(password)) {
//            return Result.Error(CodeMsg.PASSWORD_EMPTY);
//        }
//        if (StringUtils.isEmpty(mobile)) {
//            return Result.Error(CodeMsg.MOBILE_EMPTY);
//        }
//        if (!ValidatorUtil.isMobile(mobile)) {
//            return Result.Error(CodeMsg.MOBILE_FORMAT_ERROR);
//        }
        // 登录
        Boolean b = seckillUserService.login(loginVo);
        if (b) {
            return Result.Success(true);
        } else {
            return Result.Error(CodeMsg.SERVER_ERROR);
        }
    }

}
