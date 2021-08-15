package com.mm.springboot.requests;

import lombok.Data;

@Data
public class UserPutRequestBody {

    private Long id;
    private String name;
    private String surname;
    private String gender;

}
