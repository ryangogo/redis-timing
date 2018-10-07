package com.ryan.demo.service.impl;

import com.ryan.demo.dao.TestMapper;
import com.ryan.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * describe：
 * Created with IDEA
 * author:ryan
 * Date:2018/10/4
 * Time:下午4:09
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public String getMapperDemo() {
        return testMapper.selectById(1).toString();
    }

}
