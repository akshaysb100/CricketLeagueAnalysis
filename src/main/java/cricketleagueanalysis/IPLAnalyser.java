package cricketleagueanalysis;

import com.google.gson.Gson;

import java.util.*;

import static java.util.stream.Collectors.toCollection;

public class IPLAnalyser {
    Map<String, IPLAnalysisDAO> analysisMap = new HashMap<String, IPLAnalysisDAO>();
    Map<SortedDataBaseOnField, Comparator<IPLAnalysisDAO>> fields = null;

    public IPLAnalyser() {
        this.fields = new HashMap<>();
        this.fields.put(SortedDataBaseOnField.AVERAGE, Comparator.comparing(census -> census.average, Comparator.reverseOrder()));
        this.fields.put(SortedDataBaseOnField.STRIKING_RATE, Comparator.comparing(census -> census.strikeRate, Comparator.reverseOrder()));
        this.fields.put(SortedDataBaseOnField.BY_4s_AND_6s, new SortMethodContainer().reversed());
        this.fields.put(SortedDataBaseOnField.STRIKING_RATE_WITH_6S_And_4s, new SortMethodContainer().reversed().thenComparing(compare -> compare.strikeRate));
        Comparator<IPLAnalysisDAO> comp = Comparator.comparing(censusDAO -> censusDAO.average, Comparator.reverseOrder());
        this.fields.put(SortedDataBaseOnField.AVERAGE_WITH_BEST_STRIKING_RATE, comp.thenComparing(censusDAO -> censusDAO.strikeRate, Comparator.reverseOrder()));
        Comparator<IPLAnalysisDAO> runs = Comparator.comparing(censusDAO -> censusDAO.numberOfRuns, Comparator.reverseOrder());
        this.fields.put(SortedDataBaseOnField.MAXIMUM_RUN_WITH_AVERAGE, runs.thenComparing(comp));
    }

    public int loadIplData(IPLCSVFileEnum fileEnum, String csvFilePath) throws IPLException {
        IPLCSVFIleAdapter iplAdapter = IPLCSVFileFactory.getIPLDataObject(fileEnum);
        analysisMap = iplAdapter.loadIplData(fileEnum, csvFilePath);
        return analysisMap.size();
    }

    public String getTopAverageBattingPlayerName(SortedDataBaseOnField fieldName) throws IPLException {
        if (analysisMap == null || analysisMap.size() == 0) {
            throw new IPLException("No Data", IPLException
                    .ExceptionType.CENSUS_FILE_PROBLEM);
        }
        ArrayList arrayList = analysisMap.values().stream()
                .sorted(this.fields.get(fieldName))
                .collect(toCollection(ArrayList::new));
        String sortedStateCensus = new Gson().toJson(arrayList);
        return sortedStateCensus;
    }
}
