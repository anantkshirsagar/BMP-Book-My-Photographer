<!DOCTYPE html>
<%
	if (session.getAttribute("email") == null) {
		response.sendRedirect(request.getContextPath() + "/logout.jsp");
	}
%>
<html>
<head>
<title>Photographer Profile</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<script src="lib/angjs.js"></script>
<script src="lib/w3-lib.js"></script>
<div ng-include="'commons/load-libs.html'"></div>
</head>
<body ng-app="">
	<div class="jumbotron">
		<div class="container">
			<div class="w3-card-4">
				<div class="w3-container w3-green">
					<h2>Complete your profile</h2>
				</div>
				<div class="w3-container">
					<br>
					<form action="PhotographerUpdationServlet" method="post"
						enctype="multipart/form-data">
						<!-- <label>Camera Type </label> <input class="w3-check"
							type="checkbox" name="cameraType"> <label>Full
							Frame</label> <input class="w3-check" type="checkbox" name="cameraType">
						<label>Crop Frame</label> <br>  --><br> <label>Category(add
							comma separated multiple categories)</label> <input class="w3-input"
							type="text" name="category" id="category" required> <br> <br>
						<label>Upload Your best photos(Maximum 8)</label> <input
							type="file" id="files" name="filename" id="uploadBox" multiple
							accept="image/*" onchange="checkFiles()">
						<!-- <input type="file" name="filename" value="Upload File"  multiple> -->
						<br> <br>
						<div>
							<h4>
								<b>Terms : </b>
							</h4>
							<h5>1. After final submit application will go for
								verification.</h5>
							<h5>2. Verification can take upto 1 week.</h5>
							<h5>3. Verification is based on uploaded photos.</h5>
						</div>
						<br>
						<button type="submit" class="w3-button w3-blue w3-block">Final
							Submit</button>
						<br> <br> <br>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function checkFiles() {
			var numFiles = $("input:file")[0].files.length;
			if (numFiles > 8) {
				alert("Maximum 8 photos allowd!")
				$("input:file")[0].value = null;
			}
			if (numFiles < 4) {
				alert("Minimum 4 photos required!")
				$("input:file")[0].value = null;
			}
		}
	</script>
</body>
</html>