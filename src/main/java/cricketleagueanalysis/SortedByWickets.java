package cricketleagueanalysis;

import java.util.Comparator;

public class SortedByWickets implements Comparator<IPLAnalyserDAO> {
    @Override
    public int compare(IPLAnalyserDAO iplAnalyserDAO1, IPLAnalyserDAO iplAnalyserDAO2) {
        return ((iplAnalyserDAO1.numberOfFiveWickets * 5) + (iplAnalyserDAO1.numberOfFoursWicket * 4)) -
                ((iplAnalyserDAO2.numberOfFiveWickets * 5) + (iplAnalyserDAO2.numberOfFoursWicket * 4));
    }
}
