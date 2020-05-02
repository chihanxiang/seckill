package org.chihx.seckill.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.chihx.seckill.domain.SeckillUser;

@Mapper
public interface SeckillUserDao {
    @Select("select * from miaosha_user where id = #{id}")
    public SeckillUser getById(@Param("id")Long id);
}
