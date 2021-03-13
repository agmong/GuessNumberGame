package academy.test;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator {

    //Logger

    //Fields

    private final Game game;

    //Constructior

    @Autowired
    public MessageGeneratorImpl(Game game) {
        this.game = game;
    }


    //Methods

    @Override
    public String getMainMethod() {
        return "Number is between "+ game.getSmallest() +" and "+ game.getBiggest() + ". Can you guess it?";
    }

    @Override
    public String getResultMethod() {
        if(game.isGameWon()){
            return "You got it right the number was "+ game.getNumber();
        }else if(game.isGameLost()){
            return "You lost the number was "+ game.getNumber();
        } else if(!game.isValidNumberRange()){
            return "Invalid number Range";
        }else if (game.getRemainingGuesses()== game.getGuessCount()){
            return "What is ur first guess?";
        }   else {
            String direction = "Lower";
            if (game.getGuess() <game.getNumber()){
                direction="higher";
            }
            return direction + "! You have "+ game.getRemainingGuesses() + " guesses left";
        }
    }
    @PostConstruct
    public void getLog(){
        log.info("The guess count is: "+game.getGuessCount());
    }
}
