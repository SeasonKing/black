package service;

import java.sql.Connection;
import java.util.List;

import bean.User;
import util.DBConnection;
import dao.UserDao;
import dao.UserDaoImpl;



public class UserServiceImpl implements UserService {


	private UserDao userDao;//用户Dao
	
	public UserServiceImpl(){
		Connection conn=DBConnection.createConnextion();
		userDao=new UserDaoImpl(conn);
	}
		
	
	
	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		
		List list =userDao.findAll();
		return list;
	}



	@Override
	public List findBlackList() {
		// TODO Auto-generated method stub
		
		return userDao.findBlackList();
	}



	@Override
	public void updateStatus(User user) {
		// TODO Auto-generated method stub
		userDao.updateStatus(user);
	}



	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return userDao.finById(id);
	}



	@Override
	public User findByName(String name) {
		// TODO Auto-generated method stub
		return userDao.finByName(name);
	}



	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		    userDao.updateUser(user);
		
	}



	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.addUser(user);
	}



	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		userDao.deleteUser(id);
	}

	}



	



	


