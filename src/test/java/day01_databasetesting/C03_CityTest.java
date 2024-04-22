package day01_databasetesting;

import org.junit.Assert;
import org.junit.Test;

import java.sql.*;

public class C03_CityTest {

   /*
connect to the database
get city names from the 'cities' table
verify that there are at least 10 city names in the city_name column
close the connection
    */

    @Test
    public void test01() throws SQLException {
        // Database e bağlan
        String url="jdbc:postgresql://localhost:5432/myDatabase";
        String user="tester";
        String password = "tester";
        Connection con=DriverManager.getConnection(url,user,password);
        Statement st=con.createStatement();

        // cities tablosundan sahir isimlerini al
        String sql="select city_name from cities";
        ResultSet rs=st.executeQuery(sql);

        // city_name  sutunda en az 10 kayıt oldugunu dogrula

        int counter=0;
        while (rs.next()){
            counter++;
        }

        Assert.assertTrue(counter>9);

        // baglantiyi kapat
        st.close();
        con.close();
    }

    @Test
    public void secondWay() throws SQLException {
        // Database e bağlan
        String url="jdbc:postgresql://localhost:5432/myDatabase";
        String user="tester";
        String password = "tester";
        Connection con=DriverManager.getConnection(url,user,password);
        Statement st=con.createStatement();

        // cities tablosundan sahir isimlerinin sayisini al

        ResultSet rs=st.executeQuery("select count(*) from cities");
        rs.next();
        int actualResult= rs.getInt("count");
        Assert.assertTrue( actualResult>9);

        // baglantiyi kapat
        st.close();
        con.close();

    }
}
