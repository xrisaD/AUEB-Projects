//The basic structure of the code and the comments are from Lucene Demo IndexFiles.java

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.similarities.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndexFiles {
    public static void main(String[] args) {
        //optional arguments: for extra capabilities
        //usage: [-index INDEX_PATH] [-update]\n\n

        String indexPath = "index"; //default index path
        boolean create = true; //by default a new index will be created
        //read arguments to take usefull information about the index
        for(int i=0;i<args.length;i++) {
            if ("-index".equals(args[i])) {
                indexPath = args[i+1];
                i++;
            } else if ("-update".equals(args[i])) {
                create = false;
            }
        }
        //file with documents
        Path current = Paths.get("IR2020//documents.txt");
        String corpus = current.toAbsolutePath().toString();

        Date start = new Date();//current date
        try {
            System.out.println("Indexing to directory '" + indexPath + "'...");
            Directory dir = FSDirectory.open(Paths.get(indexPath));

            //choose the convenient analyzer
            //EnglishAnalyzer: for the normalization of the documents
            Analyzer analyzer = new EnglishAnalyzer();
            //choose the convenient similarity function:
            Similarity similarity = new BM25Similarity();
            //Similarity similarity = new  BooleanSimilarity();

            //Write index using the particular analyzer
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            iwc.setSimilarity(similarity);

            if (create) { //default
                // Create a new index in the directory, removing any
                // previously indexed documents
                iwc.setOpenMode(OpenMode.CREATE);
            }else {
                // Add new documents to an existing index
                iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
            }
            // create the IndexWriter with the specific configuration
            IndexWriter writer = new IndexWriter(dir, iwc);

            //from file with documents, create a list with myDoncument objects
            ArrayList<myDocument> documents = Parser.preprocessing(corpus);
            indexDocs(writer, documents);

            writer.close();
            Date end = new Date();
            System.out.println(end.getTime() - start.getTime() + " total milliseconds");
        }catch (IOException e) {
            System.out.println(" caught a " + e.getClass() +
                    "\n with message: " + e.getMessage());
        }
    }

    /**
     *
     * @param writer
     * @param documents
     * @throws IOException
     */
    static void indexDocs(final IndexWriter writer, ArrayList<myDocument> documents) throws IOException {
        for (myDocument document : documents) {
            indexDoc(writer, document);
        }
        System.out.println("Sum of documents :"+documents.size());
    }
    /** Indexes a single document */
    static void indexDoc(IndexWriter writer, myDocument document) throws IOException {
        //create an empty document
        Document doc = new Document();

        // create the fields of the document:
        //id field is a StoredField because we want to store it
        StoredField id = new StoredField("id", document.getId());
        //text field is very important because it is is indexed and tokenized.
        //Our system is going to use it for search
        //we store it because we may want to give the content to the user
        TextField contents = new TextField("contents", document.getText(), Field.Store.YES);
        //add this fields to this document
        doc.add(id);
        doc.add(contents);
        if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
            // New index, so we just add the document (no old document can be there):
            System.out.println("adding document with id:" + document.getId());
            writer.addDocument(doc);
        }else {
            // Existing index (an old copy of this document may have been indexed) so
            // we use updateDocument instead to replace the old one matching
            System.out.println("updating " + doc);
            writer.updateDocument(new Term("contents",document.getText()),doc);
        }

    }
}
