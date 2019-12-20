package cricketleagueanalysis;

public class IPLAnalysisDAO {
    public int position;
    public String playerName;
    public Double strikeRate;
    public Double average;
    public int numberOf6s;
    public int numberOf4s;
    public int numberOfRuns;

    public IPLAnalysisDAO(IPLMostRunCsvData iplMostRunCsvData) {
        position = iplMostRunCsvData.position;
        playerName = iplMostRunCsvData.playerName;
        strikeRate = iplMostRunCsvData.strikeRate;
        average = iplMostRunCsvData.average;
        numberOf4s = iplMostRunCsvData.numberOfFours;
        numberOf6s = iplMostRunCsvData.numberOfSix;
        numberOfRuns = iplMostRunCsvData.runs;
    }

    public IPLAnalysisDAO(IPLMostWicketsCsvData iplMostWicketsCsvData) {
        playerName = iplMostWicketsCsvData.playerName;
    }
}
