package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDAO;
import model.User;

/**
 * Servlet implementation class LoginCheck
 *
 * @author <hidden>
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ログインする。
	 * @param request - クライアントが Servlet へ要求したリクエスト内容を含む HttpServletRequest オブジェクト
	 * @param response - Servlet がクライアントに返すレスポンス内容を含む HttpServletResponse オブジェクト
	 * @throws java.io.IOException - Servlet が GET リクエストを処理している間に入出力エラーが発生した場合
	 * @throws ServletException - GET に相当するリクエストが処理できない場合
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//鍵用意
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("login.jsp");
		} else {
			response.sendRedirect("menu.jsp");
		}
	}

	/**
	 * @param request - クライアントが Servlet へ要求したリクエスト内容を含む HttpServletRequest オブジェクト
	 * @param response - Servlet がクライアントに返すレスポンス内容を含む HttpServletResponse オブジェクト
	 * @throws java.io.IOException - Servlet が POST リクエストを処理している間に入出力エラーが発生した場合
	 * @throws ServletException - POST に相当するリクエストが処理できない場合
	 * @throws Exception - 情報取得処理が行われなかった場合
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//鍵用意
		HttpSession session = request.getSession();

		//値の取得
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		//インスタンス生成
		EmployeeDAO dao = EmployeeDAO.getInstance();

		//初期化
		User user = null;

		try {
			dao.dbConnect();
			user = dao.LoginCheck(userId, password);
		} catch (Exception e) {
			e.getStackTrace();
		}finally {
			dao.dbDiscon();
		}
		session.setAttribute("user", user);
		response.sendRedirect("menu.jsp");

	}
}