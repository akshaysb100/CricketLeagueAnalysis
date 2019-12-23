package iplanaysistest;

import com.google.gson.Gson;
import cricketleagueanalysis.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class IPLBowlerTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private static final String IPL_MOST_RUN_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_MOST_WICKETS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkt.csv";

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

    @Test
    public void givenIPLMostWicketCSVFile_WithPassWrongFile_ShouldThrowException() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        int numOfRecords = 0;
        try {
            numOfRecords = iplAnalyser.loadIplData(Player.BATSMAN, WRONG_CSV_FILE_PATH);
            Assert.assertEquals(100, numOfRecords);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }

    @Test
    public void givenIPLMostRunCSVFile_SortedOnBestBattingAverage_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        String sortedData = null;
        try {
            iplAnalyser.loadIplData(Player.BOWLER, IPL_MOST_WICKETS_CSV_FILE_PATH);
            sortedData = iplAnalyser.getSortedPlayerData(SortedDataBaseOnField.AVERAGE_BOWLER);
            IPLBowlerData[] iplCSVData = new Gson().fromJson(sortedData, IPLBowlerData[].class);
            Assert.assertEquals("Suresh Raina", iplCSVData[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLMostWicketCSVFile_DataNotLoad_ShouldThrowException() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        String sortedData = null;
        try {
            sortedData = iplAnalyser.getSortedPlayerData(SortedDataBaseOnField.AVERAGE_BOWLER);
            IPLBowlerData[] iplCSVData = new Gson().fromJson(sortedData, IPLBowlerData[].class);
            Assert.assertEquals("Suresh Raina", iplCSVData[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLMostWicketCSVFile_TopStrikingRatesOfBowler_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        String sortedData = null;
        try {
            iplAnalyser.loadIplData(Player.BOWLER, IPL_MOST_WICKETS_CSV_FILE_PATH);
            sortedData = iplAnalyser.getSortedPlayerData(SortedDataBaseOnField.STRIKING_RATE_BOWLER);
            IPLBowlerData[] iplCSVData = new Gson().fromJson(sortedData, IPLBowlerData[].class);
            Assert.assertEquals("Suresh Raina", iplCSVData[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLMosWicketCSVFile_TopEconomyOfBowler_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        String sortedData = null;
        try {
            iplAnalyser.loadIplData(Player.BOWLER, IPL_MOST_WICKETS_CSV_FILE_PATH);
            sortedData = iplAnalyser.getSortedPlayerData(SortedDataBaseOnField.ECONOMY);
            IPLBowlerData[] iplCSVData = new Gson().fromJson(sortedData, IPLBowlerData[].class);
            Assert.assertEquals("Shivam Dube", iplCSVData[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLMostWicketCSVFile_TopStrikingRatesOfBowlerWithMaximum5wAnd4w_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        String sortedData = null;
        try {
            iplAnalyser.loadIplData(Player.BOWLER, IPL_MOST_WICKETS_CSV_FILE_PATH);
            sortedData = iplAnalyser.getSortedPlayerData(SortedDataBaseOnField.BY_4w_AND_5w);
            IPLBowlerData[] iplCSVData = new Gson().fromJson(sortedData, IPLBowlerData[].class);
            Assert.assertEquals("Kagiso Rabada", iplCSVData[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }

    @Test
    public void givenIPLMostWicketCSVFile_WhoHadGreatAverageWithBEstStrikingRates_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        String sortedData = null;
        try {
            iplAnalyser.loadIplData(Player.BOWLER, IPL_MOST_WICKETS_CSV_FILE_PATH);
            sortedData = iplAnalyser.getSortedPlayerData(SortedDataBaseOnField.AVERAGE_WITH_BEST_STRIKING_RATE_BOWLER);
            IPLBowlerData[] iplCSVData = new Gson().fromJson(sortedData, IPLBowlerData[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplCSVData[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }

    @Test
    public void givenIPLMostWicketCSVFile_WhoHitMaximumRunsWithBestAverage_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        String sortedData = null;
        try {
            iplAnalyser.loadIplData(Player.BOWLER, IPL_MOST_WICKETS_CSV_FILE_PATH);
            sortedData = iplAnalyser.getSortedPlayerData(SortedDataBaseOnField.MAXIMUM_WICKET_WITH_AVERAGE);
            IPLBowlerData[] iplCSVData = new Gson().fromJson(sortedData, IPLBowlerData[].class);
            Assert.assertEquals("Imran Tahir", iplCSVData[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }

    @Test
    public void givenIPLCSV_WhoHadBestBowlingAndBatingAverage_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        try {
            iplAnalyser.loadIplData(Player.All_ROUNDER, IPL_MOST_RUN_CSV_FILE_PATH, IPL_MOST_WICKETS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortedPlayerData(SortedDataBaseOnField.BEST_BOWLING_BATTING_AVERAGE);
            IPLAnalyserDAO[] iplCSVData = new Gson().fromJson(sortedData, IPLAnalyserDAO[].class);
            Assert.assertEquals("MS Dhoni", iplCSVData[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }

    @Test
    public void givenIPLCSV_WhoAreTheBestAllRounder_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        try {
            iplAnalyser.loadIplData(Player.All_ROUNDER, IPL_MOST_RUN_CSV_FILE_PATH, IPL_MOST_WICKETS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortedPlayerData(SortedDataBaseOnField.BEST_ALL_ROUNDER);
            IPLAnalyserDAO[] iplCSVData = new Gson().fromJson(sortedData, IPLAnalyserDAO[].class);
            Assert.assertEquals("Marcus Stoinis", iplCSVData[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }
}
