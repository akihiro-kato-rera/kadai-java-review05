import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Review05 {

    public static void main(String[] args) {

        Connection con = null;
        PreparedStatement spstmt = null;
        PreparedStatement ipstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/kadaidb?useSSL=false&allowPublickeyRetrieval=true",
                    "root",
                    "Kanae19910309"
                    );

         String selectsql = "SELECT * FROM person where id = ?";
         spstmt = con.prepareStatement(selectsql);

         System.out.print("検索キーワードを入力してください　>");
         String str1 = keyIn();

         spstmt.setString(1, str1);

         rs = spstmt.executeQuery();

         while(rs.next()) {

             String name = rs.getString("Name");
             int age = rs.getInt("age");

             System.out.println(name);
             System.out.println(age);
         }
         rs.close();

        }catch(ClassNotFoundException e) {
            System.err.println("JDBCドライバのロードに失敗しました。");
            e.printStackTrace();
        }catch(SQLException e){
            System.err.println("データベースに異常が発生しました。");
            e.printStackTrace();
        }finally {
            if(rs != null) {
                try {
                    rs.close();
                }catch(SQLException e) {
                    System.err.println("ResultSetを閉じるときにエラーが発生しました。");
                    e.printStackTrace();
                }
            }
            if(ipstmt != null) {
                try{
                    ipstmt.close();
                }catch(SQLException e) {
                    System.err.println("PreparedStatementを閉じるときにエラーが発生しました。");
                    e.printStackTrace();
                }
            }
            if(con != null) {
                try {
                    con.close();
                }catch(SQLException e) {
                    System.err.println("データベース切断時にエラーが発生しました。");
                    e.printStackTrace();

                }
            }
        }
    }





         private static String keyIn() {
             String line = null;
             try {
                 BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
                 line = key.readLine();
             }catch(IOException e) {
             }
             return line;
             }

    }

//}

