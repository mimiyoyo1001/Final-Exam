import java.io.*;
import java.lang.Exception;
import java.net.Socket;
import java.lang.*;
import java.net.SocketException;

public class client
{
	private static final int receveSocket = 8888;
	private static String sentHost = "127.0.0.1";
	private static final int sentToSocket = 8888;
	public static String line = "";
	public static boolean flag = false;
	public static boolean disconnectFlag = true;

	public static void runClient()
	{
		//Scanner sc = new Scanner(System.in);
		System.out.println("輸入想要連接的的位址");
		//sentHost = sc.nextLine();
		/*Thread thread = new Thread() //定義執行緒
		{
			public void run()
			{
				try
				{
					ServerSocket serverSocket = new ServerSocket(receveSocket); //開啟SOCKET
					Socket clientSocket = serverSocket.accept();
					BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					for (; ; )
					{
						System.out.println(in.readLine());//印出得到的字串
					}
				} catch (Exception e)
				{
					System.out.print("error, connect end");// 錯誤處理
					return;
				}
			}
		};
		thread.start();//執行緒開始
		*/
		Thread thread = new Thread()
		{
			public void run()
			{
				disconnectFlag = true;
				try
				{
					Socket socket = new Socket(sentHost, sentToSocket); //接收SOCKET
					//socket.setSoTimeout(2);
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					System.out.println("連接成功");
					while (disconnectFlag)
					{
						sleep(10);
						if (!line.equals(""))
						{
							out.println(line);
							line = "";
							sleep(10);
							View.displayGetMessage(in.readLine());
							flag = false;
						}
					}
					View.displayGetMessage("中斷連線.");
				} catch (Exception e)
				{
					System.out.println(e.toString());
					System.out.print("error, connect end");
					return;
				}
			}
		};
		thread.start();
	}

	public static void sentMessage(String message)
	{
		line = message;
		flag = true;
	}

	public static void disconnectFlag()
	{
		disconnectFlag = false;
	}

	public static void displayGetMessage(String message)
	{
		View.displayGetMessage(message);
	}
}