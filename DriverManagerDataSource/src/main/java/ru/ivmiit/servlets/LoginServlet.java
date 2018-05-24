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
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;


@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    private UsersDao usersDao;

    @Override
    public void init() {
        try {
            usersDao = ConnectionDB.getUsersDao();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
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

        if(usersDao.authentication(name, password)){
            // создаем для него сессию
            HttpSession session = req.getSession();
            // кладем в атрибуты сессии атрибут user с именем пользователя
            session.setAttribute("user", name);
            // перенаправляем на страницу home
            req.getServletContext().getRequestDispatcher("/home").forward(req, resp);
        }
        else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}


