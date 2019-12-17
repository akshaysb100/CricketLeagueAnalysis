package cricketleagueanalysis;

import com.opencsv.bean.CsvBindByName;

public class IPLMostWicketsCsvData {

    @CsvBindByName(column = "POS", required = true)
    public int position;

    @CsvBindByName(column = "PLAYER", required = true)
    public String playerName;

    public IPLMostWicketsCsvData() {
    }
}
