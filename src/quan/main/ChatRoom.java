package quan.main;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ChatRoom {
	private boolean open;
	private int port;//端口号
	private int i;//开启线程
	public void start() throws IOException {
		//日志
		File file =new File("log.txt");
		if(!file.exists()) {
			file.createNewFile();
		}
		PrintStream log = new PrintStream(file);
		List<Chat>servers = new ArrayList<Chat>();
		ServerSocket serverSocket;
		serverSocket = new ServerSocket(port);
		i = 0;
		while(true) {
			Socket server = serverSocket.accept();
			Chat chat = new Chat(server,"****",log);
			servers.add(chat);
			log.println("线程"+i);
			Runnable r = new Chater(servers,i);
			Thread t = new Thread(r);
			t.start();
			i++;
		}
	}
	
	//结束
	public void finish() {
		System.exit(0);
	}
	
	//设置端口号
	public void s️etPort(int port) {
		this.port = port;
	}
	
	//查看已开启线程
	public int threadNum() {
		return i;
	}
	
}
