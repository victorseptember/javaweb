package com.practice.ciphertextsrh.service.impl;

import com.practice.ciphertextsrh.dao.SelectDao;
import com.practice.ciphertextsrh.dao.impl.SelectDaoImpl;
import com.practice.ciphertextsrh.service.SelectService;

import java.util.List;

public class SelectServiceImpl implements SelectService {

    SelectDao check = new SelectDaoImpl();
    @Override
    public List<String> selectService(String string) {
        return check.selectBreachDao(string);
    }
}
