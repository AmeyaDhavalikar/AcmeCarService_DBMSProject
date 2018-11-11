import java.sql.*;
import java.util.Scanner;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*ResultSet rs = null;
		ConnectionToDatabase obj = new ConnectionToDatabase();
		rs = obj.read_db(); */
		Scanner scan = new Scanner(System.in);
		int main_menu_choice =0, login_menu_choice=0;
		int role;
		String uid, pwd;
		//do
		//{
			System.out.println("Welcome to Acme Car Service");
			System.out.println("\n 1. Login \n2. Signup\n 3. Exit");
			System.out.println("Enter your choice : ");
		
			main_menu_choice = scan.nextInt();
		
			switch(main_menu_choice)
			{
				case 1 : 
						Login obj = new Login();
						System.out.println("1. Sign-In \n2. Go Back");
						System.out.println("Enter your choice :");
						login_menu_choice = scan.nextInt();
						//System.out.println();
						if(login_menu_choice == 1)
						{
							System.out.println("Enter user id : ");
							uid = scan.nextLine();
							obj.set_user_id(uid);
							System.out.println("Got user id");
							System.out.println("Enter password : ");
							pwd = scan.nextLine();
							obj.set_password(pwd);
							role = obj.validate_login();
							if(role == 1)
							{
								//Display Manager menu
								Manager mgr = new Manager("56");
								mgr.show();							
							}
							else if (role == 2)
							{
								//display Receptionist menu
						//		Receptionist r = new Receptionist(user_id);
						//		r.show();
							}
							else if (role == 3)
							{	//display Customer menu
						//		Customer c = new Customer(user_id);
						//		c.show();
							}
							else
							{
								//no user found / or handling incorrect username/pwd
							}
						}
							break;
				case 2 :
							break;
				case 3 :
							break;
				default :
							System.out.println("Invalid choice entered");
			}//switch
		//}while(main_menu_choice!=3);
		/*try {
		while (rs.next()) {
		    String s = rs.getString("CENTER_ID");
		    String n = rs.getString("ZIPCODE");
		    System.out.println(s + "   " + n);
		}
		}catch(Throwable oops)
		{oops.printStackTrace();}//catch */
	}//main

}//class
