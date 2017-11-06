package com.xsty.scheduler.common;

import org.springframework.data.repository.CrudRepository;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by XST on 27/10/2017.
 */
public abstract class ObservableCrudRepositoryService<T,I extends Serializable> extends ObservableCrudService<T,I> {
    private CrudRepository<T,I> crudRepository;

    public ObservableCrudRepositoryService(CrudRepository<T,I> repository){
        this.crudRepository = repository;
    }

    @Override
    protected List<T> findAllAction() {
        List<T> elements = new ArrayList<>();
        crudRepository.findAll().forEach(elements::add);
        return elements;
    }

    @Override
    protected T findOneAction(I id) {
        return crudRepository.findOne(id);
    }

    @Override
    protected T saveAction(T element) {
        return crudRepository.save(element);
    }

    @Override
    protected void deleteAction(T element) {
        crudRepository.delete(element);
    }

}
