package com.jincrates.springbootjpa.controller;

import com.jincrates.springbootjpa.domain.Address;
import com.jincrates.springbootjpa.domain.Member;
import com.jincrates.springbootjpa.domain.item.Book;
import com.jincrates.springbootjpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {

        if(result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    //리스트를 뿌릴때도 Member 엔티티를 그대로 가져와서 뿌리기보다는
    //필요한 정보를 담고 있는 DTO를 만들어서 뿌리는 것을 권장함
    //API를 만들 때는 이유를 불문하고 절대 엔티티를 넘겨선 안 된다.
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
