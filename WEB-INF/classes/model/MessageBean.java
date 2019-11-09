
package model;

/**
 *
 * @author <hidden>
 */

public class MessageBean {
	/**
	 * @param messageId - メッセージID
	 */
	int messageId;

	/**
	 * @param sendEmployee - 送信者の従業員コード
	 */
	String sendEmployee;

	/**
	 * @param recieveEmployee - 受信者の従業員コード
	 */
	String recieveEmployee;

	/**
	 * @param messageBody - メッセージ本文
	 */
	String messageBody;

	/**
	 * @param postDatetime - メッセージを送信した日時
	 */
	String postDatetime;

	/**
	 * @param lastName - 氏
	 */
	String lastName;

	/**
	 * @param firstName - 名
	 */
	String firstName;

	/**
	 * @param myEmpCode - 送信元の従業員コード
	 */
	String myEmpCode;

	/**
	 * @param comEmpCode - 送信先の従業員コード
	 */
	String comEmpCode;

	/**
	 * @param fullName - 名前
	 */
	String fullName = lastName + firstName;

	/**
	 * @param senderFlag - 送信メッセージフラグ
	 */
	boolean senderFlag;

	/**
	 * @param recieverFlag - 受信者メッセージフラグ
	 */
	boolean recieverFlag;

	/**
	 * MessageBeanを構築します。
	 *
	 * @param messageId - メッセージID
	 * @param sendEmployee - 送信者の従業員コード
	 * @param recieveEmployee - 受信者の従業員コード
	 * @param messageBody - メッセージ本文
	 * @param postDatetime - メッセージを送信した日時
	 */
	public MessageBean(int messageId, String sendEmployee, String recieveEmployee, String messageBody,
			String postDatetime) {
		this.messageId = messageId;
		this.sendEmployee = sendEmployee;
		this.recieveEmployee = recieveEmployee;
		this.messageBody = messageBody;
		this.postDatetime = postDatetime;
	}

