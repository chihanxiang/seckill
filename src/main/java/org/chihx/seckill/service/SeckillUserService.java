package org.chihx.seckill.service;

import org.chihx.seckill.Result.CodeMsg;
import org.chihx.seckill.dao.SeckillUserDao;
import org.chihx.seckill.domain.SeckillUser;
import org.chihx.seckill.exception.GlobalException;
import org.chihx.seckill.util.MD5Util;
import org.chihx.seckill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillUserService {
    @Autowired
    SeckillUserDao seckillUserDao;

    public SeckillUser getById(Long id) {
        return seckillUserDao.getById(id);
    }

    public Boolean login(LoginVo loginVo) {
        if (loginVo == null) {
            // 交给全局异常处理器去处理
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        // 判断手机号是否存在
        SeckillUser user = getById(Long.valueOf(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        // 验证密码
        String dbPass = user.getPassword();
        String salt = user.getSalt();

        String calcPass = MD5Util.formPassToDBPass(formPass, salt);
        if (calcPass.equals(dbPass)) {
            // 密码正确
//            return CodeMsg.SUCCESS;
            return true;
        } else {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }


    }
}
