package com.tn.rebository;

import com.tn.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findByAccountName(String accountName);
    Account findByBirthday(Date birthday);
    Account findByAddress(String address);

    @Query("From Account where accountName like %:accountNameSearch%")
    List<Account> search(String accountNameSearch);

}
