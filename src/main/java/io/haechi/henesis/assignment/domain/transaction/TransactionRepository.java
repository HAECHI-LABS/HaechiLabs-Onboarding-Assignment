package io.haechi.henesis.assignment.domain.transaction;


import io.haechi.henesis.assignment.domain.UserWallet;
import io.haechi.henesis.assignment.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String>, JpaSpecificationExecutor<UserWallet> {

    /*
    @Transactional
    @Query("SELECT u from UserWallet u WHERE u.walletId = :walletId")
    Optional<UserWallet> getUserWalletsByWalletId(@Param("walletId")final String walletId);
    @Transactional
    UserWallet findUserWalletByWalletIdIsNotNull(final String walletId);
    */

    //Optional<Transaction> findByTransactionId(String txId);

    Optional<Transaction> findByTransactionIdAndStatusAndTransferType(String transactionId, String status, String transferType);

    boolean existsTransactionByDetailId(int detailId);

    boolean existsTransactionByDetailIdAndStatusAndTransferType(int detailId, String status, String transferType);

    Transaction findTopByOrderByUpdatedAtDesc();
}