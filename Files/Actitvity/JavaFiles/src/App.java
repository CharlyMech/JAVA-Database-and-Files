import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static Scanner s;

    public static void main(String[] args) throws Exception {
        // Create Scanner for user CLI input
        App.s = new Scanner(System.in);
        // Boolean variable for while menu loop
        boolean flag = true;

        // Welcome
        System.out.println("-------------------------------------");
        System.out.println("- Welcome to File treatment in JAVA -");
        System.out.println("-------------------------------------");

        // MENU -
        while (flag) {
            System.out.println("Select one of these options:");
            System.out.println("\t1) Read & write (byte Stream)");
            System.out.println("\t2) Read & write (character Stream)");
            System.out.println("\t3) Read & write (buffer Streams)");
            System.out.println("\t4) OPTATIVE");
            System.out.println("\t5) Exit");
            System.out.print("INPUT: ");
            String input = App.s.next();

            int inputInt = 0;
            try {
                inputInt = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("The input must be a number (Integer)");
            }

            switch (inputInt) {
                case 1:
                    App.option1();
                    break;
                case 2:
                    App.option2();
                    break;
                case 3:
                    App.option3();
                    break;
                case 4:
                    // App.optative();
                    System.out.println("Not available");
                    break;
                case 5:
                    System.out.println("BYE BYE");
                    flag = false;
                    App.s.close();
                    break;
                default:
                    System.out.println("Not a valid value, Try again!");
                    break;
            }
        }
        // - MENU
    }

    // Option  1 - Byte Stream
    public static void option1() {
        // Flag for the menu
        boolean flag1 = true;
        // String to get the file text
        String text = "";

        System.out.println("- Read & write File with byte Streams -");
        // MENU -
        while (flag1) {
            System.out.println("Select one of these options:");
            System.out.println("\t1) Read");
            System.out.println("\t2) Write");
            System.out.println("\t3) Exit");
            System.out.print("INPUT: ");
            String input = App.s.next();

            // Check if the user input is a integer value
            int inputInt = 0; // Default value
            try {
                inputInt = Integer.parseInt(input); // Parse from String to Integer/int
            } catch (NumberFormatException e) {
                System.out.println("The input must be a number (Integer)");
            }

            switch (inputInt) {
                case 1:
                    try (FileInputStream fin = new FileInputStream("files/input.txt")) { // Create InputStream inside the Try-Catch block
                        int i; // This variable will store the ascii/unicode character code of every character in the read file
                        do { // Loop through all char
                            i = fin.read(); // Store the character code
                            if (i != -1) { // If there is file to read
                                text += (char) i; // Cast the unicode code to a char value and concatenate to the string
                            }
                        } while (i != -1); // When the iterator (character code) is -1 means that the file has ended
                    } catch (IOException ioe) {
                        System.out.println("READ FILE OPT 1 ERROR " + ioe);
                    }
                    text = App.prettifytext(text); // Prettify the text
                    System.out.println(text); // Show the prettified text
                    break;
                case 2:
                    if (text.equals("")) { // Check if the text is empty
                        System.out.println("Please first read the file");
                        break;
                    } else { // Text has been prettified
                        try (FileOutputStream fos = new FileOutputStream("files/output_byteStream.txt")) { // Create FileOutputStream to write the String content
                            byte[] bytes = text.getBytes(); // Get bytes list from String
                            fos.write(bytes); // Write content
                            System.out.println("File created successfully");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    System.out.println("BYE BYE");
                    flag1 = false;
                    break;
                default:
                    System.out.println("Not a valid value, Try again!");
                    break;
            }
        }

        // - MENU
    }

    // Option  2 - character Stream
    public static void option2() throws IOException {
        // Flag for the menu
        boolean flag2 = true;
        // String variable to store the prettify text
        String text = "";
        // Instanciate the writer -> This is for the append option, if it's not defined like this the file will be overwritten
        FileWriter writer = new FileWriter("files/output_characterStream.txt");

        System.out.println("- Read & write File with character Streams -");
        // MENU -
        while (flag2) {
            System.out.println("Select one of these options:");
            System.out.println("\t1) Read");
            System.out.println("\t2) Write");
            System.out.println("\t3) Append");
            System.out.println("\t4) Exit");
            System.out.print("INPUT: ");
            String input = App.s.next();

            // Check if the user input is a integer value
            int inputInt = 0; // Default value
            try {
                inputInt = Integer.parseInt(input); // Parse from String to Integer/int
            } catch (NumberFormatException e) {
                System.out.println("The input must be a number (Integer)");
            }

            switch (inputInt) {
                case 1:
                    try {
                        FileReader reader = new FileReader("files/input.txt"); // Create FileReader
                        int data = reader.read(); // Read data from file -> Again this stores the unicode/ascii character code

                        while (data != -1) { // If data == -1 means that file has ended
                            text += (char) data; // Concatenate the character to the String variable
                            data = reader.read(); // This line allows to read the next character until it ends
                        }

                        reader.close(); // Close reader
                        text = App.prettifytext(text); // Prettify text
                        System.out.println(text); // Show text
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case 2:
                    if (text.equals("")) { // Check if the text is empty
                        System.out.println("Please first read the file");
                        break;
                    } else { // Text has been prettified
                        try {
                            writer.write(text); // Write String content in the file 
                            // ? (notice that since writer is defined at the begining, the file is created at first)
                            System.out.println("File created successfully");
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                    break;
                case 3: // FileWriter allows to append content
                    System.out.print("Insert the text you want to add to the file: ");
                    String appendInput = s.next(); // Read user input -> Remember that Scanner only allows to read one single word
                    appendInput = "\n" + appendInput; // Add a line break

                    try {
                        writer.append(appendInput); // Append new content

                        System.out.println("Append successful!");
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    break;
                case 4:
                    System.out.println("BYE BYE");
                    writer.close();
                    flag2 = false;
                    break;
            }
        }
        // - MENU
    }

    // Option  3 - buffer Stream
    public static void option3() throws IOException {
        // Flag for the menu
        boolean flag3 = true;
        // String variable to store the prettify text
        String text = "";

        System.out.println("- Read & write File with buffered Streams -");
        // MENU -
        while (flag3) {
            System.out.println("Select one of these options:");
            System.out.println("\t1) Read");
            System.out.println("\t2) Write");
            System.out.println("\t3) Exit");
            System.out.print("INPUT: ");
            String input = App.s.next();

            // Check if the user input is a integer value
            int inputInt = 0; // Default value
            try {
                inputInt = Integer.parseInt(input); // Parse from String to Integer/int
            } catch (NumberFormatException e) {
                System.out.println("The input must be a number (Integer)");
            }

            switch (inputInt) {
                case 1:
                    try {
                        BufferedReader br = new BufferedReader(new FileReader("files/input.txt")); // Create BufferedReader

                        String s; // This variable will store every character one by one, but this time as a String datatype
                        while ((s = br.readLine()) != null) { // If s == null means that file has ended
                            text += s; // Concatenate s values to String variable
                        }

                        br.close(); // Close Reader
                        text = App.prettifytext(text); // Prettify text
                        System.out.println(text); // Show prettified text
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case 2:
                    if (text.equals("")) { // Check if the text is empty
                        System.out.println("Please first read the file");
                        break;
                    } else { // Text has been prettified
                        try (BufferedWriter bw = new BufferedWriter( // Create the BufferedReader inside the Try-Catch block
                                new FileWriter("files/output_bufferedStream.txt"));) {
                            bw.write(text); // Write String variable content

                            System.out.println("File created successfully");
                            bw.close(); // Close writer
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                    break;
                case 3:
                    System.out.println("BYE BYE");
                    flag3 = false;
                    break;
            }
        }
        // - MENU
    }

    // Option  4
    public static void optative() { // ! NOT DONE FOR NOW
        // Flag for the menu
        boolean flago = true;

        System.out.println("-  -");
        // MENU -
        while (flago) {
            System.out.println("Select one of these options:");
            System.out.println("\t1) Read");
            System.out.println("\t2) Write");
            System.out.println("\t3) Exit");
            System.out.print("INPUT: ");
            String input = App.s.next();

            int inputInt = 0;
            try {
                inputInt = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("The input must be a number (Integer)");
            }

            switch (inputInt) {
                case 1:
                    System.out.println("READ FILE");
                    break;
                case 2:
                    System.out.println("WRITE FILE");
                    break;
                case 3:
                    System.out.println("BYE BYE");
                    flago = false;
                    break;
            }
        }
        // - MENU
    }

    // Method to create the output text (prettify)
    public static String prettifytext(String input) {
        /*
            This method has the only purpose to take a string (in all cases the raw input.txt) and transform it to the desired look
            showed in the activity statement
        
            There are 2 important thinks to say:
                - '{' delimiter in the original file has been replaced for ';' because it seems that you cannot use the split() method with this symbol
                - The second Film's title is OFF, I tried to solve that line break but I wasn't able 
        */
        // Returning String
        String pretty = "--------------------------------------\n\n" +
                "\tCartelera de Cine"
                + "\n\n--------------------------------------\n\n";

        // Split Films
        String[] films = input.split(";"); // Split films

        // Loop over all films in input String[] list
        for (String f : films) {
            String[] film = f.split("#"); // Split film information
            String title = film[0];
            title.replace("\n", " "); // Just in case
            String year = film[1]; // Leave it as a String because there's no need to make calculations, only string concatenation
            String director = film[2];
            director.replace("\n", " "); // Just in case
            String duration = film[3]; // Same as year
            String synopsis = film[4];
            String cast = film[5];
            cast.replace("n", " "); // Just in case
            String session = film[6];

            // Concatenate String with film info
            pretty += "-----" + title + "-----\n";
            pretty += "Año: " + year + "\n";
            pretty += "Director: " + director + "\n";
            pretty += "Duración: " + duration + "\n";
            pretty += "Sinopsis: " + synopsis + "\n";
            pretty += "Reparto: " + cast + "\n";
            pretty += "Sesión: " + session + " horas\n\n";
        }

        pretty += "--------------------------";

        return pretty;
    }
}
