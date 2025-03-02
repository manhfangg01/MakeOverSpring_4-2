<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@taglib
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
    <title>Update-User - Hỏi Dân IT</title>
    <link href="/css/styles.css" rel="stylesheet" />
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
            <h1 class="mt-4">Update Product</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
              <li class="breadcrumb-item active">
                <a href="/admin/product">Product</a>
              </li>
              <li class="breadcrumb-item active">Update</li>
            </ol>
            <div class="mt-5">
              <div class="row">
                <div class="col-md-6 col-12 mx-auto">
                  <h3>Update a product</h3>
                  <hr />
                  <form:form
                    method="post"
                    action="/admin/product/update"
                    modelAttribute="newProduct"
                  >
                    <div class="mb-3" style="display: none">
                      <label class="form-label">Id:</label>
                      <form:input type="text" class="form-control" path="id" />
                    </div>

                    <div class="mb-3">
                      <label class="form-label">Name:</label>
                      <form:input
                        type="name"
                        class="form-control"
                        path="name"
                      />
                    </div>

                    <div class="mb-3">
                      <label class="form-label">Price:</label>
                      <form:input
                        type="number"
                        min="0"
                        step="0.01"
                        class="form-control"
                        path="price"
                      />
                    </div>
                    <div class="mb-3">
                      <label class="form-label">Detail Description:</label>
                      <form:input
                        type="text"
                        class="form-control"
                        path="detailDesc"
                      />
                    </div>
                    <div class="mb-3">
                      <label class="form-label">Short Description:</label>
                      <form:input
                        type="text"
                        class="form-control"
                        path="shortDesc"
                      />
                    </div>
                    <div class="mb-3">
                      <label class="form-label">Quantity:</label>
                      <form:input
                        type="number"
                        class="form-control"
                        path="quantity"
                      />
                    </div>
                    <div class="mb-3">
                      <label class="form-label">Factory:</label>
                      <form:select class="form-select" path="factory">
                        <!--Path này nói cho controller biết là form chọn chỉ nhập vào role.name thôi-->
                        <form:option value="Apple">Apple(Macbook)</form:option>
                        <form:option value="Asus">Asus</form:option>
                        <form:option value="Lenovo">Lenovo</form:option>
                        <form:option value="Dell">Dell</form:option>
                        <form:option value="LG">LG</form:option>
                        <form:option value="Acer">Acer</form:option>
                      </form:select>
                    </div>
                    <div class="mb-3">
                      <label class="form-label">Target:</label>
                      <form:select class="form-select" path="target">
                        <!--Path này nói cho controller biết là form chọn chỉ nhập vào role.name thôi-->
                        <form:option value="Gaming">Gaming</form:option>
                        <form:option value="Mỏng nhẹ">Mỏng nhẹ</form:option>
                        <form:option value="Kinh doanh">Kinh doanh</form:option>
                        <form:option value="Sinh viên - Văn Phòng"
                          >Sinh viên - Văn Phòng</form:option
                        >
                        <form:option value="Thiết kế đồ họa"
                          >Thiết kế đồ họa</form:option
                        >
                      </form:select>
                    </div>
                    <button type="submit" class="btn btn-warning">
                      Update
                    </button>
                  </form:form>
                </div>
              </div>
            </div>
          </div>
        </main>
        <jsp:include page="../layout/footer.jsp" />
      </div>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      crossorigin="anonymous"
    ></script>
    <script src="js/scripts.js"></script>
  </body>
</html>
