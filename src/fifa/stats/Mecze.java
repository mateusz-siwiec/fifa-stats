package fifa.stats;

import java.util.Date;


public class Mecze {
    private int Id;
    private int gospodarzId;
    private int gospodarzBramki;
    private int gospodarzDruzynaId;
    private int goscId;
    private int goscBramki;
    private int goscDruzynaId;
    private Date czasRozegrania;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getGospodarzId() {
        return gospodarzId;
    }

    public void setGospodarzId(int gospodarzId) {
        this.gospodarzId = gospodarzId;
    } 

    public int getGospodarzBramki() {
        return gospodarzBramki;
    }

    public void setGospodarzBramki(int gospodarzBramki) {
        this.gospodarzBramki = gospodarzBramki;
    }

    public int getGospodarzDruzynaId() {
        return gospodarzDruzynaId;
    }

    public void setGospodarzDruzynaId(int gospodarzDruzynaId) {
        this.gospodarzDruzynaId = gospodarzDruzynaId;
    }

    public int getGoscId() {
        return goscId;
    }

    public void setGoscId(int goscId) {
        this.goscId = goscId;
    }

    public int getGoscBramki() {
        return goscBramki;
    }

    public void setGoscBramki(int goscBramki) {
        this.goscBramki = goscBramki;
    }

    public int getGoscDruzynaId() {
        return goscDruzynaId;
    }

    public void setGoscDruzynaId(int goscDruzynaId) {
        this.goscDruzynaId = goscDruzynaId;
    }

    public Date getCzasRozegrania() {
        return czasRozegrania;
    }

    public void setCzasRozegrania(Date czasRozegrania) {
        this.czasRozegrania = czasRozegrania;
    }
}