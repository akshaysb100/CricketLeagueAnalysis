package cricketleagueanalysis;

public class IPLCSVFileFactory {
    public static IPLCSVFIleAdapter getIPLDataObject(IPLCSVFileEnum fileEnum) {
        if (fileEnum.equals(IPLCSVFileEnum.RUN_CSV_FILE)) {
            return new IPLRunCSVAdapter();
        } else return new IPLMostWicketAdapter();
    }
}
