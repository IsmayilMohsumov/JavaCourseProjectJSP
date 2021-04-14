package utility;

public enum Query {
    //elave edende
    PERSON_ADD("insert into users(id,name,surname,pin,email,password,person_type) values(?,?,?,?,?,?,?)"),
    CHANGE_IMAGE_BY_ID("update users set image_url=? where id=?"),
    //sequnce miz herdefe yeni adam elave edende id ni ozu artirir
    USER_ID("select user_seq.nextval from dual"),
    //table nin icine deyer ceken
    USER_LIST_TO_TABLE("select U.ID, U.NAME,U.SURNAME,U.EMAIL,U.PIN,D.DIC_VAL from  users u  join dictionary d on U.PERSON_TYPE=D.ID order by id"),
    //login ve passwordun duzgun olub olmadigini yoxlayir
    USER_FOR_LOGIN("select U.ID, U.NAME,U.SURNAME,U.PIN,U.IMAGE_URL  from  users u where U.EMAIL =? and U.PASSWORD=? and state=1"),
    //comboBoxun icine deyerleri getiren
    COMBO_DATAS("select* from dictionary where dic_key=?"),
    //update ucun
    UPDATE_USER("update users set name =? , surname = ? , pin=?,email=?,person_type=? where id=?"),
    //KeyReleased ucun LIKE vermek lazimdir ki gedib axtarsin like in icini ise dao dadolduracagiq
    USER_BY_PIN_TABLE("select U.ID, U.NAME,U.SURNAME,U.EMAIL,U.PIN,D.DIC_VAL from  users u  join dictionary d on U.PERSON_TYPE=D.ID "),
    //silmek ucun useri id sine gore
    DELETE_USER_BY_ID("delete from users where id=?"),
    //USers cedvelinnen son idni qaytarir ( sequence kimi )
    LAST_NUM_OF_ROWNUM_FOR_ID("SELECT * FROM (SELECT rownum, id FROM users ORDER BY rownum DESC) WHERE ROWNUM = 1"),
    //Confirm key tablennan son id ni qaytarir
    LAST_NUM_OF_ROWNUM_FOR_CONFIRM_KEY("SELECT * FROM (SELECT rownum, id FROM CONFIRM_KEYS  ORDER BY rownum DESC) WHERE ROWNUM = 1"),
    //  ID sine gore rolunu qaytaracaq
    USER_ROLES("select R.ID, R.NAME from ROLE_TO_USER rtu inner join ROLES r  on r.ID=RTU.ROLE_ID inner join USERS u on U.ID = RTU.USER_ID where RTU.USER_ID=?"),
    //id ye gore useri qaytaracaq edit user ucun
    USER_VALUES_FOR_EDITUSER_BY_ID("select U.ID, U.NAME,U.SURNAME,U.EMAIL,U.PIN,D.DIC_VAL,D.ID from  users u  join dictionary d on U.PERSON_TYPE=D.ID where U.ID=?"),
    //
    ADD_CONFIRM_KEY("Insert into confirm_keys (ID,USER_ID,CONFIRM_KEY) VALUES(?,?,?)");
//    USER1_BY_PIN_LIST("select * from users where pin like'?%'");

    private String query ;
    Query(String query) {
        this.query=query;
    }

    public String getQuery() {
        return query;
    }
}
