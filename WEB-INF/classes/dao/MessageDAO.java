package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.EmployeeBean;
import model.MessageBean;

/**
 * @author <hidden>
 */
public class MessageDAO {
	/**
	 * @param instance - インスタンス
	 */
	private static MessageDAO instance = new MessageDAO();

	/**
	 * @param con - コネクション
	 */
	private Connection con;

	/**
	 * @param st - ステートメント
	 */
	private Statement st;

	/**
	 * @param mb - 従業員情報のオブジェクト
	 */
	MessageBean mb = null;

	/**
	 * @param sendList - 送信メッセージのオブジェクト
	 */
	private List<MessageBean> sendList = new ArrayList<MessageBean>();

	/**
	 * @param sendReplaceList - (メッセージ欄に改行がない)送信メッセージのオブジェクト
	 */
	private List<MessageBean> sendReplaceList = new ArrayList<MessageBean>();

	/**
	 * @param recieveList -  (メッセージ欄に改行がない)受信メッセージのオブジェクト
	 */
	private List<MessageBean> recieveList = new ArrayList<MessageBean>();

	/**
	 * @param recieveReplaceList - (メッセージ欄に改行がない)受信メッセージのオブジェクト
	 */
	private List<MessageBean> recieveReplaceList = new ArrayList<MessageBean>();

	/**
	 * コンストラクタ
	 */
	private MessageDAO() {
	}

	/**
	 * インスタンスを取得して返します。
	 * @return instance - インスタンス
	 */
	public static MessageDAO getInstance() {
		return instance;
	}

	/**
	 * データベースへの接続を取得して返します。
	 *
	 * @throws SQLException
	 */
	public void dbConnect() throws SQLException {
		ConnectionManager cm = ConnectionManager.getInstance();
		con = cm.connect();
	}

	/**
	 * ステートメントを作成して返します。
	 *
	 * @throws SQLException
	 */
	public void createSt() throws SQLException {
		st = con.createStatement();
	}

	/**
	 * ステートメントを作成して返します。
	 *
	 * @author <hidden>
	 * @param sql - 発行されたSQL文
	 * @return pst - ステートメント
	 * @throws SQLException
	 */
	public PreparedStatement createPst(String sql) throws SQLException {
		PreparedStatement pst = con.prepareStatement(sql);
		return pst;
	}

