package vn.edu.iuh.fit.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.Models.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}