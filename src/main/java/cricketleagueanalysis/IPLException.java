package cricketleagueanalysis;

public class IPLException extends Exception {

    public enum ExceptionType {
        CENSUS_FILE_PROBLEM, WRONG_FILE_PATH
    }

    public ExceptionType type;

    public IPLException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
