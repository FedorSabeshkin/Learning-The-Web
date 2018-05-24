package ru.ivmiit.servlets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/logout")
public class LogutServlet  extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        // удаляем атрибут сессии с имене user, в которой кладется инфа,
        // если пользователь авторизовался
        session.removeAttribute("user") ;
        req.getServletContext().getRequestDispatcher("/login").forward(req, resp);
    }
}
