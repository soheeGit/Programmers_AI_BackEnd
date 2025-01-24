let add = 1 + 1;
console.log("add: " + add);

let divide = 10 / 7;
console.log("divide: " + divide);
let modulo = 10 % 7;
console.log("modulo: " + modulo);
let pow = 10 ** 3;
console.log("pow: " + pow);
console.log("zeroDivine: ", 1 / 0); // zeroDivine:  Infinity 출력

// 비교 연산자
console.log("1 == 1.0: ", 1 == 1.0); // (느슨한) 동등. true 출력
console.log("1 === 1.0: ", 1 === 1.0); // 크게보면 둘다 Number라서 비교하면 true 출력
console.log("1 != 1.0: ", 1 != 1.0); // (느슨한) 부등. false 출력
console.log(`1 == "1.0": `, 1 == "1.0"); // true 출력
console.log(`1 === "1.0": `, 1 === "1.0"); // (엄격한) 동등. false 출력
console.log(`1 !== "1.0": `, 1 !== "1.0"); // (엄격한) 부등. true 출력

console.log("1 > 10", 1 > 10);
console.log("1 < 10", 1 < 10);
console.log("1 >= 10", 1 >= 10);
console.log("1 <= 10", 1 <= 10);
console.log(`"a" > "b"`, "a" > "b"); // b에게 부여된 아스키코드(문자코드) 가 더 커서 b가 더 크다.

let condition1 = 100 < 1000; // true 조건
let condition2 = "a" < "b"; // true 조건
let condition3 = 100 > 1000; // false 조건
let condition4 = "a" > "b"; // false 조건
// AND(&&) 단축 연산자.(T면 뒤를 확인하는 방식. F면 뒤 확인 안하고 바로 false)
console.log("condition1 && condition2: ", condition1 && condition2); // T T. true 출력
console.log("condition1 && condition3: ", condition1 && condition3); // T F. false 출력
console.log("condition3 && condition2: ", condition3 && condition2); // F T. false 출력
console.log("condition4 && condition3: ", condition4 && condition3); // F F. false 출력

// OR(||) 단축 연산자.(F면 뒤를 확인하는 방식. T면 뒤 확인 안하고 바로 true)
console.log("condition1 || condition2: ", condition1 || condition2); // T T. true 출력
console.log("condition1 || condition3: ", condition1 || condition3); // T F. true 출력
console.log("condition3 || condition2: ", condition3 || condition2); // F T. true 출력
console.log("condition4 || condition3: ", condition4 || condition3); // F F. false 출력

console.log("!true: ", !true); //false
console.log("!false: ", !false); //true

// 삼항연산자
// 조건 ? 참일 때 : 거짓일 때
// python : true일 때 if조건 else 거짓일 때
console.log(100 > 10 ? "100이 10보다 크네" : "100이 10보다 작네");
console.log(100 < 10 ? "100이 10보다 크네" : "100이 10보다 작네");

// 타입 반환 연산자
console.log(typeof 42); // number 출력
console.log(typeof "hello"); // string 출력
console.log(typeof null); // object 출력. 유명한 버그. (null의 타입은 object????)
console.log(typeof undefined); // undefined 출력

let arr = [];
console.log(arr instanceof Array); // true 출력
