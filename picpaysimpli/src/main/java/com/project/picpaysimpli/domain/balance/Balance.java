package com.project.picpaysimpli.domain.balance;


import com.project.picpaysimpli.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "saldo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Balance {

    @Id
    private Long Id;

    @MapsId
    @JoinColumn(name = "id_usuario")
    @OneToOne
    private User user;

    @Column(name = "saldo")
    private Double balance;

    public Balance(User user) {
        this.user = user;
        this.balance = 0.0;
    }
}
