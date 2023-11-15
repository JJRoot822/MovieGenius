package business;

public class MovieReviewVM {
    private String movieTitle;
    private int reviewId;
    private int reviewRating;
    private String reviewComment;

    public MovieReviewVM() {
        this.movieTitle = "";
        this.reviewId = 1;
        this.reviewRating = 0;
        this.reviewComment = "";
    }

    public MovieReviewVM(String movieTitle, int reviewRating, int reviewId, String reviewComment) {
        this.movieTitle = movieTitle;
        this.reviewRating = reviewRating;
        this.reviewId = reviewId;
        this.reviewComment = reviewComment;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public String getReviewComment() {
        return reviewComment;
    }
}