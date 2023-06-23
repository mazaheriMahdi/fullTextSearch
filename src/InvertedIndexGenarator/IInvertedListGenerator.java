package InvertedIndexGenarator;

import java.util.LinkedList;
import java.util.Map;

public interface IInvertedListGenerator {
    Map<String  , LinkedList<String>> generate(Map<String, LinkedList<String>> data, LinkedList<String> tokens);
}
