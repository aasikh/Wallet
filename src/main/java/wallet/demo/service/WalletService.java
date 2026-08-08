package wallet.demo.service;

import org.springframework.stereotype.Service;
import wallet.demo.entity.Wallet;
import wallet.demo.repo.WalletRepo;

@Service
public class WalletService {
public final WalletRepo walletRepo;
public WalletService(WalletRepo walletRepo){
    this.walletRepo = walletRepo;
}

    public String create(Wallet wallet){
       Wallet userWallet = walletRepo.findByUserId(wallet.getUserId());

       if(userWallet!=null){
           return "UserId already exists";
       }
     wallet.setBalance(0);
      walletRepo.save(wallet);
      return "New wallet created";
    }

    public String store(Wallet wallet){
        String userId = wallet.getUserId();
   Wallet userWallet = walletRepo.findByUserId(userId);
    if(userWallet==null){
       return "userNotExists";
     }

    double balance =  userWallet.getBalance() + wallet.getBalance();
    userWallet.setBalance(balance);
    walletRepo.save(userWallet);
    return "Your deposite added successfully";
    }

    public double check(String userId){
     Wallet userWallet = walletRepo.findByUserId(userId);
     if(userWallet==null){
         return -1;
     }
     return userWallet.getBalance();
    }
}
