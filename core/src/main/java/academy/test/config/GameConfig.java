package academy.test.config;

import academy.test.GuessCount;
import academy.test.MaxNumber;
import academy.test.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "academy.test")
@PropertySource("classpath:config/game.properties")
public class GameConfig {

    //Fields
    @Value("${game.maxNumber}")
    private int maxNumber;
    @Value("${game.guessCount}")
    private int guessCount;
    @Value("${game.minNumber}")
    private int minNumber;

    //Bean Methods
    @Bean
    @MaxNumber
    public int maxNumber(){
        return maxNumber;
    }
    @Bean
    @GuessCount
    public int guessCount(){
        return guessCount;
    }
    @Bean
    @MinNumber
    public int minNumber(){return minNumber;}
}
