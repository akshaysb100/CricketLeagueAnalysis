package cricketleagueanalysis;

import com.opencsv.bean.CsvBindByName;

public class IPLBowlerData {
    
    @CsvBindByName(column = "PLAYER", required = true)
    public String playerName;

    @CsvBindByName(column = "Avg", required = true)
    public Double average;

    @CsvBindByName(column = "SR", required = true)
    public Double strikeRate;

    @CsvBindByName(column = "Wkts", required = true)
    public int wickets;

    @CsvBindByName(column = "Econ", required = true)
    public Double  economy;

    @CsvBindByName(column = "4w", required = true)
    public int numberOfFoursWicket;

    @CsvBindByName(column = "5w", required = true)
    public int numberOfFiveWickets;

    public IPLBowlerData() {
    }
}
