package academy.test;

public interface GameService {
    boolean isGameOVer();
    String getMainMessage();
    String getResultMessage();
    void checkGuess(int guess);
    void reset();

}
