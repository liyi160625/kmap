package org.andy.kmap.model.dao;

import java.sql.*;
import javax.sql.DataSource;

import org.andy.kmap.model.entity.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("UserDaoImpl")
public class UserDaoImpl implements UserDao {

    @Autowired
    @Qualifier("dataSource")
    private BasicDataSource dataSource;

    /**
     * This method adds a user.
     * @param user
     */
    public void addUser(User user) {

        Connection connection = null;
        PreparedStatement statement = null;
        SQLException exception = null;

        try {
            connection = this.dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO user(name, email, passwd) VALUES(?, ?, ?)");

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());

            statement.executeUpdate();
        } catch (SQLException ex) {
            exception = ex;
        } finally {
            ConnectionCloser.close(connection, statement, exception);
        }
    }


    /**
     * This method gets a user by email.
     * @param email
     * @return
     */
    public User getUser(String email) {

        Connection connection = null;
        PreparedStatement statement = null;
        SQLException exception = null;

        User user = null;

        try {
            connection = this.dataSource.getConnection();
            statement = connection.prepareStatement("SELECT id, name, email, passwd FROM user WHERE user.email = ?");

            statement.setString(1, email);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                user = new User(result.getString(2), result.getString(3), result.getString(4));
                user.setId(result.getInt(1));
            }
        } catch (SQLException ex) {
            exception = ex;
        } finally {
            ConnectionCloser.close(connection, statement, exception);
        }

        return user;
    }


    public String getUserRole(String email){

        Connection connection = null;
        PreparedStatement statement = null;
        SQLException exception = null;

        String role=null;
        try {
            connection = this.dataSource.getConnection();
            statement = connection.prepareStatement("SELECT role FROM roles WHERE userid = ?");

            statement.setString(1, email);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                role=result.getString(1);
            }
        } catch (SQLException ex) {
            exception = ex;
        } finally {
            ConnectionCloser.close(connection, statement, exception);
        }


        return role;

    }

}