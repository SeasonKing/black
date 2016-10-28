package service;

import java.util.List;

import bean.User;

/**
 * 用户信息操作
 * @author 孙继杰 2016-08-29
 *
 */
public interface UserService {
	
	List findAll();
	
	List findBlackList();
	
	
	void updateStatus(User user);
	
	/**
	 * 同id 查找用户
	 * @param name
	 */
    User findById(int id);
    
    User findByName(String name);
    
     void updateUser(User user);
     void addUser(User user);
     void deleteUser(int id);
}
