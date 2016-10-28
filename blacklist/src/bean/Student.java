package bean;

import java.util.Date;

/**
 * @author 孙继杰 2016-09-01
 * 创建学生实体类
 */
public class Student {

	private int id;//主键
	
	private String name;//学生姓名
	
	private int sex;//性别 0-女  1-男
	
	private String code; //学生编码
	
	private Date birth;//出生日期
	
	private int clasz_id;//班级id
	
	
	public Student()
	{
		
	}

	public Student(int id, String name, int sex, String code, Date birth,
			int clasz_id) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.code = code;
		this.birth = birth;
		this.clasz_id = clasz_id;
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

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public int getClasz_id() {
		return clasz_id;
	}

	public void setClasz_id(int clasz_id) {
		this.clasz_id = clasz_id;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", code=" + code + ", birth=" + birth + ", clasz_id="
				+ clasz_id + "]";
	}
	
	
}
