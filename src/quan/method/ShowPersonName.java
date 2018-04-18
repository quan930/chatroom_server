package quan.method;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import quan.main.Chater;

public class ShowPersonName {
	public ShowPersonName(List<Chater>chats,PrintWriter out) {
		out.print("Name");
		for (int i = 0; i < chats.size(); i++) {// 遍历所有的线程
        	Socket s = chats.get(i).getChatServer();
        	if(s.isClosed()) {//判断套接字是否关闭，如关闭结束本次循环
        		continue;
        	}else {
        		out.print(chats.get(i).getChatName()+"Name");
        	}
        }
		out.print("\n");
		out.flush();
        return;
	}
}
