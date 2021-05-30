package com.learn.mybatis;

import com.learn.login.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {
    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis_config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //sqlsession默认不自动提交事务
        //每个sqlsession对象只有一个事务对象，即执行的SQL语句为一个事务，
        SqlSession session = factory.openSession();

        List<User> list = session.selectList("a.b.selAll");
        for (User user : list) {
            System.out.println(user.getUname());
        }

        User user = session.selectOne("a.b.selById",1);
        System.out.println(user.toString());

        User userInsert = new User();
        userInsert.setUid(4);
        userInsert.setUname("王二哈");
        userInsert.setPwd("666");
        userInsert.setAge(20);
        userInsert.setSex("男");

        int a = session.insert("a.b.ins",userInsert);
        if (a>0){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }

        session.commit();  //需要手动提交事务
//        session.rollback();
        session.close();
    }
}
