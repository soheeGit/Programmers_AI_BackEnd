const obj = {name: "kim", age: 15};
console.log(obj);
// console.log(obj.keys());
console.log(Object.keys(obj));  // object 타입의 객체를 담으면...
// 유틸리티 메서드. 스태틱 메서드(자바에서 자세히 설명)

console.log(Object.values(obj));
console.log(Object.entries(obj));

const objk = Object.keys(obj);
for(let index = 0; index < Array.length; index++) {
    const element = objk[index];
    // console.log(objk[index]);
    console.log("element : ", element);
}

// index는 하나씩 늘어나야하니까 값이 계속 저장되어야 하는데, v는 저장 안되어도 괜찮다.
for(const v of objk) {
    console.log("v : ", v);
}

for(const key in obj){
    console.log(key, obj[key]);
}
// sort push 같은 건 생략... 
// 1. 왜? java 때 유사한 거 많이 함
// 2. 함수형 프로그래밍 할 때 배열과 관련된 묶음처리 관련돼서 많은 메서드 다시 등장 
// 3. 화면/데이터/네트워크 관련돼서 여러 데이터 처리(정렬, 맵, 리듀스, 필터)... 어쩔 수 없이 들어감.