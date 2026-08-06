package wallet.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wallet.demo.entity.Wallet;
import wallet.demo.service.WalletService;

@Controller

public class WebController {
    public final WalletService walletService;
    public WebController(WalletService walletService){
        this.walletService = walletService;
    }

    @GetMapping("/home")
    public String index(@ModelAttribute Wallet wallet){

        return "index";
    }

    @GetMapping("/create-form")
    public String showCreateForm(){
        return "wallet-form";
    }


    @PostMapping("/create")
    public String create(@ModelAttribute Wallet wallet, Model model){
        String userResponse =  walletService.create(wallet);
        model.addAttribute("message", userResponse);
        return "index";
    }

    @GetMapping("/deposite")
    public String showDepositeForm(){
        return "deposite";
    }

    @PostMapping("/store-deposite")
    public String addDeposite(@ModelAttribute Wallet wallet, Model model){
        String depositeResponse =  walletService.store(wallet);
        model.addAttribute("depositeResponse", depositeResponse);
        return "index";
    }

   @GetMapping("/balance")
    public String showBalancePage(){
        return "balance";
   }
   @GetMapping("/checkBalance")
    public String checkBalace(@RequestParam("userId") String userId, Model model){
        double balance = walletService.check(userId);
        model.addAttribute("balance" , balance);
        return "index";
   }
}
