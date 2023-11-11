import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    public static boolean isClosed;
    public static void main(String[] args) {
        try{
            Socket s = new Socket("localhost",6666);
            Scanner in = new Scanner(System.in);
            //Receives data from the server
            DataInputStream dis = new DataInputStream(s.getInputStream());
            //Will send data to the server
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            isClosed = false;
            while(true){
                System.out.print("Enter Something: ");
                String input = in.nextLine();
                dos.writeUTF(input);
                if(input.equalsIgnoreCase("/exit")){
                    break;
                }
                System.out.println("Server: " + dis.readUTF());
            }
            //Close Resources
            dis.close();
            dos.close();
            in.close();
            s.close();
            isClosed = true;
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
