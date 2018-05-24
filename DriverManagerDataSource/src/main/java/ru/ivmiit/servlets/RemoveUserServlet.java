package ru.ivmiit.servlets;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ivmiit.connection.ConnectionDB;
import ru.ivmiit.dao.UsersDao;
import ru.ivmiit.dao.UsersDaoJdbcTemplateImpl;
import ru.ivmiit.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

@WebServlet("/home/remove")
public class RemoveUserServlet extends HttpServlet {
    private UsersDao usersDao;

    @Override
    public void init() throws ServletException {

        try {
            usersDao = ConnectionDB.getUsersDao();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            req.getServletContext().getRequestDispatcher("/jsp/remove.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("МЕТОД POST");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        User removeUser = new User(name,password);
        System.out.println("Имя: " + removeUser.getName()+"   " + "Пароль: " + removeUser.getPassword());
        int i;
        List<User> users;
        users = usersDao.findAll();

        for (i = users.size(); i>0; i--){
            System.out.println(users.get(i-1));
        }
        usersDao.delete(removeUser);
        req.getServletContext().getRequestDispatcher("/jsp/removeTrue.jsp").forward(req, resp);
    }
}