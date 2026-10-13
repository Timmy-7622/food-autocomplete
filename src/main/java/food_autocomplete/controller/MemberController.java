package food_autocomplete.controller;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import food_autocomplete.dto.MemberUpdateDTO;
import food_autocomplete.dto.MemberLoginDTO;
import food_autocomplete.dto.MemberRegisterDTO;
import food_autocomplete.dto.MemberResponseDTO;
import food_autocomplete.entity.Member;
import food_autocomplete.service.MemberService;
import jakarta.servlet.http.HttpSession;

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
    public MemberResponseDTO login(@RequestBody MemberLoginDTO request, HttpSession session) {
        // 把Request的帳號密碼交給Service驗證
        // 驗證成功後Service return完整Member
        Member member = memberService.login(
                request.getAccount(),
                request.getPassword());

        // 把DB的member轉成Spring Security認得的ROLE_MEMBER
        // GrantedAuthority是Spring Security的權限介面
        List<GrantedAuthority> authorities = List.of(
                // Spring提供的實作SimpleGrantedAuthority
                new SimpleGrantedAuthority(
                        "ROLE_" + member.getRole()));
        // ③ 建立 Spring Security 認得的登入身分
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                member.getAccount(), // principal
                null, // credentials
                authorities // authorities
        );

        // 建立準備回傳給前端的DTO
        MemberResponseDTO response = new MemberResponseDTO();
        // 從Member挑出允許回傳的資料
        response.setMemberId(member.getMemberId());
        response.setAccount(member.getAccount());
        response.setName(member.getName());
        response.setEmail(member.getEmail());
        response.setPhone(member.getPhone());
        response.setRole(member.getRole());

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

        // 交給Spring → json → HTP response
        return response;
    }

    @GetMapping("/me")
    public MemberResponseDTO  getCurrentMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //authentication.getName取得timmy003 是因為authentication是 Spring Security 定義的介面
        //當 principal 是字串 "timmy003" 時，getName() 就會取得這個字串。
        //credentials = null
        //authorities = ROLE_MEMBER
        String account = authentication.getName();
        Member member = memberService.findByAccount(account);
        MemberResponseDTO response = new MemberResponseDTO();

        response.setMemberId(member.getMemberId());
        response.setAccount(member.getAccount());
        response.setName(member.getName());
        response.setEmail(member.getEmail());
        response.setPhone(member.getPhone());
        response.setRole(member.getRole());
        return response;
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();// 讓目前這個 HttpSession 失效
        return "登出成功";
    }
    
    @PutMapping ("/profile")
    public MemberResponseDTO updateProfile(@RequestBody MemberUpdateDTO dto, Authentication authentication){
        // 1. 取得目前登入會員的帳號
        String account = authentication.getName();
        // 2. 呼叫 Service 更新會員資料
        Member member = memberService.updateMember(account, dto);
        // 3. 建立準備回傳給前端的 DTO
        MemberResponseDTO response = new MemberResponseDTO();
        // 4. 將更新後的會員資料放進 ResponseDTO
        response.setMemberId(member.getMemberId());
        response.setAccount(member.getAccount());
        response.setName(member.getName());
        response.setEmail(member.getEmail());
        response.setPhone(member.getPhone());
        response.setRole(member.getRole());

        return  response;
    }
}
