package WordProcessor;

import WordProcessor.WordProcessingThreadParams;

import java.util.LinkedList;
import java.util.Map;

public class WordProcessingThread extends Thread  {
    private final int number;

    public int getNumber() {
        return number;
    }


    private final Map<String, LinkedList<String>> data;
    private final String word;
    private final Map<String, LinkedList<String>> invertedIndex ;

    public WordProcessingThread( WordProcessingThreadParams wordProcessingThreadParams) {
        this.number = wordProcessingThreadParams.number();
        this.data = wordProcessingThreadParams.data();
        this.word = wordProcessingThreadParams.word();
        this.invertedIndex = wordProcessingThreadParams.invertedIndex();
    }

    @Override
    public void run() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.printf(number + " %s\n", word);
        LinkedList<String> docs = getDocs(data, word);
        invertedIndex.put(word, docs);
    }
    private LinkedList<String> getDocs(Map<String, LinkedList<String>> data, String word) {
        LinkedList<String> docs = new LinkedList<>();
        for (String docsName : data.keySet()) {
            LinkedList<String> words = data.get(docsName);
            if (words.contains(word)) {
                docs.add(docsName);
            }
        }
        return docs;
    }
}
