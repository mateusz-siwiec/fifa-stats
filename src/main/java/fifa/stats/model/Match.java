package fifa.stats.model;

import fifa.stats.PlayerResult;
import java.time.LocalDate;
import java.util.Date;

public class Match {

    private Integer id;
    private PlayerResult hostResult;
    private PlayerResult guestResult;
    private LocalDate DateOfTheMatch;

    public Match(PlayerResult hostResult, PlayerResult guestResult, LocalDate DateOfTheMatch) {
        this.hostResult = hostResult;
        this.guestResult = guestResult;
        this.DateOfTheMatch = DateOfTheMatch;
    }

    public Match(Integer id, PlayerResult hostResult, PlayerResult guestResult, LocalDate DateOfTheMatch) {
        this.id = id;
        this.hostResult = hostResult;
        this.guestResult = guestResult;
        this.DateOfTheMatch = DateOfTheMatch;
    }

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

    public LocalDate getDateOfTheMatch() {
        return DateOfTheMatch;
    }

    public void setDateOfTheMatch(LocalDate DateOfTheMatch) {
        this.DateOfTheMatch = DateOfTheMatch;
    }

    @Override
    public String toString() {
        return "Match{" + "id=" + id + ", hostResult=" + hostResult + ", guestResult=" + guestResult + ", DateOfTheMatch=" + DateOfTheMatch + '}';
    }
}
