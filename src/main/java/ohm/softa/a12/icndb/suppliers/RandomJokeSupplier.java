package ohm.softa.a12.icndb.suppliers;

import ohm.softa.a12.icndb.ICNDBApi;
import ohm.softa.a12.icndb.ICNDBService;
import ohm.softa.a12.model.JokeDto;
import ohm.softa.a12.model.ResponseWrapper;
import org.apache.commons.lang3.NotImplementedException;

import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * @author Peter Kurfer
 */

public final class RandomJokeSupplier implements Supplier<ResponseWrapper<JokeDto>> {

    /* ICNDB API proxy to retrieve jokes */
    private final ICNDBApi icndbApi;

    public RandomJokeSupplier() {
        icndbApi = ICNDBService.getInstance();
    }

    public ResponseWrapper<JokeDto> get() {
        /* TODO fetch a random joke synchronously
         * if an exception occurs return null */

		ResponseWrapper<JokeDto> joke;

		try {
			joke = icndbApi.getRandomJoke().get();
		} catch (InterruptedException | ExecutionException e) {
			joke = null;
		}
		return joke;
    }
}
