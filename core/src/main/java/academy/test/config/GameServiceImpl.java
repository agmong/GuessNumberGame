package academy.test.config;

import academy.test.Game;
import academy.test.GameService;
import academy.test.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class GameServiceImpl implements GameService {
    //Fields
    private final Game game;

    private final MessageGenerator messageGenerator;

    //Constructor
    @Autowired
    public GameServiceImpl(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    //init method
    @PostConstruct
    public void init(){
    log.info("number = {}",game.getNumber());
    log.info("main message= {}",messageGenerator.getMainMethod());
    }

    @Override
    public boolean isGameOVer() {
        return game.isGameLost() || game.isGameWon();
    }

    @Override
    public String getMainMessage() {
        return messageGenerator.getMainMethod();
    }

    @Override
    public String getResultMessage() {
        return messageGenerator.getResultMethod();
    }

    @Override
    public void checkGuess(int guess) {
        game.setGuess(guess);
        game.check();
    }

    @Override
    public void reset() {
        game.reset();
    }
}
