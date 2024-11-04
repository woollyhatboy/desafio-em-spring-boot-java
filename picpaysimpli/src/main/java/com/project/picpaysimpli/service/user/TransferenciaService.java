package com.project.picpaysimpli.service.user;

import com.project.picpaysimpli.domain.balance.Balance;
import com.project.picpaysimpli.domain.balance.BalanceRepository;
import com.project.picpaysimpli.domain.user.UserRole;
import com.project.picpaysimpli.domain.user.dto.InformationToTransferDTO;
import com.project.picpaysimpli.domain.user.dto.InsertMoneyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransferenciaService {

    @Autowired
    private BalanceRepository balanceRepository;


    public ResponseEntity transferMoney(InformationToTransferDTO informationToTransferDTO) {

        Optional<Balance> pagadorOpt = balanceRepository.findById(informationToTransferDTO.payer());

        Optional<Balance> recebidorOpt = balanceRepository.findById(informationToTransferDTO.receiver());

        Double valor = informationToTransferDTO.money();

        if (pagadorOpt.isEmpty() || recebidorOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("pagador ou recebidor nao existe");
        }

        Balance pagador = pagadorOpt.get();
        Balance recebidor = recebidorOpt.get();

        if (pagador.getBalance() >= valor && pagador.getUser().getUserRole() != UserRole.LOJISTA) {

            Double novoValorPagador = pagador.getBalance() - valor;

            pagador.setBalance(novoValorPagador);

            Double novoValorRecebidor = recebidor.getBalance() + valor;

            recebidor.setBalance(novoValorRecebidor);

            balanceRepository.save(pagador);
            balanceRepository.save(recebidor);

            return ResponseEntity.ok().body("Transferencia realizada com sucesso !");
        } else {
            return ResponseEntity.badRequest().body("Erro na transferencia, talvez voce seja um lojista ou nao tem saldo suficiente");
        }
    }

    public ResponseEntity insertMoney( InsertMoneyDTO insertMoneyDTO) {

        Optional<Balance> userOpt = balanceRepository.findById(insertMoneyDTO.id());

        Double valor = insertMoneyDTO.money();

        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuario nao existe");
        }

        Balance user = userOpt.get();

        Double novoValorUser = user.getBalance() + valor;

        user.setBalance(novoValorUser);

        balanceRepository.save(user);

        return ResponseEntity.ok("Voce transferiu: " + valor + "R$ com sucesso !");
    }
}
