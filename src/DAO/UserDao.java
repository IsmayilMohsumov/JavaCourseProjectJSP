package DAO;

import ModelDTO.DictionaryDTO;
import ModelDTO.UserDTO;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {


    List<UserDTO> getUserLsit() throws SQLException, NamingException;

    void addUserToDB(UserDTO user) throws SQLException, NamingException;

    List<DictionaryDTO> ComboDatas(String dicKey) throws SQLException, NamingException;

    void removeUser(Integer id) throws SQLException, NamingException;

    void addUserByRegister(HttpServletRequest request, UserDTO user) throws SQLException, NamingException;

    UserDTO login(String username, String password) throws SQLException, NamingException;

    UserDTO findUserByIdForEditUser(Integer id) throws SQLException, NamingException;

    void updateUserById(UserDTO userDatas) throws SQLException, NamingException;

    void changeImage(Integer userId, String writeName) throws SQLException, NamingException;
}
