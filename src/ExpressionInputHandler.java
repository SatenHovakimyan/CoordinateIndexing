import java.util.List;
import java.util.Scanner;

class ExpressionInputHandler {
    private final ExpressionSearcher searcher;

    public ExpressionInputHandler(ExpressionSearcher searcher) {
        this.searcher = searcher;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter an expression (or 'exit' to quit): ");
            String expression = scanner.nextLine();
            if (expression.equalsIgnoreCase("exit")) {
                break; // Exit the loop if the user enters 'exit'
            }
            List<Integer> documentsWithExpression = searcher.findDocumentWithExpression(expression);
            System.out.println("Documents containing the expression: " + documentsWithExpression);
        }

        scanner.close();
    }
}
