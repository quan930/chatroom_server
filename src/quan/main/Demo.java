package quan.main;

import java.io.IOException;
import java.util.Scanner;

public class Demo {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("菜单");
		System.out.println("1:启动服务器");
		System.out.println("2:查看启动线程");
		System.out.println("3:结束服务器");
		QQServer s = new QQServer();
		while(true) {
			System.out.print("请输入:");
			int menu = in.nextInt();
			switch(menu) {
			case 1:
				System.out.print("请设置端口号:");
				int prot = in.nextInt();
				s.start(prot);
				break;
			case 2:
				s.numThreads();
				break;
			case 3:
				s.stop();
				return;
			default:
				break;
			}
		}
	}

}
