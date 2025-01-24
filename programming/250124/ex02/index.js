let integer = 4; //정수
console.log(integer);
let float = 3.141592; //실수
console.log(float);

// 문자열은 보통 작은따옴표나 큰따옴표로 묶여있다다
let string = "Hello"; //문자열
let emptyString = ""; //빈 문자열 (들어있는 문자가 없음)
// length, size ...
console.log(`안녕하세요! ${string}`); // 템플릿 리터럴 문법(변수를 ${} 안에 넣어 바로 표현)

// boolean, bool. -> jeorge bool이 만듦.
let isCold = true; // 1+
let isHard = false; // 0, 아예없거나, 애초에 존재하지도 않았던 것들. 빈 것들

// undefined: defined 되지 않은 것. 정의되지 않았다?
let memory;
console.log("메모리: " + memory); //undefined 출력

// null
let areYouReady = null;
console.log("areYouReady: " + areYouReady); //null 출력
//undefined는 세상에 있어본적이 없는 것. null은 일부러 비워놓은 것

// object
let person = {
  name: "jsh",
  job: "tutor",
  hunger: 100,
};
console.log(person); // { name: 'jsh', job: 'tutor', hunger: 100 }
console.log(person.job); // tutor
console.log(person["job"]); // tutor
console.log(person.school); // undefined
console.log(person["school"]); // undefined

// array: 꼭 같은 타입일 필요는 없다. 0.5 인덱스도 가능하다.
let array = [1, 2, 3];
console.log(array); //[ 1, 2, 3 ] 출력
array.push("4");
console.log(array); //[ 1, 2, 3, '4' ] 출력
array.pop();
console.log(array); //[ 1, 2, 3 ] 출력
array.push(4);
console.log(array); //[ 1, 2, 3, 4 ] 출력
console.log(array.join(" ")); // 1 2 3 4 출력
