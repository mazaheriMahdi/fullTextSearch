package QueryHandler;

import lombok.NoArgsConstructor;

import java.util.LinkedList;


public record QueryResult(LinkedList<String> noPrefix , LinkedList<String> plusPrefix , LinkedList<String> minusPrefix) {

    public QueryResult(LinkedList<String> noPrefix, LinkedList<String> plusPrefix, LinkedList<String> minusPrefix) {
        this.noPrefix = noPrefix;
        this.plusPrefix = plusPrefix;
        this.minusPrefix = minusPrefix;
    }

    public QueryResult() {
        this(new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
    }
}
