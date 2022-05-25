package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.gitee.sunchenbin.mybatis.actable.annotation.*;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.*;

@Getter
@Setter
@ToString
@TableName(value = "t_user")
public class User {
    @TableId(type = IdType.AUTO)
    @Column(name = "id", isKey = true, isAutoIncrement = true)
    private Integer id;

    @Column(name = "username", length = 10, type = MySqlTypeConstant.CHAR)
    @Unique
    private String username;

    @Column(name = "password", length = 15, type = MySqlTypeConstant.CHAR)
    private String password;
}
