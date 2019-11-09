package servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDAO;
import model.ChangeStr;
import model.User;

/**
 * Servlet implementation class Edit
 *
 * @author <hidden>
 */
@WebServlet("/EditCheck")
public class EditCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 従業員情報を編集する。
	 * @param request - クライアントが Servlet へ要求したリクエスト内容を含む HttpServletRequest オブジェクト
	 * @param response - Servlet がクライアントに返すレスポンス内容を含む HttpServletResponse オブジェクト
	 * @throws java.io.IOException - Servlet が GET リクエストを処理している間に入出力エラーが発生した場合
	 * @throws ServletException - GET に相当するリクエストが処理できない場合
	 * @throws SQLException - 編集処理が行われなかった場合
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//ログイン済みかどうか確認するif文を挿入する。直接URLを叩かれた時のためのチェック
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");

		if(user!=null) {

			EmployeeDAO dao=EmployeeDAO.getInstance();
			int flag=0;

			//EmployeeDisplayフォームからemployeeCodeを取得
			String employeeCode=request.getParameter("employeeCode");
			String lastName=request.getParameter("lastName");
			String firstName=request.getParameter("firstName");
			String lastKanaName=request.getParameter("lastKanaName");
			String firstKanaName=request.getParameter("firstKanaName");
			int gender=Integer.parseInt(request.getParameter("gender"));
			String birthDay=request.getParameter("birthDay");
			String sectionCode=request.getParameter("sectionCode");
			String hireDate=request.getParameter("hireDate");
			String mailPass = request.getParameter("mailPass");

			//タグ対策
			lastName = ChangeStr.changeStr(lastName);
			firstName = ChangeStr.changeStr(firstName);
			lastKanaName = ChangeStr.changeStr(lastKanaName);
			firstKanaName = ChangeStr.changeStr(firstKanaName);
			mailPass = ChangeStr.changeStr(mailPass);

			//SQL文
			String sql = "update m_employee set last_name= ?,first_name= ?,last_kana_name= ?,"
						+" first_kana_name= ?,gender= ?,birth_day= ?,section_code= ?,hire_date= ?, mail_pass= ? where employee_code=? ;";

			try {
				dao.dbConnect();
				PreparedStatement ps=dao.createPst(sql);

				//プレースホルダへの値の設置
				ps.setString(1, lastName);//last_name
				ps.setString(2, firstName);//first_name
				ps.setString(3, lastKanaName);//last_kana_name
				ps.setString(4, firstKanaName);//first_kana_name
				ps.setInt(5, gender);//gender
				ps.setString(6, birthDay);//birth_day
				ps.setString(7, sectionCode);//section_code
				ps.setString(8, hireDate);//hire_date
				ps.setString(9, mailPass);//mail_pass
				ps.setString(10, employeeCode);//employee_code

				//SQLステートメントの実行
				flag=ps.executeUpdate();


			} catch (SQLException e) {
				response.sendRedirect("employeeEdit.jsp");
			}catch (Exception e) {
				response.sendRedirect("employeeEdit.jsp");
			}finally {
				dao.dbDiscon();
			}


			if(flag!=0) {
				response.sendRedirect("employeeEditComp.jsp");
			}else {
				response.sendRedirect("employeeEditError.jsp");
			}

		}else {
			response.sendRedirect("login.jsp");
		}
	}

	/**
	 * @param request - クライアントが Servlet へ要求したリクエスト内容を含む HttpServletRequest オブジェクト
	 * @param response - Servlet がクライアントに返すレスポンス内容を含む HttpServletResponse オブジェクト
	 * @throws java.io.IOException - Servlet が POST リクエストを処理している間に入出力エラーが発生した場合
	 * @throws ServletException - POST に相当するリクエストが処理できない場合
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
