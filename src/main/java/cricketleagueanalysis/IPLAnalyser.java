package cricketleagueanalysis;

import com.google.gson.Gson;

import java.util.*;

import static java.util.stream.Collectors.toCollection;

public class IPLAnalyser {
    Map<String, IPLAnalyserDAO> iplAnalysisMap = null;
    Map<SortedDataBaseOnField, Comparator<IPLAnalyserDAO>> fieldsName = null;
    public IPLAdapter iplAdapter;

    public IPLAnalyser() {
        this.iplAnalysisMap = new HashMap<>();
        this.fieldsName = new HashMap<>();
        this.fieldsName.put(SortedDataBaseOnField.AVERAGE_BATSMAN, Comparator.comparing(census -> census.batsmanAverage, Comparator.reverseOrder()));
        this.fieldsName.put(SortedDataBaseOnField.STRIKING_RATE, Comparator.comparing(census -> census.strikeRate, Comparator.reverseOrder()));
        this.fieldsName.put(SortedDataBaseOnField.BY_4s_AND_6s, new SortMethodContainer().reversed());
        this.fieldsName.put(SortedDataBaseOnField.STRIKING_RATE_WITH_6S_And_4s, new SortMethodContainer().reversed().thenComparing(compare -> compare.strikeRate));
        Comparator<IPLAnalyserDAO> average = Comparator.comparing(censusDAO -> censusDAO.batsmanAverage, Comparator.reverseOrder());
        this.fieldsName.put(SortedDataBaseOnField.AVERAGE_WITH_BEST_STRIKING_RATE, average.thenComparing(censusDAO -> censusDAO.strikeRate, Comparator.reverseOrder()));
        Comparator<IPLAnalyserDAO> bestRuns = Comparator.comparing(censusDAO -> censusDAO.numberOfRuns, Comparator.reverseOrder());
        this.fieldsName.put(SortedDataBaseOnField.MAXIMUM_RUN_WITH_AVERAGE, bestRuns.thenComparing(average));
        this.fieldsName.put(SortedDataBaseOnField.AVERAGE_BOWLER, Comparator.comparing(census -> census.bowlerAverage.intValue()));
        this.fieldsName.put(SortedDataBaseOnField.STRIKING_RATE_BOWLER, Comparator.comparing(census -> census.strikeRate.intValue()));
        this.fieldsName.put(SortedDataBaseOnField.ECONOMY, Comparator.comparing(census -> census.economy));
        this.fieldsName.put(SortedDataBaseOnField.BY_4w_AND_5w, new SortedByWickets().reversed().thenComparing(census -> census.strikeRate));
        Comparator<IPLAnalyserDAO> bowlingAverage = Comparator.comparing(censusDAO -> censusDAO.bowlerAverage, Comparator.reverseOrder());
        this.fieldsName.put(SortedDataBaseOnField.AVERAGE_WITH_BEST_STRIKING_RATE_BOWLER, bowlingAverage.thenComparing(censusDAO -> censusDAO.strikeRate));
        Comparator<IPLAnalyserDAO> bestWickets = Comparator.comparing(censusDAO -> censusDAO.wickets, Comparator.reverseOrder());
        this.fieldsName.put(SortedDataBaseOnField.MAXIMUM_WICKET_WITH_AVERAGE, bestWickets.thenComparing(bowlingAverage));
        Comparator<IPLAnalyserDAO> batingAverage = Comparator.comparing(censusDAO -> censusDAO.batsmanAverage,Comparator.reverseOrder());
        this.fieldsName.put(SortedDataBaseOnField.BEST_BOWLING_BATTING_AVERAGE,  bowlingAverage.thenComparing(batingAverage));
        this.fieldsName.put(SortedDataBaseOnField.BEST_ALL_ROUNDER, new MostRunAndWickets().reversed());
    }

    public void setIPLAdapter(IPLAdapter iplAdapter) {
      this.iplAdapter=iplAdapter;
    }

    public int loadIplData(Player playerType, String... csvFilePath) throws IPLException {
        IPLAdapter iplAdapter = IPLObjectFactory.getIPLDataObject(playerType);
        this.iplAnalysisMap = iplAdapter.loadIplData(csvFilePath);
        return this.iplAnalysisMap.size();
    }

    public String getSortedPlayerData(SortedDataBaseOnField fieldName) throws IPLException {
        if (this.iplAnalysisMap == null || this.iplAnalysisMap.size() == 0) {
            throw new IPLException("No Data", IPLException
                    .ExceptionType.CENSUS_FILE_PROBLEM);
        }
        ArrayList<IPLAnalyserDAO> arrayList = this.iplAnalysisMap.values().stream()
                .sorted(this.fieldsName.get(fieldName))
                .collect(toCollection(ArrayList::new));
        String sortedStateCensus = new Gson().toJson(arrayList);
        return sortedStateCensus;
    }
}
