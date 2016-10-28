package bean;
/**
 * 创建用户实体类
 * @author ccy 16-08-24
 *
 */
public class User {

	private int id; //id
	private String name;//用户名称
	private String password;//用户密码
	private String email;//用户邮箱
	private int status;//黑名单
	
	public User (){
		
	}
	
	
	

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", email=" + email + ", status=" + status + "]";
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public int getStatus() {
		return status;
	}




	public void setStatus(int status) {
		this.status = status;
	}


	public User(int id, String name, String password, String email, int status) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.status = status;
	}




	
	
	
}