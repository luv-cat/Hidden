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

/**
 * Servlet implementation class ReplyMsg
 *
 * @author <hidden>
 */
@WebServlet("/ReplyMsg")
public class ReplyMsg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * メッセージの返信を制御する。
	 * @param request - クライアントが Servlet へ要求したリクエスト内容を含む HttpServletRequest オブジェクト
	 * @param response - Servlet がクライアントに返すレスポンス内容を含む HttpServletResponse オブジェクト
	 * @throws java.io.IOException - Servlet が GET リクエストを処理している間に入出力エラーが発生した場合
	 * @throws ServletException - GET に相当するリクエストが処理できない場合
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		String msgBody = request.getParameter("msgBody");
		int msgId = Integer.parseInt(request.getParameter("msgId"));

		MessageDAO MsgDAO = MessageDAO.getInstance();
		try {
			MsgDAO.dbConnect();
			MsgDAO.createSt();
			//　メッセージIDから受信者の従業員コードを呼び出す
			String returnId = (MsgDAO.returnEmpCode(msgId));

			String fullName = MsgDAO.returnCreate(returnId);

			//　返信先が退職済みの社員であるメッセージを代入
			if(fullName==null) {
				RequestDispatcher rd = request.getRequestDispatcher("retirementMsg.jsp");
				rd.forward(request, response);
				return;
			}

			session.setAttribute("msgBody", msgBody);
			session.setAttribute("fullName", fullName);
			session.setAttribute("returnId", returnId);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MsgDAO.dbDiscon();
		}
		RequestDispatcher rd = request.getRequestDispatcher("replyMessage.jsp");
		rd.forward(request, response);

	}

}
