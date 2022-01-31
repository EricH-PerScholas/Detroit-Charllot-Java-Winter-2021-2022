package perscholas.form;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class RegisterFormBean {

    // making sure the email is not null and is not empty as in ""
    @NotEmpty(message = "Email is required.")
    @Pattern(regexp = "^.+@.+$" , message = "Invalid email format")
    private String email;

    @Length(min = 1, max = 5,
            message = "First Name must be between 1 and 5 characters in length.")
    private String firstName;

    @NotEmpty(message = "Last Name is required.")
    private String lastName;

    // these annotations work on integer values only
    @Min(value=3, message="Age must be at least 3")
    @Max(value=10, message="Age must be at most 10")
    private Integer age;

    private String password;
    private String confirmPassword;

    // this list is populated by the controller with all error messages
    // in the binding result.
    private List<String> errorMessages = new ArrayList<>();

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
