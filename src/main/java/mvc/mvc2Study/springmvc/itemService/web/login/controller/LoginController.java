package mvc.mvc2Study.springmvc.itemService.web.login.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.mvc2Study.springmvc.itemService.domain.login.service.LoginService;
import mvc.mvc2Study.springmvc.itemService.domain.member.entity.Member;
import mvc.mvc2Study.springmvc.itemService.web.SessionConst;
import mvc.mvc2Study.springmvc.itemService.web.login.entity.dto.LoginDto;
import mvc.mvc2Study.springmvc.itemService.web.session.SessionManager;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginDto") LoginDto loginDto){
        return "login/loginForm";
    }

//    @PostMapping("/login")
    public String loginV1(@Validated @ModelAttribute("loginDto") LoginDto loginDto,
                        BindingResult bindingResult, HttpServletResponse response){
        if(bindingResult.hasErrors())
            return "login/loginForm";

        Member loginMember = loginService.login(loginDto.getLoginId(), loginDto.getPassword());
        if(loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호 오류 입니다.");
            return "login/loginForm";
        }

        // 로그인 성공 처리 TODO

        // 쿠키에 시간 정보를 주지 않으면 세션 쿠키가 된다.
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        response.addCookie(idCookie);

        return "redirect:/";
    }

//    @PostMapping("/logout")
    public String logoutV1(HttpServletResponse response){
        expireCookie(response, "memberId");

        return "redirect:/";
    }

//    @PostMapping("/login")
    public String loginV2(@Validated @ModelAttribute("loginDto") LoginDto loginDto,
                          BindingResult bindingResult, HttpServletResponse response){
        if(bindingResult.hasErrors())
            return "login/loginForm";

        Member loginMember = loginService.login(loginDto.getLoginId(), loginDto.getPassword());
        if(loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호 오류 입니다.");
            return "login/loginForm";
        }

        // 로그인 성공 처리 TODO

        // 쿠키에 시간 정보를 주지 않으면 세션 쿠키가 된다.
        sessionManager.createSession(loginMember, response);

        return "redirect:/";
    }

//    @PostMapping("/login")
    public String loginV3(@Validated @ModelAttribute("loginDto") LoginDto loginDto,
                          BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors())
            return "login/loginForm";

        Member loginMember = loginService.login(loginDto.getLoginId(), loginDto.getPassword());
        if(loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호 오류 입니다.");
            return "login/loginForm";
        }

        // 로그인 성공 처리 TODO
        // 세션이 있으면 있는 세션 반환, 세션이 없으면 생성해서 반환
        // creat 옵션 default -> true
        // creat false -> 세션이 없을 시 생성 X
        HttpSession session = request.getSession();
        // 세션에 로그인 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);


        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginV4(@Validated @ModelAttribute("loginDto") LoginDto loginDto,
                          BindingResult bindingResult, HttpServletRequest request,
                          @RequestParam(value = "redirectUrl", defaultValue = "/") String url){
        if(bindingResult.hasErrors())
            return "login/loginForm";

        Member loginMember = loginService.login(loginDto.getLoginId(), loginDto.getPassword());
        if(loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호 오류 입니다.");
            return "login/loginForm";
        }

        // 로그인 성공 처리 TODO
        // 세션이 있으면 있는 세션 반환, 세션이 없으면 생성해서 반환
        // creat 옵션 default -> true
        // creat false -> 세션이 없을 시 생성 X
        HttpSession session = request.getSession();
        // 세션에 로그인 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:" + url;
    }
//    @PostMapping("/logout")
    public String logoutV2(HttpServletRequest request){
        sessionManager.expire(request);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request){
        // true면 생성 시 세션을 만들기 때문에 false
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }

        return "redirect:/";
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}
