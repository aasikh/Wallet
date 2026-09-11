package wallet.demo.service;

import org.springframework.stereotype.Service;
import wallet.demo.entity.Wallet;
import wallet.demo.repo.WalletRepo;

import java.nio.file.LinkOption;

@Service
public class WalletService {
public final WalletRepo walletRepo;
public WalletService(WalletRepo walletRepo){
    this.walletRepo = walletRepo;
}

    public String create(Long userId){
       Wallet userWallet = walletRepo.findByUserId(userId);

       if(userWallet!=null){
           return "UserId already exists dont create another account";
       }

       Wallet wallet = new Wallet();
       wallet.setBalance(0);
       wallet.setUserId(userId);
       walletRepo.save(wallet);
      return "New wallet created";
    }

    public String store(Wallet wallet) {

        Long userId = wallet.getUserId();

        // 1. Check whether logged-in user has a wallet
        Wallet userWallet = walletRepo.findByUserId(userId);

        if (userWallet == null) {
            return "Wallet does not exist";
        }

        // 2. Check deposit amount
        if (wallet.getBalance() <= 0) {
            return "Deposit amount must be greater than 0";
        }

        // 3. Add deposit to existing balance
        double newBalance =
                userWallet.getBalance() + wallet.getBalance();

        userWallet.setBalance(newBalance);

        // 4. Save updated wallet
        walletRepo.save(userWallet);

        return "Your deposit added successfully";
    }

    public double check(Long userId){
     Wallet userWallet = walletRepo.findByUserId(userId);
     if(userWallet==null){
         return -1;
     }
     return userWallet.getBalance();
    }
}