package model;

/**
 *
 * @author <hidden>
 */

public class EmployeeBean {

	/**
	 * @param employeeCode - 従業員コード
	 */
	private String employeeCode;

	/**
	 * @param lastName - 従業員の氏
	 */
	private String lastName;

	/**
	 * @param firstName - 従業員の名
	 */
	private String firstName;

	/**
	 * @param lastKanaName - 従業員の氏かな
	 */
	private String lastKanaName;

	/**
	 * @param firstKanaName - 従業員の名かな
	 */
	private String firstKanaName;

	/**
	 * @param gender - 従業員の性別
	 */
	private int gender;

	/**
	 * @param birthDay - 従業員の生年月日
	 */
	private String birthDay;

	/**
	 * @param sectionCode - 従業員の部署コード
	 */
	private String sectionCode;

	/**
	 * @param hireDate - 従業員の入社日
	 */
	private String hireDate;

	/**
	 * @param mailPass - 従業員用のパスワード
	 */
	private String mailPass;

	/**
	 * 	EmployeeBeanを構築します。
	 * @param employeeCode - 従業員コード
	 * @param lastName - 従業員の氏
	 * @param firstName - 従業員の名
	 * @param lastKanaName - 従業員の氏かな
	 * @param firstKanaName - 従業員の名かな
	 * @param gender - 従業員の性別
	 * @param birthDay - 従業員の生年月日
	 * @param sectionCode - 従業員の部署コード
	 * @param hireDate - 従業員の入社日
	 */
	public EmployeeBean(String employeeCode, String lastName, String firstName, String lastKanaName, String firstKanaName, int gender, String birthDay, String sectionCode, String hireDate) {
		this.employeeCode = employeeCode;
		this.lastName = lastName;
		this.firstName = firstName;
		this.lastKanaName = lastKanaName;
		this.firstKanaName = firstKanaName;
		this.gender = gender;
		this.birthDay = birthDay;
		this.sectionCode = sectionCode;
		this.hireDate = hireDate;
	}

	/**
	 * 	EmployeeBeanを構築します。
	 * @param employeeCode - 従業員コード
	 * @param lastName - 従業員の氏
	 * @param firstName - 従業員の名
	 * @param lastKanaName - 従業員の氏かな
	 * @param firstKanaName - 従業員の名かな
	 * @param gender - 従業員の性別
	 * @param birthDay - 従業員の生年月日
	 * @param sectionCode - 従業員の部署コード
	 * @param hireDate - 従業員の入社日
	 * @param mailPass - 従業員用のパスワード
	 */
	public EmployeeBean(String employeeCode, String lastName, String firstName, String lastKanaName, String firstKanaName, int gender, String birthDay, String sectionCode, String hireDate,String mailPass) {
		this.employeeCode = employeeCode;
		this.lastName = lastName;
		this.firstName = firstName;
		this.lastKanaName = lastKanaName;
		this.firstKanaName = firstKanaName;
		this.gender = gender;
		this.birthDay = birthDay;
		this.sectionCode = sectionCode;
		this.hireDate = hireDate;
		this.mailPass=mailPass;
	}

	/**
	 * 	EmployeeBeanを構築します。
	 * @param employeeCode - 従業員コード
	 * @param lastName - 従業員の氏
	 * @param firstName - 従業員の名
	 * @param lastKanaName - 従業員の氏かな
	 * @param firstKanaName - 従業員の名かな
	 * @param gender - 従業員の性別
	 * @param birthDay - 従業員の生年月日
	 * @param sectionCode - 従業員の部署コード
	 * @param hireDate - 従業員の入社日
	 */
	public EmployeeBean(String employeeCode,String mailPass, String lastName, String firstName, String lastKanaName, String firstKanaName, int gender, String birthDay, String sectionCode, String hireDate) {
		this.employeeCode = employeeCode;
		this.mailPass=mailPass;
		this.lastName = lastName;
		this.firstName = firstName;
		this.lastKanaName = lastKanaName;
		this.firstKanaName = firstKanaName;
		this.gender = gender;
		this.birthDay = birthDay;
		this.sectionCode = sectionCode;
		this.hireDate = hireDate;
	}

	/**
	 * 	EmployeeBeanを構築します。
	 */
	public EmployeeBean() {	}

	/**
	 * フィールドemployeeCodeの値を返します。
	 * @return employeeCode - 従業員コード
	 */
	public String getEmployeeCode() {
		return employeeCode;
	}

	/**
	 * フィールドemployeeCodeの値を設定します。
	 * @param employeeCode - 従業員コード
	 */
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	/**
	 * フィールドlastNameの値を返します。
	 * @return lastName - 従業員の氏
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * フィールドlastNameの値を設定します。
	 * @param lastName - 従業員の氏
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * フィールドfirstNameの値を返します。
	 * @return firstName - 従業員の名
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * フィールドfirstNameの値を設定します。
	 * @param firstName - 従業員の名
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * フィールドlastKanaNameの値を返します。
	 * @return lastKanaName - 従業員の氏かな
	 */
	public String getLastKanaName() {
		return lastKanaName;
	}

	/**
	 * フィールドlastKanaNameの値を設定します。
	 * @param lastKanaName - 従業員の氏かな
	 */
	public void setLastKanaName(String lastKanaName) {
		this.lastKanaName = lastKanaName;
	}

	/**
	 * フィールドfirstKanaNameの値を返します。
	 * @return firstKanaName - 従業員の名かな
	 */
	public String getFirstKanaName() {
		return firstKanaName;
	}

	/**
	 * フィールドfirstKanaNameの値を設定します。
	 * @param firstKanaName - 従業員の名かな
	 */
	public void setFirstKanaName(String firstKanaName) {
		this.firstKanaName = firstKanaName;
	}

	/**
	 * フィールドgenderの値を返します。
	 * @return gender - 従業員の性別
	 */
	public int getGender() {
		return gender;
	}

	/**
	 * フィールドgenderの値を設定します。
	 * @param gender - 従業員の性別
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}

	/**
	 * フィールドbirthDayの値を返します。
	 * @return birthDay - 従業員の生年月日
	 */
	public String getBirthDay() {
		return birthDay;
	}

	/**
	 * フィールドbirthDayの値を設定します。
	 * @param birthDay - 従業員の生年月日
	 */
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	/**
	 * フィールドsectionCodeの値を返します。
	 * @return sectionCode - 従業員の部署コード
	 */
	public String getSectionCode() {
		return sectionCode;
	}

	/**
	 * フィールドsectionCodeの値を設定します。
	 * @param sectionCode - 従業員の部署コード
	 */
	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	/**
	 * フィールドhireDateの値を返します。
	 * @return hireDate - 従業員の入社日
	 */
	public String getHireDate() {
		return hireDate;
	}

	/**
	 * フィールドhireDateの値を設定します。
	 * @param hireDate - 従業員の入社日
	 */
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	/**
	 * フィールドmailPassの値を返します。
	 * @return mailPass - 従業員用のパスワード
	 */
	public String getMailPass() {
		return mailPass;
	}

	/**
	 * フィールドmailPassの値を設定します。
	 * @param mailPass 従業員用のパスワード
	 */
	public void setMailPass(String mailPass) {
		this.mailPass = mailPass;
	}
}

