<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-md-3">

                    <!-- Profile Image -->
                    <div class="box box-primary">
                        <div class="box-body box-profile">
                            <h3 class="profile-username text-center">${vehicle.manufacturer} ${vehicle.model}</h3>

                            <ul class="list-group list-group-unbordered">
                                <li class="list-group-item">
                                    <b>Reservation(s)</b> <a class="pull-right">${lenReservations}</a>
                                </li>
                                <li class="list-group-item">
                                    <b>Client proprietaire</b> <a class="pull-right">0</a>
                                </li>
                            </ul>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
                <div class="col-md-9">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active">
                                <a href="#rents" data-toggle="tab">Reservations</a>
                            </li>
                            <li>
                                <a href="#client" data-toggle="tab">Client propriétaire</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="active tab-pane" id="rents">
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Voiture</th>
                                            <th>Date de debut</th>
                                            <th>Date de fin</th>
                                        </tr>
                                        <c:forEach items="${reservations}" var="reservation">
                                            <tr>
                                                <td>${reservation.id}.</td>
                                                <td>${reservation.vehicle_id}</td>
                                                <td>${reservation.startTime}</td>
                                                <td>${reservation.endTime}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="client">
                                <!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <c:if test="${client == null}">
                                        <div class="alert alert-danger alert-dismissible">
                                            <h4><i class="icon fa fa-ban"></i> Attention</h4>
                                            Aucun client n'est proprietaire de cette voiture.
                                        </div>
                                    </c:if>
                                    <c:if test="${client != null}">
                                        <table class="table table-striped">
                                            <tr>
                                                <th style="width: 10px">#</th>
                                                <th>Modele</th>
                                                <th>Constructeur</th>
                                                <th style=>Nombre de sieges</th>
                                            </tr>
                                            <tr>
                                                <td>${vehicle.id}.</td>
                                                <td>${vehicle.model}</td>
                                                <td>${vehicle.manufacturer}</td>
                                                <td>${vehicle.nb_places}</td>
                                            </tr>
                                        </table>
                                    </c:if>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                        </div>
                        <!-- /.tab-content -->
                    </div>
                    <!-- /.nav-tabs-custom -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->

        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>