package cricketleagueanalysis;

import com.opencsv.bean.CsvBindByName;

public class IPLBowlerData {
    
    @CsvBindByName(column = "PLAYER")
    public String playerName;

    public IPLBowlerData() {
    }
}
