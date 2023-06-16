import java.io.*;
import java.util.*;

public class Main {
    static List<String> stopWords = List.of(
            "&", "a", "an", "the", "in", "on", "at",
            "to", "is", "are", "am", "was", "were",
            "be", "been", "being", "have", "has",
            "had", "do", "does", "did", "will", "would",
            "shall", "should", "may", "might", "must", "can",
            "could", "of", "for", "from", "by", "with", "about",
            "between", "among", "into", "onto", "upon", "after",
            "before", "above", "under", "below", "over", "under",
            "underneath", "since", "until", "till", "of", "off",
            "out", "up", "down", "over", "under", "underneath", "since"
            , "until", "till", "of", "off", "out", "up", "down", "over", "under"
            , "underneath", "since", "until", "till", "of", "off", "out", "up",
            "down", "over", "under", "underneath", "since", "until", "till", "of",
            "off", "out", "up", "down", "over", "under", "underneath", "since", "until",
            "till", "of", "off", "out", "up", "down", "over", "under", "underneath", "since",
            "until", "till", "of", "off", "out", "up", "down", "over", "under", "underneath",
            "since", "until", "till", "of", "off", "out", "up", "down", "over", "under", "underneath",
            "since", "until", "till", "of", "off", "out", "up", "down", "over", "under", "underneath", "since", "until", "till");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File folder = new File(scanner.nextLine());
        List<File> files = List.of(folder.listFiles());
        LinkedList<String> list = new LinkedList<>();
        Map<String, LinkedList<String>> data = new HashMap<>();
        for (File file : files) {
            try {

                Scanner reader = new Scanner(file);
                StringBuilder content = new StringBuilder();
                while (reader.hasNextLine()) {
                    content.append(reader.nextLine());
                }

                LinkedList<String> words = new LinkedList<>(Arrays.asList(content.toString().toUpperCase().split("\\s|'|\\.|!|\"|\"|,|:|;|\\?|\\(|\\)|\\[|\\]|\\{|\\}|\\*|/|\\\\")));
                data.put(file.getName(), words);
            } catch (Exception e) {
                System.out.println("File not found");
            }
        }

        LinkedList<String> tokens = new LinkedList<>();
        for (String docs : data.keySet()) {

            tokens.addAll(data.get(docs));
        }

        Map<String, LinkedList<String>> invertedIndex = new HashMap<>();
        final Integer[] index = {tokens.size()};


        ArrayList<Thread> threads = new ArrayList<>();
        for (String word : tokens) {
            if (stopWords.contains(word)) {
                continue;
            }

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Processing word: " + index[0]-- + "/" + tokens.size());
                    LinkedList<String> docs = new LinkedList<>();
                    for (String docsName : data.keySet()) {
                        if (data.get(docsName).contains(word)) {
                            docs.add(docsName);
                        }
                    }
                    invertedIndex.put(word, docs);
                }
            });
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
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("data.txt"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(invertedIndex);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            System.out.println("Enter word to search: ");
            String search = scanner.nextLine();
            LinkedList<String> result = new LinkedList<>();

            LinkedList<String> plus = new LinkedList<>();
            LinkedList<String> minus = new LinkedList<>();

            for (String s : search.split(" ")) {
                if (invertedIndex.containsKey(s.toUpperCase())){
                    if (s.startsWith("+"))
                        plus.add(s.substring(1).toUpperCase());
                    else if (s.startsWith("-"))
                        minus.add(s.substring(1).toUpperCase());
                    else
                        if (result.isEmpty())result.addAll(invertedIndex.get(s.toUpperCase()));
                        else result.retainAll(invertedIndex.get(s.toUpperCase()));
                }
            }
            if (!plus.isEmpty()){
                result.retainAll(plus);
                result.removeAll(minus);
            }

            System.out.println(result);
        }

//        System.out.println(invertedIndex);
    }

}