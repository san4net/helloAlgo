package moviestore;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MovieStoreTest {
    // option + command + F  i.e initialize window to move

    private final Movie sholay = new Movie("Sholay", "Ramesh Sippy");
    private final Movie sita = new Movie("Sita Aur Gita", "xyz");
    private final Movie shimba = new Movie("Shimba", "Ramesh Sippy");
    private final MovieStore movieStore = new MovieStore();

    @Before
    public void setUp() throws Exception {
        movieStore.add(sholay);
        movieStore.add(sita);
        movieStore.add(shimba);
    }

    @Test
    public void returnsNoResultsWhenNoTitlesPartiallyMatchSearch(){
        MovieStore movieStore=  new MovieStore();
        List<Movie> movies = movieStore.findByPartialMatchTitle("abc");
        assertThat(movies.size(), is(0));
    }

    @Test
    public void findResultsWhenTitlesPartiallyMatchSearchCaseInsensitive(){

        List<Movie> movies = movieStore.findByPartialMatchTitle("Sh");

        assertThat(movies.size(), is(2));
        assertThat(movies, containsInAnyOrder(sholay, shimba));
    }

    @Test
    public void findMovieWhereDirectorAreExactlyMatched(){
        MovieStore movieStore = new MovieStore();
        movieStore.add(new Movie("Sholay", "Ramesh Sippy"));
        movieStore.add(new Movie("Sita Aur Gita",  "SRK"));
        movieStore.add(new Movie("Shimba", "Ramesh Sippy"));

        List<Movie> movies = movieStore.findByDirector("SRK");

        assertThat(movies.size(), is(1));
    }


}