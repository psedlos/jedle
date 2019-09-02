package com.example.jedle;

import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;


public class ClientListen implements Runnable {
    //MainContainer mc;
    Warsztat warsztat;
    public ClientListen(Warsztat warsztat){this.warsztat = warsztat;}//MainContainer mc){        this.mc = mc;    }


    @Override
    public void run() {
        Looper.prepare();
        boolean run = true;
        while (run) {
            try {
                DatagramSocket udpSocket = new DatagramSocket(null);
                if (udpSocket.isBound()) {
                    udpSocket.setReuseAddress(true);
                    Log.d("UDP Bound:", "address reused");
                }
                udpSocket.setReuseAddress(true);
                InetSocketAddress port = new InetSocketAddress(6666);
                udpSocket.bind(port);
                byte[] message = new byte[8000];
                DatagramPacket packet = new DatagramPacket(message, message.length);
                Log.i("UDP client:", "about to wait to receive");
                udpSocket.receive(packet);
                int[] intmessage = new int[8000];
                for (int i = 0; i < message.length; i++) {
                    intmessage[i] = message[i] & 0xff;
                }
                String text = new String(intmessage, 0, packet.getLength());
                Log.d("Received Data", text);
                warsztat.getMessage(text);


            } catch (IOException e) {
                Log.e("UDP Client has IOE", "error: ", e);
                run = false;
            }
        }
    }
}
