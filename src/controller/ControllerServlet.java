package controller;

import DAO.UserDao;
import DAO.UserDaoImplement;
import ModelDTO.DictionaryDTO;
import ModelDTO.UserDTO;
import dbConfig.DbConfig;
import utility.Utility;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//request home.jsp den gelen requestimizdir icindeki getdir yoxsa postdur (actionu yeni )oyrenmek ucun : String action = request.getParameter("action");
//basqa birsey oyrenmek istese idik getParameter() moterizede ferqli birsey


public class ControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //methoda gonderirir butun deyerleri
        register(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //methoda gonderirir butun deyerleri
        register(request,response);
    }

    //herdefe methodda get ve ya post secmemek ucun bu methodu yaradiriq
    //ve get ve post methodlarinda register(request,response); gonderdik.
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "";
        String action = request.getParameter("action");
        System.out.println(action);

        //daodan method cagirmaq ucun objectini yaratdiq
        UserDao userDao = new UserDaoImplement();

        //niye action.equals yox? cunki actionun ozunun null gelmek ehtimali var ve NullPointer ata biler.
        try{
            if("userList".equalsIgnoreCase(action)){
                path="/WEB-INF/tables/users.jsp";

                    //db den deyerleri almaq ucun methodu cagiririq ve cagirdigimiz method list qaytardigi ucun onu liste menimsediirik
                    List<UserDTO> users = userDao.getUserLsit();

                    //deyerleri aldig artiq onu fronta gostermek ucun home.jsp ye gondermek lazimdir
                    request.setAttribute("userList",users);
                    System.out.println(users);
            }
            else if ("addUser".equalsIgnoreCase(action)){
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String pin = request.getParameter("pin");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String personType = request.getParameter("personType");
                System.out.println("Person Tipimiiz:::" + personType);

                UserDTO user = new UserDTO();
                user.setName(name);
                user.setSurname(surname);
                user.setEmail(email);
                user.setPin(pin);
                user.setPassword(password);
                user.setPersonLabel(personType);
                System.out.println("USERIMIZZZZZZZZZZZ: "+user);
                userDao.addUserToDB(user);

            }
            else if ("loadCombo".equalsIgnoreCase(action)){
                String dicKey = request.getParameter("dicKey");
                List<DictionaryDTO> datas = userDao.ComboDatas(dicKey);

                Map<String, List<DictionaryDTO>> comboDatas = new HashMap<>();//yeni bir map yaradiriq
                comboDatas.put(dicKey,datas);//bu o demekdir ki (personType , datas(Teacher,Student,Reception) yeni bir deyerle butun deyerleri cagiraciyiq ;
                path="/WEB-INF/combos.jsp";
                request.setAttribute("comboDatas",comboDatas);

            }
            else if ("removeUser".equalsIgnoreCase(action)){
                Integer id = Integer.valueOf(request.getParameter("id"));
                System.out.println("Controllerdki Indexsimiz "+id);
                userDao.removeUser(id);

            }
            else if ("editUser".equalsIgnoreCase(action)){
                Integer id = Integer.valueOf(request.getParameter("userId"));
                UserDTO user = userDao.findUserByIdForEditUser(id);
                path="/WEB-INF/editUser.jsp";
                request.setAttribute("user",user);
            }
            else if ("registerByAdmin".equalsIgnoreCase(action)){
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String email = request.getParameter("email");
                String pin = request.getParameter("pin");
                Integer personType = Integer.valueOf(request.getParameter("personType"));
                UserDTO user = new UserDTO(name,surname,pin,email, Utility.passwordGenerator(),personType);
                userDao.addUserByRegister(request,user);

            }
            else if ("updateUser".equalsIgnoreCase(action)){
                Integer id = Integer.valueOf(request.getParameter("id"));
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String pin = request.getParameter("pin");
                String email = request.getParameter("email");
                Integer personType = Integer.valueOf(request.getParameter("personType"));
                UserDTO userDatas = new UserDTO(id,name,surname,email,pin,personType);
                userDao.updateUserById(userDatas);

            }

        } catch (SQLException e) {
             e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        } finally {
            try {
                DbConfig.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ;
        }
        System.out.println("Pathimiz:"+path );
        RequestDispatcher dispatcher=request.getRequestDispatcher(path);
        dispatcher.forward(request,response);
    }
}
