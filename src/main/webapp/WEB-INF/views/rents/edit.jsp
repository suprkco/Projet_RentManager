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
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Modification d'une reservation
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box">

                        <!-- form start -->
                        <form class="form-horizontal" method="post" >
                            <div class="box-body">
                                <!-- vehicle -->
                                <div class="form-group">
                                    <label for="vehicle" class="col-sm-2 control-label">Voiture</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="vehicle" name="vehicle" placeholder="${reservation.vehicle_id}" value="${reservation.vehicle_id}">
                                    </div>
                                </div>

                                <!-- client -->
                                <div class="form-group">
                                    <label for="client" class="col-sm-2 control-label">Client</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="client" name="client" placeholder="${reservation.client_id}" value="${reservation.client_id}">
                                    </div>
                                </div>

                                <!-- startTime -->
                                <div class="form-group">
                                    <label for="startTime" class="col-sm-2 control-label">Debut</label>

                                    <div class="col-sm-10">
                                        <input type="date" class="form-control" id="startTime" name="startTime" placeholder="${reservation.startTime}" value="${reservation.startTime}">
                                    </div>
                                </div>

                                <!-- endTime -->
                                <div class="form-group">
                                    <label for="endTime" class="col-sm-2 control-label">Fin</label>

                                    <div class="col-sm-10">
                                        <input type="date" class="form-control" id="endTime" name="endTime" placeholder="${reservation.endTime}" value="${reservation.endTime}">
                                    </div>
                                </div>

                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-info pull-right">Modifier</button>
                            </div>
                            <!-- /.box-footer -->
                        </form>

                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
