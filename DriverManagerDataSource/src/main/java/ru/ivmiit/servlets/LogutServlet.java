package ru.ivmiit.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/home/logout")
public class LogutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        HttpSession session = req.getSession(false);

        // удаляем атрибут сессии с имене user, в которой кладется инфа,
        // если пользователь авторизовался
        session.removeAttribute("user") ;
        try {
            req.getServletContext().getRequestDispatcher("/login").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
