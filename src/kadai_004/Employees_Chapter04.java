package kadai_004;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Employees_Chapter04 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Connection con = null;
		Statement statement = null;
		
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					"");
			
			System.out.println("データベース接続成功：" + con);
			
			statement = con.createStatement();
			String sql = """
					CREATE TABLE employees(
					id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
					name VARCHAR(60) NOT NULL,
					email VARCHAR(225) NOT NULL,
					age INT,
					address VARCHAR(225))
					""";
			
			int rowCnt = statement.executeUpdate(sql);
			
			System.out.println("社員テーブルを作成しました:更新レコード数=" + rowCnt);
			
		}catch(SQLException e) {
			System.out.println("接続失敗" + e.getMessage());
		}finally {
			if(con != null) {
				try { con.close();}
				catch(SQLException ignore) {}
			}
			if(statement != null) {
				try { statement.close();}
				catch(SQLException ignore) {}
			}
		}
	}

}
