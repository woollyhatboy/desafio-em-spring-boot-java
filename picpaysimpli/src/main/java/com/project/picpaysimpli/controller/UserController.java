package com.project.picpaysimpli.controller;

import com.project.picpaysimpli.domain.user.dto.UserInDTO;
import com.project.picpaysimpli.domain.user.dto.UserLoginDTO;
import com.project.picpaysimpli.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity createUser(@RequestBody @Valid UserInDTO userInDTO, UriComponentsBuilder uriComponentsBuilder) {
        return userService.createUser(userInDTO, uriComponentsBuilder);
    }

    @PostMapping()
    public ResponseEntity login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }
}
