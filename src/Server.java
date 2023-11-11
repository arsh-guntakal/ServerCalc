import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Server {
    private static ServerSocket serverSocket;
    private static Socket s;
    public static void main(String[] args) {
        try {
            serverSocket= new ServerSocket(6666);
            s = serverSocket.accept();

            DataInputStream dataInputStream= new DataInputStream(s.getInputStream());
            DataOutputStream dataOutputStream= new DataOutputStream(s.getOutputStream());

            while(true){
                String input = dataInputStream.readUTF();
                System.out.println("Client: " + input);
                if(input.equals("/exit")){
                    break;
                }
                int answer = processInput(input);
                if(answer == Integer.MIN_VALUE){
                    dataOutputStream.writeUTF("Illegal Input. Usage: int + int");
                }else{
                    dataOutputStream.writeUTF(answer + "");
                }
            }
            System.out.println("Closing Server");
            s.close();
            serverSocket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static int processInput(String input) {
        String[] parts = input.split(" ");
        try{
            int a = Integer.parseInt(parts[0]) + Integer.parseInt(parts[2]);
            return a;
        }catch (NumberFormatException e){
            return Integer.MIN_VALUE;
        }
    }
}
