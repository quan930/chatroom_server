package quan.main;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;
import quan.Protocol.QQProtocol;
import quan.method.*;

//线程执行
public class Chater implements Runnable {
	List<Chat>servers = new ArrayList<Chat>();
	private Socket server;
	private int threadId;
	private PrintStream log;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			OutputStream outServer = server.getOutputStream();
			InputStream inServer = server.getInputStream();
			Scanner in = new Scanner(inServer,"UTF-8");
			PrintWriter out = new PrintWriter(new OutputStreamWriter(outServer,"UTF-8"),true);
			log.print(new SimpleDateFormat("yyyy年MM月dd日\tHH时mm分ss秒").format(new Date())+"有一个客户端进行了连接\t\t"+"在线人数:"+new ShowPersonNum(servers).getNum());
			System.out.print(new SimpleDateFormat("yyyy年MM月dd日\tHH时mm分ss秒").format(new Date())+"有一个客户端进行了连接\t\t"+"在线人数:"+new ShowPersonNum(servers).getNum());
			out.println("已连接服务器(quit退出)"+"你的Id为"+threadId);
			boolean isOnline= true;//上下线
			while(isOnline) {
				String stringSer = in.nextLine();
				//判断字符串长度有客户端以后可以省略
				if(stringSer.length()<4) {
					continue;
				}
				QQProtocol information = new QQProtocol(stringSer);
				switch(information.menu()) {
				case 1://群发
					new AllSend(information,servers,server,log,threadId);
					break;
				case 2://单发
					new SendNameMessage(out,information,servers,server,threadId,log);
					break;
				case 3:
					//文件
					break;
				case 4://显示在线人数
					out.println("在线人数:"+new ShowPersonNum(servers).getNum());
					break;
				case 5://显示当前上线的name
					new ShowPersonName(servers,out);
					break;
				case 6://退出
					new ChatOut(servers,server,out,log,threadId);
					server.close();
					in.close();
					out.close();
					isOnline = false;
					log.println(new SimpleDateFormat("yyyy年MM月dd日\tHH时mm分ss秒").format(new Date())+"\n"+threadId+"退出了连接");
					break;
				case 7://命名
					new ChatName(information,servers,threadId,out,server,log);
					break;
				default:
					break;
				}
			}
		} catch (IOException e) {//抛出异常
			// TODO Auto-generated catch block
			log.println(new SimpleDateFormat("yyyy年MM月dd日\tHH时mm分ss秒").format(new Date()));
			e.printStackTrace(log);
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			log.println(new SimpleDateFormat("yyyy年MM月dd日\tHH时mm分ss秒").format(new Date()));
			e1.printStackTrace(log);
			e1.printStackTrace();
		}
	}
	public Chater(List<Chat>servers,int threadId) {
		this.log = servers.get(threadId).getLog();
		this.server = servers.get(threadId).getChatServer();
		this.threadId = threadId;
		this.servers = servers;
	}
	
}
