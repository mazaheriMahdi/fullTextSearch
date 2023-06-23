package Search;

import DocDivider.DocDivider;
import DocDivider.IDocDivider;
import InvertedIndexGenarator.IInvertedListGenerator;
import InvertedIndexGenarator.InvertedIndexGenerator;
import QueryHandler.IQueryHandler;
import QueryHandler.QueryHandler;
import QueryHandler.QueryResult;
import java.io.File;
import java.util.*;

public class Search {
    private final IInvertedListGenerator invertedListGenerator;
    private final IDocDivider docDivider;
    private final IQueryHandler queryHandler;
    private Map<String, LinkedList<String>> docWithLetters;
    private Map<String  , LinkedList<String>> invertedIndex;

    public Search() {
        queryHandler = new QueryHandler();
        invertedIndex = new HashMap<>();
        this.invertedListGenerator = new InvertedIndexGenerator();
        this.docDivider = new DocDivider();
        this.docWithLetters = new HashMap<>();
    }

    private void getAllWords(String path) {
        File folder = new File(path);
        List<File> files = List.of(folder.listFiles());
        for (File file : files) {
            try {
                docDivider.divideDoc(docWithLetters, file);
            } catch (Exception e) {
                System.out.println("File not found");
            }
        }
    }

    public void initiate(String path) {
        getAllWords(path);
        LinkedList<String> tokens = joinAllWords();
        invertedIndex = invertedListGenerator.generate(docWithLetters, tokens);
    }



    private LinkedList<String> joinAllWords() {
        LinkedList<String> tokens = new LinkedList<>();
        for (String docs : docWithLetters.keySet()){
            tokens.addAll(docWithLetters.get(docs));
        }
        return tokens;
    }

    public String search(String query) {
        QueryResult queryResult = queryHandler.handleQuery(query);
        System.out.printf(queryHandler.handleQuery(query).noPrefix().toString());
        SearchResult searchResult = new SearchResult();
        searchResult.setNoPrefix(findAllInList(queryResult.noPrefix()));
        searchResult.setPlusPrefix(findAllInList(queryResult.plusPrefix()));
        searchResult.setMinusPrefix(findAllInList(queryResult.minusPrefix()));
        return searchResult.join().toString();
    }

    public void startSearch(){
        Scanner scanner  = new Scanner(System.in);
        while (true) {
            System.out.println("Enter word to search: ");
            String searchPhrase = scanner.nextLine();
            String result = search(searchPhrase);
            System.out.println(result);
        }
    }
    public LinkedList<String> findAllInList(List<String> words){
        LinkedList<String> result = new LinkedList<>();
        for (String word : words){
            if (invertedIndex.containsKey(word.toUpperCase())){
                result.addAll(invertedIndex.get(word.toUpperCase()));
            }
        }
        return result;
    }
}
