package danylko.vendingsnackmachine.command;

import danylko.vendingsnackmachine.console.ConsoleWriter;
import danylko.vendingsnackmachine.console.handler.ConsoleHandler;
import org.springframework.stereotype.Component;

@Component
public class HelpCommand implements Command {

    private final String NAME_COMMAND = "help";

    public HelpCommand() {
        CommandHandler.commands.put(NAME_COMMAND, this);
    }

    @Override
    public void execute(String args) {
        ConsoleWriter.println(ConsoleHandler.HELP);
        for (String command : CommandHandler.commands.keySet()) {
            ConsoleWriter.println("\t"+command);
        }
    }
}
