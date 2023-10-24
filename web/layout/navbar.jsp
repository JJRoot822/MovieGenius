<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<a href="#main-content" id="stmc">Skip to Main Content</a>

<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="index.jsp">Movie Genius</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#movie-genius-navbar" aria-controls="movie-genius-navbar" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
      
        <div class="collapse navbar-collapse" id="movie-genius-navbar">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item text-dark">
                    <a href="index.jsp" class="nav-link">Home</a>
                </li>
                <li class="nav-item">
                    <a href="movies.jsp" class="nav-link">Movies</a>
                </li>
                
                <c:if test="${not empty loggedInUser || loggedInUser != null}">
                    <li class="nav-item">
                        <form action="Private" method="post">
                            <input type="hidden" value="logout" name="action" />
                            <button role="link" type="submit" class="nav-link text-dark">Log Out</button>
                        </form>
                    </li>
                </c:if>

                <c:if test="${empty sessionScope.loggedInUser}">
                    <li class="nav-item">
                        <a href="login.jsp" class="nav-link text-dark">Log In</a>
                    </li>

                    <li class="nav-item">
                        <a href="register.jsp" class="nav-link text-dark">Register</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </nav>
</header>