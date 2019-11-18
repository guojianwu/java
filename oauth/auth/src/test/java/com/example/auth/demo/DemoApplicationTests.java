package com.example.auth.demo;

import com.example.auth.demo.domain.auth.UserDetail;
import com.example.auth.demo.mapper.AuthMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Autowired
	AuthMapper authMapper;
	@Test
	public void contextLoads() {
		UserDetail userById = authMapper.getUserById(3L);
		System.out.println(userById);
	}

}
