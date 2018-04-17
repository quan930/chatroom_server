package quan.method;

import java.net.Socket;
import java.util.List;
import quan.main.Chater;

public class ShowPersonNum {
	private int num;
	public ShowPersonNum(List<Chater>chats) {
		num = 0;
		for (int i = 0; i < chats.size(); i++) {// 遍历所有的线程
        	Socket s = chats.get(i).getChatServer();
			if(s.isClosed()) {//判断套接字是否关闭，如关闭结束本次循环
				continue;
			}else{
				num = num+1;
			}
        }
		return;
	}
	public int getNum() {
		return num;
	}
}
