package com.jaeuk.baseball;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BaseballGameController {

    private final BaseballService baseballService;
    private final List<GuessResult> history;

    public BaseballGameController(BaseballService baseballService) {
        this.baseballService = baseballService;
        this.history = new ArrayList<>();
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("history", history);
        if (!history.isEmpty()) {
            GuessResult lastGuess = history.get(history.size() - 1);
            model.addAttribute("win", lastGuess.result.contains("승리"));
        }
        return "index";
    }

    @PostMapping("/guess")
    public String guess(@RequestParam String guess, Model model) {
        String result = baseballService.guess(guess);
        history.add(new GuessResult(guess, result));
        return "redirect:/";
    }

    @PostMapping("/reset")
    public String reset() {
        baseballService.init();
        history.clear();
        return "redirect:/";
    }

    class GuessResult {
        String guess;
        String result;

        public GuessResult(String guess, String result) {
            this.guess = guess;
            this.result = result;
        }

        public String getGuess() {
            return guess;
        }

        public String getResult() {
            return result;
        }
    }
}
