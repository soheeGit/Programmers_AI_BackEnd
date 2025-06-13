const promptForm = document.querySelector('#promptForm');
const promptText = document.querySelector('#promptText');

promptForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    // alert('폼 제출!')
    const promptTextValue = promptText.value.trim();
    // alert(`입력된 프롬프트: ${promptTextValue}`);
    if (!promptTextValue) {
        alert('프롬프트를 입력해주세요.');
        return;
    }
    // 페치 시 폼 버튼 disabled
    const submitButton = document.querySelector('#promptForm button');
    submitButton.disabled = true;
    // 프롬프트 처리
    try {
        const baseUrl = 'https://annoying-sunbae.onrender.com'; // 이걸 바꾸면 됩니다
        const response = await fetch(`${baseUrl}/api/prompt`, {
            method: 'POST',
            // Fetch시 직렬화하세요...
            body: JSON.stringify({ text: promptTextValue }),
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (!response.ok) {
            alert('프롬프트 처리 중 오류가 발생했습니다.');
            return;
        }
        const result = await response.json();
        // alert(JSON.stringify(result));
        // 결과를 화면에 표시
        const promptDataId = document.querySelector('#promptDataId');
        const promptDataQuestion = document.querySelector('#promptDataQuestion');
        const promptDataAnswer = document.querySelector('#promptDataAnswer');
        promptDataId.textContent = result.id;
        promptDataQuestion.textContent = result.question;
        promptDataAnswer.textContent = result.answer;
        // 복사 관련
        const pageUrl = 'https://qus0in.github.io/sunflower';
        const shareLink = document.querySelector('#shareLink');
        shareLink.value = `${pageUrl}/history/?id=${result.id}`;
        const openLink = document.querySelector('#openLink');
        openLink.href = `${pageUrl}/history/?id=${result.id}`;
        const promptData = document.querySelector('#promptData');
        promptData.style.display = 'block';
    } finally {
        // 다 끝나면
        submitButton.disabled = false;
    }
});