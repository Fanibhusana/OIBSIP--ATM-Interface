// TASK 3 ATM INTERFACE


package mysql;

import static  java.lang.System.out;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ATM_Interface {
	static int  pin=12345;
	static Scanner sc=new Scanner(System.in);
	static void atm() throws SQLException {
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","Fani@2002");
		out.println("-----------------------------------------------------------------");
		out.println(""" 
				Enter your Choice
				
					0- Balance Check
					1- Transaction History
					2- Withdraw
					3- Deposit
					4- Transfer
					5- Quit""");
		int a=sc.nextInt();
		switch(a) {
		case 0->{ int dep=0,with=0;
			Statement stm1=con.createStatement();
			Statement stm11=con.createStatement();
			String s="select sum(deposit) from Fani";
			String s1="select sum(withdraw) from Fani";
			ResultSet rs=stm1.executeQuery(s);
			while(rs.next()) {
				
				int deposit=rs.getInt(1);
				dep=dep+deposit;
				
			}
			ResultSet rs1=stm11.executeQuery(s1);
			while(rs1.next()) {
				
				int withdraw=rs1.getInt(1);
				with=with+withdraw;
				
			}
			out.println("Your current balance is :"+(dep-with));
			out.println();
			out.println("Thank  You");
			}
		case 1->{
			Statement stm1=con.createStatement();
			String s="select * from Fani";
			ResultSet rs=stm1.executeQuery(s);
			out.println("Transaction History");
			while(rs.next()) {
				int no=rs.getInt("no");
				int deposit=rs.getInt("deposit");
				int withdraw=rs.getInt("withdraw");
				out.println(" -> "+no+" || deposit  "+deposit+" || withdraw : "+withdraw);
			}
			out.println();
			out.println("Thank  You");
			}
		case 2->{
			out.println("Enter amout");
			int a1=sc.nextInt();
			Statement stm1=con.createStatement();
			String s="insert into Fani(deposit,withdraw)values(0,"+a1+");";
			stm1.execute(s);
			out.println("Transaction Complited");
			out.println();
			out.println("Thank  You");
			}
		case 3->{
			out.println("Enter amout");
			int a1=sc.nextInt();
			Statement stm1=con.createStatement();
			String s="insert into Fani(deposit,withdraw)values("+a1+",0);";
			stm1.execute(s);
			out.println("Transaction Complited");
			out.println();
			out.println("Thank  You");
			}
		case 4->{
			out.println("Enter receiver name");
			String s=sc.next();
			out.println("Enter amout");
			int a1=sc.nextInt();
			Statement stm1=con.createStatement();
			String s1="insert into Fani(deposit,withdraw)values(0,"+a1+");";
			stm1.execute(s1);
			Statement stm11=con.createStatement();
			String s11="insert into "+s+"(deposit,withdraw)values("+a1+",0);";
			stm11.execute(s11);
			out.println("Transaction Complited");
			out.println();
			out.println("Thank  You");
			}
		case 5->{
			out.println("Thank  You using our ATM");System.exit(0);
				}
			}
		
		con.close();
	}
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		out.println("Welcome to ---_ SBI ATM _---");
		out.println();
		out.println();
		int times=3;
		out.println("Enter ID and PIN ");
		String ID=sc.next();
		int PIN=sc.nextInt();
		
		if(pin!=PIN){
			while(true) {
				times--;
				out.println("Wrong PIN");
				out.println("Re-enter PIN (Attempts Remaining : "+times+")");
				PIN=sc.nextInt();
				if(pin==PIN) break;
				if(times==1) {
					out.println("You have  exceeds your trials, Retry After sometimes !");
					System.exit(0);
				}
			}
		}
		if(pin==PIN)
		{
			
			out.println("Welcome "+ID);
			while(true) {atm();}
		}
	}

}
