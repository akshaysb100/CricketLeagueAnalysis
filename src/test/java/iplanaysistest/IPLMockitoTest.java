package iplanaysistest;

import com.google.gson.Gson;
import cricketleagueanalysis.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPLMockitoTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    private static final String IPL_MOST_RUN_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_MOST_WICKETS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/test/resources/WrongDelimiterIPL2019MostRun.csv";
    public Map<String, IPLAnalyserDAO> batsmanPlayerList;
    public Map<String, IPLAnalyserDAO> bowlerPlayerList;

    @Mock
    IPLAdapter iplAdapter;

    @Before
    public void setUp() throws Exception {
        this.iplAdapter = mock(IPLAdapter.class);
        this.batsmanPlayerList = new HashMap<String, IPLAnalyserDAO>();
        this.batsmanPlayerList.put("Aksahy1", new IPLAnalyserDAO("Akshay", 350, 68.2, 40, 6, 0));
        this.batsmanPlayerList.put("Akshay2", new IPLAnalyserDAO("Pravin", 450, 78.2, 30, 8, 1));
        this.bowlerPlayerList = new HashMap<String, IPLAnalyserDAO>();
        this.bowlerPlayerList.put("Aksahy3", new IPLAnalyserDAO("Kartik", 375, 66.2, 33, 5, 5));
        this.bowlerPlayerList.put("Akshay4", new IPLAnalyserDAO("Akshay", 250, 60.2, 16, 6, 10));
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenSampleData_ShouldReturnCount() {
        try {
            Map<String, IPLAnalyserDAO> iplAnalyserDAOMap = new HashMap<>();
            IPLAnalyserDAO iplAnalyserDAO = new IPLAnalyserDAO();
            iplAnalyserDAOMap.put("virat kohli", iplAnalyserDAO);
            iplAnalyserDAOMap.put("Rohit", iplAnalyserDAO);
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.setIPLAdapter(iplAdapter);
            when(iplAdapter.loadIplData(IPL_MOST_RUN_CSV_FILE_PATH)).thenReturn(iplAnalyserDAOMap);
            int iplData = iplAnalyser.loadIplData(Player.BATSMAN, IPL_MOST_RUN_CSV_FILE_PATH);
            Assert.assertEquals(2, iplData);
        } catch (IPLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCricketLeagueData_MockitoTest() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.setIPLAdapter(iplAdapter);
            when(iplAdapter.loadIplData(IPL_MOST_RUN_CSV_FILE_PATH)).thenReturn(batsmanPlayerList);
            int i = iplAnalyser.loadIplData(Player.BATSMAN, IPL_MOST_RUN_CSV_FILE_PATH);
            Assert.assertEquals(2, i);
        } catch (IPLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenBatsmanData_SortBasedOnBatsmanAverage() {
        try {
            IPLAnalyser cricketLeague = new IPLAnalyser();
            cricketLeague.setIPLAdapter(iplAdapter);
            when(iplAdapter.loadIplData(IPL_MOST_RUN_CSV_FILE_PATH)).thenReturn(batsmanPlayerList);
            cricketLeague.loadIplData(Player.BATSMAN, IPL_MOST_RUN_CSV_FILE_PATH);
            String sortedCricketLeagueData = cricketLeague.getSortedPlayerData(SortedDataBaseOnField.BATSMAN_AVERAGE);
            IPLBatsmanData[] leagueCSV = new Gson().fromJson(sortedCricketLeagueData, IPLBatsmanData[].class);
            Assert.assertEquals("Pravin", leagueCSV[0].playerName);
        } catch (IPLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenBatsmanData_SortBasedOnMaximumRuns() {
        try {
            IPLAnalyser cricketLeague = new IPLAnalyser();
            cricketLeague.setIPLAdapter(iplAdapter);
            when(iplAdapter.loadIplData(IPL_MOST_RUN_CSV_FILE_PATH)).thenReturn(batsmanPlayerList);
            cricketLeague.loadIplData(Player.BATSMAN, IPL_MOST_RUN_CSV_FILE_PATH);
            String sortedCricketLeagueData = cricketLeague.getSortedPlayerData(SortedDataBaseOnField.MAXIMUM_RUN_WITH_AVERAGE);
            IPLBatsmanData[] leagueCSV = new Gson().fromJson(sortedCricketLeagueData, IPLBatsmanData[].class);
            Assert.assertEquals("Pravin", leagueCSV[0].playerName);
        } catch (IPLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenBowlerData_SortBasedOnMaximumWicketsWithAverage() {
        try {
            IPLAnalyser cricketLeague = new IPLAnalyser();
            cricketLeague.setIPLAdapter(iplAdapter);
            when(iplAdapter.loadIplData(IPL_MOST_RUN_CSV_FILE_PATH)).thenReturn(batsmanPlayerList);
            cricketLeague.loadIplData(Player.BATSMAN, IPL_MOST_RUN_CSV_FILE_PATH);
            String sortedCricketLeagueData = cricketLeague.getSortedPlayerData(SortedDataBaseOnField.MAXIMUM_WICKET_WITH_AVERAGE);
            IPLBatsmanData[] leagueCSV = new Gson().fromJson(sortedCricketLeagueData, IPLBatsmanData[].class);
            Assert.assertEquals("Akshay", leagueCSV[0].playerName);
        } catch (IPLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLCSV_WhoHadBestBowlingAndBatingAverage_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.setIPLAdapter(new IPLAllRounderAdapter());
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
    public void givenIPLCSV_WhenPassWrongFile_ShouldThrowException() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.setIPLAdapter(new IPLAllRounderAdapter());
        try {
            iplAnalyser.loadIplData(Player.All_ROUNDER, IPL_MOST_RUN_CSV_FILE_PATH, WRONG_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortedPlayerData(SortedDataBaseOnField.BEST_ALL_ROUNDER);
            IPLAnalyserDAO[] iplCSVData = new Gson().fromJson(sortedData, IPLAnalyserDAO[].class);
            Assert.assertEquals("Marcus Stoinis", iplCSVData[0].playerName);
        } catch (IPLException e) {
            Assert.assertEquals(IPLException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
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
    public void givenIPLMostWicketCSVFile_WhoHitMaximumRunsWithBestAverage_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.setIPLAdapter(new IPLBowlerAdapter());
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
    public void givenIPLMostWicketCSVFile_TopStrikingRatesOfBowlerWithMaximum5wAnd4w_ShouldReturnPlayerName() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.setIPLAdapter(new IPLBowlerAdapter());
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
}
