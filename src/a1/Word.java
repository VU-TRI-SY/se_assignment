package a1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
public class Word {

    public static Set<String> stopWords = new HashSet<>();

    private String word;

    public Word(String word) {
        this.word = word;
    }

    public boolean isValid() {
        if (this.word.length() < 0) return false;
        if (this.word.contains(" ")) return false;
        String regex = "\\W*[a-zA-Z]+([-'][a-zA-Z]+)*\\W*";
        return this.word.matches(regex);
    }

    public static Word createWord(String rawText) {
        return new Word(rawText);
    }

    public boolean isKeyword() {
        if (this.getText().isEmpty()) {
            return false;
        }
        if (!this.isValid()) return false;

        for (String sw : stopWords) {
            if (this.getText().equalsIgnoreCase(sw)) return false;

        }
        return true;

    }

    public String getPrefix() {
        if (!this.isValid()) return "";
        int index = 0;
        for (int i = 0; i < this.word.length(); i++) {
            if (Character.isLetterOrDigit(this.word.charAt(i))) {
                index = i;
                break;
            }

        }
        return this.word.substring(0, index);

    }

    public String getSuffix() {
        if (!this.isValid()) return "";

        for (int i = this.word.length() - 1; i >= 0; i--) {
            char c = this.word.charAt(i);
            if (Character.isLetter(c)) {
                if (c == 't') {
                    return this.word.substring(i + 1);
                } else {
                    for (int j = i; j >= 0; j--) {
                        if (this.word.charAt(j) == '\'') {
                            return this.word.substring(j);
                        }
                    }
                    return this.word.substring(i + 1);
                }

            }

        }return this.word.substring(9 +1);

    }

    public String getText() {
        return this.word.substring(getPrefix().length(), this.word.length() - getSuffix().length());
    }


    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Word)) return false;
        return this.getText().equalsIgnoreCase(((Word) o).getText());
    }


    /**
     * @return the raw text of the word
     */
    @Override
    public String toString() {
        return this.word;
    }

    public static boolean loadStopWords(String fileName){
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                stopWords.add(line);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}

