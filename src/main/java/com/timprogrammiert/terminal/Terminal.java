package com.timprogrammiert.terminal;

import com.timprogrammiert.commands.LsCommand;
import com.timprogrammiert.host.Host;
import com.timprogrammiert.util.DirectoryInfo;

import java.util.Scanner;

/**
 * @author tmatz
 */
public class Terminal {
    private Host host;
    public Terminal() {
        host = new Host();
        run();
    }

    private void run(){
        Scanner scanner = new Scanner(System.in);
        String promptText = DirectoryInfo.getAbsolutPath(host.getCurrentDirectory()) + " ";
        String input = null;
        while (true){
            System.out.print(promptText);
            input = scanner.nextLine();
            String[] testCommand = new String[]{"/etc"};
            LsCommand.execute(testCommand, host);

            if(input.equals("exit")) return;

        }
    }
}
