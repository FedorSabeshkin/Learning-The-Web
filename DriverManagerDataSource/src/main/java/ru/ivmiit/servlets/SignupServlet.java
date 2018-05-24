package ru.ivmiit.servlets;


import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ivmiit.connection.ConnectionDB;
import ru.ivmiit.dao.UsersDao;
import ru.ivmiit.dao.UsersDaoJdbcTemplateImpl;
import ru.ivmiit.models.User;
import ru.ivmiit.security.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Properties;



@WebServlet("/signUp")
public class SignupServlet extends HttpServlet{
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


        List<User> users;
        users = usersDao.findAll();
        req.setAttribute("usersFromServer", users);
        try {
            req.getServletContext().getRequestDispatcher("/jsp/signUp.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        List<User> users;
        users = usersDao.findLogin(name);
        System.out.println(users);
        if ((users.isEmpty())) {
            password = BCrypt.hashpw(password,BCrypt.gensalt());
            User addingUser = new User(name, password);
            usersDao.save(addingUser);
            resp.sendRedirect(req.getContextPath() + "/signUp");
        }
        else {
            req.getServletContext().getRequestDispatcher("/jsp/loginUnLucky.jsp").forward(req, resp);
        }
    }
}
