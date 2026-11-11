package food_autocomplete.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

//Vue 註冊時允許送進 Spring Boot 的資料格式
public class MemberRegisterDTO {
    private String account;
    private String password;
    private String name;
    private String email;
    private String phone;

    @NotBlank(message = "帳號不能為空")
    public String getAccount() {
        return account;
    }

    @NotBlank(message = "密碼不能為空")
    public String getPassword() {
        return password;
    }

    @NotBlank(message = "姓名不能為空")
    public String getName() {
        return name;
    }

    @NotBlank(message = "Email不能為空")
    @Email(message = "Email格式錯誤")
    public String getEmail() {
        return email;
    }

    @NotBlank(message = "手機號碼不能為空")
    @Pattern(regexp = "^09\\d{8}$", message = "手機號碼格式錯誤")
    public String getPhone() {
        return phone;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
