package iplanaysistest;

import cricketleagueanalysis.IPLAnalyser;
import cricketleagueanalysis.IPLException;
import cricketleagueanalysis.Player;
import org.junit.Assert;
import org.junit.Test;

public class IPLBowlerTest {
    private static final String IPL_MOST_WICKETS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenIPLMostWktCSVFile_ShouldGetMapOfCorrectSize() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        int numOfRecords = 0;
        try {
            numOfRecords = iplAnalyser.loadIplData(Player.BOWLER, IPL_MOST_WICKETS_CSV_FILE_PATH);
            Assert.assertEquals(99, numOfRecords);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
}
