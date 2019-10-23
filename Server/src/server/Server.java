/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import start.ServerForm;

/**
 *
 * @author Phenom
 */
public class Server extends Thread{
    private boolean active = true;
    private int i;
    private List<ClientThread> clients;
    private List<Socket> clientSocket;
    private ServerForm sf;
    private ServerSocket ss;

    public Server(ServerForm sf) {
        this.sf = sf;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the clients
     */
    public List<ClientThread> getClients() {
        return clients;
    }

    /**
     * @param clients the clients to set
     */
    public void setClients(List<ClientThread> clients) {
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            ss = new ServerSocket(9000);
            System.out.println("Server is up and running!");
            clients = new LinkedList<>();
            clientSocket = new LinkedList<>();
            this.i = 0;
            sf.serverRunningSuccess();
            while (active) {                
                Socket socket = ss.accept();
                ClientThread clientThread = new ClientThread(socket,this,i);
                clientThread.start();
                System.out.println("Client joined");
                i++;
                clients.add(clientThread);
                clientSocket.add(socket);
            }
            
        } catch (IOException ex) {
            sf.serverRunningFailed();
        }
        
    }
    
    public void stopServer() {
        try {
            active = false;
            ss.close();
            for (Socket socket : clientSocket) {
                socket.close();
            }
            
            System.out.println("Server is stopped");
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
