package com.mm.springboot.requests;

import lombok.Data;

@Data
public class UserPostRequestBody {

    private String name;
    private String surname;
    private String gender;

}
