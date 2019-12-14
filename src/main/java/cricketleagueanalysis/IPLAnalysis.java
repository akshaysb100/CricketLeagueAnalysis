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

public class IPLAnalysis {
    Map<String, IPLMostRunCsvData> analysisMap = new HashMap<String, IPLMostRunCsvData>();

    public int loadIPLCSVFileData(String iplMostRunCsvFilePath) throws IPLException {
        try (Reader reader = Files.newBufferedReader(Paths.get(iplMostRunCsvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLMostRunCsvData> censusList = csvBuilder.getCSVFileIterator(reader, IPLMostRunCsvData.class);
            Iterable<IPLMostRunCsvData> indiaCensusCSVS = () -> censusList;
            StreamSupport.stream(indiaCensusCSVS.spliterator(), false)
                    .map(IPLMostRunCsvData.class::cast)
                    .forEach(csvCensus -> analysisMap.put(csvCensus.playerName, new IPLMostRunCsvData(csvCensus)));
        } catch (IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.WRONG_FILE_PATH);
        } catch (CsvBuilderException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        return analysisMap.size();
    }
}
