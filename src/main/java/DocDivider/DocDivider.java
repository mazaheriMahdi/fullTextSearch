package DocDivider;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class DocDivider implements IDocDivider{
    public void divideDoc(Map<String, LinkedList<String>> data, File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        StringBuilder content = new StringBuilder();
        while (reader.hasNextLine()) {
            content.append(reader.nextLine());
        }
        LinkedList<String> words = new LinkedList<>(Arrays.asList(content.toString().toUpperCase().split("\\s|'|\\.|!|\"|\"|,|:|;|\\?|\\(|\\)|\\[|\\]|\\{|\\}| |\\\\* |\\*|/|\\\\")));
        data.put(file.getName(), words);
    }
}
