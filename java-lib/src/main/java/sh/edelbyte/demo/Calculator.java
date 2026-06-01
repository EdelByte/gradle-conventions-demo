package sh.edelbyte.demo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Calculator {
    
    public int add(int a, int b) {
        log.debug("Adding {} and {}", a, b);
        return a + b;
    }
    
    public int subtract(int a, int b) {
        log.debug("Subtracting {} from {}", b, a);
        return a - b;
    }
}
