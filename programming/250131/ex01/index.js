console.log("여기서 실행이");
myFun();    // 된다! -> 호이스팅

// 함수 선언과 함수 표현식
function myFun(){
    console.log("안녕하세요!");
}

myFun;
console.log(myFun);     // 객체 상태(타입) 자체가 출력
myFun();    // 호출 되었다
console.log(myFun()); // 호출 + 그 결과값이 출력(return값이 없으면 undefined)

// yourFun();  // 에러! ReferenceError: Cannot access 'yourFun' before initialization
const yourFun = function(){
    console.log("여러분 눈 오는 곳에 계시나요?");
}
yourFun();

// arrow function, '->' / '=>'
// java는 '->'
// javascript는 '=>'
// python은 lamda? : ...

merge("파이썬", "SQL");

function merge(a, b) {
  console.log(`${a}와 ${b}는 머지되었다!`);
}

merge("파이썬", "SQL");

// function merge(a, b) {
//   console.log(`${a}와 ${b}는 머지되었다!!!`);
// } // 가장 마지막에 있는 function이 호이스팅
const merge2 = function (a, b) {
  console.log(`${a}와 ${b}는 머지되었다!!!`);
}; // 가장 마지막에 있는 function이 호이스팅
const merge3 = (a, b) => {
  // return이 없다면 고민 없이 중괄호에 화살표
  console.log(`${a}와 ${b}는 머지되었다!!!`);
}; // 가장 마지막에 있는 function이 호이스팅

merge2("자바", "도커");
merge3("HTML", "GitHub");

const merge4 = (a, b) => {
  // return이 없다면 고민 없이 중괄호에 화살표
  console.log(`${a}와 ${b}는 머지되었다!!!`);
  return "결과";
}; // 가장 마지막에 있는 function이 호이스팅
console.log(merge4("두", "둥"));
const merge5 = (a, b) => a + b;
// 객체가 아닌 형태의 리턴값을 가질 때 return 생략 가능
console.log(merge5(1, 2));
// 객체라면 어짜피 return을 생략할 수 없음 -> merge4처럼 해라...

// -----------------------------------

// 자바 람다식

function eat(menu = "밥"){  // 디폴트 매개변수
    console.log(`${menu}을(를) 먹는다`);
}

eat();

function test1(a=1, b=2){
    console.log(a, b);
}
function test2(a, b=2){
    console.log(a, b);
}
function test3(a=1, b){
    console.log(a, b);
}

test1();
test1(1);
test1(1, 2);
test2();
test2(1);
test2(1, 2);
test3();
test3(1);   // 1 undefined 출력
test3(1, 2);

// -------------------------------

function sum(...numbers) {  // numbers가 배열로 받아짐
    console.log(numbers);
}

sum(1, 2, 3);

function ourFun(a, b, ...c){   // ...은 무조건 마지막에 가능
    console.log(a, b, c);
}

ourFun(1, 2, 3, 4, 5, "6");
// => undefined를 만나게 되는 상황들 예시
// 1. 객체 등에서 키나 프로퍼티 빼먹은 이슈(혹은 dom에서 id, class)
// 2. 함수나 메서드, 생성자에서 패러미터를 빼먹을 시
// 3. 비동기 처리 시 제대로 동기 처리나 큐/쓰레드 완료 못하면

function createCounter(){   // 감싸기만 했어.. 본체가 아니야
    let count = 0;
    return function(){
        return ++count; // 증감연산자
        // ++이 앞에 붙으면 일단 증가를 하고 그 값을 바로 리턴함
        // ++이 뒤로 가면 값을 돌려주고 증가를 함(--도 같은 로직)
    }
}

const counter = createCounter();
console.log(counter());
console.log(counter());
console.log(counter());

function funfun1(){
    // 자동으로 return이 있는 셈 친다.
}

console.log(funfun1);
console.log(funfun1());

function funfun2(){
    return;     // break랑 유사한데 대신, 함수단위...
}
// 위의 결과와 같다.
console.log(funfun2);
console.log(funfun2());
function funfun3(){
    return 1; 
    console.log("뭐가 있어도 도달 못함");
}
// 위의 결과와 같다.
console.log(funfun3);
console.log(funfun3());