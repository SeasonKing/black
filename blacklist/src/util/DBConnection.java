package util;
//��Ŀ·��F:\apache-tomcat-7.0.70-windows-x86\apache-tomcat-7.0.70\me-webapps

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ���ݿ����ӹ�����
 * @author Administrator
 *
 */
public class DBConnection {
	//���ݿ���������
	private static final String DRIVER_NAME="com.mysql.jdbc.Driver";
	//���ݿ��url��ַ
	private static String url="jdbc:mysql://localhost:3306/login";
	//���ݿ��½���û�����
	private static String username="root";
	//���ݿ��½���û�����
	private static String password="1234";
	/**
	 * �������ݿ����Ӷ���
	 * 
	 * @return���Ӷ���
	 */
	public static Connection createConnextion(){
		Connection conn = null;
		try {
			//����������
			Class.forName(DRIVER_NAME);
			//�������ݿ�����Ӷ���
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
