package ru.avalc.t1test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.avalc.t1test.domain.StringToProcess;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.avalc.t1test.domain.StringToProcess.*;

/**
 * @author Alexei Valchuk, 19.09.2023, email: a.valchukav@gmail.com
 */

@ExtendWith({SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StringControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testWhenStringIsNull() throws Exception {
        StringToProcess stringToProcess = new StringToProcess();
        stringToProcess.setInput(null);

        ResultActions response = mockMvc.perform(post("/t1/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(stringToProcess)));

        response
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(MESSAGE_WHEN_NULL + "."));
    }

    @Test
    public void testWhenStringIsEmpty() throws Exception {
        StringToProcess stringToProcess = new StringToProcess();
        stringToProcess.setInput("");

        ResultActions response = mockMvc.perform(post("/t1/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(stringToProcess)));

        response
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(MESSAGE_WHEN_INVALID_SIZE + "."));
    }

    @Test
    public void testWhenStringIsTooLarge() throws Exception {
        StringToProcess stringToProcess = new StringToProcess();
        stringToProcess.setInput("1".repeat(MAX_INPUT_SIZE + 1));

        ResultActions response = mockMvc.perform(post("/t1/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(stringToProcess)));

        response
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(MESSAGE_WHEN_INVALID_SIZE + "."));
    }

    @Test
    public void testWhenOk() throws Exception {
        StringToProcess stringToProcess = new StringToProcess();
        stringToProcess.setInput("aaaaabcccc");

        ResultActions response = mockMvc.perform(post("/t1/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(stringToProcess)));

        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.output.a").value(5))
                .andExpect(jsonPath("$.output.c").value(4))
                .andExpect(jsonPath("$.output.b").value(1));
    }
}
