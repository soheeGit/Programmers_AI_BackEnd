try {
    //   const a = "";
    //   const a = ""; // SyntaxError는 막아주지 못함
    let a = null; // 객체가 아니라 null에게 프로퍼티 접근을 시도할 경우 실행 중 에러가 남
    console.log(a.a);
  } catch {
    console.error("에러!");
  }
  
  try {
    let a = null; // 객체가 아니라 null에게 프로퍼티 접근을 시도할 경우 실행 중 에러가 남
    console.log(a.a);
  } catch (error) {
    if (error instanceof TypeError) {
      console.log("타입 에러네요!");
    }
    console.error(error);
  }
  
  // finally
  
  try {
    const n = null;
    n.n;
    console.log(1);
  } catch {
    // 에러가 없으니까...?
    console.log(2);
  } finally {
    console.log(3);
  }
  console.log(4);
  
  function fin() {
    // ???
    try {
      const r = Math.random();
      if (r > 0.5) {
        null.n;
      }
      console.log("살았다!");
      return r;
    } catch {
      console.log("앗!");
      return -1;
    } finally {
      // 비활성화 표시가 안돼? 버근가??
      // 무조건 실행이 되고... 이 return은 모든 걸 이김...
      console.log("하핫!");
      return -100;
    }
    // return이 있으면 도달하지 않아요...
    console.log("헤헷!");
  }
  
  console.log(fin());
  
  // 다시 던지기, throw해서 흐름 차단하기
  
  try {
    // 확률적 탈출인데... 2중 확률이야 근데 함수도 루프도 아냐...
    if (Math.random() > 0.5) {
      // throw new Error("1차 탈출!!!");
      throw new Error("아이디가 일치하지 않습니다");
    }
    if (Math.random() > 0.5) {
      //   throw new Error("2차 탈출!!!");
      throw new Error("비밀번호가 일치하지 않습니다");
    }
  } catch (error) {
    console.log(error);
  }
  
  try {
    try {
      // 확률적 탈출인데... 2중 확률이야 근데 함수도 루프도 아냐...
      if (Math.random() > 0.5) {
        // throw new Error("1차 탈출!!!");
        throw new Error("아이디가 일치하지 않습니다");
      }
      if (Math.random() > 0.5) {
        //   throw new Error("2차 탈출!!!");
        throw new Error("비밀번호가 일치하지 않습니다");
      }
    } catch (error) {
      console.log(error);
      throw error;
    }
  } catch {
    console.log("아 뭔가 에러가;;;;");
  }
  
  // 스코프
  const a = 0; // 바깥 블록.
  let b = 0;
  console.log(b);
  try {
    // try 블록
    if (Math.random() > 0.5) null.n;
    const a = 0;
    b++;
    console.log("try", a, b);
  } catch {
    // catch 블록
    const a = 0;
    b++;
    console.log("catch", a, b);
  } finally {
    // finally 블록
    const a = 0;
    b++;
    console.log("finally", a, b);
  }
  console.log(a, b);
  