package com.xsty.scheduler.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public abstract class ObservableCrudListService<T extends Identifiable> extends ObservableCrudService<T,Long> {

    private Map<Long,T> mapElemets = new HashMap<>();
    private AtomicLong seq = new AtomicLong();


    @Override
    protected List<T> findAllAction() {
        List<T> elements = new ArrayList<>();
        mapElemets.values().forEach(elements::add);
        return elements;
    }

    @Override
    protected T findOneAction(Long id) {
        return mapElemets.get(id);
    }

    @Override
    protected T saveAction(T element) {
        element.setId(seq.incrementAndGet());
        return mapElemets.put(element.getId(), element);
    }

    @Override
    protected void deleteAction(T element) {
        mapElemets.remove(element);
    }
}
