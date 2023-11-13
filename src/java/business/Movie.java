/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.time.LocalDate;

/**
 *
 * @author tmdel
 */
public class Movie {
    private int movieID, genreID;
    private String title, summary;
    private LocalDate releaseDate;

    public Movie() {
    }

    public Movie(String title, String summary, LocalDate releaseDate, int genreID) {
        this.title = title;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.genreID = genreID;
    }

    
    
    public Movie(int movieID, String title, String summary, LocalDate releaseDate, int genreID) {
        this.movieID = movieID;
        this.title = title;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.genreID = genreID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }
    
}
