<script>
history.pushState(null, null, 'no-back-button');
window.addEventListener('popstate', function(event) {
  history.pushState(null, null, 'no-back-button');
});
</script>


<p>Work In Progress </p>
<a href="${pageContext.request.contextPath}/logout">Log Out </a>