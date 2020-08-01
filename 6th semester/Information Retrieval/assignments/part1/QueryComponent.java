import org.apache.lucene.search.ScoreDoc;
public class QueryComponent {
    private String id;
    private String content;
    private long numTotalHits;
    ScoreDoc[] hits;
    //constructor
    QueryComponent(String id, String content){
        this.id = id;
        this.content = content;
        this.numTotalHits = 0;
        this.hits = null;
    }
    //setters and getters
    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public void setNumTotalHits(long numTotalHits) {
        this.numTotalHits = numTotalHits;
    }

    public void setHits(ScoreDoc[] hits) {
        this.hits = hits;
    }

    public long getNumTotalHits() {
        return numTotalHits;
    }

    public ScoreDoc[] getHits() {
        return hits;
    }
}
