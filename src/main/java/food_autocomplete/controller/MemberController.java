package food_autocomplete.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import food_autocomplete.dto.MemberLoginDTO;
import food_autocomplete.dto.MemberRegisterDTO;
import food_autocomplete.dto.MemberResponseDTO;
import food_autocomplete.entity.Member;
import food_autocomplete.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;// DI注入

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    // Http RequestBody Body → JSON → 透過@RequestBody →
    // 轉成MemberRegisterDTO的格式後面才可以dto.getxxx
    public MemberResponseDTO register(@RequestBody MemberRegisterDTO request) {

        Member member = new Member();

        member.setAccount(request.getAccount());
        member.setPassword(request.getPassword());
        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setPhone(request.getPhone());

        Member savedMember = memberService.register(member);

        MemberResponseDTO response = new MemberResponseDTO();

        response.setMemberId(savedMember.getMemberId());
        response.setAccount(savedMember.getAccount());
        response.setName(savedMember.getName());
        response.setEmail(savedMember.getEmail());
        response.setPhone(savedMember.getPhone());

        return response;
    }

    @PostMapping("/login")
    public MemberResponseDTO login(@RequestBody MemberLoginDTO request){
        Member member = memberService.login(
            request.getAccount(),
            request.getPassword()
        );

        MemberResponseDTO response = new MemberResponseDTO();
        response.setMemberId(member.getMemberId());
        response.setAccount(member.getAccount());
        response.setName(member.getName());
        response.setEmail(member.getEmail());
        response.setPhone(member.getPhone());
        response.setRole(member.getRole());

        return response;
    }
    
}
