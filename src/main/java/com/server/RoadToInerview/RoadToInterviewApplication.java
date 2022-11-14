package com.server.RoadToInerview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//@SpringBootApplication(exclude ={DataSourceAutoConfiguration.class}) //데이터베이스 연결 전에 실행 가능하도록 만듦
public class RoadToInterviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoadToInterviewApplication.class, args);
	}

}
