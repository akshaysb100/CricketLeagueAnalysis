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

public class IPLMockTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    private static final String IPL_MOST_RUN_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
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
}
