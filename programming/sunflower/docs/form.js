// 캐릭터 선택 기능
const characterCards = document.querySelectorAll('.character-card');
const characterInput = document.getElementById('characterInput');
const selectedCharacterSpan = document.getElementById('selectedCharacter');
const submitBtn = document.getElementById('submitBtn');
const textInput = document.getElementById('textInput');

const characterNames = {
    'eren': '에렌 예거 🔥',
    'mikasa': '미카사 아커만 🗡️',
    'armin': '아르민 알레르토 📚',
    'levi': '리바이 아커만 ⚡',
    'zeke': '지크 예거 🧠'
};

characterCards.forEach(card => {
    card.addEventListener('click', () => {
        // 기존 선택 제거
        characterCards.forEach(c => c.classList.remove('selected'));

        // 새로운 선택 추가
        card.classList.add('selected');

        const character = card.dataset.character;
        characterInput.value = character;
        selectedCharacterSpan.textContent = characterNames[character];

        checkFormValid();
    });
});

// 폼 유효성 검사
function checkFormValid() {
    const hasCharacter = characterInput.value !== '';
    const hasText = textInput.value.trim() !== '';

    submitBtn.disabled = !(hasCharacter && hasText);
}

textInput.addEventListener('input', checkFormValid);

// 복사 기능
if (document.getElementById('copyBtn')) {
    document.getElementById('copyBtn').addEventListener('click', () => {
        const input = document.getElementById('shareLink');
        input.select();
        document.execCommand('copy');
        document.getSelection().removeAllRanges();
        document.getElementById('copyBtn').textContent = '복사됨';
        setTimeout(() => document.getElementById('copyBtn').textContent = '복사', 2000);
    });
}