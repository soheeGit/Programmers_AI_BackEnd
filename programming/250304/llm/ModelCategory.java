package llm;

public enum ModelCategory {
    LLAMA("meta-llama/Llama-3.3-70B-Instruct-Turbo-Free"),
    FLUX("black-forest-labs/FLUX.1-schnell-Free"),
    R1("deepseek-ai/DeepSeek-R1-Distill-Llama-70B-free");
    public final String name;

    private ModelCategory(String name) {
        this.name = name;
    }
}