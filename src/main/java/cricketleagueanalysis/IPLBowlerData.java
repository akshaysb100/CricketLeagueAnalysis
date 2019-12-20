package cricketleagueanalysis;

import com.opencsv.bean.CsvBindByName;

public class IPLBowlerData {
    
    @CsvBindByName(column = "PLAYER")
    public String playerName;

    @CsvBindByName(column = "Avg")
    public Double average;

    public IPLBowlerData() {
    }
}
