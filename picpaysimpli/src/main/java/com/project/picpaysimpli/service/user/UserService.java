package com.project.picpaysimpli.service.user;

import com.project.picpaysimpli.domain.balance.Balance;
import com.project.picpaysimpli.domain.balance.BalanceRepository;
import com.project.picpaysimpli.domain.user.User;
import com.project.picpaysimpli.domain.user.UserRepository;
import com.project.picpaysimpli.domain.user.dto.TokenOutDTO;
import com.project.picpaysimpli.domain.user.dto.UserInDTO;
import com.project.picpaysimpli.domain.user.dto.UserLoginDTO;
import com.project.picpaysimpli.service.authentication.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private BalanceRepository balanceRepository;


    public ResponseEntity createUser(UserInDTO userInDTO, UriComponentsBuilder uriComponentsBuilder) {

        if (userRepository.existsByEmail(userInDTO.email())) {
            return ResponseEntity.badRequest().body("Email ja usado !");
        }

        if (userRepository.existsByCpfCnpj(userInDTO.cpfCnpj())) {
            return ResponseEntity.badRequest().body("cpf/cnpj ja usado !");
        }

        String cryp = new BCryptPasswordEncoder().encode(userInDTO.password());

        User user = new User(userInDTO, cryp);

        userRepository.save(user);

        Balance balance = new Balance(user);

        balanceRepository.save(balance);

        URI uri = uriComponentsBuilder.path("/login/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body("Usuario cadastrado com sucesso !");
    }

    public ResponseEntity login(UserLoginDTO userLoginDTO) {
        UsernamePasswordAuthenticationToken userNamePassword = new UsernamePasswordAuthenticationToken(userLoginDTO.email(), userLoginDTO.password());

        var auth = this.authenticationManager.authenticate(userNamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new TokenOutDTO(token));
    }
}
