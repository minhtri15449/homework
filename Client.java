import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        Scanner reader = null;
        int serverPort = 8080;
        try {
            socket = new Socket("127.0.0.1", serverPort);
            reader = new Scanner(System.in);
            // Read data from server
            FileWriter writer = new FileWriter(new File("C:/Users/hacke/Documents/advertising_client.csv"));
            DataInputStream streamIn = new DataInputStream(socket.getInputStream());
            DataOutputStream streamOut = new DataOutputStream(socket.getOutputStream());
            String line = null;
            boolean done = false;
            while (!done) {
                try {
                    line = streamIn.readUTF();
                    if ((done = line.equals(".bye")) == false) {
                        writer.write(line + "\n");
                        System.out.println(line);
                    }
                } catch (Exception e) {
                    done = true;
                }
            }
            writer.close();
            System.out.print("Input data: ");
            line = reader.nextLine();
            streamOut.writeUTF(line);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("Socket closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
