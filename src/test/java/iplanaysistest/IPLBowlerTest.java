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
            IPLBowlerData[] censusCSV = new Gson().fromJson(sortedData, IPLBowlerData[].class);
            Assert.assertEquals("Suresh Raina", censusCSV[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
}
