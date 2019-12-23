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

public abstract class IPLAdapter {
    public abstract Map<String, IPLAnalyserDAO> loadIplData(String... filePath) throws IPLException;

    Map<String, IPLAnalyserDAO> analysisMap = new HashMap<String, IPLAnalyserDAO>();

    public <E> Map<String, IPLAnalyserDAO> loadIplCSVFileData(Class<E> censusCSVClass, String... csvFilePath) throws IPLException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0]))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> censusList = csvBuilder.getCSVFileIterator(reader, censusCSVClass);
            Iterable<E> CensusCSVS = () -> censusList;
            if (censusCSVClass.getName().equals("cricketleagueanalysis.IPLBatsmanData")) {
                StreamSupport.stream(CensusCSVS.spliterator(), false)
                        .map(IPLBatsmanData.class::cast)
                        .forEach(csvCensus -> analysisMap.put(csvCensus.playerName, new IPLAnalyserDAO(csvCensus)));
            } else if (censusCSVClass.getName().equals("cricketleagueanalysis.IPLBowlerData")) {
                StreamSupport.stream(CensusCSVS.spliterator(), false)
                        .map(IPLBowlerData.class::cast)
                        .forEach(csvCensus -> analysisMap.put(csvCensus.playerName, new IPLAnalyserDAO(csvCensus)));
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
