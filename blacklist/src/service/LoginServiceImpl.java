package service;

import java.sql.Connection;

import util.DBConnection;
import bean.User;
import dao.UserDao;
import dao.UserDaoImpl;


/**
 * 登录注册实现类
 * @author 孙继杰 2016-08-25
 *dao 
 */
public class LoginServiceImpl implements LoginService {

	private UserDao userDao;//用户Dao
	
	public LoginServiceImpl(){
		Connection conn=DBConnection.createConnextion();
		userDao=new UserDaoImpl(conn);
	}
	
	@Override
	public int register(User user) {
		// TODO Auto-generated method stub
		//判断该用户是否存在
		User us=userDao.finByName(user.getName());
		if(us==null){
			userDao.addUser(user);
			return 0;
		}
		System.out.println("该用户"+user.getName()+"存在");
		return 1;
	}

	@Override
	public int login(User user) {
		// TODO Auto-generated method stub
		//判断用户是否存在
		User us=userDao.finByName(user.getName());
		if(us==null){
			System.out.println("该用户"+user.getName()+"不存在");
			return 1;
		}
		else{
			//判断用户的密码是否相同
			if(us.getPassword().equals(user.getPassword()))
			{
				return 0;
				
			}
			else{
				System.out.println("该用户"+user.getName()+"密码不对");
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
