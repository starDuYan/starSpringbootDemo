package com.star;

import com.star.bean.Student;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j
@SpringBootTest
class StarSpringbootJdbcTemplateApplicationTests {

    @Test
    void contextLoads() {
        log.error(new Student().toString());
    }

}
