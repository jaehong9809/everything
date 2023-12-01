package hong.everything.web;


import com.zaxxer.hikari.HikariDataSource;
import hong.everything.domain.Paper;
import hong.everything.repository.PaperRepository;
import hong.everything.repository.PaperRepositoryV2;
import hong.everything.repository.PaperRepositoryV3;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

import static hong.everything.repository.ConnectionConst.*;

@Controller
public class WebController {
    DataSource dataSource;
    private final PaperRepositoryV3 paperRepository;
    public WebController(DataSource dataSource) {
        this.paperRepository = new PaperRepositoryV3(dataSource);
    }

    @GetMapping
    public String main() {
        return "application/main";
    }

    @GetMapping("/note")
    public String note(Model model) {
        model.addAttribute("papers", paperRepository.findAll());
        return "application/note";
    }

    @PostMapping("/note")
    public String note2(@RequestParam("words") String words, Model model) {
        paperRepository.save(words);
        model.addAttribute("papers", paperRepository.findAll());

        return "redirect:/note";
    }

    @GetMapping("/note/edit")
    public String editNote(Model model) {
        return "application/editNote";
    }

    @PostMapping("/note/edit")
    public String editNote2(@ModelAttribute("paper") Paper paper, Model model) {
        paperRepository.update(paper.getId(), paper.getWords());
        return "redirect:/note";
    }

    @PostMapping("/note/delete")
    public String deleteNote(@RequestParam("id") Long id) {
        paperRepository.delete(id);
        return "redirect:/note";
    }

   /* private void tmpDATASAVE() {
        Paper paper1 = new Paper("가나다라가 마바사구나");
        Paper paper2 = new Paper("아요오요우이가 으이구나");
        paperRepository.save(paper1);
        paperRepository.save(paper2);
    }*/


}
