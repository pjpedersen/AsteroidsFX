package dk.sdu.mmmi.cbse.scoresystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ScoringSystemSpring {

    private long scoreCounter = 0;

    public static void main(String[] args) {
        SpringApplication.run(ScoringSystemSpring.class, args);
    }

    @GetMapping("/score")
    public long getScore() {
        return scoreCounter;
    }

    @PutMapping("/score/increment")
    public void incrementScore() {
        scoreCounter++;
    }

    @PutMapping("/score/add/{value}")
    public void addScore(long value) {
        if(value > 0) {
            scoreCounter += value;
        }
    }

    @PutMapping("/score/reset")
    public void resetScore() {
        scoreCounter = 0;
    }
}
