package precodes;
import java.util.*;
class addressgen{
		static int i,j,first,last,k,subnetbit;
		static int ipadd[],notsubnet[],submask[],fa[];
		static String ip,subnet;
		static void string_to_array(){
				first=k=0;
				ipadd=new int[4];
				submask=new int[4];
				notsubnet=new int[4];
				for(i=0;i<ip.length();i++){
						if(ip.charAt(i)=='.'||ip.charAt(i)=='/'){
								ipadd[k]=Integer.parseInt(ip.substring(first,i));
								first=i+1;
								k++;
							}
						if(ip.charAt(i)=='/'){
								subnetbit=Integer.parseInt(ip.substring(i+1));
								break;
							}
					}
				System.out.print("Entered IP address is:\t");
				for(i=0;i<3;i++){
						System.out.print(ipadd[i]+".");
					}
					System.out.println(ipadd[i]);
				System.out.print("Number of addresses are:");
				int subbit;
				subnet="";
				subbit=32-subnetbit;
				System.out.print("\t"+(Math.pow(2,subbit)));
				for(i=0;i<subnetbit;i++){
						subnet=subnet.concat("1");
					}
				for(i=subnetbit;i<32;i++){
						subnet=subnet.concat("0");
					}
				first=0;
				last=8;
				for(i=0;i<4;i++){
						submask[i]=Integer.parseInt(subnet.substring(first,last),2);
						first+=8;
						last+=8;	
					}
				System.out.print("\nThe Subnet mask is:\t");
				for(i=0;i<3;i++){
						System.out.print(submask[i]+".");	
					}
				System.out.println(submask[i]);
				
				System.out.print("\nThe First IP address is:\t");
				for(i=0;i<3;i++){		
						System.out.print((submask[i]&ipadd[i])+".");	
					}
				System.out.println(submask[i]&ipadd[i]);
				for(i=0;i<4;i++){
						String s=Integer.toBinaryString(~submask[i]);
						s=s.substring(24);
						notsubnet[i]=Integer.parseInt(s,2);
					}
				System.out.print("\nThe Last IP address is:\t");
				for(i=0;i<3;i++){		
						System.out.print((notsubnet[i]|ipadd[i])+".");	
					}
				System.out.println(notsubnet[i]|ipadd[i]);
			} 
		public static void main(String args[]){
				System.out.println("Enter the IP address: (in /n format):");
				Scanner sc=new Scanner(System.in);
				ip=sc.next();
				addressgen a=new addressgen();
				a.string_to_array();
			}
	}