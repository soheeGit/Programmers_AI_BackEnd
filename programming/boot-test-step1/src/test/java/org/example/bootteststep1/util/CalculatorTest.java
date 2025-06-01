package org.example.bootteststep1.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("계산기 기능 테스트") // Overall display name for the test class
public class CalculatorTest {

    private Calculator calculator; // The instance of the Calculator class to be tested
    private Logger logger; // Logger for outputting information during tests

    /**
     * 각 테스트 메소드 실행 전에 호출됩니다.
     * Calculator 인스턴스를 초기화하고, 로거를 설정합니다.
     * Given/When/Then 구조의 'Given' 단계의 일부로 볼 수 있습니다.
     */
    @BeforeEach
    void setUp() {
        calculator = new Calculator(); // Initialize a fresh calculator for each test
        logger = LoggerFactory.getLogger(getClass()); // Get a logger for this test class
        logger.info("테스트 실행 전 Calculator 인스턴스 초기화"); // Log that setup is complete
        System.out.println("--- 각 테스트 전에 실행됨 ---"); // Console output for clarity
    }

    /**
     * 덧셈 기능을 테스트합니다. 고정된 값으로 정확한 결과가 나오는지 확인합니다.
     * TDD 관점에서 가장 기본적인 성공 케이스를 먼저 작성합니다.
     */
    @Test
    @DisplayName("두 양의 정수를 더하여 올바른 합계 반환")
    void add_positiveNumbers_returnsCorrectSum() {
        // Given: 테스트를 위한 초기 상태 설정
        int a = 5;
        int b = 3;
        logger.debug("덧셈 테스트: a={}, b={}", a, b);

        // When: 테스트 대상 메소드 실행
        int result = calculator.add(a, b);

        // Then: 예상 결과와 실제 결과를 비교하여 검증
        assertEquals(8, result, "5 + 3 = 8 이어야 합니다."); // Custom message for assertion failure
        logger.info("덧셈 테스트 성공: {} + {} = {}", a, b, result);
    }

    /**
     * 덧셈 기능을 테스트합니다. 무작위 값으로도 올바른 결과가 나오는지 확인하여 견고성을 높입니다.
     */
    @Test
    @DisplayName("무작위 두 정수를 더하여 올바른 합계 반환")
    void add_randomNumbers_returnsCorrectSum() {
        // Given: 무작위 숫자를 생성하여 테스트 데이터로 사용
        Random random = new Random();
        int a = random.nextInt(1000) - 500; // -500 ~ 499 범위의 무작위 수
        int b = random.nextInt(1000) - 500;
        logger.debug("무작위 덧셈 테스트: a={}, b={}", a, b);

        // When: Calculator의 add 메소드 호출
        int result = calculator.add(a, b);

        // Then: 자바의 기본 덧셈 연산 결과와 비교
        assertEquals(a + b, result, "무작위 숫자 덧셈 결과가 일치해야 합니다.");
        logger.info("무작위 덧셈 테스트 성공: {} + {} = {}", a, b, result);
    }

    /**
     * 뺄셈 기능을 테스트합니다.
     */
    @Test
    @DisplayName("두 정수를 빼서 올바른 차이 반환")
    void subtract_returnsCorrectDifference() {
        // Given
        int a = 10;
        int b = 4;
        logger.debug("뺄셈 테스트: a={}, b={}", a, b);

        // When
        int result = calculator.subtract(a, b);

        // Then
        assertEquals(6, result, "10 - 4 = 6 이어야 합니다.");
        logger.info("뺄셈 테스트 성공: {} - {} = {}", a, b, result);
    }

    /**
     * 곱셈 기능을 테스트합니다.
     */
    @Test
    @DisplayName("두 정수를 곱하여 올바른 곱 반환")
    void multiply_returnsCorrectProduct() {
        // Given
        int a = 7;
        int b = 6;
        logger.debug("곱셈 테스트: a={}, b={}", a, b);

        // When
        int result = calculator.multiply(a, b);

        // Then
        assertEquals(42, result, "7 * 6 = 42 이어야 합니다.");
        logger.info("곱셈 테스트 성공: {} * {} = {}", a, b, result);
    }

