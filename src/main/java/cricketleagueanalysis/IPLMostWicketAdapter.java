package cricketleagueanalysis;

import java.util.HashMap;
import java.util.Map;

public class IPLMostWicketAdapter extends IPLCSVFIleAdapter {
    @Override
    public Map<String, IPLAnalysisDAO> loadIplData(IPLCSVFileEnum fileEnum, String filePath) throws IPLException {
        Map<String, IPLAnalysisDAO> iplDAOMap = new HashMap<>();
        iplDAOMap = super.loadIplCSVFileData(IPLMostWicketsCsvData.class, filePath);
        return iplDAOMap;
    }
}
