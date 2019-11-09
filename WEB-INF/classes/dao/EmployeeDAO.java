package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.EmployeeBean;
import model.User;

/**
 * @author <hidden>
 */
public class EmployeeDAO {
	/**
	 * @param instance - インスタンス
	 */
	private static EmployeeDAO instance = new EmployeeDAO();

	/**
	 * @param con - コネクション
	 */
	private Connection con;

	/**
	 * @param st - ステートメント
	 */
	private Statement st;

	/**
	 * コンストラクタ
	 */
	private EmployeeDAO() {
	}

	/**
	 * インスタンスを取得して返します。
	 * @return インスタンス
	 */
	public static EmployeeDAO getInstance() {
		return instance;
	}

	/**
	 * hiddendbデータベースへの接続おこなう。
	 *
	 * @throws SQLException - DB処理が正常に行われなかった場合
	 */
	public void dbConnect() throws SQLException {
		ConnectionManager cm = ConnectionManager.getInstance();
		con = cm.connect();
	}

	/**
	 * ステートメントを作成して返します。
	 *
	 * @throws SQLException - DB処理が正常に行われなかった場合
	 */
	public void createSt() throws SQLException {
		st = con.createStatement();
	}

	/**
	 * ステートメントを作成して返します。
	 *
	 * @author <hidden>
	 * @param 発行されたsql文
	 * @return ステートメント(pst)
	 * @throws SQLException - DB処理が正常に行われなかった場合
	 */
	public PreparedStatement createPst(String sql) throws SQLException {
		PreparedStatement pst = con.prepareStatement(sql);
		return pst;
	}

	/**
	 * hiddendbへのデータベース切断をおこなう。
	 * @throws SQLException - DB処理が正常に行われなかった場合
	 */
	public void dbDiscon() {
		try {
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * hiddendbデータベース従業員一覧を取得して返します。
	 *
	 * @author <hidden>
	 * @param 従業員のオブジェクト
	 * @return 従業員型(EmployeeBean)リストのオブジェクト
	 * @throws SQLException - DB処理が正常に行われなかった場合
	 */
	public List<EmployeeBean> showAllList() throws SQLException {
		List<EmployeeBean> employeeList = new ArrayList<EmployeeBean>();
		String sql = "SELECT * FROM m_employee WHERE exist_flag = ?;";
		PreparedStatement pstmt = createPst(sql);
		pstmt.setInt(1, 1);

		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			//従業員コード
			String employeeCode = rs.getString(1);
			//姓
			String lastName = rs.getString(2);
			//名
			String fastName = rs.getString(3);
			//かな（姓）
			String lastKanaName = rs.getString(4);
			//かな（名）
			String firstKanaName = rs.getString(5);
			//性別
			int gender = rs.getInt(6);
			//生年月日
			String birthDay = rs.getString(7);
			//部署コード
			String sectionCode = rs.getString(8);
			//入社日
			String hireDate = rs.getString(9);
			//
			String mail_pass = rs.getString(11);

			employeeList.add(new EmployeeBean(employeeCode, lastName, fastName, lastKanaName,
					firstKanaName, gender, birthDay, sectionCode, hireDate, mail_pass));
		}
		return employeeList;
	}


	/**
	 * hiddendbへの従業員情報の登録可否を真偽値で返します。
	 *
	 * @author <hidden>
	 * @param employeeCode - 従業員コード
	 * @param lastName - 従業員の氏
	 * @param firstName - 従業員の名
	 * @param lastKanaName - 従業員の氏かな
	 * @param firstKanaName - 従業員の名かな
	 * @param gender - 従業員の性別
	 * @param birthDay - 従業員の生年月日
	 * @param sectionCode - 従業員の部署コード
	 * @param hireDate - 従業員の入社日
	 * @param mailPass - 従業員のメッセージ確認用パスワード
	 * @return 従業員情報の登録可否の真偽値(true:登録成功時、false:登録失敗時)
	 * @throws SQLException - DB処理が正常に行われなかった場合
	 */
	public boolean regist(String employeeCode, String lastName, String firstName, String lastKanaName,
			String firstKanaName, int gender, String birthDay, String sectionCode, String hireDate, String mailPass)
			throws SQLException {
		con.setAutoCommit(false);
		boolean flag = false;
		String sql = "insert into m_employee values(?, ?, ?, ?, ?, ?, ?, ?, ?,null, ?, ?)";

		PreparedStatement pstmt = createPst(sql);
		pstmt.setString(1, employeeCode);
		pstmt.setString(2, lastName);
		pstmt.setString(3, firstName);
		pstmt.setString(4, lastKanaName);
		pstmt.setString(5, firstKanaName);
		pstmt.setInt(6, gender);
		pstmt.setString(7, birthDay);
		pstmt.setString(8, sectionCode);
		pstmt.setString(9, hireDate);
		pstmt.setString(10, mailPass);
		pstmt.setString(11, "1");

		int count = pstmt.executeUpdate();
		if (count > 0) {
			flag = true;
			con.commit();
		}

		return flag;
	}

	/**
	 * hiddendbに存在する、従業員情報の削除可否を真偽値で返します。
	 *
	 * @author <hidden>
	 * @param employeeCode - 従業員コード
	 * @return 従業員情報の削除可否の真偽値
	 * @throws SQLException - DB処理が正常に行われなかった場合
	 */

	public boolean delete(String employeeCode) throws SQLException {
		//成功フラグ
		boolean flag = false;

		dbConnect();
		con.setAutoCommit(false);

		String sql = "update m_employee set exist_flag = 0 where employee_code = ?;";

		PreparedStatement pstmt = createPst(sql);
		pstmt.setString(1, employeeCode);

		int result = pstmt.executeUpdate();

		if (result > 0) {
			flag = true;
			System.out.println(flag+"デバッグ用のフラグ");
			con.commit();
		}

		//trueの場合成功。
		return flag;

	}


	/**
	 * ログインの可否を真偽値で返します。
	 *
	 * @author <hidden>
	 * @param userId - 従業員管理のユーザID
	 * @param password - 従業員管理のパスワード
	 * @return ログインユーザーのインスタンス情報(User型)
	 * @throws SQLException - DB処理が正常に行われなかった場合
	 */
	public User LoginCheck(String userId, String password) throws SQLException {

		// オートコミットを無効にする
		con.setAutoCommit(false);

		User user = null;

		// userIdとpasswordでマッチしたユーザレコードを取得する
		String sql = "select * from m_user where user_id= ? and password= ?;";

		PreparedStatement pstmt = createPst(sql);
		pstmt.setString(1, userId);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();

		// 該当レコードが存在するかどうかの判定
		while (rs.next()) {
			String id = rs.getString("user_id");
			String pw = rs.getString("password");

			if (id.equals(userId) && pw.equals(password)) {
				user = new User(id, password);
				//flag = true;
				con.commit();
				break;
			}
		}

		return user;
	}
}
