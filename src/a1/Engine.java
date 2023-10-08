package a1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Engine {
    private Doc[] docs;
    public int loadDocs(String dirname){
        File directory = new File(dirname);
        File[] files = directory.listFiles();
        if (files == null){
            return 0;
        }
        docs = new Doc[files.length];
        int count = 0;
        for (File file : files) {
            try {
                Scanner scanner = new Scanner(file);
                count += 1;
                while (scanner.hasNextLine()) {
                    String title = scanner.nextLine();
                    String body = scanner.nextLine();
                    docs[count-1] = new Doc(title + "\n" + body);
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return count;
    }

    public Doc[] getDocs(){
        return this.docs;
    }

    public List<Result> search(Query q){
        List<Result> results = new ArrayList<>();
        for (Doc doc: docs) {
            List<Match> matches = q.matchAgainst(doc);
            Result result = new Result(doc, matches);
            if (matches.size() > 0) {
                results.add(result);
            }
        }

        Collections.sort(results);
        return results;
    }

    public String htmlResult(List<Result> results) {
        StringBuilder html = new StringBuilder();
        for (Result result : results) {
            html.append(result.htmlHightlight());
        }
        return html.toString();
    }
}
