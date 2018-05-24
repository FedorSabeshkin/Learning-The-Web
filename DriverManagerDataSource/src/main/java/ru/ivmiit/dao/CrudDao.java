package ru.ivmiit.dao;

import java.util.List;


/**
 * 04.04.2018
 * CrudDao
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface CrudDao<T> {

    void save(T model);
    void update(T model);
    void delete(T model);

    List<T> findAll();
}
