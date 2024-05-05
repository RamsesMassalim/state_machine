package com.example.state_machine.domain.statemachine.action;

import com.example.state_machine.domain.statemachine.event.PurchaseEvent;
import com.example.state_machine.domain.statemachine.state.PurchaseState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class CancelAction implements Action<PurchaseState, PurchaseEvent> {

    @Override
    public void execute(StateContext<PurchaseState, PurchaseEvent> context) {
        final String productId = context.getExtendedState().get("PRODUCT_ID", String.class);

        System.out.println("Product with id " + productId + " was canceled");
    }
}
