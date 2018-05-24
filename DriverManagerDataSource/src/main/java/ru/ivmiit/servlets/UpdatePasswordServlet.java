package ru.ivmiit.servlets;

import ru.ivmiit.connection.ConnectionDB;
import ru.ivmiit.dao.UsersDao;
import ru.ivmiit.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home/updatePass")
public class UpdatePasswordServlet extends HttpServlet {

    private UsersDao usersDao;

    @Override
    public void init(){
        try {
            usersDao = ConnectionDB.getUsersDao();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        try {
            req.getServletContext().getRequestDispatcher("/jsp/updatePass.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        User userUpdatePassword = new User(name, password);
        usersDao.update(userUpdatePassword);
        try {
            req.getServletContext().getRequestDispatcher("/jsp/updatePassLucky.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
