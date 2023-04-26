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
                Utilisateurs
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
                                <!-- lastname -->
                                <div class="form-group">
                                    <label for="lastname" class="col-sm-2 control-label">Lastname</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Lastname">
                                    </div>
                                </div>

                                <!-- firstname -->
                                <div class="form-group">
                                    <label for="firstname" class="col-sm-2 control-label">Firstname</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="firstname" name="firstname" placeholder="Firstname">
                                    </div>
                                </div>

                                <!-- email -->
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">Email</label>

                                    <div class="col-sm-10">
                                        <input type="email" class="form-control" id="email" name="email" placeholder="Email">
                                    </div>
                                </div>

                                <!-- Birthdate -->
                                <div class="form-group">
                                    <label for="birthdate" class="col-sm-2 control-label">Birthdate</label>

                                    <div class="col-sm-10">
                                        <input type="date" class="form-control" id="birthdate" name="birthdate" placeholder="Birthdate">
                                    </div>
                                </div>

                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-info pull-right">Ajouter</button>
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
