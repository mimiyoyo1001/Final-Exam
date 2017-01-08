import java.io.DataOutputStream;
import java.io.*;
import java.lang.Exception;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.lang.*;
import java.util.Scanner;
import java.net.SocketException;
public class Main {
    private static final int receveSocket = 65033;
    private static String sentHost = "25.62.61.158";
    private static final int sentToSocket = 65033;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line;
        System.out.println("輸入想要連接的的位址");
        sentHost = sc.nextLine();
        Thread thread = new Thread() //定義執行緒
        {
            public void run(){
                try{
                    ServerSocket serverSocket = new ServerSocket(receveSocket); //開啟SOCKET
                    Socket clientSocket = serverSocket.accept();
                    BufferedReader in =
                            new BufferedReader(
                                    new InputStreamReader(clientSocket.getInputStream()));
                    for(;;)
                    {
                        System.out.println(in.readLine());//印出得到的字串
                    }
                }catch(Exception e){
                    System.out.print("error, connect end");// 錯誤處理
                    return;
                }
            }
        };
        thread.start();//執行緒開始
        try{
            Socket socket = new Socket(sentHost, sentToSocket); //接收SOCKET
            socket.setSoTimeout(2);
            PrintWriter out =
                    new PrintWriter(socket.getOutputStream(), true);
            System.out.println("連接成功");
            while ((line = sc.nextLine()) != null) {
                out.println(line);
            }
        }catch(SocketException e){
            System.out.println(e.toString());
            return;
        }catch(Exception e){
            System.out.print("error, connect end");
            return;
        }
    }
};