package numbers;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        write your code here
        Game.welcomeUsers();
        Game game = new Game(Game.promptGame());
        if(!game.getInputString().equals("0"))
            do {
                if(game.checkEntry(game))
                    game.generateResult(game);
                game.setInputString(Game.promptGame());
            } while(!game.getInputString().equals("0"));
        System.out.println("\nGoodbye!");
    }
}

enum Properties {
    BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD, EVEN, ODD;
}

class Game {
    private String inputString;
    private Properties property;

    public Game(String number) {
        this.inputString = number;
    }

    public String getInputString() {
        return inputString;
    }

    public void setInputString(String number) {
        this.inputString = number;
    }

    public static String promptGame(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a request: ");
        return scanner.nextLine();
    }

    public static void welcomeUsers(){
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println("\nSupported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.\n");
    }

    public boolean checkEntry(Game game) {

        boolean exist;
        int errors = 0;

        StringBuilder sbErrorProperties = new StringBuilder();
        StringBuilder sbInputProperties = new StringBuilder();
        String[] vector2D = game.getInputString().split(" ");

        ArrayList<String> restrictions = new ArrayList<String>();
        ArrayList<String> propertiesX = new ArrayList<String>();
        ArrayList<String> propertiesY = new ArrayList<String>();

        restrictions.add(Properties.EVEN.name() + " " + Properties.ODD.name());
        restrictions.add(Properties.SPY.name() + " " + Properties.DUCK.name());
        restrictions.add(Properties.SQUARE.name() + " " + Properties.SUNNY.name());
        restrictions.add(Properties.HAPPY.name() + " " + Properties.SAD.name());
        restrictions.add("-" + Properties.EVEN.name() + " -" + Properties.ODD.name());


        for(int j = 0; j < vector2D.length; j++){
            String[] vector1D = vector2D[j].split("");

            if(j < 2) {
                if(vector1D[0].equals("0") && vector1D.length == 1){
                    if(j == 0) System.out.println("\nThe first parameter should be a natural number or zero.\n");
                    if(j == 1) System.out.println("\nThe second parameter should be a natural number.\n");
                    return false;
                }
                for(int i = 0; i < vector1D.length; i++) {
                    char caracter = vector1D[i].charAt(0);
                    int codigoAscii = Character.codePointAt(String.valueOf(caracter), 0);
                    if (!(codigoAscii >= 48 && codigoAscii <= 57)) {
                        if(j == 0) System.out.println("\nThe first parameter should be a natural number or zero.\n");
                        if(j == 1) System.out.println("\nThe second parameter should be a natural number.\n");
                        return false;
                    }
                }
            }

            if(j >= 2) {

                exist = false;
                Properties[] properties = Properties.values();
                for (Properties properEnum : properties)
                    if (vector2D[j].toString().charAt(0) == Character.valueOf('-')){
                        if(vector2D[j].toString().substring(1).toUpperCase().equals(properEnum.toString()))
                            exist = true;
                    } else {
                        if(vector2D[j].toString().substring(0).toUpperCase().equals(properEnum.toString()))
                            exist = true;
                    }

                if (!exist){
                    sbErrorProperties.append(vector2D[j].toUpperCase() + " ");
                    errors++;
                }
            }
        }

        if(errors > 0){
            System.out.print("\nThe propert");
            if(errors == 1) System.out.print("y [");
            if(errors >  1) System.out.print("ies [");
            System.out.print( game.printProperties(sbErrorProperties).toLowerCase() + "] ");
            if(errors == 1) System.out.print("is ");
            if(errors >  1) System.out.print("are ");
            System.out.println("wrong.");
            System.out.println("Available properties: ["
                    + Properties.BUZZ + ", "
                    + Properties.DUCK + ", "
                    + Properties.PALINDROMIC + ", "
                    + Properties.GAPFUL + ", "
                    + Properties.SPY + ", "
                    + Properties.SQUARE + ", "
                    + Properties.SUNNY + ", "
                    + Properties.JUMPING + ", "
                    + Properties.HAPPY + ", "
                    + Properties.SAD + ", "
                    + Properties.EVEN + ", "
                    + Properties.ODD + "]\n");
            return false;
        }

        if (game.getInputString().split(" ").length > 2){

            for (int k = 2; k < game.getInputString().split(" ").length; k++){
                sbInputProperties.append(game.getInputString().split(" ")[k].toUpperCase() + " ");

                if (game.getInputString().split(" ")[k].charAt(0) == '-'){
                    propertiesX.add(game.getInputString().split(" ")[k].substring(1));
                } else {
                    propertiesY.add(game.getInputString().split(" ")[k].substring(0));
                }
            }

            for (int i = 0; i < propertiesX.size() ; i++) {
                for (int j = 0; j < propertiesY.size(); j++) {
                    if (propertiesX.get(i).equals(propertiesY.get(j)) ) {
                        String dobleProperties = propertiesX.get(i).toString() + " -" +propertiesX.get(i).toString();
                        System.out.println("\nThe request contains mutually exclusive properties: " +
                                "[" + game.printProperties(new StringBuilder(dobleProperties)) + "]\n" +
                                "There are no numbers with these properties.\n");
                        return false;
                    }
                }
            }

            for (int i = 0; i < restrictions.size(); i++){
                if(isContains(restrictions.get(i).toString(), sbInputProperties.toString())){
                    System.out.println("\nThe request contains mutually exclusive properties: " +
                            "[" + game.printProperties(new StringBuilder(restrictions.get(i).toString())) + "]\n" +
                            "There are no numbers with these properties.\n");
                    return false;
                }
            }

        }
        return true;
    }

