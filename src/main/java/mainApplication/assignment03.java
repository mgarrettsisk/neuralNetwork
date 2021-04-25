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
                {1,0,0,0,0},
                {1,0,0,0,1},
                {1,0,0,1,0},
                {1,0,0,1,1},
                {1,0,1,0,0},
                {1,0,1,0,1},
                {1,0,1,1,0},
                {1,0,1,1,1},
                {1,1,0,0,0},
                {1,1,0,0,1},
                {1,1,0,1,0},
                {1,1,0,1,1},
                {1,1,1,0,0},
                {1,1,1,0,1},
                {1,1,1,1,0},
                {1,1,1,1,1}
        };

        Scanner programControl = new Scanner(System.in);
        int runProgram = -1;
        System.out.println("**************** Simple Artificial Neural Network ****************");
        System.out.println("*                      Marion Garrett Sisk                       *");
        System.out.println("*                     CS 7375 Assignment 03                      *");
        System.out.println("******************************************************************");

        while (runProgram != 0) // whole program runs in a while loop dependent on value of runProgram
        {
            // Main Menu Dialog
            System.out.println();
            System.out.println("Current Parameters: ");
            System.out.println("Learning Rate: " + learnRate);
            System.out.println("Training Epochs: " + epochs);
            System.out.println();
            System.out.println("-------- Main Menu --------");
            System.out.println("\t1: Change Learning Rate");
            System.out.println("\t2: Change total training epochs");
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
                    Scanner learning = new Scanner(System.in);
                    System.out.print("Enter a new learning rate between 0 and 1: ");
                    learnRate = learning.nextDouble();
                    System.out.println("The learning rate is now " + learnRate);
                    System.out.println();
                    break;
                case(2):
                    Scanner epochNum = new Scanner(System.in);
                    System.out.print("Enter a new training epoch limit (integer values): ");
                    epochs = epochNum.nextInt();
                    System.out.println("The training epoch limit is now " + epochs);
                    System.out.println();
                    break;
                case(3):
                    System.out.println("Training the neural network using the training set.");
                    ann.train(trainSet,epochs,learnRate);
                    break;
                case(4):
                    System.out.println("Iterating through all test scenarios.");
                    for (int i = 0; i < testSet.length; i++) {
                        System.out.println("**********************************");
                        System.out.println("Test scenario " + i);
                        //System.out.println(testSet[i].toString());
                        if (isDark(testSet[i])) {
                            System.out.println("Manual Determination = Dark");
                        } else {
                            System.out.println("Manual Determination = Light");
                        }
                        System.out.println("*** ANN Determinations");
                        displayType(ann.feedForward(testSet[i]));
                        System.out.println();
                        System.out.println();
                    }
                    break;
            }
            System.out.println();
        }
    }

    public static void displayType(double[] results) {
        // this method takes the results from a feed forward operation and prints a result to the console
        System.out.println("Light: " + results[1]);
        System.out.println("Dark: " + results[0]);
        if (results[0] > results[1]) {
            System.out.print("Image is Dark.");
        } else
            System.out.print("Image is Light.");
    }

    public static boolean isDark(double[] testInput) {
        // this method takes a sum of entries of the array to manually check whether the image should be light
        // or dark. This is used to compare the determination done by the ANN
        double arraySum = 0;
        for (int j = 0; j < testInput.length; j++) {
            arraySum = arraySum + testInput[j];
        }
        if (arraySum > 3) {
            return true;
        } else {
            return false;
        }
    }
}

