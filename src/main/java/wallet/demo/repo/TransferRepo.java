package wallet.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import wallet.demo.entity.Transfer;

public interface TransferRepo extends JpaRepository<Transfer, Long> {

}
