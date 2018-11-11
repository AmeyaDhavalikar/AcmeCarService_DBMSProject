
public class Login {
	private String user_id, password;

	public void set_user_id(String uid)
	{
		this.user_id = uid;
	}
	
	public void set_password(String pwd)
	{
		this.password = pwd;
	}
	
	public String get_user_id()
	{
		return user_id;
	}
	
	public String get_password()
	{
		return password;
	}
	
	public int validate_login()
	{
		//write code to query mgr, recep, cust table to find mayching user id and pwd
		
		int role =1;
		String uid, pwd;
		uid = get_user_id();
		pwd = get_password();
		System.out.println(uid +" "+pwd);
		return role;
		}//method validate_login
	
}//class
