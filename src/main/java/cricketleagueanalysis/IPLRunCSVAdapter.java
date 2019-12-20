package cricketleagueanalysis;

import java.util.HashMap;
import java.util.Map;

public class IPLRunCSVAdapter extends IPLCSVFIleAdapter {
    @Override
    public Map<String, IPLAnalysisDAO> loadIplData(IPLCSVFileEnum fileEnum, String filePath) throws IPLException {
        Map<String, IPLAnalysisDAO> iplDAOMap = new HashMap<>();
        iplDAOMap = super.loadIplCSVFileData(IPLMostRunCsvData.class, filePath);
        return iplDAOMap;
    }
}
