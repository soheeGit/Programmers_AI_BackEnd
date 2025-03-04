import llm.LLM;
import llm.ModelCategory;
import slack.Slack;
import util.logger.MyLogger;
import util.secret.NoEnvException;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * Application 클래스는 LLM 요청을 병렬로 처리하고 결과를 Slack으로 전송하는 메인 로직을 포함한다.
 */
public class Application {

    public static void main(String[] args) throws NoEnvException, InterruptedException, IOException, ExecutionException {
        // 로깅 인스턴스 생성
        MyLogger logger = MyLogger.getLogger();
        Slack slack = new Slack();
        LLM llm = new LLM();

        // 시작 시간 기록
        long startTime = System.currentTimeMillis();
        logger.info("START!");

        // 초기 AI 결과 생성 (짧은 설명 요청)
        String aiResult = llm.sendPrompt(ModelCategory.LLAMA, "What is Java? No markdown, No escape character, nut shell");

        // 병렬 작업을 위한 스레드 풀 생성
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 이미지 생성 작업을 병렬로 제출
        LLMTask imageTask = new LLMTask(
                ModelCategory.FLUX,
                "Create thumbnail images for '%s'.".formatted(aiResult)
        );
        Future<String> imageFuture = executor.submit((Callable<String>) imageTask);

        // 상세한 설명 생성을 병렬로 제출
        LLMTask reasoningTask = new LLMTask(
                ModelCategory.R1,
                "A fairly detailed and complex description of '%s'. Without markdown and escape characters. No more than 500 characters. only use korean character and english character and use korean. Translate all Chinese and Chinese characters that can be translated into Korean if possible, and English if not possible. Finally, review your compliance with the restrictions so far."
                        .formatted(aiResult)
        );
        Future<String> reasoningFuture = executor.submit((Callable<String>) reasoningTask);

        LLMTask reasoningTask2 = new LLMTask(
                ModelCategory.R1,
                "Write tips to help you get a job based on '%s'. Without markdown and escape characters. No more than 500 characters. only use korean character and english character and use korean. Translate all Chinese and Chinese characters that can be translated into Korean if possible, and English if not possible. Finally, review your compliance with the restrictions so far."
                        .formatted(aiResult)
        );
        Future<String> reasoningFuture2 = executor.submit((Callable<String>) reasoningTask2);

        // 모든 작업의 결과를 받아옴
        String imageResult = imageFuture.get();
        String reasoningResult = reasoningFuture.get();
        String reasoningResult2 = reasoningFuture2.get();

        // Slack에 결과 메시지 전송
        slack.sendMessage("%s %s".formatted(reasoningResult, reasoningResult2), imageResult);

        // 스레드 풀 종료
        executor.shutdown();

        // 종료 시간 및 소요 시간 로깅
        logger.info("FINISH! %d".formatted(System.currentTimeMillis() - startTime));
    }
}

/**
 * LLMTask 클래스는 LLM 모델을 통해 주어진 프롬프트에 대한 응답을 얻어오는 작업을 정의한다.
 * 이 클래스는 Callable 및 Runnable 인터페이스를 구현해 ExecutorService에서 실행 가능하다.
 */
class LLMTask implements Runnable, Callable<String> {
    private final LLM llm;
    private final String prompt;
    private final ModelCategory model;
    private String result;

    /**
     * LLMTask 생성자
     *
     * @param model 사용할 LLM 모델의 카테고리
     * @param prompt 모델에 전달할 프롬프트 문자열
     * @throws NoEnvException 환경 변수 문제가 발생할 경우 던져짐
     */
    LLMTask(ModelCategory model, String prompt) throws NoEnvException {
        llm = new LLM();
        this.prompt = prompt;
        this.model = model;
    }

    /**
     * Runnable 인터페이스 구현 메서드.
     * ExecutorService가 이 작업을 실행할 때 사용됨.
     */
    @Override
    public void run() {
        try {
            result = llm.sendPrompt(model, prompt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Callable 인터페이스 구현 메서드.
     * 작업의 결과값을 Future로 반환함.
     *
     * @return LLM 모델로부터 받은 응답 문자열
     * @throws Exception 작업 수행 중 발생할 수 있는 예외
     */
    @Override
    public String call() throws Exception {
        run();
        return result;
    }
}