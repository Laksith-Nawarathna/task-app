package lk.ijse.dep9.app.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserController {

    @PostMapping
    public void createUserAccount(){

    }

    @PatchMapping("/me")
    public void updateUserAccountDetails(){

    }

    @GetMapping("/me")
    public void getUserAccountDetails(){

    }

    @DeleteMapping("/me")
    public void deleteUserAccount(){

    }

}
