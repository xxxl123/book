<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page
	import="com.rain.bean.AdminBean,com.rain.dao.AdminDao,com.rain.bean.AdminBean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN" class="ax-vertical-centered">
<head>
<meta charset="UTF-8">
<title>图书馆管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="static/css/bootstrap-admin-theme.css">
<link rel="stylesheet" href="static/css/bootstrap-admin-theme.css">
<script src="static/js/bootstrap.min.js"></script>
<script src="static/jQuery/jquery-3.1.1.min.js"></script>
<script src="static/js/bootstrap-dropdown.min.js"></script>
<script src="static/js/adminUpdateInfo.js"></script>
<script src="static/js/adminUpdatePwd.js"></script>
<style>
body {
	background-image: url("01.jpg");
}
#error{
      color:red;
      font-weight:bold;
  }
#error1{
      color:red;
      font-weight:bold;
  }
#error2{
      color:red;
      font-weight:bold;
  }
#error3{
      color:red;
      font-weight:bold;
  }
#error4{
      color:red;
      font-weight:bold;
  }
#error5{
      color:red;
      font-weight:bold;
  }
</style>
</head>

<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>

<body class="bootstrap-admin-with-small-navbar">
	<%
		AdminBean admin = new AdminBean();
		String aid = (String) session.getAttribute("aid");
		AdminDao admindao = new AdminDao();
		admin = admindao.get_AidInfo2(aid);
	%>
	<nav
		class="navbar navbar-inverse navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small"
		role="navigation">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="collapse navbar-collapse main-navbar-collapse">
						<a class="navbar-brand" href="admin.jsp"><strong>欢迎使用图书馆管理系统</strong></a>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a href="#" role="button"
								class="dropdown-toggle" data-hover="dropdown"> <i
									class="glyphicon glyphicon-user"></i> 欢迎您，<%out.write(admin.getName());%>
										(<%=session.getAttribute("aid")%>)
									<i class="caret"></i></a>
								<ul class="dropdown-menu">
									<li><a href="#updateinfo" data-toggle="modal">个人资料</a></li>
									<li role="presentation" class="divider"></li>
									<li><a href="#updatepwd" data-toggle="modal">修改密码</a></li>
									<li role="presentation" class="divider"></li>
									<!-- href="#identifier"  来指定要切换的特定的模态框（带有 id="identifier"）。-->
									<li><a href="/books/login.jsp">退出</a></li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</nav>

	<div class="container">
		<!-- left, vertical navbar & content -->
		<div class="row">
			<!-- left, vertical navbar -->
			<div class="col-md-2 bootstrap-admin-col-left">
				<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
					<li><a href="/books/tobookshow.bk"><i
							class="glyphicon glyphicon-chevron-right"></i> 图书管理</a></li>
					<li><a href="/books/toadminuser.rd"><i
							class="glyphicon glyphicon-chevron-right"></i> 读者管理</a></li>
					<li><a href="/books/tobooktypeshow.tp"><i
							class="glyphicon glyphicon-chevron-right"></i> 图书分类管理</a></li>
					<li><a href="/books/admin_borrow.jsp"><i
							class="glyphicon glyphicon-chevron-right"></i> 图书借阅信息</a></li>
					<li><a href="/books/admin_history.jsp"><i
							class="glyphicon glyphicon-chevron-right"></i> 图书归还信息</a></li>
					<li><a href="/books/admin_login.jsp"><i
							class="glyphicon glyphicon-chevron-right"></i>管理员管理(需登录)</a></li>
				</ul><br><br>
				<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
					<li><a href="/books/admin_bdtimes.jsp"><i
							class="glyphicon glyphicon-chevron-right"></i> 热门推荐</a></li>
					<li><a href="/books/admin_brtimes.jsp"><i
							class="glyphicon glyphicon-chevron-right"></i> 最佳读者</a></li>
				</ul><br><br>
				<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
					<li><a href="/books/admin_feedback.jsp"><i
							class="glyphicon glyphicon-chevron-right"></i> 读者反馈</a></li>
				</ul>
			</div>
		
			<!-- content -->
			<div class="col-md-10">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default bootstrap-admin-no-table-panel">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">读者管理</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form class="form-horizontal" action="/books/selectServlet"
									method="post">
									<div class="col-lg-3 form-group">
										<button type="button" class="btn btn-primary" id="btn_add"
											data-toggle="modal" data-target="#addModal">添加读者</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-lg-12">
						<table id="data_list" class="table table-hover table-bordered"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>ID</th>
									<th>账号</th>
									<th>姓名</th>
									<th>邮箱</th>
									<th>手机号</th>
									<th>可借阅天数</th>
									<th>最大可借数</th>
									<th>操作</th>
								</tr>
							</thead>
