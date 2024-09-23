package pl.chmielewski.Euro.request;

public record CreateBet(
        Long matchId,
        Integer firstTeamScore,
        Integer secondTeamScore
) {

    @Override
    public String toString() {
        return "CreateBet{" +
                "matchId=" + matchId +
                ", firstTeamScore=" + firstTeamScore +
                ", secondTeamScore=" + secondTeamScore +
                '}';
    }
}
