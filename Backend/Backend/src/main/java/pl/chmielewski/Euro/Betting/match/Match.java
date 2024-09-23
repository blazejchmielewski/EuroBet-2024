package pl.chmielewski.Euro.Betting.match;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "matches", schema = "euro")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long firstTeam;
    private Long secondTeam;
    private Integer firstTeamScore;
    private Integer secondTeamScore;
    private Long winner;
    private String result;
    private LocalDateTime playDateTime;

    public Match(Integer firstTeamScore, Integer secondTeamScore,  Long firstTeam, Long secondTeam, LocalDateTime playDateTime) {
        this.firstTeamScore = firstTeamScore;
        this.secondTeamScore = secondTeamScore;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.playDateTime = playDateTime;
    }

    public Match() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(Long firstTeam) {
        this.firstTeam = firstTeam;
    }

    public Long getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(Long secondTeam) {
        this.secondTeam = secondTeam;
    }

    public int getFirstTeamScore() {
        return firstTeamScore;
    }

    public void setFirstTeamScore(int firstTeamScore) {
        this.firstTeamScore = firstTeamScore;
    }

    public int getSecondTeamScore() {
        return secondTeamScore;
    }

    public void setSecondTeamScore(int secondTeamScore) {
        this.secondTeamScore = secondTeamScore;
    }

    public void setFirstTeamScore(Integer firstTeamScore) {
        this.firstTeamScore = firstTeamScore;
    }

    public void setSecondTeamScore(Integer secondTeamScore) {
        this.secondTeamScore = secondTeamScore;
    }

    public Long getWinner() {
        return winner;
    }

    public void setWinner(Long winner) {
        this.winner = winner;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDateTime getPlayDateTime() {
        return playDateTime;
    }

    public void setPlayDateTime(LocalDateTime playDateTime) {
        this.playDateTime = playDateTime;
    }
}
