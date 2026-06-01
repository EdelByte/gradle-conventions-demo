package sh.edelbyte.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {
    
    private final Calculator calculator = new Calculator();
    
    @Test
    void testAdd() {
        assertThat(calculator.add(2, 3)).isEqualTo(5);
    }
    
    @Test
    void testSubtract() {
        assertThat(calculator.subtract(5, 3)).isEqualTo(2);
    }
}
