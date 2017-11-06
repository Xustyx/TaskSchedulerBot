package com.xsty.scheduler.common;

import java.io.Serializable;
import java.util.List;

public interface CrudService<T, I extends Serializable>  {
    List<T> findAll();

    T findOne(I id);

    T save(T element);

    void delete(T element);
}
