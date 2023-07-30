package dev.donggi.core.api.core.question.dto;

import dev.donggi.core.api.core.common.PageDto;
import dev.donggi.core.api.core.question.application.dto.QuestionDto;
import dev.donggi.core.api.core.question.domain.Question;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
public class QuestionByCategoryPaginationDto {

    private String category;
    private List<QuestionDto> questions = new ArrayList<>();
    private PageDto page;

    public static QuestionByCategoryPaginationDto ofEntity(Slice<Question> sliceQuestion, String category) {
        QuestionByCategoryPaginationDto questionPaginationDto = new QuestionByCategoryPaginationDto();
        questionPaginationDto.category = category;

        List<Question> questions = sliceQuestion.getContent();
        questionPaginationDto.questions = questions.stream()
            .map(QuestionDto::ofEntity)
            .collect(Collectors.toList());

        int questionsSize = questions.size();
        long nextId = questions.isEmpty() ? 0L : questions.get(questionsSize - 1).getId();
        questionPaginationDto.page = new PageDto(questionsSize, nextId, sliceQuestion.isLast());

        return questionPaginationDto;
    }
}
