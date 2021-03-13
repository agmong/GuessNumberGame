package academy.test;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
@Slf4j
@Getter
@Component
public class GameImpl implements Game {
   //Constants



    //Fields

    //Prevents it from making a getter for numbergenerator
    @Getter(AccessLevel.NONE)
     private final NumberGenerator numberGenerator;

     private final int guessCount;
     private int number;

     @Setter
     private int guess;

     private int smallest;
     private int biggest;
     private int remainingGuesses;
     private boolean validNumberRange=true;

     //Constructor for constructor injection

    @Autowired
    public GameImpl(NumberGenerator numberGenerator, @GuessCount int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }


// init method

    @PostConstruct
    @Override
    public void reset() {
        smallest=numberGenerator.getMinNumber();
        guess=numberGenerator.getMinNumber();
        remainingGuesses=guessCount;
        biggest=numberGenerator.getMaxNumber();
        number = numberGenerator.next();

        log.debug("the number is {}",number);
    }

    @PreDestroy
    public void preDestroy(){
        log.info("in game preDestroy");
    }


    // Public Methods
    /*This is for setter based Dependency Injection
    public void setNumberGenerator(NumberGenerator num){
        this.numberGenerator=num;
    }
    */

    @Override
    public void check() {
        checkNumberRange();
        if(validNumberRange){
            if(guess> number){
                biggest=guess-1;
            }
            if(guess<number){
                smallest=guess+1;
            }
        }
        remainingGuesses--;

    }

    @Override
    public boolean isGameWon() {
        return guess==number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses<=0;
    }

    //private methods

    private void checkNumberRange(){
        validNumberRange=(guess>= smallest) && (guess<= biggest);
    }

}