    public boolean isSquare(String string){
        double raiz = Math.sqrt(Long.valueOf(string));
        int raizEntera = (int) raiz;
        if(raizEntera * raizEntera == Long.valueOf(string)) return true;
        else return false;
    }

    public boolean isSunny(String string){
        double raiz = Math.sqrt(Long.valueOf(string) + 1);
        int raizEntera = (int) raiz;
        return raizEntera * raizEntera == (Long.valueOf(string) + 1);
    }

    public boolean isEven(String string){
        if(Long.valueOf(string) % 2 == 0) return true;
        else return false;
    }

    public boolean isOdd(String string){
        if(Long.valueOf(string) % 2 == 0) return false;
        else return true;
    }

    public boolean isBuzz(String string){
        long num = Long.valueOf(string.split(" ")[0]);
        if((num % 7) == 0 || (num % 10) == 7) return true;
        else return false;
    }

    public boolean isDuck(String numberOfDigits){
        String[] vectorNumberOfDigits = numberOfDigits.split("");

        for(int i = 1; i < vectorNumberOfDigits.length; i++) {
            char caracter = vectorNumberOfDigits[i].charAt(0);
            int codigoAscii = Character.codePointAt(String.valueOf(caracter), 0);
            if (codigoAscii == 48) return true;
        }

        return false;
    }

    public boolean isPalindromic(String string){
        String[] vector = string.split("");
        for(int i = 0, j = vector.length - 1; i < vector.length && j >= 0; i++, j--) {
                if(!(vector[i].equals(vector[j])))
                    return false;
        }
        return true;
    }

    public boolean isGapful(String string){
        String[] vector = string.split("");
        if(vector.length >= 3) {
            long fulNunber = Long.valueOf(string);
            StringBuilder sb = new StringBuilder();
            sb.append(vector[0]);
            sb.append(vector[vector.length - 1]);
            long divNumber = Long.valueOf(sb.toString());
            if(fulNunber % divNumber == 0) { return true; } else { return  false; }
        } else { return false; }
    }

    public boolean isSpy(String string){
        String[] vector = string.split("");
        long sum = 0;
        long product = 1;
        for (int i = 0; i < vector.length; i++){
            sum = sum + Long.valueOf(vector[i]);
            product = product * Long.valueOf(vector[i]);
        }
        if(sum == product) return true; else return false;
    }

    public boolean isJumping(String string){
        String[] vector = string.split("");
        for(int i = 0; i < vector.length - 1; i++) {
            if(Math.abs(Long.valueOf(vector[i]) - Long.valueOf(vector[i +  1])) != 1) return false;
        }
        return true;
    }

    public boolean isHappy(String string) {
        long slow = Long.valueOf(string);
        long fast = Long.valueOf(string);

        do {
            slow = sumOfSquares(slow);
            fast = sumOfSquares(sumOfSquares(fast));
        } while (slow != fast);

        return slow == 1;
    }

    private static long sumOfSquares(long number) {
        int sum = 0;
        while (number != 0) {
            long digit = number % 10;
            sum += digit * digit;
            number /= 10;
        }
        return sum;
    }

    public boolean isSad(String string){
        if(isHappy(string)) return false;
        else return true;
    }

    public String printProperties(StringBuilder sb){
        StringBuilder sbOther = new StringBuilder();
        String[] properties = sb.toString().split(" ");
        sb.delete(0,sb.length());
        for(String i : properties) {
            sb.append(i + ", ");
        }

        for (int i = 0; i < sb.length() - 2; i++) {
            //System.out.print(sb.charAt(i));
            sbOther.append(sb.charAt(i));
        }
        sb.delete(0,sb.length());
        return sbOther.toString();
    }



