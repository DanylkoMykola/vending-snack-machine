package danylko.vendingsnackmachine.parser;

public class CategoryParser {

    public static String parseCategory(String args) {
        String category = "";
        if(args == null || args.isEmpty()) {
            return category;
        }
        String trimArgs = args.trim();
        if (trimArgs.contains(" ")) {
            category = trimArgs.substring(0 ,trimArgs.indexOf(" "));
        }
        else {
            category = trimArgs;
        }
        return category;
    }
}
