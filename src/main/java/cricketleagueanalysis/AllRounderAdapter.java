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

    @Override
    public Map<String, IPLAnalyserDAO> loadIplData(String... filePath) throws IPLException {
        Map<String, IPLAnalyserDAO> map1 = new HashMap<>();
        map1 = super.loadIplCSVFileData(IPLBatsmanData.class, filePath[0]);
        if (filePath.length == 3)
            this.loadBowlerData(map1, filePath[1]);
        return map1;
    }

    private int loadBowlerData(Map<String, IPLAnalyserDAO> map1, String iplFilePath) throws IPLException {
        try (Reader reader = Files.newBufferedReader(Paths.get(iplFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLBowlerData> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IPLBowlerData.class);
            Iterable<IPLBowlerData> csvIterable = () -> csvFileIterator;
            StreamSupport.stream
                    (csvIterable.spliterator(), false)
                    .map(IPLBowlerData.class::cast)
                    .filter(csvPlayer -> map1.get(csvPlayer.economy) != null && map1.get(csvPlayer.average) != null)
                    .forEach(bowler -> map1.get(bowler.playerName).bowlerAverage = bowler.average);
            return map1.size();
        } catch (IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CsvBuilderException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }
}
