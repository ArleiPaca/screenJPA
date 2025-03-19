package br.com.arlei.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {
    public static String obterTraducao(String texto) {
        OpenAiService service = new OpenAiService("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");

        CompletionRequest requisicao = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt("traduza para o português o texto: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var resposta = service.createCompletion(requisicao);
        return resposta.getChoices().get(0).getText();
    }
}