package wallet.demo.service;

import org.springframework.stereotype.Service;
import wallet.demo.entity.Transfer;
import wallet.demo.entity.Wallet;
import wallet.demo.repo.TransferRepo;
import wallet.demo.repo.WalletRepo;

import java.time.LocalDateTime;

@Service
public class TransferService {
 private final WalletRepo walletRepo;
private final TransferRepo transferRepo;
 public TransferService(WalletRepo walletRepo, TransferRepo transferRepo){
     this.walletRepo = walletRepo;
     this.transferRepo = transferRepo;
 }

    public String transferAmount(Transfer transfer){
        Long senId = transfer.getSenderId();
        Long recId = transfer.getRecieverId();
         Wallet walletSen =  walletRepo.findByUserId(senId);
        Wallet walletReci =  walletRepo.findByUserId(recId);
        if(walletSen==null || walletReci==null){
            return "Account is not exists";
        }
      double sendBalance = walletSen.getBalance();
        if(sendBalance< transfer.getAmount()){
            return "Insufficient balance";
        }
        if( transfer.getAmount()<=0){
            return "invalid transfer amount";
        }
        double currRecBalance = walletReci.getBalance() + transfer.getAmount();
        walletReci.setBalance(currRecBalance);
        double currSendBalance = walletSen.getBalance() - transfer.getAmount();
        walletSen.setBalance(currSendBalance);
       walletRepo.save(walletSen);
       walletRepo.save(walletReci);
       transfer.setCreateAt(LocalDateTime.now());
       transferRepo.save(transfer);

        return "Your Amount  Transfered Successfully!";
    }
}
