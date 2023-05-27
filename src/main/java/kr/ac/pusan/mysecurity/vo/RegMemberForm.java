package kr.ac.pusan.mysecurity.vo;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@RequiredArgsConstructor
public class RegMemberForm {

    @Size(min = 4, max = 8, message = "아이디는 4~8 사이여야합니다.")
    private String userId;
    @NotBlank(message = "이름을 작성해주세요.")
    private String username;
    @Email
    private String email;
    @Min(value = 6 , message = "비밀번호는 6자 이상이여햡니다.")
    private String password;

    public void Encryption(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }
}
