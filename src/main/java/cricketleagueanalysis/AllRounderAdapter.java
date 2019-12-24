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

public class AllRounderAdapter extends IPLAdapter {
    Map<String, IPLAnalyserDAO> map1 = new HashMap<>();

    @Override
    public Map<String, IPLAnalyserDAO> loadIplData(String... filePath) throws IPLException {
        map1 = super.loadIplCSVFileData(IPLBatsmanData.class, filePath[0]);
        this.loadBowlerData(filePath[1]);
        return map1;
    }

    private int loadBowlerData(String filePath) throws IPLException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLBowlerData> stateCSVIterator = csvBuilder.getCSVFileIterator(reader, IPLBowlerData.class);
            Iterable<IPLBowlerData> csvIterable = () -> stateCSVIterator;
            StreamSupport.stream
                    (csvIterable.spliterator(), false)
                    .map(IPLBowlerData.class::cast)
                    .filter(csvPlayer -> map1.get(csvPlayer.economy) != null)
                    .forEach(mergedData -> {
                        map1.get(mergedData.playerName).bowlerAverage = mergedData.average;
                        map1.get(mergedData.playerName).wickets = mergedData.wickets;
                    });
        } catch (IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CsvBuilderException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        return map1.size();
    }
}
