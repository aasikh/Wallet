//package wallet.demo.controller;
//
//import jakarta.servlet.http.HttpSession;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import wallet.demo.entity.Wallet;
//import wallet.demo.service.WalletService;
//
//@RestController
//@RequestMapping("/api/wallet")
//public class WalletController {
//    private final WalletService walletService;
//    public WalletController(WalletService walletService){
//        this.walletService = walletService;
//    }
//
////    @PostMapping("/create")
////    public String create(HttpSession session, Model model){
////        Long userId = (Long) session.getAttribute("userId");
////
////      return walletService.create(wallet);
////    }
////    @PostMapping("/deposite")
////    public String add(@RequestBody Wallet wallet){
////        if(wallet.getBalance()<0){
////           return "balance Must be greater than 0";
////         }
////       return walletService.store(wallet);
////    }
//
//    @GetMapping("/checkBalance")
//    public String checkBalace(HttpSession session, Model model){
//        Long userId = (Long) session.getAttribute("userId");
//        double balance = walletService.check(userId);
//        model.addAttribute("balance" , balance);
//        return "dashboard";
//    }
//}
//
