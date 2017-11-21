
package fifa.stats;


public class WynikGracza {
    private int graczId;
    private int druzynaId; 
    private int liczbaBramek;

    public int getGraczId() {
        return graczId;
    }

    public void setGraczId(int graczId) {
        this.graczId = graczId;
    }

    public int getDruzynaId() {
        return druzynaId;
    }

    public void setDruzynaId(int druzynaId) {
        this.druzynaId = druzynaId;
    }

    public int getLiczbaBramek() {
        return liczbaBramek;
    }

    public void setLiczbaBramek(int liczbaBramek) {
        this.liczbaBramek = liczbaBramek;
    }

    @Override
    public String toString() {
        return "WynikGracza{" + "graczId=" + graczId + ", druzynaId=" + druzynaId + ", liczbaBramek=" + liczbaBramek + '}';
    }
    
    
}
