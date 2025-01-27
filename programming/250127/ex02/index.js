class Programmer{
    constructor(language, device, disease, nation, age, junior, coffee, stress, eye) {
        this.language = language;   // this는 현재 객체를 가리킵니다.
        this.device = device;
        this.disease = disease;
        this.nation = nation;
        this.age = age;
        this.junior = junior;
        this.coffee = coffee;
        this.stress = stress;
        this.eye = eye;
    }
    introduce() {
        console.log(
          `안녕하세요, 저는 ${this.age}살 ${this.nation} 출신 개발자입니다. 주로 ${
            this.language
          } 언어를 사용하며, ${this.device}로 개발합니다. ${
            this.coffee ? "커피를 좋아합니다." : "커피를 즐기지 않습니다."
          } ${this.junior ? "주니어 개발자입니다." : "경력 개발자입니다."} 눈은 ${
            this.eye
          }이고, 스트레스 지수는 ${this.stress}입니다. ${
            this.disease ? "지병이 있습니다." : "건강합니다."
          }`
        );
    }
}

const programmers = [
    new Programmer("JavaScript", "MacBook Pro", false, "한국", 28, true, true, 8, "갈색"),
    new Programmer("Python", "Dell XPS", false, "미국", 25, true, true, 5, "파란색"),
    new Programmer("Java", "ThinkPad", true,"한국", 30, false, true, 9, "검은색"),
    new Programmer("C++", "MacBook Air", false, "일본", 27, false, false, 6, "갈색"),
    new Programmer("JavaScript", "LG Gram", true, "한국", 23, true, true, 7, "초록색"),
];
  
for (const programmer of programmers) {
    programmer.introduce();
}

// 상속
class JavaScriptProgrammer extends Programmer {
    constructor(language, device, disease, nation, age, junior, coffee, stress, eye, framwork){
        super(language, device, disease, nation, age, junior, coffee, stress, eye);
        this.framwork = framwork;
    }
    introduce() {
        console.log(
          `안녕하세요, 저는 ${this.age}살 ${this.nation} 출신 개발자입니다. 주로 ${
            this.language
          } 언어를 사용하며, ${this.device}로 개발합니다. ${
            this.framework
          } 프레임워크를 사용합니다. ${
            this.coffee ? "커피를 좋아합니다." : "커피를 즐기지 않습니다."
          } ${this.junior ? "주니어 개발자입니다." : "경력 개발자입니다."} 눈은 ${
            this.eye
          }이고, 스트레스 지수는 ${this.stress}입니다. ${
            this.disease ? "지병이 있습니다." : "건강합니다."
          }`
        );
    }
}

const js = new Programmer("JavaScript", "MacBook Pro", false, "한국", 31, false, true, 3, "갈색");
const js2 = new JavaScriptProgrammer("JavaScript", "MacBook Air", false, "미국", 26, true, false, 5, "파란색", "React");
js.introduce();
js2.introduce();

const jsProgrammers = [
    new JavaScriptProgrammer("JavaScript", "MacBook Pro", false, "한국", 28, true, true,8, "갈색", "React"),
    new JavaScriptProgrammer("JavaScript", "Dell XPS", false, "미국",25, true, true, 5, "파란색", "Angular"),
    new JavaScriptProgrammer("JavaScript", "ThinkPad", true, "한국", 30, false, true, 9, "검은색", "Vue"),
];

for (const jsProgrammer of jsProgrammers) {
    jsProgrammer.introduce();
}  

class Senior {
    constructor(){
        //this.money = 0;
        this._money = 0;
    }
    get money() {
        return this._money;
    }

    set money(val) {
        this._money = val;
    }

    money2(){
        return 10000;
    }

    money3(newMoney){
        this._money = newMoney;
    }
}

const young = new Senior();
// console.log(young.money);
console.log(young._money); //가능은 하지만 쓰지 마세요(이 상태에서도 수정은 가능하다)
young.money = 1000;
console.log("young.money : ", young.money);
console.log("young.money2 : ", young.money2);
console.log("young.money2() : ", young.money2());
console.log("young.money3 : ", young.money3);
console.log("young.money : ", young.money);
console.log("young.money3() : ", young.money3());
console.log("young.money : ", young.money);
console.log("young.money3(100000) : ", young.money3(100000));
console.log(young.money);