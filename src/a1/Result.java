package a1;

import java.util.List;

public class Result  implements Comparable<Result>{
    private Doc doc;
    private List<Match> matches;

    public Result(Doc d, List<Match> matches){
        this.doc = d;
        this.matches = matches;
    }

    public List<Match> getMatches(){
        return this.matches;
    }

    public Doc getDoc(){
        return doc;
    }

    public int getTotalFrequency(){
        int count = 0;
        for(Match m : matches){
            count += m.getFreq();
        }

        return count;
    }

    public double getAverageFirstIndex(){
        double totalAvgFirstIndex = 0;
        for(Match m : matches){
            totalAvgFirstIndex += m.getFirstIndex();
        }
        return totalAvgFirstIndex / matches.size();
    }

    public boolean isSearchWord(Word word){
        for (Match m: matches){
            Word w = m.getWord();
            if(w.equals(word)) return true;
        }

        return false;
    }
    public String htmlHightlight(){
        StringBuilder title = new StringBuilder();
        title.append("<h3>");

        for (Word w : doc.getTitle()) {
            if (isSearchWord(w)) {
                title.append(w.getPrefix() + "<u>" + w.getText() + "</u>" + w.getSuffix() + " ");
            } else {
                title.append(w.getPrefix()+ w.getText()+ w.getSuffix() +" ");
            }
        }
        title.deleteCharAt(title.length() - 1); //remove the excess space char at the end of title before appending "<h3>"
        title.append("</h3>");

        StringBuilder body = new StringBuilder();
        body.append("<p>");
        for (Word w : doc.getBody()) {
            if (isSearchWord(w)) {
                body.append(w.getPrefix() + "<b>" + w.getText() + "</b>" + w.getSuffix() + " ");
            } else {
                body.append(w.getPrefix()+ w.getText()+ w.getSuffix() + " ");
            }
        }
        body.deleteCharAt(body.length() - 1); //remove the excess space char at the end of body before appending "<h3>"
        body.append("</p>");
        return title.toString() + body.toString();

    }

    public int compareTo(Result o) {
        if (matches.size() > o.matches.size()) return -1;
        if (matches.size() < o.matches.size()) return 1;
        if (getTotalFrequency() > o.getTotalFrequency()) return -1;
        if (getTotalFrequency() < o.getTotalFrequency()) return 1;
        if (getAverageFirstIndex() < o.getAverageFirstIndex()) return -1;
        if (getAverageFirstIndex() > o.getAverageFirstIndex()) return 1;
        return 0;
    }
}
