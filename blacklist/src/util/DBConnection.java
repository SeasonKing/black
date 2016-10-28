package util;
//项目路径F:\apache-tomcat-7.0.70-windows-x86\apache-tomcat-7.0.70\me-webapps

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接工具类
 * @author Administrator
 *
 */
public class DBConnection {
	//数据库驱动名称
	private static final String DRIVER_NAME="com.mysql.jdbc.Driver";
	//数据库的url地址
	private static String url="jdbc:mysql://localhost:3306/login";
	//数据库登陆的用户名称
	private static String username="root";
	//数据库登陆的用户密码
	private static String password="1234";
	/**
	 * 创建数据库连接对象
	 * 
	 * @return连接对象
	 */
	public static Connection createConnextion(){
		Connection conn = null;
		try {
			//加载驱动类
			Class.forName(DRIVER_NAME);
			//创建数据库的连接对象
			conn=DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public static void main(String[] args) {
		Connection conn=createConnextion();
	}
}
