package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MessageDAO;
import model.ChangeStr;
import model.EmployeeBean;
import model.User;

/**
 * Servlet implementation class MessageCheck
 *
 * @author <hidden>
 */
@WebServlet("/MessageCheck")
public class MessageCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * メッセージの情報を確認する。
	 * @param request - クライアントが Servlet へ要求したリクエスト内容を含む HttpServletRequest オブジェクト
	 * @param response - Servlet がクライアントに返すレスポンス内容を含む HttpServletResponse オブジェクト
	 * @throws java.io.IOException - Servlet が GET リクエストを処理している間に入出力エラーが発生した場合
	 * @throws ServletException -GET に相当するリクエストが処理できない場合
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
	 * @throws SQLException - 情報取得処理が行われなかった場合
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		EmployeeBean emp = (EmployeeBean) session.getAttribute("empInstance");

		String myEmpCode = emp.getEmployeeCode();
		String comEmpCode = request.getParameter("returnId");
		String messageBody = request.getParameter("msgBody");

		messageBody = ChangeStr.changeStr(messageBody);

		MessageDAO MsgDAO = MessageDAO.getInstance();

		boolean flag = false;

		try {
			MsgDAO.dbConnect();
			MsgDAO.createSt();

			flag = MsgDAO.sendMessage(myEmpCode, comEmpCode, messageBody);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MsgDAO.dbDiscon();
		}

		if (flag) {
			RequestDispatcher rd = request.getRequestDispatcher("SendComp.jsp");
			rd.forward(request, response);
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("SendError.jsp");
			rd.forward(request, response);
		}

	}

}
