package ru.ivmiit.dao;

import ru.ivmiit.models.User;

import java.util.List;

/**
 * 04.04.2018
 * UsersDao
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface UsersDao extends CrudDao<User> {

    User findOneByName(String name);
    List<User> findLogin (String newName);
    Boolean authentication(String name, String password);
}
