package wallet.demo.controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import wallet.demo.entity.Users;
import wallet.demo.entity.Wallet;
import wallet.demo.service.UserService;
import wallet.demo.service.WalletService;
import wallet.demo.repo.WalletRepo;

@Controller
public class UserController {
    private final UserService userService;
    private final WalletRepo walletRepo;

    public UserController(UserService userService, WalletRepo walletRepo){
        this.userService = userService;
        this.walletRepo = walletRepo;
    }
    @GetMapping("/register")
    public String openForm(){
        return "register";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login";

    }

    @PostMapping("/registerStore")
    public String register(
            @ModelAttribute Users users,
            Model model,
            HttpSession session) {

        Users savedUser = userService.register(users);

        if (savedUser != null) {

            session.setAttribute("userId", savedUser.getId());
            session.setAttribute("name", savedUser.getName());

            return "redirect:/dashboard";
        }

        model.addAttribute("message", "User already exists, Please login");
        return "register";
    }


    @GetMapping("/dashboard")
    public String dashboard(
            HttpSession session,
            Model model) {

        Long userId = (Long) session.getAttribute("userId");
        String name = (String) session.getAttribute("name");
        Wallet wallet = walletRepo.findByUserId(userId);

        model.addAttribute("name", name);
        model.addAttribute("wallet", wallet);

        return "dashboard";
    }

    @PostMapping("/loginStore")
    public String loginStore(
            @ModelAttribute Users users,
            Model model,
            HttpSession session) {

        Users savedUser = userService.login(users);

        if (savedUser != null) {

            session.setAttribute("userId", savedUser.getId());
            session.setAttribute("name", savedUser.getName());

            return "redirect:/dashboard";
        }else {

            model.addAttribute("message", "email is not exists or password invalid");
            return "login";
        }
    }


 @GetMapping("/logout")
    public String logOut(HttpSession session){
        session.invalidate();
        return "index";
}

}
