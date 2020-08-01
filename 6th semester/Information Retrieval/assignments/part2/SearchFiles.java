import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.en.EnglishPossessiveFilterFactory;
import org.apache.lucene.analysis.en.PorterStemFilterFactory;
import org.apache.lucene.analysis.miscellaneous.KeywordMarkerFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.apache.lucene.analysis.synonym.SynonymGraphFilterFactory;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchFiles {
    private SearchFiles() {}

    public static void main(String[] args) throws Exception {
        String index = "index"; //index path: it is at the index file
        String field = "contents";//this field will be searched
        //default interactiveQueries is true: the user can put queries to the system by command line
        //non interactive queries: queries should read by a file
        boolean interactiveQueries = true;
        //initialize
        IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
        IndexSearcher searcher = new IndexSearcher(reader);
        //use the same similarity function as IndexFiles' similarity
        searcher.setSimilarity(new BM25Similarity());
        //searcher.setSimilarity(new BooleanSimilarity());

        //Let's start the search
        // #3 interactiveQueries = false;
        // #4 interactiveQueries = false;
        search(searcher, field, interactiveQueries);

    }
    /*
    * Create custom analyzer
     */
    public static CustomAnalyzer createAnalyzer(){
        try {
            // create wn_s_without_verds.pl file
            // it is the wn_s file when we delete lines with verbs
            Utilities.deleteVerbs();

            // Read synonyms from wn_s.pl file
            Map<String, String> sffargs = new HashMap<>();
            sffargs.put("synonyms", "wn_s_without_verbs.pl");
            sffargs.put("format", "wordnet");
            // Create Custom Analyzer
            // Custom analyzer should analyze query text like the EnglishAnalyzer and have
            CustomAnalyzer.Builder builder = CustomAnalyzer.builder()
                    .withTokenizer(StandardTokenizerFactory.class)
                    .addTokenFilter(EnglishPossessiveFilterFactory.class)
                    .addTokenFilter(LowerCaseFilterFactory.class)
                    .addTokenFilter(StopFilterFactory.class)
                    .addTokenFilter(KeywordMarkerFilterFactory.class)
                    .addTokenFilter(PorterStemFilterFactory.class)
                    .addTokenFilter(SynonymGraphFilterFactory.class, sffargs)
                    ;
            return builder.build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    * Search for documents
     */
    private static void search(IndexSearcher searcher, String field, boolean interactiveQueries){
        //use the same analyzer function as IndexFiles' analyzer
        //Analyzer analyzer = new EnglishAnalyzer();
         Analyzer analyzer = createAnalyzer();

        // create a query parser on the field "contents"
        QueryParser parser = new QueryParser(field, analyzer);
        try{
            BufferedReader in = null;
            //non interactive search
            if (!interactiveQueries) {
                nonInteractiveQueries(searcher, parser, field);
                return ;
            } //interactive search
            else {
                in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
            }
            while (true) {
                System.out.println("Enter query or press 'q' to quit: ");
                System.out.print(">>");
                String line = in.readLine();
                if(line.trim().equals('q')){
                    break; //quit
                }
                if (line == null || line.trim().length() <= 0) {
                    continue;
                }
                //user write a query
                Query query = parser.parse(line);
                System.out.println("Searching for: " + query.toString(field));

                // search the index using the searcher
                TopDocs results = searcher.search(query, 100);
                ScoreDoc[] hits = results.scoreDocs;
                TotalHits numTotalHits = results.totalHits;
                System.out.println(numTotalHits.value + " total matching documents");

                //display results
                /*for(int i=0; i<hits.length; i++){
                    Document hitDoc = searcher.doc(hits[i].doc);
                    System.out.println("\tScore "+hits[i].score +"\tid "+hitDoc.get("id"));
                }*/

            }
            in.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    /*
    * Read queries from queries.txt and create results
     */
    private static void nonInteractiveQueries(IndexSearcher searcher, QueryParser parser, String field) {
        String fileNameWithQueries = "IR2020\\queries.txt";
        ArrayList<QueryComponent> queries = Utilities.readQueries(fileNameWithQueries);
        if(queries!=null) {
            try {
                for (QueryComponent q : queries) {
                    if (q.getContent() == null || q.getContent().trim().length() <= 0) {
                        System.out.print("Empty query with id: " + q.getId());
                        continue;
                    }
                    //user write a query
                    Query query = parser.parse(q.getContent());
                    // search the index using the searcher
                    TopDocs results = searcher.search(query, 100);
                    ScoreDoc[] hits = results.scoreDocs;
                    TotalHits numTotalHits = results.totalHits;
                    //set num of total hints and hits
                    q.setNumTotalHits(numTotalHits.value);
                    q.setHits(hits);
                }
                //create txt with results
                int[] ks3 =new int[]{20, 30, 50};
                int[] ks4 =new int[]{5, 10, 15, 20};
                // #4 Utilities.createResultsTxt(queries, searcher, ks4);
                // #3 Utilities.display(queries, searcher, ks3); Utilities.createResultsTxt(queries, searcher, ks3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}