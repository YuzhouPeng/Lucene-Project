import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        try{
            Indexer indexer = new Indexer();
            indexer.createIndex();
            Searcher searcher = new Searcher();
            searcher.readIndex(1);
            ArrayList<ArrayList<Integer>> base = FiledataReader.readQrelScore();
            ArrayList<String> queries =  FiledataReader.readQueries();
            Evaluator evaluator = new Evaluator();
            double averageMap = evaluator.avgMAP(searcher,base,queries);
            double averageRecall = evaluator.avgRecall(searcher,base,queries);
            System.out.println("MAP of BM25 is: "+averageMap);
            System.out.println("Recall of BM25 is: "+averageRecall);

            Searcher searcher1 = new Searcher();
            searcher1.readIndex(2);
            ArrayList<ArrayList<Integer>> base1 = FiledataReader.readQrelScore();
            ArrayList<String> queries1 =  FiledataReader.readQueries();
            Evaluator evaluator1 = new Evaluator();
            double averageMap1 = evaluator1.avgMAP(searcher1,base1,queries1);
            double averageRecall1 = evaluator1.avgRecall(searcher1,base1,queries1);
            System.out.println("MAP of TF-IDF is: "+averageMap1);
            System.out.println("Recall of TF-IDF is: "+averageRecall1);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }
}
