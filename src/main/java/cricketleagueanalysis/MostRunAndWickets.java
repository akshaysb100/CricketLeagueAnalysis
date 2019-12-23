package cricketleagueanalysis;

import java.util.Comparator;

public class MostRunAndWickets implements Comparator<IPLAnalyserDAO> {
    @Override
    public int compare(IPLAnalyserDAO iplMostRunsCSV, IPLAnalyserDAO iplMostRunsCSV1) {
        return ((iplMostRunsCSV.numberOfRuns) + (iplMostRunsCSV.wickets*6)) -
                ((iplMostRunsCSV1.numberOfRuns) + (iplMostRunsCSV1.wickets*6));
    }

}
