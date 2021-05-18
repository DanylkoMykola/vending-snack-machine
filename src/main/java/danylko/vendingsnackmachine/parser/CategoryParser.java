package danylko.vendingsnackmachine.parser;

public class CategoryParser {

    public static String parseCategory(String args) {
        String category = "";
        if(args == null || args.isEmpty()) {
            return category;
        }
        if (args.contains(" ")) {
            category = args.substring(0 ,args.indexOf(" "));
        }
        else {
            category = args;
        }
        return category;
    }
}
