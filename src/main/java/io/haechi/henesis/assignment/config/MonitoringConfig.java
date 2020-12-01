package io.haechi.henesis.assignment.config;

import io.haechi.henesis.assignment.domain.transaction.Action;
import io.haechi.henesis.assignment.domain.transaction.ActionSupplier;
import io.haechi.henesis.assignment.domain.transaction.Situation;
import io.haechi.henesis.assignment.domain.transaction.UpdateBalanceAction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MonitoringConfig {
    @Bean
    public ActionSupplier<Action> actionSupplier(UpdateBalanceAction updateBalanceAction) {
        Map<Situation, Action> s = new HashMap<>();
        s.put(Situation.DEPOSIT_CONFIRMED, updateBalanceAction);
        s.put(Situation.ROLLBACK, updateBalanceAction);
        return new ActionSupplier<>(s);
    }
}