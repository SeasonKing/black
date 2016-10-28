package service;

import bean.User;



/**
 * 登录注册服务接口
 * @author 孙继杰 2016-08-25
 *
 */
public interface LoginService {

	/**
	 * 注册用户
	 * @param user 注册的用户信息
	 * @return true-注册成功  false-注册失败
	 */
	int register(User user);
	
	/**
	 * 用户登录
	 * @param user 登录的用户信息
	 * @return 0-登录成功  1-该用户不存在  2-该用户密码不正确
	 */
	int login(User user);
	/**
	 * 用户登录
	 * @param username需要传入用户名 与密码
	 * @param password
	 * @return 登录成功返回当前登录用户对象
	 */
	
	User login(String username,String password);
	
	
	
}