    public boolean isContains(String strA, String strB){//A contains to B //includedProperties, generateProperties
        String[] vectorA =  strA.toUpperCase().split(" ");
        String[] vectorB = strB.toUpperCase().split(" ");

        int count = 0;
        if (strA.equals("")) return true;

        for(int i = 0; i < vectorA.length; i++) {
            for (int j = 0; j < vectorB.length; j++) {
                if (vectorA[i].toString().equals(vectorB[j].toString())){
                    count++;
                    break;
                }
            }
        }

        return count == vectorA.length;
    }

    public boolean isNotContains(String strA, String strB){//A contains to B
        String[] vectorA =  strA.toUpperCase().split(" ");
        String[] vectorB = strB.toUpperCase().split(" ");
        int count = 0;

        for(int i = 0; i < vectorA.length; i++){
            for (int j = 0; j < vectorB.length; j++){
                if(vectorA[i].toString().equals(vectorB[j].toString()))
                    count++;
            }
        }
        return count == 0;
    }

    public void generateResult(Game game){

        if(game.getInputString().split(" ").length == 1){
            System.out.println("\nProperties of " + game.getInputString());
            System.out.println("buzz: "           + game.isBuzz(game.getInputString()));
            System.out.println("duck: "           + game.isDuck(game.getInputString()));
            System.out.println("palindromic: "    + game.isPalindromic(game.getInputString()));
            System.out.println("gapful: "         + game.isGapful(game.getInputString()));
            System.out.println("spy: "            + game.isSpy(game.getInputString()));
            System.out.println("square: "         + game.isSquare(game.getInputString()));
            System.out.println("sunny: "          + game.isSunny(game.getInputString()));
            System.out.println("jumping: "        + game.isJumping(game.getInputString()));
            System.out.println("happy: "          + game.isHappy(game.getInputString()));
            System.out.println("sad: "            + game.isSad(game.getInputString()));
            System.out.println("even: "           + game.isEven(game.getInputString()));
            System.out.println("odd: "            + game.isOdd(game.getInputString()) + "\n");
        }

        if (game.getInputString().split(" ").length >= 2){

            StringBuilder inputProperties = new StringBuilder();
            StringBuilder sbIncludeProperties = new StringBuilder();
            StringBuilder sbExcludeProperties = new StringBuilder();

            StringBuilder generatedProperties = new StringBuilder();

            if (game.getInputString().split(" ").length >= 3)
                for (int k = 2; k < game.getInputString().split(" ").length; k++)
                    if (game.getInputString().split(" ")[k].toUpperCase().charAt(0) == '-'){
                        sbExcludeProperties.append(game.getInputString().split(" ")[k].toUpperCase().substring(1) + " ");
                    } else {
                        sbIncludeProperties.append(game.getInputString().split(" ")[k].toUpperCase().substring(0) + " ");
                    }


            long initialValue = Long.valueOf(game.getInputString().split(" ")[0]);
            long count = initialValue;
            long finalValue = Long.valueOf(game.getInputString().split(" ")[0]) + Integer.valueOf(game.getInputString().split(" ")[1]);
            do {

                if (game.isBuzz(String.valueOf(initialValue)))        generatedProperties.append("buzz ");
                if (game.isDuck(String.valueOf(initialValue)))        generatedProperties.append("duck ");
                if (game.isPalindromic(String.valueOf(initialValue))) generatedProperties.append("palindromic ");
                if (game.isGapful(String.valueOf(initialValue)))      generatedProperties.append("gapful ");
                if (game.isSpy(String.valueOf(initialValue)))         generatedProperties.append("spy ");
                if (game.isSquare(String.valueOf(initialValue)))      generatedProperties.append("square ");
                if (game.isSunny(String.valueOf(initialValue)))       generatedProperties.append("sunny ");
                if (game.isJumping(String.valueOf(initialValue)))     generatedProperties.append("jumping ");
                if (game.isHappy(String.valueOf(initialValue)))       generatedProperties.append("happy ");
                if (game.isSad(String.valueOf(initialValue)))         generatedProperties.append("sad ");
                if (game.isEven(String.valueOf(initialValue)))        generatedProperties.append("even ");
                if (game.isOdd(String.valueOf(initialValue)))         generatedProperties.append("odd ");

                if(game.isContains(sbIncludeProperties.toString(),generatedProperties.toString()) &&
                        game.isNotContains(sbExcludeProperties.toString().toUpperCase(),generatedProperties.toString().toUpperCase()) ||
                        game.getInputString().split(" ").length == 2){
                    System.out.print("\n" + initialValue + " is " + game.printProperties(generatedProperties));
                    count ++;
                }

                initialValue++;
                generatedProperties.delete(0,generatedProperties.length());

            } while (count != finalValue);
            System.out.println("\n");
        }

    }

}
