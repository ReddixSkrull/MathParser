package de.doering;

import de.doering.mathinterpreter.interpreter.MathInterpreter;

public class Main {


    public static void main(String[] args) {
        MathInterpreter mathInterpreter = new MathInterpreter();
        mathInterpreter.calculateFile("src/main/resources/example2.txt");
    }
}