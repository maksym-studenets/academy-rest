package co.inventorsoft.academyrest.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Accessors(chain = true)
public class CustomerDto {

    private Integer id;

    @Size(min = 3, max = 50, message = "Last name must be at 3 chars long")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    @Email(message = "Email must be valid")
    private String email;

    private String phone;
}
