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
                    <a href="index.jsp" class="nav-link text-dark">Home</a>
                </li>
                <c:if test="${loggedInUser.userType eq 'admin'}">
                    <!-- Begin Admin Dropdown -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Admin
                        </a>
                        <ul class="dropdown-menu">
                            <li class="dropdown-item">
                                <a href="Private?action=gotoAdminMovie" class="nav-link text-dark">Admin Movies</a>
                            </li>
                            <li class="dropdown-item">
                                <a href="Private?action=adminUserAction" class="nav-link text-dark">Admin Users</a>
                            </li>
                        </ul>
                    </li>
                    <!-- End Admin Dropdown -->

                </c:if>
                <c:if test="${not empty loggedInUser || loggedInUser != null}">
                    <li class="nav-item">
                        <a href="Private?action=movieList" class="nav-link text-dark">Movies</a>
                    </li>
                    <li class="nav-item">
                        <a href="Private?action=gotoMovieFilter" class="nav-link text-dark">Movie Filter</a>
                    </li>
                    <li class="nav-item">
                        <a href="Private?action=gotoUserPage" class="nav-link text-dark">User Page</a>
                    </li>
                    
                    <li class="nav-item">
                        <a href="Private?action=top10movies" class="nav-link text-dark">Top 10 Movies</a>
                    </li>
                    <li class="nav-item">
                        <a href="Private?action=newReleases" class="nav-link text-dark">New Releases</a>
                    </li>
                    <form action="Private" method="post">
                        <input type="hidden" value="logout" name="action" />
                        <button role="link" type="submit" class="nav-link text-dark">Log Out</button>
                    </form>
                    </li>
                </c:if>
                <c:if test="${empty sessionScope.loggedInUser}">
                    <li class="nav-item">
                        <a href="register.jsp" class="nav-link text-dark">Register</a>
                    </li>
                    <li class="nav-item">
                        <a href="login.jsp" class="nav-link text-dark">Log In</a>
                    </li>

                </c:if>
            </ul>
        </div>
    </nav>
</header>