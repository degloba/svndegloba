package com.degloba.ecommerce.application.events.eventbus.impl.guava.subscribers.vendes;

import java.util.concurrent.CountDownLatch;

import com.degloba.ecommerce.vendes.eventsourcing.events.CompraAmbCreditEvent;
import com.degloba.ecommerce.vendes.eventsourcing.events.CompraEnEfectiuEvent;
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
    public void handleEventConcurrent(CompraAmbCreditEvent event) {
        pause(300l);
        doneSignal.countDown();
    }

    @Subscribe
    public void handleEventNonConcurrent(CompraEnEfectiuEvent event) {
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