package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import service.UserService;
import service.UserServiceImpl;

public class FindByIdServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public FindByIdServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	           doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        //��ȡǰ̨��������Ϊid�Ĳ���
		String sid=request.getParameter("id");
		 
	//ʵ����UserService ���� ,UserService�ӿ�����findById�ķ���
		//���ڽӿڲ���new ���Ե���UserServiceImplʵ����
 		UserService userservice=new UserServiceImpl();
 		
		//ͨ�������findbyid ���� ������String ����sidǿ��ת��ΪInt���͵�id
 		//fandById��������User����
		User user=userservice.findById(Integer.parseInt(sid));
		
		//�� user���� �浽"User"�����У�׼����ҳ�����
		request.setAttribute("user", user);
		//��תҳ�浽update.jsp
		request.getRequestDispatcher("../update.jsp").forward(request, response);
	 
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
