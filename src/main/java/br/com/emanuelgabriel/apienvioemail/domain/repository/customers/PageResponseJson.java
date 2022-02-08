package br.com.emanuelgabriel.apienvioemail.domain.repository.customers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.PageImpl;

import java.io.IOException;

/**
 * @author emanuel.sousa
 * @since 08/02/2022
 *
 */

@JsonComponent
public class PageResponseJson {

    public static class PageSerializer extends JsonSerializer<PageImpl<?>> {

        @Override
        public void serialize(PageImpl<?> page, JsonGenerator jsonGenerator, SerializerProvider serializers)
                throws IOException {

            jsonGenerator.writeStartObject();

            // customização do Page
            jsonGenerator.writeObjectField("itens", page.getContent()); // conteúdo
            jsonGenerator.writeNumberField("totalPaginas", page.getTotalPages()); // total de páginas
            jsonGenerator.writeNumberField("quantidadePorPagina", page.getSize()); // retorna o tamanho de itens do Page
            // - quantidade por página
            jsonGenerator.writeObjectField("totalElementos", page.getTotalElements()); // total de elementos

            jsonGenerator.writeEndObject();
        }

    }



}
