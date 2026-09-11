package wallet.demo.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EmailScheduler {
    private  final EmailWorker emailWorker;

    public EmailScheduler(EmailWorker emailWorker){
        this.emailWorker = emailWorker;
    }

    @Scheduled(fixedDelay = 5000)
    public void runEmailWorker(){
        emailWorker.processOneEmail();
    }
}
