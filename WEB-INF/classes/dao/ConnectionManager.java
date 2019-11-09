package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author <hidden>
 */
public class ConnectionManager {

	/**
	 * データベースURL
	 * @param URL - hiddendbのデータベース接続先
	 */
	private static final String URL = "jdbc:mysql://pc-sakurai:3306/hiddendb?useSSL=false";

	/**
	 * ユーザ
	 * @param USER - hiddendb管理ユーザー
	 */
	private static final String USER = "hiddenuser";

	/**
	 * パスワード
	 * @param PASSWORD - hiddendbのユーザーのパスワード
	 */
	private static final String PASSWORD = "hiddenpass";

	/**
	 *
	 */
	private static ConnectionManager instance = new ConnectionManager();

	/**
	 *コンストラクタ
	 */
	private ConnectionManager() {
	}

	/**
	 *	コネクションマネージャのインスタンスを取得して返します。
	 * @return instance - コネクションマネージャのインスタンス
	 */
	public static ConnectionManager getInstance() {
		return instance;
	}

	/**
	 * hiddendbへデータベース接続し、取得して返します。
	 * @return - hiddendbへのコネクション
	 * @throws SQLException - SQLに関する例外処理
	 */
	public Connection connect() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
