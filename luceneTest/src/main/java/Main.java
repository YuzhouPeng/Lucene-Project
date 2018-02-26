import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try{
            Indexer indexer = new Indexer();
            indexer.createIndex(1);
            Searcher searcher = new Searcher();
            searcher.readIndex(1);
            ArrayList<ArrayList<Integer>> base = FiledataReader.readQrelScore();
            ArrayList<String> queries =  FiledataReader.readQueries();
            Evaluator evaluator = new Evaluator();
            double averageMap = evaluator.avgMAP(searcher,base,queries);
            double averageRecall = evaluator.avgRecall(searcher,base,queries);
            System.out.println("Standardanalyzer MAP of BM25 is: "+averageMap);
            System.out.println("Standardanalyzer Recall of BM25 is: "+averageRecall);

            Searcher searcher1 = new Searcher();
            searcher1.readIndex(2);
            ArrayList<ArrayList<Integer>> base1 = FiledataReader.readQrelScore();
            ArrayList<String> queries1 =  FiledataReader.readQueries();
            Evaluator evaluator1 = new Evaluator();
            double averageMap1 = evaluator1.avgMAP(searcher1,base1,queries1);
            double averageRecall1 = evaluator1.avgRecall(searcher1,base1,queries1);
            System.out.println("Standardanalyzer MAP of TF-IDF is: "+averageMap1);
            System.out.println("Standardanalyzer Recall of TF-IDF is: "+averageRecall1);

            Indexer stopindexer = new Indexer();
            stopindexer.createIndex(2);
            Searcher stopsearcher = new Searcher();
            stopsearcher.readIndex(1);
            ArrayList<ArrayList<Integer>> stopbase = FiledataReader.readQrelScore();
            ArrayList<String> stopqueries =  FiledataReader.readQueries();
            Evaluator stopevaluator = new Evaluator();
            double stopaverageMap = stopevaluator.avgMAP(stopsearcher,stopbase,stopqueries);
            double stopaverageRecall = stopevaluator.avgRecall(stopsearcher,stopbase,stopqueries);
            System.out.println("Stopanalyzer MAP of BM25 is: "+stopaverageMap);
            System.out.println("Stopanalyzer Recall of BM25 is: "+stopaverageRecall);

            Searcher stopsearcher1 = new Searcher();
            stopsearcher1.readIndex(2);
            ArrayList<ArrayList<Integer>> stopbase1 = FiledataReader.readQrelScore();
            ArrayList<String> stopqueries1 =  FiledataReader.readQueries();
            Evaluator stopevaluator1 = new Evaluator();
            double stopaverageMap1 = stopevaluator1.avgMAP(stopsearcher1,stopbase1,stopqueries1);
            double stopaverageRecall1 = stopevaluator1.avgRecall(stopsearcher1,stopbase1,stopqueries1);
            System.out.println("Stopanalyzer MAP of TF-IDF is: "+stopaverageMap1);
            System.out.println("Stopanalyzer Recall of TF-IDF is: "+stopaverageRecall1);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }
}
