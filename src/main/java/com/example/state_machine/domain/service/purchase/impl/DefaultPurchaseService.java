package com.example.state_machine.domain.service.purchase.impl;

import com.example.state_machine.domain.service.purchase.PurchaseService;
import com.example.state_machine.domain.statemachine.event.PurchaseEvent;
import com.example.state_machine.domain.statemachine.state.PurchaseState;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import static com.example.state_machine.domain.statemachine.event.PurchaseEvent.*;

@Service
@SuppressWarnings("deprecation")
public class DefaultPurchaseService implements PurchaseService {

    private final StateMachinePersister<PurchaseState, PurchaseEvent, String> persister;
    private final StateMachineFactory<PurchaseState, PurchaseEvent> stateMachineFactory;

    public DefaultPurchaseService(
            StateMachinePersister<PurchaseState, PurchaseEvent, String> persister,
            StateMachineFactory<PurchaseState, PurchaseEvent> stateMachineFactory
    ) {
        this.persister = persister;
        this.stateMachineFactory = stateMachineFactory;
    }

    @Override
    public boolean reserved(String userId, String productId) {
        final StateMachine<PurchaseState, PurchaseEvent> stateMachine = stateMachineFactory.getStateMachine();

        stateMachine.getExtendedState().getVariables().put("PRODUCT_ID", productId);
        stateMachine.sendEvent(RESERVE);

        try {
            persister.persist(stateMachine, userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return false;
        }

        return true;
    }

    @Override
    public boolean cancelReserve(String userId) {
        return restoreAndSendEvent(userId, RESERVE_DECLINE);
    }

    @Override
    public boolean buy(String userId) {
        return restoreAndSendEvent(userId, BUY);
    }

    private boolean restoreAndSendEvent(String userId, PurchaseEvent purchaseEvent) {
        final StateMachine<PurchaseState, PurchaseEvent> stateMachine = stateMachineFactory.getStateMachine();

        try {
            persister.restore(stateMachine, userId);
            stateMachine.sendEvent(purchaseEvent);
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return false;
        }

        return true;
    }
}
