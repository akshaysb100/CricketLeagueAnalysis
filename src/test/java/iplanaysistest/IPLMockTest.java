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
    Map<String, IPLAnalyserDAO> playerList;

    @Mock
    IPLAdapter iplAdapter;

    @Before
    public void setUp() throws Exception {
        this.playerList = new HashMap<String, IPLAnalyserDAO>();
        this.playerList.put("iho", new IPLAnalyserDAO("Akshay", 250, 68.2, 153, 8, 10));
        this.playerList.put("iho", new IPLAnalyserDAO("Akshay B", 365, 93.56, 150, 4, 12));
        this.playerList.put("iho", new IPLAnalyserDAO("Laxman C", 465, 69.22, 142, 9, 11));
        this.playerList.put("iho", new IPLAnalyserDAO("Mangesh", 345, 67.92, 132, 10, 15));
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenSampleData_ShouldReturnCount() {

        try {
            Map<String,IPLAnalyserDAO> iplAnalyserDAOMap = new HashMap<>();
            IPLAnalyserDAO iplAnalyserDAO = new IPLAnalyserDAO();

            iplAnalyserDAOMap.put("virat kohli",iplAnalyserDAO);
            iplAnalyserDAOMap.put("Rohit",iplAnalyserDAO);

            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.setIPLAdapter(iplAdapter);
            when(iplAdapter.loadIplData(IPL_MOST_RUN_CSV_FILE_PATH)).thenReturn(iplAnalyserDAOMap);
            Assert.assertEquals(2,iplAnalyserDAOMap.size());
        } catch (IPLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenCricketLeagueData_MockitoTest() {
        try {
            IPLAdapter iplAdapter = mock(IPLBatsmanAdapter.class);
            IPLAnalyser  mostWickets = new IPLAnalyser();
            int i = mostWickets.loadIplData(Player.BATSMAN,IPL_MOST_RUN_CSV_FILE_PATH);
            when(iplAdapter.loadIplData(IPL_MOST_RUN_CSV_FILE_PATH)).thenReturn(playerList);
            Assert.assertEquals(100 , i);
        } catch (IPLException e) {
            e.printStackTrace();
        }
    }


}
