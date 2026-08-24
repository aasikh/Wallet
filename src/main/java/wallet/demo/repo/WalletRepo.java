package wallet.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import wallet.demo.entity.Wallet;



public interface WalletRepo extends JpaRepository<Wallet, Long> {
Wallet findByUserId (String userId);
}
