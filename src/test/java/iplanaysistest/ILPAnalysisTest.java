package iplanaysistest;

import com.google.gson.Gson;
import cricketleagueanalysis.IPLAnalysis;
import cricketleagueanalysis.IPLException;
import cricketleagueanalysis.IPLMostRunCsvData;
import cricketleagueanalysis.SortedDataBaseOnField;
import org.junit.Assert;
import org.junit.Test;

public class ILPAnalysisTest {

    private static final String IPL_MOST_RUN_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_DELIMITER_CSV_FILE_PATH = "./src/test/resources/WrongDelimiterIPL2019MostRun.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkt.csv";

    @Test
    public void givenIPLMostRunCSVFile_ShouldGetMapOfCorrectSize() {
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
    public void givenIPLMostRunCSVFile_WithWrongDelimiter_ShouldThrowException() {
        IPLAnalysis iplAnalysis = new IPLAnalysis();
        int numOfRecords = 0;
        try {
            numOfRecords = iplAnalysis.loadIPLCSVFileData(WRONG_DELIMITER_CSV_FILE_PATH);
            Assert.assertEquals(100, numOfRecords);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCSVFile_WithWrongFile_ShouldThrowException() {
        IPLAnalysis iplAnalysis = new IPLAnalysis();
        int numOfRecords = 0;
        try {
            numOfRecords = iplAnalysis.loadIPLCSVFileData(WRONG_CSV_FILE_PATH);
            Assert.assertEquals(100, numOfRecords);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCSVFile_WithPassNullFile_ShouldThrowException() {
        IPLAnalysis iplAnalysis = new IPLAnalysis();
        int numOfRecords = 0;
        try {
            numOfRecords = iplAnalysis.loadIPLCSVFileData("");
            Assert.assertEquals(100, numOfRecords);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCSVFile_SortedOnBestBattingAverage_ShouldReturnPlayerName() {
        IPLAnalysis iplAnalysis = new IPLAnalysis();
        String sortedData = null;
        try {
            iplAnalysis.loadIPLCSVFileData(IPL_MOST_RUN_CSV_FILE_PATH);
            sortedData = iplAnalysis.getTopAverageBattingPlayerName(SortedDataBaseOnField.AVERAGE);
            IPLMostRunCsvData[] censusCSV = new Gson().fromJson(sortedData, IPLMostRunCsvData[].class);
            Assert.assertEquals("MS Dhoni", censusCSV[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }
}
