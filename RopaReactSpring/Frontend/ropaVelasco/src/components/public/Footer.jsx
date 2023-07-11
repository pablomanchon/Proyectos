export const Footer = () => {
  return (
    <>
      <footer>
        <div>
          <a href="/" className="text-decoration-none lh-1">
            <i className="bi bi-bootstrap-fill"></i>
          </a>
          <span className="mb-3 mb-md-0">© 2023 Velasco SA</span>
        </div>

        <ul>
          <li className="ms-3">
            <a href="#">
              <i className="bi bi-twitter"></i>
            </a>
          </li>
          <li className="ms-3">
            <a href="#">
              <i className="bi bi-whatsapp"></i>
            </a>
          </li>
          <li className="ms-3">
            <a href="#">
              <i className="bi bi-instagram"></i>
            </a>
          </li>
          <li className="ms-3">
            <a href="#">
              <i className="bi bi-facebook"></i>
            </a>
          </li>
        </ul>
      </footer>
    </>
  );
};
