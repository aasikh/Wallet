package wallet.demo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wallet.demo.entity.Transfer;
import wallet.demo.service.TransferService;

@Controller
public class TransferController {
private final TransferService transferService;
 public TransferController(TransferService transferService){
     this.transferService = transferService;
 }

    @GetMapping("transferForm")
    public String openForm(){
        return "transfer";
    }

    @PostMapping("transferAmount")
     public String transferAmount(@ModelAttribute Transfer transfer,
                            HttpSession session,
                            RedirectAttributes redirectAttributes){
           Long senderId = (Long) session.getAttribute("userId");
           transfer.setSenderId(senderId);
           String transferResponse =  transferService.transferAmount(transfer);
           redirectAttributes.addFlashAttribute("transferMessage" , transferResponse);
           return "redirect:/dashboard";
        }

}
