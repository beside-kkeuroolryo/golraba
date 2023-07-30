package dev.donggi.core.api.core.question.application.dto;

import dev.donggi.core.api.core.common.PageDto;
import dev.donggi.core.api.core.question.domain.Question;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
public class QuestionPaginationDto {

    private String content;
    private List<QuestionDto> questions = new ArrayList<>();
    private PageDto page;

    public static QuestionPaginationDto ofEntity(Slice<Question> sliceQuestion, String content) {
        QuestionPaginationDto questionPaginationDto = new QuestionPaginationDto();
        questionPaginationDto.content = content;

        List<Question> questions = sliceQuestion.getContent();
        questionPaginationDto.questions = questions.stream()
            .map(QuestionDto::ofEntity)
            .collect(Collectors.toList());

        int postsSize = questions.size();
        long nextId = questions.isEmpty() ? 0L : questions.get(postsSize - 1).getId();
        questionPaginationDto.page = new PageDto(postsSize, nextId, sliceQuestion.isLast());

        return questionPaginationDto;
    }
}
