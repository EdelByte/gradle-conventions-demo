package sh.edelbyte.demo.domain;

import lombok.Value;

@Value
public class Excuse {
    Category category;
    String description;
    
    public enum Category {
        SLOW_BUILD,
        FAILED_BUILD
    }
}
