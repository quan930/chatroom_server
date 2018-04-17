package quan.method;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import quan.main.Chat;

public class ShowPersonName {
	public ShowPersonName(List<Chat>servers,PrintWriter out) {
		for (int i = 0; i < servers.size(); i++) {// 遍历所有的线程
        	Socket s = servers.get(i).getChatServer();
        	if(s.isClosed()) {//判断套接字是否关闭，如关闭结束本次循环
        		continue;
        	}else {
        		out.println("Name:"+servers.get(i).getChatName());
        	}
        }
        return;
	}
}
