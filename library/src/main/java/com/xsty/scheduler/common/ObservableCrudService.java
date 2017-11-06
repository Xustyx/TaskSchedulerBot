package com.xsty.scheduler.common;

import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public abstract class ObservableCrudService<T,I extends Serializable> extends Observable implements CrudService<T,I> {

    @Override
    public List<T> findAll() {
        List<T> elements = findAllAction();
        return elements;
    }

    protected abstract List<T> findAllAction();

    @Override
    public T findOne(I id) {
        Assert.notNull(id,"Id must not be null");
        return findOneAction(id);
    }

    protected abstract T findOneAction(I id);

    @Override
    public T save(T element) {
        Assert.notNull(element,"Element must not be null");
        T _element = saveAction(element);
        this.setChanged();
        notifyObservers(element);
        return _element;
    }

    protected abstract T saveAction(T element);

    @Override
    public void delete(T element) {
        Assert.notNull(element,"Element must not be null");
        notifyObservers(element);
        deleteAction(element);
    }

    protected abstract void deleteAction(T element);
}
