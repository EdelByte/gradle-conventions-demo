package sh.edelbyte.demo.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sh.edelbyte.demo.domain.Excuse;
import sh.edelbyte.demo.lib.ExcuseGenerator;

@RestController
@RequestMapping("/api/excuses")
public class ExcuseController {

    private final ExcuseGenerator excuseGenerator;

    public ExcuseController(ExcuseGenerator excuseGenerator) {
        this.excuseGenerator = excuseGenerator;
    }

    @GetMapping
    public ExcuseResponse getExcuse() {
        Excuse excuse = excuseGenerator.getRandomExcuse();
        return new ExcuseResponse(excuse.getCategory().name(), excuse.getDescription());
    }

    public record ExcuseResponse(String category, String description) {}
}
