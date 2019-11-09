
package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MessageDAO;
import model.EmployeeBean;

/**
 * Servlet implementation class MessageLoginCheck
 *
 * @author <hidden>
 */
@WebServlet("/MessageLoginCheck")
public class MessageLoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * メッセージフォームにログインする。
	 * @param request - クライアントが Servlet へ要求したリクエスト内容を含む HttpServletRequest オブジェクト
	 * @param response - Servlet がクライアントに返すレスポンス内容を含む HttpServletResponse オブジェクト
	 * @throws java.io.IOException - Servlet が GET リクエストを処理している間に入出力エラーが発生した場合
	 * @throws ServletException - GET に相当するリクエストが処理できない場合
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("MessageLogin.jsp");
	}

	/**
	 * @param request - クライアントが Servlet へ要求したリクエスト内容を含む HttpServletRequest オブジェクト
	 * @param response - Servlet がクライアントに返すレスポンス内容を含む HttpServletResponse オブジェクト
	 * @throws java.io.IOException - Servlet が POST リクエストを処理している間に入出力エラーが発生した場合
	 * @throws ServletException - POST に相当するリクエストが処理できない場合
	 * @throws SQLException - 情報取得処理が行われなかった場合
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		EmployeeBean mb = null;

		//鍵用意
		HttpSession session = request.getSession();

		//値の取得
		String employeeCode = request.getParameter("employeeCode");
		String mailPass = request.getParameter("mailPass");

		//インスタンス生成
		MessageDAO dao = MessageDAO.getInstance();

		try {
			dao.dbConnect();
			mb = dao.LoginCheck(employeeCode, mailPass);

		} catch (SQLException e) {
			e.getStackTrace();
			response.sendRedirect("MessageLogin.jsp");
		} catch (Exception e) {
			e.getStackTrace();
			response.sendRedirect("MessageLogin.jsp");
		} finally {
			dao.dbDiscon();
		}

		if (mb != null) {
			session.setAttribute("empInstance", mb);
			response.sendRedirect("MessageBox");
		} else {
			session.setAttribute("passErr", "passErr");
			response.sendRedirect("MessageLogin.jsp");
		}

	}
}
