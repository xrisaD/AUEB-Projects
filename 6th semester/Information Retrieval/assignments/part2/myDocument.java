public class myDocument {
    private int id;
    private String text;

    //constructor
    myDocument(int id, String text){
        this.id = id;
        this.text = text;
    }

    //setters and getters
    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }
}
