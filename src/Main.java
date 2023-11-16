public class Main {
    public static void main(String[] args) {
        CoordinateIndexProcessor indexProcessor = new CoordinateIndexProcessor();
        DataInitializer.initializeCoordinateIndices(indexProcessor);

        indexProcessor.displayCoordinateIndex();

        ExpressionSearcher expressionSearcher = new ExpressionSearcher(indexProcessor);

        ExpressionInputHandler expressionInputHandler = new ExpressionInputHandler(expressionSearcher);
        expressionInputHandler.start();
    }
}