    /**
     * 나눗셈 기능을 테스트합니다.
     */
    @Test
    @DisplayName("두 정수를 나누어 올바른 몫 반환")
    void divide_returnsCorrectQuotient() {
        // Given
        int a = 10;
        int b = 2;
        logger.debug("나눗셈 테스트: a={}, b={}", a, b);

        // When
        int result = calculator.divide(a, b);

        // Then
        assertEquals(5, result, "10 / 2 = 5 이어야 합니다.");
        logger.info("나눗셈 테스트 성공: {} / {} = {}", a, b, result);
    }

    /**
     * 0으로 나누는 예외 상황을 테스트합니다.
     * `IllegalArgumentException`이 발생하는지, 그리고 메시지가 정확한지 검증합니다.
     */
    @Test
    @DisplayName("0으로 나눌 때 IllegalArgumentException 발생")
    void divide_byZero_throwsIllegalArgumentException() {
        // Given
        int a = 10;
        int b = 0; // 0으로 나누는 상황
        logger.debug("0 나눗셈 테스트: a={}, b={}", a, b);

        // When & Then: 특정 예외가 발생하는지 검증
        // assertThrows는 첫 번째 인자로 기대하는 예외 클래스를 받고,
        // 두 번째 인자로 예외를 발생시킬 람다식을 받습니다.
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class, // 기대하는 예외 타입
                () -> calculator.divide(a, b),   // 예외를 발생시킬 코드 블록
                "0으로 나누면 IllegalArgumentException이 발생해야 합니다."
        );

        // Then: 발생한 예외의 메시지를 검증
        assertEquals("0으로 나눌 수 없습니다", thrown.getMessage(), "예외 메시지가 '0으로 나눌 수 없습니다'여야 합니다.");
        logger.warn("0 나눗셈 테스트 성공: 예상대로 IllegalArgumentException 발생");
    }

    /**
     * 짝수 판별 기능을 테스트합니다. 짝수인 경우.
     */
    @Test
    @DisplayName("짝수 판별: 짝수인 경우 true 반환")
    void isEven_whenEven_returnsTrue() {
        // Given
        int a = 4;
        logger.debug("짝수 판별 테스트 (짝수): a={}", a);

        // When
        boolean result = calculator.isEven(a);

        // Then
        assertTrue(result, "4는 짝수이므로 true를 반환해야 합니다.");
        logger.info("짝수 판별 테스트 성공: {}는 짝수", a);
    }

    /**
     * 짝수 판별 기능을 테스트합니다. 홀수인 경우.
     */
    @Test
    @DisplayName("짝수 판별: 홀수인 경우 false 반환")
    void isEven_whenOdd_returnsFalse() {
        // Given
        int a = 7;
        logger.debug("짝수 판별 테스트 (홀수): a={}", a);

        // When
        boolean result = calculator.isEven(a);

        // Then
        assertFalse(result, "7은 홀수이므로 false를 반환해야 합니다.");
        logger.info("짝수 판별 테스트 성공: {}는 홀수", a);
    }

    /**
     * 짝수 판별 기능을 테스트합니다. 0인 경우 (짝수로 간주).
     */
    @Test
    @DisplayName("짝수 판별: 0인 경우 true 반환")
    void isEven_whenZero_returnsTrue() {
        // Given
        int a = 0;
        logger.debug("짝수 판별 테스트 (0): a={}", a);

        // When
        boolean result = calculator.isEven(a);

        // Then
        assertTrue(result, "0은 짝수이므로 true를 반환해야 합니다.");
        logger.info("짝수 판별 테스트 성공: {}는 짝수", a);
    }
}