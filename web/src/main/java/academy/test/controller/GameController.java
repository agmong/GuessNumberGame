package academy.test.controller;

import academy.test.GameService;
import academy.test.util.AttributeNames;
import academy.test.util.GameMappings;
import academy.test.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class GameController {

    //fields
    private final GameService gameService;
    //constructor
    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // == request menthods
    @GetMapping(GameMappings.PLAY)
    public String play(Model model){
        model.addAttribute(AttributeNames.MAIN_MESSAGE,gameService.getMainMessage());
        model.addAttribute(AttributeNames.RESULT_MESSAGE, gameService.getResultMessage());
        log.info("model={}",model);

        if(gameService.isGameOVer()){
            return GameMappings.REDIRECT_GAMEOVER;
        }
        return ViewNames.PLAY;
    }
    @PostMapping(GameMappings.PLAY)
    //Request param int name must match name in html form
    public String processMessage(@RequestParam int guess){
        log.info("guess ={}",guess);
        gameService.checkGuess(guess);
        return GameMappings.REDIRECT_PLAY;
    }
    @GetMapping(GameMappings.GAME_OVER)
    public String gameover(Model model){
        model.addAttribute(AttributeNames.RESULT_MESSAGE,gameService.getResultMessage());
        return ViewNames.GAME_OVER;
    }
    @GetMapping(GameMappings.RESTART)
    public String restart(Model model){
        gameService.reset();
        return GameMappings.REDIRECT_PLAY;
    }

}
