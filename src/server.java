import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server
{
	public static void main(String[] args)
	{
		try
		{
			ServerSocket server = new ServerSocket(8888);
			System.out.println("Server已建立,等待客戶端連線...");
			int i = 0;
			while (true)
			{
				Socket toClient = server.accept();
				i++;
				System.out.println("客戶端已連線:" + toClient.getInetAddress());
				ToClientThread thread = new ToClientThread(toClient, i);
				thread.start();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

class ToClientThread extends Thread
{
	Socket toClient;
	int no;

	public ToClientThread(Socket toClient, int no)
	{
		this.toClient = toClient;
		this.no = no;
	}

	@Override
	public void run()
	{
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(
					toClient.getInputStream()));
			OutputStreamWriter out = new OutputStreamWriter(toClient.getOutputStream());
			PrintWriter printWriter = new PrintWriter(out, true);
			String str;
			while ((str = br.readLine()) != null)
			{
				System.out.println(no + ":" + str);
				printWriter.println("Server received:" + str);
			}
			br.close();
			toClient.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

