package com.degloba.ecommerce.sales.application.events.guava.eventbus.subscriber;

import java.util.concurrent.CountDownLatch;

import com.degloba.ecommerce.sales.application.events.guava.eventbus.events.CashPurchaseEvent;
import com.degloba.ecommerce.sales.application.events.guava.eventbus.events.CreditPurchaseEvent;
import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;

public class LongProcessSubscriber {

    private CountDownLatch doneSignal;

    private LongProcessSubscriber(CountDownLatch doneSignal) {
        this.doneSignal = doneSignal;
    }

    @Subscribe
    @AllowConcurrentEvents
    public void handleEventConcurrent(CreditPurchaseEvent event) {
        pause(300l);
        doneSignal.countDown();
    }

    @Subscribe
    public void handleEventNonConcurrent(CashPurchaseEvent event) {
        pause(300l);
        doneSignal.countDown();
    }

    private void pause(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            //Ignore
        }
    }

    public static LongProcessSubscriber instance(AsyncEventBus asyncEventBus, CountDownLatch countDownLatch) {
        LongProcessSubscriber subscriber = new LongProcessSubscriber(countDownLatch);
        asyncEventBus.register(subscriber);
        return subscriber;
    }
}