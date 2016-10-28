package service;

import java.util.List;

import bean.User;

/**
 * �û���Ϣ����
 * @author ��̽� 2016-08-29
 *
 */
public interface UserService {
	
	List findAll();
	
	List findBlackList();
	
	
	void updateStatus(User user);
	
	/**
	 * ͬid �����û�
	 * @param name
	 */
    User findById(int id);
    
    User findByName(String name);
    
     void updateUser(User user);
     void addUser(User user);
     void deleteUser(int id);
}
