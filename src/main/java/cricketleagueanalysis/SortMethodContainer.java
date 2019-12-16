package cricketleagueanalysis;

import java.util.Comparator;

public class SortMethodContainer implements Comparator<IPLAnalysisDAO> {

    @Override
    public int compare(IPLAnalysisDAO iplMostRunsCSV, IPLAnalysisDAO iplMostRunsCSV1) {
        return ((iplMostRunsCSV.numberOf6s * 6) + (iplMostRunsCSV.numberOf4s * 4)) - ((iplMostRunsCSV1.numberOf6s * 6) + (iplMostRunsCSV1.numberOf4s * 4));
    }
}
