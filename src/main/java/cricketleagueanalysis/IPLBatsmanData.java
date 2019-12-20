package cricketleagueanalysis;

import com.opencsv.bean.CsvBindByName;

public class IPLBatsmanData {
    @CsvBindByName(column = "POS")
    public int position;

    @CsvBindByName(column = "PLAYER", required = true)
    public String playerName;

    @CsvBindByName(column = "Mat", required = true)
    public int matches;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "NO", required = true)
    public int notOut;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "HS", required = true)
    public String highScore;

    @CsvBindByName(column = "Avg", required = true)
    public Double average;

    @CsvBindByName(column = "BF", required = true)
    public Double ballFaced;

    @CsvBindByName(column = "SR", required = true)
    public Double strikeRate;

    @CsvBindByName(column = "100", required = true)
    public int numberOfHundreds;

    @CsvBindByName(column = "50", required = true)
    public int numberOfFifty;

    @CsvBindByName(column = "4s", required = true)
    public int numberOfFours;

    @CsvBindByName(column = "6s", required = true)
    public int numberOfSix;
}
