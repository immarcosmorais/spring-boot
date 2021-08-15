package com.mm.springboot.service;

import com.mm.springboot.domain.User;
import com.mm.springboot.repository.UserRepository;
import com.mm.springboot.requests.UserPostRequestBody;
import com.mm.springboot.requests.UserPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Page<User> listAll(Pageable pageable){
        return this.userRepository.findAll(pageable);
    }

    public User findByIdOrThrowBadRequestException(long id){
        return this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not Found"));
    }

    public User save(UserPostRequestBody userPostRequestBody){
        return this.userRepository.save(
                User.builder()
                .name(userPostRequestBody.getName())
                .surname(userPostRequestBody.getSurname())
                .gender(userPostRequestBody.getGender())
                .build()
        );
    }

    public void delete(long id){
        this.userRepository.delete(this.findByIdOrThrowBadRequestException(id));
    }

    public void replace(UserPutRequestBody userPutRequestBody){
        var savedUser = this.findByIdOrThrowBadRequestException(userPutRequestBody.getId());
        var user = User.builder()
                .id(savedUser.getId())
                .name(userPutRequestBody.getName())
                .surname(userPutRequestBody.getSurname())
                .gender(userPutRequestBody.getGender())
                .build();
        this.userRepository.save(user);
    }

}
