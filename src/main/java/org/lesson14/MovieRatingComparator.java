package org.lesson14;

import java.util.Comparator;


public class MovieRatingComparator implements Comparator<Movie> {

    @Override
    public int compare(Movie movie1, Movie movie2) {
            return Double.compare(movie1.getRating(), movie2.getRating());
    }
}
