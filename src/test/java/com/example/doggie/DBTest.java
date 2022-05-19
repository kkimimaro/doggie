package com.example.doggie;

import com.example.doggie.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.Driver;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DBTest {

    User dbUser = new User("naruto", "naruto");
    User dbUserNeg = new User("kimimasdq2aro", "kaasdq12eguya");
    User pro = new User(10L, "kimimaro1", "kaguya1", true, "qweqwe@mail.ru", "12dwiahskdh6o2ihajshd8h2kasd", "кимимаро", "кагуя");

    Long id = pro.getId();
    String log = pro.getUsername();
    String pas = pro.getPassword();
    boolean active = pro.isActive();
    String email = pro.getEmail();
    String activationCode = pro.getActivationCode();
    String name = pro.getName();
    String surname = pro.getSurname();

    
    String login = dbUser.getUsername();
    String password = dbUser.getPassword();

    String login1 = dbUserNeg.getUsername();
    String password1 = dbUserNeg.getPassword();

    Connection conn;

    @BeforeEach
    public void testInfoBefore() throws SQLException {
        String url = "jdbc:postgresql://localhost/dgdb";

        DriverManager.registerDriver(new Driver());
        conn = DriverManager.getConnection(url,"postgres", "Lushlife2018");

        System.out.println("Test started");
    }

    @AfterEach
    public void testInfoAfter() {
        System.out.println("Test completed");
    }

    @Test
    void UserExistsPos() throws SQLException {
        try {
            boolean isUserExists = false;
            try (PreparedStatement ps = conn.prepareStatement("select 1 from usr where username = ? and password = ?")) {
                ps.setString(1, login);
                ps.setString(2, password);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        isUserExists = true;
                    }
                }

                assertTrue(isUserExists);

            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Test
    void UserExistsNeg() throws SQLException {
        try {

            boolean isUserExists = false;
            try (PreparedStatement ps = conn.prepareStatement("select 1 from usr where username = ? and password = ?")) {
                ps.setString(1, login1);
                ps.setString(2, password1);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        isUserExists = true;
                    }
                }

                assertFalse(isUserExists);

            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Test
    void InsertDB() throws SQLException {
        boolean isUserExists = false;

        try {
            Statement stat = (Statement) conn.createStatement();
            String insert = "insert into usr (id, active, password, username, email, activation_code, name, surname) " +
                    "values (" + id + "," + active + "," + "'" + pas + "', '" + log + "', '" + email + "', '" + activationCode + "', '" + name + "', '" + surname + "')";

            stat.executeUpdate(insert);

            PreparedStatement ps = conn.prepareStatement("select 1 from usr where username = ? and password = ?");
            ps.setString(1, log);
            ps.setString(2, pas);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    isUserExists = true;
                }

                assertTrue(isUserExists);

            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
