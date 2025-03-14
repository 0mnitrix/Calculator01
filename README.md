# Console Calculator

A simple, console-based calculator program implemented in Java. This calculator supports basic arithmetic operations, several advanced functions, calculation history, and robust error handling.

## Features

*   **Basic Arithmetic:** Supports addition (`+`), subtraction (`-`), multiplication (`*`), division (`/`), and modulus (`%`) operations.
*   **Advanced Functions:** Includes `power(base, exponent)`, `sqrt(number)`, `abs(number)`, and `round(number)` functions.
*   **Error Handling:** Gracefully handles invalid inputs (e.g., non-numeric input, division by zero, invalid function calls).
*   **Calculation History:** Records past calculations and allows the user to view them.
*   **User-Friendly Interface:** Provides a clear and intuitive console-based menu and prompts.

## How to Use

1.  **Clone the Repository:**

    ```bash
    git clone [repository_url]
    cd ConsoleCalculator
    ```

2.  **Compile the Java Code:**

    ```bash
    javac Calculator.java
    ```

3.  **Run the Program:**

    ```bash
    java Calculator
    ```

4.  **Interact with the Calculator:**

    *   The program will display a menu with options to perform a calculation, view history, or exit.
    *   To perform a calculation, enter `1`. You will then be prompted to enter an expression.
    *   Valid expressions include:
        *   Basic arithmetic: `2 + 3`, `10 - 5`, `4 * 6`, `20 / 4`, `15 % 2`
        *   Functions: `power(2, 3)`, `sqrt(16)`, `abs(-7)`, `round(3.14)`
    *   To view calculation history, enter `2`.
    *   To exit the calculator, enter `3`.
