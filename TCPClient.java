import java.util.*;
import java.io.*;
import java.net.*;

public class TCPClient 
{
public static void main(String[] args) {
try
{	
Socket s=new Socket("localhost",22222);
Scanner sc=new Scanner(System.in);
String msg="";
DataOutputStream dout=null;
DataInputStream dis=null;
int i;

for(i=1;i<=5;i++)
{
dout=new DataOutputStream(s.getOutputStream());
dis=new DataInputStream(s.getInputStream());
System.out.println("Enter your message:");
msg=sc.nextLine();
dout.writeUTF(msg);
dout.flush();

String	str=(String)dis.readUTF();
System.out.println("Girish : "+str);

}
dout.close();
s.close();


}catch(Exception e){System.out.println(e);}
}
}

