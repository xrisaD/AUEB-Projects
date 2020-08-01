import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.store.FSDirectory;
import org.deeplearning4j.models.embeddings.learning.impl.elements.CBOW;
import org.deeplearning4j.models.embeddings.learning.impl.elements.SkipGram;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

public class SearchFiles {
    private static Word2Vec vec;

    private SearchFiles() {}

    public static void main(String[] args) throws Exception {
        boolean train = true;

        //train model
        if(train){
            SkipGram();
        } else {
            File gModel = new File("savedModel.txt");
            vec = WordVectorSerializer.readWord2VecModel(gModel);
        }

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

        //Let's start the search
        // #3 interactiveQueries = false;
        // #4 interactiveQueries = false;
        search(searcher, field, interactiveQueries);

    }

    //create iterator
    public static SentenceIterator createIter(){
        Path current = Paths.get("IR2020//IR2020_clean.txt");
        String filePath = current.toAbsolutePath().toString();

        SentenceIterator iter = null;
        try {
            iter = new BasicLineIterator(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return iter;
    }

    // fit Skip-Gram model, using Sampling and Negative Sample
    public static void SkipGramWithSamplingAndNegativeSample(){
        SentenceIterator iter = createIter();

        vec = new Word2Vec.Builder()
                .layerSize(250)
                .windowSize(4)
                .iterate(iter)
                .epochs(3)
                .elementsLearningAlgorithm(new SkipGram<>())
                .useHierarchicSoftmax(false)
                .sampling(0.00001)
                .negativeSample(20)
                .build();
        vec.fit();
        //save model
        WordVectorSerializer.writeWord2VecModel(vec, "savedModel.txt");
    }
    //fit SkipGram model
    public static void SkipGram(){
        SentenceIterator iter = createIter();
        vec = new Word2Vec.Builder()
                .layerSize(250)
                .windowSize(5)
                .iterate(iter)
                .epochs(3)
                .elementsLearningAlgorithm(new SkipGram<>())
                .build();
        vec.fit();
        //save model
        WordVectorSerializer.writeWord2VecModel(vec, "savedModel.txt");
    }
    //fit CBOW model
    public static void CBOW(){
        SentenceIterator iter = createIter();

        vec = new Word2Vec.Builder()
                .layerSize(250)
                .windowSize(4)
                .iterate(iter)
                .epochs(3)
                .elementsLearningAlgorithm(new CBOW<>())
                .build();
        vec.fit();
        //save model
        WordVectorSerializer.writeWord2VecModel(vec, "savedModel.txt");
    }

    //add similar words and create new query
    private static String addWords(String query){
        String newQuery = "";

        String[] words = query.split(" ");

        for (String w : words) {
            newQuery = newQuery + w + " ";
            //get nearest words
            Collection<String> lst = vec.wordsNearest(w, 20);
            //add new words
            for (String w2 : lst) {
                double cosSim = vec.similarity(w, w2);
                if(cosSim > 0.65) {
                    System.out.println("For word: " + w);
                    System.out.println("Add word " + w2 +" with similarity :" + cosSim);
                    newQuery = newQuery + w2 + " ";
                }
            }
        }
        return newQuery;
    }

    /*
     * Search for documents
     */
    private static void search(IndexSearcher searcher, String field, boolean interactiveQueries){
        //use the same analyzer function as IndexFiles' analyzer
        Analyzer analyzer = new EnglishAnalyzer();

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
                long numTotalHits = results.totalHits;
                System.out.println(numTotalHits + " total matching documents");

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
                        System.out.println("Empty query with id: " + q.getId());
                        continue;
                    }
                    String givenQuery = q.getContent();

                    String newQuery = addWords(givenQuery);

                    Query query = parser.parse(newQuery);

                    // search the index using the searcher
                    TopDocs results = searcher.search(query, 100);
                    ScoreDoc[] hits = results.scoreDocs;
                    long numTotalHits = results.totalHits;
                    //set num of total hints and hits
                    q.setNumTotalHits(numTotalHits);
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