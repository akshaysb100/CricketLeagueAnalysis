package iplanaysistest;

import com.csvbuilder.CsvBuilderException;
import cricketleagueanalysis.IPLAnalysis;
import cricketleagueanalysis.IPLException;
import org.junit.Assert;
import org.junit.Test;

public class ILPAnalysisTest {

    private static final String IPL_MOST_RUN_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_DELIMITER_CSV_FILE_PATH = "./src/test/resources/WrongDelimiterIPL2019MostRun.csv";

    @Test
    public void givenIPLMostRunFile_ShouldGetMapOfCorrectSize() {
        IPLAnalysis iplAnalysis = new IPLAnalysis();
        int numOfRecords = 0;
        try {
            numOfRecords = iplAnalysis.loadIPLCSVFileData(IPL_MOST_RUN_CSV_FILE_PATH);
            Assert.assertEquals(100, numOfRecords);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLMostRunFile_WithWrongDelimiter_ShouldThrowException() {
        IPLAnalysis iplAnalysis = new IPLAnalysis();
        int numOfRecords = 0;
        try {
            numOfRecords = iplAnalysis.loadIPLCSVFileData(WRONG_DELIMITER_CSV_FILE_PATH);
            Assert.assertEquals(100, numOfRecords);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
}
