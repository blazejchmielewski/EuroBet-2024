package pl.chmielewski.Euro.Betting.match;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MatchDetails {
    private Long id;
    private Long firstTeamId;
    private String firstTeamName;
    private Long secondTeamId;
    private String secondTeamName;
    private LocalDateTime playDateTime;
    private String result;
    private String type;

    public MatchDetails() {
    }

    public MatchDetails(Long id, Long firstTeamId, String firstTeamName, Long secondTeamId, String secondTeamName, LocalDateTime playDateTime, String result, String type) {
        this.id = id;
        this.firstTeamId = firstTeamId;
        this.firstTeamName = firstTeamName;
        this.secondTeamId = secondTeamId;
        this.secondTeamName = secondTeamName;
        this.playDateTime = playDateTime;
        this.result = result;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFirstTeamId() {
        return firstTeamId;
    }

    public void setFirstTeamId(Long firstTeamId) {
        this.firstTeamId = firstTeamId;
    }

    public String getFirstTeamName() {
        return firstTeamName;
    }

    public void setFirstTeamName(String firstTeamName) {
        this.firstTeamName = firstTeamName;
    }

    public Long getSecondTeamId() {
        return secondTeamId;
    }

    public void setSecondTeamId(Long secondTeamId) {
        this.secondTeamId = secondTeamId;
    }

    public String getSecondTeamName() {
        return secondTeamName;
    }

    public void setSecondTeamName(String secondTeamName) {
        this.secondTeamName = secondTeamName;
    }

    public LocalDateTime getPlayDateTime() {
        return playDateTime;
    }

    public void setPlayDateTime(LocalDateTime playDateTime) {
        this.playDateTime = playDateTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static List<MatchDetails> fromRawData(List<Object[]> rawData) {
        List<MatchDetails> matchDetailsList = new ArrayList<>();
        for (Object[] data : rawData) {
            Long id = (Long) data[0];
            Long firstTeamId = (Long) data[1];
            String firstTeamName = (String) data[2];
            Long secondTeamId = (Long) data[3];
            String secondTeamName = (String) data[4];
            Timestamp playDateTime = (Timestamp) data[5];
            String result = (String) data[6];
            matchDetailsList.add(new MatchDetails(id, firstTeamId, firstTeamName, secondTeamId, secondTeamName, playDateTime.toLocalDateTime(), result, null));
        }
        return matchDetailsList;
    }

    public static List<MatchDetails> fromRawDataType(List<Object[]> rawData) {
        List<MatchDetails> matchDetailsList = new ArrayList<>();
        for (Object[] data : rawData) {
            Long id = (Long) data[0];
            Long firstTeamId = (Long) data[1];
            String firstTeamName = (String) data[2];
            Long secondTeamId = (Long) data[3];
            String secondTeamName = (String) data[4];
            Timestamp playDateTime = (Timestamp) data[5];
            String result = (String) data[6];
            String type = (String) data[7];
            matchDetailsList.add(new MatchDetails(id, firstTeamId, firstTeamName, secondTeamId, secondTeamName, playDateTime.toLocalDateTime(), result, type));
        }
        return matchDetailsList;
    }
}
