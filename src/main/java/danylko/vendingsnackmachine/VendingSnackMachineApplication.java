package danylko.vendingsnackmachine;

import danylko.vendingsnackmachine.command.AddCategoryCommand;
import danylko.vendingsnackmachine.command.Command;
import danylko.vendingsnackmachine.command.CommandHandler;
import danylko.vendingsnackmachine.service.ProductService;
import danylko.vendingsnackmachine.service.ProductServiceJPAImpl;
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] arguments;
        String str = "";
        String command = "";
        try {
            while((str = reader.readLine()) != null) {
                arguments = str.split(" ");
                if(arguments.length > 1)
                command = arguments[0];
                CommandHandler.commands.get(command).execute(str);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
