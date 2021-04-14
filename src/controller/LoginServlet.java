package controller;

import DAO.UserDao;
import DAO.UserDaoImplement;
import ModelDTO.UserDTO;
import dbConfig.DbConfig;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException        {
        String username = request.getParameter("username");
        String password = request.getParameter("password"); // deyerler cekdik

        String path = ""; // success halinda home.jsp ye gondereciyik.  // error halinda ise login.jsp de <p> ye gondereciyik

        UserDao userDao = new UserDaoImplement();

        try {
            UserDTO user  = userDao.login(username, password); //username ve passwordu gonderdik eger bu deyerlerle ust uste dusen deyer varsa geriye dolu user objecti qayidacaq

            if (user != null) { //eger objectin ici doludursa girecek bura eks halda else halina
                path = "home.jsp";
                HttpSession session = request.getSession(true);
                session.setAttribute("loginUser",user);
            } else {
                request.setAttribute("errorMessage", "Login denied");
            }

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
            requestDispatcher.forward(request, response);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }finally {
            try {
                DbConfig.close();
            } catch (SQLException e) {

            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if("logout".equalsIgnoreCase(req.getParameter("action"))){
//            HttpSession session = req.getSession(true);
//            /session.invalidate();
            req.getSession(true).invalidate();
            req.getRequestDispatcher("login.jsp").forward(req,resp);
            return;
            }
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }
}
