package io.haechi.henesis.assignment.infra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetWalletBalanceDto {

    @JsonProperty("coinType")
    private String coinType;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("spendableAmount")
    private String spendableAmount;
    @JsonProperty("name")
    private String name;
    @JsonProperty("symbol")
    private String symbol;
}