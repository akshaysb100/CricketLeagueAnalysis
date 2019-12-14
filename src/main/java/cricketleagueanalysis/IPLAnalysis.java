package cricketleagueanalysis;

import com.csvbuilder.CSVBuilderFactory;
import com.csvbuilder.CsvBuilderException;
import com.csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class IPLAnalysis {

    public int loadIPLCSVFileData(String iplMostRunCsvFilePath) {
        int count = 0;
        try (Reader reader = Files.newBufferedReader(Paths.get(iplMostRunCsvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator censusList = csvBuilder.getCSVFileIterator(reader, IPLMostRunCsvData.class);
            while (censusList.hasNext()) {
                count++;
                censusList.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvBuilderException e) {
            e.printStackTrace();
        }
        return count;
    }
}
