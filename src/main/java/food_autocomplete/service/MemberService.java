package food_autocomplete.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import food_autocomplete.entity.Member;
import food_autocomplete.repository.MemberRepository;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // DI 依賴注入
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member register(Member member) {
        // 1. 檢查帳號是否已存在
        if (memberRepository.findByAccount(member.getAccount()).isPresent()) {
            throw new RuntimeException("帳號已存在");
        }

        // encode把密碼用亂碼處理...
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
        // 3. 一般註冊會員的角色由後端決定
        member.setRole("MEMBER");
        // 4. 儲存會員
        return memberRepository.save(member);
    }

    public Member login(String account, String password){
        Member member = memberRepository.findByAccount(account).orElseThrow(() -> new RuntimeException("帳號不存在"));

        if(!passwordEncoder.matches(password,member.getPassword())){
            throw new RuntimeException("密碼錯誤");
        }
        return member;    
    }
}
