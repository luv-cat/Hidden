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
 * Servlet implementation class MessageDelete
 *
 * @author <hidden>
 */
@WebServlet("/MessageDelete")
public class MessageDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * メッセージの削除を行う。
	 * @param request - クライアントが Servlet へ要求したリクエスト内容を含む HttpServletRequest オブジェクト
	 * @param response - Servlet がクライアントに返すレスポンス内容を含む HttpServletResponse オブジェクト
	 * @throws java.io.IOException - Servlet が GET リクエストを処理している間に入出力エラーが発生した場合
	 * @throws ServletException - GET に相当するリクエストが処理できない場合
	 * @see HttpServlet#HttpServlet()
	 */
	public MessageDelete() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//文　文字コード設定
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		//getParameterで、Message.jsp、SendMessage.jspからvalue取得
		//　受信メール一覧ページから飛んで来たらrecvMsgから取得、送信履歴ページから飛んで来たらsendMsgから取得する。
		String recv="",send="";
		recv=request.getParameter("recvMsg");
		send=request.getParameter("sendMsg");
		int recvMessageId =0;
		int sendMessageId = 0;
		if(recv!=null) {
			recvMessageId = Integer.parseInt(request.getParameter("recvMsg"));
		}
		if(send!=null) {
			sendMessageId = Integer.parseInt(request.getParameter("sendMsg"));
		}

		//int recvMessageId = Integer.parseInt(request.getParameter("recvMsg"));

		//int sendMessageId = Integer.parseInt(request.getParameter("sendMsg"));

		//flag宣言・代入
		boolean recvFlag = false;
		boolean sendFlag = false;
		//MessageDAOのインスタンス生成
		MessageDAO MsgDAO = MessageDAO.getInstance();

		try {
			//DB接続
			MsgDAO.dbConnect();
			//SQL文送信
			MsgDAO.createSt();
			//メ　該当するDAOのメソッドを呼び出し・boolean型戻り値代入
			recvFlag = MsgDAO.recvFlag(recvMessageId);
			sendFlag = MsgDAO.sendFlag(sendMessageId);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//受　reciever_flagをfalseに出来ればフォワードで完了画面へ
		if (recvFlag) {
			RequestDispatcher rd = request.getRequestDispatcher("RecvMsgComp.jsp");
			rd.forward(request, response);
			return;
		}//受　send_flagをfalseに出来ればフォワードで完了画面へ
		else if (sendFlag) {
			session.setAttribute("sendBoxFlag", true);
			RequestDispatcher rd = request.getRequestDispatcher("SendMsgComp.jsp");
			rd.forward(request, response);
			return;
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("SendMsgComp.jsp");
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
