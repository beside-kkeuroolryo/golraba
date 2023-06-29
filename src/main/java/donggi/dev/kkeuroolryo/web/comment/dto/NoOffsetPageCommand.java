package donggi.dev.kkeuroolryo.web.comment.dto;

import donggi.dev.kkeuroolryo.core.comment.domain.exception.NoOffsetPageInvalidException;
import lombok.Getter;

@Getter
public class NoOffsetPageCommand {

    private Long searchAfterId;
    private Long size;

    public NoOffsetPageCommand(String searchAfterId, String size) {
        try {
            this.searchAfterId  = Long.parseLong(searchAfterId);
            this.size  = Long.parseLong(size);

            if (this.searchAfterId < 0L || this.size < 0L) {
                throw new NoOffsetPageInvalidException();
            }
        } catch (NumberFormatException e) {
            throw new NoOffsetPageInvalidException(e);
        }
    }
}
