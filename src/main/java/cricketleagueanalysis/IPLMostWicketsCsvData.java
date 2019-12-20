package cricketleagueanalysis;

import com.opencsv.bean.CsvBindByName;

public class IPLMostWicketsCsvData {
    
    @CsvBindByName(column = "PLAYER")
    public String playerName;

    public IPLMostWicketsCsvData() {
    }
}
