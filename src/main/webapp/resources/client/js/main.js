(function ($) {
  ("use strict");

  // Spinner
  var spinner = function () {
    setTimeout(function () {
      if ($("#spinner").length > 0) {
        $("#spinner").removeClass("show");
      }
    }, 1);
  };
  spinner();

  // Fixed Navbar
  $(window).scroll(function () {
    if ($(window).width() < 992) {
      $(".fixed-top").toggleClass("shadow", $(this).scrollTop() > 55);
    } else {
      $(".fixed-top")
        .toggleClass("shadow", $(this).scrollTop() > 55)
        .css("top", 0);
    }
  });

  // Back to top button
  $(window).scroll(function () {
    $(".back-to-top").toggle($(this).scrollTop() > 300);
  });
  $(".back-to-top").click(function () {
    $("html, body").animate({ scrollTop: 0 }, 1500, "easeInOutExpo");
    return false;
  });

  $(document).ready(function () {
    $(".childCheckbox").prop("checked", true);
    $("#checkboxCart").prop("checked", true);
    $("#labelCheckbox").text("Bỏ chọn tất cả");
    toggleDeleteButton();
    updateTotalCartPrice();
  });

  // Cập nhật số lượng sản phẩm
  $(".quantity button").on("click", function () {
    var button = $(this);
    var input = button.closest(".quantity").find("input");
    var oldValue = parseInt(input.val());
    var newVal = oldValue;
    var price = parseFloat(input.data("cart-detail-price")) || 0;
    var id = input.data("cart-detail-id");
    var priceElement = $(`p[data-cart-detail-id='${id}']`);

    if (button.hasClass("btn-plus")) {
      newVal++;
    } else if (oldValue > 1) {
      newVal--;
    }

    input.val(newVal);
    priceElement.text(formatCurrency(price * newVal) + " đ");
    updateTotalCartPrice();
  });

  function updateTotalCartPrice() {
    let total = 0;
    $(".childCheckbox:checked").each(function () {
      let row = $(this).closest("tr");
      let price = parseFloat(row.find("input").data("cart-detail-price")) || 0;
      let quantity = parseInt(row.find("input").val()) || 0;
      total += price * quantity;
    });

    $("p[data-cart-total-price]").text(formatCurrency(total) + " đ");
  }

  $(".childCheckbox").on("change", updateTotalCartPrice);

  function formatCurrency(value) {
    return new Intl.NumberFormat("vi-VN").format(value);
  }

  $("#checkboxCart").on("click", function () {
    var checked = $(this).prop("checked");
    $(".childCheckbox").prop("checked", checked);
    $("#labelCheckbox").text(
      checked ? "Bỏ chọn tất cả" : "Chọn tất cả sản phẩm"
    );
    toggleDeleteButton();
  });

  $(".childCheckbox").on("click", toggleDeleteButton);

  function toggleDeleteButton() {
    $("#deleteAll").toggle($(".childCheckbox:checked").length > 0);
  }
})(jQuery);
