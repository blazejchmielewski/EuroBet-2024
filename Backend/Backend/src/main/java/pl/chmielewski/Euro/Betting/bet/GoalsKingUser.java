package pl.chmielewski.Euro.Betting.bet;

import java.util.ArrayList;
import java.util.List;

public class GoalsKingUser {

    private String name;
    private int goals;

    public GoalsKingUser() {
    }

    public GoalsKingUser(String name, int goals) {
        this.name = name;
        this.goals = goals;
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

    public static List<GoalsKingUser> fromRawDataType(List<Object[]> rawData) {
        List<GoalsKingUser> matchDetailsList = new ArrayList<>();
        for (Object[] data : rawData) {
            String name = (String) data[0];
            int goals = (int) data[1];

            matchDetailsList.add(new GoalsKingUser(name, goals));
        }
        return matchDetailsList;
    }

}
