package com.practice.pagination.service.impl;

import com.learn.login.pojo.User;
import com.practice.pagination.pojo.PageInfo;
import com.practice.pagination.service.ShowPageService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowPageServiceImpl implements ShowPageService {

    PageInfo pageInfo = new PageInfo();
    @Override
    public PageInfo showPage(int pageSize, int pageNumber) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis_config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

        SqlSession session = factory.openSession();

        Map<String,Object> map = new HashMap<>();
        map.put("pageStart", pageSize*(pageNumber-1));
        map.put("pageSize", pageSize);
        List<User> list = session.selectList("a.b.selByPage",map);

        int total = session.selectOne("a.b.selCount");
        pageInfo.setList(list);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNumber(pageNumber);
        pageInfo.setTotal(total);

        return pageInfo;
    }
}
