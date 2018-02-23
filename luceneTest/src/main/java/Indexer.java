import org.apache.lucene.LucenePackage;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.document.*;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Indexer {
    void createIndex(){
        try{
            IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            Directory dir = FSDirectory.open(FilePaths.Index_Path);
            IndexWriter writer = new IndexWriter(dir,config);
            List<Document> documents = FiledataReader.Documentfiles();
            Iterator<Document> iter = documents.iterator();
            while(iter.hasNext()) {
                Document o = iter.next();
                org.apache.lucene.document.Document doc = new org.apache.lucene.document.Document();
                StringField idfield = new StringField("id", o.id, Field.Store.YES);
                TextField titlefield = new TextField("title", o.Title, Field.Store.YES);
                TextField authorfield = new TextField("author", o.Author, Field.Store.YES);
                TextField biblifield = new TextField("bibliography", o.Bibliography, Field.Store.YES);
                TextField contentfield = new TextField("content", o.content, Field.Store.YES);
                doc.add(idfield);
                doc.add(titlefield);
                doc.add(authorfield);
                doc.add(biblifield);
                doc.add(contentfield);
                writer.addDocument(doc);
            }
            writer.close();
        }catch (NullPointerException e){
            Logger.getGlobal().log(Level.SEVERE,"iteration failed");
            e.printStackTrace();
            System.exit(0);
        }catch (Exception e){
            Logger.getGlobal().log(Level.SEVERE,"build index failed");
            e.printStackTrace();
            System.exit(0);
        }
    }
}
