package mainApplication;

import java.util.Scanner;

public class assignment03 {

    public static void main(String[] args) {
        // this is the entry point to the assignment03 application to train
        // sentinel loop that allows for user to run program again, or end process

        // Create variables for the program to function correctly
        neuralNetwork ann = new neuralNetwork();
        double learnRate = 0.1;
        int epochs = 50000;
        double[][] trainSet = {
                {1,0,0,0,0,0,1},
                {1,0,0,0,1,0,1},
                {1,0,0,1,0,0,1},
                {1,0,0,1,1,0,1},
                {1,0,1,0,0,0,1},
                {1,0,1,0,1,0,1},
                {1,0,1,1,0,0,1},
                {1,0,1,1,1,1,0},
                {1,1,0,0,0,0,1},
                {1,1,0,0,1,0,1},
                {1,1,0,1,0,0,1},
                {1,1,0,1,1,1,0},
                {1,1,1,0,0,0,1},
                {1,1,1,0,1,1,0},
                {1,1,1,1,0,1,0},
                {1,1,1,1,1,1,0}
        };

        double[][] testSet = {
                {1,0,0,0,1},
                {1,0,0,1,0},
                {1,0,1,0,0},
                {1,1,0,0,0},
                {1,0,0,0,1},
                {1,0,0,1,1},
                {1,0,1,1,0},
                {1,1,1,0,0},
                {1,0,1,1,1},
                {1,1,1,1,0},
                {1,1,1,1,1},
                {1,0,0,0,1},
        };

        Scanner programControl = new Scanner(System.in);
        int runProgram = -1;
        System.out.println("**************** Simple Artificial Neural Network ****************");
        System.out.println("*                      Marion Garrett Sisk                       *");
        System.out.println("*                     CS 7375 Assignment 03                      *");
        System.out.println("******************************************************************");

        while (runProgram != 0) // whole program runs in a while loop dependent on value of runProgram
        {
            // *****************  START OF PROGRAM CODE

            // Main Menu Dialog
            System.out.println();
            System.out.println("Current Parameters: ");
            System.out.println("Learning Rate: " + learnRate);
            System.out.println("Training Epochs: " + epochs);
            System.out.println();
            System.out.println("-------- Main Menu --------");
            System.out.println("\t1: View Test Set");
            System.out.println("\t2: View Training Set");
            System.out.println("\t3: Train Artificial Neural Network");
            System.out.println("\t4: Test Artificial Neural Network");
            System.out.println("\t0: Exit Program");
            System.out.println();
            System.out.print("Enter your choice: "); // prompt user for their choice
            runProgram = programControl.nextInt(); // overwrite runProgram string w/ entered value
            System.out.println();

            // Program modes, based upon which
            switch(runProgram) {
                case(1):
                    System.out.println("This is option 1.");
                    break;
                case(2):
                    System.out.println("This is option 2.");
                    break;
                case(3):
                    System.out.println("Training the neural network using the training set.");
                    ann.train(trainSet,epochs,learnRate);
                    break;
                case(4):
                    System.out.println("Test Artificial Neural Network with Test Set entry.");
                    displayType(ann.feedForward(testSet[8]));
                    break;
            }

            // *****************  END OF PROGRAM CODE

            System.out.println();
        }
    }

    public static void displayType(double[] results) {
        // this method takes the results from a feed forward operation and prints a result to the console
        if (results[0] > results[1]) {
            System.out.print("Image is Dark.");
        } else
            System.out.print("Image is Light.");
    }
}

