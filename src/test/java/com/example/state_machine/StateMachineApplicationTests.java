package com.example.state_machine;

import com.example.state_machine.domain.statemachine.event.PurchaseEvent;
import com.example.state_machine.domain.statemachine.state.PurchaseState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.test.StateMachineTestPlan;
import org.springframework.statemachine.test.StateMachineTestPlanBuilder;

import static com.example.state_machine.domain.statemachine.event.PurchaseEvent.*;
import static com.example.state_machine.domain.statemachine.state.PurchaseState.*;

@SpringBootTest
class StateMachineApplicationTests {

    @Autowired
    private StateMachineFactory<PurchaseState, PurchaseEvent> factory;

    @Test
    public void testWhenReservedCancel() throws Exception {
        StateMachine<PurchaseState, PurchaseEvent> machine = factory.getStateMachine();
        StateMachineTestPlan<PurchaseState, PurchaseEvent> plan =
                StateMachineTestPlanBuilder.<PurchaseState, PurchaseEvent>builder()
                        .defaultAwaitTime(2)
                        .stateMachine(machine)
                        .step()
                        .expectStates(NEW)
                        .expectStateChanged(0)
                        .and()
                        .step()
                        .sendEvent(RESERVE)
                        .expectState(RESERVED)
                        .expectStateChanged(1)
                        .and()
                        .step()
                        .sendEvent(RESERVE_DECLINE)
                        .expectState(CANCEL_RESERVED)
                        .expectStateChanged(1)
                        .and()
                        .build();

        plan.test();
    }

    @Test
    public void testWhenPurchaseComplete() throws Exception {
        StateMachine<PurchaseState, PurchaseEvent> machine = factory.getStateMachine();
        StateMachineTestPlan<PurchaseState, PurchaseEvent> plan =
                StateMachineTestPlanBuilder.<PurchaseState, PurchaseEvent>builder()
                        .defaultAwaitTime(2)
                        .stateMachine(machine)
                        .step()
                        .expectStates(NEW)
                        .expectStateChanged(0)
                        .and()
                        .step()
                        .sendEvent(RESERVE)
                        .expectState(RESERVED)
                        .expectStateChanged(1)
                        .and()
                        .step()
                        .sendEvent(BUY)
                        .expectState(PURCHASE_COMPLETE)
                        .expectStateChanged(1)
                        .and()
                        .build();

        plan.test();
    }

}
