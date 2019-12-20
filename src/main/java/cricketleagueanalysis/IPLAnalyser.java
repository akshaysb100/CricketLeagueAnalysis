package cricketleagueanalysis;

import com.google.gson.Gson;

import java.util.*;

import static java.util.stream.Collectors.toCollection;

public class IPLAnalyser {
    Map<String, IPLAnalyserDAO> analysisMap = new HashMap<String, IPLAnalyserDAO>();
    Map<SortedDataBaseOnBatsmanField, Comparator<IPLAnalyserDAO>> fieldsName = null;

    public IPLAnalyser() {
        this.fieldsName = new HashMap<>();
        this.fieldsName.put(SortedDataBaseOnBatsmanField.AVERAGE_BATSMAN, Comparator.comparing(census -> census.average, Comparator.reverseOrder()));
        this.fieldsName.put(SortedDataBaseOnBatsmanField.STRIKING_RATE, Comparator.comparing(census -> census.strikeRate, Comparator.reverseOrder()));
        this.fieldsName.put(SortedDataBaseOnBatsmanField.BY_4s_AND_6s, new SortMethodContainer().reversed());
        this.fieldsName.put(SortedDataBaseOnBatsmanField.STRIKING_RATE_WITH_6S_And_4s, new SortMethodContainer().reversed().thenComparing(compare -> compare.strikeRate));
        Comparator<IPLAnalyserDAO> comp = Comparator.comparing(censusDAO -> censusDAO.average, Comparator.reverseOrder());
        this.fieldsName.put(SortedDataBaseOnBatsmanField.AVERAGE_WITH_BEST_STRIKING_RATE, comp.thenComparing(censusDAO -> censusDAO.strikeRate, Comparator.reverseOrder()));
        Comparator<IPLAnalyserDAO> runs = Comparator.comparing(censusDAO -> censusDAO.numberOfRuns, Comparator.reverseOrder());
        this.fieldsName.put(SortedDataBaseOnBatsmanField.MAXIMUM_RUN_WITH_AVERAGE, runs.thenComparing(comp));
        this.fieldsName.put(SortedDataBaseOnBatsmanField.AVERAGE_BOWLER, Comparator.comparing(census -> census.average));
    }

    public int loadIplData(Player fileEnum, String csvFilePath) throws IPLException {
        IPLAdapter iplAdapter = IPLObjectFactory.getIPLDataObject(fileEnum);
        analysisMap = iplAdapter.loadIplData(fileEnum, csvFilePath);
        return analysisMap.size();
    }

    public String getTopAverageBattingPlayerName(SortedDataBaseOnBatsmanField fieldName) throws IPLException {
        if (analysisMap == null || analysisMap.size() == 0) {
            throw new IPLException("No Data", IPLException
                    .ExceptionType.CENSUS_FILE_PROBLEM);
        }
        ArrayList arrayList = analysisMap.values().stream()
                .sorted(this.fieldsName.get(fieldName))
                .collect(toCollection(ArrayList::new));
        String sortedStateCensus = new Gson().toJson(arrayList);
        return sortedStateCensus;
    }
}
