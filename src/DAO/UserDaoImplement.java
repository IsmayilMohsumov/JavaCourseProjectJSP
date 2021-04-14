package DAO;

import ModelDTO.DictionaryDTO;
import ModelDTO.RoleDTO;
import ModelDTO.UserDTO;
import dbConfig.DbConfig;
import oracle.jdbc.proxy.annotation.Pre;
import utility.Query;
import utility.SendMail;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDaoImplement  implements UserDao{

    @Override // db den deyerleri cekmek ucun (name,id,surname,email,pin)
    public List<UserDTO> getUserLsit() throws SQLException, NamingException {
        Connection connection = DbConfig.open();
        PreparedStatement pst = connection.prepareStatement(Query.USER_LIST_TO_TABLE.getQuery());

        List<UserDTO> datas = new ArrayList<>();
        ResultSet resultSet= pst.executeQuery();
        while (resultSet.next()){
            Integer id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String surname = resultSet.getString(3);
            String email = resultSet.getString(4);
            String pin = resultSet.getString(5);
            String personType = resultSet.getString("DIC_VAL");
            UserDTO personDTO = new UserDTO(id,name,surname,email,pin,personType);
            datas.add(personDTO);
        }

        return datas;
    }

    @Override//Deyerleri Database yuklemek
    public void addUserToDB(UserDTO user) throws SQLException, NamingException {
        Connection connection =DbConfig.open();
        int id = 1;
        PreparedStatement pst = connection.prepareStatement(Query.LAST_NUM_OF_ROWNUM_FOR_ID.getQuery());
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            id = rs.getInt(1);
            id++;
            System.out.println("Id miz:"+id);
        }


        if(id!=0){
            PreparedStatement ps = connection.prepareStatement(Query.PERSON_ADD.getQuery());
            ps.setInt(1,id);
            ps.setString(2,user.getName());
            ps.setString(3,user.getSurname());
            ps.setString(4,user.getPin());
            ps.setString(5,user.getEmail());
            ps.setString(6,user.getPassword());
            ps.setInt(7,Integer.parseInt(user.getPersonLabel()));
            ps.execute();
            ps.close();
        }
    }

    @Override//select* from dictionary where dic_key=? //comboboxumuzu doldurmaaq ucun
    public List<DictionaryDTO> ComboDatas(String dicKey) throws SQLException, NamingException {
        Connection connection = DbConfig.open();
        //select* from dictionary where dic_key=?
        PreparedStatement pst = connection.prepareStatement(Query.COMBO_DATAS.getQuery());
        pst.setString(1,dicKey);
        ResultSet rs = pst.executeQuery();

        List<DictionaryDTO> datas = new ArrayList<>();
        while (rs.next()){
            Integer id = rs.getInt(1);
            String dicVal = rs.getString("dic_val");
            DictionaryDTO dtoDatas = new DictionaryDTO(id,dicVal);
            datas.add(dtoDatas);

            //bu yuxarida yazdigimizi bu sekilde de yaza bilerdik :
//            datas.add(new DictionaryDTO(rs.getInt(1),rs.getString("dic_val")));
        }

        return datas;
    }

    @Override // id ye gore useri silmek
    public void removeUser(Integer id) throws SQLException, NamingException {
        Connection connection = DbConfig.open();
        PreparedStatement pst = connection.prepareStatement(Query.DELETE_USER_BY_ID.getQuery());
        pst.setInt(1,id);
        pst.execute();

    }

    @Override   // registration bolmesinnen user elave etmek
    public void addUserByRegister(HttpServletRequest request, UserDTO user) throws SQLException, NamingException {
        Connection connection =DbConfig.open();
        Integer id = 1;

        PreparedStatement pst = connection.prepareStatement(Query.LAST_NUM_OF_ROWNUM_FOR_ID.getQuery());
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            id = rs.getInt(1);
            id++;
            System.out.println("Id miz:"+id);
            pst.execute();
        }




        System.out.println("dao da id;::"+id);

        if(id!=-1) {
            PreparedStatement ps = connection.prepareStatement(Query.PERSON_ADD.getQuery());
            ps.setInt(1, id);
            ps.setString(2, user.getName());
            ps.setString(3, user.getSurname());
            ps.setString(4, user.getPin());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPassword());
            ps.setInt(7, user.getPersonType());
            ps.execute();

        }

        Integer idForConfirm=1;
        PreparedStatement pstForConfirmKey = connection.prepareStatement(Query.LAST_NUM_OF_ROWNUM_FOR_CONFIRM_KEY.getQuery());
        ResultSet rsForConfirmKey= pstForConfirmKey.executeQuery();
        if (rsForConfirmKey.next()){
            idForConfirm=rsForConfirmKey.getInt(1);
            idForConfirm++;
            pstForConfirmKey.execute();
            System.out.println(idForConfirm);
        }


            if(idForConfirm!=0) {
                String confirmKey = UUID.randomUUID().toString();
                PreparedStatement pstAddConfirmKey = connection.prepareStatement(Query.ADD_CONFIRM_KEY.getQuery());
                pstAddConfirmKey.setInt(1, idForConfirm);
                pstAddConfirmKey.setInt(2, id);
                pstAddConfirmKey.setString(3, confirmKey);
                pstAddConfirmKey.execute();

//            SendMail.sendMail(request,user.getEmail(),"Qeydiyyatin tesdiqi",user.getPassword(),confirmKey);

            }

    }

    @Override //login olmaq site a
    public UserDTO login(String username, String password) throws SQLException, NamingException  {
        Connection connection = DbConfig.open();

        //select U.ID, U.NAME,U.SURNAME,U.PIN  from  users u where U.EMAIL =? and U.PASSWORD=? and state=1
        PreparedStatement pst = connection.prepareStatement(Query.USER_FOR_LOGIN.getQuery());
        pst.setString(1,username); //yeni email
        pst.setString(2,password);
        ResultSet resultSet= pst.executeQuery();


        if (resultSet.next()){ //eger email ve password dogrudursa deyerleri ceke biler
            Integer id = resultSet.getInt(1); //id ni alir
            String name = resultSet.getString(2);   // name i alir
            String surname = resultSet.getString(3);
            String pin = resultSet.getString(4);
            String imageUrl = resultSet.getString(5);
            UserDTO personDTO = new UserDTO(id,name,surname,pin);
            personDTO.setImageUrl(imageUrl);

//            /select R.ID, R.NAME from ROLE_TO_USER rtu inner join ROLES r  on r.ID=RTU.ROLE_ID inner join USERS u on U.ID = RTU.USER_ID where RTU.USER_ID=?
            PreparedStatement preparedStatement = connection.prepareStatement(Query.USER_ROLES.getQuery());
            preparedStatement.setInt(1,id);

            ResultSet rsRoles = preparedStatement.executeQuery();
            List<RoleDTO> roles = new ArrayList<>();
            while (rsRoles.next()){
                RoleDTO roleDTO ; // objectini yaradiriq bu sekilde yaratdiq cunki constructor deyerleri isteyir
                Integer idRoles =rsRoles.getInt(1);
                String  nameRoles =rsRoles.getString(2);
                roleDTO = new RoleDTO(idRoles,nameRoles);
                roles.add(roleDTO);
            }
            personDTO.setRoles(roles);

            return personDTO;
        }


        return null;
    }

    @Override//editUser ajax innan gelen ID ile DB den useri cekeciyik .
    public UserDTO findUserByIdForEditUser(Integer id) throws SQLException, NamingException {
        Connection connection = DbConfig.open();

        //select U.ID, U.NAME,U.SURNAME,U.EMAIL,U.PIN,D.DIC_VAL,D.ID from  users u  join dictionary d on U.PERSON_TYPE=D.ID where U.ID=?
        PreparedStatement pst = connection.prepareStatement(Query.USER_VALUES_FOR_EDITUSER_BY_ID.getQuery());
        pst.setInt(1,id);
        ResultSet resultSet= pst.executeQuery();
        while (resultSet.next()){
            String name = resultSet.getString(2);
            String surname = resultSet.getString(3);
            String email = resultSet.getString(4);
            String pin = resultSet.getString(5);
            String personType = resultSet.getString("DIC_VAL");
            Integer personTypeID = resultSet.getInt(7);
            UserDTO usersDTO = new UserDTO(id,name,surname,email,pin,personType,personTypeID);
            return usersDTO;
        }



        return null;
    }

    @Override
    public void updateUserById(UserDTO userDatas) throws SQLException, NamingException {
        Connection connection = DbConfig.open();

        //update users set name =? , surname = ? , pin=?,email=?,person_type=? where id=?
        PreparedStatement pst = connection.prepareStatement(Query.UPDATE_USER.getQuery());
        pst.setString(1,userDatas.getName());
        pst.setString(2,userDatas.getSurname());
        pst.setString(3,userDatas.getPin());
        pst.setString(4,userDatas.getEmail());
        pst.setInt(5,userDatas.getPersonType());
        pst.setInt(6,userDatas.getId());
        pst.execute();
        pst.close();

    }

    @Override
    public void changeImage(Integer userId, String writeName) throws SQLException, NamingException {
        Connection connection= DbConfig.open();
        PreparedStatement pst = connection.prepareStatement(Query.CHANGE_IMAGE_BY_ID.getQuery());
        pst.setString(1,writeName);
        pst.setInt(2,userId);
        pst.execute();

    }


}
