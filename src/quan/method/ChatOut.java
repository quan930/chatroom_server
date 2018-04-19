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
import quan.main.Chater;

public class ChatOut {
	public ChatOut(Chater chater) {
		for	(int i = 0; i < chater.getChats().size(); i++) {// 遍历所有的线程
	        Socket s = chater.getChats().get(i).getChatServer();
			if(s.isClosed()) {//判断套接字是否关闭，如关闭结束本次循环
				continue;
			}else {
				if(s != chater.getChatServer()) {//判断是否自己
					PrintWriter outS;//创建输出流
					try {
						outS = new PrintWriter(new OutputStreamWriter(s.getOutputStream(),"UTF-8"),true);
						outS.println(chater.getChatName()+"下线");
						//outS.close();//关闭流 ？？？？？？！！！！！！！
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
			}
        }		
		return;
	}
}
