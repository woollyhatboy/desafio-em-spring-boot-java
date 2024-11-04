package com.project.picpaysimpli.domain.balance;

import com.project.picpaysimpli.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
}
