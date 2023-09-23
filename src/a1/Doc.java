package a1;

import java.util.ArrayList;
import java.util.List;

public class Doc {
    private List<Word> title;
    private List<Word> body;

    public Doc(String content) {
        title = new ArrayList<>();
        body = new ArrayList<>();

        String[] strs = content.split("\n");
        String[] titleDoc = strs[0].split(" ");
        String[] bodyDoc = strs[1].split(" ");

        for(String rawText: titleDoc){
            title.add(Word.createWord(rawText));
        }

        for(String rawText: bodyDoc){
            body.add(Word.createWord(rawText));
        }
    }

    public List<Word> getTitle() {
        return title;
    }

    public List<Word> getBody() {
        return body;
    }

    public boolean equals(Object o) { //d1.equals(d2)
        if (o == null) return false;
        if (o instanceof Doc){
            Doc oo = (Doc) o;
            if (title.size() != oo.title.size() || body.size() != oo.body.size()){
                return false;
            }

            for(int i = 0; i < title.size(); i++){

                if(!title.get(i).equals(oo.title.get(i))){
                    return false;
                }
            }

            for(int i = 0; i < body.size(); i++){
                if(!body.get(i).equals(oo.body.get(i))){
                    return false;
                }
            }

            return true;
        }
        return false;
    }
}