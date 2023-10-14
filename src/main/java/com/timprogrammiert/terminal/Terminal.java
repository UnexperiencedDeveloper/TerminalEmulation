package com.timprogrammiert.terminal;

import com.timprogrammiert.commands.CommandParser;
import com.timprogrammiert.commands.LsCommand;
import com.timprogrammiert.host.Host;
import com.timprogrammiert.util.DirectoryInfo;

import java.util.Scanner;

/**
 * @author tmatz
 */
public class Terminal {
    private Host host;
    private CommandParser commandParser;
    public Terminal() {
        host = new Host();
        commandParser = new CommandParser(host);
        run();
    }

    private void run(){
        Scanner scanner = new Scanner(System.in);
        String input = null;
        while (true){
            System.out.print(DirectoryInfo.getAbsolutPath(host.getCurrentDirectory()) + " ");
            input = scanner.nextLine();
            //String[] testCommand = new String[]{"/etc"};
            //LsCommand.execute(testCommand, host);
            String[] commandInput = input.split(" ");
            commandParser.parseCommand(commandInput);
            if(input.equals("exit")) return;

        }
    }
}
