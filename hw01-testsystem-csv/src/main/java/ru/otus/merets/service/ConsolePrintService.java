package ru.otus.merets.service;


import java.io.PrintStream;

public class ConsolePrintService implements PrintService{
    private final PrintStream printStream;

    public ConsolePrintService()
    {
        this.printStream = System.out;
    }

    @Override
    public void printMessage(Object obj) {
        printStream.println( obj.toString() );
    }
}
