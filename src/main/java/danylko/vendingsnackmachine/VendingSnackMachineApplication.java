package danylko.vendingsnackmachine;

import danylko.vendingsnackmachine.command.Command;
import danylko.vendingsnackmachine.command.CommandHandler;
import danylko.vendingsnackmachine.console.ConsoleWriter;
import danylko.vendingsnackmachine.console.handler.ConsoleHandler;
import danylko.vendingsnackmachine.parser.CommandParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class VendingSnackMachineApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(VendingSnackMachineApplication.class, args);
    }

    @Override
    public void run(String... args) {
        String str = "";
        String commandStr = "";
        ConsoleWriter.println(ConsoleHandler.START_LINE);
        ConsoleWriter.print("> ");
        try ( BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while(!(str = reader.readLine().trim()).equals("exit")) {
                commandStr = CommandParser.parseCommand(str);
                Command command = CommandHandler.commands.get(commandStr);
                if (command != null) {
                    command.execute(CommandParser.removeCharBiggerThen(str));
                }
                else {
                    ConsoleWriter.println(ConsoleHandler.NO_EXISTING_COMMAND);
                }
                ConsoleWriter.print("> ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
