package cricketleagueanalysis;

public class IPLAnalyserDAO {
    public int numberOfFiveWickets;
    public int numberOfFoursWicket;
    public Double economy;
    public int wickets;
    public int position;
    public String playerName;
    public Double strikeRate;
    public Double batsmanAverage;
    public Double bowlerAverage;
    public int numberOf6s;
    public int numberOf4s;
    public int numberOfRuns;

    public IPLAnalyserDAO() {
    }

    public IPLAnalyserDAO(IPLBatsmanData iplBatsmanData) {
        position = iplBatsmanData.position;
        playerName = iplBatsmanData.playerName;
        strikeRate = iplBatsmanData.strikeRate;
        batsmanAverage = iplBatsmanData.average;
        numberOf4s = iplBatsmanData.numberOfFours;
        numberOf6s = iplBatsmanData.numberOfSix;
        numberOfRuns = iplBatsmanData.runs;
    }

    public IPLAnalyserDAO(IPLBowlerData iplBowlerData) {
        playerName = iplBowlerData.playerName;
        bowlerAverage = iplBowlerData.average;
        strikeRate = iplBowlerData.strikeRate;
        wickets =iplBowlerData.wickets;
        economy = iplBowlerData.economy;
        numberOfFoursWicket = iplBowlerData.numberOfFoursWicket;
        numberOfFiveWickets = iplBowlerData.numberOfFiveWickets;
    }
}
