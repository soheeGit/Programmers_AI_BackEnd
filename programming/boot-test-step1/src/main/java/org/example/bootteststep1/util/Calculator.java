package org.example.bootteststep1.util;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
//        return 8; // 테스트가 뭔지 알고 있다면?
        // if 문으로도 가능하겠죠?
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            // 런타임 에러, 컴파일 에러
            throw new IllegalArgumentException("0으로 나눌 수 없습니다"); // 커스텀으로 에러 종류를 바꿔버림
            // 0으로 나눈게 문제가 아니라 0을 패러미터로 넣은게 문제야!
        }
        return a / b;
    }

    public boolean isEven(int a) {
        return a % 2 == 0;
    }
}