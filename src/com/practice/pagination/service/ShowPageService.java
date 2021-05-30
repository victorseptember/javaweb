package com.practice.pagination.service;

import com.practice.pagination.pojo.PageInfo;

import java.io.IOException;

public interface ShowPageService {
    PageInfo showPage(int pageSize,int pageNumber) throws IOException;
}
