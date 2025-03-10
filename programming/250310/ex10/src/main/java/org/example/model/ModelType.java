package org.example.model;

public enum ModelType {
    GROQ_LLAMA("llama-3.3-70b-versatile", ModelPlatform.GROQ),
    TOGETHER_LLAMA("meta-llama/Llama-3.3-70B-Instruct-Turbo-Free", ModelPlatform.TOGETHER);

    ModelType(String name, ModelPlatform platform) {
        this.name = name;
        this.platform = platform;
    }

    public final String name;
    public final ModelPlatform platform;
}