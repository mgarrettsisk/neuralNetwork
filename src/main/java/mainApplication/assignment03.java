package mainApplication;

import java.util.Scanner;

public class assignment03 {

    public static void main(String[] args) {
        // this is the entry point to the assignment03 application to train
        // sentinel loop that allows for user to run program again, or end process
        Scanner programControl = new Scanner(System.in);
        String runProgram = "y";

        while (runProgram.equalsIgnoreCase("y")) // whole program runs in a while loop dependent on value of runProgram
        {
            // *****************  START OF PROGRAM CODE

            // *****************  END OF PROGRAM CODE

            // Prompt user to continue running program or quit (changes value in sentinel loop)
            System.out.println();
            System.out.print("Do you want to run the program again? (y/n):"); // prompt user for their choice
            runProgram = programControl.next(); // overwrite runProgram string w/ entered value
            System.out.println();
        }
    }
}
