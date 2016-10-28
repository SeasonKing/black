package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.LoginService;
import service.LoginServiceImpl;
import bean.User;



public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
		//��ȡ����Ĳ���
				String name=request.getParameter("username");
				String password=request.getParameter("password");
				
				//�����������װ��ʵ��bean��
				User user=new User();
				
				user.setName(name);
				user.setPassword(password);
				//������¼ �ķ��������
		LoginService service=new LoginServiceImpl();
		//�û����е�½
		int log=service.login(user);
		
		if(log==0){
			
			HttpSession session=request.getSession(true);
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(10);
			
			//���ʴ���
			ServletContext servletContext=this.getServletContext();
			String num=(String)servletContext.getAttribute("countnum");
			if(num==null){
				num="0";
			}
			
			int num1=Integer.parseInt(num)+1;
			servletContext.setAttribute("countnum", ""+num1);
			
			//cookie
			String radio=request.getParameter("remenber");
			System.out.println(radio);
			if(radio !=null){
				//��ȡcookie���� �����û���������浽cookie�� ��cookie��ΪloginInfo
				Cookie c=new Cookie("loginInfo",URLEncoder.encode(name,"utf-8")+"_"+ password);
				//����cookie���·��
				c.setPath("/blacklist");
				//cookie ����ʱ��   ��λ��
				c.setMaxAge(60*60*24);
				//��cookie ��ŵ��������
				response.addCookie(c);
			}
			
		     response.sendRedirect("FindAllServlet");
		}
		else{
			response.sendRedirect("../index.jsp?log_res="+log);
		}

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
