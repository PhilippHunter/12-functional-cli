package ohm.softa.a12.icndb.suppliers;

import ohm.softa.a12.icndb.ICNDBApi;
import ohm.softa.a12.icndb.ICNDBService;
import ohm.softa.a12.model.JokeDto;
import ohm.softa.a12.model.ResponseWrapper;
import org.apache.commons.lang3.NotImplementedException;

import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * Supplier implementation to retrieve all jokes of the ICNDB in a linear way
 * @author Peter Kurfer
 */

public final class AllJokesSupplier implements Supplier<ResponseWrapper<JokeDto>> {

    /* ICNDB API proxy to retrieve jokes */
    private final ICNDBApi icndbApi;
    private int numberOfJokes;
    private int currentJoke;

    public AllJokesSupplier() {
        icndbApi = ICNDBService.getInstance();
        /* TODO fetch the total count of jokes the API is aware of
         * to determine when all jokes are iterated and the counters have to be reset */
        currentJoke = 0;

        try {
        	numberOfJokes = icndbApi.getJokeCount().get().getValue();
		} catch (ExecutionException | InterruptedException e) {
        	numberOfJokes = 0;
		}
    }

    public ResponseWrapper<JokeDto> get() {
        /* TODO retrieve the next joke
         * note that there might be IDs that are not present in the database
         * you have to catch an exception and continue if no joke was retrieved to an ID
         * if you retrieved all jokes (count how many jokes you successfully fetched from the API)
         * reset the counters and continue at the beginning */

        if (numberOfJokes == 0) return null;

		ResponseWrapper<JokeDto> joke;

		do {
			try {
				joke = icndbApi.getJoke(currentJoke++ % numberOfJokes).get();
			} catch (InterruptedException | ExecutionException e) {
				joke = null;
			}
		} while (joke == null);

		return joke;
    }

}
