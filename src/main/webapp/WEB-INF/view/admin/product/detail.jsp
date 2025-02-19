<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib
uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
    <meta name="author" content="Hỏi Dân IT" />
    <title>View-Product - Hỏi Dân IT</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <!-- UPDATED: Thêm Bootstrap -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <script
      src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
      crossorigin="anonymous"
    ></script>
  </head>

  <body class="sb-nav-fixed">
    <jsp:include page="../layout/header.jsp" />
    <div id="layoutSidenav">
      <jsp:include page="../layout/sidebar.jsp" />
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">View Users</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
              <li class="breadcrumb-item active">
                <a href="/admin/product">Product</a>
              </li>
              <li class="breadcrumb-item active">Detail</li>
            </ol>
            <div class="mt-5">
              <div class="row">
                <div class="col-12 mx-auto">
                  <div class="d-flex justify-content-between">
                    <h3>Product detail with id = ${id}</h3>
                  </div>

                  <hr />

                  <!-- UPDATED: Responsive bố cục -->
                  <div
                    class="d-flex flex-wrap justify-content-center align-items-start gap-3"
                  >
                    <!-- UPDATED: Điều chỉnh kích thước card -->
                    <div class="card col-12 col-md-6">
                      <div class="card-header">Product information</div>
                      <ul class="list-group list-group-flush">
                        <li class="list-group-item">ID: ${product.id}</li>
                        <li class="list-group-item">
                          Product's name: ${product.name}
                        </li>
                        <li class="list-group-item">Price: ${product.price}</li>
                        <li class="list-group-item">
                          Detail Description: ${product.detailDesc}
                        </li>
                        <li class="list-group-item">
                          Short Description: ${product.shortDesc}
                        </li>
                        <li class="list-group-item">
                          Quantity: ${product.quantity}
                        </li>
                        <li class="list-group-item">
                          Factory: ${product.factory}
                        </li>
                        <li class="list-group-item">
                          Target: ${product.target}
                        </li>
                      </ul>
                    </div>

                    <!-- UPDATED: Điều chỉnh ảnh avatar -->
                    <div class="card col-12 col-md-5 text-center">
                      <div class="card-header"><h6>Product Image</h6></div>
                      <div class="p-3">
                        <img
                          src="/images/productImage/${product.image}"
                          class="img-fluid rounded-circle object-fit-cover"
                          style="width: 150px; height: 150px"
                          alt="Product Image"
                        />
                      </div>
                    </div>
                  </div>

                  <a href="/admin/product" class="btn btn-success mt-3">Back</a>
                </div>
              </div>
            </div>
          </div>
        </main>
        <jsp:include page="../layout/footer.jsp" />
      </div>
    </div>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
      crossorigin="anonymous"
    ></script>
    <script src="js/scripts.js"></script>
  </body>
</html>
