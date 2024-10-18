package com.selvam.dreamshops.request;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
