package moviestore;

import java.util.ArrayList;
import java.util.List;

public class MovieStore {

    List<Movie> movies = new ArrayList<>();

    public List<Movie> findByPartialMatchTitle(String title) {
        List<Movie> matched = new ArrayList<>();

        for(Movie m: movies){
          if(m.title().toUpperCase().contains(title.toUpperCase())){
            matched.add(m);
          }
        }

        return matched;
    }

    public void add(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> findByDirector(String dirctor) {
        List<Movie> matched = new ArrayList<>();

        for (Movie m :
                movies) {
            if (m.director().equals(dirctor) ){
                matched.add(m);
            }
            }
            return matched;
    }
}
