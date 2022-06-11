<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 5/16/2022
  Time: 9:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href ="../css/styles.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.css">
    <script type="text/javascript">

        function viewCustomers(){
            console.log("Start Test");
            console.log("Start");
            //getStatusCount();
            $(document).ready(function() {
                $.ajax({
                    type : "GET",
                    contentType : "application/json",
                    url : "/listCustomer",
                    data : {},
                    dataType : 'json',
                    cache : false,
                  //  timeout : 600000,
                    success : function(data) {
                        console.log("GoHome");

                        // Clear table data before load
                        var tBodyRef = document.getElementById('viewCustomerTable');
                        while (tBodyRef.rows.length > 1) {
                            tBodyRef.deleteRow(1);
                        }
                        tBodyRef.createTBody();

                        // Load table data
                        $.each(data.data, function (index) {
                            let printString = '<tr><td>' + data.data[index].id + '</td>' +
                                '<td>' + data.data[index].fname + '</td>' +
                                '<td>' + data.data[index].lname + '</td>' +
                                '<td>' + data.data[index].address + '</td>' +
                                '<td>' + data.data[index].contactNo + '</td>' +
                                '<td>' + data.data[index].totalTrans + '</td>'+
                                '<td>' + '<button id="deleteBtn" class="min-w-36 btn btn-default btn-sm mr-2" title="Delete" onClick="deleteCustomer(\'' +
                                data.data[index].id +
                                '\')">' +
                                '<img src="../images/delete.jpg" style="height:25px; weidth:25px" alt=""></button>' + '</td></tr>';
                            $('#viewCustomerTable tr:last').after(printString);

                        });
                    },
                    error : function(e) {
                        alert(e);
                    }
                });
            });
        }

        function getStatusCount(){
            console.log("Start Status Count");
            $(document).ready(function() {
                $.ajax({
                    type : "GET",
                    contentType : "application/json",
                    url : "/customer/getStatus",
                    data : {},
                    dataType : 'json',
                    cache : false,
                  //  timeout : 600000,
                    success : function(data) {
                        console.log("GoHome Status Count");
                        console.log(data);
                        $('#packingCount').html(data.packing);
                        $('#deliveringCount').html(data.delivering);
                        $('#completedCount').html(data.completed);
                        $('#revenueCount').html(data.total);
                    },
                    error : function(e) {
                        console.log("Error: " + e);
                    }
                });
            });
        }

        function deleteCustomer(keyVal) {
            console.log("key val: " + keyVal);
            $('#deleteCodeCommon').val(keyVal);
            $('#modalDeleteCommon').modal('toggle');
            $('#modalDeleteCommon').modal('show');
        }

        function deleteCommon(){

            console.log($('#deleteCodeCommon').val());

            $.ajax({
                type: 'POST',
                url: '/deleteCustomer',
                data: {id: $('#deleteCodeCommon').val()},
                success: function (res) {

                    //close delete modal
                    $('#modalDeleteCommon').modal('toggle');

                    if (res.flag) { //success
                        toastr.success(res.successMessage);
                        viewCustomers();
                        getStatusCount();
                    } else {
                        //Set error messages
                        toastr.error(res.errorMessage);
                    }
                },
                error: function (jqXHR) {

                }
            });
        }

    </script>
</head>
<body onload="viewCustomers(); getStatusCount();">
<div class="side-menu">
    <br/>
    <div class="brand-name">
        <img src="../images/coop.png"/>

    </div>
    <br/>
    <ul>
        <li><span> </span></li>
        <li><span> </span></li>
        <li onclick="window.location.href='customer'"><img src="../images/cus.png" alt="" style="height:40px; weidth:40px">&nbsp;&nbsp; <span>Customers</span></li>
        <li onclick="window.location.href='deliverer'"><img src="../images/delivery.png" alt="" style="height:40px; weidth:40px">&nbsp;&nbsp;<span>Deliverers</span></li>
        <li onclick="window.location.href='product'"><img src="../images/product.png" alt="" style="height:40px; weidth:40px">&nbsp;&nbsp;<span>Products</span></li>
        <li><img src="../images/his.png" alt="" style="height:40px; weidth:40px">&nbsp;&nbsp;<span>History</span></li>
        <li><img src="../images/report.png" alt="" style="height:40px; weidth:40px">&nbsp;&nbsp;<span>Reports</span></li>
        <li><img src="../images/logout.png" alt="" style="height:40px; weidth:40px">&nbsp;&nbsp; <span>LogOut</span></li>

    </ul>
