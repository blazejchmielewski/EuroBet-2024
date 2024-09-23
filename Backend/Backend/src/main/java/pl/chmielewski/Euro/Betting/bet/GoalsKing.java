package pl.chmielewski.Euro.Betting.bet;

import jakarta.persistence.*;

@Entity
@Table(name = "king_goals", schema = "euro")
public class GoalsKing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int goals;
    private Long userId;

    public GoalsKing(String name, int goals, Long userId) {
        this.name = name;
        this.goals = goals;
        this.userId = userId;
    }

    public GoalsKing() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
