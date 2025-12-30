package com.vilelo.sdjpacreditcard.listeners;

import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.springframework.stereotype.Component;

@Component
public class PreInsertListener implements PreInsertEventListener {

    @Override
    public boolean onPreInsert(PreInsertEvent preInsertEvent) {
        System.out.println("PreInsertListener onPreInsert");
        return false;
    }

}
