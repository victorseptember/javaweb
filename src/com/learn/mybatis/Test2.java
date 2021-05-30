package com.learn.mybatis;

import com.learn.login.pojo.User;
import com.learn.mapper.TeacherMapper;
import com.learn.mapper.UserMapper;
import com.learn.pojo.Teacher;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 测试使用getMapper接口绑定以及多参数传递
 */
public class Test2 {
    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis_config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

        SqlSession session = factory.openSession();
//        UserMapper userMapper = session.getMapper(UserMapper.class);
        TeacherMapper teacherMapper = session.getMapper(TeacherMapper.class);

//        List<User> list = (List<User>) userMapper.selAll();
//        for (User user : list) {
//            System.out.println(user.getUname());
//        }

//        List<User> list = (List<User>) userMapper.selByAgeAndSex(20,"男");
//        for (User user : list) {
//            System.out.println(user.toString());
//        }

//        List<Teacher> list = session.selectList("com.learn.mapper.TeacherMapper.selAll");
//        List<Teacher> list = teacherMapper.selAll();
//        for (Teacher teacher : list) {
//            System.out.println(teacher.toString());
//        }

        List<Teacher> list = teacherMapper.selAll2();
        for (Teacher teacher : list) {
            System.out.println(teacher.toString());
        }

        }
}
