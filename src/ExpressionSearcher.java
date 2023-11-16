import java.util.*;

public class ExpressionSearcher {
    private final CoordinateIndexProcessor coordinateIndexProcessor;

    public ExpressionSearcher(CoordinateIndexProcessor coordinateIndexProcessor) {
        this.coordinateIndexProcessor = coordinateIndexProcessor;
    }

    public List<Integer> findDocumentWithExpression(String expression) {
        List<String> expressionTerms = TextTokenizer.tokenizeText(expression);

        if (expressionTerms.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression format. Use 'word1 word2' or 'word /N word2' format.");
        }

        if (expressionTerms.size() == 1) {
            // Search for a single word in documents
            return findDocIDsWithSingleWord(expressionTerms.get(0));
        } else if (TextTokenizer.isDistancePattern(expression)) {
            return findDocIDsWithDistanceExpression(expressionTerms, expression);
        } else {
            return findDocIDsWithSimpleExpression(expressionTerms);
        }
    }

    private List<Integer> findDocIDsWithSingleWord(String word) {

        List<Integer> result = new ArrayList<>(coordinateIndexProcessor.getDocumentIDsForTerm(word));

        return result;
    }

    private List<Integer> findDocIDsWithSimpleExpression(List<String> expressionTerms) {
        List<Integer> result = new ArrayList<>();

        // Find common document IDs where all expressionTerms are present
        Set<Integer> commonDocumentIDs = coordinateIndexProcessor.getDocumentIDsForAllTerms(expressionTerms);

        for (int docID : commonDocumentIDs) {
            if (areTokensInCorrectOrder(expressionTerms, docID)) {
                result.add(docID);
            }
        }
        return result;
    }

    private List<Integer> findDocIDsWithDistanceExpression(List<String> expressionTerms, String expression) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> commonDocumentIDs = coordinateIndexProcessor.getDocumentIDsForAllTerms(TextTokenizer.tokenizeTextWithoutNumbers(expression));

        for (int docID : commonDocumentIDs) {
            if (areTokensInCorrectDistance(expressionTerms, docID)) {
                result.add(docID);
            }
        }

        return result;
    }

    private boolean areTokensInCorrectOrder(List<String> expressionTokens, int docID) {
        int numTerms = expressionTokens.size();
        int countOfTrues = 0;
        int matchedPos = 0; // ete gtav vor barery irar hajordum en, pahuma bari positiony vor heto et hamemati hajordi het

        for (int i = 1; i < numTerms; i++) {
            String currentTerm = expressionTokens.get(i);
            String previousTerm = expressionTokens.get(i - 1);

            TreeSet<Integer> positionsCurrent = coordinateIndexProcessor.getPositionsForTermAndDocID(currentTerm, docID);
            TreeSet<Integer> positionsPrevious = coordinateIndexProcessor.getPositionsForTermAndDocID(previousTerm, docID);

            // Check if the current token's position is one greater than the previous token's position
            if (positionsCurrent.isEmpty() || positionsPrevious.isEmpty()) {
                return false;
            }

            if (countOfTrues == 0) {
                for (int currentPos : positionsCurrent) {
                    if (positionsPrevious.contains(currentPos - 1)) {
                        countOfTrues++;
                        matchedPos = currentPos;
                        break;
                    }
                }
            } else {
                if (positionsCurrent.contains(matchedPos + 1)) {
                    countOfTrues++;
                    matchedPos++;
                }
            }
        }
        return countOfTrues == numTerms - 1;
    }

    private boolean areTokensInCorrectDistance(List<String> expressionTokens, int docID) {
        int numTerms = expressionTokens.size();
        int countOfTrues = 0;
        int distance = 0;

        for (int i = 1; i < numTerms; i += 2) {
            distance = Integer.parseInt((expressionTokens.get(i).substring(1)));
            String currentTerm = expressionTokens.get(i + 1);
            String previousTerm = expressionTokens.get(i - 1);

            TreeSet<Integer> positionsCurrent = coordinateIndexProcessor.getPositionsForTermAndDocID(currentTerm, docID);
            TreeSet<Integer> positionsPrevious = coordinateIndexProcessor.getPositionsForTermAndDocID(previousTerm, docID);

            // Check if the current token's position is one greater than the previous token's position
            if (positionsCurrent.isEmpty() || positionsPrevious.isEmpty()) {
                return false;
            }


            for (int currentPos : positionsCurrent) {
                if (positionsPrevious.contains(currentPos + distance) || positionsPrevious.contains(currentPos - distance)) {
                    countOfTrues++;
                    break;
                }
            }
        }
        return countOfTrues == numTerms / 2;
    }

    private List<Integer> findCommonDocumentIDs(List<String> expressionTokens) {
        // Initialize the commonDocumentIDs with the document IDs of the first token
        Set<Integer> commonDocumentIDs = new HashSet<>(coordinateIndexProcessor.getPositionsForTermAndDocID(expressionTokens.get(0), 1));

        for (int i = 1; i < expressionTokens.size(); i++) {
            String word = expressionTokens.get(i);
            TreeSet<Integer> positions = coordinateIndexProcessor.getPositionsForTermAndDocID(word, 1);

            // Calculate the intersection of commonDocumentIDs and the current word's document IDs
            commonDocumentIDs.retainAll(positions);
        }

        return new ArrayList<>(commonDocumentIDs);
    }

}
