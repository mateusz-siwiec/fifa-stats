package fifa.stats;


public class Gracz {
    private int id;
    private String imie;
    private String nazwisko;

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }  

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Override
    public String toString() {
        return "Gracze{" + "id=" + id + ", imie=" + imie + ", nazwisko=" + nazwisko + '}';
    }

    public void Gracze(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }
    
    
}