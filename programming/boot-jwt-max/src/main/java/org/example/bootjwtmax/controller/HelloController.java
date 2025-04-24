package org.example.bootjwtmax.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@Tag(name = "Greeting API", description = "간단한 인사말을 반환하는 엔드포인트") // [1] 클래스 레벨 Tag 애너테이션 추가
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "JWT Bearer token"
)
public class HelloController {

    @Operation( // [2] 메소드 레벨 Operation 애너테이션 추가
            summary = "Hello World 메시지 가져오기", // Operation 요약
            description = "이 엔드포인트는 'Hello World' 문자열을 반환합니다.", // Operation 상세 설명
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = { // [3] 메소드 레벨 ApiResponses/ApiResponse 애너테이션 추가
            @ApiResponse(responseCode = "200", description = "성공적으로 메시지 반환") // 200 OK 응답 설명
            // 필요에 따라 다른 응답 코드 (예: 400, 404, 500 등)에 대한 ApiResponse 추가 가능
    })
    @GetMapping
    public String hello() {
        return "Hello World";
    }

    // 주의!!! 얘 없으면 JWT 적용 안된당
    @Operation(
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/me")
    public String me(Authentication authentication) {
        return "%s, %s".formatted(authentication.getName(), authentication.getAuthorities());
    }
}