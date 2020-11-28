package io.haechi.henesis.assignment.application;

import io.haechi.henesis.assignment.application.dto.*;
import io.haechi.henesis.assignment.domain.*;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeApplicationService {

    private final WalletService walletService;
    private final UserWalletRepository userWalletRepository;
    private final FlushedTxRepository flushedTxRepository;

    public ExchangeApplicationService(WalletService walletService,
                                      UserWalletRepository userWalletRepository,
                                      FlushedTxRepository flushedTxRepository) {
        this.walletService = walletService;
        this.userWalletRepository = userWalletRepository;
        this.flushedTxRepository = flushedTxRepository;
    }


    @Transactional
    public CreateWalletResponseDTO createUserWallet(CreateWalletRequestDTO request) {

        UserWallet userWallet = walletService.createUserWallet(request.getWalletName());
        userWalletRepository.save(userWallet);

        return CreateWalletResponseDTO.builder()
                .name(userWallet.getWalletName())
                .createdAt(userWallet.getCreatedAt())
                .blockchain(userWallet.getBlockchain())
                .address(userWallet.getWalletAddress())
                .id(userWallet.getWalletId())
                .build();
    }

    @Transactional
    public TransferResponseDTO transfer(TransferRequestDTO request) {

        Optional<UserWallet> userWallet = userWalletRepository.findByWalletId(request.getUserWalletId());
        if (userWallet.isEmpty()) {
            throw new IllegalStateException(String.format("Can't find Wallet(ID : %s)",request.getUserWalletId()));
        }


        Amount spendableAmount = walletService.getMasterWalletBalance(request.getTicker());
        Amount walletBalance = userWallet.get().getWalletBalance();
        Amount amount = request.getAmount();

        System.out.println("amount : "+amount);
        System.out.println("spendableAmount : "+spendableAmount);
        System.out.println(userWallet);
        System.out.println("userWalletBalance : "+walletBalance);


        if (spendableAmount.compareTo(walletBalance) < 0
                && spendableAmount.compareTo(request.getAmount()) < 0) {
            throw new IllegalStateException("Not Enough Money..!");
        }

        walletService.transfer(
                request.getAmount(),
                request.getTo(),
                request.getTicker()
        );

        walletBalance.subtract(amount);
        userWalletRepository.save(userWallet.get());

        return TransferResponseDTO.builder()
                .walletName(userWallet.get().getWalletName())
                .amount(amount)
                .walletBalance(walletBalance)
                .build();
    }

    @Transactional
    public FlushResponseDTO flush(FlushRequestDTO request) {

        List<String> userWalletIds = walletService.getUserWalletIds();
        System.out.println("Get All User Wallet Id : " + userWalletIds);

        Transaction transaction = walletService.flushAll(
                request.getTicker(),
                userWalletIds
        );

        flushedTxRepository.save(new FlushedTx(transaction));

        return FlushResponseDTO.builder()
                .txId(transaction.getTxId())
                .blockchain(transaction.getBlockchain())
                .build();
    }

}
