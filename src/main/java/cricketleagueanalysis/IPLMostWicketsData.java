package cricketleagueanalysis;

import com.opencsv.bean.CsvBindByName;

public class IPLMostWicketsData {

    @CsvBindByName(column = "POS", required = true)
    public int position;

    @CsvBindByName(column = "PLAYER", required = true)
    public String playerName;

    @CsvBindByName(column = "Mat", required = true)
    public int matches;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "Or", required = true)
    public int over;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "Wkt", required = true)
    public int wickets;

    @CsvBindByName(column = "BBI", required = true)
    public int bestBowlingInMatch;

    @CsvBindByName(column = "Avg", required = true)
    public Double average;

    @CsvBindByName(column = "Econ", required = true)
    public Double economy;

    @CsvBindByName(column = "SR", required = true)
    public Double strikeRate;

    @CsvBindByName(column = "4w", required = true)
    public int numberOfFoursWickets;

    @CsvBindByName(column = "5w", required = true)
    public int numberOfFifthWickets;
}
