document.addEventListener('DOMContentLoaded', function () {
    let alertElements = document.querySelectorAll('.alert');
  
    alertElements.forEach(function (alert) {
        alert.addEventListener('closed.bs.alert', function () {
            let movieSearchField = document.getElementById("movie-search-field");

            movieSearchField.focus();
        });
    });
});
  