package wallet.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import wallet.demo.entity.Wallet;

import javax.swing.text.html.parser.Entity;

public interface WalletRepo extends JpaRepository<Wallet, Long> {
Wallet findByUserId (String userId);
}
