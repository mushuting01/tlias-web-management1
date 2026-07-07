package org.example.tliaswebmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Tlias教学管理系统 - SpringBoot启动类
 *
 * @SpringBootApplication 是一个组合注解，包含了：
 *   - @SpringBootConfiguration：标识这是一个SpringBoot配置类
 *   - @EnableAutoConfiguration：启用SpringBoot自动配置机制，自动根据依赖装配Bean
 *   - @ComponentScan：自动扫描当前包及其子包下的Spring组件（如@Controller、@Service、@Mapper等）
 */

@ServletComponentScan//开启了对servlet组件的支持
@SpringBootApplication
public class TliasWebManagementApplication {

    /**
     * 应用程序入口方法
     * SpringApplication.run() 负责启动SpringBoot应用，初始化IOC容器
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 启动SpringBoot应用，返回IOC容器上下文
        SpringApplication.run(TliasWebManagementApplication.class, args);
    }

}
