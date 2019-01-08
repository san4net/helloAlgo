package moviestore;

import java.util.Objects;

public class Movie {

    private final String title;
    private final String director;

    public Movie(String title, String director) {
        this.title = title;
        this.director =director;
    }

    public String title() {
        return title;
    }

    public String director() {
        return director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) &&
                Objects.equals(director, movie.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, director);
    }

}
