package keizaiya.second.connection;


import keizaiya.second.Potato;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class tuusin {

    public static boolean stop = false;
    public static ServerSocket socket = null;

    public static Thread startcom(){
        System.out.println("立ち上げました");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (stop == false) {
                    try {
                        socket = new ServerSocket(42732);
                        System.out.println(socket);
                        Socket sock = socket.accept();
                        System.out.println(sock.getInetAddress());
                        byte[] data = new byte[1024];
                        OutputStream out = sock.getOutputStream();
                        String name = "test:sucsess";
                        out.write(name.getBytes("UTF-8"));
                        System.out.println("リクエストに応えました");
                        out.close();
                        sock.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        return thread;
    }

    public static void starttuu(){
        stop = false;
        new BukkitRunnable(){
            @Override
            public void run() {
                if(stop == false) {
                    try {
                        ServerSocket Soclet = new ServerSocket(42732);
                        System.out.println(Soclet);
                        Socket sock = Soclet.accept();
                        System.out.println(sock.getInetAddress());
                        byte[] data = new byte[1024];
                        OutputStream out = sock.getOutputStream();
                        String name = "test:sucsess";
                        out.write(name.getBytes("UTF-8"));
                        System.out.println("リクエストに応えました");
                        out.close();
                        sock.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        this.cancel();
                    }
                }else {
                    this.cancel();
                }
            }
        }.runTaskTimer(Potato.plugin, 0, 1);
    }
}
