package iplanaysistest;

import com.google.gson.Gson;
import cricketleagueanalysis.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class ILPBatsmanTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private static final String IPL_MOST_RUN_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_DELIMITER_CSV_FILE_PATH = "./src/test/resources/WrongDelimiterIPL2019MostRun.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkt.csv";

    @Test
    public void givenIPLMostRunCSVFile_ShouldGetMapOfCorrectSize() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
        int numOfRecords = 0;
        try {
            numOfRecords = iplAnalyser.loadIplData(Player.BATSMAN, IPL_MOST_RUN_CSV_FILE_PATH);
            Assert.assertEquals(100, numOfRecords);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCSVFile_WithWrongDelimiter_ShouldThrowException() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
        int numOfRecords = 0;
        try {
            numOfRecords = iplAnalyser.loadIplData(Player.BATSMAN, WRONG_DELIMITER_CSV_FILE_PATH);
            Assert.assertEquals(100, numOfRecords);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCSVFile_WithWrongFile_ShouldThrowException() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
        int numOfRecords = 0;
        try {
            numOfRecords = iplAnalyser.loadIplData(Player.BATSMAN, WRONG_CSV_FILE_PATH);
            Assert.assertEquals(100, numOfRecords);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCSVFile_WithPassNullFile_ShouldThrowException() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
        int numOfRecords = 0;
        try {
            numOfRecords = iplAnalyser.loadIplData(Player.BATSMAN, "");
            Assert.assertEquals(0, numOfRecords);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCSVFile_SortedOnBestBattingAverage_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
        String sortedData = null;
        try {
            iplAnalyser.loadIplData(Player.BATSMAN, IPL_MOST_RUN_CSV_FILE_PATH);
            sortedData = iplAnalyser.getSortedPlayerData(SortedDataBaseOnField.BATSMAN_AVERAGE);
            IPLBatsmanData[] censusCSV = new Gson().fromJson(sortedData, IPLBatsmanData[].class);
            Assert.assertEquals("MS Dhoni", censusCSV[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCSVFile_DataNotLoad_ShouldThrowException() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
        String sortedData = null;
        try {
            sortedData = iplAnalyser.getSortedPlayerData(SortedDataBaseOnField.BATSMAN_AVERAGE);
            IPLBatsmanData[] censusCSV = new Gson().fromJson(sortedData, IPLBatsmanData[].class);
            Assert.assertEquals("MS Dhoni", censusCSV[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCSVFile_TopStrikingRatesOfBatsman_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
        String sortedData = null;
        try {
            iplAnalyser.loadIplData(Player.BATSMAN, IPL_MOST_RUN_CSV_FILE_PATH);
            sortedData = iplAnalyser.getSortedPlayerData(SortedDataBaseOnField.STRIKING_RATE);
            IPLBatsmanData[] censusCSV = new Gson().fromJson(sortedData, IPLBatsmanData[].class);
            Assert.assertEquals("Ishant Sharma", censusCSV[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCsvFile_WhoHitMaximumSixAndFour_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
        String sortedData = null;
        try {
            iplAnalyser.loadIplData(Player.BATSMAN, IPL_MOST_RUN_CSV_FILE_PATH);
            sortedData = iplAnalyser.getSortedPlayerData(SortedDataBaseOnField.BY_4s_AND_6s);
            IPLBatsmanData[] censusCSV = new Gson().fromJson(sortedData, IPLBatsmanData[].class);
            Assert.assertEquals("Andre Russell", censusCSV[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCSVFile_TopStrikingRatesOfBatsmanWithMaximum6sAnd4s_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
        String sortedData = null;
        try {
            iplAnalyser.loadIplData(Player.BATSMAN, IPL_MOST_RUN_CSV_FILE_PATH);
            sortedData = iplAnalyser.getSortedPlayerData(SortedDataBaseOnField.STRIKING_RATE_WITH_6S_And_4s);
            IPLBatsmanData[] censusCSV = new Gson().fromJson(sortedData, IPLBatsmanData[].class);
            Assert.assertEquals("Andre Russell", censusCSV[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCSVFile_WhoHadGreatAverageWithBEstStrikingRates_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
        String sortedData = null;
        try {
            iplAnalyser.loadIplData(Player.BATSMAN, IPL_MOST_RUN_CSV_FILE_PATH);
            sortedData = iplAnalyser.getSortedPlayerData(SortedDataBaseOnField.AVERAGE_WITH_BEST_STRIKING_RATE);
            IPLBatsmanData[] censusCSV = new Gson().fromJson(sortedData, IPLBatsmanData[].class);
            Assert.assertEquals("MS Dhoni", censusCSV[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCSVFile_WhoHitMaximumRunsWithBestAverage_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
        String sortedData = null;
        try {
            iplAnalyser.loadIplData(Player.BATSMAN, IPL_MOST_RUN_CSV_FILE_PATH);
            sortedData = iplAnalyser.getSortedPlayerData(SortedDataBaseOnField.MAXIMUM_RUN_WITH_AVERAGE);
            IPLBatsmanData[] censusCSV = new Gson().fromJson(sortedData, IPLBatsmanData[].class);
            Assert.assertEquals("David Warner", censusCSV[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }
}
