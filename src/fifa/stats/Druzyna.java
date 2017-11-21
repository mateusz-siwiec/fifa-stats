package fifa.stats;




public class Druzyna {
    private int id;
    private String nazwa;
    private String kraj;
    private String liga;

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    } 

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    @Override
    public String toString() {
        return "Druzyna{" + "id=" + id + ", nazwa=" + nazwa + ", kraj=" + kraj + ", liga=" + liga + '}';
    }  

    public void Druzyna(String nazwa, String kraj, String liga) {
        this.nazwa = nazwa;
        this.kraj = kraj;
        this.liga = liga;
    }
    
    public void Druzyna(String nazwa){
        this.nazwa = nazwa;
    }
    

}