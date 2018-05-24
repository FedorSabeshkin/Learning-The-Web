package ru.ivmiit.dao;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ru.ivmiit.models.User;
import ru.ivmiit.security.BCrypt;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.*;

/**
 * 04.04.2018
 * UsersDaoJdbcTemplateImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersDaoJdbcTemplateImpl implements UsersDao, InitializingBean{
    private JdbcTemplate template;

    private Map<Integer, User> usersMap = new HashMap<>();

    //language=SQL
    private final String SQL_DELATE_USER =
            "DELETE FROM site_user WHERE name = ?";

    //language=SQL
    private final String SQL_UPDATE_PASSWORD =
            "UPDATE site_user SET password = ? WHERE name = ?";

    private final String SQL_SELECT_ALL =
            "SELECT * FROM site_user;";

    private final String SQL_SELECT_ALL_BY_NAME =
            "SELECT * FROM site_user WHERE name = ?";

    //language=SQL
    private final String SQL_ADD_USER = "INSERT INTO site_user (name, password) VALUES (?, ?);";

    public UsersDaoJdbcTemplateImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

//    private RowMapper<User> oneUserRowMapper
//            = (ResultSet resultSet, int i) -> {
//        String password = resultSet.getString("password");
//        String name = resultSet.getString("name");
//        if (!usersMap.containsValue(name)) {
//            Integer id = resultSet.getInt("id");
//            User user = new User(id, name, password);
//            usersMap.put(id, user);
//        }
//        return usersMap.get(name);
//    };


    RowMapper<User> userRowMapper
            = (ResultSet resultSet, int i) -> {
        Integer id = resultSet.getInt("id");
        if (!usersMap.containsKey(id))  {
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            User user = new User(id, name, password);
            usersMap.put(id, user);
        }
       return usersMap.get(id);
    };

    private RowMapper<User> findByNameRowMapper
            = (ResultSet resultSet, int i) -> {
        String name = resultSet.getString("name");
        String password = resultSet.getString("password");
        User user = new User();
        if ((name != null)){
            user = new User(name, password);
            System.out.println(user);
        }
        return user;
    };

//    private RowMapper<User> findLoginRowMapper
//            = (ResultSet resultSet, int i) -> {
//        String name = resultSet.getString("name");
//        String password = resultSet.getString("password");
//        List<User> oneUser = new ArrayList<>();
//        System.out.println("Новый лист " + oneUser);
//        User user = new User();
//        if ((name != null)){
//            user = new User(name, password);
//            System.out.println(user);
//        }
//        return user;
//    };

    @Override
    public User findOneByName(String name) {
        return template.queryForObject(SQL_SELECT_ALL_BY_NAME,findByNameRowMapper,name);
    }

    @Override
    public List<User> findLogin (String newName){
        return template.query(SQL_SELECT_ALL_BY_NAME, findByNameRowMapper, newName);
    }

    @Override
    public void save(User model) {
        template.update(SQL_ADD_USER, model.getName(), model.getPassword());
    }

    @Override
    public void update(User model) {

        User user = findOneByName(model.getName());
        String oldHashpw = user.getPassword();
        String newPassword = model.getPassword();
        if (!(BCrypt.checkpw(newPassword, oldHashpw))){
            newPassword = BCrypt.hashpw(newPassword,BCrypt.gensalt());
            template.update(SQL_UPDATE_PASSWORD, newPassword, model.getName());
        }
    }

    @Override
    public void delete(User model) {
        Boolean check = authentication(model.getName(), model.getPassword());
        System.out.println(check);
        System.out.println("Пароль удаляемого: " + model.getPassword()+" "+"Логин: "+model.getName());
        if (check) {
            template.update(SQL_DELATE_USER, model.getName());
        }
    }

    @Override
    public List<User> findAll() {
        usersMap.clear();
        return template.query(SQL_SELECT_ALL, userRowMapper);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if(template == null){
            throw new Exception("Проверить подключение к БД");
        }
    }

    @Override
    public Boolean authentication(String name, String password){
        usersMap.clear();
        List<User> authenticationList;
        authenticationList = template.query(SQL_SELECT_ALL_BY_NAME, userRowMapper,  new Object[]{name});
        Boolean check = false;
        if (!(authenticationList.isEmpty())){
            User checkUser = authenticationList.get(0);
           if (checkUser.getPassword() != null){
                String hashed = checkUser.getPassword();
                check = BCrypt.checkpw(password, hashed);
           }
        }
        return check;
    }
}
