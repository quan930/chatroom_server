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
import quan.main.Chater;

public class SendNameMessage {
	public SendNameMessage(PrintWriter out,QQProtocol information,Chater chater){
		String string = information.getString();
		int m = string.indexOf("**");
		String nameNew = string.substring(0, m);//发送方人名
		String newStr = string.substring(m+2);
		int num = 0;
		boolean existence = false;//判断人名是否存在
		for (int i = 0; i < chater.getChats().size(); i++) {// 遍历所有的线程
        	Socket s = chater.getChats().get(i).getChatServer();
        	if(s.isClosed()) {//判断套接字是否关闭，如关闭结束本次循环
        		continue;
        	}else {
        		if(chater.getChats().get(i).getChatName().equals(nameNew)) {
        			num = i;//获得对象ID
        			if(s == chater.getChatServer()) {//判断是否是自己
    					out.println("*11*error");
    					return;
    				}else {
    					PrintWriter outS;//创建输出流
    					try {
    						outS = new PrintWriter(new OutputStreamWriter(s.getOutputStream(),"UTF-8"),true);
    						outS.println(chater.getChatName()+":"+newStr);
    						out.println("*11*succsee");
    						//outS.close();//关闭流
    						existence = true;
    					} catch (UnsupportedEncodingException e) {//抛出异常
    						// TODO Auto-generated catch block
    						chater.getLog().println(new SimpleDateFormat("yyyy年MM月dd日\tHH时mm分ss秒").format(new Date()));
    						e.printStackTrace(chater.getLog());
    					} catch (IOException e) {
    						// TODO Auto-generated catch block
    						chater.getLog().println(new SimpleDateFormat("yyyy年MM月dd日\tHH时mm分ss秒").format(new Date()));
    						e.printStackTrace(chater.getLog());
    					}
    				}
				}else {
					continue;
				}
        	}
        }
		if(existence == false) {
			out.println("*11*人名不存在");
		}
		return;
	}
}
