package danylko.vendingsnackmachine.command;

import org.springframework.stereotype.Component;

@Component
public interface Command {
    void execute(String args);
}
