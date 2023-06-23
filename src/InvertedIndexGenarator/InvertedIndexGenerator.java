package InvertedIndexGenarator;
import Const.Constants;
import WordProcessor.WordProcessingThread;
import WordProcessor.WordProcessingThreadParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class InvertedIndexGenerator implements IInvertedListGenerator {

    @Override
    public Map<String , LinkedList<String>> generate(Map<String, LinkedList<String>> data, LinkedList<String> tokens) {
        Map<String, LinkedList<String>> invertedIndex = new HashMap<>();

        final Integer[] index = {tokens.size()};
        ArrayList<Thread> threads = new ArrayList<>();
        for (String word : tokens) {
            if (Constants.STOP_WORDS.contains(word) || word.isBlank()) continue;
            WordProcessingThread thread = new WordProcessingThread(new WordProcessingThreadParams(index[0]--,data, word, invertedIndex));
            threads.add(thread);
            thread.start();

        }
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return invertedIndex;

    }
}
