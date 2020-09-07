package ru.otus.merets.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.PrimitiveIterator;
import java.util.Scanner;

@Service
public class IOServiceConsole implements IOService {
    private final Scanner scanner;
    private final PrintStream printStream;

    public IOServiceConsole(@Value("#{ T(java.lang.System).in}") InputStream scanner,
                            @Value("#{ T(java.lang.System).out}") PrintStream printStream) {
        this.scanner = new Scanner( scanner );
        this.printStream = printStream;
    }

    @Override
    public void printMessage(Object obj) {
        printStream.println(obj.toString());
    }

    @Override
    public String getString() {
        return scanner.nextLine();
    }
}
