import java.util.ArrayList;
import java.util.Stack;

public class Calculator {
    public static double evaluate(String evaluation){
        ArrayList<String> result = Shunting.InfixToPostfix(evaluation);
        //https://www.geeksforgeeks.org/evaluation-of-postfix-expression/
        Stack<Double> stack = new Stack<>();
        for (String token : result) {
            if (Shunting.isNumeric(token)) {
                stack.push(Double.parseDouble(token));
            } else {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(operand1 + operand2);
                        break;
                    case "-":
                        stack.push(operand1 - operand2);
                        break;
                    case "*":
                        stack.push(operand1 * operand2);
                        break;
                    case "/":
                        stack.push(operand1 / operand2);
                        break;
                    case "^":
                        stack.push(Math.pow(operand1, operand2));
                        break;
                }
            }
        }
        return stack.pop();
    }
    public static void main(String []args) {
        evaluate("3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3");
    }
}
