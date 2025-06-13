// 복사 기능
if (document.getElementById('copyBtn')) {
    document.getElementById('copyBtn').addEventListener('click', () => {
      const input = document.getElementById('shareLink');
      input.select();
      document.execCommand('copy');
      document.getSelection().removeAllRanges();

      const btn = document.getElementById('copyBtn');
      const originalText = btn.textContent;
      btn.textContent = '✅ 복사됨!';
      btn.style.background = 'rgba(34, 197, 94, 0.8)';

      setTimeout(() => {
        btn.textContent = originalText;
        btn.style.background = '';
      }, 2000);
    });
  }

  // 페이지 로드 애니메이션
  window.addEventListener('load', () => {
    const card = document.querySelector('.result-card');
    if (card) {
      card.style.opacity = '0';
      card.style.transform = 'translateY(20px)';

      setTimeout(() => {
        card.style.transition = 'all 0.6s ease';
        card.style.opacity = '1';
        card.style.transform = 'translateY(0)';
      }, 100);
    }
  });