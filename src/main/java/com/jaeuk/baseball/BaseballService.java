package com.jaeuk.baseball;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class BaseballService {

    private List<Integer> computer;

    @PostConstruct
    public void init(){
        computer = new ArrayList<>();
        for (int i =1;i<=9;i++){
            computer.add(i);
        }
        Collections.shuffle(computer);
        computer = computer.subList(0,3);
    }

    public String guess(String guess) {
        int strike = 0;
        int ball = 0;

        for (int i = 0; i < 3; i++) {
            int inputNum = guess.charAt(i) - '0';
            if (inputNum == computer.get(i)) {
                strike++;
            } else if (computer.contains(inputNum)) {
                ball++;
            }
        }
        if (strike == 3){
            return "3 스트라이크! 게임에서 승리하셨습니다.!!";
        }else if (strike == 0 && ball ==0) {
            return "낫싱";
        }else{
            return strike + "스트라이크" + ball + "볼";
        }

    }
}
