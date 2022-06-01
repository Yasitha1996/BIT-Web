<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 5/23/2022
  Time: 11:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- AddProduct section modal popup start -->

<div class="modal fade" id="modalAddProduct" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="modalAddProductLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h6 class="modal-title" id="modalAddProductLabel">Add Product</h6>
                <button type="button" id="addProductPopupClose" class="btn-close" data-dismiss="modal" aria-label="Close"
                        onclick="closeAddModal()">
                    <i aria-hidden="true" class="ki ki-close"></i>
                </button>
            </div>
            <form:form class="form-horizontal sm" id="addProductForm" modelAttribute="product" method="post"
                       name="addProductForm">

                <div class="modal-body">
                    <div class="form-group"><span id="responseMsgAdd"></span></div>

                    <div class="form-group row">
                        <div class="col-lg-6">
                            <label>Product Name <span class="text-danger">*</span></label>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group">
                                <form:input path="product_name" name="product_name" type="text"
                                            class="form-control form-control-sm" id="addProductName" maxlength="50"
                                            placeholder="Product Name"
                                            onkeyup="this.value=$(this).val().replace(/[^a-zA-Z0-9 ]/g,'')"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-lg-6">
                            <label>Product Category <span class="text-danger">*</span></label>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group">
                                <select id="addCategory" name="category"
                                        class="form-control form-control-sm">
                                    <option selected value="">--Select Category--</option>
                                    <option value="1">Vegetables</option>
                                    <option value="2">Fruits</option>
                                    <option value="3">Meat</option>
                                    <option value="4">Grocery</option>
                                    <option value="5">Household</option>
                                    <option value="6">Bakery</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-lg-6">
                            <label>Unit Quantity<span class="text-danger">*</span></label>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group">
                                <form:input path="unit_qty" name="unit_qty" type="text"
                                            class="form-control form-control-sm" id="addUnitQty" maxlength="50"
                                            placeholder="Unit Quantity"
                                            onkeyup="this.value=$(this).val().replace(/[^kgKG0-9 .]/g,'')"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-lg-6">
                            <label>Unit Price (LKR)<span class="text-danger">*</span></label>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group">
                                <form:input path="unit_price" name="unit_price" type="text"
                                            class="form-control form-control-sm" id="addUnitPrice" maxlength="50"
                                            placeholder="Unit Price"
                                            onkeyup="this.value=$(this).val().replace(/[^0-9.]/g,'')"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-lg-6">
                            <label>Available Stock<span class="text-danger">*</span></label>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group">
                                <form:input path="available_stock" name="available_stock" type="text"
                                            class="form-control form-control-sm" id="addAailableStock" maxlength="50"
                                            placeholder="Availble Stock"
                                            onkeyup="this.value=$(this).val().replace(/[^kgKG0-9 .]/g,'')"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-lg-6">
                            <label>Description<span class="text-danger">*</span></label>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group">
                                <form:textarea path="description" name="description" rows="3" cols="30"
                                               class="form-control form-control-sm" id="addDesription"
                                               maxlength="250"
                                               placeholder="Description"
                                               onkeyup="this.value=$(this).val().replace(/[^a-zA-Z0-9 ]/g,'')"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-lg-6">
                            <label id="lblAddProductImage">Product Image <span class="text-danger">*</span></label>
                        </div>
                        <div class="col-lg-6">
                            <input type="file" id="addProductImage" class="form-control form-control-sm" name="addProductImage"
                                   accept="image/*" onchange="getImage('addProductImage', '#base64AddProductImage', 'addProductImagePreview')" style="display:none"/>
                            <label for="addProductImage" id="lblAddProductImagePreview">
                                <img src="../images/click_here_to_upload.jpeg" id="addProductImagePreview" alt="Image Not Found" width="100" height="100"/>
                            </label>
                            <form:input path="product_img" name="product_img" type="hidden" id="base64AddProductImage"/>
                        </div>
                    </div>

                    <!-- /.card-body -->
                </div>
                <div class="modal-footer justify-content-end">
                    <button id="addProductBack" type="button" class="btn btn-secondary" onclick="resetAddProduct()">
                        Reset
                    </button>
                    <button id="addProductBtn" type="button" onclick="addProduct()" class="btn btn-primary">Add
                    </button>
                </div>
            </form:form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<script>
    function getImage(imageType, imageCode, imagePreview) {

        $('#responseMsgAdd').hide();
        var fileSelected = document.getElementById(imageType);
        var regex = new RegExp("([a-zA-Z0-9\s_\\.\-:])+(.jpg|.png|.gif|.jpeg)$");

        if (fileSelected.files.length !== 0) {
            if (regex.test(fileSelected.value.toLowerCase())) {

                var fileToLoad = fileSelected.files[0];
                var fileReader = new FileReader();

                fileReader.onload = function(fileLoadedEvent) {
                    var image = new Image();
                    image.src = fileLoadedEvent.target.result;

                    image.onload = function () {

                        var max_width = 345;
                        var max_height = 135;

                        var height = this.height;
                        var width = this.width;

                        // -- START --   Image resize
                        if (width > max_width) {
                            height = height * (max_width / width);
                            width = max_width;
                        }
                        if (height > max_height) {
                            width = width * (max_height / height);
                            height = max_height;
                        }
                        // -- END --   Image resize

                        if (width > max_width) {
                            document.getElementById(imageType).val = "";
                            $('#responseMsgAdd').show();
                            $('#responseMsgAdd').addClass('error-response').text("You cannot upload this image. Image width should be less than " + max_width +"pixels.");
                            toastr.error("Image width should be less than " + max_width +"pixels.", "You cannot upload this image.");
                            // return false;
                        } else if (height > max_height) {
                            document.getElementById(imageType).val = "";
                            $('#responseMsgAdd').show();
                            $('#responseMsgAdd').addClass('error-response').text("You cannot upload this image. Image height should be less than " + max_height +"pixels.");
                            toastr.error("Image height should be less than " + max_height +"pixels.", "You cannot upload this image.");
                            // return false;
                        } else {
                            // -- START --   Resized image assign to canvas
                            var canvas = document.createElement("canvas");
                            canvas.width = width;
                            canvas.height = height;
                            var ctx = canvas.getContext("2d");
                            ctx.drawImage(image, 0, 0, width, height);
                            // -- END --   Resized image assign to canvas

                            var dataurl = canvas.toDataURL(fileToLoad.type);

                            let newBase64Value = dataurl.replace("data:", "").replace(/^.+,/, "");
                            $(imageCode).val(newBase64Value);

                            // set image preview
                            document.getElementById(imagePreview).setAttribute('src', dataurl);
                            // document.getElementById(imagePreview).setAttribute('src', "data:image/jpeg;base64," + newBase64Value);
                            $('#' + imagePreview).show();
                            // return true;
                        }
                    };
                    image.src = fileLoadedEvent.target.result;
                };
                fileReader.readAsDataURL(fileToLoad);

            } else {
                $('#responseMsgAdd').show();
                $('#responseMsgAdd').addClass('error-response').text("Invalid image format. Please select a valid Image file.(ex: .png , .jpg , .jpeg)");
                toastr.error("Please select a valid Image file.(ex: .png , .jpg , .jpeg)", "Invalid image format.");
                return false;
            }
        }
    }

    function closeAddModal(){
        resetAddProduct();
        $('#modalAddProduct').modal('toggle');
    }

    function resetAddProduct() {
        $('form[name=addProductForm]').trigger("reset");
        $('#responseMsgAdd').hide();

        $('#base64AddProductImage').val("");

        document.getElementById('addProductImagePreview').setAttribute('src', "../images/click_here_to_upload.jpeg");

    }

    function addProduct() {
        let data = $('form[name=addProductForm]').serialize();
        console.log("######");
        console.log(data);
        $.ajax({
            type: 'POST',
            url: '/addProduct',
            data: data,
            success: function (res) {
                if (res.flag) { //success
                    $('form[name=addProductForm]').trigger("reset");
                    toastr.success(res.successMessage);
                    $('#responseMsgAdd').show();
                    $('#responseMsgAdd').addClass('success-response').text(res.successMessage);
                    viewProduct();
                    getStatusCount();
                    resetAddProduct();
                    $('#modalAddProduct').modal('toggle');

                } else {
                    $('#responseMsgAdd').show();
                    $('#responseMsgAdd').addClass('error-response').text(res.errorMessage);
                    toastr.error(res.errorMessage);
                }
            },
            error: function (jqXHR) {
                <%--window.location = "${pageContext.request.contextPath}/logout.htm";--%>
            }
        });
    }

</script>
