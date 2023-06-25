package DocDivider;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Map;

public interface IDocDivider {
    void divideDoc(Map<String, LinkedList<String>> data, File file) throws FileNotFoundException;
}
