package mainApplication;

import org.ejml.simple.SimpleMatrix;

import java.util.Random;

public class neuralNetwork {
    // This class builds an artificial neural network with 4 input neurons (plus 1 bias neuron), 1 hidden neuron, and 2
    // output neurons. The test implementation allows for the determination of a 2x2 matrix with either 0 or 1 in each
    // cell. A 1 would correspond to a "dark" pixel, and a 0 would correspond to a "light" pixel. The ANN should be able
    // to determine whether a given combination of the 2x2 matrix is "light" or "dark" based on a training set.

    // attributes
    private double[] inputArray = {1,0,0,0,0};
    private double[] w1 = {0.0, 0., 0.5, 0.5, 0.5};
    private double a1 = 0.0;
    private double[] w2 = {0.5, 0.5};
    private double[] a2 = {0,0};



    neuralNetwork() {
        generateRandomWeights();
    }

    // public methods
    public double[] feedForward(double[] inputVector) {
        // For input layer to hidden layer, we multiply the input vector by the weight matrix and sum the values.
        double inputSum = 0.0;
        for (int i = 0; i < inputVector.length; i++) {
           inputSum = inputSum + inputVector[i]*this.w1[i];
        }
        // activation of hidden neuron from 5 input neurons
        a1 = sigmoid(inputSum);

        // From hidden layer (1 neuron) to output layer (2 neurons)
        a2[0] = sigmoid(a1*w2[0]);
        a2[1] = sigmoid(a1*w2[1]);

        // returns the output
        return a2;
    }

    public void train(double[][] trainingSet, int epochs, double learningRate) {
        // This method takes a training set of values and iterates over them using the backpropagation algorithm to set
        // the appropriate weights. It will iterate over the training set for the provided number of epochs.
        //
        //  The training set should have the following values:
        //
        //      T = {{bias, x0y0, x1y0, x0y1, x1y1, dark, light}, ... }
        //
        //  In this instance, the bias is always equal to 1. The dark or light is either a 1 or a 0 depending upon the
        //  configuration of the pixels themselves.

        int iteration = 0;

        while (iteration < epochs) {
            // for each element in the training set
            for (int i = 0; i < trainingSet.length; i++) {

                // run the test vector through the feedForward method and obtain results
                double[] results = feedForward(trainingSet[i]);

                // calculate error for each output
                double[] outputErrors = {
                        outputError(trainingSet[i][5], results[0]),
                        outputError(trainingSet[i][6], results[1])
                };

                // back propagate the errors to the hidden neuron unit
                // compute the weighted sum of errors from outputs
                double errorSum = outputErrors[0]*this.w2[0] + outputErrors[1]*this.w2[1];
                // compute the weighted error for back propagation to input neurons
                double inputError = this.a1*(1-this.a1)*errorSum;

                // compute the change in weights between inputs and hidden layer and adjust weights accordingly
                double[] inputDeltas = {
                        learningRate*inputError*trainingSet[i][0],
                        learningRate*inputError*trainingSet[i][1],
                        learningRate*inputError*trainingSet[i][2],
                        learningRate*inputError*trainingSet[i][3],
                        learningRate*inputError*trainingSet[i][4]
                };
                // alter weights in w1 with deltas from above
                for (int j = 0; j < w1.length; j++) {
                    this.w1[j] = this.w1[j] + inputDeltas[j];
                }

                // compute the change in weights between outputs and hidden layer and adjust weights accordingly
                double[] outputDeltas = {
                        learningRate*outputErrors[0]*this.a1,
                        learningRate*outputErrors[1]*this.a1
                };
                // alter weights in w2 with deltas from above
                for (int k = 0; k < w2.length; k++) {
                    this.w2[k] = this.w2[k] + outputDeltas[k];
                }
                // then repeat the process for the next set of test values
            }
            iteration++;
        }
    }

    // private methods
    private double sigmoid(double input) {
        // this method takes a double value as input and returns the equivalent sigmoid value as another double
        return (1/(1+Math.exp(-input)));
    }

    private double outputError(double expected, double actual) {
        // this method takes the expected value and the actual value and calculates the error based on the
        return actual*(1-actual)*(expected-actual);
    }

    private void generateRandomWeights() {
        // this method generates random starting weights for arrays w1 and w2 in this object. This is only called in the
        // constructor method upon instantiation
        Random rand = new Random();
        for (int i = 0; i < this.w1.length; i++) {
            this.w1[i] = (rand.nextDouble() - 0.5);
        }
        for (int i = 0; i < this.w2.length; i++) {
            this.w2[i] = (rand.nextDouble() - 0.5);
        }
    }
}
