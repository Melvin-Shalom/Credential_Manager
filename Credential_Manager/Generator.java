import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.NoSuchElementException;
import java.util.Scanner;
import static java.lang.System.*;

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
    out.println();
    out.println("Welcome to Credential Manager ;)");
    printMenu();

    String userOption = "-1";

    while (!userOption.equals("5")) {

        try {
        userOption = keyboard.next();
        }
        catch (NoSuchElementException e) {
            err.println("Input error: " + e.getMessage());
        }

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
                encDecry();
                printMenu();
            }
            case "4" -> {
                printInfo();
                printMenu();
            }
            case "5" -> printQuit();
            
            default -> {
                out.println();
                printMenu();
                out.println("Please Select one of the given Commands");
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


private void encDecry(){

    while (true) {
        out.println();
        out.print("Choose an option (view, add, x): ");
        String mode;
        
        do {
            mode = keyboard.nextLine().trim().toLowerCase();
        } while (mode.isEmpty());

        if (mode.equals("view") || mode.equals("add")) {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request;
            if (mode.equals("view")) {
                out.print("Master Password: ");
                String n = keyboard.nextLine();
                if (n.equals("spark")) {
                    request = HttpRequest.newBuilder()
                            .uri(URI.create("http://localhost:5000/view"))
                            .GET()
                            .build();
                }
                else {
                    out.println("Wromg PassKey");
                    continue;
                }
            }
            else { // mode.equals("add")
                out.print("Username: ");
                String name = keyboard.nextLine();
                out.print("Password: ");
                String password = keyboard.nextLine();
                String requestBody = String.format("{\"name\": \"%s\", \"password\": \"%s\"}", name, password);

                request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:5000/add"))
                        .header("Content-Type", "application/json")
                        .POST(BodyPublishers.ofString(requestBody))
                        .build();
            }
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                out.println("Response body: " + response.body());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (mode.equals("x")) {
            break;
        }
        else {
            out.println("Invalid Mode.");
        }
    }
}


private void printInfo() {
    out.println();
    out.print(
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

    out.println();
    out.print("Hello, welcome to the Password Generator :) answer the following questions by Yes or No (y/n)\n\n");

    do {
        String input;
        correctParams = false;

        do {
            out.print("Do you want Lowercase letters \"abcd...\" to be used? ");
            input = keyboard.next();
            PasswordRequestError(input);
        } while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));

        if (isInclude(input)) IncludeLower = true;

        do {
            out.print("Do you want Uppercase letters \"ABCD...\" to be used? ");
            input = keyboard.next();
            PasswordRequestError(input);
        } while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));

        if (isInclude(input)) IncludeUpper = true;

        do {
        out.print("Do you want Numbers \"1234...\" to be used? ");
        input = keyboard.next();
        PasswordRequestError(input);
        } while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));

        if (isInclude(input)) IncludeNum = true;

        do {
        out.print("Do you want Symbols \"!@#$...\" to be used? ");
        input = keyboard.next();
        PasswordRequestError(input);
        } while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));

        if (isInclude(input)) IncludeSym = true;

        //No Pool Selected
        if (!IncludeUpper && !IncludeLower && !IncludeNum && !IncludeSym) {
            out.println("You have selected no characters to generate your password, at least one of your answers should be Yes\n");
            correctParams = true;
        }

    } while (correctParams);

    out.println("Great! Good Job");
    out.print("Now enter the length of the password: ");

    int length = keyboard.nextInt();

    final Generator generator = new Generator(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
    final Password password = generator.GeneratePassword(length);

    out.println("\nYour generated password -> " + password);
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
        out.println("You have entered something incorrect let's go over it again \n");
    }
}

private void checkPassword() {
    String input;

    out.print("\nEnter your password: ");
    input = keyboard.next();

    final Password p = new Password(input);
    out.println(p.calculateScore());
}

private void printMenu() {
    out.println();
    out.println("------- Select 1,2,3,4,5 -------");
    out.println();
    out.println("1 - Password Generator");
    out.println("2 - Password Evaluator");
    out.println("3 - Password Organiser");
    out.println("4 - Useful Information");
    out.println("5 - Quit");
    out.println();
    out.print("Choice: ");
}

private void printQuit() {
    out.println("Adios, Closing......");
    exit(0);
}

}
