package a1;

import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        boolean canLoadStopWords = Word.loadStopWords("stopwords2.txt");

        if (canLoadStopWords) {
            System.out.println("Word.loadStopWords(): incorrect return value (expected: false)");
        }
        canLoadStopWords = Word.loadStopWords("D:/SE_Assignemnt/src/a1/stopwords.txt");
        if (!canLoadStopWords) {
            System.out.println("Word.loadStopWords(): incorrect return value (expected: true)");
        }
        if (Word.stopWords.size() != 174) {
            System.out.println("Word.loadStopWords(): incorrect number of stopWords loaded (expected: 174)");
        }

        if (Word.createWord("").isKeyword())
            System.out.println("Word.createWord(): empty string ('') should be an invalid word (not a keyword)");
        if (Word.createWord("123456").isKeyword())
            System.out.println("Word.createWord(): '123456' should be an invalid word (not a keyword)");
        if (Word.createWord("!@#$%^").isKeyword())
            System.out.println("Word.createWord(): '!@#$%^' should be an invalid word (not a keyword)");
        if (Word.createWord("se2021").isKeyword())
            System.out.println("Word.createWord(): 'se2021' should be an invalid word (not a keyword)");
        if (Word.createWord(" and").isKeyword())
            System.out.println("Word.createWord(): ' and' should be treated as an invalid word (not a keyword because it contains a space)");
        if (Word.createWord(",se2021.").isKeyword())
            System.out.println("Word.createWord(): ',se2021.' should be an invalid word");
        if (Word.createWord("the").isKeyword())
            System.out.println("Word.isKeyword(): 'the' is a stop word (not a keyword)");
        if (Word.createWord("of").isKeyword())
            System.out.println("Word.isKeyword(): 'of' is a stop word (not a keyword)");
        if (!Word.createWord("context").isKeyword())
            System.out.println("Word.isKeyword(): 'context' should be a keyword");
        if (!Word.createWord("design").isKeyword())
            System.out.println("Word.isKeyword(): 'design' should be a keyword");
        if (!Word.createWord(",se2021.").getText().equals(",se2021."))
            System.out.println("Word.createWord(): the text part of ',se2021.' should be ',se2021.'");
        if (!Word.createWord(",se2021.").getPrefix().equals(""))
            System.out.println("Word.createWord(): the prefix of ',se2021.' should be empty");
        if (!Word.createWord(",se2021.").getSuffix().equals(""))
            System.out.println("Word.createWord(): the suffix of ',se2021.' should be empty");

        if (!Word.createWord("word,").getText().equals("word"))
            System.out.println("Word.createWord(): the text part of 'word,' should be 'word'");

        if (!Word.createWord("word,").getPrefix().equals(""))
            System.out.println("Word.createWord(): the prefix of 'word,' should be empty");
        if (!Word.createWord("word,").getSuffix().equals(","))
            System.out.println("Word.createWord(): the suffix of 'word,' should be ','");
        if (!Word.createWord("«word»").getText().equals("word"))
            System.out.println("Word.createWord(): the text part of '«word»' should be 'word'");
        if (!Word.createWord("«WORD»").getPrefix().equals("«"))
            System.out.println("Word.createWord(): the prefix of '«WORD»' should be '«'");
        if (!Word.createWord("«Word»").getSuffix().equals("»"))
            System.out.println("Word.createWord(): the suffix of '«Word»' should be '»'");
        if (!Word.createWord("apple").equals(Word.createWord("apple")))
            System.out.println("Word.equals() failed with case 'apple'");
        if (!Word.createWord("apple").equals(Word.createWord("Apple")))
            System.out.println("Word.equals() should be case-insensitive, so 'apple' should be equal to 'Apple'");
        if (!Word.createWord("content").equals(Word.createWord("\"content\".")))
            System.out.println("Word.equals() should compare the text part only, so 'content' should be equal to '\"content\".'");

    }
}
