
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author avi
 */
public class Peer implements Runnable{
    MulticastSocket socket;
    InetAddress host;
    int id;
    
    public Peer(int id){
        try {
            socket = new MulticastSocket(5000);
            host = InetAddress.getByName("230.0.0.5");
            socket.joinGroup(host);
        } catch (IOException ex) {

        }
        
    }
    public void msg_send(String cad){
        byte [] buffer = cad.getBytes();
        DatagramPacket paquete = new DatagramPacket(buffer,buffer.length,host,5000);
        try {
            socket.send(paquete);
        } catch (IOException ex) {

        }
    }
    @Override
    public void run() {
        
        try {
            byte [] buffer = new byte[30];
            DatagramPacket paquete = new DatagramPacket(buffer,buffer.length);
            socket.receive(paquete);
        } catch (IOException ex) {
            Logger.getLogger(Peer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
