package pl.chmielewski.Euro.Betting.bet;

import jakarta.persistence.*;
import pl.chmielewski.Euro.Authentication.user.User;
import pl.chmielewski.Euro.Betting.match.Match;

import java.time.LocalDateTime;

@Entity
@Table(name = "bets", schema = "euro")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @Column(name = "first_team_score")
    private Integer firstTeamScore;

    @Column(name = "second_team_score")
    private Integer secondTeamScore;

    private LocalDateTime timestamp;

    public Bet(User user, Match match, Integer firstTeamScore, Integer secondTeamScore, LocalDateTime timestamp) {
        this.user = user;
        this.match = match;
        this.firstTeamScore = firstTeamScore;
        this.secondTeamScore = secondTeamScore;
        this.timestamp = timestamp;
    }

    public Bet() {
    }

    private Integer points;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Integer getFirstTeamScore() {
        return firstTeamScore;
    }

    public void setFirstTeamScore(Integer firstTeamScore) {
        this.firstTeamScore = firstTeamScore;
    }

    public Integer getSecondTeamScore() {
        return secondTeamScore;
    }

    public void setSecondTeamScore(Integer secondTeamScore) {
        this.secondTeamScore = secondTeamScore;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
