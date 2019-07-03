package com.mypack;

import com.mypack.domain.Employee;
import com.mypack.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootCacheApplicationTests {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisTemplate<Object, Employee> empRedisTemplate;
    @Test
    public void contextLoads() {
        Employee empById = employeeMapper.getEmpById(1);
//        redisTemplate.opsForValue().set("emp-01",empById);
        empRedisTemplate.opsForValue().set("emp-02",empById);

    }
    @Test
    public void contextLoads1() {
       stringRedisTemplate.opsForValue().append("msg","world");
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);
    }

}