package model;

public class Repository extends Deliverable{

    private int documents;
    
    public Repository(TypeOfFase fase, String link, int documents) {
        super(fase,link);
        this.documents = documents;
    }

    public int getDocuments() {
        return documents;
    }
    public void setDocuments(int documents) {
        this.documents = documents;
    }
}
