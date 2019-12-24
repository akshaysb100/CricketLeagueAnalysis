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

public class IPLBatsmanAdapter extends IPLAdapter {
    Map<String, IPLAnalyserDAO> iplDAOMap = new HashMap<>();
    @Override
    public Map<String, IPLAnalyserDAO> loadIplData( String... filePath) throws IPLException {
        Map<String, IPLAnalyserDAO> iplDAOMap = new HashMap<>();
        iplDAOMap = super.loadIplCSVFileData(IPLBatsmanData.class, filePath[0]);
        return iplDAOMap;
    }
}
