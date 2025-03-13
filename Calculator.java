import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Calculator {

    private static final List<String> history = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCalculating = true;

        while (continueCalculating) {
            System.out.println("\nCalculator Menu:");
            System.out.println("1. Perform Calculation");
            System.out.println("2. View History");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = getIntInput(scanner);

            switch (choice) {
                case 1:
                    performCalculation(scanner);
                    break;
                case 2:
                    viewHistory();
                    break;
                case 3:
                    continueCalculating = false;
                    System.out.println("Exiting Calculator. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void performCalculation(Scanner scanner) {
        System.out.print("Enter calculation (e.g., 2 + 3, power(2,3), sqrt(16), abs(-5), round(3.14)): ");
        String input = scanner.nextLine();

        try {
            double result = evaluateExpression(input);
            System.out.println("Result: " + result);
            history.add(input + " = " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static double evaluateExpression(String expression) {
        expression = expression.trim();

        if (expression.contains("(")) { // Function call
            if (expression.startsWith("power")) {
                return handlePower(expression);
            } else if (expression.startsWith("sqrt")) {
                return handleSqrt(expression);
            } else if (expression.startsWith("abs")) {
                return handleAbs(expression);
            } else if (expression.startsWith("round")) {
                return handleRound(expression);
            } else {
                throw new IllegalArgumentException("Invalid function: " + expression.substring(0, expression.indexOf("(")));
            }
        } else { // Basic arithmetic
            return handleBasicArithmetic(expression);
        }
    }

    private static double handleBasicArithmetic(String expression) {
        try {
            String[] parts = expression.split(" ");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid arithmetic expression format.");
            }

            double num1 = Double.parseDouble(parts[0]);
            String operator = parts[1];
            double num2 = Double.parseDouble(parts[2]);

            switch (operator) {
                case "+": return num1 + num2;
                case "-": return num1 - num2;
                case "*": return num1 * num2;
                case "/":
                    if (num2 == 0) throw new IllegalArgumentException("Cannot divide by zero.");
                    return num1 / num2;
                case "%": return num1 % num2;
                default: throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format.");
        }
    }


    private static double handlePower(String expression) {
        try {
            String[] parts = extractArguments(expression, "power");
            double base = Double.parseDouble(parts[0]);
            double exponent = Double.parseDouble(parts[1]);
            return Math.pow(base, exponent);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in power function.");
        }
    }

    private static double handleSqrt(String expression) {
        try {
            String[] parts = extractArguments(expression, "sqrt");
            double number = Double.parseDouble(parts[0]);
            if (number < 0) throw new IllegalArgumentException("Cannot take square root of a negative number.");
            return Math.sqrt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in sqrt function.");
        }
    }

    private static double handleAbs(String expression) {
        try {
            String[] parts = extractArguments(expression, "abs");
            double number = Double.parseDouble(parts[0]);
            return Math.abs(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in abs function.");
        }
    }

    private static double handleRound(String expression) {
        try {
            String[] parts = extractArguments(expression, "round");
            double number = Double.parseDouble(parts[0]);
            return Math.round(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in round function.");
        }
    }


    private static String[] extractArguments(String expression, String functionName) {
        int startIndex = expression.indexOf("(") + 1;
        int endIndex = expression.indexOf(")");
        if (startIndex == 0 || endIndex == -1) {
            throw new IllegalArgumentException("Invalid function call format: " + functionName);
        }

        String arguments = expression.substring(startIndex, endIndex);
        return arguments.split(",");
    }

    private static void viewHistory() {
        if (history.isEmpty()) {
            System.out.println("No calculations in history.");
        } else {
            System.out.println("\nCalculation History:");
            for (int i = 0; i < history.size(); i++) {
                System.out.println((i + 1) + ". " + history.get(i));
            }
        }
    }

    private static int getIntInput(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.print("Invalid input. Please enter an integer: ");
            scanner.next(); // Consume the invalid input
            return getIntInput(scanner); // Recursive call for valid input
        } finally {
          scanner.nextLine(); // Consume newline left by nextInt()
        }
    }
}