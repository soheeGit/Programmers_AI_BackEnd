// 객체 리터럴과 프로퍼티
const newYear = {
    name: "을사년",
    number: 2025,
    "wish": ["취업", "다이어트", "건강"],
    1: "숫자도 가능",   //권장X
    true: "가능?",  //이건 boolean true가 아니라 변수명으로 들어감
    hello: function(){
        console.log("안녕하세요!");
    }
}

console.log(newYear.name);
console.log(newYear.number);
console.log(newYear["number"]);
console.log(newYear.wish);
console.log(newYear["wish"]);
// console.log(newYear.1);  오류!
console.log(newYear["1"]);
console.log(newYear.true);
console.log(newYear.hello);
console.log(newYear.hello());
newYear.hello();

// ----------------------------

// es6+ 이후로는 문자열 프로퍼티 하에서는 어느정도의 순서보장이 된다.
console.log(Object.keys(newYear));
// 자바스크립트 바나나 (banana)
console.log(("b" + "a" + +"a" + "a").toLowerCase());

console.log(Object.values(newYear)); // key의 순서에 대응, 하지만 이걸 가지고 뭘 하지는 말라
// 왜? : 브라우저마다 결과가 다를 수 있음(보장되지 않음)

console.log(Object.entries(newYear));


// ----------------------------

const language = ["python", "js", "c++", 0, true, {}, []];
// 자바스크립트에서의 함수형 프로그래밍 -> jsx, tsx react. next.

// R과 엑셀을 제외하고서는 (가끔씩 LLM도 1부터씩 세는데 오히려 0부터 세서 불편할 때가 더 많음)
// 0이라고 하는 태그를 붙여서 1씩 증가하면서 숫자 태그를 붙여간다
// 고정적인 길이를 가지는 경우도 있지만, 자바스크립트는 굳이 고정하지 않는다면 그 크기는 유동적이다. 
// 자르다 1 - 3 => js, c++.
console.log(language.slice(1, 3));  // 포함, 불포함 여부를 체크해야 함
// 끝점은 포함되지 않음. 시작점은 포함, 끝점은 불포함
// (1) 배열의 인덱스를 적용하고 있는가(0부터 시작하는가)
// (2) 시작점과 끝점을 어떻게 적용하는가 (일반적으로 시작점은 포함, 끝점은 제외)
// -> 자바스크립트는 음수 인덱스가 없다(주의!)(파이썬과 혼용 주의)
console.log(language.length);   //() => 괄호가 필요한 건 호출이 필요
// 호출이 필요한 건 -> 함수 또는 메서드라는 것
// 파이썬은 len(...) 자바는 배열.length()나 자료구조.size()로 처리

console.log(language.concat(language)); 
// 배열들로 구성된 원소가 있을경우 그 원소들로 합쳐진 (1차적인 배열이 해제된) 배열을 리턴해준다.
// language.concat(language) => 원본과 무슨 관계?

const l2 = language.concat(language, language); // concat -> 2개를 연결
// 아예 새로운 배열이 나온 것.

console.log(language);
console.log(l2);

l2.pop();   // 맨 끝에 있는 것 제거...

console.log(language);  // 영향이 없음.
console.log(l2);

for(let i = 0; i < language.length; i++){
    console.log(i, language[i]);    // 배열은 인덱스와 배열[인덱스]로 값 호출
    // 마이너스라던가, 전체 길이 이상의 값을 호출한다면
    // arr.length 이상의 값을 호출하면 에러? undefined?
    // -> 없는 키를 호출하면 undefined가 난다;; (정말 신기한 언어...)
}

for(const v of language){
    // 값 하나 하나를 굳이 인덱스를 호출하는 번거로움을 거치지 않고,
    // 그냥 v라는 변수로 그때 그때 (이 v는 다른 이름이어도 되고, 블록이 지나면 소멸)
    console.log(v);
    // 인덱스를 못쓰는 것이 단점. 백트래킹 류라던가..?
}

// 어레이에서는 의미가 없고 객체 사용해야함
for(const key in newYear){
    // console.log(key, newYear.key); // key라는 이름의 프로퍼티를 조회
    console.log(key, newYear[key]); // 무조건 대괄호
}