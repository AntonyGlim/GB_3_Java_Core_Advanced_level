package lesson_6_Work_with_network.consoleСhatTask.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerOut  implements Runnable{

    private Socket socket;
    private DataOutputStream out;
    private BufferedReader br;

    public ServerOut(Socket socket) throws IOException {
        this.socket = socket;
        out = new DataOutputStream(socket.getOutputStream());
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    @Override
    public void run() {
        try {
            while (!socket.isOutputShutdown()){
                String msg = br.readLine();
                out.writeUTF(msg);
                out.flush();
//                if (msg.equalsIgnoreCase("/q")) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            br.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
