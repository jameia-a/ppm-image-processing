import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            System.out.print("Enter the input PPM file: ");
            String inputFile = keyboard.nextLine();

            System.out.print("Enter the output PPM file: ");
            String outputFile = keyboard.nextLine();
            
            in = new FileInputStream(inputFile);
            out = new FileOutputStream(outputFile);

            PPMFilter filter = new PPMFilter(in, out);

            System.out.println("Choose a filter:");
            System.out.println("1. Red");
            System.out.println("2. Green");
            System.out.println("3. Blue");
            System.out.println("4. Greyscale");
            System.out.println("5. Invert");
            System.out.println("Enter 1 through 5: ");

            int choice = keyboard.nextInt();

            if (choice ==1) {
                filter.redFilter();
            } else if (choice == 2) {
                filter.greenFilter(); 
            } else if (choice == 3){
                filter.blueFilter();
            } else if (choice == 4){
                filter.greyscaleFilter();
            } else if (choice == 5){
                filter.invertFilter();
            } else {
                System.out.println("Invalid choice.");
            }

            }
        
        catch (IOException e) {
            e.printStackTrace();
        }
    finally {
        try{
            if (in != null) {
                in.close();
            }
            
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        keyboard.close();
    }
}
}
