const me = {
    name: "김개발",
    "job": "영화감독",
    age: 37,
    height: 172.5,
    married: false,
    hobby: ["코딩", "야구", "카드게임"],
    1: "영화",
    moveie: {
        name: "AI 감독관",
        year: 2025
    },
    hello: function(){
        console.log("안녕하세요!");
    },
    sayMyName: function(){
        console.log(`내 이름은 ${this.name}!`);
    },
};

console.log(me.name);
console.log(me["name"]);
// console.log(me.1) 이런거 주의
console.log(me[1]);

me.hello();
me.sayMyName();

// 프로시저 -> 일반적으로 뭐가 없어? return이 없음.
// 트리거 -> 이벤트/조건 -> 절차적, 묶음의 반복
// 함수. (라이브러리 함수. <사용자 정의 함수>.)

function add(a, b) {
    console.log("console.log(a + b): ", a + b);
    // return a + b;
    console.log(a + b);
    return;
    console.log("뒤가 없음");
}

console.log(add(1, 2));