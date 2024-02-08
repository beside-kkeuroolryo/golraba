package donggi.dev.kkeuroolryo.question.application.dto;

import donggi.dev.kkeuroolryo.common.PageDto;
import donggi.dev.kkeuroolryo.question.domain.Question;
import java.util.List;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
public class QuestionPaginationDto {

    private List<QuestionDto> questions;
    private PageDto page;

    public static QuestionPaginationDto ofEntity(Slice<Question> sliceQuestions) {
        QuestionPaginationDto questionPaginationDto = new QuestionPaginationDto();

        List<Question> questions = sliceQuestions.getContent();
        questionPaginationDto.questions = questions.stream()
            .map(QuestionDto::ofEntity)
            .toList();

        int postsSize = questions.size();
        long nextId = questions.isEmpty() ? 0L : questions.get(postsSize - 1).getId();
        questionPaginationDto.page = new PageDto(postsSize, nextId, sliceQuestions.isLast());

        return questionPaginationDto;
    }
}
