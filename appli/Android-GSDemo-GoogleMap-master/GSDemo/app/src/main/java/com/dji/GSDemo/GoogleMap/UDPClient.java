package com.dji.GSDemo.GoogleMap;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {
    //On envoi une instruction au raspberry afin de lacher des graines
    //On utilise un protocole UDP
    public void sendInstruction(){
        new Thread(){
            @Override
            public void run(){
                try {
                    DatagramSocket UdpSocket = new DatagramSocket(8080);
                    InetAddress serverAddr = InetAddress.getByName("91.161.249.193");
                    byte[] buf = new byte[1];
                    buf[0] = 1;
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, 23600);
                    UdpSocket.send(packet);
                } catch(SocketException e) {
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

        }.start();
    }
}
