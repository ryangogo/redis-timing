package com.ryan.demo.dao;

import com.ryan.demo.pojo.Test;
import org.apache.ibatis.annotations.Param;

/**
 * describe：
 * Created with IDEA
 * author:ryan
 * Date:2018/10/4
 * Time:下午3:27
 */

public interface TestMapper {

    Test selectById(@Param("id") Integer id);

}
