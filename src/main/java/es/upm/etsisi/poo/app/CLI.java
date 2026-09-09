package es.upm.etsisi.poo.app;

import es.upm.etsisi.poo.Messages;
import es.upm.etsisi.poo.command.Command;
import es.upm.etsisi.poo.command.CommandParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLI {

    private final CommandParser parser;

    public CLI(CommandParser parser){
        this.parser = parser;
    }

    public void runInteractive() {
        System.out.println(Messages.WELCOME);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            String line;
            while (true) {
                System.out.print(Messages.PROMPT);
                line = reader.readLine();
                if(line == null || line.equalsIgnoreCase("exit")) break;
                processLine(line);
            }
        } catch (IOException e) {
            System.out.println(Messages.errorReadingInput(e.getMessage()));
        }
        System.out.println(Messages.EXIT);
    }

    public void runFromFile(String filePath){
        System.out.println(Messages.runningFile(filePath));
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine())!=null){
                processLine(line);
            }
        } catch (IOException e) {
            System.out.println(Messages.errorReadingFile(filePath, e.getMessage()));
        }
        System.out.println(Messages.FILE_PROCESSED);
    }

    public void processLine(String line) {
        Command command = parser.parse(line);
        if(command!=null){
            command.execute();
        }
    }
}
