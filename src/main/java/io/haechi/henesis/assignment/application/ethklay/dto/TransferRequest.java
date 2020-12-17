package io.haechi.henesis.assignment.application.ethklay.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.haechi.henesis.assignment.domain.ethklay.Amount;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransferRequest {

    @JsonProperty("userWalletId")
    private String userWalletId;
    @JsonProperty("to")
    private String to;
    @JsonProperty("amount")
    private Amount amount;
}
