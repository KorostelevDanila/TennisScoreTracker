package edu.korostelev.TennisScoreTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages= "edu/korostelev/TennisScoreTracker/model")
public class TennisScoreTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TennisScoreTrackerApplication.class, args);
	}

}
