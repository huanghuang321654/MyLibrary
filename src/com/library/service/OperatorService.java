package com.library.service;

import com.library.entity.Operator;

import java.util.List;

public interface OperatorService {
    //登录方法
    boolean login(Operator operator);

    //插入方法
    int insert(Operator operator);

    //删除方法
    int delete(Integer id);

    //修改方法
    int update(Operator operator);

    //通过操作员ID进行查询的方法
    List<Operator> queryByOperatorId(Integer id);

    //查询全部操作员信息
    List<Operator> queryAll();

    ////通过操作员名字进行查询的方法
    List<Operator> queryByOperatorName(String name);
}
