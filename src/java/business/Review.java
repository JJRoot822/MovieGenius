package business;

public class Review {
    private int reviewID, rating, userID, movieID;
    private String comment;

    public Review() {
    }

    public Review(int rating, int userID, int movieID, String comment) {
        this.rating = rating;
        this.userID = userID;
        this.movieID = movieID;
        this.comment = comment;
    }
    
    public Review(int reviewID, int rating, int userID, int movieID, String comment) {
        this.reviewID = reviewID;
        this.rating = rating;
        this.userID = userID;
        this.movieID = movieID;
        this.comment = comment;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    
    
    
}
