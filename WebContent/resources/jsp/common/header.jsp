<%@ include file="/resources/includes/taglibs.jsp"%>
<!-- -------------- Header  -------------- -->
<header class="navbar navbar-fixed-top bg-dark">

	<div class="navbar-logo-wrapper">
		<a class="navbar-logo-text" href="Dashboard.html"> <img
			src="assets/img/hcm-logo.png" class="logo"> <b>HCMOne</b>
		</a> <span id="sidebar_left_toggle" class="ad ad-lines"></span>
	</div>


	<form class="navbar-form navbar-left search-form square" role="search">
		<div class="input-group add-on">
			<input type="text" class="form-control" placeholder="Search..."
				onfocus="this.placeholder=''" onblur="this.placeholder='Search...'">
			<div class="input-group-btn">
				<button class="btn btn-default" type="submit">
					<i class="glyphicon glyphicon-search"></i>
				</button>
			</div>
		</div>
	</form>
	<ul class="nav navbar-nav navbar-right">

		<li class="dropdown dropdown-fuse">
			<div class="navbar-btn btn-group">
				<div data-toggle="dropdown" class=" btn-md dropdown-toggle">
					<%--  <s:form action="doLogout">
                            <span>
									<s:submit class="fa fa-power-off fs26 text-danger" key="MLogout"></s:submit>
									</span>
							</s:form>    --%>
					<span class="fa fa-power-off fs26 text-danger"></span>
				</div>
			</div>
		</li>
	</ul>
</header>
<!-- -------------- /Header  -------------- -->