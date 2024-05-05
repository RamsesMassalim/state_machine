package com.example.state_machine.domain.statemachine.action;

import com.example.state_machine.domain.statemachine.event.PurchaseEvent;
import com.example.state_machine.domain.statemachine.state.PurchaseState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class ErrorAction implements Action<PurchaseState, PurchaseEvent> {

    @Override
    public void execute(StateContext<PurchaseState, PurchaseEvent> context) {
        System.out.println("Error was found while state transition to " + context.getTarget().getId());
    }
}
