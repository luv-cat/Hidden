package servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
 * Servlet implementation class get
 *
 * @author <hidden>
 */
@WebServlet("/GetEmployeeData")
public class GetEmployeeData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 従業員情報を編集するための情報を受け取る。
	 * @param request - クライアントが Servlet へ要求したリクエスト内容を含む HttpServletRequest オブジェクト
	 * @param response - Servlet がクライアントに返すレスポンス内容を含む HttpServletResponse オブジェクト
	 * @throws java.io.IOException - Servlet が GET リクエストを処理している間に入出力エラーが発生した場合
	 * @throws ServletException - GET に相当するリクエストが処理できない場合
	 * @throws SQLException - 情報取得処理が行われなかった場合
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログイン済みかどうか確認するif文を挿入する。直接URLを叩かれた時のためのチェック
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		User user=(User)session.getAttribute("user");

		if(user!=null) {
			//SQL文
			String sql = "select * from m_employee where employee_code= ? ;";

			//EmployeeDisplayフォームからemployeeCodeを取得
			String employeeCode=request.getParameter("employeeCode");

			//変数の初期化宣言
			String employeeCode2 = null, lastName = null, firstName = null, lastKanaName = null, firstKanaName = null,
					birthDay = null, sectionCode = null, hireDate = null, mailPass = null;
			int gender = 0;
			EmployeeBean editData = null;
			boolean flag=false;
			EmployeeDAO dao=EmployeeDAO.getInstance();
			String judge = request.getParameter("judge");

			try {
				dao.dbConnect();
				dao.createPst(sql);
				PreparedStatement ps=dao.createPst(sql);
				//プレースホルダへの値の設置
				ps.setString(1, employeeCode);
				//SQLステートメントの実行
				ResultSet rs = ps.executeQuery();

				if(rs.next()) {
					flag=true;
					employeeCode2 = rs.getString(1);
					lastName = rs.getString(2);
					firstName = rs.getString(3);
					lastKanaName = rs.getString(4);
					firstKanaName = rs.getString(5);
					gender = rs.getInt(6);
					birthDay = rs.getString(7);
					sectionCode = rs.getString(8);
					hireDate = rs.getString(9);
					mailPass = rs.getString(11);
					editData = new EmployeeBean(employeeCode2, lastName, firstName, lastKanaName, firstKanaName, gender,
							birthDay, sectionCode, hireDate, mailPass);
				}

				session.setAttribute("editData", editData);

			} catch (SQLException e) {
				e.printStackTrace();
				response.sendRedirect("ShowAll");
			}catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("ShowAll");
			}finally {
				dao.dbDiscon();
			}

			if(flag) {
				if (judge.equals("0")) {
					response.sendRedirect("employeeEdit.jsp");
				} else if (judge.equals("1")) {
					response.sendRedirect("employeeDelete.jsp");
				}
			}else {
				response.sendRedirect("ShowAllList");
			}
		} else {
			response.sendRedirect("login.jsp");
		}
	}

	/**
	 * @param request - クライアントが Servlet へ要求したリクエスト内容を含む HttpServletRequest オブジェクト
	 * @param response - Servlet がクライアントに返すレスポンス内容を含む HttpServletResponse オブジェクト
	 * @throws java.io.IOException - Servlet が POST リクエストを処理している間に入出力エラーが発生した場合
	 * @throws ServletException - POST に相当するリクエストが処理できない場合
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
