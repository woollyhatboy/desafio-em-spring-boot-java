package com.project.picpaysimpli.domain.user;

import com.project.picpaysimpli.domain.user.dto.UserInDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String password;

    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    @Column(name = "lojista")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User(UserInDTO userInDTO, String cryp) {
        this.name = userInDTO.name();
        this.email = userInDTO.email();
        this.password = cryp;
        this.cpfCnpj = userInDTO.cpfCnpj();
        this.userRole = userInDTO.role();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.userRole == UserRole.LOJISTA) {
            return List.of(new SimpleGrantedAuthority("ROLE_LOJISTA"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
