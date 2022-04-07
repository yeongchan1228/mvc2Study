package mvc.mvc2Study.springmvc.exception.api.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import mvc.mvc2Study.springmvc.exception.api.exception.UserException;
import mvc.mvc2Study.springmvc.exception.api.exceptionhandler.ErrorResult;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ApiExceptionV2Controller {

//    @ExceptionHandler(IllegalArgumentException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResult illegalExHandler(IllegalArgumentException e){
//        log.error("[exceptionHandler] ex", e);
//        return new ErrorResult("BAD", e.getMessage());
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<ErrorResult> userExHandler(UserException e){
//        log.error("[exceptionHandler] ex", e);
//        ErrorResult bad = new ErrorResult("UserException", e.getMessage());
//        return new ResponseEntity(bad, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ErrorResult ExHandler(Exception e){
//        log.error("[exceptionHandler] ex", e);
//        return new ErrorResult("EX", "내부 오류");
//    }

    @ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult typeMismatchExHandler(IllegalArgumentException e){
        log.error("[typeMismatchException] ex", e);
        return new ErrorResult("TypeMismatch", e.getMessage());
    }


    @GetMapping("/api/v2/members/{id}")
    public MemberDto getMember(@PathVariable String id){
        if(id.equals("ex")) throw new RuntimeException("잘못된 사용자");

        if(id.equals("bad")) throw new IllegalArgumentException("잘못된 입력 값");

        if(id.equals("user-ex")) throw new UserException("사용자 오류");

        return new MemberDto(id, "hello " + id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String memberId;
        private String name;
    }
}
