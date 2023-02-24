package com.springannotations.annotations;

import componentScanTest.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan({"componentScanTest", "com.springannotations.annotations"})
public class AnnotationsApplication implements CommandLineRunner {

	@Autowired
	@Qualifier("user1")
	User user1;

	@Autowired
	@Qualifier("user2")
	User user2;

	@Autowired
	Employee employee;


	public static void main(String[] args) {
		SpringApplication.run(AnnotationsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		user1.printUserData();
//		user2.printUserData();
		employee.getEmployee();
	}
}
