# java-backpropagation-microframework

com.algorithms.Microframework for creating multilayer perceptrons (MSE is used as default error).

## Sample usage

```java
package com;

import com.algorithms.Perceptron;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class Main {

    public static void main(String[] args) {
        Perceptron perceptron = new Perceptron(2, new int[]{2, 3, 3, 3, 2});

        RealMatrix andInput = getAndInputNew();
        RealMatrix andOutput = getAndOutputNew();

        for (int i = 0; i < 1000; i++) {
            perceptron.trainOnBatchBsgd(andInput, andOutput);
        }
        System.out.println(perceptron.feedForwardVector(andInput.getColumnMatrix(0)));
    }

    public static RealMatrix getAndOutputNew() {
        RealMatrix output = MatrixUtils.createRealMatrix(2, 4);
        output.setEntry(0, 0, 0);
        output.setEntry(1, 0, 0);
        output.setEntry(0, 1, 0);
        output.setEntry(1, 1, 0);
        output.setEntry(0, 2, 0);
        output.setEntry(1, 2, 0);
        output.setEntry(0, 3, 1);
        output.setEntry(1, 3, 1);
        return output;
    }

    public static RealMatrix getAndInputNew() {
        RealMatrix input = MatrixUtils.createRealMatrix(2, 4);
        input.setEntry(0, 0, 0);
        input.setEntry(1, 0, 0);
        input.setEntry(0, 1, 0);
        input.setEntry(1, 1, 1);
        input.setEntry(0, 2, 1);
        input.setEntry(1, 2, 0);
        input.setEntry(0, 3, 1);
        input.setEntry(1, 3, 1);
        return input;
    }
}

```

