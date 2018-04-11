import java.util.*;
class subnetdivide{
		static String ip,subnet;
		static int ipadd[],submask[],notsubnet[],n[],p2[],first,last,subnetbit,i,j,k,num,total=0;
		static void nextdivision(){
				for(k=1;k<num;k++){
					System.out.println("---------------------------------------------------");
						System.out.println("\n\nFOR "+(k+1)+" DIVISION");
				subnetgenerate(k);
				System.out.print("\nThe Subnet mask is:\t");
				for(i=0;i<3;i++){
						System.out.print(submask[i]+".");	
					}
				System.out.println(submask[i]);
				
				System.out.print("\n\nThe Network IP address is:\t");
				for(i=0;i<3;i++){		
						System.out.print(ipadd[i]+".");	
					}
				System.out.println(ipadd[i]+"/"+p2[k]);
				System.out.print("\nThe First valid host IP address is:");
				for(i=0;i<3;i++){
						System.out.print(ipadd[i]+".");
					}
				System.out.println((ipadd[i]+1));
				for(i=0;i<4;i++){
						String a=Integer.toBinaryString(~submask[i]);
						a=a.substring(24);
						notsubnet[i]=Integer.parseInt(a,2);
					}
				System.out.print("\n\nThe Broadcast IP address is:\t");
				for(i=0;i<4;i++){		
						ipadd[i]=notsubnet[i]|ipadd[i];
					}
				for(i=0;i<3;i++){		
						System.out.print(ipadd[i]+".");
					}
				System.out.println(ipadd[i]+"/"+p2[k]);
				System.out.print("\nThe Last valid host IP address is:");
				for(i=0;i<3;i++){
						System.out.print(ipadd[i]+".");
					}
				System.out.println((ipadd[i]-1));
				increment();
				}
			}
		static void increment(){
				for(i=3;i>0;i++){
						if(ipadd[i]<255){
							ipadd[i]++;
							break;
							}
						else{
								ipadd[i-1]++;
								ipadd[i]=0;
								break;
							}
					}
			}
		static void powertwo(){
				p2=new int[num];
				int count;
				for(i=0;i<num;i++){
						count=0;
						while(n[i]>1){
								n[i]=n[i]/2;
								count++;
							}
						p2[i]=32-count;
					}
			}
		static void subnetgenerate(int lvl){
			subnet="";
				for(i=0;i<p2[lvl];i++){
						subnet=subnet.concat("1");
						
					}
				for(i=p2[lvl];i<32;i++){
						subnet=subnet.concat("0");
					}
				first=0;
				last=8;
				for(i=0;i<4;i++){
						submask[i]=Integer.parseInt(subnet.substring(first,last),2);
						first+=8;
						last+=8;	
					}
			}
		static void calculate(){
			System.out.println("---------------------------------------------------");
				System.out.println("\n\nFOR 1 DIVISION");
				
				System.out.print("\nThe Subnet mask is:\t");
				for(i=0;i<3;i++){
						System.out.print(submask[i]+".");	
					}
				System.out.println(submask[i]);
				
				System.out.print("\nThe Network IP address is:\t");
				for(i=0;i<3;i++){		
						System.out.print((submask[i]&ipadd[i])+".");	
					}
				System.out.println((submask[i]&ipadd[i])+"/"+p2[0]);
				System.out.print("\nThe First valid host IP address is:\t");
				for(i=0;i<3;i++){
						System.out.print((submask[i]&ipadd[i])+".");
					}
				System.out.println(((submask[i]&ipadd[i])+1));
				for(i=0;i<4;i++){
						String s=Integer.toBinaryString(~submask[i]);
						s=s.substring(24);
						notsubnet[i]=Integer.parseInt(s,2);
					}
				System.out.print("\nThe Broadcast IP address is:\t");
				for(i=0;i<4;i++){		
						ipadd[i]=notsubnet[i]|ipadd[i];
					}
				for(i=0;i<3;i++){		
						System.out.print(ipadd[i]+".");
					}
				System.out.println(ipadd[i]+"/"+p2[0]);
				System.out.print("\nThe Last valid host IP address is:\t");
				for(i=0;i<3;i++){
						System.out.print(ipadd[i]+".");
					}
				System.out.println((ipadd[i]-1));
				increment();
				nextdivision();
			}
		public static void main(String args[]){
				Scanner sc=new Scanner(System.in);
				System.out.println("Enter the IP address of the network:");
				ip=sc.next();

				System.out.println("Enter the number of subnets required:");
				num=sc.nextInt();
				n=new int[num];
				System.out.println("Enter the number of addresses in:");
				for(i=0;i<num;i++){
						System.out.println((i+1)+" subnet");
						n[i]=sc.nextInt();
						total=total+n[i];
					}
				powertwo();
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
				System.out.println();
				if(total<=(Math.pow(2,subbit)))
				{
					subnetgenerate(0);
					calculate();
				}
				else{
						System.out.println("Number of addresses required are more than available addresses!");
					}
				System.out.println("\n\nRemaining addresses are:"+((Math.pow(2,subbit)-total)));
			}
	}