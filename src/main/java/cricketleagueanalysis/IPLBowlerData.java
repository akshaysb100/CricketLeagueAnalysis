package cricketleagueanalysis;

import com.opencsv.bean.CsvBindByName;

public class IPLBowlerData {
    
    @CsvBindByName(column = "PLAYER")
    public String playerName;

    @CsvBindByName(column = "Avg")
    public Double average;

    @CsvBindByName(column = "SR")
    public Double strikeRate;

    @CsvBindByName(column = "Wkt")
    public int wickets;

    @CsvBindByName(column = "Econ")
    public Double  economy;

    public IPLBowlerData() {
    }
}
