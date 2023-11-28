package business;

public class UserReviewData {
    private int userID;
    private int reviewID;
    private int rating;
    private String comment;
    private String movieTitle;

    public UserReviewData() {
    }

    public UserReviewData(int reviewID, String movieTitle, int rating, String comment) {
        this.reviewID = reviewID;
        this.rating = rating;
        this.comment = comment;
        this.movieTitle = movieTitle;
    }

    public UserReviewData(int userID, int reviewID, int rating, String comment, String movieTitle) {
        this.userID = userID;
        this.reviewID = reviewID;
        this.rating = rating;
        this.comment = comment;
        this.movieTitle = movieTitle;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
    
    
    
}
