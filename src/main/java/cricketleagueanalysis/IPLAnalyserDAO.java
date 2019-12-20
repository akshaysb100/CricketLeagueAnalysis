package cricketleagueanalysis;

public class IPLAnalyserDAO {
    public int position;
    public String playerName;
    public Double strikeRate;
    public Double average;
    public int numberOf6s;
    public int numberOf4s;
    public int numberOfRuns;

    public IPLAnalyserDAO(IPLBatsmanData iplBatsmanData) {
        position = iplBatsmanData.position;
        playerName = iplBatsmanData.playerName;
        strikeRate = iplBatsmanData.strikeRate;
        average = iplBatsmanData.average;
        numberOf4s = iplBatsmanData.numberOfFours;
        numberOf6s = iplBatsmanData.numberOfSix;
        numberOfRuns = iplBatsmanData.runs;
    }

    public IPLAnalyserDAO(IPLBowlerData iplBowlerData) {
        playerName = iplBowlerData.playerName;
        average = iplBowlerData.average;
    }
}
