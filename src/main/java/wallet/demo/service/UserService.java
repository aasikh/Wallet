package wallet.demo.service;

import org.springframework.stereotype.Service;
import wallet.demo.entity.EmailQueue;
import wallet.demo.entity.Users;
import wallet.demo.repo.EmailQueueRepo;
import wallet.demo.repo.UserRepo;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final EmailService emailService;
   private final EmailQueueRepo emailQueueRepo;
    private UserService(UserRepo userRepo, EmailService emailService
                     , EmailQueueRepo emailQueueRepo)
    {
        this.userRepo = userRepo;
        this.emailService = emailService;
        this.emailQueueRepo = emailQueueRepo;
    }

    public Users register(Users users){
       Users userExists = userRepo.findByEmail(users.getEmail());
       if(userExists!=null){
           return null;
       }
       Users savedUser = userRepo.save(users);
       if(savedUser!=null){
           EmailQueue queue = new EmailQueue();
           queue.setName(savedUser.getName());
           queue.setEmail(savedUser.getEmail());
           queue.setStatus("PENDING");
           emailQueueRepo.save(queue);
       }
        return savedUser;
    }


    public Users login(Users users){
        Users userExists = userRepo.findByEmail(users.getEmail());
        if(!userExists.getPassword().equals(users.getPassword()) || userExists==null){
            return null;
        }
        return userExists;
    }
}