</div>
<div class="container">
    <div class="header">
        <div class="nav">
            <div class="search">
                <input type="text" placeholder="Search.." >
                <button type="submit"><img src="../images/search.png" alt=""></button>
            </div>
            <div class="user">


            </div>
        </div>
    </div>
    <div class="content">
        <div class="cards">
            <div class="card" style="background:#CE2F15">
                <div class="box">
                    <h1 id="packingCount">22</h1>
                    <h3>Packing..</h3>
                </div>
                <div class="icon-case">
                    <img src="../images/pending.png" alt="" style="height:50px; weidth:50px">
                </div>
            </div>
            <div class="card" style="background:#E6F40C">
                <div class="box">
                    <h1 id="deliveringCount">15</h1>
                    <h3>Delivering..</h3>
                </div>
                <div class="icon-case">
                    <img src="../images/deliver.png" alt="" style="height:50px; weidth:50px">
                </div>
            </div>
            <div class="card" style="background:#1DF40C">
                <div class="box">
                    <h1 id="completedCount">10</h1>
                    <h3>Completed</h3>
                </div>
                <div class="icon-case">
                    <img src="../images/completed.png" alt="" style="height:50px; weidth:50px">
                </div>
            </div>
            <div class="card" style="background:#EDEEEE">
                <div class="box">
                    <h1 id="revenueCount">Rs: 25500</h1>
                    <h3>Total Revenue</h3>
                </div>
                <div class="icon-case">
                    <img src="../images/rev.png" alt="" style="height:50px; weidth:50px">
                </div>
            </div>
        </div>
        <div class="content-2">
            <div class="recent-payments">
                <div class="title">
                    <h2>Registered Customers</h2>

                </div>
                <table id="viewCustomerTable">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Address</th>
                        <th>Contact No</th>
                        <th>Total Trans</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                    <%--<tr>
                        <td>1</td>
                        <td>Yasitha</td>
                        <td>Dilshan</td>
                        <td>182/A, Watinapaha, Veyangoda</td>
                        <td>0112289765</td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5</td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;<img src="dlt.jpg" style="height:25px; weidth:25px"></td>

                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Hashan</td>
                        <td>Madusanka</td>
                        <td>18/A, Develapola, Veyangoda</td>
                        <td>0112289905</td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5</td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;<img src="dlt.jpg" style="height:25px; weidth:25px"></td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Hasitha</td>
                        <td>Karunathilake</td>
                        <td>185/B, Kamaragoda, Minuwangoda</td>
                        <td>0112469765</td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;12</td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;<img src="dlt.jpg" style="height:25px; weidth:25px"></td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>Pasindu</td>
                        <td>Dushan</td>
                        <td>182/A, Yakahatuwa, Minuwangoda</td>
                        <td>0118889765</td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7</td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;<img src="dlt.jpg" style="height:25px; weidth:25px"></td>
                    </tr>
                    <tr>
                        <td>5</td>
                        <td>Malani</td>
                        <td>Perera</td>
                        <td>192/A, Waagowwa, Minuwangoda</td>
                        <td>0112209365</td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6</td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;<img src="dlt.jpg" style="height:25px; weidth:25px"></td>
                    </tr>--%>

                </table>
            </div>

        </div>
    </div>
</div>
</body>

<jsp:include page="deleteCommon.jsp"/>

</html>
