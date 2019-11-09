package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDAO;
import model.ChangeStr;
import model.EmployeeBean;
import model.User;

/**
 * Servlet implementation class RegistCheck
 *
 * @author <hidden>
 */
@WebServlet("/RegistCheck")
public class RegistCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 従業員情報を新規登録する。
	 * @param request - クライアントが Servlet へ要求したリクエスト内容を含む HttpServletRequest オブジェクト
	 * @param response - Servlet がクライアントに返すレスポンス内容を含む HttpServletResponse オブジェクト
	 * @throws java.io.IOException - Servlet が GET リクエストを処理している間に入出力エラーが発生した場合
	 * @throws ServletException - GET に相当するリクエストが処理できない場合
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//鍵用意
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("user");
				if (user == null) {
					response.sendRedirect("login.jsp");
				} else {
					response.sendRedirect("employeeRegist.jsp");
				}
	}

	/**
	 * @param request - クライアントが Servlet へ要求したリクエスト内容を含む HttpServletRequest オブジェクト
	 * @param response - Servlet がクライアントに返すレスポンス内容を含む HttpServletResponse オブジェクト
	 * @throws java.io.IOException - Servlet が POST リクエストを処理している間に入出力エラーが発生した場合
	 * @throws ServletException - POST に相当するリクエストが処理できない場合
	 * @throws SQLException - 情報取得処理が行われなかった場合
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		EmployeeBean bean = new EmployeeBean();
		EmployeeDAO dao = EmployeeDAO.getInstance();
		boolean judge = false;

		String employeeCode = request.getParameter("employeeCode");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String lastKanaName = request.getParameter("lastKanaName");
		String firstKanaName = request.getParameter("firstKanaName");
		String mailPass = request.getParameter("mailPass");
		int gender = Integer.parseInt(request.getParameter("gender"));
		String birthDay = request.getParameter("birthDay");
		String sectionCode = request.getParameter("sectionCode");
		String hireDate = request.getParameter("hireDate");

		//タグ対策
		employeeCode = ChangeStr.changeStr(employeeCode);
		lastName = ChangeStr.changeStr(lastName);
		firstName = ChangeStr.changeStr(firstName);
		lastKanaName = ChangeStr.changeStr(lastKanaName);
		firstKanaName = ChangeStr.changeStr(firstKanaName);
		mailPass = ChangeStr.changeStr(mailPass);

		if (lastKanaName.equals("")) {
			lastKanaName = null;
		}
		if (firstKanaName.equals("")) {
			firstKanaName = null;
		}
		if (birthDay.equals("")) {
			birthDay = null;
		}
		if (hireDate.equals("")) {
			hireDate = null;
		}

		try {
			dao.dbConnect();
			dao.createSt();
			judge = dao.regist(employeeCode, lastName, firstName, lastKanaName, firstKanaName, gender, birthDay, sectionCode, hireDate, mailPass);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.dbDiscon();
		}

		if(judge) {
			response.sendRedirect("employeeRegistComp.jsp");
		} else {
			response.sendRedirect("employeeRegistError.jsp");
		}
	}

}
