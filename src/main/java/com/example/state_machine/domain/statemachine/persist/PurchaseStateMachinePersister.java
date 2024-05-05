package com.example.state_machine.domain.statemachine.persist;

import com.example.state_machine.domain.statemachine.event.PurchaseEvent;
import com.example.state_machine.domain.statemachine.state.PurchaseState;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;

import java.util.HashMap;

public class PurchaseStateMachinePersister implements StateMachinePersist<PurchaseState, PurchaseEvent, String> {

    private final HashMap<String, StateMachineContext<PurchaseState, PurchaseEvent>> contexts = new HashMap<>();

    @Override
    public void write(StateMachineContext<PurchaseState, PurchaseEvent> context, String contextObj) {
        contexts.put(contextObj, context);
    }

    @Override
    public StateMachineContext<PurchaseState, PurchaseEvent> read(String contextObj) {
        return contexts.get(contextObj);
    }
}
