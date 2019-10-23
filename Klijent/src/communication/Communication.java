/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import transfer.Request;
import transfer.Response;

/**
 *
 * @author Phenom
 */
public class Communication {
    private static Communication instance;
    private Socket socket;

    public Communication() throws IOException {
        socket = new Socket("127.0.0.1", 9000);
    }

    public static Communication getInstance() throws IOException {
        if(instance == null)
            instance = new Communication();
        return instance;
    }

    public void sendRequest(Request request) {
        try{
            ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
            outSocket.writeObject(request);
        }catch (IOException ex){
            JOptionPane.showMessageDialog(null, "Server is down!","Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Response readResponse() throws IOException, ClassNotFoundException {
        ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
        return (Response) inSocket.readObject();
    }
    
    
    
    
    
    
}
