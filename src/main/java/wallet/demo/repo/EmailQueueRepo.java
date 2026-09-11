package wallet.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import wallet.demo.entity.EmailQueue;

import java.util.Optional;

public interface EmailQueueRepo extends JpaRepository<EmailQueue, Long> {
  Optional<EmailQueue> findFirstByStatus(String Status);
}
