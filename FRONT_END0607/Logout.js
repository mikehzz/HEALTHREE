document.getElementById("logoutBtn").addEventListener("click", function() {
  var confirmLogout = confirm("로그아웃 하시겠습니까?");
  if (confirmLogout) {
  
    // 세션 또는 토큰 삭제
    sessionStorage.removeItem('sessionID');
    localStorage.removeItem('token');
    
    window.location.href = "Home_main.html";
  }
});