package fifa.stats;

import java.util.Date;


public class Mecze {
    private int id;
    private int wynikGospodarza;
    private int wynikGoscia;
    private Date czasRozegrania;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public Date getCzasRozegrania() {
        return czasRozegrania;
    }

    public void setCzasRozegrania(Date czasRozegrania) {
        this.czasRozegrania = czasRozegrania;
    }

    public int getWynikGospodarza() {
        return wynikGospodarza;
    }

    public void setWynikGospodarza(int wynikGospodarza) {
        this.wynikGospodarza = wynikGospodarza;
    }

    public int getWynikGoscia() {
        return wynikGoscia;
    }

    public void setWynikGoscia(int wynikGoscia) {
        this.wynikGoscia = wynikGoscia;
    }

    @Override
    public String toString() {
        return "Mecze{" + "id=" + id + ", wynikGospodarza=" + wynikGospodarza + ", wynikGoscia=" + wynikGoscia + ", czasRozegrania=" + czasRozegrania + '}';
    }

    
    
    
}