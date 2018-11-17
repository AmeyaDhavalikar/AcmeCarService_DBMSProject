//import java.sql.*;
import java.io.*;

public class Main {

	public static void main(String[] args)throws IOException
	{
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		int main_menu_choice =0, login_menu_choice=0;
		int role;
		String uid, pwd;
		do
		{
			System.out.println("Welcome to Acme Car Service");
			System.out.println("\n 1. Login \n 2. Signup\n 3. Exit");
			System.out.println("Enter your choice : ");
		
			main_menu_choice = Integer.parseInt(buf.readLine());
		
			switch(main_menu_choice)
			{
				case 1 : 
						Login obj = new Login();
						System.out.println("1. Sign-In \n2. Go Back");
						System.out.println("Enter your choice :");
						login_menu_choice = Integer.parseInt(buf.readLine());
						//System.out.println();
						if(login_menu_choice == 1)
						{
							System.out.println("Enter user id : ");
							uid = buf.readLine();
							obj.set_user_id(uid);
							System.out.println("Got user id");
							System.out.println("Enter password : ");
							pwd = buf.readLine();
							obj.set_password(pwd);
							role = obj.validate_login();
							if(role == 1)
							{
								//Display Manager menu
								Manager mgr = new Manager(uid);
								mgr.show();							
							}
							else if (role == 2)
							{
								//display Receptionist menu
								//Receptionist r = new Receptionist(");
								//r.show();
							}
							else if (role == 3)
							{	//display Customer menu
								Customer c = new Customer(uid);
								c.show();
							}
							else
							{
								//no user found / or handling incorrect username/pwd
							}
						}
							break;
				case 2 :    SignUp s = new SignUp();
							s.signup_customer();
							break;
				case 3 :
							break;
				default :
							System.out.println("Invalid choice entered");
			}//switch
		}while(main_menu_choice!=3);
	}//main

}//class
