package org.example.server.main;

import org.example.server.main.ResponseSender.Commander;

import java.io.FileNotFoundException;

public class TestServer extends Thread {
    public static void main(String[] args) throws FileNotFoundException {
        Commander commander = new Commander(null, null);
        System.out.println(commander.getCommandTypeMap());
    }
}
