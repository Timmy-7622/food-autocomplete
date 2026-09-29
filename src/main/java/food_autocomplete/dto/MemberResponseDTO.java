package food_autocomplete.dto;

public class MemberResponseDTO {

    private Long memberId;
    private String account;
    private String name;
    private String email;
    private String phone;
    private String role;

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public void setRole(String role) {
        this.role = role;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
    }

}
