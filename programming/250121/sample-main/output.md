너는 반응형 디자인과 링크 미리보기와 사용성을 중시하는 프론트엔드 개발자야. html, css, bootstrap을 바탕으로 (추가적인 js와 인라인 스타일은 없이), flex와 container를 사용하여 모바일에서도 사용이 가능한 카드/모달/캐러셀/드롭다운 컴포넌트를 포함한, #FFDFDE와 #6A7BA2를 컨셉 색깔로 하는, 고양이 홍보 페이지를 만들어줘. index.html에 style.css 없이 해주고, og태그와 favicon을 만들 예정이므로 주석으로 해당 위치를 추가해줘, webfont도 추가 예정이므로 해당 위치를 주석으로 표시해줘

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Cat Adoption Page</title>

    <!-- Favicon 위치 -->
    <!-- OG 태그 위치 -->
    <!-- Webfont 추가 위치 -->

    <!-- Bootstrap CSS -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />

    <style>
      body {
        background-color: #ffdfde;
        color: #6a7ba2;
        font-family: Arial, sans-serif;
      }

      .navbar, .dropdown-menu {
        background-color: #6a7ba2;
      }

      .navbar-brand, .nav-link, .dropdown-item {
        color: #ffdfde !important;
      }

      .btn-custom {
        background-color: #6a7ba2;
        color: #ffdfde;
        border: none;
      }

      .btn-custom:hover {
        background-color: #4f5e85;
      }

      .carousel-indicators button {
        background-color: #6a7ba2;
      }

      .card {
        border: none;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
      }
    </style>

  </head>
  <body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark">
      <div class="container">
        <a class="navbar-brand" href="#">Cat Haven</a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav ms-auto">
            <li class="nav-item">
              <a class="nav-link" href="#">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#adopt">Adopt</a>
            </li>
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                id="navbarDropdown"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                More
              </a>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">About Us</a></li>
                <li><a class="dropdown-item" href="#">Contact</a></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Hero Section -->
    <header class="text-center py-5">
      <h1>Welcome to Cat Haven</h1>
      <p class="lead">Your journey to finding the purrfect companion starts here!</p>
      <a href="#adopt" class="btn btn-custom btn-lg">Find Your Cat</a>
    </header>

    <!-- Carousel -->
    <div id="catCarousel" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-indicators">
        <button
          type="button"
          data-bs-target="#catCarousel"
          data-bs-slide-to="0"
          class="active"
          aria-current="true"
          aria-label="Slide 1"
        ></button>
        <button
          type="button"
          data-bs-target="#catCarousel"
          data-bs-slide-to="1"
          aria-label="Slide 2"
        ></button>
        <button
          type="button"
          data-bs-target="#catCarousel"
          data-bs-slide-to="2"
          aria-label="Slide 3"
        ></button>
      </div>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img
            src="https://placekitten.com/1200/400"
            class="d-block w-100"
            alt="Cute Cat 1"
          />
        </div>
        <div class="carousel-item">
          <img
            src="https://placekitten.com/1201/400"
            class="d-block w-100"
            alt="Cute Cat 2"
          />
        </div>
        <div class="carousel-item">
          <img
            src="https://placekitten.com/1202/400"
            class="d-block w-100"
            alt="Cute Cat 3"
          />
        </div>
      </div>
      <button
        class="carousel-control-prev"
        type="button"
        data-bs-target="#catCarousel"
        data-bs-slide="prev"
      >
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button
        class="carousel-control-next"
        type="button"
        data-bs-target="#catCarousel"
        data-bs-slide="next"
      >
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>

    <!-- Cards -->
    <section id="adopt" class="container my-5">
      <div class="row g-4">
        <div class="col-md-4">
          <div class="card">
            <img
              src="https://placekitten.com/300/200"
              class="card-img-top"
              alt="Fluffy"
            />
            <div class="card-body">
              <h5 class="card-title">Fluffy</h5>
              <p class="card-text">Fluffy loves cuddles and naps.</p>
              <button class="btn btn-custom" data-bs-toggle="modal" data-bs-target="#adoptModal">Adopt Me</button>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card">
            <img
              src="https://placekitten.com/301/200"
              class="card-img-top"
              alt="Mittens"
            />
            <div class="card-body">
              <h5 class="card-title">Mittens</h5>
              <p class="card-text">Mittens is playful and curious.</p>
              <button class="btn btn-custom" data-bs-toggle="modal" data-bs-target="#adoptModal">Adopt Me</button>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card">
            <img
              src="https://placekitten.com/302/200"
              class="card-img-top"
              alt="Whiskers"
            />
            <div class="card-body">
              <h5 class="card-title">Whiskers</h5>
              <p class="card-text">Whiskers is full of energy and loves to play.</p>
              <button class="btn btn-custom" data-bs-toggle="modal" data-bs-target="#adoptModal">Adopt Me</button>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Modal -->
    <div
      class="modal fade"
      id="adoptModal"
      tabindex="-1"
      aria-labelledby="adoptModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="adoptModalLabel">Adopt a Cat</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">Thank you for considering adopting a cat!</div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              Close
            </button>
            <button type="button" class="btn btn-custom">Proceed</button>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>

  </body>
</html>
