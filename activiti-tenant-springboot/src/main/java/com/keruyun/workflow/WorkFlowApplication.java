package com.keruyun.workflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * 
 * @author lijianghuai
 *
 */
@SpringBootApplication(scanBasePackages = "com.keruyun.workflow")
public class WorkFlowApplication {

  public static void main(String[] args) {
    SpringApplication.run(WorkFlowApplication.class, args);
  }

}
