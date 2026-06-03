package sh.edelbyte.demo;

import lombok.Value;

@Value
public class Excuse {
    ExcuseCategory category;
    String description;
    
    public enum ExcuseCategory {
        SLOW_BUILD,
        FAILED_BUILD
    }
}