	/**
	 * MessageBeanを構築します。
	 *
	 * * @param messageId - メッセージID
	 * @param sendEmployee - 送信者の従業員コード
	 * @param recieveEmployee - 受信者の従業員コード
	 * @param messageBody - メッセージ本文
	 * @param postDatetime - メッセージを送信した日時
	 * @param lastName - 氏
	 * @param firstName - 名
	 */
	public MessageBean(int messageId, String sendEmployee, String recieveEmployee, String messageBody,
			String postDatetime, String lastName, String firstName) {
		this.messageId = messageId;
		this.sendEmployee = sendEmployee;
		this.recieveEmployee = recieveEmployee;
		this.messageBody = messageBody;
		this.postDatetime = postDatetime;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	/**
	 * MessageBeanを構築します。
	 *
	 * @param messageId - メッセージID
	 * @param fullName - 名前
	 * @param messageBody - メッセージ本文
	 * @param postDatetime - メッセージを送信した日時
	 * @param senderFlag - 送信メッセージフラグ
	 * @param recieverFlag - 受信者メッセージフラグ
	 */
	public MessageBean(int messageId, String fullName, String messageBody, String postDatetime, boolean senderFlag,
			boolean recieverFlag) {
		this.messageId = messageId;
		this.fullName = fullName;
		this.messageBody = messageBody;
		this.postDatetime = postDatetime;
		this.senderFlag = senderFlag;
		this.recieverFlag = recieverFlag;
	}

	/**
	 * MessageBeanを構築します。
	 *
	 * @param comEmpCode - 送信先の従業員コード
	 * @param fullName - 名前
	 */
	//送信者氏名取得のための、コンストラクタのオーバーロード①
	public MessageBean(String comEmpCode, String fullName) {
		this.fullName = fullName;
		this.comEmpCode = comEmpCode;
	}

	/**
	 * MessageBeanを構築します。
	 *
	 * @param messageId - メッセージID
	 * @param myEmpCode - 送信元の従業員コード
	 * @param comEmpCode - 送信先の従業員コード
	 * @param messageBody - メッセージ本文
	 */
	//送信者・受信者の従業員コード取得のための、コンストラクタのオーバーロード②
	public MessageBean(int messageId, String myEmpCode, String comEmpCode, String messageBody) {
		this.messageId = messageId;
		this.myEmpCode = myEmpCode;
		this.comEmpCode = comEmpCode;
		this.messageBody = messageBody;
	}

	/**
	 * MessageBeanを構築します。
	 *
	 * @param sendEmployee - 送信者の従業員コード
	 * @param recieveEmployee - 受信者の従業員コード
	 * @param messageBody - メッセージ本文
	 */
	//　廃棄メッセージの情報取得用コンストラクタ
	public MessageBean(String sendEmployee, String recieveEmployee, String messageBody) {
		this.sendEmployee = sendEmployee;
		this.recieveEmployee = recieveEmployee;
		this.messageBody = messageBody;
	}

	/**
	 * MessageBeanを構築します。
	 */
	public MessageBean() {
	}

	/**
	 * フィールドmessageIdの値を返します。
	 * @return messageId - メッセージID
	 */
	public int getMessageId() {
		return messageId;
	}

	/**
	 * フィールドmessageIdの値を設定します。
	 * @param messageId - メッセージID
	 */
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	/**
	 * フィールドsendEmployeeの値を返します。
	 * @return sendEmployee - 送信者の従業員コード
	 */
	public String getSendEmployee() {
		return sendEmployee;
	}

	/**
	 * フィールドsendEmployeeの値を設定します。
	 * @param sendEmployee - 送信者の従業員コード
	 */
	public void setSendEmployee(String sendEmployee) {
		this.sendEmployee = sendEmployee;
	}

	/**
	 * フィールドrecieveEmployeeの値を返します。
	 * @return recieveEmployee - 受信者の従業員コード
	 */
	public String getRecieveEmployee() {
		return recieveEmployee;
	}

	/**
	 * フィールドrecieveEmployeeの値を設定します。
	 * @param recieveEmployee - 受信者の従業員コード
	 */
	public void setRecieveEmployee(String recieveEmployee) {
		this.recieveEmployee = recieveEmployee;
	}

	/**
	 * フィールドmessageBodyの値を返します。
	 * @return messageBody - メッセージ本文
	 */
	public String getMessageBody() {
		return messageBody;
	}

	/**
	 * フィールドmessageBodyの値を設定します。
	 * @param messageBody - メッセージ本文
	 */
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	/**
	 * フィールドpostDatetimeの値を返します。
	 * @return postDatetime - メッセージを送信した日時
	 */
	public String getPostDatetime() {
		return postDatetime;
	}

	/**
	 * フィールドpostDatetimeの値を設定します。
	 * @param postDatetime - メッセージを送信した日時
	 */
	public void setPostDatetime(String postDatetime) {
		this.postDatetime = postDatetime;
	}

	/**
	 * フィールドlastNameの値を返します。
	 * @return lastName - 氏
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * フィールドlastNameの値を設定します。
	 * @param lastName - 氏
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * フィールドfirstNameの値を返します。
	 * @return param firstName - 名
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * フィールドfirstNameの値を設定します。
	 * @param param firstName - 名
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * フィールドmyEmpCodeの値を返します。
	 * @return myEmpCode - 送信元の従業員コード
	 */
	public String getMyEmpCode() {
		return myEmpCode;
	}

	/**
	 * フィールドmyEmpCodeの値を設定します。
	 * @param myEmpCode - 送信元の従業員コード
	 */
	public void setMyEmpCode(String myEmpCode) {
		this.myEmpCode = myEmpCode;
	}

	/**
	 * フィールドcomEmpCodeの値を返します。
	 * @return comEmpCode - 送信先の従業員コード
	 */
	public String getComEmpCode() {
		return comEmpCode;
	}

	/**
	 * フィールドcomEmpCodeの値を設定します。
	 * @param comEmpCode - 送信先の従業員コード
	 */
	public void setComEmpCode(String comEmpCode) {
		this.comEmpCode = comEmpCode;
	}

	/**
	 * フィールドsenderFlagの値を返します。
	 * @return senderFlag - 送信メッセージフラグ
	 */
	public boolean isSenderFlag() {
		return senderFlag;
	}

	/**
	 * フィールドsenderFlagの値を設定します。
	 * @param senderFlag - 送信メッセージフラグ
	 */
	public void setSenderFlag(boolean senderFlag) {
		this.senderFlag = senderFlag;
	}

	/**
	 * フィールドrecieverFlagの値を返します。
	 * @return recieverFlag - 受信者メッセージフラグ
	 */
	public boolean isRecieverFlag() {
		return recieverFlag;
	}

	/**
	 * フィールドrecieverFlagの値を設定します。
	 * @param recieverFlag - 受信者メッセージフラグ
	 */
	public void setRecieverFlag(boolean recieverFlag) {
		this.recieverFlag = recieverFlag;
	}

	/**
	 * フィールドfullNameの値を返します。
	 * @return fullName - 名前
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * フィールドfullNameの値を設定します。
	 * @param fullName - 名前
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
