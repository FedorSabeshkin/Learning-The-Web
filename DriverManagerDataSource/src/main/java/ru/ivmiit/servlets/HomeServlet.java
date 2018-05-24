package ru.ivmiit.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    // в случае GET-запроса следует просто отдать страницу home
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
    }

    // обработка запроса, который должен поменять цвет заголовка
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {

        HttpSession session = req.getSession(false);

        // получаем параметр запроса
        String color = req.getParameter("color");
        // создаем Cookie с данным значением
        Cookie colorCookie = new Cookie("color", color);
        // кладем в ответ
        resp.addCookie(colorCookie);
        // перенаправляем пользователя обратно на страницу home
        try {
            resp.sendRedirect(req.getContextPath() + "/home");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
