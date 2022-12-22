package lk.ijse.dep9.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lk.ijse.dep9.app.util.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = "password", allowSetters = true)
public class UserDTO implements Serializable {

    @NotBlank(message = "Username cannot be empty or null", groups = ValidationGroups.Create.class)
    private String username;
    @NotBlank(message = "Full Name cannot be empty or null")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Invalid name")
    private String fullName;
    @NotEmpty(message = "Password cannot be empty or null")
    @Length(min = 3, message = "Password should contain at least 3 characters")
    private String password;

//    @JsonIgnore
//    public String getPassword() {
//        return password;
//    }
}
