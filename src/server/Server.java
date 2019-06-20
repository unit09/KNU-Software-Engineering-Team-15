package server;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class Server {

	private static ServerSocket serverSocket;
	private static ArrayList<Socket> clientList;
	private static Thread acceptThread;
	private static Thread recvThread;
	
	public static String FILE_NAME = "data.txt";
	
	public static void main(String[] args) {
		try {
			//서버 소켓 생성
			serverSocket = new ServerSocket(1515);
			startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void startServer() {
		System.out.println("Server start.");
		
		initClientList();
		
		//client를 계속해서 accept해서 clientList에 넣어주는 쓰레드
		acceptThread = new Thread(new AcceptThreadRunnable(serverSocket));
		acceptThread.start();
		
		//client의 요청을 받아오는 쓰레드
		recvThread = new Thread(new RecvThreadRunnable(clientList));
		recvThread.start();
	}
	
	private static void initClientList() {
		if(clientList != null) { //이미 clientList가 null이 아니면 return해서 나감
			return;
		}
		
		clientList = new ArrayList<Socket>();
	}
	
	public static boolean isClientListNull() {
		if(clientList == null) {
			return true;
		} else {
			return false;
		}
	}
	
	//clientList에 client를 추가하는 함수
	public static void addClient(Socket s) {
		if(isClientListNull()) {
			return;
		}
		
		clientList.add(s);
	}

	//클라이언트에게 object를 보낸다 (client가 getObject를 하면 이 함수를 통해서 object 전달
	public static void sendObject2Client(Socket socket, Object object) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			
			oos.writeObject(object);
			oos.flush();
//			oos.close(); //close하면 socket도 close 되어버린다
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Object getFileContent() {
		try {
			File f = new File(Server.FILE_NAME);
			if(f.exists()) {
				System.out.println("file exists!!");
			} else {
				System.out.println("file not exists!!!");
				f.createNewFile();
				
				return null;
			}
			
			FileInputStream fis = new FileInputStream(Server.FILE_NAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			return ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void setFileContent(Object object) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Server.FILE_NAME));
			oos.writeObject(object);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

class AcceptThreadRunnable implements Runnable {
	ServerSocket serverSocket;
	
	public AcceptThreadRunnable(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	@Override
	public void run() {
		try {
			while(true) {
				Socket s = serverSocket.accept();
				Server.addClient(s);
				System.out.println("Accpeted a client");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class RecvThreadRunnable implements Runnable {
	ArrayList<Socket> clientList;
	
	public RecvThreadRunnable(ArrayList<Socket> clientList) {
		this.clientList = clientList;
	}
	
	@Override
	public void run() {
		while(true) {
			
			try {
				Thread.sleep(50);
//				System.out.println(clientList.size() + "");
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			for(int i = 0; i < clientList.size(); i++) {
				Socket socket = clientList.get(i);
				
				if(socket.isClosed()) { //소켓 접속 종료 했으면 리스트에서 뺌
					System.out.println(i + "번째 소켓이 close되었습니다.");
					clientList.remove(i);
					break;
				}
				
				try {
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					String type = (String)ois.readObject();
					
					if(type.equals("get")) { //get 요청인 경우
						System.out.println("get is required");
						
						Object content = Server.getFileContent();
						Server.sendObject2Client(socket, content);
						
						if(content != null) {
							System.out.println("sended data.txt content to client: " + content.toString());
						}
					} else if(type.equals("set")) { //set 요청인 경우
						Object o = ois.readObject();
						
						System.out.println("set is required");
						
						Server.setFileContent(o);
						System.out.println("file saved");
					} else {
						System.out.println("strange request is arrived from client (not get and not set)");
					}
				} catch(EOFException | SocketException e) {
					//클라이언트 소켓 갑자기 종료 되었을 때
					clientList.remove(i);
				} catch (IOException | ClassNotFoundException e) { //진짜 오류
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
}