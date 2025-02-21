import java.util.Stack;
import java.util.ArrayList;

public class Shunting {
    private static int getPrecedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return -1;
        }
    }
    // https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String infixToPostfix(String expression) {
        ArrayList<String> output = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        
        String[] tokens = expression.trim().split(" ");
        
        for (String token : tokens) {
            System.out.println("Token: " + token);
            
            if (isNumeric(token)) {
                output.add(token);
            }
            else if (token.equals("(")) {
                stack.push(token);
            }
            else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                stack.pop(); 
            }
            else {
                while (!stack.isEmpty() && getPrecedence(stack.peek()) >= getPrecedence(token)) {
                    output.add(stack.pop());
                }
                stack.push(token);
            }
            
            System.out.println("Output stack: " + output);
            System.out.println("Operator stack: " + stack);
        }
        
        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }
        
        return output;
    }
}