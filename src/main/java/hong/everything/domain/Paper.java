package hong.everything.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Paper {
    private Long id;
    private String words;

    public Paper() {
    }

    public Paper(String words) {
        this.words = words;
    }
}
