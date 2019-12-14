package cricketleagueanalysis;

public class IPLAnalysisDAO{

    public int position;
    public String playerName;
    public int matches;
    public  Double average;

    public IPLAnalysisDAO(IPLMostRunCsvData iplMostRunCsvData) {
        position = iplMostRunCsvData.position;
        playerName = iplMostRunCsvData.playerName;
        matches = iplMostRunCsvData.matches;
        average = iplMostRunCsvData.average;
    }
}
