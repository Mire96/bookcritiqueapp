/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import controller.Controller;
import domain.Autor;
import domain.Delo;
import domain.IGeneralEntity;
import domain.Korisnik;
import domain.Kritika;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author Phenom
 */
class ClientThread extends Thread {

    private int threadId;
    private Socket clientSocket;
    private Server ss;

    ClientThread(Socket socket, Server s, int i) {
        clientSocket = socket;
        ss = s;
        threadId = i;
    }

    /**
     * @return the threadId
     */
    public int getThreadId() {
        return threadId;
    }

    @Override
    public void run() {

        try {
            System.out.println(ss.getClients().size() + " clients");
            communication();
        } catch (Exception ex) {
            changeClientList();
            try {
                this.clientSocket.close();
            } catch (IOException ex1) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Klijent - kraj rada.");
        }
    }

    private void communication() throws IOException, ClassNotFoundException {
        while (!isInterrupted()) {
            ObjectInputStream inSocket = new ObjectInputStream(clientSocket.getInputStream());
            Object object = inSocket.readObject();
            if (object instanceof Request) {
                Request request = (Request) object;
                Response response = handleRequest(request);
                sendResponse(clientSocket, response);
            }
        }
    }

    private Response handleRequest(Request request) {
        int operation = request.getOperation();
        Response response = new Response();
        switch (operation) {
            case Operation.OPERATION_LOGIN:
                try {
                    Korisnik requestKorisnik = (Korisnik) request.getData();
                    Korisnik korisnik;
                    korisnik = (Korisnik) Controller.getInstance().login(requestKorisnik);
                    response.setStatus(ResponseStatus.OK);
                    response.setData(korisnik);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }
                break;

            case Operation.OPERATION_REGISTER:

                try {
                    Korisnik requestKorisnik = (Korisnik) request.getData();
                    Controller.getInstance().register(requestKorisnik);
                    response.setStatus(ResponseStatus.OK);

                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }

                break;

            case Operation.OPERATION_LOGOUT:

                try {
                    Session.getInstance().removeUser((Korisnik) request.getData());
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception e) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(e.getMessage());
                }
                break;

            case Operation.OPERATION_EDIT_USER:
                try {
                    Korisnik izmenjeniKorisnik = (Korisnik) request.getData();
                    Controller.getInstance().updateKorisnik(izmenjeniKorisnik);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }
                break;

            case Operation.OPERATION_SEARCH_USERS:
                try {
                    String search = request.getData().toString();
                    response.setData(Controller.getInstance().getAllUsers(search));
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }
                break;

            case Operation.OPERATION_DELETE_USER:
                try {
                    Korisnik korisnik = (Korisnik) request.getData();
                    Controller.getInstance().deleteKorisnik(korisnik);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }
                break;

            case Operation.OPERATION_FIND_AUTORS:

                try {
                    Delo delo = (Delo) request.getData();
                    List<IGeneralEntity> autori = Controller.getInstance().pronadjiAutore(delo);
                    response.setData(autori);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }
                break;

            case Operation.OPERATION_SEARCH_AUTORS:

                try {
                    String search = (String) request.getData();
                    response.setData(Controller.getInstance().getAutors(search));
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }
                break;

            case Operation.OPERATION_NEW_AUTOR:

                try {
                    Autor autor = (Autor) request.getData();
                    Controller.getInstance().sacuvajAutora(autor);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }
                break;

            case Operation.OPERATION_DELETE_AUTOR:

                try {
                    Autor autor = (Autor) request.getData();
                    Controller.getInstance().deleteAutor(autor);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }
                break;

            /*case Operation.OPERATION_SAVE_AUTOR:
                
                try {
                List<Autor> autori = (List<Autor>) request.getData();
                Controller.getInstance().sacuvajAutorDelo(autori, Session.getInstance().getDelo());
                response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                response.setStatus(ResponseStatus.ERROR);
                response.setError(ex.getMessage());
                }
                break;*/
            case Operation.OPERATION_SEARCH_DELO:

                try {
                    List<String> pretragaDela = (List<String>) request.getData();
                    List<IGeneralEntity> dela = Controller.getInstance().getAllDela(pretragaDela.get(0), pretragaDela.get(1));
                    response.setData(dela);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }
                break;

            case Operation.OPERATION_NEW_KRITIKA:

                try {
                    Kritika kritika = (Kritika) request.getData();
                    Controller.getInstance().posaljiKritikuNaOdobrenje(kritika);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }
                break;

            case Operation.OPERATION_APPROVE_KRITIKA:

                try {
                    Kritika kritika = (Kritika) request.getData();
                    Controller.getInstance().odobriKritiku(kritika);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }
                break;

            case Operation.OPERATION_DELETE_KRITIKA:

                try {
                    Kritika kritika = (Kritika) request.getData();
                    Controller.getInstance().deleteKritika(kritika);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }
                break;

            case Operation.OPERATION_GET_KRITIKE:

                try {
                    Delo delo = (Delo) request.getData();
                    response.setData(Controller.getInstance().getKritike(delo));
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }
                break;

            case Operation.OPERATION_GET_KRITIKE_ZA_ODOBRENJE:

                try {
                    response.setData(Controller.getInstance().getKritikeZaOdobrenje());
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }
                break;

            case Operation.OPERATION_DELETE_DELO:
                try {
                    int deloID = (int) request.getData();
                    Controller.getInstance().deleteDelo(deloID);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }
                break;

            case Operation.OPERATION_SAVE_DELO:

                try {
                    Delo delo = (Delo) request.getData();
                    Controller.getInstance().sacuvajDelo(delo);
                    Session.getInstance().setDelo(delo);
                    Controller.getInstance().sacuvajAutorDelo(delo.getAutori(), delo);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }
                break;
                
            case Operation.OPERATION_UPDATE_DELO:
                try {
                    Delo delo= (Delo) request.getData();
                    Controller.getInstance().izmeniDelo(delo);
                    Session.getInstance().setDelo(delo);
                    Controller.getInstance().sacuvajAutorDelo(delo.getAutori(), delo);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }
                break;

        }
        return response;
    }

    private void sendResponse(Socket clientSocket, Response response) throws IOException {
        ObjectOutputStream outSocket = new ObjectOutputStream(clientSocket.getOutputStream());
        outSocket.writeObject(response);
    }

    private void changeClientList() {
        List<ClientThread> clients = ss.getClients();

        for (ClientThread client : clients) {
            if (client.getThreadId() == this.threadId) {
                clients.remove(client);
            }
        }

        ss.setClients(clients);
        System.out.println(ss.getClients().size() + " clients");
    }

}
