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

import dao.MessageDAO;
import model.MessageBean;

/**
 * Servlet implementation class SenderList
 *
 * @author <hidden>
 */
@WebServlet("/SenderList")
public class SenderList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * メッセージを作成を制御する。
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

		MessageDAO MsgDAO = MessageDAO.getInstance();
		try {
		MsgDAO.dbConnect();
		List<MessageBean> senderList = MsgDAO.senderCreate();
		session.setAttribute("senderList", senderList);
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			MsgDAO.dbDiscon();
		}
		RequestDispatcher rd = request.getRequestDispatcher("MessageWrite.jsp");
		rd.forward(request, response);
	}

	/**
	 * @param request - クライアントが Servlet へ要求したリクエスト内容を含む HttpServletRequest オブジェクト
	 * @param response - Servlet がクライアントに返すレスポンス内容を含む HttpServletResponse オブジェクト
	 * @throws java.io.IOException - Servlet が POST リクエストを処理している間に入出力エラーが発生した場合
	 * @throws ServletException - POST に相当するリクエストが処理できない場合
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
