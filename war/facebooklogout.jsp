<html>
<title>Logging out of Facebook</title>
<body>
<p>Please wait...</p>
<%
	session.invalidate();
%>
<div id="fb-root"></div>
<script src="http://connect.facebook.net/en_US/all.js"></script>
<script>
FB.init({ apiKey: '<%=com.pgu.server.utils.ConfigApp.FB_APP_KEY.getValue()%>' });
FB.getLoginStatus(onResponse);
function onResponse(response) {
    if (!response.session) {
    	   window.location = "http://pgu-languages.appspot.com/";
    	   return;
    }
    FB.logout(onResponse);
}
</script>

</body>
</html>
