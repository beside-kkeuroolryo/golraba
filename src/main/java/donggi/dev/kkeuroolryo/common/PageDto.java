package donggi.dev.kkeuroolryo.common;

import lombok.Getter;

@Getter
public class PageDto {

    private final long size;
    private final long nextId;
    private final boolean last;

    public PageDto(long size, long nextId, boolean last) {
        this.size = size;
        this.nextId = nextId;
        this.last = last;
    }
}
