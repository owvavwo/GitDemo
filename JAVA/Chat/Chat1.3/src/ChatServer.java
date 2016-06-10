import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
	boolean started = false;
	ServerSocket ss = null;	
	List<Client> clients = new ArrayList<Client> ();
	
	
	public static void main(String[] args) {
		new ChatServer().start();		
	}
	
	public void start () {
		try {
			ss = new ServerSocket(8888);
			started = true;
		}catch(BindException e) {
			System.out.println("The port has been used!");
			System.exit(-1);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		try {			
			while(started) {
				boolean bConnected = false;
				Socket s = ss.accept();
				Client c = new Client(s); //main�Ǿ�̬�����ڲ���ֱ��new ��̬�� Ҫ�� static�򵥶���װ��һ������
				new Thread(c).start();
				clients.add(c);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				ss.close();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
		
	}
	
	class Client implements Runnable { 
		private Socket s;
		private DataOutputStream dos = null;
		private DataInputStream dis = null;
		private boolean bConnected = false;
		public Client(Socket s ) {
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				bConnected = true;
			} catch (IOException e) {				
				e.printStackTrace();
			}
			
		}
		
		public void send(String str)  {
			try {
				dos.writeUTF(str);
			} catch (IOException e) {				
				clients.remove(this);
				System.out.println("�Է��˳��ˣ��Ҵ�list��ȥ���ˣ�");
				//e.printStackTrace();				
			}
		}
		
		@Override
		public void run() {
			
			Client c = null;
			try {
				while(bConnected) {
					String str = dis.readUTF();				
//System.out.println(str);
					for(int i=0;i<clients.size();i++) {
						c = clients.get(i);
						c.send(str);
					}
					
					/*Iterator<Client> it = clients.iterator();
					while(it.hasNext()) {               //������Ч�ʲ���
						Client c = it.next();
						c.send(str);
					}*/
				}
			}catch (EOFException e) {			
				System.out.println("Bye~");
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if(dis != null) dis.close();
					if(dos != null) dos.close();
					if(s != null) { 
						s.close();
						//s = null;
					}
					
				} catch (IOException e) {				
					e.printStackTrace();
				}
				
				
			}			
			
		}
		
	}
}
