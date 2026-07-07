package org.example.tliaswebmanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * SpringBoot 集成测试类
 * 用于测试Spring容器是否能正常启动并加载所有Bean
 *
 * @SpringBootTest 注解：该注解会启动完整的Spring应用上下文，
 * 用于进行集成测试，测试时所有配置和Bean都会被加载
 */
@SpringBootTest
class TliasWebManagementApplicationTests {

    /**
     * 测试SpringBoot应用上下文能否正常加载
     * 如果IOC容器创建成功，则测试通过；否则测试失败
     */
    @Test
    void contextLoads() {
    }

}
