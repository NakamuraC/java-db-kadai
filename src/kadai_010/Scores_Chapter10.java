package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {

	public static void main(String[] args) throws SQLException {
		// TODO 自動生成されたメソッド・スタブ
		Connection con = null;
		Statement statement = null;
		int rowCnt = 0;

		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					"");

			System.out.println("データベース接続成功：" + con);
			System.out.println("レコード更新を実行します");

			statement = con.createStatement();
			String sql = "UPDATE scores SET score_math = 95, score_english = 80 WHERE id = 5;";
			
			rowCnt = statement.executeUpdate(sql);
			
			System.out.println(rowCnt + "件のレコードが更新されました");
			
			String sql2 = "SELECT * FROM scores ORDER BY score_math DESC, score_english DESC ;";
			ResultSet result = statement.executeQuery(sql2);
			
			System.out.println("数学・英語の点数が高い順に並べ替えました");
			
			while(result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				int score_math = result.getInt("score_math");
				int score_english = result.getInt("score_english");
				
				System.out.println(result.getRow() + "件目：生徒ID=" + id
						+ "/氏名=" + name + "/数学=" + score_math + "/英語=" + score_english);
			}

		}catch(SQLException e){
			System.out.println(e.getMessage());
		}finally {
			
			if (statement != null) {
				statement.close();
			}
			if (con != null) {
				con.close();
			}
			
		}

	}

}
