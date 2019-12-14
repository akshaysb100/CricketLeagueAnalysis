package iplanaysistest;

import cricketleagueanalysis.IPLAnalysis;
import org.junit.Assert;
import org.junit.Test;

public class ILPAnalysisTest {

    private static final String IPL_MOST_RUN_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";

    @Test
    public void givenIPLMostRunFile_ShouldGetMapOfCorrectSize() {
        IPLAnalysis iplAnalysis = new IPLAnalysis();
        int numOfRecords = iplAnalysis.loadIPLCSVFileData(IPL_MOST_RUN_CSV_FILE_PATH);
        Assert.assertEquals(100,numOfRecords);
    }
}
