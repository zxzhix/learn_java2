package data;

import java.sql.*;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos", "root", "zz4180645");
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
            //System.out.println("Insert succ");
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
           // String tInsql="insert into pos.user (UserCode, UserName,password, Sex, Birthday, State,MakeDate,ModifyDate)" +
             //       "      VALUES (CONCAT('user', '"+i+"'), CONCAT('name', '"+i+"'),'password', 0,sysdate(), 1, sysdate(),sysdate());";

          //  String tInsql="insert into pos.Commodity (CommID, Commname,Price, Count, EffectStartDate, EffectEndDate ,state,MakeDate,ModifyDate)" +
           //         "      VALUES (CONCAT('CommID', '"+i+"'), CONCAT('Commname', '"+i+"'),'Price', '"+i+"',sysdate()-6,sysdate()+10 ,'1', sysdate(),sysdate());";

            ExecutorService executorService = Executors.newCachedThreadPool();
            long startTime = System.currentTimeMillis();
            for(int i=1;i<=100;i++)
            {
                executorService.submit(new Runnable() {
                    @Override
                   // for(int m=1;m<=1000;m++)
                    //{


                        public void run() {
                        String tInsql="insert into pos.OrderMain ( UserCode,TotalMoney, Count, state,MakeDate,ModifyDate)" +
                                "      VALUES ( 'user',1000, 10,'1', sysdate(),sysdate());";

                        jdbc.insert(tInsql);
                  //  }
                    }

                });

            }
            long endtTime = System.currentTimeMillis();
            System.out.println("执行时间"+(endtTime-startTime));

           /* String tQuSql="select * from test where pid='2'";
            List<Map<String, Object>> results = jdbc.query(tQuSql, tMap);
            for (Map r: results) {
                System.out.println(r.toString());
            }*/

            jdbc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
