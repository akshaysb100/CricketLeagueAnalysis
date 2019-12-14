package cricketleagueanalysis;

import com.csvbuilder.CSVBuilderFactory;
import com.csvbuilder.CsvBuilderException;
import com.csvbuilder.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toCollection;

public class IPLAnalysis {
    Map<String, IPLAnalysisDAO> analysisMap = new HashMap<String, IPLAnalysisDAO>();
    Map<SortedDataBaseOnField, Comparator<IPLAnalysisDAO>> fields = null;

    public IPLAnalysis() {
        this.fields = new HashMap<>();
        this.fields.put(SortedDataBaseOnField.AVERAGE, Comparator.comparing(census -> census.average,Comparator.reverseOrder()));
        this.fields.put(SortedDataBaseOnField.STRIKING_RATE, Comparator.comparing(census -> census.strikeRate,Comparator.reverseOrder()));
    }

    public int loadIPLCSVFileData(String iplMostRunCsvFilePath) throws IPLException {
        try (Reader reader = Files.newBufferedReader(Paths.get(iplMostRunCsvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLMostRunCsvData> censusList = csvBuilder.getCSVFileIterator(reader, IPLMostRunCsvData.class);
            Iterable<IPLMostRunCsvData> indiaCensusCSVS = () -> censusList;
            StreamSupport.stream(indiaCensusCSVS.spliterator(), false)
                    .map(IPLMostRunCsvData.class::cast)
                    .forEach(csvCensus -> analysisMap.put(csvCensus.playerName, new IPLAnalysisDAO(csvCensus)));
        } catch (IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.WRONG_FILE_PATH);
        } catch (CsvBuilderException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
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
