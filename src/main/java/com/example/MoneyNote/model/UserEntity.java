package com.example.MoneyNote.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "姓名不可為空")
    private String name;

    @Email(message = "帳號必須是Email 格式")
    @NotBlank(message = "帳號不可為空")
    @Column
    private String username;

    @NotBlank(message = "密碼不可為空")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[\\w]{6,16}$",
            message = "密碼必須為長度6~16位碼大小寫英文加數字")
    @Column
    private String password;

    @Column
    private String salt;

    @Column
    private String created_time;

    @Column
    private String modified_time;
}
