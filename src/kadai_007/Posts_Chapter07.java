package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Posts_Chapter07 {
	public static void main(String[] args) throws SQLException {
		Connection con = null;
		PreparedStatement statement = null;
		Statement statement2 = null;
		
		String[][] data = {
				{"1003", "2023-02-08","昨日の夜は徹夜でした・・","13"},
				{"1002", "2023-02-08","お疲れ様です！", "12"},
				{"1003", "2023-02-09","今日も頑張ります！", "18"},
				{"1001", "2023-02-09","無理は禁物ですよ！", "17"},
				{"1002", "2023-02-10","明日から連休ですね！", "20"}
		};
		
		
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java", 
					"root",
					"");
			System.out.println("接続成功;" + con);
			
			
			String sql = "INSERT INTO posts ( user_id, posted_at, post_content, likes) VALUES (?, ?, ?, ?);";
			statement = con.prepareStatement(sql);
			int rowCnt = 0;
			
			for(int i = 0; i < data.length; i++) {
				statement.setString(1, data[i][0]);
				statement.setString(2, data[i][1]);
				statement.setString(3, data[i][2]);
				statement.setInt(4, Integer.parseInt(data[i][3]));
				rowCnt += statement.executeUpdate();
			}

			System.out.println(rowCnt + "件のレコードが追加されました");
			
			statement2 = con.createStatement();
			String sql2 = "SELECT  posted_at, post_content, likes FROM posts WHERE user_id = 1002;";
			
			System.out.println("ユーザーIDが1002のレコードを検索しました");
			
			ResultSet result = statement2.executeQuery(sql2);
			
			String posted_at = null;
			String post_content = null;
			int likes = 0;
			
			while(result.next()) {
				posted_at = result.getString("posted_at");
				post_content = result.getString("post_content");
				likes = result.getInt("likes");
				System.out.println("件目：投稿日時=" + posted_at + "/投稿内容=" + post_content + "／いいね数=" + likes);
				
			}

		}finally {
			if(con != null) {
				try {con.close();}catch(SQLException ignore) {}
			}
			if(statement != null) {
				try {statement.close();}catch(SQLException ignore) {}
			}
			if(statement2 != null) {
				try {statement2.close();}catch(SQLException ignore) {}
			}
		}
	}
}
