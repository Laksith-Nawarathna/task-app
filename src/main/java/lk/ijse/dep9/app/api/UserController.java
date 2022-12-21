package lk.ijse.dep9.app.api;

import lk.ijse.dep9.app.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserController {

    /*   To take the error handling to our hand from spring   */

//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping(consumes = "application/json")
//    public void createUserAccount(@Valid @RequestBody UserDTO user, Errors errors) {
//        Optional<FieldError> firstError = errors.getFieldErrors().stream().findFirst();
//        if (firstError.isPresent()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, firstError.get().getDefaultMessage());
//        }
//    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public void createUserAccount(@Valid @RequestBody UserDTO user) {
        System.out.println(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/me", consumes = "application/json")
    public void updateUserAccountDetails(@Valid @RequestBody UserDTO user) {
        System.out.println(user);
    }

    @GetMapping("/me")
    public void getUserAccountDetails() {

    }

    @DeleteMapping("/me")
    public void deleteUserAccount() {

    }

}
