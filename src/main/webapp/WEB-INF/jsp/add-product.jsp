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
                <button type="button" id="addProductPopupClose" class="close" data-dismiss="modal" aria-label="Close"
                        onclick="resetAddProduct()">
                    <i aria-hidden="true" class="ki ki-close"></i>
                </button>
            </div>
            <%--<form:form class="form-horizontal sm" id="addProductForm" modelAttribute="product" method="post"
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
                    </div>--%>

                    <!-- /.card-body -->
                </div>
                <div class="modal-footer justify-content-end">
                    <button id="addProductBack" type="button" class="btn btn-secondary" onclick="resetAddProduct()">
                        Reset
                    </button>
                    <button id="addProductBtn" type="button" onclick="addProduct()" class="btn btn-primary">Add
                    </button>
                </div>
            <%--</form:form>--%>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<script>

    function resetAddProduct() {
        $('form[name=addProductForm]').trigger("reset");
        $('#responseMsgAdd').hide();
    }

    function addProduct(){

    }

</script>
