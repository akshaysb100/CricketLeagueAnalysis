package cricketleagueanalysis;

import java.util.HashMap;
import java.util.Map;

public class IPLBowlerAdapter extends IPLAdapter {
    @Override
    public Map<String, IPLAnalyserDAO> loadIplData( String... filePath) throws IPLException {
        Map<String, IPLAnalyserDAO> iplDAOMap = new HashMap<>();
        iplDAOMap = super.loadIplCSVFileData(IPLBowlerData.class, filePath[0]);
        return iplDAOMap;
    }
}
