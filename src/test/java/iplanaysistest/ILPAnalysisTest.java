package iplanaysistest;

import com.google.gson.Gson;
import cricketleagueanalysis.*;
import org.junit.Assert;
import org.junit.Test;

public class ILPAnalysisTest {
    private static final String IPL_MOST_RUN_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_DELIMITER_CSV_FILE_PATH = "./src/test/resources/WrongDelimiterIPL2019MostRun.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkt.csv";
    private static final String IPL_MOST_WICKETS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenIPLMostRunCSVFile_ShouldGetMapOfCorrectSize() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        int numOfRecords = 0;
        try {
            numOfRecords = iplAnalyser.loadIplData(IPLCSVFileEnum.RUN_CSV_FILE,IPL_MOST_RUN_CSV_FILE_PATH);
            Assert.assertEquals(100, numOfRecords);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCSVFile_WithWrongDelimiter_ShouldThrowException() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        int numOfRecords = 0;
        try {
            numOfRecords = iplAnalyser.loadIplData(IPLCSVFileEnum.RUN_CSV_FILE,WRONG_DELIMITER_CSV_FILE_PATH);
            Assert.assertEquals(100, numOfRecords);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCSVFile_WithWrongFile_ShouldThrowException() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        int numOfRecords = 0;
        try {
            numOfRecords = iplAnalyser.loadIplData(IPLCSVFileEnum.RUN_CSV_FILE,WRONG_CSV_FILE_PATH);
            Assert.assertEquals(100, numOfRecords);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCSVFile_WithPassNullFile_ShouldThrowException() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        int numOfRecords = 0;
        try {
            numOfRecords = iplAnalyser.loadIplData(IPLCSVFileEnum.RUN_CSV_FILE,"");
            Assert.assertEquals(0, numOfRecords);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCSVFile_SortedOnBestBattingAverage_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        String sortedData = null;
        try {
            iplAnalyser.loadIplData(IPLCSVFileEnum.RUN_CSV_FILE,IPL_MOST_RUN_CSV_FILE_PATH);
            sortedData = iplAnalyser.getTopAverageBattingPlayerName(SortedDataBaseOnField.AVERAGE);
            IPLMostRunCsvData[] censusCSV = new Gson().fromJson(sortedData, IPLMostRunCsvData[].class);
            Assert.assertEquals("MS Dhoni", censusCSV[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCSVFile_DataNotLoad_ShouldThrowException() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        String sortedData = null;
        try {
            sortedData = iplAnalyser.getTopAverageBattingPlayerName(SortedDataBaseOnField.AVERAGE);
            IPLMostRunCsvData[] censusCSV = new Gson().fromJson(sortedData, IPLMostRunCsvData[].class);
            Assert.assertEquals("MS Dhoni", censusCSV[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCSVFile_TopStrikingRatesOfBatsman_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        String sortedData = null;
        try {
            iplAnalyser.loadIplData(IPLCSVFileEnum.RUN_CSV_FILE,IPL_MOST_RUN_CSV_FILE_PATH);
            sortedData = iplAnalyser.getTopAverageBattingPlayerName(SortedDataBaseOnField.STRIKING_RATE);
            IPLMostRunCsvData[] censusCSV = new Gson().fromJson(sortedData, IPLMostRunCsvData[].class);
            Assert.assertEquals("Ishant Sharma", censusCSV[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCsvFile_WhoHitMaximumSixAndFour_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        String sortedData = null;
        try {
            iplAnalyser.loadIplData(IPLCSVFileEnum.RUN_CSV_FILE,IPL_MOST_RUN_CSV_FILE_PATH);
            sortedData = iplAnalyser.getTopAverageBattingPlayerName(SortedDataBaseOnField.BY_4s_AND_6s);
            IPLMostRunCsvData[] censusCSV = new Gson().fromJson(sortedData, IPLMostRunCsvData[].class);
            Assert.assertEquals("Andre Russell", censusCSV[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCSVFile_TopStrikingRatesOfBatsmanWithMaximum6sAnd4s_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        String sortedData = null;
        try {
            iplAnalyser.loadIplData(IPLCSVFileEnum.RUN_CSV_FILE,IPL_MOST_RUN_CSV_FILE_PATH);
            sortedData = iplAnalyser.getTopAverageBattingPlayerName(SortedDataBaseOnField.STRIKING_RATE_WITH_6S_And_4s);
            IPLMostRunCsvData[] censusCSV = new Gson().fromJson(sortedData, IPLMostRunCsvData[].class);
            Assert.assertEquals("Andre Russell", censusCSV[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCSVFile_WhoHadGreatAverageWithBEstStrikingRates_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        String sortedData = null;
        try {
            iplAnalyser.loadIplData(IPLCSVFileEnum.RUN_CSV_FILE,IPL_MOST_RUN_CSV_FILE_PATH);
            sortedData = iplAnalyser.getTopAverageBattingPlayerName(SortedDataBaseOnField.AVERAGE_WITH_BEST_STRIKING_RATE);
            IPLMostRunCsvData[] censusCSV = new Gson().fromJson(sortedData, IPLMostRunCsvData[].class);
            Assert.assertEquals("MS Dhoni", censusCSV[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCSVFile_WhoHitMaximumRunsWithBestAverage_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        String sortedData = null;
        try {
            iplAnalyser.loadIplData(IPLCSVFileEnum.RUN_CSV_FILE,IPL_MOST_RUN_CSV_FILE_PATH);
            sortedData = iplAnalyser.getTopAverageBattingPlayerName(SortedDataBaseOnField.MAXIMUM_RUN_WITH_AVERAGE);
            IPLMostRunCsvData[] censusCSV = new Gson().fromJson(sortedData, IPLMostRunCsvData[].class);
            Assert.assertEquals("David Warner", censusCSV[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }

    @Test
    public void givenIPLMostWktCSVFile_ShouldGetMapOfCorrectSize() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        int numOfRecords = 0;
        try {
            numOfRecords = iplAnalyser.loadIplData(IPLCSVFileEnum.WICKET_CSV_FILE,IPL_MOST_WICKETS_CSV_FILE_PATH);
            Assert.assertEquals(99, numOfRecords);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
}
