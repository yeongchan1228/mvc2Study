package mvc.mvc2Study.springmvc.itemService.domain.login.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.mvc2Study.springmvc.itemService.domain.member.entity.Member;
import mvc.mvc2Study.springmvc.itemService.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public Member login(String loginId, String password){
//        Optional<Member> findMemberOptional = memberRepository.findByLoginId(loginId);
//        Member findMember = findMemberOptional.get();
//        if (findMember.getPassword().equals(password)){
//            return findMember;
//        }
//        return null;
        log.info("loginId = {}, password = {}", loginId, password);
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password)).orElse(null);

    }

}
