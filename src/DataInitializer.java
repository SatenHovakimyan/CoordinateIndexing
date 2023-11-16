import java.util.*;

public class DataInitializer {
    public static void initializeCoordinateIndices(CoordinateIndexProcessor indexProcessor) {
        // Term: "hello"
        indexProcessor.addCoordinateIndex("hello", 1, Arrays.asList(36, 78, 170, 251, 458));
        indexProcessor.addCoordinateIndex("hello", 4, Arrays.asList(12, 22, 64, 102));
        indexProcessor.addCoordinateIndex("hello", 8, List.of(17));

        // Term:
        // "to"
        indexProcessor.addCoordinateIndex("to", 1, Arrays.asList(47, 86, 234, 999));
        indexProcessor.addCoordinateIndex("to", 4, Arrays.asList(14, 24, 774, 944));
        indexProcessor.addCoordinateIndex("to", 8, Arrays.asList(199, 319, 589, 608));

        // Term: "world"
        indexProcessor.addCoordinateIndex("world", 1, Arrays.asList(47, 87, 704, 722, 901));
        indexProcessor.addCoordinateIndex("world", 4, Arrays.asList(8, 23, 43, 68, 103));
        indexProcessor.addCoordinateIndex("world", 8, Arrays.asList(19, 189, 618));

        // Term: "where"
        indexProcessor.addCoordinateIndex("where", 1, Arrays.asList(3, 5, 19, 46, 147, 201, 341, 387));
        indexProcessor.addCoordinateIndex("where", 4, Arrays.asList(7, 24, 58, 111, 321));

        // Term: "full"
        indexProcessor.addCoordinateIndex("full", 1, Arrays.asList(1, 17, 74, 158, 222));
        indexProcessor.addCoordinateIndex("full", 4, Arrays.asList(8, 74, 106, 287, 450));
        indexProcessor.addCoordinateIndex("full", 8, Arrays.asList(5, 78, 188));

        // Term: "go"
        indexProcessor.addCoordinateIndex("go", 1, Arrays.asList(2, 13, 25, 46, 96, 307));
        indexProcessor.addCoordinateIndex("go", 4, Arrays.asList(58, 96, 108, 368));
        indexProcessor.addCoordinateIndex("go", 8, Arrays.asList(69, 421));

        // Term: "school"
        indexProcessor.addCoordinateIndex("school", 1, Arrays.asList(10, 31, 48, 135, 768));
        indexProcessor.addCoordinateIndex("school", 4, Arrays.asList(21, 47, 87, 99));
        indexProcessor.addCoordinateIndex("school", 8, Arrays.asList(8, 41, 198, 318, 487));
    }
}
