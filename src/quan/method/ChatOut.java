package quan.method;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import quan.main.Chat;

public class ChatOut {
	public ChatOut(List<Chat>servers,Socket server,PrintWriter out,PrintStream log,int threadId) {
		for	(int i = 0; i < servers.size(); i++) {// 遍历所有的线程
	        Socket s = servers.get(i).getChatServer();
			if(s.isClosed()) {//判断套接字是否关闭，如关闭结束本次循环
				continue;
			}else {
				if(s != server) {//判断是否自己
					PrintWriter outS;//创建输出流
					try {
						outS = new PrintWriter(new OutputStreamWriter(s.getOutputStream(),"UTF-8"),true);
						outS.println(servers.get(threadId).getChatName()+"退出");
						//outS.close();//关闭流 ？？？？？？！！！！！！！
					} catch (UnsupportedEncodingException e) {//抛出异常
						// TODO Auto-generated catch block
						log.println(new SimpleDateFormat("yyyy年MM月dd日\tHH时mm分ss秒").format(new Date()));
						e.printStackTrace(log);
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						log.println(new SimpleDateFormat("yyyy年MM月dd日\tHH时mm分ss秒").format(new Date()));
						e.printStackTrace(log);
						e.printStackTrace();
					}
				}
			}
        }
		return;
	}
}
