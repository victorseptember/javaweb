package com.learn.mapper;

import java.util.List;

public interface UserMapper {
    List<?> selAll();
    List<?> selByAgeAndSex(int age, String sex);
}
