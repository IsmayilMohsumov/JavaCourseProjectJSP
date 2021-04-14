package dbConfig;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbConfig {

    public static Connection connection;

    public static Connection open() throws SQLException, NamingException {
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup("java:/comp/env/course");
        //return hissesinde bu yuxaridaki connectiona gonderir
        return connection= dataSource.getConnection();

    }

    public static void close() throws SQLException {
        if(connection!=null) connection.close();
    }

}
