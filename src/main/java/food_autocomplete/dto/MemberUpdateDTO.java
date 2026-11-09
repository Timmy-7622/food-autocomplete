package food_autocomplete.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public class MemberUpdateDTO {

    @NotBlank(message = "請輸入會員姓名")
    private String name;
    @NotBlank(message = "請輸入 Email")
    // 檢查基本 Email 格式
    @Email(message = "Email 格式錯誤")
    private String email;
    @NotBlank(message = "請輸入手機號碼")
    @Pattern(regexp = "^09\\d{8}$", message = "手機號碼格式錯誤")
    private String phone;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
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
