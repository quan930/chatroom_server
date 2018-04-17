package quan.main;

import java.io.PrintStream;
import java.net.Socket;
//信息
public class Chat {
	private Socket server;
	private String name;
	private PrintStream log;
	
	public Socket getChatServer() {
		return server;
	}
	
	public String getChatName() {
		return name;
	} 
	
	public PrintStream getLog() {
		return log;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Chat(Socket server,String name,PrintStream log) {
		this.server = server;
		this.name = name;
		this.log = log;
	}
}
