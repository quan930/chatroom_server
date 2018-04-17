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

import quan.Protocol.QQProtocol;
import quan.main.Chat;

public class SendNameMessage {
	public SendNameMessage(PrintWriter out,QQProtocol information,List<Chat>servers,Socket server,int threadId,PrintStream log){
		String string = information.getString();
		int m = string.indexOf("**");
		String name = string.substring(0, m);
		String newStr = string.substring(m+2);
		int num = 0;
		boolean existence = false;//判断人名是否存在
		for (int i = 0; i < servers.size(); i++) {// 遍历所有的线程
        	Socket s = servers.get(i).getChatServer();
        	if(s.isClosed()) {//判断套接字是否关闭，如关闭结束本次循环
        		continue;
        	}else {
        		if(servers.get(i).getChatName().equals(name)) {
        			num = i;//获得对象ID
        			if(s == server) {//判断是否是自己
    					out.println("不可以给给自己发消息error!");
    					return;
    				}else {
    					PrintWriter outS;//创建输出流
    					try {
    						outS = new PrintWriter(new OutputStreamWriter(s.getOutputStream(),"UTF-8"),true);
    						outS.println(servers.get(threadId).getChatName()+":"+newStr);
    						//outS.close();//关闭流
    						existence = true;
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
				}else {
					continue;
				}
        	}
        }
		if(existence == false) {
			out.println("人名不存在");
		}
		return;
	}
}
