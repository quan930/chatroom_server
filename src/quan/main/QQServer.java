package quan.main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QQServer {
	private long startDate;
	private long endDate;
	private ChatRoom chatRoom;
	public void start(int port) throws IOException {
		startDate = System.currentTimeMillis();
		chatRoom = new ChatRoom();
		chatRoom.s️etPort(port);
		chatRoom.start();
		//启动
	}
	//停止
	public void stop() {
		chatRoom.finish();
		endDate = System.currentTimeMillis();
		System.out.println("程序运行时间： "+(endDate-startDate)+"ns");
	}
	//查看开启线程数
	public void numThreads() {
		System.out.println("已开启线程:"+chatRoom.threadNum());
	}
}
