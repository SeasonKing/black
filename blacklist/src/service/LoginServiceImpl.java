package service;

import java.sql.Connection;

import util.DBConnection;
import bean.User;
import dao.UserDao;
import dao.UserDaoImpl;


/**
 * ��¼ע��ʵ����
 * @author ��̽� 2016-08-25
 *dao 
 */
public class LoginServiceImpl implements LoginService {

	private UserDao userDao;//�û�Dao
	
	public LoginServiceImpl(){
		Connection conn=DBConnection.createConnextion();
		userDao=new UserDaoImpl(conn);
	}
	
	@Override
	public int register(User user) {
		// TODO Auto-generated method stub
		//�жϸ��û��Ƿ����
		User us=userDao.finByName(user.getName());
		if(us==null){
			userDao.addUser(user);
			return 0;
		}
		System.out.println("���û�"+user.getName()+"����");
		return 1;
	}

	@Override
	public int login(User user) {
		// TODO Auto-generated method stub
		//�ж��û��Ƿ����
		User us=userDao.finByName(user.getName());
		if(us==null){
			System.out.println("���û�"+user.getName()+"������");
			return 1;
		}
		else{
			//�ж��û��������Ƿ���ͬ
			if(us.getPassword().equals(user.getPassword()))
			{
				return 0;
				
			}
			else{
				System.out.println("���û�"+user.getName()+"���벻��");
				return 2;
			}
		}
		
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		
		return userDao.login(username, password);
	}

}
