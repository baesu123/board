package com.example.board.mapper;

import com.example.board.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User findByUsername(@Param("username") String username);

    User findByRefreshToken(@Param("refreshToken") String refreshToken);

    boolean existsByUsername(@Param("username") String username);

    int insert(User user);

    void updateRefreshToken(@Param("username") String username,
                            @Param("refreshToken") String refreshToken);
}