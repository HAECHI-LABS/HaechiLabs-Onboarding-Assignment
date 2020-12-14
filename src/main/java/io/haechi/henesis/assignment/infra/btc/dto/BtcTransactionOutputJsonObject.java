package io.haechi.henesis.assignment.infra.btc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BtcTransactionOutputJsonObject {
    @JsonProperty("outputIndex")
    private Integer outputIndex;
    @JsonProperty("transactionId")
    private String transactionId;
    @JsonProperty("address")
    private String address;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("isChange")
    private boolean isChange;
}