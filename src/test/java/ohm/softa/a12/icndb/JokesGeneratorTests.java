package ohm.softa.a12.icndb;

import ohm.softa.a12.model.JokeDto;
import ohm.softa.a12.model.ResponseWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTimeout;

/**
 * @author Peter Kurfer
 * Created on 12/28/17.
 */
class JokesGeneratorTests {

	private static final Logger logger = LogManager.getLogger();
	private JokeGenerator jokeGenerator = new JokeGenerator();

	@Test
	void testRandomStream() {
		/* timeout to ensure that stream does not loop forever */
		/* TODO implement a test for the random joke stream */
		assertTimeout(Duration.ofSeconds(5L), () -> jokeGenerator.randomJokesStream()
			.filter(Objects::nonNull)
			.skip(3)
			.limit(5)
			.map(ResponseWrapper::getValue)
			.map(JokeDto::getJoke)
			.forEach(Assertions::assertNotNull)
		);
	}


	@Test
	void testJokesStream() {
		/* TODO implement a test for the linear jokes generator */
		assertTimeout(Duration.ofMinutes(1L), () -> jokeGenerator.jokesStream()
			.filter(Objects::nonNull)
			.map(ResponseWrapper::getValue)
			.skip(200L)
			.limit(400L)
			.map(JokeDto::getJoke)
			.forEach(Assertions::assertNotNull)
		);

	}

}
