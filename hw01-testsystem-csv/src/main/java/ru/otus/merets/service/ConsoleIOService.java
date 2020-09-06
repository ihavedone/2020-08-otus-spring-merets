package ru.otus.merets.service;


import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class ConsoleIOService implements IOService {
    private final BufferedReader bufferedReader;
    private final BufferedWriter bufferedWriter;

    public ConsoleIOService() {
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void printMessage(Object obj) {
        try {
            bufferedWriter.write( obj.toString());
            bufferedWriter.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getString() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
