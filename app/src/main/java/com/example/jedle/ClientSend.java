package com.example.jedle;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class ClientSend{

    public void sendText(String textToSend, String address){
        try {
            DatagramSocket udpSocket = new DatagramSocket(null);
            if (udpSocket.isBound()){
                udpSocket.setReuseAddress(true);
                Log.d("UDP Bound:", "address reused");
            }

            InetSocketAddress port = new InetSocketAddress(6667);
            udpSocket.bind( port);
            InetAddress serverAddr = InetAddress.getByName(address);
            byte[] buf = textToSend.getBytes();
            DatagramPacket packet = new DatagramPacket(buf,0, buf.length, serverAddr, 6666);
            udpSocket.send(packet);
            udpSocket.close();
        }
        catch (SocketException e){
            Log.e("Udp:", "Socket Error:", e);
        }
        catch (IOException e ){
            Log.e("UDP Send:", "IO Error:", e);
        }
    }
}
// it is made that port is listing for messages came from port 6667
