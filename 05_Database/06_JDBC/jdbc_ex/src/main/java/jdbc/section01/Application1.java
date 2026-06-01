package jdbc.section01;

import com.mysql.cj.protocol.Resultset;
import jdbc.common.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application1 {

    /*
    Statement
    - JDBC에서 SQL문을 실행하기 위한 인터페이스
    - SQL문을 문자열 그대로 직접 작성해서 실행

     */
    public static void main(String[] args) {

        // Connection
        Connection conn = JDBCUtil.getConnection();

        // java.sql의 interface로 import
        Statement stmt = null;

        // 결과집합 (Select) 인터페이스
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();

            // SQL Injection
            String userInput = "' OR '1' = '1''";

            String query = "select * from usertbl where name = '" + userInput + "'";

            rs = stmt.executeQuery(query);

            System.out.println("query : " + query);

            while (rs.next()) {
                System.out.println(rs.getString("id") + ", "
                        + rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close();
        }

    }
}
