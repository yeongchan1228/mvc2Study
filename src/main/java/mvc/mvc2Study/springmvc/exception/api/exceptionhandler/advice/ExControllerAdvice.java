package mvc.mvc2Study.springmvc.exception.api.exceptionhandler.advice;

import lombok.extern.slf4j.Slf4j;
import mvc.mvc2Study.springmvc.exception.api.controller.ApiExceptionController;
import mvc.mvc2Study.springmvc.exception.api.controller.ApiExceptionV2Controller;
import mvc.mvc2Study.springmvc.exception.api.exception.UserException;
import mvc.mvc2Study.springmvc.exception.api.exceptionhandler.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice(annotations = RestController.class) // @RestController가 붙은 컨트롤러만 처리
//@ControllerAdvice("java/mvc/mvc2Study/springmvc/exception/api/controller") // 지정 패키지 하위에 전부 적용
//@ControllerAdvice(assignableTypes = {ApiExceptionController.class, ApiExceptionV2Controller.class}) // 클래스 지정 가능
public class ExControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult illegalExHandler(IllegalArgumentException e){
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler(UserException e){
        log.error("[exceptionHandler] ex", e);
        ErrorResult bad = new ErrorResult("UserException", e.getMessage());
        return new ResponseEntity(bad, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResult ExHandler(Exception e){
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }
}
