package cricketleagueanalysis;

public class IPLObjectFactory {
    public static IPLAdapter getIPLDataObject(Player fileEnum) {
        if (fileEnum.equals(Player.BATSMAN)) {
            return new IPLBatsmanAdapter();
        } else return new IPLBowlerAdapter();
    }
}
