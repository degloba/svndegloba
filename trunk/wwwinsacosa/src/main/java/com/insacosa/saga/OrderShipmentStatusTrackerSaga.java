package com.insacosa.saga;

import javax.inject.Inject;

import ddd.sagas.Saga;
import ddd.sagas.SagaAction;
import ddd.sagas.SagaInstance;
import com.insacosa.domain.Order;
import com.insacosa.domain.OrderRepository;
import com.insacosa.domain.events.OrderCreatedEvent;
import com.insacosa.domain.events.OrderSubmittedEvent;
import com.insacosa.domain.events.OrderShippedEvent;
import com.insacosa.domain.events.ShipmentDeliveredEvent;

@Saga
public class OrderShipmentStatusTrackerSaga extends SagaInstance<OrderShipmentStatusTrackerData> {

    @Inject
    private OrderRepository orderRepository;

    @SagaAction
    public void handleOrderCreated(OrderCreatedEvent event) {
        data.setOrderId(event.getOrderId());
        completeIfPossible();
    }

    @SagaAction
    public void handleOrderSubmitted(OrderSubmittedEvent event) {
        data.setOrderId(event.getOrderId());
        // do some business
        completeIfPossible();
    }

    @SagaAction
    public void orderShipped(OrderShippedEvent event) {
        data.setOrderId(event.getOrderId());
        data.setShipmentId(event.getShipmentId());
        completeIfPossible();
    }

    @SagaAction
    public void shipmentDelivered(ShipmentDeliveredEvent event) {
        data.setShipmentId(event.getShipmentId());
        data.setShipmentReceived(true);
        completeIfPossible();
    }

    private void completeIfPossible() {
        if (data.getOrderId() != null && data.getShipmentId() != null && data.getShipmentReceived()) {
            Order shippedOrder = orderRepository.load(data.getOrderId());
            shippedOrder.archive();
            orderRepository.save(shippedOrder);
            markAsCompleted();
        }
    }
}
