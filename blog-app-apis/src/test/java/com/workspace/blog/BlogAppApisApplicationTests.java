package com.workspace.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.workspace.blog.repositories.UserRepo;
import com.workspace.blog.service.UserService;

@SpringBootTest
class BlogAppApisApplicationTests {
	
//	@Autowired
//	private UserRepo userRepo;
	
	@Test
	void contextLoads() {
	}
//	@Test
//	public void repoTest() {
//		String className=this.userRepo.getClass().getName();
//		String packageName=this.userRepo.getClass().getPackageName();
//		System.out.println(className);
//		System.out.println(packageName);
//	}

}
