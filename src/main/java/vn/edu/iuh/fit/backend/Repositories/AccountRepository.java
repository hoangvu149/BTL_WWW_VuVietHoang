package vn.edu.iuh.fit.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.backend.Models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}