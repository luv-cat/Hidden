package model;

/**
*
* @author <hidden>
*/

public class User {

	/**
	 * @param userId - 従業員管理のユーザID
	 */
	private String userId;

	/**
	 * @param password - 従業員管理のパスワード
	 */
	private String password;

	/**
	 * 	Userを構築します。
	 * @param userId - 総務部従業員ID
	 * @param password - 従業員管理のパスワード
	 */
	public User(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}

	/**
	 * フィールドuserIdの値を返します。
	 * @return userId - 総務部従業員ID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * フィールドuserIdの値を設定します。
	 * @param userId - 総務部従業員ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * フィールドpasswordの値を返します。
	 * @return password - 従業員管理のパスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * フィールドpasswordの値を設定します。
	 * @param password - 従業員管理のパスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
