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
		//�����ݽ�������ת�� ת��ΪHttpServletRequest��
		HttpServletRequest request=(HttpServletRequest) arg0;
		HttpServletResponse response=(HttpServletResponse) arg1;
		//��ȡ·��
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		//����Cookie����
		Cookie loginInfo= null;
		//��ȡȫ��cookie����
		Cookie[] cookies =request.getCookies();
		//�ж�cookie�Ƿ�Ϊ��
		if(cookies !=null)
		{
			//ѭ����������
			for(Cookie cookie:cookies)
			{
				//�ж��������Ƿ�����loginInfoͬ�� ��cookie����
				if("loginInfo".equals(cookie.getName())){
					//������ͬ�����ݽ�ֵ����loginInfo
					loginInfo=cookie;
					break;
				}
			}
		
		
		if(loginInfo !=null){
			//��ȡ��ΪloginInfo��cookie���� ���ڴ�
			String info=loginInfo.getValue();//1_1
			//��info ���в��
			String username=info.split("_")[0];
			String password=info.split("_")[1];
			
			LoginService ud=new LoginServiceImpl();
			HttpSession session=request.getSession();
			User user=ud.login(URLDecoder.decode(username,"utf-8"),password);
			//���user��Ϊnull ˵����¼�ɹ�����user�������session�б���
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