<c:forEach items="${list}" var="rd" varStatus="index">
				<tr>
					<%-- <td style="width:65px;">${index.index+1}</td> --%>
					<td >${rd.aid}</td>
					
					<td >${rd.username}</td>
					<td >${rd.name}</td>
					
					<td>${rd.email}</td>
					<td>${rd.phone}</td>
					
					<td>${rd.lend_num}</td>
					<td>${rd.max_num}</td>
					
					<td><button type="button" class="btn btn-warning btn-xs"
					data-toggle="modal" data-target="#updateModal" id="btn_update"
					onclick="showInfo2('${rd.aid}','${rd.username}','${rd.name}','${rd.email}',
					'${rd.phone}','${rd.password}','${rd.lend_num}','${rd.max_num}')">修改</button>
					<button type="button" class="btn btn-danger btn-xs"
					onclick="deletebook(${rd.aid})">删除</button></td>
				</tr>
	</c:forEach>
	
							<%-- <!---在此插入信息-->
							<%
								ArrayList<AdminBean> data2 = new ArrayList<AdminBean>();
								data2 = (ArrayList<AdminBean>) request.getAttribute("data");
								if (data2 == null) {
	
									data2 = (ArrayList<AdminBean>) admindao.get_ListInfo();
								}
								for (AdminBean bean : data2) {
							%>
							<tbody>
								<td><%=bean.getAid()%></td>
								<td><%=bean.getUsername()%></td>
								<td><%=bean.getName()%></td>
								<td><%=bean.getEmail()%></td>
								<td><%=bean.getPhone()%></td>
								<td><%=bean.getLend_num()%></td>
								<td><%=bean.getMax_num()%></td>
								<td><button type="button" class="btn btn-warning btn-xs"
										data-toggle="modal" data-target="#updateModal" id="btn_update"
										onclick="showInfo2('<%=bean.getAid()%>','<%=bean.getUsername()%>','<%=bean.getName()%>','<%=bean.getEmail()%>',
										'<%=bean.getPhone()%>','<%=bean.getPassword()%>','<%=bean.getLend_num()%>','<%=bean.getMax_num()%>')">修改</button>
									<button type="button" class="btn btn-danger btn-xs"
										onclick="deletebook(<%=bean.getAid()%>)">删除</button></td>
							</tbody>
							<%
								}
							%> --%>
						</table>
					</div>
				</div>
			</div>
		</div>
	
	<script type="text/javascript">
    function showInfo2(aid,username,name,email,phone,password,lend_num,max_num) {
        document.getElementById("updateaid").value = aid;
        document.getElementById("updateusername").value = username;
        document.getElementById("updatename").value = name;
        document.getElementById("updateemail").value = email;
        document.getElementById("updatephone").value = phone;
        document.getElementById("updatepassword").value = password;
        document.getElementById("updatelend_num").value = lend_num;
        document.getElementById("updatemax_num").value = max_num;
    }
    function deletebook(aid) {
    	con=confirm("是否删除?"); 
    	if(con==true){
    		location.href = "/books/deleteUserServlet.rd?aid="+aid;
    	}
    }
    </script>
	<!-- 修改模态框（Modal） -->
	<!-------------------------------------------------------------->
	
	<!-- 修改模态框（Modal） -->
	<form class="form-horizontal" method="post"
		action="/books/updateUserServlet.rd">
		<!--保证样式水平不混乱-->
		<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
			aria-labelledby="updateModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="updateModalLabel">修改读者信息</h4>
					</div>
					<div class="modal-body">

						<!---------------------表单-------------------->

						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">账号</label>
							<div class="col-sm-7">
								<input type="hidden" id="updateaid" name="aid"> <input
									type="text" class="form-control" id="updateusername"
									name="username" placeholder=""> <label
									class="control-label" for="updateISBN" style="display: none;"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">姓名</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="updatename"
									name="name" placeholder=""> <label
									class="control-label" for="updateBookName"
									style="display: none;"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">邮箱</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="updateemail"
									name="email" oninput="OnInput1(event)" onpropertychange="OnPropChanged1(event)" 
									placeholder=""> 
									<label id="error1"></label>
									<label
									class="control-label" for="updateAutho" style="display: none;"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">手机号</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="updatephone"
									name="phone" oninput="OnInput(event)" onpropertychange="OnPropChanged(event)" 
									 placeholder=""> 
									 <label id="error"></label>
									 <label
									class="control-label" for="updatePress" style="display: none;"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">密码</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="updatepassword"
									name="password" placeholder=""> <label
									class="control-label" for="updatePress" style="display: none;"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">可借阅天数</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="updatelend_num"
									name="lend_num" placeholder=""> <label
									class="control-label" for="updatePress" style="display: none;"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">最大借阅数</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="updatemax_num"
									name="max_num" placeholder=""> <label
									class="control-label" for="updatePress" style="display: none;"></label>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default"
							data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary">修改</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
	</form>

	<!-------------------------------------------------------------->

	<!--------------------------------------添加的模糊框------------------------>
	<form class="form-horizontal" method="post"
		action="/books/AddUserServlet.rd">
		<!--保证样式水平不混乱-->
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">添加新读者</h4>
					</div>
					<div class="modal-body">

						<!---------------------表单-------------------->

						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">账号</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="username"
									id="addISBN" required="required" placeholder="请输入账号">
								<label class="control-label" for="addISBN"
									style="display: none;"></label>
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">姓名</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="name"
									id="addBookName" required="required" placeholder="请输入姓名">
								<label class="control-label" for="addBookName"
									style="display: none;"></label>
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">邮箱</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="email"
									id="addAutho" oninput="OnInput3(event)" onpropertychange="OnPropChanged3()event)"
									 required="required" placeholder="请输入邮箱">
									<label id="error3"></label>
								<label class="control-label" for="addAutho"
									style="display: none;"></label>
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">手机号</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="phone"
									id="addPress" oninput="OnInput2(event)" onpropertychange="OnPropChanged2(event)" 
									 required="required" placeholder="请输入手机号">
									 <label id="error2"></label>
								<label class="control-label" for="addPress"
									style="display: none;"></label>								</div>
							</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">密码</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="password"
									id="addPress" required="required" placeholder="请输入密码">
								<label class="control-label" for="addPress"
									style="display: none;"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">可借阅天数</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="lend_num"
									id="updatelend_num" required="required" placeholder="请输入可借阅天数">
								<label class="control-label" for="addNum"
									style="display: none;"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">最大可借数</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="max_num"
									id="updatemax_num" required="required" placeholder="请输入最大可借数">
								<label class="control-label" for="addPress"
									style="display: none;"></label>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default"
							data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary">添加</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
	</form>
	<!--------------------------------------添加的模糊框------------------------>

	<!-------------------------------------------------------------->

	<form class="form-horizontal" method="post"
		action="/books/AdminServlet">
		<!--保证样式水平不混乱-->
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="updatepwd" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">修改密码</h4>
					</div>
					<div class="modal-body">
					
						<!--正文-->
						<input type="hidden" name="tip" value="1"> <input
							type="hidden" name="url" value="admin_user">
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">原密码</label>
							<div class="col-sm-7">
								<input type="password" class="form-control" name="password"
									id="oldPwd" placeholder="请输入原密码"> <label
									class="control-label" for="oldPwd" style="display: none"></label>
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">新密码</label>
							<div class="col-sm-7">
								<input type="password" class="form-control" name="password2"
									id="newPwd" placeholder="请输入新密码"> <label
									class="control-label" for="newPwd" style="display: none"></label>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default"
							data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary">修改</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
	</form>
	<!-------------------------------------------------------------->

	<!-------------------------个人资料模糊框------------------------------------->

	<form class="form-horizontal" method="post"
		action="/books/AdminServlet">
		<!--保证样式水平不混乱-->
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="updateinfo" tabindex="-1" role="dialog"
			aria-labelledby="ModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="ModalLabel">个人资料</h4>
					</div>
					<div class="modal-body">

							<!--正文-->
						<input type="hidden" name="tip" value="2"> <input
							type="hidden" name="url" value="admin_user">
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">真实姓名</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="name" name="name"
									placeholder="请输入您的真实姓名"
									value='<%out.write(admin.getName());%>'> <label
									class="control-label" for="name" style="display: none"></label>
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">手机号</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="phone" name="phone"
								oninput="OnInput4(event)" onpropertychange="OnPropChanged4(event)" 
									placeholder="请输入您的手机号"
									value='<%out.write(admin.getPhone());%>'> 
									<label id="error4"></label>
									<label
									class="control-label" for="phone" style="display: none"></label>
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">邮箱</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="email" name="email"
								oninput="OnInput5(event)" onpropertychange="OnPropChanged5(event)" 
									placeholder="请输入您的邮箱"
									value='<%out.write(admin.getEmail());%>'> 
									<label id="error5"></label>
									<label
									class="control-label" for="email" style="display: none"></label>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default"
							data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary">修改</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
	</form>
	<script type="text/javascript">
      // Firefox, Google Chrome, Opera, Safari, Internet Explorer from version 9
            function OnInput(event) {
                //alert ("The new content: " + event.target.value);
                var value = event.target.value;
                check(value);
            }
      // Internet Explorer
            function OnPropChanged(event) {
            console.info(event)
                if (event.propertyName.toLowerCase () == "value") {
                    var value = event.srcElement.value;
                    check(value);
                }
            }
        //验证用户名方法(只能是数字字母和中文，不能包括特殊字符)
        function check(value){
            var regex =/^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
            if(regex.test(value) == true){
                //格式正确
                document.getElementById("error").innerHTML="";
            }else{
                //格式错误
                document.getElementById("error").innerHTML="手机号格式不正确";
            }
        }
        </script>
        
