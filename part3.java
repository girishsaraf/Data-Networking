import java.util.*;
class part3{
		static String ip,ipclass,octet,bitmaskpart,restpart,bst,rst;
		static int ipadd[],ipadd1[],submask[],num,i,j,k,l,first,last,total,subnetbit,bitmask,local,position;
		static int arrayindex,bit,rest;
		static void st(){
				bst="";
				rst="";
				for(i=0;i<bitmask;i++){
						bst=bst.concat("0");
					}
				for(i=bitmask;i<8;i++){
						rst=rst.concat("0");
					}
			}
		static void lastvalid() {
			ipadd1=new int[4];
			for(l=0;l<4;l++) {
				ipadd1[l]=ipadd[l];
			}
			for(l=3;l>=0;) {
				if(ipadd1[l]>0) {
					ipadd1[l]--;
					break;
				}
				else {
					ipadd1[l]=255;
					ipadd1[l-1]--;
					break;
				}
			}
			System.out.println("The Last valid host IP address is:");
			for(l=0;l<3;l++){		
				System.out.print(ipadd1[l]+".");	
			}
			System.out.println(ipadd1[l]);
		}
		static void firstvalid() {
			ipadd1=new int[4];
			for(l=0;l<4;l++) {
				ipadd1[l]=ipadd[l];
			}
			for(l=3;l>=0;) {
				if(ipadd1[l]<255) {
					ipadd1[l]++;
					break;
				}
				else {
					ipadd1[l]=0;
					ipadd1[l-1]++;
					break;
				}
			}
			System.out.println("The First valid host IP address is:");
			for(l=0;l<3;l++){		
				System.out.print(ipadd1[l]+".");	
			}
			System.out.println(ipadd1[l]);
		}
		static void divide() {
			for(i=1;i<=num;i++) {
				System.out.println("\n-----------------------------------------------\n");
				System.out.println(i+" SUBNET:\n\n");
				bitmaskpart="";
				bitmaskpart=bitmaskpart.concat(Integer.toBinaryString(i));
				bitmaskpart=(bst + bitmaskpart).substring(bitmaskpart.length());
				restpart="";
				restpart=restpart.concat(Integer.toBinaryString(0));
				restpart=(rst + restpart).substring(restpart.length());
				octet=bitmaskpart.concat(restpart);
				ipadd[arrayindex]=Integer.parseInt(octet,2);
				System.out.println("The Network IP address is:");
				for(k=0;k<3;k++){		
					System.out.print(ipadd[k]+".");	
				}
				System.out.println(ipadd[k]);
				firstvalid();
				restpart="";
				restpart=restpart.concat(Integer.toBinaryString(total));
				restpart=(rst + restpart).substring(restpart.length());
				octet=bitmaskpart.concat(restpart);
				ipadd[arrayindex]=Integer.parseInt(octet,2);
				for(k=arrayindex+1;k<4;k++){
						ipadd[k]=255;
					}
				lastvalid();
				System.out.println("The Broadcast IP address is:");
				for(k=0;k<3;k++){		
					System.out.print(ipadd[k]+".");	
				}
				System.out.println(ipadd[k]);
				System.out.println("\n-----------------------------------------------\n");
				for(k=arrayindex+1;k<4;k++){
					ipadd[k]=0;
				}
			}
		}
		static void calculate() {
			System.out.println("For the Network:");
			System.out.print("\nThe First IP address of the network is:\t");
			for(i=0;i<4;i++){		
					ipadd[i]=submask[i]&ipadd[i];
				}
			for(i=0;i<3;i++){		
				System.out.print(ipadd[i]+".");	
			}
			System.out.println(ipadd[i]);
			divide();
		}
		static void calcbitmask() {
			bitmask=0;
			int count=1;
			local=num+2;
			while(count<local) {
				count=count*2;
				bitmask++;
			}
			System.out.println("Bitmask for the given number of subnets is:"+bitmask);
			System.out.println("\n-----------------------------------------------\n");
			for(k=0;k<8-bitmask;k++) {
					total=total+(int)Math.pow(2,k);
			}
			st();
		}
		static void calculatesubclass() {
			ipclass="";
			if((ipadd[0]>=1)&&(ipadd[0]<=126)) {
				submask[0]=255;
				submask[2]=submask[2]=submask[3]=0;
				ipclass=ipclass.concat("A");
				arrayindex=1;
			}
			if((ipadd[0]>=128)&&(ipadd[0]<=191)) {
				submask[0]=submask[1]=255;
				submask[2]=submask[3]=0;
				ipclass=ipclass.concat("B");
				arrayindex=2;
			}
			if((ipadd[0]>=192)&&(ipadd[0]<=223)) {
				submask[3]=0;
				submask[0]=submask[1]=submask[2]=255;
				ipclass=ipclass.concat("C");
				arrayindex=3;
			}
			System.out.println("The Subnet mask is:");
			for(i=0;i<3;i++) {
				System.out.print(submask[i]+".");
			}
			System.out.println(submask[i]);
			System.out.println("The class of given IP address is:"+ipclass);
		}
		public static void main(String args[]){
				Scanner sc=new Scanner(System.in);
				System.out.println("Enter the IP address of the network:");
				ip=sc.next();
				ipadd=new int[4];
				submask=new int[4];
				first=k=0;
				for(i=0;i<ip.length();i++) {
					if(ip.charAt(i)=='.') {
						ipadd[k]=Integer.parseInt(ip.substring(first,i));
						first=i+1;
						k++;
					}
				}
				ipadd[k]=Integer.parseInt(ip.substring(first));
				System.out.println("Enter the number of subnets required:");
				num=sc.nextInt();
				System.out.println("\n-----------------------------------------------\n");
				System.out.print("Entered IP address is:\t");
				for(i=0;i<3;i++){
					System.out.print(ipadd[i]+".");
					}
					System.out.println(ipadd[i]);
				calculatesubclass();
				calcbitmask();
				calculate();
			}
	}