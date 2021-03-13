package academy.test.console;

import academy.test.Game;
import academy.test.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
public class ConsoleNumberGuess  {

    //Constants




    //Fields

    private final Game game;


    private final MessageGenerator messageGenerator;

    //Constrctor


    public ConsoleNumberGuess(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }


    //Events

    //No paramater allows for us to listen to multiple events
    @EventListener(ContextRefreshedEvent.class)
    public void start() {
        log.info("Start() Container Ready for use.");

        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println(messageGenerator.getMainMethod());
            System.out.println(messageGenerator.getResultMethod());
            int guess= scanner.nextInt();
            game.setGuess(guess);
            game.check();

            if(game.isGameWon() || game.isGameLost()){
                System.out.println(messageGenerator.getResultMethod());
                System.out.println("Play again? y/n");
                String playAgain = scanner.next();
                if(!playAgain.equalsIgnoreCase("y")){
                    break;
                }
                game.reset();
            }
        }
    }
}
