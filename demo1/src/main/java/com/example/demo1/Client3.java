/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo1;



import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client3 {
    Socket mSocket;
    int port = 9090;
    String serverAddress = "127.0.0.1";

    InputStream fromServerStream;
    OutputStream toServerStream;

    DataInputStream reader;
    PrintWriter writer;

    public Client3() {
        try {

            mSocket = new Socket(serverAddress, port);

            System.out.println("connect to server ....");

            // input stream (stream from server)
            fromServerStream = mSocket.getInputStream();

            // output stream (stream to server)
            toServerStream = mSocket.getOutputStream();

            reader = new DataInputStream(fromServerStream);
            writer = new PrintWriter(toServerStream, true);

            menu();

        } catch (UnknownHostException e) {
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    public void menu(){

        Scanner sc=new Scanner(System.in);

        String name = "user";

        sendName(name);
        HelloApplication.go();

    }
    private void sendName(String name){
        writer.println(name);
    }




    public static void main(String[] args) {
        new Client3();
    }
}