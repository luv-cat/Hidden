package servlet;

/*2019/03/05
 * auther:<hidden>
 * 削除サーブレット
 *
 * */

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDAO;
import model.User;

/**
 * Servlet implementation class DeleteCheck
 *
 * @author <hidden>
 */
@WebServlet("/DeleteCheck")
public class DeleteCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 従業員情報を削除する。
	 * @param request - クライアントが Servlet へ要求したリクエスト内容を含む HttpServletRequest オブジェクト
	 * @param response - Servlet がクライアントに返すレスポンス内容を含む HttpServletResponse オブジェクト
	 * @throws java.io.IOException - Servlet が GET リクエストを処理している間に入出力エラーが発生した場合
	 * @throws ServletException - GET に相当するリクエストが処理できない場合
	 * @throws SQLException - 削除処理が行われなかった場合
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//やってきたデータをエンコ
		response.setContentType("text/html;charset=UTF-8");
		//送るデータをエンコ

		//鍵用意
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user == null) {
			response.sendRedirect("login.jsp");
		} else {
			EmployeeDAO dao = EmployeeDAO.getInstance();
			try {
				//DAOインスタンスを取得

				dao.dbConnect();
				boolean deleteFlag = false;
				String id = request.getParameter("id");

				//deleteが成功した場合true
				deleteFlag = dao.delete(id);

				//deleteが失敗した場合
				if (deleteFlag == false) {
				}
				dao.dbDiscon();
				response.sendRedirect("employeeDeleteComp.jsp");

			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				response.sendRedirect("employeeDeleteError.jsp");
			}
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
