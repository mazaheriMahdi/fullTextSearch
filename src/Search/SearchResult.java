package Search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchResult {
    private LinkedList<String> noPrefix;
    private LinkedList<String> plusPrefix;
    private LinkedList<String> minusPrefix;

    public List<String> join(){
        LinkedList<String> result = new LinkedList<>();
        result.addAll(noPrefix);
        if (!plusPrefix.isEmpty()) result.retainAll(plusPrefix);
        if (!minusPrefix.isEmpty())result.removeAll(minusPrefix);
        return result;
    }


    public LinkedList<String> getNoPrefix() {
        return noPrefix;
    }

    public void setNoPrefix(LinkedList<String> noPrefix) {
        this.noPrefix = noPrefix;
    }

    public LinkedList<String> getPlusPrefix() {
        return plusPrefix;
    }

    public void setPlusPrefix(LinkedList<String> plusPrefix) {
        this.plusPrefix = plusPrefix;
    }

    public LinkedList<String> getMinusPrefix() {
        return minusPrefix;
    }

    public void setMinusPrefix(LinkedList<String> minusPrefix) {
        this.minusPrefix = minusPrefix;
    }
}

