package danylko.vendingsnackmachine.parser;

public class CommandParser {

    public static String parseCommand(String args) {
        String command = "";
        if(args == null || args.isEmpty()) {
            return command;
        }
        if (args.contains(" ")) {
            command = args.substring(0 ,args.indexOf(" "));
        }
        else {
            command = args;
        }
        return command;
    }
    public static String removeCharBiggerThen(String args){
        return args.substring(1).trim();
    }
}