	/**
	 * データベースへの切断を取得して返します。
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
	 * 送信先の氏名・従業員コードを取得して返します。
	 *
	 * @author <hidden>
	 * @return senderList - 送信先の氏名・従業員コード
	 * @throws SQLException
	 */
	public List<MessageBean> senderCreate() throws SQLException {
		List<MessageBean> senderList = new ArrayList<MessageBean>();
		String sql = "SELECT * FROM m_employee WHERE exist_flag = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, 1);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			String comEmpCode = rs.getString(1);
			String lastName = rs.getString(2);
			String firstName = rs.getString(3);
			String fullName = lastName + firstName;

			senderList.add(new MessageBean(comEmpCode, fullName));
		}
		return senderList;
	}

	/**
	 * 返信メッセージの宛先を取得して返します。
	 *
	 * @author <hidden>
	 * @param returnId  - 返信するメッセージのメッセージ番号
	 * @return fullName - 返信メッセージの宛先
	 * @throws SQLException
	 */
	public String returnCreate(String returnId) throws SQLException {
		String fullName = null;
		;
		String sql = "SELECT last_name, first_name FROM m_employee WHERE (employee_code = ?)and(exist_flag=?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, returnId);
		pstmt.setInt(2, 1);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			String lastName = rs.getString(1);
			String firstName = rs.getString(2);
			fullName = lastName + firstName;
		}
		return fullName;
	}

	/**
	 * 返信相手の従業員番号を取得して返します。
	 *
	 * @author <hidden>
	 * @param msgId - 返信するメッセージのメッセージ番号
	 * @return empCode - 返信相手の従業員番号
	 * @throws SQLException
	 */
	public String returnEmpCode(int msgId) throws SQLException {
		String empCode = null;
		String sql = "SELECT send_employee FROM t_message WHERE (message_id = ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, msgId);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			empCode = rs.getString(1);
		}
		return empCode;
	}

	/**
	 * 送信したメッセージをDBに追加し、その可否の真偽値を返します。
	 *
	 * @author <hidden>
	 * @param myEmpCode - 送信元の従業員コード
	 * @param comEmpCode - 送信先の従業員コード
	 * @param messageBody - メッセージ本文
	 * @return flag - メッセージ送信の可否の真偽値
	 * @throws SQLException
	 */
	public boolean sendMessage(String myEmpCode, String comEmpCode, String messageBody)
			throws SQLException {
		con.setAutoCommit(false);
		boolean flag = false;
		String sql = "INSERT INTO t_message"
				+ " (send_employee, recieve_employee, message_body)"
				+ " values(?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, myEmpCode);
		pstmt.setString(2, comEmpCode);
		pstmt.setString(3, messageBody);
		int count = pstmt.executeUpdate();

		if (count > 0) {
			flag = true;
			con.commit();
		}
		return flag;
	}

	/**
	 * 受信メッセージを取得して返します。
	 *
	 * @author <hidden>
	 * @param employeeCode - 従業員コード
	 * @return recieveList - 受信メッセージのリスト
	 * @throws SQLException
	 */
	public List<MessageBean> getRecieveMessage(String employeeCode) throws SQLException {
		recieveList.clear();
		//  employeeCodeがrecieve_employeeとマッチしたレコードを取得する
		String sql = "select message_id, CONCAT(joinedtable.last_name,joinedtable.first_name)as sesnder ,recieve_employee,message_body,"
				+ " post_datetime,sender_flag  ,reciever_flag from (select t1.message_id ,t1.send_employee,t1.recieve_employee ,t1.message_body,"
				+ " t1.post_datetime,t2.last_name,t2.first_name,t1.sender_flag  ,t1.reciever_flag from t_message t1 LEFT OUTER JOIN m_employee t2 "
				+ " ON t1.send_employee = t2.employee_code) joinedtable where joinedtable.recieve_employee= ? and joinedtable.reciever_flag=true order by  post_datetime desc;";
		PreparedStatement pstmt = createPst(sql);
		pstmt.setString(1, employeeCode);

		ResultSet rs = pstmt.executeQuery();

		//該当レコードの取得とレコードのインスタンスをrecieveリスト(フィールド)に格納
		while (rs.next()) {
			int messageId = rs.getInt(1);
			String fullName = rs.getString(2);
			String messageBody = rs.getString(4);
			String postDatetime = rs.getString(5);
			boolean senderFlag = rs.getBoolean(6);
			boolean recieverFlag = rs.getBoolean(7);
			MessageBean mb = new MessageBean(messageId, fullName, messageBody, postDatetime, senderFlag, recieverFlag);
			recieveList.add(mb);
		}
		return recieveList;
	}

	/**
	 * (メッセージ欄に改行がない)受信メッセージを取得して返します。
	 *
	 * @author <hidden>
	 * @param employeeCode - 従業員コード
	 * @return recieveReplaceList - (メッセージ欄に改行がない)受信メッセージのリスト
	 * @throws SQLException
	 */
	public List<MessageBean> getReplaceRecMess(String employeeCode) throws SQLException {
		recieveReplaceList.clear();
		//  employeeCodeがrecieve_employeeとマッチしたレコードを取得する
		String sql = "select message_id, CONCAT(joinedtable.last_name,joinedtable.first_name)as sesnder ,recieve_employee, replace(replace(replace(message_body,char(13),''),char(10),''),char(9),''),"
				+ " post_datetime,sender_flag  ,reciever_flag from (select t1.message_id ,t1.send_employee,t1.recieve_employee ,t1.message_body,"
				+ " t1.post_datetime,t2.last_name,t2.first_name,t1.sender_flag  ,t1.reciever_flag from t_message t1 LEFT OUTER JOIN m_employee t2 "
				+ " ON t1.send_employee = t2.employee_code) joinedtable where joinedtable.recieve_employee= ? and joinedtable.reciever_flag=true order by  post_datetime desc;";
		PreparedStatement pstmt = createPst(sql);
		pstmt.setString(1, employeeCode);

		ResultSet rs = pstmt.executeQuery();

		//該当レコードの取得とレコードのインスタンスをrecieveリスト(フィールド)に格納
		while (rs.next()) {
			int messageId = rs.getInt(1);
			String fullName = rs.getString(2);
			String messageBody = rs.getString(4);
			String postDatetime = rs.getString(5);
			boolean senderFlag = rs.getBoolean(6);
			boolean recieverFlag = rs.getBoolean(7);
			MessageBean mb = new MessageBean(messageId, fullName, messageBody, postDatetime, senderFlag, recieverFlag);
			recieveReplaceList.add(mb);
		}
		return recieveReplaceList;
	}

	/**
	 * 送信メッセージを取得して返します。
	 *
	 * @author <hidden>
	 * @param employeeCode - 従業員コード
	 * @return sendList - 送信メッセージのリスト
	 * @throws SQLException
	 */
	public List<MessageBean> getSendMessage(String employeeCode) throws SQLException {
		sendList.clear();
		//  employeeCodeがsend_employeeとマッチしたレコードを取得する
		String sql = "select message_id, CONCAT(joinedtable.last_name,joinedtable.first_name)as reciever ,send_employee,message_body,"
				+ " post_datetime ,sender_flag ,reciever_flag from (select t1.message_id ,t1.send_employee,t1.recieve_employee "
				+ ",t1.message_body, t1.post_datetime,t2.last_name, t2.first_name ,t1.sender_flag ,t1.reciever_flag from t_message t1 "
				+ "LEFT OUTER JOIN m_employee t2 ON t1.recieve_employee = t2.employee_code) joinedtable where joinedtable.send_employee=? and joinedtable.sender_flag=true order by  post_datetime desc;";
		PreparedStatement pstmt = createPst(sql);
		pstmt.setString(1, employeeCode);

		ResultSet rs = pstmt.executeQuery();
		//該当レコードの取得とレコードのインスタンスをsendリスト(フィールド)に格納
		while (rs.next()) {
			int messageId = rs.getInt(1);
			String fullName = rs.getString(2);
			String messageBody = rs.getString(4);
			String postDatetime = rs.getString(5);
			boolean senderFlag = rs.getBoolean(6);
			boolean recieverFlag = rs.getBoolean(7);
			MessageBean mb = new MessageBean(messageId, fullName, messageBody, postDatetime, senderFlag, recieverFlag);
			sendList.add(mb);
		}

		return sendList;
	}


	/**
	 * (メッセージ欄に改行がない)送信メッセージを取得して返します。
	 *
	 * @author <hidden>
	 * @param employeeCode - 従業員コード
	 * @return sendReplaceList - (メッセージ欄に改行がない)送信メッセージのリスト
	 * @throws SQLException
	 */
	public List<MessageBean> getReplaceSendMessage(String employeeCode) throws SQLException {
		sendReplaceList.clear();
		//  employeeCodeがsend_employeeとマッチしたレコードを取得する
		String sql = "select message_id, CONCAT(joinedtable.last_name,joinedtable.first_name)as reciever ,send_employee, replace(replace(replace(message_body,char(13),''),char(10),''),char(9),''),"
				+ " post_datetime ,sender_flag ,reciever_flag from (select t1.message_id ,t1.send_employee,t1.recieve_employee "
				+ ",t1.message_body, t1.post_datetime,t2.last_name, t2.first_name ,t1.sender_flag ,t1.reciever_flag from t_message t1 "
				+ "LEFT OUTER JOIN m_employee t2 ON t1.recieve_employee = t2.employee_code) joinedtable where joinedtable.send_employee=? and joinedtable.sender_flag=true order by  post_datetime desc;";
		PreparedStatement pstmt = createPst(sql);
		pstmt.setString(1, employeeCode);

		ResultSet rs = pstmt.executeQuery();
		//該当レコードの取得とレコードのインスタンスをsendリスト(フィールド)に格納
		while (rs.next()) {
			int messageId = rs.getInt(1);
			String fullName = rs.getString(2);
			String messageBody = rs.getString(4);
			String postDatetime = rs.getString(5);
			boolean senderFlag = rs.getBoolean(6);
			boolean recieverFlag = rs.getBoolean(7);
			MessageBean mb = new MessageBean(messageId, fullName, messageBody, postDatetime, senderFlag, recieverFlag);
			sendReplaceList.add(mb);
		}

		return sendReplaceList;
	}

	/**
	 * ログインしたユーザのオブジェクトを返します。
	 *
	 * @author <hidden>
	 * @param employeeCode - 従業員コード
	 * @param mailPass - パスワード
	 * @return	mb - ログインした従業員のオブジェクト
	 * @throws SQLException
	 */
	public EmployeeBean LoginCheck(String employeeCode, String mailPass) throws SQLException {

		EmployeeBean mb = null;
		// オートコミットを無効にする。
		con.setAutoCommit(false);

		// mail_passがマッチしたレコードを取得する
		String sql = "select * from m_employee "
				+ "where (employee_code= ?) and (mail_pass= ?) "
				+ "and (exist_flag=1);";

		PreparedStatement pstmt = createPst(sql);
		pstmt.setString(1, employeeCode);
		pstmt.setString(2, mailPass);

		ResultSet rs = pstmt.executeQuery();

		// 該当レコードがあればインスタンスを生成する
		if (rs.next()) {
			employeeCode = rs.getString(1);
			mailPass = rs.getString(11);
			String lastName = rs.getString(2);
			String firstName = rs.getString(3);
			String lastKanaName = rs.getString(4);
			String firstKanaName = rs.getString(5);
			int gender = rs.getInt(6);
			String birthDay = rs.getString(7);
			String sectionCode = rs.getString(8);
			String hireDate = rs.getString(9);
			mb = new EmployeeBean(employeeCode, mailPass, lastName, firstName, lastKanaName, firstKanaName, gender,
					birthDay, sectionCode, hireDate);
			con.commit();
		}
		return mb;
	}

	/**
	 * 受信メッセージを削除し、その可否の真偽値を返します。
	 *
	 * @author <hidden>
	 * @param recvMessageId - 受信メッセージのID
	 * @return recvFlag - 受信メッセージ削除の可否の真偽値
	 * @throws SQLException
	 */
	public boolean recvFlag(int recvMessageId) throws SQLException {
		con.setAutoCommit(false);
		boolean recvFlag = false;
		String sql = "update t_message set reciever_flag = 0 "
				+ "where message_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, recvMessageId);
		int count = pstmt.executeUpdate();

		if (count > 0) {
			recvFlag = true;
			con.commit();
		}
		return recvFlag;

	}

	/**
	 * 送信メッセージを削除し、その可否の真偽値を返します。
	 *
	 * @author <hidden>
	 * @param sendMessageId - 送信者したメッセージのID
	 * @return sendFlag - 送信メッセージ削除の可否の真偽値
	 * @throws SQLException
	 */
	public boolean sendFlag(int sendMessageId) throws SQLException {
		con.setAutoCommit(false);
		boolean sendFlag = false;
		String sql = "update t_message set sender_flag = 0 "
				+ "where message_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, sendMessageId);
		int count = pstmt.executeUpdate();

		if (count > 0) {
			sendFlag = true;
			con.commit();
		}
		return sendFlag;

	}

}
