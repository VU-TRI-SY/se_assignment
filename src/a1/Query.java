package a1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Query {
    List<Word> keywords = new ArrayList<Word>();

    public Query(String searchPhrase) {
        String[] keys = searchPhrase.split(" ");
        for (String key : keys) {
            if (Word.createWord(key).isKeyword()) {
                keywords.add(Word.createWord(key));
            }
        }
    }

    public List<Word> getKeywords() {
        return keywords;
    }

    public List<Match> matchAgainst(Doc d) {
        List<Match> result = new ArrayList<>();
        List<Word> title = d.getTitle();
        List<Word> body = d.getBody();
        for (Word keyWord: keywords) {
            int frequency = 0;
            int firstIndex = -1;

            for(int i = 0; i < title.size(); i++){
                if(title.get(i).equals(keyWord)){
                    frequency ++;
                    if (firstIndex == -1){
                        firstIndex = i;
                    }
                }
            }

            for(int i = 0; i < title.size(); i++){
                if(body.get(i).equals(keyWord)){
                    frequency ++;
                    if (firstIndex == -1){
                        firstIndex = i;
                    }
                }
            }

            if (frequency > 0) {
                Match match = new Match(d, keyWord, frequency, firstIndex);
                result.add(match);
            }

        }
        Collections.sort(result);
        return result;
    }
}

