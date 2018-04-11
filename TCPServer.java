import java.io.*;
import java.util.*;
import java.net.*;

public class TCPServer {
	public static void main(String[] args){
		DataInputStream dis=null;
		DataOutputStream dout=null;
		Scanner sc=new Scanner(System.in);
		try{
			ServerSocket ss=new ServerSocket(22222);
			Socket s=ss.accept();
			dis=new DataInputStream(s.getInputStream());
			dout=new DataOutputStream(s.getOutputStream());
			for(int i=0;i<5;i++){	
				String	str=(String)dis.readUTF();
				System.out.println("Saumil:"+str); 
				System.out.println("Enter Reply:");
				String reply=sc.nextLine();
				dout.writeUTF(reply);
				dout.flush();
			}
			dout.close();
			ss.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
