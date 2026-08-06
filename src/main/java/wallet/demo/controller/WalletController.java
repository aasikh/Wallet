package wallet.demo.controller;

import org.springframework.web.bind.annotation.*;
import wallet.demo.entity.Wallet;
import wallet.demo.service.WalletService;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    private final WalletService walletService;
    public WalletController(WalletService walletService){
        this.walletService = walletService;
    }

    @PostMapping("/create")
    public String create(@RequestBody Wallet wallet){
      return walletService.create(wallet);
    }
    @PostMapping("/deposite")
    public String add(@RequestBody Wallet wallet){
        if(wallet.getBalance()<0){
           return "balance Must be greater than 0";
         }
       return walletService.store(wallet);
    }

    @GetMapping("/balance/{userId}")
    public double check(@PathVariable String userId){
        return walletService.check(userId);
    }
}

