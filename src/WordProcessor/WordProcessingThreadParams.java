package WordProcessor;

import java.util.LinkedList;
import java.util.Map;

public record WordProcessingThreadParams(int number,Map<String, LinkedList<String>> data, String word,
                                         Map<String, LinkedList<String>> invertedIndex) {
}