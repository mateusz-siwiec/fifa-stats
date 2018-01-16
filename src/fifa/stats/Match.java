package fifa.stats;

import java.util.Date;

public class Match {

    private Integer id;
    private PlayerResult hostResult;
    private PlayerResult guestResult;
    private Date DateOfTheMatch;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PlayerResult getHostResult() {
        return hostResult;
    }

    public void setHostResult(PlayerResult hostResult) {
        this.hostResult = hostResult;
    }

    public PlayerResult getGuestResult() {
        return guestResult;
    }

    public void setGuestResult(PlayerResult guestResult) {
        this.guestResult = guestResult;
    }

    public Date getDateOfTheMatch() {
        return DateOfTheMatch;
    }

    public void setDateOfTheMatch(Date DateOfTheMatch) {
        this.DateOfTheMatch = DateOfTheMatch;
    }

    @Override
    public String toString() {
        return "Match{" + "id=" + id + ", hostResult=" + hostResult + ", guestResult=" + guestResult + ", DateOfTheMatch=" + DateOfTheMatch + '}';
    }

    public Match(PlayerResult hostResult, PlayerResult guestResult, Date DateOfTheMatch) {

        this.hostResult = hostResult;
        this.guestResult = guestResult;
        this.DateOfTheMatch = DateOfTheMatch;
    }

}
