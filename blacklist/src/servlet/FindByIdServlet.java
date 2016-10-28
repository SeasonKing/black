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
        //获取前台传来的名为id的参数
		String sid=request.getParameter("id");
		 
	//实例化UserService 对象 ,UserService接口里有findById的方法
		//由于接口不能new 所以调用UserServiceImpl实现类
 		UserService userservice=new UserServiceImpl();
 		
		//通过对象调findbyid 方法 ，并将String 类型sid强制转换为Int类型的id
 		//fandById方法返回User对象
		User user=userservice.findById(Integer.parseInt(sid));
		
		//将 user对象 存到"User"名字中，准备给页面调用
		request.setAttribute("user", user);
		//跳转页面到update.jsp
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
