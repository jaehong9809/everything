package hong.everything.web;

import hong.everything.domain.Paper;
import hong.everything.repository.PaperRepository;
import hong.everything.repository.PaperRepositoryV3;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.sql.DataSource;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final PaperRepository repository;
    @GetMapping
    public String main() {
        return "application/main";
    }

    @GetMapping("/note")
    public String note(Model model) {
        model.addAttribute("papers", repository.findAll());
        return "application/note";
    }

    @PostMapping("/note")
    public String note2(@RequestParam("words") String words, Model model) {
        Paper paper = new Paper(words);
        Paper save = repository.save(paper);
        model.addAttribute("papers", repository.findAll());
        return "redirect:/note";
    }

    @GetMapping("/note/edit")
    public String editNote(Model model) {
        return "application/editNote";
    }

    @PostMapping("/note/edit")
    public String editNote2(@ModelAttribute("paper") Paper paper, Model model) {
        repository.update(paper.getId(), paper.getWords());
        return "redirect:/note";
    }

    @PostMapping("/note/delete")
    public String deleteNote(@RequestParam("id") Long id) {
        repository.delete(id);
        return "redirect:/note";
    }

   /* private void tmpDATASAVE() {
        Paper paper1 = new Paper("가나다라가 마바사구나");
        Paper paper2 = new Paper("아요오요우이가 으이구나");
        paperRepository.save(paper1);
        paperRepository.save(paper2);
    }*/


}
