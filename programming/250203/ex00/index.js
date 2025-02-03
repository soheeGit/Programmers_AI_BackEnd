// 함수 선언식

myFun();    //호이스팅

function myFun(){
    console.log("myFun");
}

console.log(myFun);
console.log(myFun());
myFun();

// 함수 표현식

// myFun2();  //호이스팅 안됨.  ReferenceError: Cannot access 'myFun2' before initialization

const myFun2 = function(){
    console.log("myFun2");
}

myFun2();

// 화살표 함수(arrow function)

// () => {} // 함수의 형태

function yourFun(){
    return true;
}
const yourFun2 = function(){
    return true;
}
const yourFun3 = () => {return true};
const yourFun4 = () => true;
const yourFun5 = a => true; // 매개변수가 하나일 때는 괄호 생략 가능. 린팅, 규칙에 따라서 가독성에서는 넣는 걸 권장.

// undefined! 
function ourFun(a = "aaa", b, c = "ccc"){
    console.log(a, b, c);
}

ourFun();
ourFun(1);
ourFun(1, 2);
ourFun(1, 2, 3);

// 나머지 매개변수(rest parameter)
function ourFun2(...arr){
    console.log(arr);
}

console.log();
console.log(1);
console.log([]);
console.log({});
console.log(1, "", 3);

// map. 사상.
const arr = ["Python", "JavaScript", "Java", "C++"];
// 권장: 문자열 처리 따로 공부할 것.(메서드들)
const newArr = [];
for(const v of arr){
    newArr.push(v.toLowerCase());   // 소문자로 변환
    console.log(newArr);
}

const mapArr = arr.map(function(el){
    // vscode 알아서 추론해준 것.
    return el.toLowerCase();
})
console.log(mapArr);
console.log(Object.is(mapArr, arr));

// filter
const ddbk = [{ price: 15000 }, { price: 20000 }, { price: 18000 }];
console.log(
    ddbk.filter(function(el){
        return el.price > 17000;
    })
);

// reduce -> 2개 acc(prev), cur
const elements = [{ name: "월개발" }, { name: "화개발" }, { name: "수개발" }];
// 1. map
console.log(
    elements.map((el) => el.name)
);
// 2. reduce
console.log(
    elements.reduce((acc, cur) => {
        acc.push(cur.name);
        return acc;   // acc-> return된게 다음 acc(prev)로 들어감
    }, [])
);

// 배열 구조 분해 할당
const a = [1, 2, 3];
const [x, y, z] = a;    // 파이썬 언패킹과 비슷
console.log(x, y, z);

// ...스프레드
const b = {name: "john", age: 20};
const {name, age} = b;
const {name: name2, age: age2, job = "programmer"} = b;
console.log(name, name2, job);

// 스프레드 연산자
console.log(a, [...a], Object.is(a, [...a]));   //false
console.log(b, {...b}, Object.is(b, {...b}));   //false