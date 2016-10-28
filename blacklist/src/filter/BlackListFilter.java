package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import service.UserService;
import service.UserServiceImpl;

/**
 * Servlet Filter implementation class BlackListFilter
 */
@WebFilter("/BlackListFilter")
public class BlackListFilter implements Filter {

    /**
     * Default constructor. 
     */
    public BlackListFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		// pass the request along the filter chain
		
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		
		String name=req.getParameter("username");
		UserService userService=new UserServiceImpl();
		User user =userService.findByName(name);
		if(user==null){
			resp.sendRedirect("../index.jsp?log_res="+2);
		}else if(user==null){
			resp.sendRedirect("../index.jsp?log_res="+1);
		}else if(user.getStatus()==1){
			resp.sendRedirect("../index.jsp?log_res="+3);
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
