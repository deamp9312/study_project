package jpabook.jpashop.controller;

import jpabook.jpashop.config.CommonCodeService;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.dto.Code;
import jpabook.jpashop.dto.CodeRepository;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    @Autowired
    private final CommonCodeService service;

    private final MemberService memberService;

    @Autowired
    private final CodeRepository codeRepository;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        List<Code> commonCodes = service.getCommonCodes();
        for (Code commonCode : commonCodes) {
            System.out.println("commonCode = " + commonCode);
        }

        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        Code code = new Code();
        code.setId(999);
        code.setCode("B1");
        codeRepository.save(code);
        return "members/memberList";
    }

}
