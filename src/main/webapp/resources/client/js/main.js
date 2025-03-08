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
    let change = 0;

    var button = $(this);
    var oldValue = button.parent().parent().find("input").val();
    if (button.hasClass("btn-plus")) {
      var newVal = parseFloat(oldValue) + 1;
      change = 1;
    } else {
      if (oldValue > 1) {
        var newVal = parseFloat(oldValue) - 1;
        change = -1;
      } else {
        newVal = 1;
      }
    }
    const input = button.parent().parent().find("input");
    input.val(newVal);

    //set form index
    const index = input.attr("data-cart-detail-index");
    const el = document.getElementById(`cartDetails${index}.quantity`);
    $(el).val(newVal);

    //get price
    const price = input.attr("data-cart-detail-price");
    const id = input.attr("data-cart-detail-id");

    const priceElement = $(`p[data-cart-detail-id='${id}']`);
    if (priceElement) {
      const newPrice = +price * newVal;
      priceElement.text(formatCurrency(newPrice.toFixed(2)) + " đ");
    }

    //update total cart price
    const totalPriceElement = $(`p[data-cart-total-price]`);

    if (totalPriceElement && totalPriceElement.length) {
      const currentTotal = totalPriceElement
        .first()
        .attr("data-cart-total-price");
      let newTotal = +currentTotal;
      if (change === 0) {
        newTotal = +currentTotal;
      } else {
        newTotal = change * +price + +currentTotal;
      }

      //reset change
      change = 0;

      //update
      totalPriceElement?.each(function (index, element) {
        //update text
        $(totalPriceElement[index]).text(
          formatCurrency(newTotal.toFixed(2)) + " đ"
        );

        //update data-attribute
        $(totalPriceElement[index]).attr("data-cart-total-price", newTotal);
      });
    }
  });
  function updateTotalCartPrice() {
    let newTotal = 0;
    const totalPriceElement = $(`p[data-cart-total-price]`);
    $(".cart-body").each(function () {
      const checkbox = $(this).find(".childCheckbox");
      const price =
        parseFloat($(this).find(".form-control").data("cart-detail-price")) ||
        0;
      const quantity = parseInt($(this).find(".form-control").val()) || 1;
      if (checkbox.prop("checked")) {
        newTotal += price * quantity;
      }
    });

    totalPriceElement?.each(function (index, element) {
      //update text
      $(totalPriceElement[index]).text(
        formatCurrency(newTotal.toFixed(2)) + " đ"
      );

      //update data-attribute
      $(totalPriceElement[index]).attr("data-cart-total-price", newTotal);
    });
  }

  $(".childCheckbox").on("change", function () {
    updateTotalCartPrice();
  }); // Khi 1 checkBox bị thay đổi thì nó sẽ gọi hàm updataTotalPrice
  $("#checkboxCart").on("change", updateTotalCartPrice);
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

  // Trigger toast
})(jQuery);
