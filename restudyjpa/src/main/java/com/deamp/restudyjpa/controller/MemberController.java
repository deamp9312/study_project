package com.deamp.restudyjpa.controller;

import com.deamp.restudyjpa.dto.MemberSearchCondition;
import com.deamp.restudyjpa.dto.MemberTeamDto;
import com.deamp.restudyjpa.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberJpaRepository repository;

    @GetMapping("/v1/members")
    public List<MemberTeamDto> searchMemberV1(MemberSearchCondition condition) {
        return repository.search(condition);
    }

}
