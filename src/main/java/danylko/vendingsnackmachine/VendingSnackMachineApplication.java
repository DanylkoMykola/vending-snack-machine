package danylko.vendingsnackmachine;

import danylko.vendingsnackmachine.command.Command;
import danylko.vendingsnackmachine.command.CommandHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootApplication
public class VendingSnackMachineApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(VendingSnackMachineApplication.class, args);
    }

    @Override
    public void run(String... args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] arguments;
        String str = "";
        String commandStr = "";
        System.out.println("\nPlease start inputting your command. If you need help input \"help\":");
        try {
            while(!(str = reader.readLine()).equals("exit")) {
                if (!str.isEmpty()) {
                    arguments = str.split(" ");
                    commandStr = arguments[0];
                    Command command = CommandHandler.commands.get(commandStr);
                    if (command != null) {
                        command.execute(str);
                    }
                    else {
                        System.out.println("Enter correct command:a");
                    }
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
