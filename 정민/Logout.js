/**
 * 로그아웃
 */
// 로그아웃 버튼 클릭 시 처리
document.getElementById('logoutBtn').addEventListener('click', function() {
  // 서버로 로그아웃 요청 전송 (예시에서는 생략)
  
  // 세션 또는 토큰 삭제
  sessionStorage.removeItem('sessionID');
  localStorage.removeItem('token');

  // 로그아웃 상태 처리
  // 로그아웃 후 리다이렉트 또는 다른 작업 수행 가능
  alert('로그아웃되었습니다.');
});