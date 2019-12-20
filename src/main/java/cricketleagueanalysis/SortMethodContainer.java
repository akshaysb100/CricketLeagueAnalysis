package cricketleagueanalysis;

import java.util.Comparator;

public class SortMethodContainer implements Comparator<IPLAnalyserDAO> {
    @Override
    public int compare(IPLAnalyserDAO iplMostRunsCSV, IPLAnalyserDAO iplMostRunsCSV1) {
        return ((iplMostRunsCSV.numberOf6s * 6) + (iplMostRunsCSV.numberOf4s * 4)) -
                ((iplMostRunsCSV1.numberOf6s * 6) + (iplMostRunsCSV1.numberOf4s * 4));
    }
}
