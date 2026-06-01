package jdbc.section01;

import jdbc.common.JDBCUtil;

import java.sql.*;

public class Application2 {

    /*
    Prepared Statement
    - Statement의 SQL 실행 성능과 보안성을 향상시키기 위해 만들어짐
    - 쿼리를 미리 컴파일해두고 실행

    위치홀더 : ?
    - SQL문에서 실제 데이터 값이 들어갈 자리를 표시 가능
     */
    public static void main(String[] args) {

        // Connection
        Connection conn = JDBCUtil.getConnection();

        // java.sql의 interface로 import
        PreparedStatement pstmt = null;

        // 결과집합 (Select) 인터페이스
        ResultSet rs = null;

        try {

            String query = "select * from usertbl where role = ?";
            String query2 = "select * from usertbl where role = ? and name = ?";

            // prepareStatement는 객체 생성 시 Query를 함께 넣어주어야함.
            pstmt = conn.prepareStatement(query2);

            String userInput = "user";
            String userInput2 = "방문자";

            pstmt.setString(1,userInput); // 인덱스 시작 1부터
            pstmt.setString(2,userInput2);

            rs = pstmt.executeQuery();

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
