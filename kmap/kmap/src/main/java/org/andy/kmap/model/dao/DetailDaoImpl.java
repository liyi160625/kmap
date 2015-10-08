package org.andy.kmap.model.dao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import javax.sql.DataSource;

import org.andy.kmap.model.entity.*;

public class DetailDaoImpl implements DetailDao {

    private DataSource dataSource;


    public DetailDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    /**
     * This method gets a list of details belonging to a certain course.
     * @param course
     * @return
     */
    public List<Detail> getDetails(Course course) {

        Connection connection = null;
        PreparedStatement statement = null;
        SQLException exception = null;

        List<Detail> details = new ArrayList<>();

        try {
            connection = this.dataSource.getConnection();
            statement = connection.prepareStatement("SELECT detail.id, detail.name FROM detail WHERE detail.course = ?");

            statement.setInt(1, course.getId());

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Detail detail = new Detail(result.getInt(1), result.getString(2));
                details.add(detail);
            }
        } catch (SQLException ex) {
            exception = ex;
        } finally {
            ConnectionCloser.close(connection, statement, exception);
        }


        return details;
    }


    /**
     * This method gets a list of successors of a certain detail.
     * @param tail
     * @return
     */
    public List<Detail> getHeads(Detail tail) {

        Connection connection = null;
        PreparedStatement statement = null;
        SQLException exception = null;

        List<Detail> heads = new ArrayList<>();

        try {
            connection = this.dataSource.getConnection();
            statement = connection.prepareStatement("SELECT detail.id, detail.name FROM detail WHERE detail.id IN (SELECT dd.head FROM dd WHERE dd.tail = ?)");

            statement.setInt(1, tail.getId());

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Detail head = new Detail(result.getInt(1), result.getString(2));
                heads.add(head);
            }
        } catch (SQLException ex) {
            exception = ex;
        } finally {
            ConnectionCloser.close(connection, statement, exception);
        }

        return heads;
    }


    /**
     * This method gets a list of predecessors of a certain detail.
     * @param head
     * @return
     */
    public List<Detail> getTails(Detail head) {

        Connection connection = null;
        PreparedStatement statement = null;
        SQLException exception = null;

        List<Detail> tails = new ArrayList<>();

        try {
            connection = this.dataSource.getConnection();
            statement = connection.prepareStatement("SELECT detail.id, detail.name FROM detail WHERE detail.id IN (SELECT dd.tail FROM dd WHERE dd.head = ?)");


            statement.setInt(1, head.getId());

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Detail tail = new Detail(result.getInt(1), result.getString(2));
                tails.add(tail);
            }
        } catch (SQLException ex) {
            exception = ex;
        } finally {
            ConnectionCloser.close(connection, statement, exception);
        }

        return tails;
    }
}