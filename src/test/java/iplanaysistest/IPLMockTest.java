package iplanaysistest;

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
    public Map<String, IPLAnalyserDAO> playerList;

    @Mock
    IPLAdapter iplAdapter;

    @Before
    public void setUp() throws Exception {
        this.iplAdapter = mock(IPLAdapter.class);
        this.playerList = new HashMap<String, IPLAnalyserDAO>();
        this.playerList.put("Aksahy", new IPLAnalyserDAO("Akshay", 250, 68.2, 153, 8, 10));
        this.playerList.put("iho", new IPLAnalyserDAO("Pravin", 250, 68.2, 153, 8, 10));
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
            when(iplAdapter.loadIplData(IPL_MOST_RUN_CSV_FILE_PATH)).thenReturn(playerList);
            int i = iplAnalyser.loadIplData(Player.BATSMAN, IPL_MOST_RUN_CSV_FILE_PATH);
            Assert.assertEquals(2, i);
        } catch (IPLException e) {
            e.printStackTrace();
        }
    }
}
