package academy.test;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
//For Constructor Injection

@Component
@Getter
public class NumberGeneratorImpl implements NumberGenerator {

   //Fields
    @Getter(AccessLevel.NONE)
    private final Random random = new Random();

    private final int maxNumber;


    private final int minNumber;

    //Constroctors

    @Autowired
    public NumberGeneratorImpl(@MaxNumber int maxNumber, @MinNumber int minNumber) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }

    //Public methods
    @Override
    public int next() {
        return ThreadLocalRandom.current().nextInt(minNumber,maxNumber+1);
    }


}
