package dao;

import java.util.List;

import bean.User;


public interface UserDao {

	/**
	 * 添加用户
	 * @param user
	 */
	void addUser(User user);
	/**
	 * 删除用户
	 * @param id
	 */
	void deleteUser(int id);
	/**
	 * 更改信息
	 * @param user
	 */
	void updateUser(User user);
	/**
	 * 查找用户
	 * @param id 用的id
	 * @return 该用户对象
	 */
	User finById(int id);
	/**
	 * 通过用户名称查找用户
	 * @param name 用户名称
	 * @return
	 */
	User finByName(String name);
	
	/**
	 * 查找全部
	 * @return
	 */
	List findAll();
	
	/**
	 * 查看黑名单用户
	 * @return 返回list集合
	 */
	List findBlackList();

	/**
	 * 修改人员状态
	 * @param user
	 */
	void updateStatus(User user);
	
	/**
	 * 传入用户名与密码
	 * @param username
	 * @param password
	 * @return 返回当前登录用户
	 */
	User login(String username ,String password);
	
	
}
 