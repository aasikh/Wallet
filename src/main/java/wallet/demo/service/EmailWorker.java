package wallet.demo.service;

import org.springframework.stereotype.Service;
import wallet.demo.entity.EmailQueue;
import wallet.demo.repo.EmailQueueRepo;

import java.util.Optional;

@Service
public class EmailWorker {
    private final EmailQueueRepo emailQueueRepo ;
    private final EmailService emailService;
    public EmailWorker(EmailQueueRepo emailQueueRepo , EmailService emailService){
        this.emailQueueRepo = emailQueueRepo;
        this.emailService = emailService;
    }

    public String processOneEmail(){
        Optional<EmailQueue> em = emailQueueRepo.findFirstByStatus("PENDING");
        if(em.isEmpty()){
            return "there is no record";
        }
        EmailQueue queue  = em.get();
        String email = queue.getEmail();
        String name = queue.getName();
        boolean result = emailService.send(email,name);
        if(result==true){
            queue.setStatus("SENT");
            emailQueueRepo.save(queue);
        }
        return "ok";
    }


}
