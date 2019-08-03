package orthae.com.github.medicalmanagementsystem.server.session.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
