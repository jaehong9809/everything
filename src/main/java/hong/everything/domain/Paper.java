package hong.everything.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Paper {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String words;

    public Paper() {
    }

    public Paper(String words) {
        this.words = words;
    }
}
