package cricketleagueanalysis;

import java.util.HashMap;
import java.util.Map;

public class IPLBowlerAdapter extends IPLAdapter {
    @Override
    public Map<String, IPLAnalyserDAO> loadIplData(Player fileEnum, String filePath) throws IPLException {
        Map<String, IPLAnalyserDAO> iplDAOMap = new HashMap<>();
        iplDAOMap = super.loadIplCSVFileData(IPLBowlerData.class, filePath);
        return iplDAOMap;
    }
}
