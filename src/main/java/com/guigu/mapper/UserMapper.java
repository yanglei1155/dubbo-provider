package com.guigu.mapper;

import com.guigu.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void saveUseAndAccount(User user);
}
