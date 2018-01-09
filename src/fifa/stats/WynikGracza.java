
package fifa.stats;


public class WynikGracza {
    private Gracz gracz;
    private Druzyna druzyna; 
    private int liczbaBramek;

    @Override
    public String toString() {
        return "WynikGracza{" + "gracz=" + gracz + ", druzyna=" + druzyna + ", liczbaBramek=" + liczbaBramek + '}';
    }

    public Gracz getGracz() {
        return gracz;
    }

    public void setGracz(Gracz gracz) {
        this.gracz = gracz;
    }

    public Druzyna getDruzyna() {
        return druzyna;
    }

    public void setDruzyna(Druzyna druzyna) {
        this.druzyna = druzyna;
    }

    public int getLiczbaBramek() {
        return liczbaBramek;
    }

    public void setLiczbaBramek(int liczbaBramek) {
        this.liczbaBramek = liczbaBramek;
    }

}