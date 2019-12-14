package cricketleagueanalysis;

public class IPLAnalysisDAO{

    public int position;
    public String playerName;
    public Double strikeRate;
    public  Double average;

    public IPLAnalysisDAO(IPLMostRunCsvData iplMostRunCsvData) {
        position = iplMostRunCsvData.position;
        playerName = iplMostRunCsvData.playerName;
        strikeRate = iplMostRunCsvData.strikeRate;
        average = iplMostRunCsvData.average;
    }
}
