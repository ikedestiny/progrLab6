package org.example.client;


import org.example.client.util.RequestChecker;
import org.example.client.util.SpaceMarineCreator;
import org.example.common.data.SpaceMarine;
import org.example.common.util.ClientRequest;
import org.example.common.util.CommandType;
import org.example.common.util.ServerResponse;


import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;



public class Client {
    public static void main(String[] args) {

        try {
           // Socket socket= new Socket("192.168.10.80",1234);
            Socket socket= new Socket("localhost",1234);

            System.out.println("connected to server "+socket.getInetAddress());
            Scanner scanner = new Scanner(System.in);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in  = new ObjectInputStream(socket.getInputStream());
            String command ="";

            System.out.println("Enter Command: ");
            Map<String, CommandType> map = (Map<String, CommandType>) in.readObject();
            RequestChecker requestChecker = new RequestChecker(map);
            System.out.println(map);
            CommandType commandType;
            while(scanner.hasNext()){
            //get input from console
                String[] input = scanner.nextLine().split(" ");
                command = input[0];
                String param = input.length>1?input[1] : " no args";
                ClientRequest clientRequest = new ClientRequest();
                try{
                    clientRequest = requestChecker.check(param,command);
                }
                catch (NullPointerException e){
                    System.out.println("No such command");
                }

                out.writeObject(clientRequest);



                //  wait for server response
            ServerResponse response = (ServerResponse) in.readObject();
//                Object obj = in.readObject();
//                System.out.println(obj.toString());
                //String clientRequest1=new String(array1);
           System.out.println("STATUS: "+response.getStatus()+"\nRESPONSE: "+response.getMessage()+"\nMarines:"+response.getMarines());
            System.out.print("==> ");
            }

        } catch (SocketException e){
            System.out.println("The server has been disconnected or not even started");
            System.out.println(e.getMessage());
        } catch (Exception e){
           e.printStackTrace();
        }
    }
}


