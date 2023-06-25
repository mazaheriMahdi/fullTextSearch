package QueryHandler;

import java.util.LinkedList;
import java.util.List;

public class QueryHandler implements IQueryHandler{
    @Override
    public QueryResult handleQuery(String query) {
        QueryResult queryResult = new QueryResult();

        List<String> tokens = List.of(query.split(" "));

        for (String token : tokens) {

            if (token.trim().startsWith("+")) {
                queryResult.plusPrefix().add(token.substring(1));
            } else if (token.trim().startsWith("-")) {
                queryResult.minusPrefix().add(token.substring(1));
            } else {
                queryResult.noPrefix().add(token);
            }
        }
        return queryResult;
    }
}
