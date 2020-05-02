package org.chihx.seckill.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.chihx.seckill.domain.User;

@Mapper
public interface UserDao {
//    @Select("select * from tUser where id = #{id}")
//    public User getUserById(@Param("id") int id);

    @Select("select * from tUser where id = #{id}")
    public User getById(@Param("id")int id);
}
