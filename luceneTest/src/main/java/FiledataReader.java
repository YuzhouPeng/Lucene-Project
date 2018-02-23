import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FiledataReader {

    static List<Document> Documentfiles(){
        try{
            ArrayList<Document> docFiles = new ArrayList<>();
            List<String> line = Files.readAllLines(FilePaths.Document_File);
            String linetext = String.join(" ", line);
            linetext = linetext.replace("?", "");
            String articles[] = linetext.split(".I");
            System.out.println();
            int i = 1;
            while(i<=1400){
                String doclines[] = articles[i].split(".T|.A|.B|.W");
                Document document = new Document();

                document.id = doclines[0];
                document.Title = doclines[1];
                document.Author = doclines[2];
                document.Bibliography = doclines[3];
                document.content = doclines[4];
                docFiles.add(document);
                i++;
            }

            return docFiles;
        }catch (IOException e){
            Logger.getGlobal().log(Level.SEVERE,"read document failed");
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    static ArrayList<String> readQueries(){
       try{
           List<String> line = Files.readAllLines(FilePaths.Query_File);
           String linetext =  String.join(" ", line);
           linetext = linetext.replace("?", "");
           String lines[] = linetext.split("\\.I.*?.W");
           ArrayList<String> cranqueries = new ArrayList<>(Arrays.asList(lines));
           cranqueries.remove(0);
           return cranqueries;
       }catch (Exception e){
           Logger.getGlobal().log(Level.SEVERE,"read query failed");
           e.printStackTrace();
           System.exit(0);
       }
       return null;
    }

    static ArrayList<ArrayList<Integer>> readQrelScore(){
        ArrayList<ArrayList<Integer>> cranqrel = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(FilePaths.Query_Score_File.toFile()));
            String line = null;
            int id = 1;
            ArrayList<Integer> Lists = new ArrayList<>();
            while((line = reader.readLine()) != null){
                String[] string = line.split("\\s+");
                int queryID = Integer.parseInt(string[0]);
                int docID = Integer.parseInt(string[1]);
                int relev = Integer.parseInt(string[2]);
                if (queryID!=id){
                    id = queryID;
                    cranqrel.add(Lists);
                    Lists = new ArrayList<>();
                }
                if (relev<=FilePaths.Boundary_value){
                    Lists.add(docID);
                }
            }
            cranqrel.add(Lists);

        } catch (IOException e){
            Logger.getGlobal().log(Level.SEVERE,"read relevance failed");
            e.printStackTrace();
            System.exit(0);
        }
        return cranqrel;
    }


}
