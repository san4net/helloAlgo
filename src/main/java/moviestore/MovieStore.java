package moviestore;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MovieStore {

    List<Movie> movies = new ArrayList<>();

    /**
     *
     *
     * @param title
     * @return
     */

    public List<Movie> findByPartialMatchTitle(final String title) {
        return findBy(m->m.title().toUpperCase().contains(title.toUpperCase()));
    }

    public void add(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> findByDirector(String director) {
        return findBy(m -> m.director().equalsIgnoreCase(director));
    }

    private List<Movie> findBy(Predicate<Movie> moviePredicate) {
        List<Movie> matched = new ArrayList<>();

        for (Movie m :
                movies) {
            if (moviePredicate.test(m) ){
                matched.add(m);
            }
            }
        return matched;
    }


}
