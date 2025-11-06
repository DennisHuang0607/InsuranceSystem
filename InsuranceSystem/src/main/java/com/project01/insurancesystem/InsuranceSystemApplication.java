package com.project01.insurancesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//應用系統啟動時進行初始化佈署的地方(控制器/Spring Bean/Spring Service/Repository/Component...)
@SpringBootApplication //用在主類別(Main Class)上，讓Spring Boot設定環境(自動配置、元件掃描)
//自動掃描指定package並註冊Spring Bean
@ComponentScan(basePackages= {"com.project01.controller","com.project01.repository","com.project01.service","com.project01.entity","com.project01.config","com.project01.component"})
@EntityScan(basePackages = {"com.project01.entity"})
@EnableJpaRepositories(basePackages = {"com.project01.repository"})
public class InsuranceSystemApplication {

	public static void main(String[] args) {
		//Spring Boot應用程式(網站系統)的啟動入口
		SpringApplication.run(InsuranceSystemApplication.class, args);
	}

}
