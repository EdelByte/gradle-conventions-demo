package sh.edelbyte.demo.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sh.edelbyte.demo.domain.Excuse;
import sh.edelbyte.demo.lib.ExcuseGenerator;
import sh.edelbyte.demo.lib.FixEstimator;

@RestController
@RequestMapping("/api/excuses")
public class ExcuseController {

    private final ExcuseGenerator excuseGenerator;
    private final FixEstimator fixEstimator;

    public ExcuseController(ExcuseGenerator excuseGenerator, FixEstimator fixEstimator) {
        this.excuseGenerator = excuseGenerator;
        this.fixEstimator = fixEstimator;
    }

    @GetMapping
    public ExcuseResponse getExcuse() {
        Excuse excuse = excuseGenerator.getRandomExcuse();
        return new ExcuseResponse(
            excuse.getCategory().name(),
            excuse.getDescription(),
            fixEstimator.estimateFixDuration().getPhrase()
        );
    }

    public record ExcuseResponse(String category, String description, String duration) {}
}
