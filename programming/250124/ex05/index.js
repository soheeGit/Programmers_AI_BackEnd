let w = 0;
while (w < 10) {
  console.log("w", w);
  w++;
}

let w2 = 2;
while (true) {
  if (w2 > 100) {
    break;
  }
  w2 **= 2;
  console.log(w2);
}

while (true) {
  if (Math.random() > 0.5) {
    console.log("HEAD!");
    break;
  } else {
    console.log("TAIL ㅠㅠ");
  }
}

for (let index = 0; index < 10; index++) {
  if (index % 2 == 0) {
    continue; // 하던거를 일단 멈춰. 근데 반복은 continue.(stop은 하고.)
  }
  console.log("index", index);
}

for (;;) {
  break;
}
