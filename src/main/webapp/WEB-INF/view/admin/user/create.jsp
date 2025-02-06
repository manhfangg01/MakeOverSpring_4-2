<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@taglib
uri="http://www.springframework.org/tags/form" prefix="form" %>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Create Users</title>
    <!-- Latest compiled and minified CSS -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <!-- <link href="/css/demo.css" rel="stylesheet"> -->
  </head>

  <body>
    <div class="container mt-5">
      <div class="row">
        <div class="col-md-6 col-12 mx-auto">
          <!--Độ rộng bằng một nửa độ dài màn hình "col-md-6" -->
          <!-- mx-auto tự động căn lề trái và căn lề phải-->
          <h3>Create a user</h3>
          <hr />
          <form:form
            method="post"
            action="/admin/user/create"
            modelAttribute="newUser"
          >
            <!-- ModelAttribute = newUser : tức là sau khi người dùng nhập xong thì tất cả những thông tin đó sẽ được gán cho một đối tượng tên là newUser-->
            <div class="mb-3">
              <label class="form-label">Email:</label>
              <form:input type="email" class="form-control" path="email" />
              <!-- Không nên dùng id mà hãy dùng "path" dùng để định danh giống id nhưng dễ dùng hơn-->
              <!-- Tên path của mỗi input phải trùng với các thuộc tính của đổi tượng-->
            </div>
            <div class="mb-3">
              <label class="form-label">Password:</label>
              <form:input
                type="password"
                class="form-control"
                path="password"
              />
            </div>
            <div class="mb-3">
              <label class="form-label">Phone number:</label>
              <form:input type="text" class="form-control" path="phone" />
            </div>
            <div class="mb-3">
              <label class="form-label">Full Name:</label>
              <form:input type="text" class="form-control" path="fullName" />
            </div>
            <div class="mb-3">
              <label class="form-label">Address:</label>
              <form:input type="text" class="form-control" path="address" />
            </div>

            <button type="submit" class="btn btn-primary">Create</button>
          </form:form>
        </div>
      </div>
    </div>
  </body>
</html>
