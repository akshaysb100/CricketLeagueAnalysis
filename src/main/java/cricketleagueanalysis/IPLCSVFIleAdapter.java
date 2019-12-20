package cricketleagueanalysis;

import com.csvbuilder.CSVBuilderFactory;
import com.csvbuilder.CsvBuilderException;
import com.csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class IPLCSVFIleAdapter {
    public abstract Map<String, IPLAnalysisDAO> loadIplData(IPLCSVFileEnum fileEnum, String filePath) throws IPLException;

    Map<String, IPLAnalysisDAO> analysisMap = new HashMap<String, IPLAnalysisDAO>();

    public <E> Map<String, IPLAnalysisDAO> loadIplCSVFileData(Class<E> censusCSVClass, String csvFilePath) throws IPLException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> censusList = csvBuilder.getCSVFileIterator(reader, censusCSVClass);
            Iterable<E> CensusCSVS = () -> censusList;
            if (censusCSVClass.getName().equals("cricketleagueanalysis.IPLMostRunCsvData")) {
                StreamSupport.stream(CensusCSVS.spliterator(), false)
                        .map(IPLMostRunCsvData.class::cast)
                        .forEach(csvCensus -> analysisMap.put(csvCensus.playerName, new IPLAnalysisDAO(csvCensus)));
            } else if (censusCSVClass.getName().equals("cricketleagueanalysis.IPLMostWicketsCsvData")) {
                StreamSupport.stream(CensusCSVS.spliterator(), false)
                        .map(IPLMostWicketsCsvData.class::cast)
                        .forEach(csvCensus -> analysisMap.put(csvCensus.playerName, new IPLAnalysisDAO(csvCensus)));
            }
        } catch (IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.WRONG_FILE_PATH);
        } catch (CsvBuilderException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        return analysisMap;
    }


}
