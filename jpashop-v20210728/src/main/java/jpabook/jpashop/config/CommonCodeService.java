package jpabook.jpashop.config;

import jpabook.jpashop.dto.Code;
import jpabook.jpashop.dto.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import java.util.ArrayList;
import java.util.List;

@Component
public class CommonCodeService {


    @Autowired
    private CodeRepository repository;

    private List<Code> codes = new ArrayList<>(); // 메모리에 저장될 공통 코드 리스트

    // 10분마다 실행되는 메서드로, 데이터베이스에서 공통 코드를 조회하여 메모리에 저장합니다.
    @Scheduled(fixedDelayString = "${common.code.time}") // 10분마다 실행 (1000ms * 60초 * 10분)
    public void refreshCommonCodes() {
        System.out.println("10분마다 실행됨");
        codes = repository.findAll();
    }

    @PostPersist
    public void onCodeInserted(Code code) {
        codes.add(code);
    }


    // 공통 코드 리스트 반환
    public List<Code> getCommonCodes() {
        if(codes ==null || codes.isEmpty()){
            System.out.println("빈값이라 재호출 했음");
            codes = repository.findAll();
        }
        return codes;
    }
}
