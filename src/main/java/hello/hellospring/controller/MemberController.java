package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    //@Autowired /*** 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어줌, 스프링 빈 등록 ***/
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //회원 웹 기능 - 등록
    @GetMapping(value = "/members/new")
    public String createForm() {
        return "member/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {

        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    //회원 웹 기능 - 조회
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
