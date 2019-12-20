package cricketleagueanalysis;

import java.util.HashMap;
import java.util.Map;

public class IPLBatsmanAdapter extends IPLAdapter {
    @Override
    public Map<String, IPLAnalyserDAO> loadIplData(Player fileEnum, String filePath) throws IPLException {
        Map<String, IPLAnalyserDAO> iplDAOMap = new HashMap<>();
        iplDAOMap = super.loadIplCSVFileData(IPLBatsmanData.class, filePath);
        return iplDAOMap;
    }
}
