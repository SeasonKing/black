package filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import service.LoginService;
import service.LoginServiceImpl;

/**
 * Servlet Filter implementation class CookieFilter
 */
@WebFilter("/CookieFilter")
public class CookieFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CookieFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		//将数据进行类型转换 转换为HttpServletRequest型
		HttpServletRequest request=(HttpServletRequest) arg0;
		HttpServletResponse response=(HttpServletResponse) arg1;
		//获取路径
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		//声明Cookie对象
		Cookie loginInfo= null;
		//获取全部cookie对象
		Cookie[] cookies =request.getCookies();
		//判断cookie是否为空
		if(cookies !=null)
		{
			//循环遍历数组
			for(Cookie cookie:cookies)
			{
				//判断数组中是否有与loginInfo同名 的cookie数据
				if("loginInfo".equals(cookie.getName())){
					//若存在同名数据将值赋给loginInfo
					loginInfo=cookie;
					break;
				}
			}
		
		
		if(loginInfo !=null){
			//获取名为loginInfo的cookie对象 的内存
			String info=loginInfo.getValue();//1_1
			//对info 进行拆分
			String username=info.split("_")[0];
			String password=info.split("_")[1];
			
			LoginService ud=new LoginServiceImpl();
			HttpSession session=request.getSession();
			User user=ud.login(URLDecoder.decode(username,"utf-8"),password);
			//如果user不为null 说明登录成功，将user对象存入session中备用
			if(user !=null){
				session.setAttribute("user", user);
				response.sendRedirect("servlet/FindAllServlet");
			}
		}else{
			chain.doFilter(request, response);
		}
	} 
		else{
		chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
