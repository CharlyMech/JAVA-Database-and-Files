import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // Create Scanner for user CLI input
        Scanner s = new Scanner(System.in);
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
            String input = s.next();

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
                    // App.option2();
                    break;
                case 3:
                    // App.option3();
                    break;
                case 4:
                    // App.optative();
                    break;
                case 5:
                    System.out.println("BYE BYE");
                    flag = false;
                    s.close();
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
        // Create another Scanner for Option 1
        Scanner s1 = new Scanner(System.in);
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
            int input = s1.nextInt();

            switch (input) {
                case 1:
                    try (FileInputStream fin = new FileInputStream("files/input.txt")) {
                        int i;
                        do {
                            i = fin.read();
                            if (i != -1) {
                                text += (char) i;
                            }
                        } while (i != -1);
                    } catch (IOException ioe) {
                        System.out.println("READ FILE OPT 1 ERROR " + ioe);
                    }
                    text = App.prettifytext(text);
                    System.out.println(text);
                    break;
                case 2:
                    if (text.equals("")) {
                        System.out.println("Please first read the file");
                        break;
                    } else {
                        try (FileOutputStream fos = new FileOutputStream("files/output_byteStream.txt")) {
                            byte[] bytes = text.getBytes();
                            fos.write(bytes);
                            System.out.println("Archivo creado exitosamente.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    System.out.println("BYE BYE");
                    flag1 = false;
                    s1.close();
                    break;
                default:
                    System.out.println("Not a valid value, Try again!");
                    break;
            }
        }

        // - MENU
    }

    // Option  2 - character Stream
    public static void option2() {
        // Create another Scanner for Option 2
        Scanner s2 = new Scanner(System.in);
        // Flag for the menu
        boolean flag2 = true;

        System.out.println("- Read & write File with character Streams -");
        // MENU -
        while (flag2) {
            System.out.println("Select one of these options:");
            System.out.println("\t1) Read");
            System.out.println("\t2) Write");
            System.out.println("\t3) Exit");
            System.out.print("INPUT: ");
            int input = s2.nextInt();

            switch (input) {
                case 1:
                    System.out.println("READ FILE");
                    break;
                case 2:
                    System.out.println("WRITE FILE");
                    break;
                case 3:
                    System.out.println("BYE BYE");
                    flag2 = false;
                    s2.close();
                    break;
            }
        }
        // - MENU
    }

    // Option  3 - buffer Stream
    public static void option3() {
        // Create another Scanner for Option 3
        Scanner s3 = new Scanner(System.in);
        // Flag for the menu
        boolean flag3 = true;

        System.out.println("- Read & write File with buffer Streams -");
        // MENU -
        while (flag3) {
            System.out.println("Select one of these options:");
            System.out.println("\t1) Read");
            System.out.println("\t2) Write");
            System.out.println("\t3) Exit");
            System.out.print("INPUT: ");
            int input = s3.nextInt();

            switch (input) {
                case 1:
                    System.out.println("READ FILE");
                    break;
                case 2:
                    System.out.println("WRITE FILE");
                    break;
                case 3:
                    System.out.println("BYE BYE");
                    flag3 = false;
                    s3.close();
                    break;
            }
        }
        // - MENU
    }

    // Option  4
    public static void optative() {
        // Create another Scanner for Option 4
        Scanner so = new Scanner(System.in);
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
            int input = so.nextInt();

            switch (input) {
                case 1:
                    System.out.println("READ FILE");
                    break;
                case 2:
                    System.out.println("WRITE FILE");
                    break;
                case 3:
                    System.out.println("BYE BYE");
                    flago = false;
                    so.close();
                    break;
            }
        }
        // - MENU
    }

    // Method to create the output text (prettify)
    public static String prettifytext(String input) {
        // Returning String
        String pretty = "--------------------------------------\n\n" +
                "\tCartelera de Cine"
                + "\n\n--------------------------------------\n\n";

        // Split Films
        String[] films = input.split(";");

        // Loop over all films in input String[] list
        for (String f : films) {
            String[] film = f.split("#");
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
