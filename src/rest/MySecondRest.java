package rest;

import rest.utils.DbUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * ...
 */
@Path("/student")
public class MySecondRest {
    @GET
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudents() {


        List<Student> students = new ArrayList<>();
        DbUtils dbUtils = new DbUtils();
        Connection connection = dbUtils.createConnection();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM  students");
                while (resultSet.next()) {
                    students.add(new Student(resultSet.getInt("id"), resultSet.getString("name"),
                            resultSet.getString("surname"), resultSet.getString("email"),
                            resultSet.getString("phone")));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return students;
    }

    @GET
    @Path("/{id}/student")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudent(@PathParam("id") int id) {
        Student student = null;
        DbUtils dbUtils = new DbUtils();
        Connection connection = dbUtils.createConnection();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT  * FROM Students where id=" + id);
                resultSet.next();
                student = new Student(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("surname"), resultSet.getString("email"),
                        resultSet.getString("phone"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return student;
    }
}
