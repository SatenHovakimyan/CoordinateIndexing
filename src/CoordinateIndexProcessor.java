import java.util.*;

public class CoordinateIndexProcessor {
    private Map<String, TreeMap<Integer, TreeSet<Integer>>> coordinateIndex = new TreeMap<>();

    public void addCoordinateIndex(String term, int documentID, List<Integer> positions) {
        if (!coordinateIndex.containsKey(term)) {
            coordinateIndex.put(term, new TreeMap<>());
        }
        TreeMap<Integer, TreeSet<Integer>> termIndices = coordinateIndex.get(term);
        if (!termIndices.containsKey(documentID)) {
            termIndices.put(documentID, new TreeSet<>());
        }
        TreeSet<Integer> documentPositions = termIndices.get(documentID);
        documentPositions.addAll(positions);
    }

    public Map<String, TreeMap<Integer, TreeSet<Integer>>> getCoordinateIndex() {
        return coordinateIndex;
    }
    public void displayCoordinateIndex() {
        for (String term : coordinateIndex.keySet()) {
            System.out.println(term + ":");
            TreeMap<Integer, TreeSet<Integer>> treeIndices = coordinateIndex.get(term);
            for (int documentID : treeIndices.keySet()) {
                System.out.println(documentID + ": " + treeIndices.get(documentID));
            }
        }
    }

    public TreeSet<Integer> getPositionsForTermAndDocID(String term, int documentID) {
        if (coordinateIndex.containsKey(term)) {
            TreeMap<Integer, TreeSet<Integer>> termIndices = coordinateIndex.get(term);
            if (termIndices.containsKey(documentID)) {
                return termIndices.get(documentID);
            }
        }
        return new TreeSet<>();
    }

    public TreeSet<Integer> getDocumentIDsForTerm(String term) {
        TreeMap<Integer, TreeSet<Integer>> termIndices = coordinateIndex.get(term);
        if(termIndices == null || termIndices.isEmpty()) {
            return new TreeSet<>();
        }
        return new TreeSet<>(termIndices.keySet());
    }

    public TreeSet<Integer> getDocumentIDsForAllTerms(List<String> terms) {
        TreeSet<Integer> documentIDs = new TreeSet<>();

        if (terms.isEmpty()) {
            return documentIDs;
        }

        String firstTerm = terms.iterator().next();
        documentIDs.addAll(getDocumentIDsForTerm(firstTerm));

        for (String term : terms) {
            // Intersection of documentIDs and the current term's document IDs
            documentIDs.retainAll(getDocumentIDsForTerm(term));
        }
        return documentIDs;
    }
}
