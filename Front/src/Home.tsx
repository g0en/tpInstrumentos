import "./Home.css";
import MenuOpciones from "../src/componentes/MenuOpciones";

function Home() {
  return (
    <div className="container">
      <MenuOpciones />

      <h1 className="main-heading">TIENDA INSTRUMENTOS</h1>

      <div id="carouselExample" className="carousel slide">
        <div className="carousel-inner">
          <div className="carousel-item active">
            <img src={"/images/piano.jpg"} className="carousel-image" alt="Piano" />
          </div>
          <div className="carousel-item">
            <img src={"/images/guitarra.jpg"} className="carousel-image" alt="Guitarra" />
          </div>
          <div className="carousel-item">
            <img src={"/images/instrumentos3.jpg"} className="carousel-image" alt="Instrumentos" />
          </div>
        </div>
        <button className="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
          <span className="carousel-control-prev-icon" aria-hidden="true"></span>
          <span className="visually-hidden">Previous</span>
        </button>
        <button className="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
          <span className="carousel-control-next-icon" aria-hidden="true"></span>
          <span className="visually-hidden">Next</span>
        </button>
      </div>

      <div className="content">
        <p>
          <strong>Tienda de Instrumentos.</strong> Los mejores precios de toda la Zona Este! Venite a San Martin que te esperamos con los instrumentos de mejor calidad del mercado!
        </p>
      </div>
    </div>
  );
}

export default Home;
