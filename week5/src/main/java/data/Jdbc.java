package data;

import java.sql.*;
import java.util.*;

/**
 * jdbc直接连
 */
public class Jdbc {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    private void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println();
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find mysql jdbc driver");
            e.printStackTrace();
            return;
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/posa", "root", "zz4180645");
            if (connection != null) {
                System.out.println("Connection succ!");
            } else {
                System.out.println("Connection fail!");
            }
        } catch (SQLException e) {
            System.out.println("Connection fail!");
            e.printStackTrace();
        }
    }

    public boolean insert(String tSql) {
        try {
            preparedStatement = connection.prepareStatement(tSql);
            System.out.println(preparedStatement.toString());

            preparedStatement.execute();
            System.out.println("Insert succ");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<Map<String, Object>> query(String tQuSql,Map tQueryMap) {
        try {

            preparedStatement = connection.prepareStatement(tQuSql);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Map<String, Object>> list = new ArrayList<>();
            Map tResult = new HashMap();
            while (resultSet.next()) {
                for (Object key: tQueryMap.keySet()) {
                    tResult.put(key, resultSet.getObject((String) key));
                }
                list.add(tResult);
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void close() throws SQLException {
        preparedStatement.close();
        connection.close();
        System.out.println("Connection close");
    }

    public static void main(String[] args) {
        Map<String, Object> tMap = new HashMap<>();
        tMap.put("pid", 0);
        tMap.put("name", "name");
        tMap.put("price", "price");

        try {
            Jdbc jdbc = new Jdbc();
            jdbc.createConnection();

            String tInsql="insert into test (pid,name,price) values(2,'苹果',22);";

           // jdbc.insert(tInsql);

            String tQuSql="select * from test where pid='2'";
            List<Map<String, Object>> results = jdbc.query(tQuSql, tMap);
            for (Map r: results) {
                System.out.println(r.toString());
            }

            jdbc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
