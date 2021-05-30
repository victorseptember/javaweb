package com.learn.mapper;

import com.learn.pojo.Course;

import java.util.List;

public interface CourseMapper {
    List<Course> selByTno(int tno);
}
