package com.vilelo.sdjpacreditcard.listeners;

import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.springframework.stereotype.Component;

@Component
public class PreUpdateListener implements PreUpdateEventListener {

    @Override
    public boolean onPreUpdate(PreUpdateEvent preUpdateEvent) {
        System.out.println("PreUpdateListener onPreUpdate");
        return false;
    }
}
