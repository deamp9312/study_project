package jpabook.jpashop.config;

import jpabook.jpashop.dto.Code;
import jpabook.jpashop.dto.CodeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommonCodeServiceTest {

    @Autowired
    private CodeRepository repository;
    @Test
    public void initData(){
        for (int i = 0; i < 100; i++) {
            Code code = new Code();
            code.setId(i);
            code.setCode("A"+i);
            repository.save(code);
        }
    }

}