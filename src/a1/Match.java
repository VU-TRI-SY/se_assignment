package a1;

public class Match  implements Comparable<Match> {
    Doc d;
    Word w;
    int freq;
    int firstIndex;

    public Match (Doc d, Word w, int freq, int firstIndex){
        this.d = d;
        this.w = w;
        this.freq = freq;
        this.firstIndex = firstIndex;
    }

    public  int getFreq(){
        return freq;
    }

    public int getFirstIndex(){
        return firstIndex;
    }

    public Word getWord(){
        return w;
    }

    public Doc getDoc(){
        return d;
    }

    public int compareTo (Match o ) {
        if (this.getFirstIndex() < o.getFirstIndex())
            return -1;
        else if (this.getFirstIndex() > o.getFirstIndex())
            return 1;
        else
            return 0;
    }
}

