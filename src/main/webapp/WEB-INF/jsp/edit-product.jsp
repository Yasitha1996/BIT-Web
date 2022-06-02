<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 6/2/2022
  Time: 11:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- EditProduct section modal popup start -->
edit
<div class="modal fade" id="modalEditProduct" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="modalEditProductLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h6 class="modal-title" id="modalEditProductLabel">Update Product</h6>
                <button type="button" id="editProductPopupClose" class="btn-close" data-dismiss="modal" aria-label="Close"
                        onclick="closeUpdateModal()">
                    <i aria-hidden="true" class="ki ki-close"></i>
                </button>
            </div>
            <form:form class="form-horizontal sm" id="updateProductForm" modelAttribute="product" method="post"
                       name="updateProductForm">

                <div class="modal-body">
                    <div class="form-group"><span id="responseMsgUpdate"></span></div>

                    <div hidden>
                        <input name="product_id" id="editProductId"/>
                    </div>
                    <div class="form-group row">
                        <div class="col-lg-6">
                            <label>Product Name <span class="text-danger">*</span></label>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group">
                                <form:input path="product_name" name="product_name" type="text"
                                            class="form-control form-control-sm" id="editProductName" maxlength="50"
                                            placeholder="Product Name"
                                            onkeyup="this.value=$(this).val().replace(/[^a-zA-Z0-9 ]/g,'')"/>
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
                                            class="form-control form-control-sm" id="editUnitQty" maxlength="50"
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
                                            class="form-control form-control-sm" id="editUnitPrice" maxlength="50"
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
                                            class="form-control form-control-sm" id="editAailableStock" maxlength="50"
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
                                               class="form-control form-control-sm" id="editDesription"
                                               maxlength="250"
                                               placeholder="Description"
                                               onkeyup="this.value=$(this).val().replace(/[^a-zA-Z0-9 ]/g,'')"/>
                            </div>
                        </div>
                    </div>

                    <!-- /.card-body -->
                </div>
                <div class="modal-footer justify-content-end">
                    <button id="editProductBack" type="button" class="btn btn-secondary" onclick="resetUpdateProduct()">
                        Reset
                    </button>
                    <button id="editProductBtn" type="button" onclick="updateProduct()" class="btn btn-primary">Update
                    </button>
                </div>
            </form:form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<script>
    function updateProduct() {

        $.ajax({
            type: 'POST',
            url: '/updateProduct',
            data: $('form[name=updateProductForm]').serialize(),
            success: function (res) {
                if (res.flag) { //success
                    toastr.success(res.successMessage);
                    $('#modalEditProduct').modal('toggle');
                    viewProduct();
                    getStatusCount();
                } else {
                    $('#responseMsgUpdate').show();
                    $('#responseMsgUpdate').addClass('error-response').text(res.errorMessage);
                    toastr.error(res.errorMessage);
                }
            },
            error: function (jqXHR) {
                <%--window.location = "${pageContext.request.contextPath}/logout.htm";--%>
            }
        });
    }


    function resetUpdateProduct() {
        $.ajax({
            url: "/getProduct",
            data: {
                productId: $('#editProductId').val()
            },
            dataType: "json",
            type: 'GET',
            contentType: "application/json",

            success: function (data) {

                $('#editProductId').val(data.product_id);
                $('#editProductName').val(data.product_name);
                $('#editUnitQty').val(data.unit_qty);
                $('#editUnitPrice').val(data.unit_price);
                $('#editAailableStock').val(data.available_stock);
                $('#editDesription').val(data.description);

            },
            error: function (data) {
                console.log(data);
                <%--window.location = "${pageContext.request.contextPath}/logout.htm";--%>
            }
        });

        resetUpdateTaskFormData();

    }

    function resetUpdateTaskFormData() {
        $(".validation-err").remove();

        if ($('#responseMsgUpdate').hasClass('success-response')) {
            $('#responseMsgUpdate').removeClass('success-response');
        }

        if ($('#responseMsgUpdate').hasClass('error-response')) {
            $('#responseMsgUpdate').removeClass('error-response');
        }

        $('#responseMsgUpdate').hide();
    }

    function closeUpdateModal(){
        $('#editProductId').val("");
        resetUpdateTaskFormData();
        $('#modalEditProduct').modal('toggle');
    }
</script>
