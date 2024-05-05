package com.example.state_machine.domain.statemachine.guard;

import com.example.state_machine.domain.statemachine.event.PurchaseEvent;
import com.example.state_machine.domain.statemachine.state.PurchaseState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

public class HideGuard implements Guard<PurchaseState, PurchaseEvent> {

    @Override
    public boolean evaluate(StateContext<PurchaseState, PurchaseEvent> context) {
        PurchaseState sourcesState = context.getSource().getId();
        PurchaseState targetState = context.getTarget().getId();
        PurchaseEvent event = context.getEvent();

        return sourcesState == PurchaseState.RESERVED
                && targetState == PurchaseState.PURCHASE_COMPLETE
                && event == PurchaseEvent.BUY;
    }
}
