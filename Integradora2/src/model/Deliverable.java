package model;

public abstract class Deliverable {
    private String link;
    private String id;
    private TypeOfFase fase;
    
    public Deliverable(TypeOfFase fase, String link) {
        this.fase = fase;
        this.id=generateId();
    }
    
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    
    public String getId() {
        return id;
    }
    public String generateId(){
        return hashCode()+"";
    }

    public TypeOfFase getFase() {
        return fase;
    }
    public void setFase(TypeOfFase fase) {
        this.fase = fase;
    }
    
}