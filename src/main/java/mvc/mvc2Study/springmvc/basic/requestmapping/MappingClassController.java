package mvc.mvc2Study.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    @GetMapping()
    public String users(){
        return "get users";
    }

    @PostMapping()
    public String addUser(){
        return "add user";
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId){
        return "user " + userId;
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId){
        return "update user " + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        return "delete user " + userId;
    }

}
