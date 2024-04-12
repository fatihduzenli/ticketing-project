package com.cydeo.dto;

import com.cydeo.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String firstname;
    private String lastname;
    private String password;
    private String userName;
    private boolean enabled;
    private String phone;
    private RoleDTO role;
    private Gender gender;

}
