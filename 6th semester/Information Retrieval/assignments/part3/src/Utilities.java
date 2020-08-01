
import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;

import java.io.*;
import java.util.ArrayList;


public class Utilities {

    /*
     * delete verbs from wn_s.pl file
     */
    public static void deleteVerbs(){

        try {
            FileWriter myWriter = new FileWriter("src\\wn_s_without_verbs.pl", false);
            BufferedReader reader = openFile("src\\wn_s.pl");

            String line = null;
            line = reader.readLine();
            while (line != null) {
                if(!line.contains(",v,")){
                    myWriter.write(line);
                    myWriter.write("\n");
                }
                line = reader.readLine();
            }
            myWriter.close();
            reader.close();
        }catch (IOException e) {
            System.out.println	("Error  ...");
        }
    }
    /*
     * Read txt file with documents and return a list with them
     */
    public static ArrayList<myDocument> preprocessing(String fileName){
        ArrayList<myDocument> docs= new ArrayList<myDocument>();
        try{
            BufferedReader reader = openFile(fileName);

            String line = null;

            line = reader.readLine();
            while (line != null) {
                int id = Integer.parseInt(line.trim());
                line = reader.readLine();

                String text = "";
                while (line!=null && !line.trim().equals("///")) {
                    text += line;
                    line = reader.readLine();
                }
                //text = tokenize(text);
                docs.add(new myDocument(id,text));
                line = reader.readLine();
            }
            reader.close();
        }catch (IOException e) {
            System.out.println	("Error reading line ...");
        }
        return docs;
    }
    /*
     * tokenize
     */
    public static String tokenize(String text) throws IOException {
        System.out.println("BEFORE" + text);
        String tokens = "";

        StreamTokenizer st = new StreamTokenizer(new StringReader(text));

        while(st.nextToken() != StreamTokenizer.TT_EOF) {
            if (st.ttype == StreamTokenizer.TT_WORD) {
                tokens += st.sval + " ";
            }
        }
        System.out.println("After" + tokens);
        return tokens;
    }

    /*
     *read txt file with queries and return a list with them
     * you can have until 99 queries
     */
    public static ArrayList<QueryComponent> readQueries(String fileName){
        ArrayList<QueryComponent> queries= new ArrayList<QueryComponent>();
        try{
            BufferedReader reader = openFile(fileName);
            String line=null;
            line = reader.readLine(); //read Query id
            while (line != null) {
                String id = line.trim();
                if (id.length() == 1) {
                    id = "Q" + 0 + id;
                } else if(id.length() == 2){
                    id = "Q" + id;
                }
                else{
                    System.out.println("Wrong file format: " + id);
                    return null;
                }
                line = reader.readLine(); //read Query
                String content = line;
                line = reader.readLine(); //read ///
                if(!line.trim().equals("///")){
                    System.out.println("Wrong file format: " + line);
                    return null;
                }
                //add query to the list of queries
                queries.add(new QueryComponent(id, content));
                line = reader.readLine(); //read Query id
            }
            reader.close();
        }catch (IOException e) {
            System.out.println	("Error reading line ...");
        }
        return queries;
    }
    /*
     * display results for each query
     */
    public static void display(ArrayList<QueryComponent> queries, IndexSearcher searcher, int ks[]){
        for(int k :ks) {
            System.out.println("k: "+k);
            for(QueryComponent q:queries){
                try {
                    System.out.println("Query: "+q.getContent());
                    System.out.println("Num of hits: "+q.getNumTotalHits());
                    ScoreDoc[] hits = q.getHits();
                    //display results
                    for(int i=0; i<k; i++){
                        if(hits.length>k) {
                            Document hitDoc = searcher.doc(hits[i].doc);
                            System.out.println("\tScore " + hits[i].score + "\tid " + hitDoc.get("id"));
                        }
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
        }
    }
    /*
     * create txt with results
     */
    public static void createResultsTxt(ArrayList<QueryComponent> queries, IndexSearcher searcher, int ks[]){
        //delete the previous data from txt, if exists
        for(int k :ks) {
            try {
                FileWriter myWriter = new FileWriter("IR2020\\result" + k + ".txt", false);
                myWriter.write("");
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        //write knew data
        for(int k :ks) {
            for(QueryComponent q:queries){
                try {
                    FileWriter myWriter = new FileWriter("IR2020\\result" + k + ".txt",true);
                    ScoreDoc[] hits = q.getHits();
                    for(int i = 0; i<k; i++){
                        if(hits.length>k) {
                            Document hitDoc = searcher.doc(hits[i].doc);
                            String id = hitDoc.get("id");
                            float score = hits[i].score;
                            myWriter.write(q.getId() + " 0 " + id + " 0 " + score + " IRmethod" + "\n");
                        }
                    }
                    myWriter.close();
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
        }
    }

    /*
     * open a file
     */
    public static BufferedReader openFile(String input) {
        File f = null;
        BufferedReader reader = null;
        try {
            f = new File(input);
        } catch (NullPointerException e) {
            System.err.println("File not found.");
        }
        try {
            reader = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file!");
        }
        return reader;
    }

}
