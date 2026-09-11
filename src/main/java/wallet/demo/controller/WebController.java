package wallet.demo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

//        @GetMapping("/create-form")
//    public String showCreateForm(){
//        return "wallet-form";
//    }


    @GetMapping("/deposite")
    public String showDepositeForm(){
        return "deposite";
    }

    @PostMapping("/store-deposite")
    public String addDeposite(@ModelAttribute Wallet wallet, HttpSession session, RedirectAttributes redirectAttributes){
        Long userId = (Long) session.getAttribute("userId");
        wallet.setUserId(userId);
        String depositeResponse =  walletService.store(wallet);
         redirectAttributes.addFlashAttribute("depositeResponse", depositeResponse);
        return "redirect:/dashboard";
    }

   @GetMapping("/checkBalance")
    public String checkBalace(HttpSession session, RedirectAttributes redirectAttributes){
        Long userId = (Long) session.getAttribute("userId");
        if(userId==null){
            return "redirect:/login";
        }
        double balance = walletService.check(userId);
         redirectAttributes.addFlashAttribute("balance", balance);
         return "redirect:/dashboard";
   }

    @PostMapping("/create")
    public String create(HttpSession session, RedirectAttributes redirectAttributes){
        Long userId = (Long) session.getAttribute("userId");
        String userResponse =  walletService.create(userId);
         redirectAttributes.addFlashAttribute("walletCreated", userResponse);
        return "redirect:/dashboard";
    }
}
