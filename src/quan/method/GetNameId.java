package quan.method;

import java.net.Socket;
import java.util.List;
import quan.main.Chater;
//有用？？？？！！！！！！1
public class GetNameId {
	private int id; 
	public GetNameId(String name,List<Chater>chats) {

		for (int i = 0; i < chats.size(); i++) {// 遍历所有的线程
        	Socket s = chats.get(i).getChatServer();
        	if(s.isClosed()) {//判断套接字是否关闭，如关闭结束本次循环
        		continue;
        	}else {
        		if(chats.get(i).getChatName().equals(name)) {
        			id = i;
				}else {
					continue;
				}
        	}
        }
	}
	public int getId() {
		return id;
	}
}