<script type="text/javascript">
      // Firefox, Google Chrome, Opera, Safari, Internet Explorer from version 9
            function OnInput1(event) {
                //alert ("The new content: " + event.target.value);
                var value = event.target.value;
                check1(value);
            }
      // Internet Explorer
            function OnPropChanged1(event) {
            console.info(event)
                if (event.propertyName.toLowerCase () == "value") {
                    var value = event.srcElement.value;
                    check1(value);
                }
            }
        //验证用户名方法(只能是数字字母和中文，不能包括特殊字符)
        function check1(value){
            var regex = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
            if(regex.test(value) == true){
                //格式正确
                document.getElementById("error1").innerHTML="";
            }else{
                //格式错误
                document.getElementById("error1").innerHTML="邮箱格式不正确";
            }
        }
        </script>
        
        <script type="text/javascript">
      // Firefox, Google Chrome, Opera, Safari, Internet Explorer from version 9
            function OnInput2(event) {
                //alert ("The new content: " + event.target.value);
                var value = event.target.value;
                check2(value);
            }
      // Internet Explorer
            function OnPropChanged2(event) {
            console.info(event)
                if (event.propertyName.toLowerCase () == "value") {
                    var value = event.srcElement.value;
                    check2(value);
                }
            }
        //验证用户名方法(只能是数字字母和中文，不能包括特殊字符)
        function check2(value){
            var regex = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
            if(regex.test(value) == true){
                //格式正确
                document.getElementById("error2").innerHTML="";
            }else{
                //格式错误
                document.getElementById("error2").innerHTML="手机号格式不正确";
            }
        }
        </script>


