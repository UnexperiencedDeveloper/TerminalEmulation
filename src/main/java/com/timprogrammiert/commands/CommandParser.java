package com.timprogrammiert.commands;
import com.timprogrammiert.commands.cd.CdCommand;
import com.timprogrammiert.commands.ls.LsCommand;
import com.timprogrammiert.host.Host;
import java.util.*;

/**
 * @author tmatz
 */
public class CommandParser {
    private Host host;
    private Map<String, ICommand> commandMap;

    public CommandParser(Host host) {
        this.host = host;
        initCommands();
    }

    public void parseCommand(String[] args){
        commandMap.get(args[0]).execute(substractCommandName(args), host);
    }

    private void initCommands(){
        commandMap = new HashMap<>();
        commandMap.put("ls", new LsCommand());
        commandMap.put("cd", new CdCommand());
    }

    private String[] substractCommandName(String[] args){
        return Arrays.copyOfRange(args, 1, args.length);
    }


}
