package com.library.service.impl;

import com.library.dao.OperatorDao;
import com.library.dao.impl.OperatorImpl;
import com.library.entity.Operator;
import com.library.service.OperatorService;

import java.util.List;

public class OperatorServiceImpl implements OperatorService {
    OperatorDao operatorDao = new OperatorImpl();

    @Override
    public boolean login(Operator operator) {
        return operatorDao.login(operator);
    }

    @Override
    public int insert(Operator operator) {
        return operatorDao.insert(operator);
    }

    @Override
    public int delete(Integer id) {
        return operatorDao.delete(id);
    }

    @Override
    public int update(Operator operator) {
        return operatorDao.update(operator);
    }

    @Override
    public List<Operator> queryByOperatorId(Integer id) {
        return operatorDao.queryByOperatorId(id);
    }

    @Override
    public List<Operator> queryAll() {
        return operatorDao.queryAll();
    }

    @Override
    public List<Operator> queryByOperatorName(String name) {
        return operatorDao.queryByOperatorName(name);
    }
}