<script type="text/javascript">
      // Firefox, Google Chrome, Opera, Safari, Internet Explorer from version 9
            function OnInput3(event) {
                //alert ("The new content: " + event.target.value);
                var value = event.target.value;
                check3(value);
            }
      // Internet Explorer
            function OnPropChanged3(event) {
            console.info(event)
                if (event.propertyName.toLowerCase () == "value") {
                    var value = event.srcElement.value;
                    check3(value);
                }
            }
        //验证用户名方法(只能是数字字母和中文，不能包括特殊字符)
        function check3(value){
            var regex = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
            if(regex.test(value) == true){
                //格式正确
                document.getElementById("error3").innerHTML="";
            }else{
                //格式错误
                document.getElementById("error3").innerHTML="邮箱格式不正确";
            }
        }
        </script>

<script type="text/javascript">
      // Firefox, Google Chrome, Opera, Safari, Internet Explorer from version 9
            function OnInput4(event) {
                //alert ("The new content: " + event.target.value);
                var value = event.target.value;
                check4(value);
            }
      // Internet Explorer
            function OnPropChanged4(event) {
            console.info(event)
                if (event.propertyName.toLowerCase () == "value") {
                    var value = event.srcElement.value;
                    check4(value);
                }
            }
        //验证用户名方法(只能是数字字母和中文，不能包括特殊字符)
        function check4(value){
            var regex = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
            if(regex.test(value) == true){
                //格式正确
                document.getElementById("error4").innerHTML="";
            }else{
                //格式错误
                document.getElementById("error4").innerHTML="手机号格式不正确";
            }
        }
        </script>
<script type="text/javascript">
      // Firefox, Google Chrome, Opera, Safari, Internet Explorer from version 9
            function OnInput5(event) {
                //alert ("The new content: " + event.target.value);
                var value = event.target.value;
                check5(value);
            }
      // Internet Explorer
            function OnPropChanged5(event) {
            console.info(event)
                if (event.propertyName.toLowerCase () == "value") {
                    var value = event.srcElement.value;
                    check5(value);
                }
            }
        //验证用户名方法(只能是数字字母和中文，不能包括特殊字符)
        function check5(value){
            var regex = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
            if(regex.test(value) == true){
                //格式正确
                document.getElementById("error5").innerHTML="";
            }else{
                //格式错误
                document.getElementById("error5").innerHTML="邮箱格式不正确";
            }
        }
        </script>
</body>
</html>