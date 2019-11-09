package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MessageDAO;
import model.EmployeeBean;
import model.MessageBean;


/**
 * Servlet implementation class MessageBox
 *
 * @author <hidden>
 */
@WebServlet("/MessageBox")
public class MessageBox extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * メッセージの情報を格納する。
	 * @param request - クライアントが Servlet へ要求したリクエスト内容を含む HttpServletRequest オブジェクト
	 * @param response - Servlet がクライアントに返すレスポンス内容を含む HttpServletResponse オブジェクト
	 * @throws java.io.IOException - Servlet が GET リクエストを処理している間に入出力エラーが発生した場合
	 * @throws ServletException - GET に相当するリクエストが処理できない場合
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @param request - クライアントが Servlet へ要求したリクエスト内容を含む HttpServletRequest オブジェクト
	 * @param response - Servlet がクライアントに返すレスポンス内容を含む HttpServletResponse オブジェクト
	 * @throws java.io.IOException - Servlet が POST リクエストを処理している間に入出力エラーが発生した場合
	 * @throws ServletException - POST に相当するリクエストが処理できない場合
	 * @throws SQLException - DB処理が正しく行われなかった場合
	 * @throws NullPointerException - 取得情報がnullの場合
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		EmployeeBean mb = null;
		boolean sendBoxFlag = false;
		List<MessageBean> sendList = new ArrayList<MessageBean>();
		List<MessageBean> sendReplaceList = new ArrayList<MessageBean>();
		List<MessageBean> recieveList = new ArrayList<MessageBean>();
		List<MessageBean> recieveReplaceList = new ArrayList<MessageBean>();

		//鍵用意
		HttpSession session = request.getSession();

		mb = (EmployeeBean) session.getAttribute("empInstance");
		Object obj= session.getAttribute("sendBoxFlag");
		if(obj!=null) {
			sendBoxFlag = (boolean)obj;
		}

		//インスタンス生成
		MessageDAO dao = MessageDAO.getInstance();

		try {
			dao.dbConnect();

			if (mb != null) {
				sendList = dao.getSendMessage(mb.getEmployeeCode());
				sendReplaceList=dao.getReplaceSendMessage(mb.getEmployeeCode());
				recieveList = dao.getRecieveMessage(mb.getEmployeeCode());
				recieveReplaceList=dao.getReplaceRecMess(mb.getEmployeeCode());
			}

		} catch (SQLException e) {
			e.getStackTrace();
			response.sendRedirect("MessageLogin.jsp");
		} catch (NullPointerException e) {
			e.getStackTrace();
			response.sendRedirect("MessageLogin.jsp");
		} catch (Exception e) {
			e.getStackTrace();
			response.sendRedirect("MessageLogin.jsp");
		} finally {
			dao.dbDiscon();
		}

		if (mb != null) {
			session.setAttribute("sendList", sendList);
			session.setAttribute("recieveList", recieveList);
			session.setAttribute("recieveReplaceList", recieveReplaceList);
			session.setAttribute("sendReplaceList", sendReplaceList);
			if(!sendBoxFlag) {
				response.sendRedirect("MessageMenu.jsp");
			}else {
				response.sendRedirect("SendMessage.jsp");
			}
		} else {
			response.sendRedirect("MessageLogin.jsp");
		}
	}

}
