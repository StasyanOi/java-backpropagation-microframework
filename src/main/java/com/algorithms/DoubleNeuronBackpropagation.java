package com.algorithms;

import static com.utils.ErrorsUtils.mse;
import static com.utils.MathUtils.average;

public class DoubleNeuronBackpropagation {

    private double[] line(double w, double[] x, double b) {
        double[] y = new double[x.length];
        for (int i = 0; i < y.length; i++) {
            y[i] = w * x[i] + b;
        }
        return y;
    }

    private double updateWeight(double initial, double partial_derivative, double learning_rate) {
        return initial - partial_derivative * learning_rate;
    }

    private double[] partialW(double[] input, double[] y_real, double[] y_calc) {
        double[] partial_w = new double[input.length];
        for (int i = 0; i < partial_w.length; i++) {
            partial_w[i] = (-2 * y_real[i] + 2 * y_calc[i]) * input[i];
        }
        return partial_w;
    }

    private double[] partialB(double[] y_real, double[] y_calc) {
        double[] partial_b = new double[y_real.length];
        for (int i = 0; i < partial_b.length; i++) {
            partial_b[i] = (-2 * y_real[i] + 2 * y_calc[i]);
        }
        return partial_b;
    }

    private double[] sum(double[] array1, double[] array2) {
        double[] array3 = new double[array1.length];
        for (int i = 0; i < array1.length; i++) {
            array3[i] = array1[i] + array2[i];
        }
        return array3;
    }

    private double[] minus(double[] array1, double[] array2) {
        double[] array3 = new double[array1.length];
        for (int i = 0; i < array1.length; i++) {
            array3[i] = array1[i] - array2[i];
        }
        return array3;
    }

    private double[] divide(double[] array1, double[] array2) {
        double[] array3 = new double[array1.length];
        for (int i = 0; i < array1.length; i++) {
            array3[i] = array1[i] / array2[i];
        }
        return array3;
    }

    private double[] deltaInitial(double[] y_real, double[] y_calc) {
        double[] delta = new double[y_real.length];
        for (int i = 0; i < delta.length; i++) {
            delta[i] = (-2 * y_real[i] + 2 * y_calc[i]);
        }
        return delta;
    }

    private double[] multiply(double[] array1, double[] array2) {
        double[] array3 = new double[array1.length];
        for (int i = 0; i < array1.length; i++) {
            array3[i] = array1[i] * array2[i];
        }
        return array3;
    }

    private double[] multiply(double[] array1, double num) {
        double[] array3 = new double[array1.length];
        for (int i = 0; i < array1.length; i++) {
            array3[i] = array1[i] * num;
        }
        return array3;
    }

    public double[][] trainModel(double[] input, double[] expected_output, double[][] parameters, int epochs, double learning_rate) {
        double[][] layer_outputs = new double[3][input.length];

        for (int i = 0; i < epochs; i++) {

            double[] calculated_output = forwardPropagate(input, parameters, layer_outputs);

            double mse = mse(expected_output, calculated_output);

            System.out.println("mse: " + mse);

            //update w2
            double[] delta_w2 = deltaInitial(expected_output, calculated_output);
            double[] partial_derivative_w2 = multiply(delta_w2, layer_outputs[1]);
            parameters[1][0] = updateWeight(parameters[1][0], average(partial_derivative_w2), learning_rate);

            //update b2
            double[] delta_b2 = deltaInitial(expected_output, calculated_output);
            double[] partial_derivative_b2 = multiply(delta_b2, 1);
            parameters[1][1] = updateWeight(parameters[1][1], average(partial_derivative_b2), learning_rate);

            //update w1
            double[] delta_w1 = multiply(delta_w2, parameters[1][0]);
            double[] partial_derivative_w1 = multiply(delta_w1, layer_outputs[0]);
            parameters[0][0] = updateWeight(parameters[0][0], average(partial_derivative_w1), learning_rate);

            //update b1
            double[] delta_b1 = multiply(delta_b2, parameters[1][1]);
            double[] partial_derivative_b1 = multiply(delta_b1, 1);
            parameters[0][1] = updateWeight(parameters[0][1], average(partial_derivative_b1), learning_rate);
        }

        return parameters;
    }

    public double[] forwardPropagate(double[] input, double[][] parameters, double[][] layer_outputs) {
        double[] calculated_output = input;
        layer_outputs[0] = input;
        for (int j = 0; j < parameters.length; j++) {
            double[] output = line(parameters[j][0], calculated_output, parameters[j][1]);
            layer_outputs[j + 1] = output;
            calculated_output = output;
        }
        return calculated_output;
    }
}
