package cricketleagueanalysis;

public class IPLAnalyserDAO {
    public int numberOfFiveWickets;
    public int numberOfFoursWicket;
    public Double economy = 99.0;
    public int wickets;
    public int position;
    public String playerName;
    public Double strikeRate = 0.0;
    public Double batsmanAverage = 0.0;
    public Double bowlerAverage = 999.0;
    public int numberOf6s;
    public int numberOf4s;
    public int numberOfRuns;

    public IPLAnalyserDAO() {
    }

    public IPLAnalyserDAO(String playerName, int numberOfRuns, double batsmanAverage, int numberOf4s, int numberOf6s, int numberOfFiveWickets) {
       this.playerName =playerName;
       this.numberOfRuns = numberOfRuns;
       this.batsmanAverage = batsmanAverage;
       this.numberOf4s = numberOf4s;
       this.numberOf6s = numberOf6s;
       this.numberOfFiveWickets = numberOfFiveWickets;
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
