package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDAO;
import model.EmployeeBean;
import model.User;

/**
 * Servlet implementation class showAllList
 *
 * @author <hidden>
 */
@WebServlet("/ShowAllList")

public class ShowAllList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 従業員の一覧を表示する。
	 * @param request - クライアントが Servlet へ要求したリクエスト内容を含む HttpServletRequest オブジェクト
	 * @param response - Servlet がクライアントに返すレスポンス内容を含む HttpServletResponse オブジェクト
	 * @throws java.io.IOException - Servlet が GET リクエストを処理している間に入出力エラーが発生した場合
	 * @throws ServletException - GET に相当するリクエストが処理できない場合
	 * @throws SQLException - DB処理が正しく行われなかった場合
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");
		//ログイン情報を保持しているかの確認
		if (user == null) {
			response.sendRedirect("login.jsp");
		} else {
			EmployeeDAO empDao = EmployeeDAO.getInstance();
			try {
				empDao.dbConnect();
				empDao.createSt();
				List<EmployeeBean> empList = empDao.showAllList();
				session.setAttribute("empList", empList);
			} catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				empDao.dbDiscon();
			}
			RequestDispatcher rd = request.getRequestDispatcher("employeeDisplay.jsp");
			rd.forward(request, response);
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
	}

}
