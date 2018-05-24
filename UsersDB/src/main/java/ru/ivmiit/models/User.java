package ru.ivmiit.models;



/**
 * 14.03.2018
 * User
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class User {
    private Integer id;
    private String name;
    private String password;


    public User(String name, String password, Integer id) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User(Integer id, String name, String password) {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

    
}
