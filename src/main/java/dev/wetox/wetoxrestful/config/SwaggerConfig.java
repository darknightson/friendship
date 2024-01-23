package dev.wetox.wetoxrestful.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.swagger.v3.oas.models.security.SecurityScheme.*;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        // swagger token 은 차후에 쓸 수도 있으므로 코드만 작성
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes("bearerAuth", new io.swagger.v3.oas.models.security.SecurityScheme()
                        .type(Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .in(In.HEADER)
                        .name("Authorization")))
                .info(apiInfo());
    }

    private Info apiInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("1. 테스트를 위해 유저는 InitDB.java 에서 생성하였습니다. \n");
        sb.append("2. 먼저 post 매핑을 통한 친구 관계를 신청하세요 \n");
        sb.append("3. 다음으로 나에게 온 요청을 확인하세요 \n");
        sb.append("4. put 요청으로 친구요청을 수락하세요 \n");
        sb.append("5. 상대방도 친구 요청을 위와 같이 해주셔야 accept 친구 관계가 성립 됩니다. \n");

        return new Info()
                .title("Friendship API")
                .description(sb.toString())
                .version("1.0.0");
    }
}
