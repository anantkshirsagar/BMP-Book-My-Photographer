function showImage(imageId, servletName){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
			var result = xhttp.responseText;
            document.getElementById(imageId).src = "data:image/png;base64," + result;
            console.log("Image is rendering!");
	    }
	};
	xhttp.open("POST", servletName, true);
	xhttp.send();
}
