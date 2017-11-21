package fifa.stats;

import java.util.Date;


public class Mecze {
    private int id;
    private WynikGracza wynikGospodarza;
    private WynikGracza wynikGoscia;
    private Date czasRozegrania;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WynikGracza getWynikGospodarza() {
        return wynikGospodarza;
    }

    public void setWynikGospodarza(WynikGracza wynikGospodarza) {
        this.wynikGospodarza = wynikGospodarza;
    }

    public WynikGracza getWynikGoscia() {
        return wynikGoscia;
    }

    public void setWynikGoscia(WynikGracza wynikGoscia) {
        this.wynikGoscia = wynikGoscia;
    }

    public Date getCzasRozegrania() {
        return czasRozegrania;
    }

    public void setCzasRozegrania(Date czasRozegrania) {
        this.czasRozegrania = czasRozegrania;
    }

    @Override
    public String toString() {
        return "Mecze{" + "id=" + id + ", wynikGospodarza=" + wynikGospodarza + ", wynikGoscia=" + wynikGoscia + ", czasRozegrania=" + czasRozegrania + '}';
    }
    
  
    
    
    
    
}