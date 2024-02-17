import java.util.Scanner;

public class Generator
{
    Alphabet alphabet;
    public static Scanner keyboard;

    public Generator(Scanner scanner) {
        keyboard = scanner;
    }

    public Generator(boolean IncludeUpper, boolean IncludeLower, boolean IncludeNum, boolean IncludeSym) {
        alphabet = new Alphabet(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
    }

    public void mainLoop() {
        System.out.println();
        System.out.println("Welcome to Credential Manager ;)\n--------Select 1,2,3,4,5--------");
        printMenu();

        String userOption = "-1";

        while (!userOption.equals("5")) {

            userOption = keyboard.next();

            switch (userOption) {
                case "1" -> {
                    requestPassword();
                    printMenu();
                }
                case "2" -> {
                    checkPassword();
                    printMenu();
                }
                case "3" -> {
                    System.out.println("\nThis Feature is Under Development, Sorry for the inconvienence");
                    printMenu();
                }
                case "4" -> {
                    printInfo();
                    printMenu();
                }
                case "5" -> printQuit();
                
                default -> {
                    System.out.println();
                    System.out.println("Please Select one of the given Commands");
                    printMenu();
                }
            }
        }
    }

    Password GeneratePassword(int length) {
        final StringBuilder pass = new StringBuilder("");

        final int alphabetLength = alphabet.getAlphabet().length();

        int max = alphabetLength - 1;
        int min = 0;
        int range = max - min + 1;

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * range) + min;
            pass.append(alphabet.getAlphabet().charAt(index));
        }

        return new Password(pass.toString());
    }

    private void printInfo() {
        System.out.println();
        System.out.print(
            "~ Aim for passwords with a minimum length of 8 characters, or longer if allowed.\n" +
            "~ Utilize a combination of lowercase and uppercase letters, numbers, and symbols.\n" +
            "~ Avoid using the same password for multiple accounts or systems.\n" +
            "~ Opt for randomly generated passwords whenever possible.\n" +
            "~ Steer clear of repeating characters, common keyboard patterns, dictionary words, or sequential characters.\n" +
            "~ Refrain from incorporating personal information such as usernames, names (including pets), romantic references, or biographical details.\n" +
            "~ Avoid using information that could be easily guessed by colleagues or acquaintances.\n" +
            "~ Do not rely solely on simple combinations of the aforementioned weak components for your passwords.\n");
        }


    private void requestPassword() {
        boolean IncludeUpper = false;
        boolean IncludeLower = false;
        boolean IncludeNum = false;
        boolean IncludeSym = false;

        boolean correctParams;

        System.out.println();
        System.out.print("Hello, welcome to the Password Generator :) answer the following questions by Yes or No (y/n)\n\n");

        do {
            String input;
            correctParams = false;

            do {
                System.out.print("Do you want Lowercase letters \"abcd...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));

            if (isInclude(input)) IncludeLower = true;

            do {
                System.out.print("Do you want Uppercase letters \"ABCD...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));

            if (isInclude(input)) IncludeUpper = true;

            do {
            System.out.print("Do you want Numbers \"1234...\" to be used? ");
            input = keyboard.next();
            PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));

            if (isInclude(input)) IncludeNum = true;

            do {
            System.out.print("Do you want Symbols \"!@#$...\" to be used? ");
            input = keyboard.next();
            PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));

            if (isInclude(input)) IncludeSym = true;

            //No Pool Selected
            if (!IncludeUpper && !IncludeLower && !IncludeNum && !IncludeSym) {
                System.out.println("You have selected no characters to generate your password, at least one of your answers should be Yes\n");
                correctParams = true;
            }

        } while (correctParams);

        System.out.println("Great! Good Job");
        System.out.print("Now enter the length of the password: ");

        int length = keyboard.nextInt();

        final Generator generator = new Generator(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
        final Password password = generator.GeneratePassword(length);

        System.err.println("Your generated password -> " + password);
    }

    private boolean isInclude(String Input) {
        if (Input.equalsIgnoreCase("y")) {
            return true;
        } 
        else {
            return false;
        }
    }

    private void PasswordRequestError(String i) {
        if (!i.equalsIgnoreCase("y") && !i.equalsIgnoreCase("n")) {
            System.out.println("You have entered something incorrect let's go over it again \n");
        }
    }

    private void checkPassword() {
        String input;

        System.out.print("\nEnter your password:");
        input = keyboard.next();

        final Password p = new Password(input);
        System.out.println(p.calculateScore());
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1 - Password Generator");
        System.out.println("2 - Password Strength Check");
        System.out.println("3 - Password Viewer (Under Progress)");
        System.out.println("4 - Useful Information");
        System.out.println("5 - Quit");
        System.out.println();
        System.out.print("Choice: ");
    }

    private void printQuit() {
        System.out.println("Adios, Closing...");
        System.exit(0);
    }
}
