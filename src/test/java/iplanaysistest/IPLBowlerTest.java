package iplanaysistest;

import com.google.gson.Gson;
import cricketleagueanalysis.*;
import org.junit.Assert;
import org.junit.Test;

public class IPLBowlerTest {
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
            sortedData = iplAnalyser.getTopAverageBattingPlayerName(SortedDataBaseOnBatsmanField.AVERAGE_BOWLER);
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
            sortedData = iplAnalyser.getTopAverageBattingPlayerName(SortedDataBaseOnBatsmanField.AVERAGE_BOWLER);
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
            sortedData = iplAnalyser.getTopAverageBattingPlayerName(SortedDataBaseOnBatsmanField.STRIKING_RATE_BOWLER);
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
            sortedData = iplAnalyser.getTopAverageBattingPlayerName(SortedDataBaseOnBatsmanField.ECONOMY);
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
            sortedData = iplAnalyser.getTopAverageBattingPlayerName(SortedDataBaseOnBatsmanField.BY_4w_AND_5w);
            IPLBowlerData[] iplCSVData = new Gson().fromJson(sortedData, IPLBowlerData[].class);
            Assert.assertEquals("Kagiso Rabada", iplCSVData[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.WRONG_FILE_PATH, e.type);
        }
    }
}
