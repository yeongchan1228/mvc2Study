package mvc.mvc2Study.springmvc.itemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.mvc2Study.springmvc.itemService.domain.member.entity.Member;
import mvc.mvc2Study.springmvc.itemService.domain.member.repository.MemberRepository;
import mvc.mvc2Study.springmvc.itemService.web.SessionConst;
import mvc.mvc2Study.springmvc.itemService.web.session.SessionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

//    @GetMapping("/")
    public String home(){
        return "home";
    }

//    @GetMapping("/")
    public String homeLoginV1(@CookieValue(name = "memberId", required = false) Long memberId, Model model){
        if(memberId == null){
            return "home";
        }

        // 로그인된 사용자
        Member findMember = memberRepository.findById(memberId);
        if(findMember == null){
            return "home";
        }
        // 성공 로직
        model.addAttribute("member", findMember);
        return "loginHome";

    }

//    @GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model){

        Member result = (Member) sessionManager.getSession(request);
        if(result == null) return "home";

        model.addAttribute("member", result);
        return "loginHome";
    }

//    @GetMapping("/")
    public String homeLoginV3(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if(session == null) return "home";

        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if(member == null) return "home";

        model.addAttribute("member", member);
        return "loginHome";
    }

    @GetMapping("/")
    public String homeLoginV4(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, Model model){
        if(member == null) return "home";

        model.addAttribute("member", member);
        return "loginHome";
    }
}
